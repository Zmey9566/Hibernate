package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    void createTable();
    void dropTable();
    void saveUser(String name, int age);
    void removeUserById(long id);
    void getUserById(long id);
    List<User> getAllUsers();
    void cleanUserTable();

}
