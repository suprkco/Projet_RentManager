package com.epf.rentmanager.main;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

public class Main {
    public static void main(String[] args) {

        // Displays all clients
        try {
            System.out.println(ClientService.getInstance().findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
