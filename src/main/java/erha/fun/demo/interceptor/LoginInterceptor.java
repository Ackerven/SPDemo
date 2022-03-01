package erha.fun.demo.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import erha.fun.demo.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/1/22 12:40 PM
 */
@Slf4j
@Component
public class LoginInterceptor extends CustomizedInterceptor {
    public LoginInterceptor() {
    }

    public LoginInterceptor(Integer verifyType) {
        this.verifyType = verifyType;
    }

    @Override
    protected boolean verifyByCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean legalUser = false;
        Cookie[] cookies = request.getCookies();
        String username = "";
        String token = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    username = c.getValue();
                    continue;
                }
                if (c.getName().equals("token")) {
                    token = c.getValue();
                    continue;
                }
            }

            if (token != null && username != null) {
                legalUser = this.checkUser(response, token, username);
                if (!legalUser) {
                    this.writeError(response, 403, "非法用户");
                }
            }
        } else {
            this.writeError(response, 404, "Cookies错误");
        }
        return legalUser;
    }

    @Override
    protected boolean verifyByHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean legalUser = false;
        String token = null;
        String username = null;
        try {
            token = request.getHeader("Token");
            username = request.getHeader("Username").toLowerCase(Locale.ROOT);
        } catch (Exception ex) {
            this.writeError(response, 404, "Header参数错误");
            return false;
        }


        if (token != null) {
            legalUser = this.checkUser(response, token, username);
            if (!legalUser) {
                this.writeError(response, 403, "非法用户");
            }
        } else {
            this.writeError(response, 404, "Token错误");
        }
        return legalUser;
    }

    private boolean checkUser(HttpServletResponse response, String token, String key) throws IOException {
        DecodedJWT verify = null;
        try {
            verify = TokenUtils.verifier.verify(token);
        } catch (Exception ex) {
            this.writeError(response, 404, "Token错误");
            return false;
        }
         return key.equals(verify.getClaim("userName").asString());
    }

}
