package test.inside.dao;

import org.springframework.stereotype.Repository;
import test.inside.model.User;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public List<User> getAllContacts() {
        return findAll();
    }
}