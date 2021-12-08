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

    public String[] getHistoryMessage(Long userId, String message){
       List<Message> history = messageDao.getMessageHistory(userId);

       int messagesCount = Integer.parseInt(message);

        String[] s;

       if(messagesCount > history.size()){
           s = new String[history.size()];
       }else {
           s = new String[messagesCount];
       }

       int i = 0;

       for (Message m : history){
           s[i] = m.getMessage();

           i++;
       }

        return s;
    }
}