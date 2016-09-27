package com.sport.saransh.nvigationdrawer.dataPackageInitialization;

import android.support.annotation.NonNull;

import com.sport.saransh.nvigationdrawer.R;
import com.sport.saransh.nvigationdrawer.datapackage.PojoNavigationDrwaer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by SARANSH on 3/12/2016.
 */
public class ListNavigationDrawer {

List<PojoNavigationDrwaer> navigationDrwaerList = new ArrayList<PojoNavigationDrwaer>() ;
    public ListNavigationDrawer(){
        navigationDrwaerList.add(new PojoNavigationDrwaer("Home", R.drawable.home));
        navigationDrwaerList.add(new PojoNavigationDrwaer("MyResume",R.drawable.resume));
        navigationDrwaerList.add(new PojoNavigationDrwaer("MyProfile",R.drawable.profile));
        navigationDrwaerList.add(new PojoNavigationDrwaer("Feedback",R.drawable.feedback));


    }

    public List<PojoNavigationDrwaer> getNavigationDrwaerList() {
        return navigationDrwaerList;
    }
}
