package com.techelevator.dao;

import com.techelevator.model.DriverDetails;

import java.util.List;

public interface DriverDetailsDao {

    List<DriverDetails> getAllDrivers();

    DriverDetails getDriverByDriverId(int driverId);

    DriverDetails getDriverByUsername(String username);

    DriverDetails createDriver(DriverDetails driverDetails);

    void updateDriver(DriverDetails driverDetails);

    void deleteDriver(int driver_Id);

}
