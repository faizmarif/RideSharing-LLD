package com.example.geektrust.services;

import com.example.geektrust.models.Location;
import com.example.geektrust.models.Ride;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RideMgmtService {
  public double getDistance(Location l1, Location l2) {
    double a = Math.pow(l1.getXCoordinate() - l2.getXCoordinate(), 2);
    double b = Math.pow(l1.getYCoordinate() - l2.getYCoordinate(), 2);
    return Math.sqrt(a+b);
  }

  public void sortMatchedRide(List<Object[]> driverIdDistancePairs) {
    Collections.sort(driverIdDistancePairs, (a,b) -> {
      if((double)a[1] == (double)b[1]) {
        return ((String)a[0]).compareTo((String) b[0]);
      }
      else {
        return (int) (((double)a[1]) - ((double)b[1]));
      }
    });
  }

  public double calculateBill(Location riderLocation, double xCoordinate, double yCoordinate, double timeTakenInMinutes) {
    double bill = 50;
    double distance = getDistance(riderLocation, new Location(xCoordinate, yCoordinate));
    bill += distance*6.5;
    bill += timeTakenInMinutes*2;
    bill += (bill*20)/100;
    return bill;
  }

}
