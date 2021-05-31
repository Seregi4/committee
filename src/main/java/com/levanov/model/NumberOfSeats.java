package com.levanov.model;

public class NumberOfSeats {
    private int id;
    private int specialtyID;
    private int count;

    public NumberOfSeats(int id, int specialtyID, int count) {
        this.id = id;
        this.specialtyID = specialtyID;
        this.count = count;
    }

    public NumberOfSeats() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpecialtyID() {
        return specialtyID;
    }

    public void setSpecialtyID(int specialtyID) {
        this.specialtyID = specialtyID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
