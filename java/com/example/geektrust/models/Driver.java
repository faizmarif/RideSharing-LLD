package com.example.geektrust.models;

import com.example.geektrust.enums.DriverStatus;
import java.util.ArrayList;
import java.util.List;

public class Driver {
  private final String driverId;
  private Location location;
  private List<Ride> ridesHistory;
  private DriverStatus driverStatus;

  public Driver(String driverId) {
    this.driverId = driverId;
    ridesHistory = new ArrayList<>();
    driverStatus = DriverStatus.NOT_DRIVING;
  }

  public String getDriverId() {
    return driverId;
  }

  public Location getLocation() {
    return location;
  }

  public List<Ride> getRidesHistory() {
    return ridesHistory;
  }

  public DriverStatus getDriverStatus() {
    return driverStatus;
  }

  public Driver setLocation(Location location) {
    this.location = location;
    return this;
  }

  public Driver addRideInHistory(Ride ride) {
    this.ridesHistory.add(ride);
    return this;
  }

  public Driver setDriverStatus(DriverStatus driverStatus) {
    this.driverStatus = driverStatus;
    return this;
  }
}
