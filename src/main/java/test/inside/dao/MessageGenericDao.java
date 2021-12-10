package test.inside.dao;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public interface MessageGenericDao<T, PK extends Serializable> {
    @Transactional
    void create(T obj);

    @Transactional
    List<T> getMessageHistoryUsers(Long userId);
}