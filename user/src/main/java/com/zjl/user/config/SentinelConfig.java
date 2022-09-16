package com.zjl.user.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import com.zjl.sentinel.SentinelBlockExceptionHandler;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SentinelConfig {

    @Bean
    public SentinelBlockExceptionHandler mySentinelBlockExceptionHandler(){
        return new SentinelBlockExceptionHandler();
    }

    @Bean
    public FilterRegistrationBean sentinelFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CommonFilter());
        registration.addUrlPatterns("/*");
        // 入口资源关闭聚合
        registration.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
        registration.setName("sentinelFilter");
        registration.setOrder(1);
        return registration;
    }


}
