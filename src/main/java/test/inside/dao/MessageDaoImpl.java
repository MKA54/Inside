package test.inside.dao;

import org.springframework.stereotype.Repository;
import test.inside.model.Message;

@Repository
public class MessageDaoImpl extends MessageGenericDaoImpl<Message, Long> implements MessageGenericDao<Message, Long> {
    public MessageDaoImpl() {
        super(Message.class);
    }
}