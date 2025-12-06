//package com.example.tryapp2.Controller;
//
//
//import com.example.tryapp2.Security.SupabaseAdminClient;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/admin")
//
//
////@PreAuthorize("#jwt.getClaimAsString('email') == 'devs@gmail.com'")---used  for single admin
//@PreAuthorize("hasRole('ADMIN')")
//public class AuthAdminController {
//    private final SupabaseAdminClient supabaseAdminClient;
//    public AuthAdminController(SupabaseAdminClient supabaseAdminClient) {
//        this.supabaseAdminClient = supabaseAdminClient;
//    }
//
//    @PutMapping("/assign-role")
//    public ResponseEntity<String> assignUserRole(@RequestParam String userEmail,
//                                                 @RequestParam String  newRole){
//        return supabaseAdminClient.updateUserRole(userEmail, newRole);
//    }
//
//
//
//    ///  get all users
//    @GetMapping("/getAllUsers")
//    public ResponseEntity<List<String>> getUser(){
//        List<Map<String, Object>> users = supabaseAdminClient.getAllUsers();
//        List<String> emails = users.stream()
//                .map(user -> user.get("email").toString())
//                .toList();
//        return ResponseEntity.ok(emails);
//    }
////        return supabaseAdminClient.getAllUsers();
//    }
//
////}
