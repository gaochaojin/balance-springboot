package com.gaochaojin.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 16:47 2020/2/20
 */
@Component
public class RabbitmqSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMessage(String exchange, String routeKey, String message) {
        try {
            amqpTemplate.convertAndSend(exchange, routeKey, message);
        } catch (AmqpException e) {
            e.printStackTrace();
        }

    }
}
