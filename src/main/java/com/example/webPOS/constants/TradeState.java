package com.example.webPOS.constants;

public enum TradeState {
    SALED,
    ORDER;
    // Optionally, add a method to get the string value for database storage
    public String getValue() {
        return this.name();
    }
    // Optionally, add a method to convert from a string (useful for reading from DB)
    public static TradeState fromValue(String value) {
        return valueOf(value);
    }
}