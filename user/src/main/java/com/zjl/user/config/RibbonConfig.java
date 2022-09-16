package com.zjl.user.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {


    /**
     * 基于服务权重负载
     * @return
     */
    @Bean
    public IRule nacosRule(){
//        return new NacosRule();
    return new NacosVersionRule();
    }



}
