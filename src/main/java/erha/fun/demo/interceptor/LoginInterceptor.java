package erha.fun.demo.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import erha.fun.demo.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/1/22 12:40 PM
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    private String verifyType;

    public LoginInterceptor(String verifyType) {
        this.verifyType = verifyType;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Intercept URL: {}", request.getRequestURI());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        if(verifyType.equals("Cookie")) {
            return this.verifyByCookie(request, response);
        } else if (verifyType.equals("Header")) {
            return this.verifyByHeader(request, response);
        }
        return false;
    }

    private boolean verifyByCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                legalUser= TokenUtils.verify(token);
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

    private boolean verifyByHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        boolean legalUser = false;
        String token = request.getHeader("Token");
        if(token != null) {
            legalUser = TokenUtils.verify(token);
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
