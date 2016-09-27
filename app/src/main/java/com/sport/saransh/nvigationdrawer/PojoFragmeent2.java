package com.sport.saransh.nvigationdrawer;

import com.sport.saransh.nvigationdrawer.pojo.Weather;

/**
 * Created by SARANSH on 9/7/2016.
 */

public class PojoFragmeent2
{
    private String day;
    private String temp;
    private String weather;


    public PojoFragmeent2(String day, String temp, String weather) {
        this.day = day;
        this.temp = temp;
        this.weather = weather;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
