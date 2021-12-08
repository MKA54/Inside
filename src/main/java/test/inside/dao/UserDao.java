package test.inside.dao;

import test.inside.model.User;

import java.util.List;

public interface UserDao extends UserGenericDao<User, Long> {
    List<User> getAllContacts();
}