package com.sport.saransh.nvigationdrawer.pojo;

import java.io.Serializable;

/**
 * Created by SARANSH on 6/22/2016.
 */

public class List implements Serializable
{
    private Clouds clouds;

    private String dt;

    private Wind wind;

    //private Sys sys;

    public Weather[] weather;

    private String dt_txt;

    private Main main;

    public List(Clouds clouds, String dt, Wind wind, Weather[] weather, String dt_txt, Main main) {
        this.clouds = clouds;
        this.dt = dt;
        this.wind = wind;
        this.weather = weather;
        this.dt_txt = dt_txt;
        this.main = main;
    }

    public Clouds getClouds ()
    {
        return clouds;
    }

    public void setClouds (Clouds clouds)
    {
        this.clouds = clouds;
    }

    public String getDt ()
    {
        return dt;
    }

    public void setDt (String dt)
    {
        this.dt = dt;
    }

    public Wind getWind ()
    {
        return wind;
    }

    public void setWind (Wind wind)
    {
        this.wind = wind;
    }

    // public Sys getSys ()
    // {
    //     return sys;
//   }
/*    public void setSys (Sys sys)
    {
        this.sys = sys;
    }*/

    public Weather[] getWeather ()
    {
        return weather;
    }

    public void setWeather (Weather[] weather)
    {
        this.weather = weather;
    }

    public String getDt_txt ()
    {
        return dt_txt;
    }

    public void setDt_txt (String dt_txt)
    {
        this.dt_txt = dt_txt;
    }

    public Main getMain ()
    {
        return main;
    }

    public void setMain (Main main)
    {
        this.main = main;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [clouds = "+clouds+", dt = "+dt+", wind = "+wind+",  weather = "+weather+", dt_txt = "+dt_txt+", main = "+main+"]";
    }
}
