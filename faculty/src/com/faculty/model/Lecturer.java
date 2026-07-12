package com.faculty.model;

public class Lecturer {

    private String fullName;
    private String department;
    private String coursesTeaching;
    private String email;
    private String mobileNumber;

    public Lecturer() {
    }

    public Lecturer(
            String fullName,
            String department,
            String coursesTeaching,
            String email,
            String mobileNumber
    ) {
        this.fullName = fullName;
        this.department = department;
        this.coursesTeaching = coursesTeaching;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCoursesTeaching() {
        return coursesTeaching;
    }

    public void setCoursesTeaching(String coursesTeaching) {
        this.coursesTeaching = coursesTeaching;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}