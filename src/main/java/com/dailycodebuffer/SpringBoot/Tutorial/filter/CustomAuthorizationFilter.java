package com.dailycodebuffer.SpringBoot.Tutorial.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refresh") || request.getServletPath().equals("/api/register")){
            filterChain.doFilter(request, response);
        }else{
            //log.info("code got here");
           String authorizationHeader = request.getHeader(AUTHORIZATION);
           if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String token = authorizationHeader.substring("Bearer ".length());
               Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); 
               JWTVerifier verifier = JWT.require(algorithm).build();
               DecodedJWT decodedJWT = verifier.verify((token));
               String username = decodedJWT.getSubject();
               String[] roles = decodedJWT.getClaim("roles").asArray(String.class); 
               Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
               stream(roles).forEach(role ->{
                   authorities.add(new SimpleGrantedAuthority(role));
               });
               UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null);
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
               filterChain.doFilter(request, response);
               
            }catch(JWTDecodeException exception){
               log.info("Am here");
              //  log.info("Error Logging in"); 
                //response.sendError(FORBIDDEN.value());
                //response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>(); 
                //log.info("Am here");
                error.put("error", exception.getMessage());
                //error.put("code",FORBIDDEN.value());
                response.setHeader("error", exception.getMessage()); 
                response.setContentType("application/json");

                new ObjectMapper().writeValue(response.getOutputStream(), error);
                //filterChain.doFilter(request, response);
            }
            catch(TokenExpiredException exception){
                log.info("Am here");
                //  log.info("Error Logging in"); 
                  //response.sendError(FORBIDDEN.value());
                  //response.setHeader("error", exception.getMessage());
                  response.setStatus(FORBIDDEN.value());
                  Map<String, String> error = new HashMap<>(); 
                  //log.info("Am here");
                  error.put("error", "Your token as expired kindly refresh or login to get another token");
                  //error.put("code",FORBIDDEN.value());
                  response.setHeader("error", exception.getMessage()); 
                  response.setContentType("application/json");
  
                  new ObjectMapper().writeValue(response.getOutputStream(), error);
                  //filterChain.doFilter(request, response);
            }    
               
           }else{
            filterChain.doFilter(request, response);
           }
        }
        
    }
    
}
