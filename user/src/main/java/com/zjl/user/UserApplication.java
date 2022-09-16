package com.zjl.user;

import com.alibaba.nacos.api.annotation.NacosProperties;
//import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
@MapperScan("com.zjl.user.mapper")
@EnableRabbit
public class UserApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(UserApplication.class, args);

//        while(true) {
//            String userName = applicationContext.getEnvironment().getProperty("user.name");
//            String userAge = applicationContext.getEnvironment().getProperty("user.age");
//            System.err.println("user name :"+userName+"; age: "+userAge);
//            TimeUnit.SECONDS.sleep(1);
//        }
    }
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        return jackson2JsonMessageConverter;
    }


}

