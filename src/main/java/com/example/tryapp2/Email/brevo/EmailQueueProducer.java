//package com.example.tryapp2.Email;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class EmailQueueProducer {
//    private final RabbitTemplate rabbitTemplate;
//
//    public void queueEmail(EmailPayLoad emailPayLoad){
//        rabbitTemplate.convertAndSend(
//                RabbitMqConfig.EXCHANGE_NAME,
//                RabbitMqConfig.ROUTING_KEY,
//                emailPayLoad
//        );
//    }
//
//
//}
