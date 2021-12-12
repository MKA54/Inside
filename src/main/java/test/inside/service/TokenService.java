package test.inside.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.inside.model.Token;

import static org.springframework.util.StringUtils.hasText;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String SECRET_KEY = "H2e87ty";

    private final Token token = new Token();

    public boolean isToken(String token) {
        return hasText(token) && token.startsWith("Bearer ");
    }

    public String getToken(String line) {
        return line.substring(7);
    }

    public Token createToken(String name) {
        token.setToken(Jwts.builder()
                .setSubject(name)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact());

        return token;
    }

    public String getUserToken(String name) {
        return createToken(name).toString();
    }
}