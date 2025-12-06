//package com.example.tryapp2.Security;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import com.fasterxml.jackson.core.JsonProcessingException;
//
////import software.amazon.awssdk.thirdparty.jackson.core.JsonProcessingException;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class SupabaseAdminClient {
//
//    private final RestTemplate restTemplate ;
//    private final ObjectMapper mapper ;
//    private final String adminUsersUrl;
//    private final HttpHeaders headers;
//    private final String baseUrl;
//
//    public SupabaseAdminClient(
//            @Value("${supabase.url}") String supabaseUrl,
//            @Value("${supabase.service.key}")  String serviceKey) {
//
//        this.adminUsersUrl = supabaseUrl+"/auth/v1/admin/users";
//        this.headers = new HttpHeaders();
//        this.mapper= new ObjectMapper();
//        this.restTemplate = new RestTemplate();
//        this.baseUrl=supabaseUrl+"/auth/v1/";
//
//
//        headers.set("apikey",serviceKey);
//        headers.set("Authorization","Bearer "+serviceKey);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//    }
//
//
//    public ResponseEntity <String > updateUserRole(String email,String newRole) {
//
//        try {
//
//
//            String searchUrl = adminUsersUrl + "?email=" + email;
//            ResponseEntity<String> response = restTemplate.exchange(
//                    searchUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);
//            //email not found
//            if (response.getStatusCode() != HttpStatus.OK) {
//                return ResponseEntity.status(response.getStatusCode()).body("User not found");
//            }
//            String userId = extractUserId(response.getBody());
//
//            String updateUrl = adminUsersUrl + "/" + userId;
//            Map<String, Object> metadata = Map.of("role", newRole);
//            Map<String, Object> body = Map.of("user_metadata", metadata);
//
//            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
//
//
//            return restTemplate.exchange(updateUrl, HttpMethod.PUT, entity, String.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Failed to parse user JSON", e);
//        }
//    }
//
//    private String extractUserId(String responseBody) throws JsonProcessingException{
//        JsonNode root= new ObjectMapper().readTree(responseBody);
//
//        // Handle responses that are either an array of users or an object containing a "users" array
//        if (root.isArray() && root.size() > 0) {
//            JsonNode first = root.get(0);
//            if (first.has("id")) {
//                return first.get("id").asText();
//            }
//        }
//        JsonNode usersNode = root.get("users");
//        if (usersNode != null && usersNode.isArray() && usersNode.size() > 0) {
//            JsonNode first = usersNode.get(0);
//            if (first.has("id")) {
//                return first.get("id").asText();
//            }
//        }
//        throw new RuntimeException("Could not extract user id from response");
//    }
//
//
//    public List<String> fetchAllVerifiedUserEmails() {
//        HttpEntity<Void> entity = new HttpEntity<>(headers);
//        ResponseEntity<String> resp = restTemplate.exchange(adminUsersUrl, HttpMethod.GET, entity, String.class);
//        try {
//            JsonNode root = mapper.readTree(resp.getBody());
//            JsonNode usersNode = root.get("users");
//            if (usersNode == null || !usersNode.isArray()) {
//                throw new RuntimeException("No 'users' array found in response");
//            }
//            List<Map<String, Object>> users = mapper.convertValue(usersNode, new TypeReference<List<Map<String, Object>>>() {});
//            return users.stream()
//                    .filter(u -> u.get("email") != null)
//                    .filter(u -> u.get("confirmed_at") != null)
//                    .map(u -> u.get("email").toString())
//                    .collect(Collectors.toList());
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Failed to parse users JSON", e);
//        }
//    }
//
//    ///  getting users by id
//    public Map<String, Object> getUserById(String userId){
//        String userUrl=baseUrl+"/admin/users/"+userId;
//        HttpEntity<Void> entity= new HttpEntity<>(headers);
//
//        ResponseEntity<String> response= restTemplate.exchange(
//                userUrl, HttpMethod.GET, entity,String.class);
//
//        try{
//            return  mapper.readValue(response.getBody(), new TypeReference<Map<String,Object>>(){});
//
//        }
//        catch (JsonProcessingException e){
//            throw new RuntimeException("Failed to parse user JSON",e);
//        }}
//    ///  getting all users
//
//    public List<Map<String,Object>> getAllUsers(){
//        String usersUrl = baseUrl + "/admin/users";
//        HttpEntity<Void> entity = new HttpEntity<>(headers);
//        ResponseEntity<String> response = restTemplate.exchange(
//                usersUrl, HttpMethod.GET, entity, String.class);
//        try {
//            JsonNode root = mapper.readTree(response.getBody());
//            JsonNode usersNode = root.get("users");
//            if (usersNode == null || !usersNode.isArray()) {
//                throw new RuntimeException("No 'users' array found in response");
//            }
//            return mapper.convertValue(usersNode, new TypeReference<List<Map<String, Object>>>() {});
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Failed to parse users JSON", e);
//        }
//    }
//
//
//    ///  update Email
//    public void updateUserEmail(String userId,String newEmail){
//        String userUrl= baseUrl+ "/admin/users/"+userId;
//        Map<String ,Object> updateRequest= new HashMap<>();
//        updateRequest.put("email",newEmail);
//        try{
//            String requestBody= mapper.writeValueAsString(updateRequest);
//            HttpEntity<String> entity= new HttpEntity<> (requestBody,headers);
//
//            restTemplate.exchange(userUrl, HttpMethod.PUT, entity, String.class);
//        }
//        catch (JsonProcessingException e){
//            throw new RuntimeException("Failed to serialize update request",e);
//        }
//    }
//
//    /// delete user
//
//    //    get user by email
//    public Map<String ,Object> getUserByEmail(String email){
//        List<Map<String,Object>> allUsers=getAllUsers();
//        return allUsers.stream()
//                .filter(user->email.equals(user.get("email")))
//                .findFirst()
//                .orElseThrow(()->new RuntimeException("User not found with email: "+email));
//    }
//    // get Verified user count
//    public Long getVerifiedUserCount(){
//        List<Map<String,Object>> allUsers=getAllUsers();
//        return allUsers.stream()
//                .filter(user->user.get("confirmed_at")!=null)
//                .count();
//    }
//
//
//
//
//
//
//
//}