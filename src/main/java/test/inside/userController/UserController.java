package test.inside.userController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import test.inside.dto.MessageDto;
import test.inside.model.Message;
import test.inside.model.Token;
import test.inside.model.User;
import test.inside.service.MessageService;
import test.inside.service.TokenService;
import test.inside.service.UserService;

import java.util.List;

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
    public Boolean addMessage(@RequestHeader("Authorization") String token, @RequestBody String message) {
        MessageDto m = gson.fromJson(message, MessageDto.class);

        if (tokenService.isToken(token)) {
            token = tokenService.getToken(token);

            if (token.equals(tokenService.getUserToken(m.getName()))) {
                return messageService.addMessage(userService.getUserId(m.getName()), m);
            }
        }

        return false;
    }

    @RequestMapping(value = "getMessageHistory", method = RequestMethod.POST)
    @ResponseBody
    public List<Message> getMessageHistory(@RequestHeader("Authorization") String token, @RequestBody String message) {
        MessageDto m = gson.fromJson(message, MessageDto.class);

        if (tokenService.isToken(token)) {
            token = tokenService.getToken(token);

            if (token.equals(tokenService.getUserToken(m.getName()))) {
                return messageService.getHistoryMessage(userService.getUserId(m.getName()), m.getMessage());
            }
        }

        return null;
    }
}