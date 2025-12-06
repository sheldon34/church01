//package com.example.tryapp2.Controller;
//
//import com.example.tryapp2.Email.EmailQueueProducer;
//import com.example.tryapp2.Email.NewEmailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//    @RestController
//    @RequestMapping("/api/email")
//    @RequiredArgsConstructor
//    @PreAuthorize("hasRole('ADMIN')")
//    public class EmailController {
//        private final EmailQueueProducer emailQueueProducer;
//        private final NewEmailService newEmailService;
//
//        @PostMapping("/broadcast")
//        public ResponseEntity<String> broadcast(@RequestBody BroadcastRequest req){
//            int queued=newEmailService.sendNewsToAllVerifiedUsers(req.subject, req.body);
//            return ResponseEntity.ok("Queued "+queued+" emails for sending");
//
//        }
//        public static record BroadcastRequest(String subject, String body){}
//
//
//}
