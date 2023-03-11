package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDAO {
    void createTable();
    void dropTable();
    void saveUser(String name, int age);
    void removeUserById(long id);
    void getUserById(long id);
    public List<User> getAllUsers ();
    void cleanUserTable();
}
