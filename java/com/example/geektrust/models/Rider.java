package com.example.geektrust.models;

import com.example.geektrust.enums.RiderStatus;
import java.util.ArrayList;
import java.util.List;

public class Rider {
  private final String riderId;
  private Location location;
  private List<Ride> ridesHistory;
  private RiderStatus riderStatus;

  public Rider(String riderId) {
    this.riderId = riderId;
    this.ridesHistory = new ArrayList<>();
    riderStatus = RiderStatus.NOT_RIDING;
  }

  public String getRiderId() {
    return riderId;
  }

  public Location getLocation() {
    return location;
  }

  public List<Ride> getRidesHistory() {
    return ridesHistory;
  }

  public RiderStatus getRiderStatus() {
    return riderStatus;
  }

  public Rider setLocation(Location location) {
    this.location = location;
    return this;
  }

  public Rider addRideInHistory(Ride ride) {
    this.ridesHistory.add(ride);
    return this;
  }

  public Rider setRiderStatus(RiderStatus riderStatus) {
    this.riderStatus = riderStatus;
    return this;
  }
}
