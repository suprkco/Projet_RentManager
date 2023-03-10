package com.epf.rentmanager.main;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

public class Main {
    public static void main(String[] args) {

        // Displays all clients
        System.out.println("\nDisplaying clients...");
        try {
            // store ClientService.getInstance().findAll() in a variable
            java.util.List<Client> clients = ClientService.getInstance().findAll();
            System.out.println(clients);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Display the client with id 1
        try {
            // store ClientService.getInstance().findById(1) in a variable
            Client client = ClientService.getInstance().findById(1);
            System.out.println(client);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Display all vehicles
        System.out.println("\nDisplaying vehicles...");
        try {
            // store VehicleService.getInstance().findAll() in a variable
            java.util.List<Vehicle> vehicles = VehicleService.getInstance().findAll();
            System.out.println(vehicles);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Display the vehicle with id 1
        try {
            // store VehicleService.getInstance().findById(1) in a variable
            Vehicle vehicle = VehicleService.getInstance().findById(1);
            System.out.println(vehicle);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Display all reservations
        System.out.println("\nDisplaying reservations...");
        try {
            // store ReservationService.getInstance().findAll() in a variable
            java.util.List<Reservation> reservations = ReservationService.getInstance().findAll();
            System.out.println(reservations);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Display the reservation with id 1
        try {
            // store ReservationService.getInstance().findById(1) in a variable
            Reservation reservation = ReservationService.getInstance().findById(1);
            System.out.println(reservation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
