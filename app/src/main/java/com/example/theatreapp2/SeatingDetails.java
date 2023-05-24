package com.example.theatreapp2;

public class SeatingDetails {

    String name;
    String seatType;
    int seatPrice;
    String wheelchairAccess;
    int seatNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(int seatPrice) {
        this.seatPrice = seatPrice;
    }

    public String getWheelchairAccess() {
        return wheelchairAccess;
    }

    public void setWheelchairAccess(String wheelchairAccess) {
        this.wheelchairAccess = wheelchairAccess;
    }


    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}