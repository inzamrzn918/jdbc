package com.company;

public class StudentModel {
    private String name,address;
    private int id,roll_no;
    private long contact;

    public StudentModel(int id, String name, String address, int roll_no, long contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.roll_no = roll_no;
        this.contact = contact;

    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public int getRoll_no() {
        return roll_no;
    }
    public long getContact() {
        return contact;
    }

}
