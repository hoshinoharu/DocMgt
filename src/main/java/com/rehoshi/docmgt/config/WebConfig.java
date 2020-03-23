package com.rehoshi.docmgt.config;

import com.rehoshi.docmgt.interceptor.PageInterceptor;
import com.rehoshi.docmgt.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        //添加token过滤器
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**")//过滤所有请求 排除 登录和注册请求
                .excludePathPatterns("/user/login", "/user/register");
        registry.addInterceptor(new PageInterceptor())
                .addPathPatterns("/**");

    }
}
