package com.HomeTaskManager.HomeTaskManagerBackend;

import java.io.IOException;

import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HomeTaskManager.HomeTaskManagerBackend.user.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
    
    @Autowired
    private MyUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // private Logger logger;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
    throws ServletException, IOException {

    final String requestTokenHeader = request.getHeader("Authorization");

    String username = null;
    String jwtToken = null;

    //JWT token is in the form "Bearer token". Remove Bearer word and get the token only
    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
        jwtToken = requestTokenHeader.substring(7);
        try{
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        } catch(IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
        } catch(ExpiredJwtException e){
            System.out.println("JWT token has expired");
        }
    }else { 
        // logger.warning("JWT Token does not begin with Bearer String");
    }
    
    //Once we have the token validate it
    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
    
        //if the token is valid configure spring security to manually set authentication
        if(jwtTokenUtil.vaildateToken(jwtToken, userDetails)) {

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
            (userDetails, null, userDetails.getAuthorities());

            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //After setting the Authentication in the context, we specify that the cuurent user 
            //is authenticated. So it passes the Spring Security Configuratiosn succesfully

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
    chain.doFilter(request, response);
    }
}