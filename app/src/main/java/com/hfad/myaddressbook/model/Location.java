package com.hfad.myaddressbook.model;

public class Location {
    private Street street;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private Coordinates coordinate;
    private Timezone timezone;

    public Location(Street street, String city, String state,
                    String country, String postcode,
                    Coordinates coordinate, Timezone timezone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
        this.coordinate = coordinate;
        this.timezone = timezone;
    }

    public Street getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPostcode() {
        return postcode;
    }

    public Coordinates getCoordinate() {
        return coordinate;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setCoordinate(Coordinates coordinate) {
        this.coordinate = coordinate;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }
}
