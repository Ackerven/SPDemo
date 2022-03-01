package erha.fun.demo.config;

import erha.fun.demo.bean.User;
import erha.fun.demo.interceptor.APIInterceptor;
import erha.fun.demo.interceptor.CustomizedInterceptor;
import erha.fun.demo.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/1/22 12:42 PM
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(CustomizedInterceptor.Cookie))
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/signup");

        registry.addInterceptor(new APIInterceptor(CustomizedInterceptor.Cookie, User.STUDENT))
                .addPathPatterns("/student/**")
                .excludePathPatterns("/login", "/signup");

        registry.addInterceptor(new APIInterceptor(CustomizedInterceptor.Cookie, User.TEACHER))
                .addPathPatterns("/teacher/**")
                .excludePathPatterns("/login", "/signup");
    }
}
