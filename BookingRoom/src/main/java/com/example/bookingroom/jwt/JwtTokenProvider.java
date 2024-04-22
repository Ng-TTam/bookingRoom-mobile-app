package com.example.bookingroom.jwt;

import com.example.bookingroom.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    public String generateToken(UserDTO userDTO) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration * 1000);

        return Jwts.builder()
                .setSubject(userDTO.getNameLogin())
                .claim("id", userDTO.getId())
                .claim("nameAccount", userDTO.getNameAccount())
                .claim("birth", userDTO.getBirth().toString())
                .claim("email", userDTO.getEmail())
                .claim("number", userDTO.getNumber())
                .claim("rewardPoint",userDTO.getRewardPoint())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public UserDTO getUserFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(claims.get("id", Integer.class));
        userDTO.setNameAccount(claims.get("nameAccount", String.class));
        userDTO.setNameLogin(username);
        userDTO.setBirth(LocalDate.parse(claims.get("birth", String.class)));
        userDTO.setEmail(claims.get("email", String.class));
        userDTO.setNumber(claims.get("number", String.class));
        userDTO.setRewardPoint(claims.get("rewardPoint", Integer.class));
        return userDTO;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
