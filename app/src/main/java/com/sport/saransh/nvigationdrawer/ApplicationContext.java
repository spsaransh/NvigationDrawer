package com.sport.saransh.nvigationdrawer;

/**
 * Created by SARANSH on 6/22/2016.
 */

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;





public class ApplicationContext extends Application {

    private static ApplicationContext sInstance;

    @Override

    public  void onCreate()
    {
        super.onCreate();
        sInstance =this;

    }

   /* @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }*/
    public static Context getAppContext()
    {
        return  sInstance.getApplicationContext();
    }

}