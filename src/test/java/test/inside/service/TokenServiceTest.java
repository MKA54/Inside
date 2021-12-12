package test.inside.service;

import org.junit.jupiter.api.Test;;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.inside.model.Token;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TokenServiceTest {
    @Autowired
    TokenService tokenService;

    @Test
    void isToken() {
        String token = "Bearer 43f87i34f7943f8934843";
        assertTrue(tokenService.isToken(token));
    }

    @Test
    void getToken() {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbmRyZSJ9.14hrrnDfUL5awjCvltHPpsx607qlz_9JML7pvLdKLyj-TF07OZNyfPsOyKvYg53hy6omGpKmoQyoL5C4oJ6p2w";
        assertEquals("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbmRyZSJ9.14hrrnDfUL5awjCvltHPpsx607qlz_9JML7pvLdKLyj-TF07OZNyfPsOyKvYg53hy6omGpKmoQyoL5C4oJ6p2w", tokenService.getToken(token));
    }

    @Test
    void createToken() {
        Token token = tokenService.createToken("Andre");
        assertEquals("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbmRyZSJ9.14hrrnDfUL5awjCvltHPpsx607qlz_9JML7pvLdKLyj-TF07OZNyfPsOyKvYg53hy6omGpKmoQyoL5C4oJ6p2w", token.getToken());
    }

    @Test
    void getUserToken() {
        assertEquals("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbmRyZSJ9.14hrrnDfUL5awjCvltHPpsx607qlz_9JML7pvLdKLyj-TF07OZNyfPsOyKvYg53hy6omGpKmoQyoL5C4oJ6p2w", tokenService.getUserToken("Andre"));
    }
}