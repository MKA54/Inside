package test.inside.service;

import org.springframework.stereotype.Service;
import test.inside.dao.UserDao;
import test.inside.model.Token;
import test.inside.model.User;

@Service
public class UserService {
    private final UserDao userDao;
    private final TokenService tokenService;

    public UserService(UserDao userDao, TokenService tokenService) {
        this.userDao = userDao;
        this.tokenService = tokenService;
    }

    public Token userValidation(User user) {
        String name = user.getName();
        String password = user.getPassword();

        if (name.length() == 0) {
            System.out.println("Enter your name");

            return null;
        }

        if (password.length() == 0) {
            System.out.println("Password not entered");

            return null;
        }

        for (User u : userDao.getAllUsers()) {
            if (name.equals(u.getName())) {
                if (password.equals(u.getPassword())) {
                    return tokenService.createToken(user.getName());
                }
            }
        }

        System.out.println("User is not found");

        return null;
    }

    public Long getUserId(String name) {
        for (User user : userDao.getAllUsers()) {
            if (user.getName().equals(name)) {

                return user.getId();
            }
        }

        return null;
    }
}