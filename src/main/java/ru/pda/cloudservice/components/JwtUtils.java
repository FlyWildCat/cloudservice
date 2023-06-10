package ru.pda.cloudservice.components;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.sessionTime}")
    private int expirationTime;
    @Value("${jwt.secret}")
    private String secretKey;

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("неправильная сигнатура JWT: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("некорректный JWT токен: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("истек период JWT токена: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("не поддерживаемый JWT токен: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("требования в JWT пусты: " + e.getMessage());
        }

        return false;
    }

    public String generateToken(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder().setSubject(principal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }
}
