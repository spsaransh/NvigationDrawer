package com.sport.saransh.nvigationdrawer;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.sport.saransh.nvigationdrawer.dataPackageInitialization.Adapter;
import com.sport.saransh.nvigationdrawer.dataPackageInitialization.ListNavigationDrawer;
import com.sport.saransh.nvigationdrawer.datapackage.PojoNavigationDrwaer;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.sport.saransh.nvigationdrawer.R.layout.toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerlayout;
  //  private FragmentDrawerListener drawerListener;
    private View containerView;




    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);




        return layout;
    }


    public void setup( DrawerLayout drawerLayout, Toolbar toolbar) {

        mDrawerlayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        mDrawerlayout.setDrawerListener(mDrawerToggle);
        mDrawerlayout.post(new Runnable() {

            @Override
            public void run() {
                mDrawerToggle.syncState();

            }

        });

    }




}
