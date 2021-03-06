package test.inside.service;

import org.springframework.stereotype.Service;
import test.inside.dao.MessageDao;
import test.inside.dto.MessageDto;
import test.inside.model.Message;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Service
public class MessageService {
    private final MessageDao messageDao;

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public Boolean addMessage(Long userId, MessageDto messageDto) {
        if (messageDto.getMessage().length() == 0) {
            System.out.println("Empty message");

            return false;
        }

        if (messageDto.getName().length() == 0) {
            System.out.println("Missing name");

            return false;
        }

        if (userId == null) {
            System.out.println("UserId not found");

            return false;
        }

        Message message = new Message();
        message.setUserId(userId);
        message.setMessage(messageDto.getMessage());

        messageDao.create(message);

        return true;
    }

    public List<Message> getHistoryMessage(Long userId, String message) {
        if (!(hasText(message) && message.startsWith("history "))) {
            System.out.println("The message format is not correct");

            return null;
        }

        message = message.substring(8);
        int messagesCount = Integer.parseInt(message);

        if (messagesCount < 1) {
            System.out.println("Incorrect number of messages sent, history: " + messagesCount);

            return null;
        }

        return messageDao.getMessageHistory(userId, messagesCount);
    }
}