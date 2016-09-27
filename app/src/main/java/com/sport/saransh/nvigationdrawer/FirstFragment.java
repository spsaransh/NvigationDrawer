package com.sport.saransh.nvigationdrawer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sport.saransh.nvigationdrawer.controllers.VolleyCallBack.Controller;
import com.sport.saransh.nvigationdrawer.pojo.City;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String cityname;
    private  City city1;
    android.content.res.AssetManager assetManager;

    private OnFragmentInteractionListener mListener;

    public FirstFragment()
    {

    }

    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragment = new FirstFragment();

        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();

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
       final  View rootview= inflater.inflate(R.layout.fragment_first, container, false);
        TextView cloudcondion =(TextView)rootview.findViewById(R.id.cloudcon);
        //Typeface iconFont = WeatherHelper.IconsPacks();



        fragment_weather weatherfragment = new fragment_weather();
       final String citu1= getArguments().getString("cityname");
        final String cloudall = getArguments().getString("cloud");
        final Double tempvurr =getArguments().getDouble("temp");
        TextView cityname1 = (TextView)rootview.findViewById(R.id.city);
        TextView curentTime =(TextView)rootview.findViewById(R.id.timenow);
        TextView curtemp =(TextView)rootview.findViewById(R.id.view);
        cityname1.setText(citu1);
        Calendar calendar = Calendar.getInstance();
        int time_hour = calendar.get(Calendar.HOUR_OF_DAY);
        String AM = "AM";
        if(time_hour>12) {

            time_hour = time_hour -12;
            AM = "PM";
        }
        int minute = calendar.get(Calendar.MINUTE);
        String min = String.valueOf(minute);
        String hour_tm = String.valueOf(time_hour);
        if(time_hour <10)
            hour_tm = "0"+time_hour;

        if(minute <10)
            min = "0"+minute;

        curentTime.setText(hour_tm + ":"+min +" "+AM);
        cloudcondion.setText(cloudall);
        //curtemp.setText(tempvurr.toString());
        Controller controller = new Controller(this.getActivity().getAssets());
        controller.SetIconTextView(curtemp,tempvurr.toString(),Controller.FontIcons.degreeicon);



        return  rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
