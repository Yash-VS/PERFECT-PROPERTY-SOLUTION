package com.example.ppt_application;

public class Lands_class {
    String name;
    String location;
    String consultant;
    String area;
    String rent;

    public Lands_class(){

    }

    public Lands_class(String name, String location, String consultant, String area , String rent){
        this.name = name;
        this.location = location;
        this.consultant = consultant;
        this.rent = rent;
        this.area = area;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }
}
