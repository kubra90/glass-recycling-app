package com.techelevator.dao;

import com.techelevator.model.PickupDetails;

import java.util.Date;
import java.util.List;

public interface PickupDetailsDao {

    PickupDetails getPickupDetailsByPickupId(int pickupId);

    List<PickupDetails> getAllUnassignedPickups();

    List<PickupDetails> getUnassignedPickupsByUsername(String username);

    List<PickupDetails> getAllOutstandingPickups();

    List<PickupDetails> getOutstandingPickupsByUsername(String username);

    List<PickupDetails> getAllPickupDetails();

    List<PickupDetails> getPickupDetailsByRecyclerUsername(String recyclerUsername);

    List<PickupDetails> getPickupDetailsByDriverUsername(String driverUsername);

    List<PickupDetails> getCompletedPickupDetailsByRecyclerUsername(String recyclerUsername);

    List<PickupDetails> getPickupDetailsByRouteId(int routeId);

    List<PickupDetails> getPickupDetailsByDriverId(int driverId);

    PickupDetails createPickupDetails(PickupDetails pickupDetails);

    void updatePickupDetails(PickupDetails pickupDetails);

    void pickupConfirmed(String username, int pickup_weight);

    void deletePickupDetails(int pickup_id);

}
