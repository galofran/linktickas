package com.proyecto.springboot.app.seguridad;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.proyecto.springboot.app.excepciones.ProyectoAppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

//Clase para validar claims, generarToken, validarToken

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-miliseconds}")
	private int jwtExpirationInMs;
	
	public String generarToken(Authentication authentication) {
		String username = authentication.getName();
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
		
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("fechaActual",fechaActual);
		claims.put("fechaExpiracion", fechaExpiracion);
		claims.put("role",authentication.getAuthorities());
		
		//String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256 , jwtSecret).compact();
	};
	

	public String obtenerUsernameDelJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		
		return claims.getSubject(); //Obtenemos el username del token
	}
	
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		
		}catch(SignatureException e) {
			throw new ProyectoAppException(HttpStatus.BAD_REQUEST,"Firma JWT no valida");
		
		}catch (MalformedJwtException ex) {
			throw new ProyectoAppException(HttpStatus.BAD_REQUEST,"Token JWT no valida");
		
		}catch (ExpiredJwtException ex) {
			throw new ProyectoAppException(HttpStatus.BAD_REQUEST,"Token JWT caducado");
		
		}catch (UnsupportedJwtException ex) {
			throw new ProyectoAppException(HttpStatus.BAD_REQUEST,"Token JWT no compatible");
		
		}catch (IllegalArgumentException ex) {
			throw new ProyectoAppException(HttpStatus.BAD_REQUEST,"La cadena claims JWT esta vacia");
		}
	}
}
