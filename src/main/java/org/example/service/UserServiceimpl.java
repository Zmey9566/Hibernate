package org.example.service;

import org.example.dao.UserDAO;
import org.example.dao.UserDAOimpl;
import org.example.model.User;

import java.util.List;

public class UserServiceimpl implements UserService{
    UserDAO userDAO = new UserDAOimpl();

    @Override
    public void createTable() {
        userDAO.createTable();

    }

    @Override
    public void dropTable() {
        userDAO.dropTable();
    }

    @Override
    public void saveUser(String name, int age) {
        userDAO.saveUser(name, age);

    }

    @Override
    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    @Override
    public void getUserById(long id) {
        userDAO.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void cleanUserTable() {
        userDAO.cleanUserTable();
    }
}
