package com.example.guzmann_.models;

public class students {
    private String imageProf;
    private String name;
    private int age;
    private int rollNo;
    private String course;

    public students(String imageProf, String name, int age, int rollNo, String course) {
        this.name = name;
        this.age = age;
        this.rollNo = rollNo;
        this.course = course;
        this.imageProf = imageProf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getImageProf() {
        return imageProf;
    }

    public void setImageProf(String imageProf) {
        this.imageProf = imageProf;
    }
}
