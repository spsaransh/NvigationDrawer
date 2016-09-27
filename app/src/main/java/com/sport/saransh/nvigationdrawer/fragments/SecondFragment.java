package com.sport.saransh.nvigationdrawer.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sport.saransh.nvigationdrawer.PojoFragmeent2;
import com.sport.saransh.nvigationdrawer.R;
import com.sport.saransh.nvigationdrawer.SingletonJava;
import com.sport.saransh.nvigationdrawer.dataPackageInitialization.MyAdapter;
import com.sport.saransh.nvigationdrawer.dataPackageInitialization.SecondFragmentAdapter;
import com.sport.saransh.nvigationdrawer.pojo.Weather;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SecondFragment extends Fragment implements Serializable
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private SupportMapFragment mSupportMapFragment;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;
    private final static boolean forceNetwork = false;
    private String mProviderName;
    private String datetext2,goal;
    private  double mintemp,latitude,longitude;
    private Weather[] weathercond;
    private  int maxtemp ;
    static final LatLng bangalore = new LatLng(21 , 57);
    private GoogleMap mMap;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String datetext,weatherdescription;
    private RecyclerView recyclerView,recyclerView1;
  ;

    private MyAdapter adapter;
    private SecondFragmentAdapter adapter1;

    public SecondFragment()
    {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        return fragment;
    }

    public static SecondFragment newInstance(int page, String title) {
        SecondFragment fragment = new SecondFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View layoutview = inflater.inflate(R.layout.fragment_second, container, false);
        ArrayList<com.sport.saransh.nvigationdrawer.pojo.List> list1 = (ArrayList<com.sport.saransh.nvigationdrawer.pojo.List>) getArguments().getSerializable("list");
        latitude = getArguments().getDouble("lat",12.972);
        longitude =getArguments().getDouble("long",77.59);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = (RecyclerView) layoutview.findViewById(R.id.weatherforecasthor);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(this.getActivity(), list1);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView1 = (RecyclerView) layoutview.findViewById(R.id.weatherforecast);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        Weather[] weather = new Weather[list1.size()];
        try {
            recyclerView.setAdapter(adapter);
        } catch (Exception ex) {
            ex.getMessage();
        }
        recyclerView.setHasFixedSize(true);
        ArrayList<PojoFragmeent2> lister = new ArrayList<PojoFragmeent2>();
        ArrayList<PojoFragmeent2> lister2 = new ArrayList<PojoFragmeent2>();
        String[] strings = new String[list1.size()];
       for(int i=0;i<list1.size();i++)
       {
            datetext = list1.get(i).getDt_txt();
            mintemp =Double.parseDouble(list1.get(i).getMain().getTemp_max())-273.15;
            maxtemp = (int)mintemp;

            weathercond = list1.get(i).getWeather();
            weatherdescription=  weathercond[0].getDescription();





          // int[] count = new int[datetext.length()];
           String[] str = datetext.split(" ");
           //String[] str2 = datetext2.split(" ");
           for (int j = 0; j< str.length; j++) {

               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
               try {
                   Date date = simpleDateFormat.parse(str[0]);
                   SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");

                   goal = outFormat.format(date);
               } catch (ParseException ex) {
                   System.out.println("Exception " + ex);
               }
           }
                        strings[i] = goal;
                       if(i==0 )
                            lister.add(new PojoFragmeent2(goal, String.valueOf(maxtemp), weatherdescription));

                        if (i > 0)
                        {
                          String x1 =strings[i];
                            String x2 =strings[i-1];

                            if (!x1.equals(x2))
                            {
                                lister.add(new PojoFragmeent2(goal, String.valueOf(maxtemp), weatherdescription));
                            }

                        }

                        lister2 = lister;



                }

        adapter1 = new SecondFragmentAdapter(this.getActivity(), lister);
        recyclerView1.setAdapter(adapter1);
        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.container);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.container, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    if (googleMap != null) {
                        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);


                        googleMap.getUiSettings().setAllGesturesEnabled(true);

                        LatLng bang = new LatLng(latitude, longitude);
                        googleMap.addMarker(new MarkerOptions().position(bang).title("You are Here").snippet("Marker Description"));
                        googleMap.getMaxZoomLevel();
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(bang).zoom(13.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        googleMap.moveCamera(cameraUpdate);

                    }

                }
            });


        }
        return layoutview;
    }


}











