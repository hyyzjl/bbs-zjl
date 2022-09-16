package com.zjl.subject.mqListener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zjl.config.FanoutMQConfig;
import com.zjl.dto.user.dto.JwtTokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@Slf4j
public class RabbitMqListeners {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = FanoutMQConfig.SECOND_FANOUT_QUEUE_NAME, declare = "true", autoDelete = "true"),
            exchange = @Exchange(value = FanoutMQConfig.FANOUT_EXCHANGE_NAME, declare = "true",type = ExchangeTypes.FANOUT)),
            concurrency = "1")
    @RabbitHandler
    public void onMessage(@Payload Message message,
                          @Headers Map<String, Object> headers,
                          Channel channel) throws IOException {
        try {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            final JwtTokenDto jwtTokenDto = JSON.parseObject(msg, JwtTokenDto.class);
            log.info("second fanout consumer receive the message:{}", jwtTokenDto.getToken()+"-----"+ FanoutMQConfig.SECOND_FANOUT_QUEUE_NAME);
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), true);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        }

    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = FanoutMQConfig.FIRST_FANOUT_QUEUE_NAME, declare = "true", autoDelete = "true"),
            exchange = @Exchange(value = FanoutMQConfig.FANOUT_EXCHANGE_NAME, declare = "true",type = ExchangeTypes.FANOUT)),
            concurrency = "1")
    @RabbitHandler
    public void onMessage2(@Payload Message message,
                          @Headers Map<String, Object> headers,
                          Channel channel) throws IOException {
        try {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            final JwtTokenDto jwtTokenDto = JSON.parseObject(msg, JwtTokenDto.class);
            log.info("second fanout consumer receive the message:{}", jwtTokenDto.getToken()+"-----"+ FanoutMQConfig.FIRST_FANOUT_QUEUE_NAME);
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), true);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        }

    }

}
