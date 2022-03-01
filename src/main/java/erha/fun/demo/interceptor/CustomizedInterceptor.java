package erha.fun.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/1/22 7:48 PM
 */
@Slf4j
public abstract class CustomizedInterceptor implements HandlerInterceptor {
    protected String verifyType;

    /**
     * 目标方法执行之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
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

    protected abstract boolean verifyByCookie(HttpServletRequest request, HttpServletResponse response) throws IOException;

    protected abstract boolean verifyByHeader(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
