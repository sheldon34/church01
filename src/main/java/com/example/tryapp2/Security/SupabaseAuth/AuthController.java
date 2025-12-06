//package com.example.tryapp2.Controller;
//
//import com.example.tryapp2.Dto.LoginRequest;
//import com.example.tryapp2.Security.JwtAuthFilter;
//import com.example.tryapp2.Security.JwtService;
//import com.example.tryapp2.Security.SupabaseAuthService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtException;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//@RestController
//@Slf4j
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173") // allow local frontend during development
//public class AuthController {
//
//
//    private final SupabaseAuthService authService;
//    private final JwtService jwtTokenService;
//    private final JwtDecoder jwtDecoder;
//    private final JwtService jwtService;
////
////    jwtDecoder public AuthController(SupabaseAuthService authService, JwtAuthFilter jwtTokenService, JwtDecoder jwtDecoder) {
////        this.authService = authService;
////        this.jwtTokenService = jwtTokenService;
////        this.jwtDecoder = jwtDecoder;
////    }
//
//
//    @PostMapping("/signup")
//    public ResponseEntity<String> signUp(@RequestBody Map<String, String> body) {
//
//
//        log.info("✅ Received signup request for: {}", body.get("email"));
//        return authService.signUp(body.get("email"), body.get("password"),"USER");
//    }
//
//    // expose POST /login so the frontend can call http://localhost:8080/login
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
//
//        ResponseEntity<Map<String, Object>> supabaseRes = authService.signIn(request.getEmail(), request.getPassword());
//        if (!supabaseRes.getStatusCode().is2xxSuccessful()) {
//            log.warn("❌ Login failed for: {}", request.getEmail());
//            return supabaseRes;
//        }
//
//        Map<String, Object> body = supabaseRes.getBody();
//        if (body == null) {
//            log.warn("❌ Empty response body from Supabase for {}", request.getEmail());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("error", "Empty response from auth provider"));
//        }
//
//        Object tokenObj = body.get("access_token");
//        if (!(tokenObj instanceof String)) {
//            log.warn("❌ No access token in Supabase response for {}", request.getEmail());
//            return ResponseEntity.badRequest().body(Map.of("error", "No access token received from Supabase"));
//        }
//
//        String supabaseToken = (String) tokenObj;
//
//        Jwt jwt;
//        try {
//            jwt = jwtDecoder.decode(supabaseToken);
//        } catch (JwtException e) {
//            log.warn("❌ Failed to decode Supabase token for {}: {}", request.getEmail(), e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid token from auth provider"));
//        }
//
//        // Extract role from claims (top-level or nested user metadata)
//        Object roleObj = jwt.getClaims().get("role");
//        String role = null;
//        if (roleObj instanceof String) role = (String) roleObj;
//        else {
//            Object userMeta = jwt.getClaims().get("user_metadata");
//            if (userMeta instanceof Map) {
//                Object nested = ((Map<?, ?>) userMeta).get("role");
//                if (nested instanceof String) role = (String) nested;
//            }
//            Object rawMeta = jwt.getClaims().get("raw_user_meta_data");
//            if (role == null && rawMeta instanceof Map) {
//                Object nested = ((Map<?, ?>) rawMeta).get("role");
//                if (nested instanceof String) role = (String) nested;
//            }
//        }
//        if (role == null) role = "USER";
//
//
//String appToken = jwtService.generateToken(request.getEmail(), role);
//        return ResponseEntity.ok(Map.of(
//                "token", appToken,
//                "role", role,
//                "email", request.getEmail()
//        ));
//
//    }
//
//    @GetMapping("/ping")
//    public String ping (){
//        log.info("Pinged");
//        return "pong";
//    }
//    @PutMapping ("/update-email")
//    public ResponseEntity<String>  updateEmail(@RequestParam String email, @RequestParam String newEmail){
//        return authService.updateUserEmail (email,newEmail);
//    }
//    @DeleteMapping("/delete-account/{id}")
//    public ResponseEntity<String> deleteAccount(
//
//            @PathVariable("id") String UserId){
//
//
//
//
//        return   authService.deleteUser(UserId);}
//
//
//
//
//    @PostMapping ("/validate")
//    public ResponseEntity<String>  validateToken(@RequestHeader("Authorization") String authHeader){
//        try {
//         String token= authHeader.substring(7);
//         if (jwtService.validateToken(token)){
//             String role= jwtService.extractRole(token);
//             String personId= jwtService.extractPersonId(token);
//                String personName= jwtService.extractName(token);
//
//                Map<String ,Object> response= new HashMap<>();
//                response.put("valid", true);
//                response.put("personId", personId);
//                response.put("personName", personName);
//                response.put("role", role);
//                return ResponseEntity.ok(""+response.toString());
//         }
//         else{
//             return ResponseEntity.status(401).body(Map.of("valid", false,"message","Invalid token").toString());
//         }
//        } catch (IllegalArgumentException e){
//            return ResponseEntity.status(400).body(Map.of("valid", false,"message",e.getMessage()).toString());
//
//
//        }
//    }
//
//
//}
