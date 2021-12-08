package test.inside.dao;

import org.springframework.stereotype.Repository;
import test.inside.model.Message;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class MessageDaoImpl extends MessageGenericDaoImpl<Message, Long> implements MessageDao {
    public MessageDaoImpl() {
        super(Message.class);
    }

    @Override
    public List<Message> getMessageHistory(Long userId) {
        return findAll().stream()
                .filter(m -> Objects.equals(m.getUserId(), userId))
                .collect(Collectors.toList());
    }
}