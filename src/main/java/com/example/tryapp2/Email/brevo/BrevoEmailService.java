//package com.example.tryapp2.Email;
//
//import org.apache.hc.client5.http.classic.methods.HttpPost;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.core5.http.ClassicHttpResponse;
//import org.apache.hc.core5.http.io.entity.StringEntity;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class BrevoEmailService {
//    @Value("${brevo.api.key}")
//    private String brevoApiKey;
//
//    public void sendEmail(EmailPayLoad emailPayLoad) {
//        try(CloseableHttpClient client= HttpClients.createDefault()){
//            HttpPost post = new HttpPost("https://api.brevo.com/v3/smtp/email");
//            post.setHeader("accept", "application/json");
//            post.setHeader("api-key", brevoApiKey);
//            post.setHeader("content-type", "application/json");
//
//            JSONObject json= new JSONObject();
//            json.put("sender", new JSONObject()
//                    .put("email","mubokasheldonmuboka@gmal.com")
//                    .put("name","Moi University Christian Union(MUCU)"));
//            json.put("to", new org.json.JSONArray()
//                    .put(new JSONObject().put("email",emailPayLoad.getTo())));
//
//            json.put("subject",emailPayLoad.getSubject());
//            json.put("htmlContent",emailPayLoad.getBody());
//
//            post.setEntity(new StringEntity(json.toString()));
//
//            ClassicHttpResponse response = (ClassicHttpResponse) client.execute(post);
//            System.out.println("Brevo response"+response.getCode()+" "+ response.getReasonPhrase());
//
//        }catch(Exception e){
//            System.out.println("Email Sending Failed via brevo"+e.getMessage());
//        }
//    }
//}
