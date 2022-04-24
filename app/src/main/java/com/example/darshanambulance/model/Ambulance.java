package com.example.darshanambulance.model;

public class Ambulance {
    public String ambulance_name;
    public Double lat,lng;

    public Ambulance(String ambulance_name, Double lat, Double lng){
        this.ambulance_name = ambulance_name;
        this.lat = lat;
        this.lng = lng;
    }


    public String getAmbulance_name() {
        return ambulance_name;
    }

    public void setAmbulance_name(String ambulance_name) {
        this.ambulance_name = ambulance_name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
