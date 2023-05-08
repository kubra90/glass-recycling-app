package com.techelevator.controller;

import com.techelevator.dao.DriverDetailsDao;
import com.techelevator.dao.RoutesDao;
import com.techelevator.model.DriverDetails;
import com.techelevator.model.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class RoutesController {

    private RoutesDao routesDao;
    private DriverDetailsDao driverDetailsDao;

    public RoutesController(RoutesDao routesDao, DriverDetailsDao driverDetailsDao) {
        this.routesDao = routesDao;
        this.driverDetailsDao = driverDetailsDao;
    }


    //RoutesDao Methods start here **********


    //Get My Routes --- Will return routes linked to logged in DRIVER account
    //--- filtering by Date, thinking this could be done w/ a filter function on the front end
    @RequestMapping(path="/routes/myRoutes", method= RequestMethod.GET)
    public List<Routes> getMyRoutes(Principal principal) {

        DriverDetails loggedInDriver = driverDetailsDao.getDriverByUsername(principal.getName());
        int driver_id = loggedInDriver.getDriver_id();

        List<Routes> myRoutes = routesDao.getRoutesByDriverId(driver_id);

        if (myRoutes.size() != 0) {
            return myRoutes;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no Routes assigned to this particular Driver");
        }
    }

    //Get all routes from the routes table
    @RequestMapping(path="/routes", method= RequestMethod.GET)
    public List<Routes> getAllRoutes() {
        List<Routes> allRoutes = routesDao.getAllRoutes();
        if (allRoutes.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no Routes to report at this time");
        }
        return allRoutes;
    }

    //Get a Route object from the routes table, by route_id
    @RequestMapping(path="/routes/{routeId}", method= RequestMethod.GET)
    public Routes getRouteByRouteId(@PathVariable int routeId) {
        Routes route = null;
        route = routesDao.getRoutesByRouteId(routeId);
        if (route != null) {
            return route;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such route exists");
        }
    }

    //Get a List of Route objects from the routes table, grouped by driver_id
    @RequestMapping(path="/routes/drivers/{driver_Id}", method= RequestMethod.GET)
    public List<Routes> getRoutesByDriverId(@PathVariable int driver_Id) {

        if (driverDetailsDao.getDriverByDriverId(driver_Id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "We do not have a Driver associated with this Driver ID");
        }

        List<Routes> results = new ArrayList<>();
        results = routesDao.getRoutesByDriverId(driver_Id);
        if (results.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That Driver is not currently assigned to any routes");
        } else {
            return results;
        }
    }

    //Add a route to the routes table - returning the new Route object
    //Admins only
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/routes", method= RequestMethod.POST)
    public Routes addNewRoute(@Valid @RequestBody Routes newRoute) {
        if (newRoute.getRouteDate() != null) {
            return routesDao.createRoute(newRoute);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Route details provided in the request");
        }
    }

    //Update a route on the routes table - returning the updated route
    //Utilize to Assign a driver to a route
    //Admins only
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path="/routes/{routeId}", method= RequestMethod.PUT)
    public Routes updateRoute(@Valid @RequestBody Routes routeToUpdate, @PathVariable int routeId) {
        if (routeToUpdate.getRouteId() != routeId) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Route ID provided does not match the Route record you're attempting to update");
        } else if (routesDao.getRoutesByRouteId(routeId) == null || routesDao.getRoutesByRouteId(routeToUpdate.getRouteId()) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That route does not exist");
        } else {
            Routes updatedRoute = null;
            routesDao.updateRoute(routeToUpdate);
            updatedRoute = routesDao.getRoutesByRouteId(routeId);
            return updatedRoute;

        }
    }

    //Deletes a route from the routes table
    //Admins only
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/routes/{routeId}", method= RequestMethod.DELETE)
    public void deleteRoute(@PathVariable int routeId) {
        if (routesDao.getRoutesByRouteId(routeId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Route record you're attempting to delete, does not exist");
        } else {
            routesDao.deleteRoute(routeId);
            }
        }
    }
