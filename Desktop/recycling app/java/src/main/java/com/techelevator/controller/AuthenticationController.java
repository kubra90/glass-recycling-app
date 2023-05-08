package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.DriverDetailsDao;
import com.techelevator.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

@RestController
@CrossOrigin
@PreAuthorize("permitAll")
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDao userDao;
    private DriverDetailsDao driverDetailsDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao, DriverDetailsDao driverDetailsDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
        this.driverDetailsDao = driverDetailsDao;
    }

    //Login for recyclers, drivers, admins
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);
        
        User user = userDao.findUserByUsername(loginDto.getUsername());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new LoginResponseDto(jwt, user), httpHeaders, HttpStatus.OK);
    }

    //Register a recycler user account(ROLE_USER) or an Admin account(ROLE_ADMIN). (is_driver = false by default)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerUser(@Valid @RequestBody RegisterUserDto newUser) {
        try {
            User user = userDao.findUserByUsername(newUser.getUsername());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists.");
        } catch (UsernameNotFoundException e) {
            userDao.create(newUser.getUsername(),newUser.getPassword(), newUser.getRole(), false);
        }
    }

    //Registers a new Driver account - adds new Driver to the users table && the driver_details table
    //Once registered via this handler method, the new Driver is ready to be assigned to routes/pickups
    //Must be logged in as admin to add driver
    //Initial Setup - admin sets both Username and password for the driver on the front-end (in admin dashboard)
    //
    //Goal:  admin sets Username and is shown options to either set a password or have one random generated
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/addDriver", method = RequestMethod.POST)
    public void registerDriver(@Valid @RequestBody RegisterUserDto newDriver) {
        try {
            User user = userDao.findUserByUsername(newDriver.getUsername());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists.");
        } catch (UsernameNotFoundException e) {
            //Registers the driver into the users table
            userDao.create(newDriver.getUsername(),newDriver.getPassword(), newDriver.getRole(), true);

            //Adds the new driver to driver_details table
            DriverDetails newDriverDetail = new DriverDetails();
            newDriverDetail.setUsername(newDriver.getUsername());
            newDriverDetail.setHome_office_address(newDriverDetail.getHome_office_address());
            driverDetailsDao.createDriver(newDriverDetail);
        }
    }
}

