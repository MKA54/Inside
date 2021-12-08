package test.inside.dao;

import test.inside.model.Message;

import java.util.List;

public interface MessageDao extends MessageGenericDao<Message, Long>{
    List<Message> getMessageHistory(Long userId);
}