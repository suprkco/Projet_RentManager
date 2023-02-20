package com.epf.rentmanager.main;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

public class Main {
    public static void main(String[] args) {

        // Displays all clients
        try {
            System.out.println(ClientService.getInstance().findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Display all vehicles
        try {
            System.out.println(VehicleService.getInstance().findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
