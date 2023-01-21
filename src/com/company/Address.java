package com.company;

public class Address {

    private String streetNumber;
    private String streetName;
    private String additionalAddressLine;
    private String zipCode;
    private String state;


    public Address(String streetNUmber, String streetName, String additionalAddressLine, String zipCode, String state) {
        this.streetNumber = streetNUmber;
        this.streetName = streetName;
        this.additionalAddressLine = additionalAddressLine;
        this.zipCode = zipCode;
        this.state = state;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getAdditionalAddressLine() {
        return additionalAddressLine;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }
}
