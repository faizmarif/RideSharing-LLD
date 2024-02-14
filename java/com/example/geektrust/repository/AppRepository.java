package com.example.geektrust.repository;

import com.example.geektrust.models.Driver;
import com.example.geektrust.models.Ride;
import com.example.geektrust.models.Rider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppRepository {
  private Map<String, Driver> driverIdMap;
  private Map<String, Rider> riderIdMap;
  private Map<String, Ride> rideIdMap;
  private Map<String, List<String>> riderDriversMatched;
  private static AppRepository appRepository;

  private AppRepository() {
    driverIdMap = new HashMap<>();
    riderIdMap = new HashMap<>();
    rideIdMap = new HashMap<>();
    riderDriversMatched = new HashMap<>();
  }

  public static AppRepository getInstance() {
    if(appRepository == null) {
      appRepository = new AppRepository();
    }
    return appRepository;
  }

  public Map<String, Driver> getDriverIdMap() {
    return driverIdMap;
  }

  public Map<String, Rider> getRiderIdMap() {
    return riderIdMap;
  }

  public Map<String, Ride> getRideIdMap() {
    return rideIdMap;
  }

  public Map<String, List<String>> getRiderDriversMatched() {
    return riderDriversMatched;
  }

  public AppRepository addDriver(
      Driver driver) {
    this.driverIdMap.put(driver.getDriverId(), driver);
    return this;
  }

  public AppRepository addRider(
      Rider rider) {
    this.riderIdMap.put(rider.getRiderId(), rider);
    return this;
  }

  public AppRepository addRide(
      Ride ride) {
    this.rideIdMap.put(ride.getRideId(), ride);
    return this;
  }

  public AppRepository addMatchedRide(
      String riderId, String driverId) {
    if(!riderDriversMatched.containsKey(riderId)) {
      riderDriversMatched.put(riderId, new ArrayList<>());
    }
    riderDriversMatched.get(riderId).add(driverId);
    return this;
  }

}
