package com.example.tryapp2.Controller;

import com.example.tryapp2.Service.AuthOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@Controller
@RequiredArgsConstructor
public class Auth_Controller {
    private final AuthOService userService;
    @GetMapping("/")
    public String home(Model model , @AuthenticationPrincipal OidcUser principal){
        if (principal!=null){
            model.addAttribute("profile",principal.getClaims());
        }
        return "index";
    }
    @DeleteMapping("/users/{id}")
public ResponseEntity<String> deleteUser(@PathVariable String id){
 userService.deleteUser(id);
 return ResponseEntity.ok("User deleted successfully"+id);
}
@PatchMapping("/user/{id}")
public ResponseEntity<Map<String,Object>> updateUser(
        @PathVariable String id,
        @RequestBody Map<String,Object> updates){
    Map<String,Object> updatedUser=userService.updateUser(id,updates);
    return ResponseEntity.ok(updatedUser);
}
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/user/email")
public ResponseEntity<Map<String,Object>> getUserByEmail(@RequestParam String email){
        Map<String,Object> user= userService.getUserByEmail(email);
        if (user !=null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
}



}


