package com.rehoshi.docmgt.config;

import com.rehoshi.docmgt.interceptor.PageInterceptor;
import com.rehoshi.docmgt.interceptor.TokenInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.MultipartConfigElement;

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
         @Bean
        public MultipartConfigElement multipartConfigElement() {
            MultipartConfigFactory factory = new MultipartConfigFactory();
            //文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
            factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
            /// 设置总上传数据总大小10M
            factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
            return factory.createMultipartConfig();
    }
}
