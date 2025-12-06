//package com.example.tryapp2.Email;
//
//import jakarta.mail.internet.MimeMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//
//@Component
//
//public class EmailQueueConsumer {
//
//    private final  BrevoEmailService brevoEmailService;
//
//    public EmailQueueConsumer ( BrevoEmailService brevoEmailService1) {
//        this.brevoEmailService = brevoEmailService1;
//
//    }
//@RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
//    public void handleEmailMessage(EmailPayLoad emailPayLoad) {
//        try{
//            brevoEmailService.sendEmail(emailPayLoad);
//            System.out.println("✅Email sent to "+emailPayLoad.getTo());
//        }catch (Exception e){
//            System.err.println("❌Failed to send email to "+emailPayLoad.getTo());
//        }
//}
//}
