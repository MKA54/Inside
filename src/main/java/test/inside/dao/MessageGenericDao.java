package test.inside.dao;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public interface MessageGenericDao<T, PK extends Serializable> {
    @Transactional
    void create(T obj);
}