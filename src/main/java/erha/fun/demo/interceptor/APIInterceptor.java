package erha.fun.demo.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import erha.fun.demo.utils.TokenUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;

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
 * @date 3/1/22 7:57 PM
 */
@Slf4j
@NoArgsConstructor
public class APIInterceptor extends CustomizedInterceptor {

    private Integer APIType;

    public APIInterceptor(String verifyType, Integer APIType) {
        super(verifyType);
        this.APIType = APIType;
    }

    @Override
    protected boolean verifyByCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
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

                Integer role = verify.getClaim("role").asInt();
                legalUser = role.equals(this.APIType);

                if (!legalUser) {
                    map.put("code", 403);
                    map.put("msg", "非法用户");
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

    @Override
    protected boolean verifyByHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        boolean legalUser = false;
        String token = null;
        try {
            token = request.getHeader("Token");
        } catch (Exception ex) {
            map.put("code", 404);
            map.put("msg", "Header参数错误");
            response.getWriter().println(new JSONObject(map));
            response.getWriter().close();
            return false;
        }

        if (token != null) {
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

            Integer role = Integer.parseInt(verify.getClaim("role").asString());
            legalUser = role.equals(this.APIType);

            if (!legalUser) {
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
