package com.example.myitschoolproject;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import fragments.FragmentCalculate;
import fragments.FragmentGames;
import fragments.FragmentHome;
import fragments.FragmentMath;
import fragments.FragmentPhysics;
import fragments.FragmentTeacherList;
import fragments.FragmentWeather;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView im_google;
    TextView tv_home;
    public static boolean flag=false;

    FragmentHome fhome;
    FragmentCalculate fcalculate;
    FragmentGames fgames;
    FragmentMath fmath;
    FragmentPhysics fphysics;
    FragmentTeacherList fteacherList;
    FragmentWeather fweather;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_home = findViewById(R.id.tv_home);
       if (!flag){
           tv_home.setText("Главная");
           flag=true;
       }else tv_home.setText("");
        im_google = findViewById(R.id.im_google);
        im_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTread myTread = new MyTread();
                myTread.start();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fhome = new FragmentHome();
        fcalculate = new FragmentCalculate();
        fgames = new FragmentGames();
        fmath = new FragmentMath();
        fphysics = new FragmentPhysics();
        fteacherList = new FragmentTeacherList();
        fweather = new FragmentWeather();


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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.nav_calculate) {
            ftrans.replace(R.id.container, fcalculate);
        } else if (id == R.id.nav_games) {
            ftrans.replace(R.id.container, fgames);

        } else if (id == R.id.nav_math) {
            ftrans.replace(R.id.container, fmath);

        } else if (id == R.id.nav_physics) {
            ftrans.replace(R.id.container, fphysics);

        } else if (id == R.id.nav_teacherList) {
            ftrans.replace(R.id.container, fteacherList);

        } else if (id == R.id.nav_weather) {
            ftrans.replace(R.id.container, fweather);
        }else if(id==R.id.nav_home){
            ftrans.replace(R.id.container, fhome);
        }

        ftrans.commit();
        tv_home.setText("");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class MyTread extends Thread{
        @Override
        public void run() {
            String url = "https://www.google.ru/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }

}
