package com.faculty.controller.faculty;

public class Lecturer {

    private int id;
    private String fullName;
    private Integer departmentId;
    private String departmentName;
    private String coursesTeaching;
    private String email;
    private String mobileNumber;

    public Lecturer(int id, String fullName, Integer departmentId, String email, String mobileNumber) {
        this.id = id;
        this.fullName = fullName;
        this.departmentId = departmentId;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    @Override
    public String toString() {
        return fullName;
    }
}
