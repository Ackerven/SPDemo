package erha.fun.demo.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import erha.fun.demo.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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
    public LoginInterceptor() { }

    public LoginInterceptor(String verifyType) {
        this.verifyType = verifyType;
    }

    protected boolean verifyByCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        boolean legalUser = false;
        Cookie[] cookies = request.getCookies();
        String username = "";
        String token = "";
        if(cookies != null) {
            for(Cookie c: cookies) {
                if(c.getName().equals("username")) {
                    username = c.getValue();
                    continue;
                }
                if(c.getName().equals("token")) {
                    token = c.getValue();
                    continue;
                }
            }

            if(token != null && username != null) {
                DecodedJWT verify = null;
                try {
                    verify = TokenUtils.verifier.verify(token);
                } catch (Exception ex) {
                    map.put("code", 404);
                    map.put("msg", "Token错误");
                    response.getWriter().println(new JSONObject(map));
                    response.getWriter().close();
                    return false;
                }
                legalUser = username.equals(verify.getClaim("userName").asString());
                if(!legalUser) {
                    map.put("code", 404);
                    map.put("msg", "Cookies错误");
                    response.getWriter().println(new JSONObject(map));
                    response.getWriter().close();
                }
            }
        } else {
            map.put("code", 404);
            map.put("msg", "Cookies不存在");
            response.getWriter().println(new JSONObject(map));
            response.getWriter().close();
        }
        return legalUser;
    }

    protected boolean verifyByHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        boolean legalUser = false;
        String token = null;
        String username = null;
        try {
            token = request.getHeader("Token");
            username = request.getHeader("Username").toLowerCase(Locale.ROOT);
        } catch (Exception ex) {
            map.put("code", 404);
            map.put("msg", "Header参数错误");
            response.getWriter().println(new JSONObject(map));
            response.getWriter().close();
            return false;
        }


        if(token != null) {
            DecodedJWT verify = null;
            try {
                verify = TokenUtils.verifier.verify(token);
            } catch (Exception ex) {
                map.put("code", 404);
                map.put("msg", "Token错误");
                response.getWriter().println(new JSONObject(map));
                response.getWriter().close();
                return false;
            }
            legalUser = username.equals(verify.getClaim("userName").asString());
            if(!legalUser) {
                map.put("code", 404);
                map.put("msg", "非法用户");
                response.getWriter().println(new JSONObject(map));
                response.getWriter().close();
            }
        } else {
            map.put("code", 404);
            map.put("msg", "Token错误");
            response.getWriter().println(new JSONObject(map));
            response.getWriter().close();
        }
        return legalUser;
    }
}
