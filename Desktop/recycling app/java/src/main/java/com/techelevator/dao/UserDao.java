package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAllUsers();

    List<User> listAllDrivers();

    List<User> listAllRecyclers();

    User getUserById(int userId);

    User findUserByUsername(String username);

    int findUserIdByUsername(String username);

    boolean create(String username, String password, String role, boolean is_driver);

    void deleteUser(int userId);

}
