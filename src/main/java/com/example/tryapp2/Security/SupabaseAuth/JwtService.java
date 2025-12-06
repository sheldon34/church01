//package com.example.tryapp2.Security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import lombok.NoArgsConstructor;
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@NoArgsConstructor
//public class JwtService {
//    @Value("${supabase.service.key}")
//    private String jwtSecret;
//@jakarta.annotation.PostConstruct
//    public void init(){
//System.out.println ("JWT Secret: " + (jwtSecret!=null?"Loaded":"Not Loaded"));
//    }
//
//
//
//    public String extractName(String token){
//        return extractClaim(token,claims->{
//            HashMap<?,?> userMetadata=claims.get("user_metadata", HashMap.class);
//            if(userMetadata!=null){
//                return userMetadata.get("name").toString();
//            }
//            return  null;
//        });
//    }
//    public String extractRole(String token) {
//        return extractClaim(token, claims -> {
//            HashMap<?, ?> userMetadata = claims.get("user_metadata", HashMap.class);
//            if (userMetadata != null) {
//                return userMetadata.get("role").toString();
//            }
//            return null;
//        });
//    }
//
//    public String extractPersonId(String  token){
//        return extractClaim(token, Claims::getSubject);
//        }
//        public Date extractExpiration(String token){
//    return extractClaim(token,Claims::getExpiration);
//        }
//        public <T> T extractClaim(String token, java.util.function.Function<Claims,T> claimsResolver){
//final Claims claims = extractAllClaims(token);
//return claimsResolver.apply(claims);
//        }
//
//    private Claims extractAllClaims(String token) {
//    Claims claims= Jwts.parserBuilder()
//            .setSigningKey(getSignInKey())
//            .build()
//            .parseClaimsJws(token)
//            .getBody();
//    return claims;
//    }
//    private Boolean isTokenExpired(String token){
//    return extractExpiration(token).before(new Date());
//}
//
//
//public Boolean validateToken(String token){
//    try{
//        boolean result= !isTokenExpired(token);
//        return result;
//    }catch (Exception e){
//        e.printStackTrace();
//        return  false;
//    }
//}
//
//private Key getSignInKey(){
//    byte[] keyBytes= jwtSecret.getBytes(StandardCharsets.UTF_8);
//    return Keys.hmacShaKeyFor(keyBytes);
//}
//
//public String generateToken(String email, String role){
//  Map<String,Object> claims=new HashMap<>();
//  Map<String , Object> userMetadata=new HashMap<>();
//  userMetadata.put("email",email);
//  userMetadata.put("role",role);
//  claims.put("user_metadata",userMetadata);
//  return  createToken(claims,email);
//}
//
//private String createToken(Map<String,Object> claims,String email){
//    return Jwts.builder()
//            .setClaims(claims)
//            .setSubject(email)
//            .setIssuedAt(new Date(System.currentTimeMillis()))
//            .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
//            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//            .compact();
//}
//}
