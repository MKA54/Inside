package test.inside.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.inside.dto.MessageDto;
import test.inside.model.Message;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MessageServiceTest {
    @Autowired
    MessageService messageService;

    @Test
    void addMessage() {
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Test");
        messageDto.setName("Andre");

        long userId = 1;

        messageService.addMessage(userId, messageDto);

        assertFalse(messageService.addMessage(null, messageDto));

        messageDto.setMessage("");
        assertFalse(messageService.addMessage(userId, messageDto));
        messageDto.setMessage("newTest");

        messageDto.setName("");
        assertFalse(messageService.addMessage(userId, messageDto));
    }

    @Test
    void getHistoryMessage() {
        List<Message> history = messageService.getHistoryMessage((long) 2, "history 5");
        assertEquals(5, history.size());

        assertNull(messageService.getHistoryMessage((long) 2, "5"));
        assertNull(messageService.getHistoryMessage((long) 2, "0"));
    }
}