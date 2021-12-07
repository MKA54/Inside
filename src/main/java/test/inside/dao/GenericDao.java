package test.inside.dao;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {
    @Transactional
    List<T> findAll();
}