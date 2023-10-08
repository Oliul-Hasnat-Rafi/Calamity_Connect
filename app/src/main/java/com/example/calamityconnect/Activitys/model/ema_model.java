package com.example.calamityconnect.Activitys.model;

public class ema_model {
    private String ema_name,ema_number;

    public ema_model(String ema_name, String ema_number) {
        this.ema_name = ema_name;
        this.ema_number = ema_number;
    }

    public String getEma_name() {
        return ema_name;
    }

    public void setEma_name(String ema_name) {
        this.ema_name = ema_name;
    }

    public String getEma_number() {
        return ema_number;
    }

    public void setEma_number(String ema_number) {
        this.ema_number = ema_number;
    }
}
