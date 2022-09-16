package com.zjl.subject.config;


import com.zjl.sentinel.SentinelBlockExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SentinelConfig {

    @Bean
    public SentinelBlockExceptionHandler mySentinelBlockExceptionHandler(){
        return new SentinelBlockExceptionHandler();
    }
//
//    @Bean
//    public SentinelRequestOriginParser mySentinelRequestOriginParser(){
//        return new SentinelRequestOriginParser();
//    }

//    @Bean
//    public FilterRegistrationBean sentinelFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new CommonFilter());
//        registration.addUrlPatterns("/*");
//        // 入口资源关闭聚合
//        registration.addInitParameter(CommonFilter.HTTP_METHOD_SPECIFY, "false");
//        registration.setName("sentinelFilter");
//        registration.setOrder(2);
//        registration.setEnabled(true);
//        return registration;
//    }

}
