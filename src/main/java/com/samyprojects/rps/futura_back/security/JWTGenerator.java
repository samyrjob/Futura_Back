package com.samyprojects.rps.futura_back.security;


import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTGenerator {


	//! Different secret key generated after each runtime 
	// private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    private final Key key;  // ✅ Non-static field (correct approach)

    @Autowired
    public JWTGenerator(JwtConfig jwtConfig) {
        this.key = jwtConfig.getSigningKey();
    }

    // @Autowired
    // JwtConfig jwtConfig;


    // private static final Key key = jwtConfig.getSigningKey();
	
	public String generateToken(Authentication authentication) {
		String userEmail = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
		
		String token = Jwts.builder()
				.setSubject(userEmail)
				.setIssuedAt( new Date())
				.setExpiration(expireDate)
				.signWith(key,SignatureAlgorithm.HS512)
				.compact();
		System.out.println("New token :");
		System.out.println(token);
		return token;
	}

	public String getUserEmailFromJWT(String token){
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			return true;
		} catch (Exception ex) {
			throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
		}
	}


	// refresh token method : 

	public String generateRefreshToken(String userEmail) {
    Instant now = Instant.now();
    return Jwts.builder()
        .setSubject(userEmail)
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plus(120, ChronoUnit.MINUTES))) // Shorter than usual
        .signWith(key)
        .compact();
}

}