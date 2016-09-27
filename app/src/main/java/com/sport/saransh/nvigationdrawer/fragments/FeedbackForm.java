package com.sport.saransh.nvigationdrawer.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;

import com.sport.saransh.nvigationdrawer.R;
import com.sport.saransh.nvigationdrawer.dataPackageInitialization.FeedBackAdapter;
import com.sport.saransh.nvigationdrawer.dataPackageInitialization.FeedbackInitialize;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FeedbackForm.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FeedbackForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackForm extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FeedbackForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackForm.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackForm newInstance(String param1, String param2) {
        FeedbackForm fragment = new FeedbackForm();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FeedbackInitialize feedbackInitialize = new FeedbackInitialize();
        // Inflate the layout for this fragment
       View layout= inflater.inflate(R.layout.fragment_feedback_form, container, false);

        RecyclerView recyclerView = (RecyclerView)layout.findViewById(R.id.feedback_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FeedBackAdapter adapter = new FeedBackAdapter(getActivity(), feedbackInitialize.getFeedbackList());

        recyclerView.setAdapter(adapter);
        setUserVisibleHint(true);
        TextView test = (TextView) layout.findViewById(R.id.text_item);
        return layout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            // Set title
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("FeedBack");
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
