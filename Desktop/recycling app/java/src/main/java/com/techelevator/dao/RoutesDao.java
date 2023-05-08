package com.techelevator.dao;

import com.techelevator.model.Routes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RoutesDao {

    List<Routes> getAllRoutes();

    Routes getRoutesByRouteId(int routeId);

    List<Routes> getRoutesByDriverId(int driverId);

    LocalDate getRouteDateByRouteId(int routeId);

    List<Routes> getRoutesByDate(Date routeDate);

    Routes createRoute(Routes route);

    void updateRoute(Routes route);

    void deleteRoute(int routeId);

}
