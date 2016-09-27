package com.sport.saransh.nvigationdrawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sport.saransh.nvigationdrawer.controllers.VolleyCallBack.Controller;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LastFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LastFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LastFragment newInstance(String param1, String param2) {
        LastFragment fragment = new LastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static LastFragment newInstance(int page, String title) {
        LastFragment fragmentLast = new LastFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentLast.setArguments(args);
        return fragmentLast;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          View rootview= inflater.inflate(R.layout.fragment_last, container, false);
          TextView humidity_txt = (TextView)rootview.findViewById(R.id.humid);
          TextView feelslike =(TextView)rootview.findViewById(R.id.feelslike);
          TextView degrre_txt =(TextView)rootview.findViewById(R.id.wind_variable);
          TextView overnight_txt =(TextView)rootview.findViewById(R.id.overnight);
          String humidity = getArguments().getString("humidity");
          Double temperature =getArguments().getDouble("temperature");
          String degree =getArguments().getString("degree");
          String speed =getArguments().getString("speed");
          String lowtemp = getArguments().getString("lowtemp");
          Double lowtemp1 =Double.parseDouble(lowtemp)-273.15;
          Double temp2 = Math.floor(lowtemp1*10)/10;
          Double speed1 = Double.parseDouble(speed);
          speed1 = Math.floor(speed1*10)/10;
          Double lessSpeed = speed1-2;
          Double kmphspeed = Math.floor((speed1*1.61)*10)/10;
          Double kmphspeedless = Math.floor((lessSpeed*1.61)*10)/10;
          humidity_txt.setText(humidity);
         // feelslike.setText(temperature.t);
          Controller controller = new Controller(this.getActivity().getAssets());
          controller.SetIconTextView(overnight_txt,"The overnight low will be "+temp2,"C",Controller.FontIcons.degreeicon);
          controller.SetIconTextView(feelslike,temperature.toString(),Controller.FontIcons.degreeicon);


         // overnight_txt.setText("The overnight low will be"+" F.");

          degrre_txt.setText("Winds variable at "+speed1.toString()+" to "+lessSpeed.toString()+" mph ("+kmphspeed.toString()+" to "+kmphspeedless.toString() +"kmph).");




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
