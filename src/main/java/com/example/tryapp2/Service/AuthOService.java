package com.example.tryapp2.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class AuthOService {
    @Value("{okta.oauth2.issuer}")
    private String issuer;
    @Value("{okta.oauth2.client-id}")
    private String clientId;
    @Value("{okta.oauth2.client-secret}")
    private String clientSecret;


    public RestTemplate restTemplate = new RestTemplate();
    private String getManagementToken(){
        Map<String,String> body=Map.of(
                "client_id",clientId,
                "client_secret",clientSecret,
                "audience",issuer+"api/v2",
                "grant_type","client_credentials"
//                "scope","okta.users.manage"
        );
        ResponseEntity <Map> response=restTemplate.postForEntity(
                issuer+"oauth/token",
                body,
                Map.class
        );
        return (String) response.getBody().get("access_token");
    }
    public void  deleteUser(String userId){
        String token =getManagementToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        restTemplate.exchange(
                issuer+"api/v2/users/"+userId,
                HttpMethod.DELETE,
                entity,Void.class
        );
    }
    public Map<String, Object>getUserByEmail(String email){
        String token= getManagementToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(
                issuer+"/api/v2/users-by-email?email="+email,
                HttpMethod.GET,
                entity,
                List.class);
        if (response.getBody() != null&& !response.getBody().isEmpty()) {
            return (Map<String, Object>) response.getBody().get(0);
        }
        else {
            return null;
        }


    }
    public List<String > getAllUserEmails(){
        String token=getManagementToken();
        HttpHeaders headers= new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity= new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(
                issuer+"api/v2/users?fields=email&include_fields=true",
                HttpMethod.GET,
                entity,
                List.class
        );
        List<Map<String,Object>> users=response.getBody();

        return users.stream()
                .map(u->u.get("email").toString())
                .collect(Collectors.toList());
    }
    public Map<String,Object> updateUser(String userId, Map<String,Object> updates){
        String token=getManagementToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<Map<String,Object>> entity= new HttpEntity<>(updates, headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                issuer+"api/v2/users/"+userId,
                HttpMethod.POST,
                entity,
                Map.class
        );
        return response.getBody();
    }

}
