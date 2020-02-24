package com.gaochaojin.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 16:43 2020/2/20
 */
@Configuration
public class RabbitmqConfig {

    @Bean(name = "message")
    public Queue queueMessage() {
        return new Queue("jack.message.response");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange.message.response");
    }

    @Bean
    public Binding bingingExchangeMessage(@Qualifier("message") Queue queueMessage,
                                          TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage)
                .to(exchange)
                .with("jack.message.routeKey.response");

    }

}
