package com.doydoit.springmvcjpa.config;

import com.doydoit.springmvcjpa.interceptor.LogInterceptor;
import com.doydoit.springmvcjpa.interceptor.LoginCheckInterceptor;
import com.doydoit.springmvcjpa.interceptor.RoleCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/items", "/members/add", "/logout", "/login", "/css/**", "/*.ico", "/error");

        registry.addInterceptor(new RoleCheckInterceptor())
                .order(3)
                .addPathPatterns("/admin/**");
    }
}
