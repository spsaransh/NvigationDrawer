package com.sport.saransh.nvigationdrawer.datapackage;

/**
 * Created by SARANSH on 3/12/2016.
 */
public class PojoNavigationDrwaer {
    private String mitem;
    private int imgres;

    public PojoNavigationDrwaer(String mitem, int imgres) {
        this.mitem = mitem;
        this.imgres = imgres;
    }

    public String getMitem() {
        return mitem;
    }

    public void setMitem(String mitem) {
        this.mitem = mitem;
    }

    public int getImgres() {
        return imgres;
    }

    public void setImgres(int imgres) {
        this.imgres = imgres;
    }
}
