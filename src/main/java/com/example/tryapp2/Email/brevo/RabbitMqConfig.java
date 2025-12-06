//package com.example.tryapp2.Email;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//
//public class RabbitMqConfig {
//    public static final String QUEUE_NAME="emailQueue";
//    public static final String EXCHANGE_NAME ="emailExchange";
//    public static final String ROUTING_KEY="emailRoutingKey";
//
//
//    @Bean
//    public Queue emailQueue(){
//        return new Queue(QUEUE_NAME, true);
//    }
//    @Bean
//    public DirectExchange emailExchange(){
//
//        return new DirectExchange(EXCHANGE_NAME);
//    }
//
//    @Bean
//    public Binding emailBinding(Queue emailQueue, DirectExchange emailExchange){
//        return BindingBuilder
//                .bind(emailQueue)
//                .to(emailExchange)
//                .with(ROUTING_KEY);
//    }
//
//    /// Explicit Json converter for RabbitMQ messages
//@Bean
//    public MessageConverter jsonConverter(ObjectMapper mapper){
//
//    return new Jackson2JsonMessageConverter(mapper);
//}
//
//
//
//
//
//
//
//}
