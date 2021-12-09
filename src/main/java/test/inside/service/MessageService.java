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

    public void addMessage(Long userId, MessageDto messageDto) {
        if (messageDto.getMessage().length() == 0) {
            System.out.println("Empty message");

            return;
        }

        if (messageDto.getName().length() == 0) {
            System.out.println("Missing name");

            return;
        }

        if (userId == null) {
            System.out.println("UserId not found");

            return;
        }

        Message message = new Message();
        message.setUserId(userId);
        message.setMessage(messageDto.getMessage());

        messageDao.create(message);

    }

    public String[] getHistoryMessage(Long userId, String message) {
        if (!(hasText(message) && message.startsWith("history "))) {
            System.out.println("The message format is not correct");

            return null;
        }

        message = message.substring(8);

        List<Message> history = messageDao.getMessageHistory(userId);

        Message[] historyArray = history.toArray(new Message[0]);

        int messagesCount = Integer.parseInt(message);

        String[] result;

        if (historyArray.length > messagesCount) {
            result = new String[messagesCount];
        } else {
            result = new String[historyArray.length];
        }

        for (int i = historyArray.length - 1, j = 0; i >= 0; i--, j++) {
            if (j == messagesCount) {
                break;
            }

            result[j] = historyArray[i].getMessage();
        }

        return result;
    }
}