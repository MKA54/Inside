package test.inside.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Transactional
public class MessageGenericDaoImpl<T, PK extends Serializable> implements MessageGenericDao<T, PK> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> clazz;

    public MessageGenericDaoImpl(Class<T> type) {
        this.clazz = type;

    }

    @Override
    public void create(T obj) {
        entityManager.persist(obj);
    }
}