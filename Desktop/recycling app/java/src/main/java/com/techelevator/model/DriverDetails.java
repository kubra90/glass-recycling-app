package com.techelevator.model;

import javax.validation.constraints.NotBlank;
import java.sql.Driver;

public class DriverDetails {

    private int driver_id;
    private String username;
    @NotBlank
    private String home_office_address;

    public DriverDetails() {}

    public DriverDetails(int driver_id, String username, String home_office_address) {
        this.driver_id = driver_id;
        this.username = username;
        this.home_office_address = home_office_address;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHome_office_address() {
        return home_office_address;
    }

    public void setHome_office_address(String home_office_address) {
        this.home_office_address = home_office_address;
    }

    @Override
    public String toString() {
        return "DriverDetails{" +
                "driver_id=" + driver_id +
                ", username='" + username + '\'' +
                ", home_office_address='" + home_office_address + '\'' +
                '}';
    }
}
