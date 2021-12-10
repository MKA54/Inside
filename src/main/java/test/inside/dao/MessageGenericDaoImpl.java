package test.inside.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Transactional
public class MessageGenericDaoImpl<T, PK extends Serializable> implements MessageGenericDao<T, PK> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> clazz;

    public MessageGenericDaoImpl(Class<T> type) {
        this.clazz = type;

    }

    @Transactional
    @Override
    public void create(T obj) {
        entityManager.persist(obj);
    }

    @Transactional
    @Override
    public List<T> getMessageHistoryUsers(Long userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);

        Root<T> root = cq.from(clazz);

        CriteriaQuery<T> select = cq.select(root);
        cq.where(cb.equal(root.get("userId"), userId));

        TypedQuery<T> q = entityManager.createQuery(select);

        return q.getResultList();
    }
}