package com.mb.api.jwt;

import java.util.Date;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.mb.api.business.service.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

//@Component
//public class JwtUtil
//{
//	@Value("${app.secretKey}")
//	private String secretKey ;
//	
//	@Value("${jwtExpirationMs}")
// 	private int jwtExpirationMs;
//
//
//	
//	//1. Method to generate tokens
//	public String generateTokens(Authentication authentication) {
//		
//		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
//		return Jwts.builder()
//				.setSubject(userPrincipal.getUsername())
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date((new Date().getTime() + jwtExpirationMs)))
//				.claim("roles", authorities)
//				.signWith(SignatureAlgorithm.HS512, secretKey)
//				.compact();
//	}
//	
//	//2. Read Claims
//	public Claims getClaims(String token) {
//		return 
//				Jwts.parser()
//				.setSigningKey(secretKey)
//				.parseClaimsJws(token)
//				.getBody();
//	}
//	
//	//3.Read only expire Date
//	public Date getExpireDate(String token) {
//		return getClaims(token).getExpiration();
//	}
//	
//	//4. Read only subject/username
//	public String getUsername(String token) {
//		return getClaims(token).getSubject();
//	}
//	
//	//5. Is expire or not 
//	public boolean isTokenExpired(String token) {
//		return getExpireDate(token).after(new Date(System.currentTimeMillis()));
//	}
//	
//	//6. Validate Token 
//	public boolean validateToken(String token, String username) {
//		String tokenUsername = getUsername(token);
//		return (username.equals(tokenUsername) && !isTokenExpired(tokenUsername));
//		
//	}
//}





@Component
public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	@Value("${app.secretKey}")
	private String jwtSecret;
	
	@Value("${jwtExpirationMs}")
	private int jwtExpirationMs;
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date((new Date().getTime() + jwtExpirationMs)))
				.claim("roles", authorities)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
}
