package com.example.geektrust.services;

public interface AppInterface {
  void addDriver(String driverId, double xCoordinate, double yCoordinate);
  void addRider(String riderId, double xCoordinate, double yCoordinate);
  void matchRide(String riderId);
  void startRide(String rideId, int matchedDriverIndex, String riderId);
  void stopRide(String rideId, double xCoordinate, double yCoordinate, double timeTakenInMinutes);
  void getBill(String rideId);
  void takeInput(String input);

}
