package com.techelevator.model;

import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.Date;

public class PickupDetails {

    private int pickup_id;
    private int route_id;
    private String requesting_username;
    //Blocks past or same day pickup requests - must be at least 1 day in advance
    @Future(message = "You must schedule a pickup for a future date")
    private LocalDate pickup_date;
    private int pickup_weight;
    private int num_of_bins;
    private Boolean is_picked_up;
    private String full_address;

    private final int FULL_BIN_WEIGHT = 60; //Full bin is 60lbs

    public PickupDetails() {}

    public PickupDetails(int pickup_id, int route_id, String requesting_username, LocalDate pickup_date, int pickup_weight, int num_of_bins, Boolean is_picked_up, String full_address) {
        this.pickup_id = pickup_id;
        this.route_id = route_id;
        this.requesting_username = requesting_username;
        this.pickup_date = pickup_date;
        this.pickup_weight = pickup_weight;
        this.num_of_bins = num_of_bins;
        this.is_picked_up = is_picked_up;
        this.full_address = full_address;
    }

    //Method to calc pickup_weight for each pickup requested, Full bin weight X num of bins
    public int calcPickupWeight() {
        pickup_weight = num_of_bins * FULL_BIN_WEIGHT;
        return pickup_weight;
    }

    public int getPickup_id() {
        return pickup_id;
    }

    public void setPickup_id(int pickup_id) {
        this.pickup_id = pickup_id;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getRequesting_username() {
        return requesting_username;
    }

    public void setRequesting_username(String requesting_username) {
        this.requesting_username = requesting_username;
    }

    public LocalDate getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(LocalDate pickup_date) {
        this.pickup_date = pickup_date;
    }

    public int getNum_of_bins() {
        return num_of_bins;
    }

    public void setNum_of_bins(int num_of_bins) {
        this.num_of_bins = num_of_bins;
    }

    public int getPickup_weight() {
        return pickup_weight;
    }

    public void setPickup_weight(int pickup_weight) {
        this.pickup_weight = pickup_weight;
    }

    public Boolean getIs_picked_up() {
        return is_picked_up;
    }

    public void setIs_picked_up(Boolean is_picked_up) {
        this.is_picked_up = is_picked_up;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }
}
