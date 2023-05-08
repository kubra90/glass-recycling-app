package com.techelevator.dao;

import com.techelevator.model.UserDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDetailsDao implements UserDetailsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final int STARTING_TOTAL_POUNDS = 0;
    private static final int STARTING_CREDITS = 0;
    private static final int STARTING_CREDITS_REDEEMED = 0;


    // Get the total pounds of glass recycled across all users in the application
    // "How many pounds of glass has Vitrum kept out of landfills?"
    @Override
    public long getTotalGlassRecycled() {
        long totalGlassRecycled = 0;
        String sql = "SELECT SUM(total_pounds_recycled) AS total_glass_recycled " +
                    "FROM user_details;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

        if (result.next()) {
            totalGlassRecycled = result.getLong("total_glass_recycled");
        }
        return totalGlassRecycled;
    }

    //Get all user details from user_details table
    @Override
    public List<UserDetails> findAllUserDetails() {
        List<UserDetails> allUsers = new ArrayList<>();
        String sql = "SELECT account_id, username, full_name, street_address, city, state_abbreviation, " +
                "zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed " +
                "FROM user_details;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            UserDetails userDetails = mapRowToUserDetail(results);
            allUsers.add(userDetails);
        }
        return allUsers;
    }

    //Get user detail from user_details table, using account_id
    @Override
    public UserDetails findUserDetailsByAccountId(int account_id) {
        UserDetails userDetails = null;
        String sql = "SELECT account_id, username, full_name, street_address, city, state_abbreviation, " +
                "zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed " +
                "FROM user_details " +
                "WHERE account_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, account_id);
        if (result.next()){
            userDetails = mapRowToUserDetail(result);
        }
        return userDetails;
    }

    //Get account_id from user_details table, by username
    public int getAccountIdByUsername(String username) {
        String sql = "SELECT account_id " +
                    "FROM user_details " +
                    "WHERE username = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()) {
            int accountId = result.getInt("account_id");
            return accountId;
        }
        return 0;
    }

    //Get My User_Details in UserController calls this, feeds in principal.getName()
    @Override
    public UserDetails findUserDetailsByUsername(String username) {
        UserDetails userDetails = null;
        String sql = "SELECT account_id, username, full_name, street_address, city, state_abbreviation, " +
                "zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed " +
                "FROM user_details " +
                "WHERE username = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()){
            userDetails = mapRowToUserDetail(result);
        }
        return userDetails;
    }

    //add new user detail to user_details table --- registering a user after they've completed information form
    @Override
    public UserDetails createUserDetails(UserDetails userDetails) {
        String sql = "INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, " +
                                                "phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed) " +
                                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING account_id;";
        Integer account_id = jdbcTemplate.queryForObject(sql, Integer.class, userDetails.getUsername(), userDetails.getFull_name(), userDetails.getStreet_address(),
                             userDetails.getCity(), userDetails.getState_abbreviation(), userDetails.getZipcode(), userDetails.getPhone_number(),
                             userDetails.getEmail_address(), STARTING_TOTAL_POUNDS, STARTING_CREDITS, STARTING_CREDITS_REDEEMED);

        return findUserDetailsByAccountId(account_id);
    }

    //update a user detail on the user_details table
    @Override
    public void updateUserDetails(UserDetails userDetails) {
        String sql = "UPDATE user_details " +
                    "SET username = ?, full_name = ?, street_address = ?, city = ?, state_abbreviation = ?, zipcode = ?, " +
                    "phone_number = ?, email_address = ?, total_pounds_recycled = ?, credits_balance = ?, credits_redeemed = ? " +
                    "WHERE account_id = ?;";
        jdbcTemplate.update(sql, userDetails.getUsername(), userDetails.getFull_name(), userDetails.getStreet_address(),
                userDetails.getCity(), userDetails.getState_abbreviation(), userDetails.getZipcode(), userDetails.getPhone_number(),
                userDetails.getEmail_address(), userDetails.getTotal_pounds_recycled(), userDetails.getCredits_balance(), userDetails.getCredits_redeemed(),
                userDetails.getAccount_id());
    }

    //deleting a user detail from user_details table
    @Override
    public void deleteUserDetails(int account_id) {
        String sql = "DELETE FROM user_details WHERE account_id = ?;";
        jdbcTemplate.update(sql, account_id);
    }

    //Get full address in string format for Routes API, using account_id
    //example - '3001 Railroad St, Pittsburgh, PA 15201'
    @Override
    public String getFullAddressByAccountId(int account_id) {
        String address = null;
        String sql = "SELECT street_address, city, state_abbreviation, zipcode " +
                    "FROM user_details WHERE account_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, account_id);
        if (result.next()){
            address = result.getString("street_address") + ", " + result.getString("city") + ", " +
                                result.getString("state_abbreviation") + " " + result.getString("zipcode");
        }
        return address;
    }

    //Get full address in string format for Routes API, using username
    //getMyFullAddress in UserController utilizes this, feeding in Principal.getName()
    //example - '3001 Railroad St, Pittsburgh, PA 15201'
    @Override
    public String getFullAddressByUsername(String username) {
        String address = null;
        String sql = "SELECT street_address, city, state_abbreviation, zipcode " +
                "FROM user_details WHERE username = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()){
            address = result.getString("street_address") + ", " + result.getString("city") + ", " +
                    result.getString("state_abbreviation") + " " + result.getString("zipcode");
        }
        return address;
    }

    //Get total amount of glass recycled for an account_id
    @Override
    public int getTotalGlassRecycledByAccountId(int account_id) {
        int totalGlassRecycled = 0;
        String sql = "SELECT total_pounds_recycled " +
                "FROM user_details WHERE account_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, account_id);
        if (result.next()){
            totalGlassRecycled = result.getInt("total_pounds_recycled");
        }
        return totalGlassRecycled;
    }

    //Get current credit/point balance for an account_id
    @Override
    public int getCreditBalanceByAccountId(int account_id) {
        int currentBalance = 0;
        String sql = "SELECT credits_balance " +
                    "FROM user_details WHERE account_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, account_id);
        if (result.next()){
            currentBalance = result.getInt("credits_balance");
        }
        return currentBalance;
    }

    //Get the total amount of credits an account has redeemed towards prizes
    @Override
    public int getCreditRedeemedByAccountId(int account_id) {
        int creditsRedeemed = 0;
        String sql = "SELECT credits_redeemed " +
                "FROM user_details WHERE account_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, account_id);
        if (result.next()){
            creditsRedeemed = result.getInt("credits_redeemed");
        }
        return creditsRedeemed;
    }



    private UserDetails mapRowToUserDetail(SqlRowSet rs) {
        UserDetails userDetail = new UserDetails();

        userDetail.setAccount_id(rs.getInt("account_id"));
        userDetail.setUsername(rs.getString("username"));
        userDetail.setFull_name(rs.getString("full_name"));
        userDetail.setStreet_address(rs.getString("street_address"));
        userDetail.setCity(rs.getString("city"));
        userDetail.setState_abbreviation(rs.getString("state_abbreviation"));
        userDetail.setZipcode(rs.getString("zipcode"));
        userDetail.setPhone_number(rs.getString("phone_number"));
        userDetail.setEmail_address(rs.getString("email_address"));
        userDetail.setTotal_pounds_recycled(rs.getInt("total_pounds_recycled"));
        userDetail.setCredits_balance(rs.getInt("credits_balance"));
        userDetail.setCredits_redeemed(rs.getInt("credits_redeemed"));

        return userDetail;
    }
}
