package test.inside.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.inside.dao.MessageGenericDao;
import test.inside.dao.UserDao;
import test.inside.dto.MessageDto;
import test.inside.model.Message;
import test.inside.model.Token;
import test.inside.model.User;

@Service
public class UserService {
    @Value("${jwt.secret}")
    private String SECRET_KEY = "1";

    private final UserDao userDao;
    private final MessageGenericDao<Message, Long> messageGenericDao;
    private final Token token = new Token();

    public UserService(UserDao userDao, MessageGenericDao<Message, Long> messageGenericDao) {
        this.userDao = userDao;
        this.messageGenericDao = messageGenericDao;
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

        for (User u : userDao.getAllContacts()) {
            if (name.equals(u.getName())) {
                if (password.equals(u.getPassword())) {
                    return createToken(user.getName());
                }
            }
        }

        System.out.println("User is not found");

        return null;
    }

    public Token createToken(String name) {
        token.setToken(Jwts.builder()
                .setSubject(name)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact());

        return token;
    }

    public void addMessage(MessageDto messageDto) {
        if (messageDto.getMessage().length() == 0) {
            System.out.println("Empty message");

            return;
        }

        if (messageDto.getName().length() == 0) {
            System.out.println("Missing name");

            return;
        }

        for (User user : userDao.getAllContacts()) {
            if (user.getName().equals(messageDto.getName())) {

                Message message = new Message();
                message.setUserId(user.getId());
                message.setMessage(messageDto.getMessage());

                messageGenericDao.create(message);

                return;
            }
        }
    }
}