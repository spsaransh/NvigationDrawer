package com.sport.saransh.nvigationdrawer.controllers.VolleyCallBack;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by SARANSH on 9/9/2016.
 */

public class NetworkChek
{
    private Context mcontext;

    public NetworkChek(Context mcontext)
    {
        this.mcontext =mcontext;


    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mcontext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null ;
    }
}
