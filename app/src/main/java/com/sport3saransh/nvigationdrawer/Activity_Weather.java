package com.sport3saransh.nvigationdrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sport.saransh.nvigationdrawer.DataCallBack;
import com.sport.saransh.nvigationdrawer.R;
import com.sport.saransh.nvigationdrawer.SingletonJava;
import com.sport.saransh.nvigationdrawer.Weatherfragment;
import com.sport.saransh.nvigationdrawer.fragment_weather;
import com.sport.saransh.nvigationdrawer.pojo.City;
import com.sport.saransh.nvigationdrawer.pojo.Clouds;
import com.sport.saransh.nvigationdrawer.pojo.List;
import com.sport.saransh.nvigationdrawer.pojo.Main;
import com.sport.saransh.nvigationdrawer.pojo.Sys;
import com.sport.saransh.nvigationdrawer.pojo.Weather;
import com.sport.saransh.nvigationdrawer.pojo.Wind;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_Weather extends AppCompatActivity
{
    private Double lat;
    private Double longitude;
    private boolean flag = false;
    private ArrayList<List> list1;
    private fragment_weather.OnFragmentInteractionListener mListener;
    private String cityname;
    private City city,city1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__weather);
        Intent intent = getIntent();
        lat =intent.getDoubleExtra("latitude",12.971);
        longitude = intent.getDoubleExtra("longitude",77.59);





        final ProgressDialog pDialog = new ProgressDialog(this);

        // Showing progress dialog before making http request
        pDialog.setMessage("Loading Articles...");
        pDialog.show();
        list1 = new ArrayList<List>();

        fetchdata(
                new DataCallBack() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        flag = true;

                        pDialog.hide();
                        try {


                            JSONObject jsonresponse = result.getJSONObject("city");
                            city1 = new City();

                            cityname = jsonresponse.getString("name");
                            city1.setName(cityname);

                            JSONArray jsonarrayresponse = result.getJSONArray("list");


                            for (int i = 0; i < jsonarrayresponse.length(); i++) {

                                JSONObject listjsonobject = jsonarrayresponse.getJSONObject(i);


                                JSONArray jsonweather = listjsonobject.getJSONArray("weather");

                                //--------------main-------------
                                JSONObject Main_list = listjsonobject.getJSONObject("main");
                                String temp = Main_list.getString("temp");
                                String temp_min = Main_list.getString("temp_min");
                                String temp_max = Main_list.getString("temp_max");
                                String pressure = Main_list.getString("pressure");
                                String sea_level = Main_list.getString("sea_level");
                                String grnd_level = Main_list.getString("grnd_level");
                                String humidity = Main_list.getString("humidity");
                                String temp_kf = Main_list.getString("temp_kf");

                                final Main datamain = new Main();
                                datamain.setTemp(temp);
                                datamain.setTemp_max(temp_max);
                                datamain.setTemp_min(temp_min);
                                datamain.setHumidity(humidity);
                                datamain.setPressure(pressure);
                                datamain.setGrnd_level(grnd_level);
                                datamain.setSea_level(sea_level);
                                datamain.setTemp_kf(temp_kf);

                                //-----------dt_text-----------------
                                final String dt_text = listjsonobject.getString("dt_txt");
                                //------------dt--------------------
                                final String dt = listjsonobject.getString("dt");
                                //--------------sys--------------------------------------------

                                JSONObject sys_json = listjsonobject.getJSONObject("sys");
                                String sys = sys_json.getString("pod");
                                final Sys syse = new Sys();
                                syse.setPopulation(sys);
                                Weather[] weather = new Weather[jsonweather.length()];
                                for (int j = 0; j < jsonweather.length(); j++) {
                                    JSONObject weatherjsonobject = jsonweather.getJSONObject(j);
                                    final String Description = weatherjsonobject.getString("description");
                                    // citytemp.setText(Description);
                                    final String Id = weatherjsonobject.getString("id");
                                    final String Icon = weatherjsonobject.getString("icon");
                                    final String Main1 = weatherjsonobject.getString("main");
                                    //------------weather-------------------------------//

                                    Weather weatherl = new Weather(Id, Icon, Description, Main1);
                                    weather[j] = weatherl;
                                }
                                //------------clouds----------//

                                JSONObject jsonclouds = listjsonobject.getJSONObject("clouds");
                                String all = jsonclouds.getString("all");
                                final Clouds clouds = new Clouds();
                                clouds.setAll(all);
                                //---------wind----------------------//
                                JSONObject jsonwind = listjsonobject.getJSONObject("wind");
                                String wind_setspeed = jsonwind.getString("speed");
                                String wind_setdegree = jsonwind.getString("deg");
                                final Wind wind = new Wind();
                                wind.setSpeed(wind_setspeed);
                                wind.setDeg(wind_setdegree);
                                com.sport.saransh.nvigationdrawer.pojo.List listm = new com.sport.saransh.nvigationdrawer.pojo.List(clouds, dt, wind, weather, dt_text, datamain);
                                list1.add(listm);

                                //adapter.notifyDataSetChanged();

                            }
                            newactivity(cityname);

                        } catch (JSONException ex) {
                            ex.getMessage();
                        }
                    }
                });
    }


    public void fetchdata(final DataCallBack callBack) {
        String uri = String.format("http://api.openweathermap.org/data/2.5/forecast?lat=%1$s&lon=%2$s&&APPID=038e225c79b983ae5d756ae50d6491a6", lat, longitude);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, uri, (String) null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        try {

                            JSONObject jsonresponse = response.getJSONObject("city");
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.w("error in response", "Error: " + error.getMessage());
                    }
                });


        SingletonJava.getinstance().addToRequestQueue(jsObjRequest);
    }
    public void newactivity(String cityname)
    {
        Intent intent = new Intent(this,Weatherfragment.class);
        intent.putExtra("cityname",cityname);
        this.finish();
        intent.putExtra("lat",lat);
        intent.putExtra("long",longitude);

        intent.putExtra("list1",list1);
        startActivity(intent);
        /*Weatherfrg weatherfrg =new Weatherfrg();
        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cityname",cityname);
        bundle.putSerializable("list1",list1);
        weatherfrg.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.framelayout1,weatherfrg).commit();*/


    }
    }

