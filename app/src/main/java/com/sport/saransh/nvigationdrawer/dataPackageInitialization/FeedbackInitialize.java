package com.sport.saransh.nvigationdrawer.dataPackageInitialization;

import com.sport.saransh.nvigationdrawer.R;
import com.sport.saransh.nvigationdrawer.datapackage.PojoFeedback;
import com.sport.saransh.nvigationdrawer.datapackage.PojoNavigationDrwaer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SARANSH on 6/11/2016.
 */

public class FeedbackInitialize
{
    List<PojoFeedback> list = new ArrayList<PojoFeedback>();
    public FeedbackInitialize()
    {
       list.add(new PojoFeedback("How do you rate this app"));
        list.add(new PojoFeedback("How did you find the UI"));
        list.add(new PojoFeedback("Overall rating of the app"));





    }
    public List<PojoFeedback> getFeedbackList()
    {
        return  list;
    }


}
