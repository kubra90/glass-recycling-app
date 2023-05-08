package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //get all users from users table (recyclers, admins, drivers)
    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    //get all drivers from users table (is_driver = true)
    @Override
    public List<User> listAllDrivers() {
        List<User> allDrivers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE is_driver = true AND role = 'ROLE_USER';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User driver = mapRowToUser(results);
            allDrivers.add(driver);
        }
        return allDrivers;
    }

    //returns list of all recycling users (is_driver = false && role = 'ROLE_USER')
    @Override
    public List<User> listAllRecyclers() {
        List<User> allRecyclers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE is_driver = false AND role = 'ROLE_USER';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            allRecyclers.add(user);
        }
        return allRecyclers;
    }

    //Get user from the users table, by username
    @Override
    public User findUserByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (User user : this.findAllUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    //Get user from users table, by user_id
    @Override
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToUser(results);
        } else {
            return null;
        }
    }

    //Get user_id from the users table, by username
    @Override
    public int findUserIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("SELECT user_id FROM users WHERE username = ?", Integer.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }
        return userId;
    }

    //Add a new user to the users table
    //implemented via Authentication Controller to create both User and Driver accounts
    @Override
    public boolean create(String username, String password, String role, boolean is_driver) {
        String insertUserSql = "INSERT INTO users (username,password_hash,role,is_driver) values (?,?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();

        return jdbcTemplate.update(insertUserSql, username, password_hash, ssRole, is_driver) == 1;
    }

    //Delete user from the users table - keeping mind of FK constraints, where else do we need to delete first?
    public void deleteUser(int userId) {
        String deleteSql = "DELETE FROM users WHERE user_id = ?;";
        jdbcTemplate.update(deleteSql, userId);
    }

    private User mapRowToUser(SqlRowSet rs) {

        User user = new User();

        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setIs_driver(rs.getBoolean("is_driver"));
        user.setActivated(true);

        return user;
    }
}
