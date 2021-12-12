package test.inside.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.inside.model.User;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void userValidation() {
        User user = new User();
        user.setName("Andre");
        user.setPassword("123");

        assertEquals("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbmRyZSJ9.14hrrnDfUL5awjCvltHPpsx607qlz_9JML7pvLdKLyj-TF07OZNyfPsOyKvYg53hy6omGpKmoQyoL5C4oJ6p2w", userService.userValidation(user).getToken());

        user.setName("");
        assertNull(userService.userValidation(user));
        user.setName("Andre");

        user.setPassword("");
        assertNull(userService.userValidation(user));

        user.setName("Gleb");
        assertNull(userService.userValidation(user));
    }

    @Test
    void getUserId() {
        Long id = userService.getUserId("Andre");
        assertEquals(1, (long) id);
    }
}