package com.epf.rentmanager.model;

// + INT id PRIMARY KEY
//        AUTOINCREMENT
//        + INT client_id
//        + INT vehicle_id
//        + DATETIME startTime
//        + DATETIME endTime

public class Reservation {
    private int id;
    private int client_id, vehicle_id;
    private String startTime, endTime;

    public Reservation() {
    }

    public Reservation(int id, int client_id, int vehicle_id, String startTime, String endTime) {
        this.id = id;
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Reservation(int client_id, int vehicle_id, String startTime, String endTime) {
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", client_id=" + client_id + ", vehicle_id=" + vehicle_id + ", startTime="
                + startTime + ", endTime=" + endTime + "]";
    }
}
