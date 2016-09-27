package com.sport.saransh.nvigationdrawer;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


import android.widget.FrameLayout;
import android.widget.Toast;

import com.sport.saransh.nvigationdrawer.dataPackageInitialization.Adapter;
import com.sport.saransh.nvigationdrawer.dataPackageInitialization.ListNavigationDrawer;
import com.sport.saransh.nvigationdrawer.datapackage.PojoNavigationDrwaer;
import com.sport.saransh.nvigationdrawer.fragments.FeedbackForm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    //  private Adapter adapter;
    Button resumebtn;
    FrameLayout frameLayout;
    NavigationDrawerFragment drawerFragment;
    private  FragmentDrawerListener drawerListener;
    boolean flag = true;
    Fragment fragment;

 DrawerLayout mDrawer;
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);

    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = new FragmentChutiya();
        fragmentManager.beginTransaction()
                .replace(R.id.framelayout1, fragment)
                .commit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout1);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Weather");


        ListNavigationDrawer listNavigationDrawer = new ListNavigationDrawer();
        final List<PojoNavigationDrwaer> listnav = listNavigationDrawer.getNavigationDrwaerList();
       final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.drawerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter = new Adapter(MainActivity.this, listnav);


        recyclerView.setAdapter(adapter);

        drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation);
        drawerFragment.setup(mDrawer, toolbar);

        ////  Button button = (Button)findViewById(R.id.feedback);
        //resumebtn =(Button)findViewById(R.id.resume);


       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Feedback.class);
                startActivity(i);
            }
        });*/
        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {


                                                @Override
                                                public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                                                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());



                                                    if(child!=null && mGestureDetector.onTouchEvent(e)){
                                                        mDrawer.closeDrawers();

                                                        Toast.makeText(MainActivity.this,"The Item Clicked is: "+recyclerView.getChildPosition(child),Toast.LENGTH_SHORT).show();
                                                        int position=     recyclerView.getChildAdapterPosition(child);
                                                        String title = getString(R.string.app_name);
                                                        fragment = null;

                                                        switch (position) {
                                                            case 0:
                                                                fragment = new FragmentChutiya();
                                                                 title = getString(R.string.title_homepage);
                                                                Log.d("KMH", "Working case 0");
                                                                break;

                                                            case 1:
                                                                CopyReadAssets();

                                                                break;
                                                            case 2:
                                                               Intent intent3 = new Intent(MainActivity.this,AboutMe.class);
                                                                startActivity(intent3);
                                                                break;
                                                            case 3:
                                                                fragment = new FeedbackForm ();
                                                                Log.d("KMH", "Working case 1");
                                                                title=getString(R.string.title_activity_feed_back);
                                                                break;
                                                            default:
                                                                fragment = new FeedbackForm();
                                                                Log.d("KMH", "Working default case");
                                                                break;
                                                        }

                                                        // Insert the fragment by replacing any existing fragment
                                                        if(fragment !=null)
                                                        {
                                                            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                                                            fragmentManager.beginTransaction()
                                                                    .replace(R.id.framelayout1, fragment).addToBackStack(null)
                                                                    .commit();
                                                            getSupportActionBar().setTitle(title);

                                                            // Highlight the selected item, update the title, and close the drawer
                                                            //setTitle(TITLES[position]);

                                                            mDrawer.closeDrawers();
                                                        }

                                                        return true;


                                                    }

                                                    return false;
                                                }



                                                @Override
                                                public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                                                }

                                                @Override
                                                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                                                }
                                            }
        );
    }
    @Override
    public void onResume() {
        super.onResume();
        // Set title
        getSupportActionBar()
                .setTitle("Weather");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed()
    {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentByTag("contact");

        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }


    }
    private void CopyReadAssets()
    {
        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), "abc.pdf");
        try
        {
            in = assetManager.open("Saransh.pdf");
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e)
        {
            Log.e("tag", e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.parse("file://" + getFilesDir() + "/abc.pdf"),
                "application/pdf");

        startActivity(intent);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }

}














