package com.sport.saransh.nvigationdrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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

import java.io.Serializable;
import java.util.ArrayList;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_weather.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_weather#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_weather extends Fragment implements Serializable{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private City city,city1;
    // TODO: Rename and change types of parameters
    private Double lat;
    private Double longitude;
    private boolean flag = false;
    private ArrayList<List> list1;
    private OnFragmentInteractionListener mListener;
    private String cityname;

    public fragment_weather() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_weather.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_weather newInstance(String param1, String param2) {
        fragment_weather fragment = new fragment_weather();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lat = getArguments().getDouble("latitude");
            longitude = getArguments().getDouble("longitude");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_chutiya, container, false);
        //Weatherfragment weatherfragment = new Weatherfragment();
        final ProgressDialog pDialog = new ProgressDialog(this.getActivity());

        // Showing progress dialog before making http request
        pDialog.setMessage("Loading Articles...");
        pDialog.show();
        list1 = new ArrayList<List>();

        fetchdata(
                new DataCallBack() {
                    @Override
                    public void onSuccess(JSONObject result)
                    {
                        flag= true;

                        pDialog.hide();
                        try{




                        JSONObject jsonresponse = result.getJSONObject("city");
                            city1 = new City();
                            cityname = jsonresponse.getString("name");
                        city1.setName(cityname);

                            JSONArray jsonarrayresponse = result.getJSONArray("list");


                            for (int i = 0; i < jsonarrayresponse.length(); i++)
                            {

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

                            }newactivity(cityname);

                } catch (JSONException ex) {
                    ex.getMessage();
                }
            }
        });


        return rootview;
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


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public void newactivity(String cityname)
    {
       Intent intent = new Intent(this.getActivity(),Weatherfragment.class);
        intent.putExtra("cityname",cityname);


        intent.putExtra("list1",list1);
        startActivity(intent);

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
