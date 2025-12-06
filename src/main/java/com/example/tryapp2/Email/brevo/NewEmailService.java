//package com.example.tryapp2.Email;
//
//import com.example.tryapp2.Security.SupabaseAdminClient;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class NewEmailService {
//    private final SupabaseAdminClient supabaseAdminClient;
//    private final EmailQueueProducer emailQueueProducer;
//
//    public int sendNewsToAllVerifiedUsers(String subject, String body){
//        List<String> verifiedEmails = supabaseAdminClient.fetchAllVerifiedUserEmails();
//        final int BatchSize = 200; // Define the batch size  avoid overwhelming the queue
//        int queued=0;
//        for(String email:verifiedEmails){
//            EmailPayLoad payLoad = new EmailPayLoad();
//            payLoad.setTo(email);
//            payLoad.setSubject(subject);
//            payLoad.setBody(body);
//            emailQueueProducer.queueEmail(payLoad);
//            queued++;
//            if(queued % BatchSize ==0){
//                try{
//                    Thread.sleep(300); // Pause for 1 second after each batch to avoid bursts (tweak or remove)
//                }catch (InterruptedException e){}
//            }
//        }
//        return queued;
//    }
//
//}
