package com.epf.rentmanager.model;

// + INT id PRIMARY KEY
//        AUTOINCREMENT
//        + VARCHAR manufacturer
//        + VARCHAR model
//        + TINYINT nb_places

public class Vehicle {
    private int id;
    private String manufacturer, model;
    private int nb_places;

    public Vehicle() {
    }

    public Vehicle(int id, String manufacturer, String model, int nb_places) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.nb_places = nb_places;
    }

    public Vehicle(int id, String manufacturer, int nb_places) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.nb_places = nb_places;
    }

    public Vehicle(String manufacturer, String model, int nb_places) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.nb_places = nb_places;
    }

    // getters and setters id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // getters and setters manufacturer
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    // getters and setters model
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    // getters and setters nb_places
    public int getNb_places() {
        return nb_places;
    }
    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    public String getVehicle_info() {
        return manufacturer + " " + model;
    }

    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", nb_places=" + nb_places
                + "]";
    }
}
