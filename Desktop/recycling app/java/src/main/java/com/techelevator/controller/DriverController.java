package com.techelevator.controller;

import com.techelevator.dao.DriverDetailsDao;
import com.techelevator.dao.PickupDetailsDao;
import com.techelevator.dao.RoutesDao;
import com.techelevator.model.DriverDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class DriverController {

    private DriverDetailsDao driverDetailsDao;
    private PickupDetailsDao pickupDetailsDao;
    private RoutesDao routesDao;

    public DriverController(DriverDetailsDao driverDetailsDao, PickupDetailsDao pickupDetailsDao, RoutesDao routesDao) {
        this.driverDetailsDao = driverDetailsDao;
        this.pickupDetailsDao = pickupDetailsDao;
        this.routesDao = routesDao;
    }


    //DriverDetailsDao Methods start here **********


    //Get all driver details objects from the driver_details table
    @RequestMapping(path="/driverDetails", method= RequestMethod.GET)
    public List<DriverDetails> getAllDrivers() {
        return driverDetailsDao.getAllDrivers();
    }

    //Get a driver detail object from the driver_details table, using driver_id
    @RequestMapping(path="/driverDetails/{driver_id}", method= RequestMethod.GET)
    public DriverDetails getDriverByEmployeeId(@PathVariable int driver_id) {
        DriverDetails driver = null;
        driver = driverDetailsDao.getDriverByDriverId(driver_id);

        if (driver == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no driver details for that employee ID");
        } else {
            return driver;
        }
    }

    //Deletes a driver detail from the driver_details table
    //Admins only
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/driverDetails/{driver_id}", method= RequestMethod.DELETE)
    public void deleteDriverDetail(@PathVariable int driver_id) {
        if (driverDetailsDao.getDriverByDriverId(driver_id) != null) {
            driverDetailsDao.deleteDriver(driver_id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The driver you're attempting to delete, does not exist");
        }
    }


    //Create a driver detail in the drivers_details table
    // Update 2/5 - Shouldn't need this handler method, /addDriver endpoint in Authentication Controller implements this already upon driver registration
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/driverDetails", method= RequestMethod.POST)
    public DriverDetails addDriverDetail(@RequestBody DriverDetails newDriver) {
        if (newDriver.getUsername() == null || newDriver.getHome_office_address() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough details provided in the request!");
        } else if (driverDetailsDao.getDriverByUsername(newDriver.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "That Driver username already exists");
        } else {
            return driverDetailsDao.createDriver(newDriver);
        }
    }

    //Update a row in the driver_details table - returns the updated Driver Detail object
    //Admins only
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path="/driverDetails/{driver_id}", method= RequestMethod.PUT)
    public DriverDetails updateDriverDetail(@RequestBody DriverDetails driverToUpdate, @PathVariable int driver_id) {
        if (driverToUpdate.getDriver_id() != driver_id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Driver ID provided does not match the record you're attempting to update");
        } else if (driverDetailsDao.getDriverByDriverId(driverToUpdate.getDriver_id()) == null || driverDetailsDao.getDriverByDriverId(driver_id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no Driver associated with that Driver ID");
        } else {
            driverDetailsDao.updateDriver(driverToUpdate);
            return driverDetailsDao.getDriverByDriverId(driver_id);
        }
    }
}

