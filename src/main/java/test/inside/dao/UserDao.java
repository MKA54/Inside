package test.inside.dao;

import test.inside.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Long> {
    List<User> getAllContacts();
}