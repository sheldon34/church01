//package com.example.tryapp2.Security;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.*;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Slf4j
//@Service
//// @RequiredArgsConstructor
//public class SupabaseAuthService {
//    private  final RestTemplate restTemplate;
//    private  final ObjectMapper objectMapper;
//    private final HttpHeaders headers;
//    private final String baseUrl;
//
//
//    public SupabaseAuthService(
//            @Value("${supabase.url}")
//            String supabaseUrl,
//            @Value("${supabase.service.key}")
//            String serviceKey
//
//    ){
//
//        this.baseUrl=supabaseUrl+"/auth/v1";
//        this.restTemplate=new RestTemplate();
//        this.objectMapper=new ObjectMapper();
//        this.headers=new HttpHeaders();
//        headers.set("Authorization", "Bearer " + serviceKey);
//        headers.set("apikey", serviceKey);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//    }
//    //Signing up users
//    public ResponseEntity<String > signUp(String email, String password,String role){
//        try{
//        String url=baseUrl+"/signup";
//        log.info("Supabase signup url: {}", url);
//        Map<String ,Object> body= new HashMap<>();
////        body.put("username",username);
//        body.put("email",email);
//        body.put("password",password);
//        Map<String,Object> userData= new HashMap<>();
//        userData.put("role",role);
//        body.put("data",userData);
//        HttpEntity<Map<String,Object>> entity = new HttpEntity<>(body,headers);
//        return restTemplate.exchange(url,HttpMethod.POST,entity,String.class);}
//        catch(HttpClientErrorException e){
//            throw new HttpClientErrorException(e.getStatusCode(), "Supabase error: " + e.getResponseBodyAsString());
//        }
//        catch (Exception e){
//            log.error("Unexpected error during sign in", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
//        }
//
//
//    }
//    public ResponseEntity <Map<String,Object>> signIn(  String email,String password){
//        String url=baseUrl+"/token?grant_type=password";
//        Map<String, Object> body= new HashMap<>();
//        body.put("email",email);
//
//        body.put("password",password);
//
//        try{
//            String jsonBody = objectMapper.writeValueAsString(body);
//            HttpEntity<String> entity= new HttpEntity<>(jsonBody,headers);
//     ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST,entity,String.class);
//     Map<String,Object> supabaseResponse=
//             objectMapper.readValue(response.getBody(),new TypeReference<Map<String,Object>>(){});
//     return ResponseEntity.ok(supabaseResponse);
//
//    }
//    catch (HttpClientErrorException e){
//            log.error("Supabase error {} {}",e.getStatusCode(),e.getResponseBodyAsString());
//            return ResponseEntity.status(e.getStatusCode()).build();
//    }
//    catch (Exception e){
//            log.error("Unexpected error during sign in",e);
//            return ResponseEntity.internalServerError().build();
//
//    }
//
//    }
//    ///  update user email
//    public ResponseEntity< String> updateUserEmail(String username,String newEmail){
//        String userUrl= baseUrl+username;
//        Map<String ,Object> updateRequest= new HashMap<>();
//        updateRequest.put("email",newEmail);
//        try{
//            String requestBody= objectMapper.writeValueAsString(updateRequest);
//            HttpEntity<String> entity= new HttpEntity<> (requestBody,headers);
//
//        return     restTemplate.exchange(userUrl, HttpMethod.PUT, entity, String.class);
//        }
//        catch (JsonProcessingException e){
//            throw new RuntimeException("Failed to serialize update request",e);
//        }
//    }
//
//    /// delete user
//    public ResponseEntity<String> deleteUser(String userId){
//        String userUrl= baseUrl+userId;
//        HttpEntity<Void> entity= new HttpEntity<>(headers);
//       return restTemplate.exchange(userUrl, HttpMethod.DELETE, entity, String.class);
//
//
//    }
//
//}
