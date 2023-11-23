package com.pcp.popcornproseserver.service;

import com.pcp.popcornproseserver.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class AuthService {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); //criando uma chave aleatoria
    private static final long EXPIRATION_TIME = 864_000_000; // A chave expira em 10 dias

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generateToken(String email) {
        String name = usuarioRepository.findByEmail(email).getNome();
        Long id = usuarioRepository.findByEmail(email).getIdUser();
        String surname = usuarioRepository.findByEmail(email).getSobrenome();

        return Jwts.builder()
                .setSubject(email)
                .claim("id", id)
                .claim("name", name)
                .claim("surname", surname)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String getNameFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims.get("name", String.class);
        } catch (Exception e) {
            return null;
        }
    }

    public Long getIDFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims.get("id", Long.class);
        } catch (Exception e) {
            return null;
        }
    }

    public String getSurnameFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims.get("surname", String.class);
        } catch (Exception e) {
            return null;
        }
    }

}
