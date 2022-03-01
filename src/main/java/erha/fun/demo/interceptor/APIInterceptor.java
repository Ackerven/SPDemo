package erha.fun.demo.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import erha.fun.demo.utils.TokenUtils;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/1/22 7:57 PM
 */
@Slf4j
@NoArgsConstructor
public class APIInterceptor extends CustomizedInterceptor {

    private Integer APIType;

    public APIInterceptor(Integer verifyType, Integer APIType) {
        super(verifyType);
        this.APIType = APIType;
    }

    @Override
    protected boolean verifyByCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean legalUser = false;
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("token")) {
                    token = c.getValue();
                    continue;
                }
            }

            if (token != null) {
                legalUser = this.checkRole(response, token);

                if (!legalUser) {
                    this.writeError(response, 403, "非法用户");
                }
            }
        } else {
            this.writeError(response, 404, "Cookies不存在");
        }
        return legalUser;
    }

    @Override
    protected boolean verifyByHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean legalUser = false;
        String token = null;
        try {
            token = request.getHeader("Token");
        } catch (Exception ex) {
            this.writeError(response, 404, "Header参数错误");
            return false;
        }

        if (token != null) {
            legalUser = this.checkRole(response, token);

            if (!legalUser) {
                this.writeError(response, 403, "非法用户");
            }
        } else {
            this.writeError(response, 404, "Token错误");
        }
        return legalUser;
    }

    private boolean checkRole(HttpServletResponse response, String token) throws IOException {
        DecodedJWT verify = null;
        try {
            verify = TokenUtils.verifier.verify(token);
        } catch (Exception ex) {
            this.writeError(response, 404, "Token错误");
            return false;
        }

        Integer role = verify.getClaim("role").asInt();
        return role.equals(this.APIType);
    }
}
