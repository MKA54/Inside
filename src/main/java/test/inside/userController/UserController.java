package test.inside.userController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import test.inside.dto.MessageDto;
import test.inside.model.Token;
import test.inside.model.User;
import test.inside.service.MessageService;
import test.inside.service.TokenService;
import test.inside.service.UserService;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequestMapping("/inside")
public class UserController {
    private final UserService userService;
    private final MessageService messageService;
    private final TokenService tokenService;
    private final Gson gson = new GsonBuilder().create();

    public UserController(UserService userService, MessageService messageService, TokenService tokenService) {
        this.userService = userService;
        this.messageService = messageService;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "authorization", method = RequestMethod.POST)
    @ResponseBody
    public Token checkPassword(@RequestBody String user) {
        User u = gson.fromJson(user, User.class);

        return userService.userValidation(u);
    }

    @RequestMapping(value = "addMessage", method = RequestMethod.POST)
    @ResponseBody
    public void addMessage(@RequestHeader("Authorization") String token, @RequestBody String message) {
        MessageDto m = gson.fromJson(message, MessageDto.class);

        if (tokenService.isToken(token)) {
            token = tokenService.getToken(token);

            if (token.equals(tokenService.getUserToken(m.getName()))) {
                messageService.addMessage(userService.getUserId(m.getName()), m);
            }
        }
    }

    @RequestMapping(value = "getMessageHistory", method = RequestMethod.POST)
    @ResponseBody
    public String[] getMessageHistory(@RequestHeader("Authorization") String token, @RequestBody String message) {
        MessageDto m = gson.fromJson(message, MessageDto.class);

        if (!(hasText(m.getMessage()) && m.getMessage().startsWith("history "))) {
            System.out.println("The message format is not correct");

            return null;
        }

        m.setMessage(m.getMessage().substring(8));

        if (tokenService.isToken(token)) {
            token = tokenService.getToken(token);

            if (token.equals(tokenService.getUserToken(m.getName()))) {
                return messageService.getHistoryMessage(userService.getUserId(m.getName()), m.getMessage());
            }
        }

        return null;
    }
}