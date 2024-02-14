package com.example.geektrust.models;

import com.example.geektrust.enums.RideStatus;

public class Ride {
  private final String rideId;
  private RideStatus rideStatus;
  private double bill;
  private Driver driver;
  private Rider rider;

  public Ride(String rideId) {
    this.rideId = rideId;
  }

  public String getRideId() {
    return rideId;
  }

  public RideStatus getRideStatus() {
    return rideStatus;
  }

  public double getBill() {
    return bill;
  }

  public Driver getDriver() {
    return driver;
  }

  public Rider getRider() {
    return rider;
  }

  public Ride setRideStatus(RideStatus rideStatus) {
    this.rideStatus = rideStatus;
    return this;
  }

  public Ride setBill(double bill) {
    this.bill = bill;
    return this;
  }

  public Ride setDriver(Driver driver) {
    this.driver = driver;
    return this;
  }

  public Ride setRider(Rider rider) {
    this.rider = rider;
    return this;
  }
}
