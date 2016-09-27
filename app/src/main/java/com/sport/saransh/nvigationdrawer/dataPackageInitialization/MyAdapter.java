package com.sport.saransh.nvigationdrawer.dataPackageInitialization;

/**
 * Created by SARANSH on 6/25/2016.
 */
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sport.saransh.nvigationdrawer.R;
import com.sport.saransh.nvigationdrawer.controllers.VolleyCallBack.Controller;
import com.sport.saransh.nvigationdrawer.pojo.List;
import com.sport.saransh.nvigationdrawer.pojo.Main;
import com.sport.saransh.nvigationdrawer.pojo.Weather;

import java.util.ArrayList;


/**
 * Created by SARANSH on 6/5/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustViewHolder> {

    private Context mcontext;
    String descripition;
    private ArrayList<List> list_weather ;
    String timeformat,timeformat1,timeformat2;
    java.sql.Time ppstime;
    String clouddesc;

    public MyAdapter(Context mcontex,ArrayList<List> list) {
        this.mcontext = mcontex;
        this.list_weather = list;
    }

    @Override
    public MyAdapter.CustViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weatherresource,null);
        CustViewHolder custViewHolder = new CustViewHolder(view);
        return custViewHolder;

    }

    @Override
    public void onBindViewHolder(MyAdapter.CustViewHolder holder, int position) {
        com.sport.saransh.nvigationdrawer.pojo.List list = list_weather.get(position);
        Main mains = list.getMain();
        Weather[] weather = list.getWeather();
        String txt= list.getDt_txt();
        String[] datetime1= list.getDt_txt().split("\\s+");

        for(int i =0; i<datetime1.length;i++)
        {
            timeformat=datetime1[1];
            if(datetime1[1].contains(":"))
            timeformat1 =datetime1[1].substring(0,2);
            timeformat2 =datetime1[1].substring(0,5);


        }


        holder.text_date.setText(timeformat2);
        double d = Double.parseDouble(timeformat1);

        for(int i =0;i<weather.length;i++)
        {
            clouddesc= weather[i].getDescription();
            if(clouddesc.contains("clear sky") && d>5.00&&d<19.00)
            {
               holder.weatherimage.setBackgroundResource(R.drawable.dayclear);
            }
            if(clouddesc.contains("clear sky") && d>19.00 ||d<5.00)
            {
                holder.weatherimage.setBackgroundResource(R.drawable.moon);
            }
            if(clouddesc.contains("scattered clouds" ))
                holder.weatherimage.setBackgroundResource(R.drawable.cloudy);
            if(clouddesc.contains("rain"))
                holder.weatherimage.setBackgroundResource(R.drawable.rain);
            if(clouddesc.contains("broken clouds"))
                holder.weatherimage.setBackgroundResource(R.drawable.cloud1);
            if(clouddesc.contains("calm") || clouddesc.contains("breeze"))
                holder.weatherimage.setBackgroundResource(R.drawable.breeze);
        }
        String temp_kf= mains.getTemp();
        double temperature_kf =Double.parseDouble(temp_kf);
        double temp_celsius= temperature_kf -273.15;
        int temp_cels= (int)temp_celsius;
        Controller controller = new Controller(mcontext.getAssets());
        controller.SetIconTextView(holder.temp,String.valueOf(temp_cels),Controller.FontIcons.degreeicon);
        //FonteAwesome ft =new FonteAwesome();
        //holder.weatherimage.setBackgroundResource(R.drawable.weather);
        //holder.temp.setText(String.valueOf(temp_cels));
        holder.text_date.setTextColor(Color.WHITE);

    }

    @Override
    public int getItemCount() {
        return list_weather.size();
    }

    public class CustViewHolder extends RecyclerView.ViewHolder {
        public TextView text_date;
        public TextView weatherimage;
        public TextView temp;

        public CustViewHolder(View itemView)
        {
            super(itemView);
            text_date = (TextView)itemView.findViewById(R.id.wftxt1);
            weatherimage=(TextView)itemView.findViewById(R.id.imgweather1);
            temp=(TextView)itemView.findViewById(R.id.wftxt2);





        }
    }
}

