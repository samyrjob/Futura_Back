package com.samyprojects.rps.futura_back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTGenerator tokenGenerator;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, IOException {

                String token = getJWTFromRequest(request);
                if(StringUtils.hasText(token) && tokenGenerator.validateToken(token)) {
                    String username = tokenGenerator.getUsernameFromJWT(token);
        
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                filterChain.doFilter(request, response);  
            }


            private String getJWTFromRequest(HttpServletRequest request) {

                //* Check JWT THROUGH COOKIE
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("JWT_token".equals(cookie.getName())) {
                            return cookie.getValue(); // Returns token from cookie
                        }
                    }
                }
                
                //* Fallback to authorization HEADER : way not using cookie but normal bearer authorization header
                // String requestURI = request.getRequestURI();

                // if ("/api/users/logoutInactivity".equals(requestURI)){

                    // String bearerToken = request.getHeader("Authorization");
                    // if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                    //     return bearerToken.substring(7); // Returns token from header
                    // }
                // }
                
                return null; // No token found
            }

            

            //* way not using cookie but normal bearer authorization header */
            // private String getJWTFromRequest(jakarta.servlet.http.HttpServletRequest request) {
            //                 String bearerToken = request.getHeader("Authorization");
            //     if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            //         return bearerToken.substring(7, bearerToken.length());
            //     }
            //     return null;
            // }
}