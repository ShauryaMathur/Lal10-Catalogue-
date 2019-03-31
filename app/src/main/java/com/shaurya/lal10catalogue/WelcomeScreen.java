package com.shaurya.lal10catalogue;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View fl=findViewById(R.id.includeviewpager);
        vp=fl.findViewById(R.id.viewpager);

        ImageAdapter adapter=new ImageAdapter(this);
        vp.setAdapter(adapter);

        ImageView btnext=findViewById(R.id.button2);
        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int a=vp.getCurrentItem();
                vp.setCurrentItem(vp.getCurrentItem()+1,true);
            }
        });

        ImageView btprev=findViewById(R.id.button3);
        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int a=vp.getCurrentItem();
                vp.setCurrentItem(vp.getCurrentItem()-1,true);
            }
        });

        /*private int getItem(int i) {
            return vp.getCurrentItem() + i;
        }*/


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "For Composing Query Emails", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                startActivity(new Intent(WelcomeScreen.this,EmailPopup.class));
            }
        });

        FloatingActionButton fab2=findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "For Adding Notes", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.welcome_screen, menu);
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
        else if(id==R.id.action_about){
            Toast.makeText(WelcomeScreen.this,"Shaurya Mathur",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

            startActivity(new Intent(WelcomeScreen.this,UploadImage.class));
        } else if (id == R.id.nav_catalog) {

            startActivity(new Intent(WelcomeScreen.this,GetAllProductsCardLayout.class));

        } else if (id == R.id.nav_slideshow) {

            //Get Product Carousel by Categories
            startActivity(new Intent(WelcomeScreen.this,CatalogueActivity.class));

        } else if (id == R.id.nav_manage) {

            //Get Products added to PDF and download PDF

            startActivity(new Intent(WelcomeScreen.this,GetPDF.class));

        } else if (id == R.id.nav_tasks) {

            //Add Logs to spreadsheets
            startActivity(new Intent(WelcomeScreen.this,AddLogs.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
