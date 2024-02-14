package com.example.geektrust.services;

import com.example.geektrust.enums.DriverStatus;
import com.example.geektrust.enums.RideStatus;
import com.example.geektrust.enums.RiderStatus;
import com.example.geektrust.models.Driver;
import com.example.geektrust.models.Location;
import com.example.geektrust.models.Ride;
import com.example.geektrust.models.Rider;
import com.example.geektrust.repository.AppRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RideSharingImpl implements AppInterface {
  private AppRepository repository = AppRepository.getInstance();
  private RideMgmtService services = new RideMgmtService();

  @Override
  public void addDriver(String driverId, double xCoordinate, double yCoordinate) {
    Driver driver = new Driver(driverId)
        .setDriverStatus(DriverStatus.NOT_DRIVING)
        .setLocation(new Location(xCoordinate, yCoordinate));
    repository.addDriver(driver);
  }

  @Override
  public void addRider(String riderId, double xCoordinate, double yCoordinate) {
    Rider rider = new Rider(riderId)
        .setRiderStatus(RiderStatus.NOT_RIDING)
        .setLocation(new Location(xCoordinate, yCoordinate));
    repository.addRider(rider);
  }

  @Override
  public void matchRide(String riderId) {
    Rider rider = repository.getRiderIdMap().get(riderId);
    Map<String, Driver> driveIdMap = repository.getDriverIdMap();
    List<Object[]> driverIdDistancePairs = new ArrayList<>();
    for(Driver driver : driveIdMap.values()) {
      double distance = services.getDistance(rider.getLocation(), driver.getLocation());
      if(distance <= 5 && driver.getDriverStatus() == DriverStatus.NOT_DRIVING) {
        driverIdDistancePairs.add(new Object[]{driver.getDriverId(), distance});
        repository.addMatchedRide(riderId, driver.getDriverId());
      }
    }
    services.sortMatchedRide(driverIdDistancePairs);
    StringBuilder toReturn = new StringBuilder();
    for(int i=0;i<driverIdDistancePairs.size();i++) {
      toReturn.append(driverIdDistancePairs.get(i)[0]).append(" ");
    }
    if(toReturn.toString().equals("")) {
      System.out.println("NO_DRIVERS_AVAILABLE");
      return;
    }
    System.out.println("DRIVERS_MATCHED " + toReturn);
  }

  @Override
  public void startRide(String rideId, int matchedDriverIndex, String riderId) {
    if(repository.getRideIdMap().containsKey(rideId)) {
      System.out.println("INVALID_RIDE");
      return;
    }
    if(repository.getRiderDriversMatched().get(riderId).size() < matchedDriverIndex) {
      System.out.println("INVALID_RIDE");
      return;
    }
    Rider rider = repository.getRiderIdMap().get(riderId);
    String driverId = repository.getRiderDriversMatched().get(riderId).get(matchedDriverIndex-1);
    Driver driver = repository.getDriverIdMap().get(driverId);
    Ride ride = new Ride(rideId)
        .setRideStatus(RideStatus.STARTED)
        .setRider(rider)
        .setDriver(driver);
    rider.setRiderStatus(RiderStatus.RIDING);
    driver.setDriverStatus(DriverStatus.DRIVING);
    repository.addRide(ride);
    System.out.println("RIDE_STARTED " + rideId);
  }

  @Override
  public void stopRide(String rideId, double xCoordinate, double yCoordinate,
      double timeTakenInMinutes) {
    if(!repository.getRideIdMap().containsKey(rideId)) {
      System.out.println("INVALID_RIDE");
      return;
    }
    Ride ride = repository.getRideIdMap().get(rideId);
    if(ride.getRideStatus() == RideStatus.STOPPED) {
      System.out.println("INVALID_RIDE");
      return;
    }
    ride.setBill(services.calculateBill(ride.getRider().getLocation(), xCoordinate, yCoordinate, timeTakenInMinutes));
    ride.getRider().setRiderStatus(RiderStatus.NOT_RIDING).addRideInHistory(ride);
    ride.getDriver().setDriverStatus(DriverStatus.NOT_DRIVING).addRideInHistory(ride);
    ride.setRideStatus(RideStatus.STOPPED);
    System.out.println("RIDE_STOPPED " + rideId);
  }

  @Override
  public void getBill(String rideId) {
    Ride ride = repository.getRideIdMap().get(rideId);
    System.out.print(rideId + " " + ride.getDriver().getDriverId() + " ");
    System.out.format("%.2f", ride.getBill());
    System.out.println();
  }

  @Override
  public void takeInput(String input) {
    String[] args = input.split(" ");
    String command = args[0];
    if(command.equals("ADD_DRIVER")) {
      addDriver(args[1], Double.parseDouble(args[2]), Double.parseDouble(args[3]));
    } else if(command.equals("ADD_RIDER")) {
      addRider(args[1], Double.parseDouble(args[2]), Double.parseDouble(args[3]));
    }  else if(command.equals("MATCH")) {
      matchRide(args[1]);
    } else if(command.equals("START_RIDE")) {
      startRide(args[1], Integer.parseInt(args[2]), args[3]);
    } else if(command.equals("STOP_RIDE")) {
      stopRide(args[1], Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]));
    } else if(command.equals("BILL")) {
      getBill(args[1]);
    }
  }
}
