package com.example.calamityconnect.Activitys.model;

public class volunteenmodel {

    String name,phone,email,occupation,district,NID;

    public volunteenmodel(String name, String phone, String email, String occupation, String district, String NID) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.occupation = occupation;
        this.district = district;
        this.NID = NID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }
}
