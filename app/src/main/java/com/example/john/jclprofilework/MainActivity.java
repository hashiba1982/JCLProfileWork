package com.example.john.jclprofilework;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private TextView contentView;
    private int navItemId;

    private static final String NAV_ITEM_ID = "nav_index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        contentView = (TextView) findViewById(R.id.content_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

/*        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Toast.makeText(MainActivity.this, menuItem.getTitle() + " pressed", Toast.LENGTH_SHORT).show();
                contentView.setText(menuItem.getTitle());

                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });*/


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle( this, drawerLayout, toolbar, R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView nav_view = (NavigationView) findViewById(R.id.navigation_view);
        nav_view.setNavigationItemSelectedListener(this);

        //記住剛剛選擇的按鈕
/*        if(null != savedInstanceState){
            navItemId = savedInstanceState.getInt(NAV_ITEM_ID, R.id.navigation_item_1);
        }
        else{
            navItemId = R.id.navigation_item_1;
        }

        navigateTo(view.getMenu().findItem(navItemId));*/

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

/*        if (id == R.id.nav_introduce) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(), "自我介紹", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_galgame) {
            Toast.makeText(getApplicationContext(), "GAL GAME專區", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_sdk) {
            Toast.makeText(getApplicationContext(), "iPlay99 SDK", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gamearea) {
            Toast.makeText(getApplicationContext(), "iPair遊戲專區", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about) {
            Toast.makeText(getApplicationContext(), "關於我", Toast.LENGTH_SHORT).show();
        }*/

        Toast.makeText(MainActivity.this, item.getTitle() + " pressed", Toast.LENGTH_SHORT).show();
        contentView.setText(item.getTitle());

        item.setChecked(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navigateTo(MenuItem menuItem){
        contentView.setText(menuItem.getTitle());

        navItemId = menuItem.getItemId();
        menuItem.setChecked(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, navItemId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                //mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Make sure this is the method with just `Bundle` as the signature
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
