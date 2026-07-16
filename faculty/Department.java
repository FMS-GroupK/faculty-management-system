package com.faculty.controller.faculty;

public class Department {

    private int id;
    private String name;
    private String hodName;
    private int staffCount;

    public Department(int id, String name, String hodName) {
        this.id = id;
        this.name = name;
        this.hodName = hodName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHodName() {
        return hodName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    public int getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(int staffCount) {
        this.staffCount = staffCount;
    }

    @Override
    public String toString() {
        return name;
    }
}
