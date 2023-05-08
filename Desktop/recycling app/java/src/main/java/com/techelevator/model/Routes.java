package com.techelevator.model;

import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.Date;

public class Routes {

    private int routeId;
    //Blocks past or same day route creation - must be at least 1 day in advance
    @Future(message = "You must schedule a route for a future date")
    private LocalDate routeDate;
    private int driverId;

    public Routes() {}

    public Routes(int routeId, LocalDate routeDate, int driverId) {
        this.routeId = routeId;
        this.routeDate = routeDate;
        this.driverId = driverId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public LocalDate getRouteDate() {
        return routeDate;
    }

    public void setRouteDate(LocalDate routeDate) {
        this.routeDate = routeDate;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
}
