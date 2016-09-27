package com.sport.saransh.nvigationdrawer;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.sport.saransh.nvigationdrawer.fragments.SecondFragment;
import com.sport.saransh.nvigationdrawer.pojo.City;
import com.sport.saransh.nvigationdrawer.pojo.List;
import com.sport.saransh.nvigationdrawer.pojo.Weather;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Weatherfragment extends AppCompatActivity implements Serializable {

    public VerticalViewPager mPager;
    MyPagerAdapter adapterViewPager;
    private static  Double tempcelsius,tempcelsius1;
    private String time_tk;
    private static ArrayList<List> list1;
    private static City city1;
    ProgressDialog progressDialog;
    public static String lowtemp;
    public static String cityname1,humidity;
    private static double latitude;
    private static double longitude;
    private static  String clouddesc,clouddesc1,speed,degree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Intent intent = getIntent();
        cityname1 = intent.getStringExtra("cityname");
        latitude = intent.getDoubleExtra("lat",12.9716);
        longitude=intent.getDoubleExtra("long",77.59);

        list1 = (ArrayList<List>)intent.getSerializableExtra("list1");
        Iterator iter = list1.iterator();
      for(int i=0;i<list1.size();i++)
      {
          List list2 = list1.get(i);
          if(i == 0)
          {

              Weather[] cloud = list2.getWeather();

              for(int j=0;j<cloud.length;j++)
               clouddesc=cloud[j].getDescription();

              String temp= list2.getMain().getTemp();
              Scanner sc = new Scanner(temp);
               tempcelsius=sc.nextDouble()-273.15;
              tempcelsius= Math.floor(tempcelsius*10)/10;
              humidity = list2.getMain().getHumidity();


          }
          if(i == 1)
          {

              Weather[] cloud = list2.getWeather();
              humidity = list2.getMain().getHumidity();

              for(int j=0;j<cloud.length;j++)
                  clouddesc1=cloud[j].getDescription();

              String temp= list2.getMain().getTemp();
              Scanner sc1 = new Scanner(temp);
              tempcelsius1=sc1.nextDouble()-273.15;
              tempcelsius1= Math.floor(tempcelsius*10)/10;
              humidity = list2.getMain().getHumidity();
              speed=list2.getWind().getSpeed();
              degree =list2.getWind().getDeg();


          }
          String dt_txt = list2.getDt_txt();
         String[] strings = dt_txt.split(" ");
          for(int j=0;j<strings.length;j++)
          {
            time_tk = strings[1];
          }
          if(time_tk.equals("00:00:00"))
              lowtemp =  list2.getMain().getTemp_min();








      }
        mPager = (VerticalViewPager) findViewById(R.id.viewPager);
       adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapterViewPager);


    }

   /* @Override
    public void onBackPressed()
    {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new FragmentChutiya();
        fragmentManager.popBackStack();

    }*/

       public static class MyPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    Bundle bundle = new Bundle();
                    bundle.putString("cityname",cityname1);
                    bundle.putString("cloud",clouddesc);
                    bundle.putDouble("temp",tempcelsius);
                    bundle.putString("lowtemp",lowtemp);

                    FirstFragment fragmenty = new FirstFragment();
                    fragmenty.setArguments(bundle);

                    return fragmenty;
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("list",list1);
                    bundle1.putDouble("lat",latitude);
                    bundle1.putDouble("long",longitude);

                    SecondFragment fragmentx = new SecondFragment();
                    fragmentx.setArguments(bundle1);
                    return fragmentx;


                case 2: // Fragment # 1 - This will show SecondFragment
                    LastFragment fragmentz=new LastFragment();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("humidity",humidity);
                    bundle2.putDouble("temperature",tempcelsius1);
                    bundle2.putString("degree",degree);
                    bundle2.putString("speed",speed);
                    bundle2.putString("lowtemp",lowtemp);
                    fragmentz.setArguments(bundle2);
                    return fragmentz;
                default:
                    return null;
            }
        }

    }




}


