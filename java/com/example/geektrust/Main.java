package com.example.geektrust;

import com.example.geektrust.services.RideSharingImpl;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RideSharingImpl rideSharing = new RideSharingImpl();
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream("/Users/fmustafa/Dummy/Ride Sharing/java-maven-starter-kit/sample_input/input2.txt");
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               rideSharing.takeInput(sc.nextLine());
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
