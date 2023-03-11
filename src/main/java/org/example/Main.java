package org.example;

import org.example.service.UserService;
import org.example.service.UserServiceimpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceimpl();
        userService.dropTable();
        userService.createTable();
        userService.saveUser("Oksana", 30);
        userService.saveUser("Dima", 25);
        userService.saveUser("Alex", 33);
        userService.removeUserById(3);
        userService.getUserById(3);
        userService.getAllUsers();
//        userService.cleanUserTable();
    }
}