package com.example.tryapp2.Security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
@Value("{okta.oauth2.issuer}")
    private String issuer;
    @Value("{okta.oauth2.client-id}")
    private String clientId;

//private final JwtAuthFilter jwtAuthFilter;
//    private final JwtAuthFilter jwtAuthFilter;
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors->{})
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
//                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/api/email/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .oauth2Login(oauth2->oauth2
                        .userInfoEndpoint(userInfo->userInfo.oidcUserService(this.oidcUserService())))

                .logout(logout->logout
                .addLogoutHandler(logoutHandler()));

        return http.build();
    }

//@Bean
//    public JwtDecoder jwtDecoder(@Value("${supabase.jwt.secret}") String jwtSecret){
//        byte[] secretBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
//
//        SecretKey key = new SecretKeySpec(secretBytes, "HmacSHA256");
//
//        return NimbusJwtDecoder.withSecretKey(key)
//                .macAlgorithm(MacAlgorithm.HS256)
//                .build();
//    }

//    private Converter<Jwt, AbstractAuthenticationToken> jwtAuthConverter(){
//    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//    converter.setJwtGrantedAuthoritiesConverter(
//            jwt->{
//                Collection<GrantedAuthority> authorities = new ArrayList<>();
//                Object roleObj = jwt.getClaims().get("role");
//                if(roleObj instanceof String) {
//                    authorities.add(new SimpleGrantedAuthority("ROLE_" + ((String) roleObj).toUpperCase()));
//
//                }
//                return authorities;
//            }
//    );
//    return converter;
//    }
private OidcUserService oidcUserService(){
    return new OidcUserService (){
        public OidcUser loadUser(OidcUserRequest userRequest) {
            OidcUser oidcUser = super.loadUser(userRequest);

            List<SimpleGrantedAuthority> mappedAuthorities = new ArrayList<>();
            List<String> roles = (List<String>) oidcUser.getClaims().getOrDefault("roles", Collections.emptyList());
            for (String role : roles) {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
            return new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
        }   };
}
private LogoutHandler logoutHandler(){
    return (request, response, authentication) -> {
        // Custom logout logic can be added here
        try {
        String baseUrl= ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        response.sendRedirect(issuer +"v2/logout?client_id="+clientId+"$returnTo="+baseUrl);
    } catch (Exception e) {
        throw new RuntimeException(e);
        }
    };

}



}

