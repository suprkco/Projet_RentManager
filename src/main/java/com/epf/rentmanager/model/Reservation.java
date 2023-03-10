package com.epf.rentmanager.model;

// + INT id PRIMARY KEY
//        AUTOINCREMENT
//        + INT client_id
//        + INT vehicle_id
//        + DATETIME startTime
//        + DATETIME endTime

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int client_id, vehicle_id;
    private String startTime, endTime;
    private String client_info, vehicle_info;

    public Reservation() {
    }

    public Reservation(int id, int client_id, int vehicle_id, LocalDate startTime, LocalDate endTime) {
        this.id = id;
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.startTime = startTime.toString();
        this.endTime = endTime.toString();
    }

    public Reservation(int client_id, int vehicle_id, LocalDate startTime, LocalDate endTime) {
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.startTime = startTime.toString();
        this.endTime = endTime.toString();
    }

    public Reservation(int id, int clientId, int vehicleId, LocalDate startTime, LocalDate endTime, String client_info, String vehicle_info) {
        this.id = id;
        this.client_id = clientId;
        this.vehicle_id = vehicleId;
        this.startTime = startTime.toString();
        this.endTime = endTime.toString();
        this.client_info = client_info;
        this.vehicle_info = vehicle_info;
    }

    // getters and setters id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // getters and setters client_id
    public int getClient_id() {
        return client_id;
    }
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    // getters and setters vehicle_id
    public int getVehicle_id() {
        return vehicle_id;
    }
    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    // getters and setters startTime
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    // getters and setters endTime
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    // getters and setters client_info/vehicle_info
    public String getClient_info() { return client_info; }
    public void setClient_info(String client_info) { this.client_info = client_info; }
    public String getVehicle_info() { return vehicle_info; }
    public void setVehicle_info(String vehicle_info) { this.vehicle_info = vehicle_info; }


    @Override
    public String toString() {
        return "Reservation [id=" + id + ", client_id=" + client_id + ", vehicle_id=" + vehicle_id + ", startTime="
                + startTime + ", endTime=" + endTime + "]";
    }
}
