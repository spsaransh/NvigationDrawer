package com.sport.saransh.nvigationdrawer.pojo;

import java.io.Serializable;

/**
 * Created by SARANSH on 6/22/2016.
 */

public class Wind implements Serializable
{
    private String speed;

    private String deg;

    public String getSpeed ()
    {
        return speed;
    }

    public void setSpeed (String speed)
    {
        this.speed = speed;
    }

    public String getDeg ()
    {
        return deg;
    }

    public void setDeg (String deg)
    {
        this.deg = deg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [speed = "+speed+", deg = "+deg+"]";
    }
}
