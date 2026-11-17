package com.revisaoWeb.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiracao-ms}")
    private long expiracaoMs;

    private SecretKey gerarChave() {
        String segredo = secret;

        byte[] segredoByte = segredo.getBytes(StandardCharsets.UTF_8);
        SecretKey chave = Keys.hmacShaKeyFor(segredoByte);

        return chave;
    }

    public String gerarToken(int idUsuario) {
        Date data = new Date();
        Date dataExpiracao = new Date(data.getTime() + expiracaoMs);

        String idUser = String.valueOf(idUsuario);

        return Jwts.builder()
                .subject(idUser)
                .issuedAt(data)
                .expiration(dataExpiracao)
                .signWith(gerarChave(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean validarToken(String token) {

        try {
            extrairDados(token);
            return true;

        }
        catch (JwtException | IllegalArgumentException e) {
            System.out.println("Erro ao validar token: " + e.getMessage());
            return false;
        }
    }

    public String extrairDados(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(gerarChave())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}
