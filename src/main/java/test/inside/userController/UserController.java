package test.inside.userController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import test.inside.dto.MessageDto;
import test.inside.model.Token;
import test.inside.model.User;
import test.inside.service.UserService;

@Controller
@RequestMapping("/inside")
public class UserController {
    private final UserService userService;
    private final Gson gson = new GsonBuilder().create();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "authorization", method = RequestMethod.POST)
    @ResponseBody
    public Token checkPassword(@RequestBody String user) {
        User u = gson.fromJson(user, User.class);

        return userService.userValidation(u);
    }

    @RequestMapping(value = "addMessage", method = RequestMethod.POST)
    @ResponseBody
    public void addMessage(@RequestBody String message) {
        MessageDto m = gson.fromJson(message, MessageDto.class);

        userService.addMessage(m);
    }
}