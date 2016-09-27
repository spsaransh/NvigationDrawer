package com.sport.saransh.nvigationdrawer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;

import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.sport.saransh.nvigationdrawer.controllers.VolleyCallBack.Controller;
import com.sport.saransh.nvigationdrawer.controllers.VolleyCallBack.NetworkChek;
import com.sport.saransh.nvigationdrawer.fragments.ContactMe;
import com.sport.saransh.nvigationdrawer.fragments.FeedbackForm;
import com.sport.saransh.nvigationdrawer.pojo.City;
import com.sport.saransh.nvigationdrawer.pojo.Clouds;
import com.sport.saransh.nvigationdrawer.pojo.List;
import com.sport.saransh.nvigationdrawer.pojo.Main;
import com.sport.saransh.nvigationdrawer.pojo.Weather;
import com.sport.saransh.nvigationdrawer.pojo.Wind;
import com.sport3saransh.nvigationdrawer.Activity_Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;



/**
 * Created by SARANSH on 6/14/2016.
 */

public class FragmentChutiya extends Fragment implements  GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private String json1String;
    private Location location;
    double currentLatitude;
    double currentLongitude;
    private LocationManager mLocationManager;
    public ArrayList<List> list1;
    private  boolean flag;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState)
    {


        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_chutiya, container, false);
        setUserVisibleHint(true);

        mLocationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(10 * 1000).setFastestInterval(1 * 1000);


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this.getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        final ImageButton aboutme1 = (ImageButton)layout.findViewById(R.id.contact);
        aboutme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AboutMe.class);
                startActivity(intent);
            }
        });

        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        final ImageButton weather = (ImageButton) layout.findViewById(R.id.weather);

         ImageButton contact_me =(ImageButton)layout.findViewById(R.id.aboutme);
        final android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();

        contact_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                fragmentManager.beginTransaction().replace(R.id.framelayout1,ContactMe.newInstance("1","sd")).addToBackStack("contact").commit();
               // ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Weather");



            }
        });


        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final LocationManager manager = (LocationManager) getActivity().getSystemService( Context.LOCATION_SERVICE );

               /* if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) )
                {
                    buildAlertMessageNoGps();
                    if(flag)
                    nextActivityWeather();


                }*/

                    nextActivityWeather();


        }

        });
        final int imgres[] ={R.drawable.beaches,R.drawable.saranshpa};
        final Handler handler = new Handler();
        final ImageView imagebeach = (ImageView)layout.findViewById(R.id.imgbeach);



        final Fragment feedbackform = new FeedbackForm();
      //  final android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();

        ImageButton feedback = (ImageButton) layout.findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction().replace(R.id.framelayout1, feedbackform).addToBackStack("feedback").commit();

            }
        });


        return layout;
    }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            // Set title
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Weather");
        }
    }

    public void nextActivityWeather()
    {
        final NetworkChek networkChek = new NetworkChek(this.getActivity());
        final boolean networkcheq = networkChek.isNetworkAvailable();
        if (networkcheq) {


            Intent intent = new Intent(getActivity(), Activity_Weather.class);
            intent.putExtra("latitude", currentLatitude);
            intent.putExtra("longitude", currentLongitude);
            startActivity(intent);

        } else {
            Context context = getActivity().getApplicationContext();
            CharSequence text = "No Network Connection Available";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    }


    @Override
    public void onLocationChanged(Location location1) {

        handlenewlocation(location1);

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {

            location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


            if (location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            } else {
                handlenewlocation(location);
            }


        } catch (SecurityException ex) {
            String excption = ex.toString();
        }

    }
    private boolean buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog,  final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
        flag = true;
        return  flag;

    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this.getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }


    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();

    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    public void handlenewlocation(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

    }
}


