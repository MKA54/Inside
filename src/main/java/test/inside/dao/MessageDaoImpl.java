package test.inside.dao;

import org.springframework.stereotype.Repository;
import test.inside.model.Message;

import java.util.List;

@Repository
public class MessageDaoImpl extends MessageGenericDaoImpl<Message, Long> implements MessageDao {
    public MessageDaoImpl() {
        super(Message.class);
    }

    @Override
    public List<Message> getMessageHistory(Long userId) {
        return getMessageHistoryUsers(userId);
    }
}