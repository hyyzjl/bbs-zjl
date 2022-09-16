package com.zjl.user.config;

import com.zjl.config.FanoutMQConfig;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFanoutMQConfig {


    /**
     * FanoutExchange,持久化、非自动删除
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FanoutMQConfig.FANOUT_EXCHANGE_NAME);
    }

    @Bean
    public Queue firstFanoutQueue() {
        return new Queue(FanoutMQConfig.FIRST_FANOUT_QUEUE_NAME);
    }

    @Bean
    public Queue secondFanoutQueue() {
        return new Queue(FanoutMQConfig.SECOND_FANOUT_QUEUE_NAME);
    }

    @Bean
    public Binding firstFanoutBinding() {
        return BindingBuilder.bind(firstFanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding secondFanoutBinding() {
        return BindingBuilder.bind(secondFanoutQueue()).to(fanoutExchange());
    }

}
