package com.sport.saransh.nvigationdrawer.datapackage;

import java.util.Date;

/**
 * Created by SARANSH on 6/22/2016.
 */

public class WeatherPojoList
{
    private Date date;
    private String temperature;
    private int img;

    public WeatherPojoList(Date date, String temperature, int img) {
        this.date = date;
        this.temperature = temperature;
        this.img = img;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
