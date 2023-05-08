package com.techelevator.dao;

import com.techelevator.model.DriverDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDriverDetailsDao implements DriverDetailsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcDriverDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Home office address
    private static final String HOME_OFFICE_ADDRESS = "3001 Railroad St, Pittsburgh, PA 15201";


    //Get all driver_details from driver_details table
    @Override
    public List<DriverDetails> getAllDrivers() {
        List<DriverDetails> allDrivers = new ArrayList<>();
        String sql = "SELECT driver_id, username, home_office_address FROM driver_details;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            DriverDetails driverDetails = mapRowToDriverDetails(results);
            allDrivers.add(driverDetails);
        }
        return allDrivers;
    }

    //Get all driver_details from driver_details table, by driver_id
    @Override
    public DriverDetails getDriverByDriverId(int driverId) {
        DriverDetails driver = null;
        String sql = "SELECT driver_id, username, home_office_address FROM driver_details " +
                     "WHERE driver_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, driverId);
        if (result.next()){
            driver = mapRowToDriverDetails(result);
        }
        return driver;
    }

    //Get all driver_details from driver_details table, by username
    @Override
    public DriverDetails getDriverByUsername(String username) {
        DriverDetails driver = null;
        String sql = "SELECT driver_id, username, home_office_address FROM driver_details " +
                    "WHERE username = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()){
            driver = mapRowToDriverDetails(result);
        }
        return driver;
    }

    //Add driver to driver_details table
    @Override
    public DriverDetails createDriver(DriverDetails driverDetails) {
        String sql = "INSERT INTO driver_details (username, home_office_address) " +
                    "VALUES (?, ?) RETURNING driver_id;";
        Integer driverId = jdbcTemplate.queryForObject(sql, Integer.class, driverDetails.getUsername(), HOME_OFFICE_ADDRESS);
        return getDriverByDriverId(driverId);
    }

    //Update a driver in the driver_details table
    @Override
    public void updateDriver(DriverDetails driverDetails) {
        String sql = "UPDATE driver_details " +
                    "SET driver_id = ?, username = ?, home_office_address = ? " +
                    "WHERE driver_id = ?;";
        jdbcTemplate.update(sql, driverDetails.getDriver_id(), driverDetails.getUsername(), driverDetails.getHome_office_address(), driverDetails.getDriver_id());
    }

    //Delete driver from driver_details table
    //Can run into FK constraint if driver is associated with routes/pickups
    @Override
    public void deleteDriver(int driverId) {
        String sql = "DELETE FROM driver_details WHERE driver_id = ?;";
        jdbcTemplate.update(sql, driverId);
    }

    private DriverDetails mapRowToDriverDetails(SqlRowSet rs) {

        DriverDetails driverDetails = new DriverDetails();

        driverDetails.setDriver_id(rs.getInt("driver_id"));
        driverDetails.setUsername(rs.getString("username"));
        driverDetails.setHome_office_address(rs.getString("home_office_address"));

        return driverDetails;
    }
}
