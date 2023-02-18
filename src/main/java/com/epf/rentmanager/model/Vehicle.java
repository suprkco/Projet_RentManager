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

    public Vehicle(String manufacturer, String model, int nb_places) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.nb_places = nb_places;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", nb_places=" + nb_places
                + "]";
    }
}
