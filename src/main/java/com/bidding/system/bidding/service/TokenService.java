/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.service;

import com.bidding.system.bidding.model.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Usuario
 */
@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    
    public SecretKey getKeySign() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String gerarToken(UserDTO user) {
        if(
            user.getId() == 0 || user.getId() == null ||
            user.getNome().equals("") ||
            user.getEmail().equals("") ||
            user.getRole().equals("")
        ) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), 
            "Um ou mais campos faltantes");
        }
        
        return Jwts.builder()
                .subject(user.getNome())
                .claim("id", user.getId())
                .claim("nome", user.getNome())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3000000))
                .signWith(this.getKeySign())
                .compact();
    }
    
    public UserDTO extrairClaim(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(this.getKeySign())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        
        UserDTO user = new UserDTO();
        user.setId(claims.get("id", Long.class));
        user.setNome(claims.get("nome", String.class));
        user.setRole(claims.get("role", String.class));
        
        return user;
    }
    
    public boolean validarToken(String token) {
        try {
            // Cria um parser JWT com a chave secreta para validação
            Jwts.parser()
                    .setSigningKey(getKeySign())
                    .build()
                    // Analisa e valida o token (lança exceção se inválido ou expirado)
                    .parseClaimsJws(token);
            // Se chegou aqui, o token é válido
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Se qualquer exceção ocorrer, o token é inválido ou expirou
            return false;
        }
    }
    
    
}
