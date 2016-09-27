package com.sport.saransh.nvigationdrawer.pojo;

import java.io.Serializable;

/**
 * Created by SARANSH on 6/22/2016.
 */

public class DataInitailization implements Serializable
{
    private String temp_min;
    private String  temp_max;
    private String humidity;
    private String clouds;

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }
}

