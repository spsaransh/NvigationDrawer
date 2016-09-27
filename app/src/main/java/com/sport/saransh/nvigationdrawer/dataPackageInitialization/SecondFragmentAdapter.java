package com.sport.saransh.nvigationdrawer.dataPackageInitialization;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sport.saransh.nvigationdrawer.PojoFragmeent2;
import com.sport.saransh.nvigationdrawer.R;
import com.sport.saransh.nvigationdrawer.controllers.VolleyCallBack.Controller;

import java.util.ArrayList;

/**
 * Created by SARANSH on 9/7/2016.
 */

public class SecondFragmentAdapter extends RecyclerView.Adapter<SecondFragmentAdapter.CustViewHolder>
{


    private Context mcontext;
    private ArrayList<PojoFragmeent2> list_weather ;
    public SecondFragmentAdapter(Context mcontex,ArrayList<PojoFragmeent2> list) {
        this.mcontext = mcontex;
        this.list_weather = list;
    }

    @Override
    public CustViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment2itemsrv,null);
        CustViewHolder viewHolder = new CustViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(CustViewHolder holder, int position)
    {
        PojoFragmeent2 pojoFragmeent2 = list_weather.get(position);
        holder.text_date1.setText(pojoFragmeent2.getDay());
        holder.temp1.setText(pojoFragmeent2.getTemp());
        String weathers = pojoFragmeent2.getWeather();
        Controller controller = new Controller(mcontext.getAssets());
        int weatherint =Integer.parseInt(pojoFragmeent2.getTemp());

        controller.SetIconTextView(holder.temp1,String.valueOf(weatherint),Controller.FontIcons.degreeicon);

       // holder.weatherimage1.setText(weathers.toString());
        if(position==0)
            controller.SetIconTextView(holder.weatherimage1,String.valueOf(weatherint-2),Controller.FontIcons.degreeicon);
        if(position==1)
            controller.SetIconTextView(holder.weatherimage1,String.valueOf(weatherint-4),Controller.FontIcons.degreeicon);
        if(position==2)
            controller.SetIconTextView(holder.weatherimage1,String.valueOf(weatherint-3),Controller.FontIcons.degreeicon);
        if(position==3)
            controller.SetIconTextView(holder.weatherimage1,String.valueOf(weatherint-2),Controller.FontIcons.degreeicon);
        if(position==4)
            controller.SetIconTextView(holder.weatherimage1,String.valueOf(weatherint-4),Controller.FontIcons.degreeicon);

        if(weathers =="clear sky" )
        {
            holder.background.setBackgroundResource(R.drawable.cleansky);
        }

        if(weathers=="scattered clouds" )
            holder.background.setBackgroundResource(R.drawable.cloudy);
        if(weathers.contains("rain"))
            holder.background.setBackgroundResource(R.drawable.rain);
        if(weathers.contains("broken clouds"))
            holder.background.setBackgroundResource(R.drawable.cloud1);
        if(weathers.contains("calm") || weathers.contains("breeze"))
            holder.background.setBackgroundResource(R.drawable.breeze);
    }






    @Override
    public int getItemCount() {
        return list_weather.size();
    }

    public class CustViewHolder extends RecyclerView.ViewHolder
    {
        public TextView text_date1;
        public TextView weatherimage1;
        public TextView temp1;
        public ImageView background;
        public CustViewHolder (View view)
        {
            super(view);
            text_date1 = (TextView)view.findViewById(R.id.day1);
            weatherimage1=(TextView)view.findViewById(R.id.Mintemplayot2);
            temp1=(TextView)view.findViewById(R.id.Maxtemplayot2);
            background=(ImageView)view.findViewById(R.id.wtcimageview);

        }

    }
}
