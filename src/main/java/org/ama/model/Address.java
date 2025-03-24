package org.ama.model;

public class Address {
    private String city;
    private String firstLine;
    private String postalCode;

    public Address(String city, String firstLine, String postalCode) {
        this.city = city;
        this.firstLine = firstLine;
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
