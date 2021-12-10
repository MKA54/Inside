package test.inside.dao;

import org.springframework.stereotype.Repository;
import test.inside.model.User;

import java.util.List;

@Repository
public class UserImplDao extends UserGenericDaoImpl<User, Long> implements UserDao {
    public UserImplDao() {
        super(User.class);
    }

    @Override
    public List<User> getAllUsers() {
        return findAll();
    }
}