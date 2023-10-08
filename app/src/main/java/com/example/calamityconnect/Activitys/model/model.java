package com.example.calamityconnect.Activitys.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class model {
    @SerializedName("name")
    @Expose
    private String title;

    @SerializedName("desig")
    @Expose
    private String des;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("valid")
    @Expose
    private String valid;

    public model(String title, String des, String image, String valid) {
        this.title = title;
        this.des = des;
        this.image = image;
        this.valid = valid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
