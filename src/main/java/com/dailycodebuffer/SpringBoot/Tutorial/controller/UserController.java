package com.dailycodebuffer.SpringBoot.Tutorial.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;


import org.springframework.web.util.UriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.dailycodebuffer.SpringBoot.Tutorial.entity.Role;
import com.dailycodebuffer.SpringBoot.Tutorial.entity.User;
import com.dailycodebuffer.SpringBoot.Tutorial.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/")
@AllArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers() {
        
        return ResponseEntity.ok().body(userService.fetchUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<User>saveUser(@RequestBody User user){
        URI uri = URI.create(UriComponentsBuilder.fromPath("api/users/").toUriString());
        System.out.println(uri);
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User>updateUser(@PathVariable("id") Long id,User user){
        return ResponseEntity.ok().body(userService.updateUser(id, user));
    }

    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user){

       URI uri = URI.create(UriComponentsBuilder.fromPath("api/register/").toUriString());
        System.out.println(uri);
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/users/addRole")
    public ResponseEntity<?> addUserRole(@RequestBody UserRoleForm form){
    userService.addUserRole(form.getEmail(),form.getRoleName());
        URI uri = URI.create(UriComponentsBuilder.fromPath("/api/addRole").toUriString());
        
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/token/refresh")
    protected void refresh(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
   String authorizationHeader = request.getHeader(AUTHORIZATION);
   if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
    try {
        String token = authorizationHeader.substring("Bearer ".length());
       Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); 
       JWTVerifier verifier = JWT.require(algorithm).build();
       DecodedJWT decodedJWT = verifier.verify((token ));
       String username = decodedJWT.getSubject();
       User user = userService.getUser(username);

       log.info("error {}",user.getRoles().size());
       String access_token = JWT.create()
       .withSubject(user.getEmail())
       .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
       .withClaim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
       .withIssuer(request.getRequestURI().toString()).sign(algorithm);
       
       String refresh_token = JWT.create()
       .withSubject(user.getEmail())
       .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
       .withIssuer(request.getRequestURI().toString())
       .sign(algorithm);

       Map<String, String> tokens = new HashMap<>(); 

         tokens.put("access_token", access_token);
         tokens.put("refresh_token", refresh_token);
         response.setContentType("application/json");

         new ObjectMapper().writeValue(response.getOutputStream(), tokens);
       
    }catch(JWTDecodeException exception){
     
        response.setStatus(HttpStatus.FORBIDDEN.value());
        Map<String, String> error = new HashMap<>(); 
        //log.info("Am here");
        error.put("error_message", exception.getMessage());
        //error.put("code",FORBIDDEN.value());
        response.setHeader("error", exception.getMessage()); 
        response.setContentType("application/json");

        new ObjectMapper().writeValue(response.getOutputStream(), error);
        //filterChain.doFilter(request, response);
    }    
       
   }else{
    throw new RuntimeException("You need to provide a refresh token");
   }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String,  Map<String,String>> handleValidationExceptions(
  MethodArgumentNotValidException ex) {
    Map<String,String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });
    Map<String,Map<String,String>> msg = new HashMap<>();
    msg.put("errors", errors);
    return msg;
}

}


@Data
class UserRoleForm{
    private String email;
    private String roleName;
}