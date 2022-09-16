package com.zjl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class FanoutMQConfig {

    /**
     * 与fanout绑定的第一个队列
     */
    public static final String FIRST_FANOUT_QUEUE_NAME = "com.zjl.mq.fanout.test1";
    /**
     * 与fanout交换机绑定的第二个队列
     */
    public static final String SECOND_FANOUT_QUEUE_NAME = "com.zjl.mq.fanout.test2";
    /**
     * fanout 交换机
     */
    public static final String FANOUT_EXCHANGE_NAME = "com.zjl.mq.fanout.exchange";



}
