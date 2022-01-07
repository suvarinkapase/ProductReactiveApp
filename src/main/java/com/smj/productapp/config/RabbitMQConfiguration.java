package com.smj.productapp.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String PRODUCT_QUEUE_A = "product_queueA";
    public static final String PRODUCT_QUEUE_B = "product_queueB";
    public static final String PRODUCT_EXCHANGE = "product_exchange";
    public static final String PRODUCT_ROUTING_KEY_A = "product_routing_keyA";
    public static final String PRODUCT_ROUTING_KEY_B = "product_routing_keyB";


    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.host}")
    private String host;


    @Bean
    Queue queueA() {
        return new Queue(PRODUCT_QUEUE_A, true);
    }

    @Bean
    Queue queueB() {
        return new Queue(PRODUCT_QUEUE_B, true);
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(PRODUCT_EXCHANGE);
    }

    @Bean
    Binding bindingA(Queue queueA,DirectExchange directExchange){
        return BindingBuilder
                .bind(queueA)
                .to(directExchange)
                .with(PRODUCT_ROUTING_KEY_A);
    }

    @Bean
    Binding bindingB(Queue queueB,DirectExchange directExchange){
        return BindingBuilder
                .bind(queueB)
                .to(directExchange)
                .with(PRODUCT_ROUTING_KEY_B);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
//        return new RabbitAdmin(connectionFactory);
//    }


//    @Bean
//    RabbitTemplate rabbitTemplate(){
//        return new RabbitTemplate();
//    }

}
