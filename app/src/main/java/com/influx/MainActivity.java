package com.influx;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.influx.fragment.LoginFragment;
import com.influx.fragment.NowPlayingFragment;
import com.influx.fragment.TopRatedFragment;
import com.influx.fragment.UpComingFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /*
       author:C.PRABAKARAN
       email:c.rajeshprabhu@gmail.com
       mobileno:9500260545
       createddate:18/12/2017
     */


   public static TextView tv_personname,tv_email;
   public static ImageView img_person;
    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View header=navigationView.getHeaderView(0);

        tv_personname=(TextView) header.findViewById(R.id.tv_personname);
        tv_email=(TextView) header.findViewById(R.id.tv_email);
        img_person=(ImageView)header.findViewById(R.id.img_person);


        LoginFragment loginFragment = new LoginFragment();
        loadFragment(loginFragment);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {

            if(LoginFragment.login_flag==-1) {
                LoginFragment loginFragment = new LoginFragment();
                loadFragment(loginFragment);
            }

        } else if (id == R.id.nav_nowplaying) {

            if(LoginFragment.login_flag==1) {
                NowPlayingFragment nowPlayingFragment = new NowPlayingFragment();
                loadFragment(nowPlayingFragment);
            }else
            {
                Snackbar.make(navigationView, "Please login here...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        } else if (id == R.id.nav_toprated) {
            if(LoginFragment.login_flag==1) {
            TopRatedFragment topratedFragment = new TopRatedFragment();
            loadFragment(topratedFragment);
            }else
            {
                Snackbar.make(navigationView, "Please login here...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        } else if (id == R.id.nav_upcoming) {
            if(LoginFragment.login_flag==1) {
            UpComingFragment upcomingFragment = new UpComingFragment();
            loadFragment(upcomingFragment);
            }else
            {
                Snackbar.make(navigationView, "Please login here...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        } else if (id == R.id.nav_logout)
        {

            LoginFragment.btnSignOut.performClick();

        }


        return true;
    }

    public  void loadFragment(Fragment selectedfragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, selectedfragment);
        fragmentTransaction.commitAllowingStateLoss();
        navigationView.invalidate();
        drawer.closeDrawers();
    }


}
