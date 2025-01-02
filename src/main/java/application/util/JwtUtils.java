package application.util;

import application.exception.TokenInvalidException;
import application.secutity.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.experimental.NonFinal;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.jta.JtaAfterCompletionSynchronization;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt_signer_key}")
    private String jwtSecret;

    public String generateToken(Authentication authentication) {
        try {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 24 * 3600 * 1000))
                    .signWith(key(), SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key())
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new TokenInvalidException("token is expired: " + e.getMessage());
        } catch (SignatureException e) {
            throw new TokenInvalidException("token is signature: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new TokenInvalidException("jwt claim string is empty: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new TokenInvalidException("token is unsupported: " + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new TokenInvalidException("Invalid jwt token: " + e.getMessage());
        }
    }
}
