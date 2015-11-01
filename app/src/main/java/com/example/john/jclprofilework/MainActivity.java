package com.example.john.jclprofilework;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.jclprofilework.GameThisMonth.DisplayGame;
import com.example.john.jclprofilework.Introduce.Introduce;
import com.example.john.jclprofilework.jclModule.Tools;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private int navItemId;

    private static final String NAV_ITEM_ID = "nav_index";

    public String backTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

/*      view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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

        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);


        //設定首頁
        Fragment homeFragment = new Introduce();
        backTag = "Introduce";
        switchFragment(homeFragment);

        //記住剛剛選擇的按鈕
/*        if(null != savedInstanceState){
            navItemId = savedInstanceState.getInt(NAV_ITEM_ID, R.id.navigation_item_1);
        }
        else{
            navItemId = R.id.navigation_item_1;
        }

        navigateTo(view.getMenu().findItem(navItemId));*/


        //取得main.xml內img1,img2的ImageView
        ImageView iv_mypic = (ImageView)findViewById(R.id.iv_mypic);
        iv_mypic.setImageBitmap(Tools.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.mypic), 80.f));


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        if (id == R.id.nav_introduce) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(), "自我介紹", Toast.LENGTH_SHORT).show();
            Fragment Introduce = new Introduce();
            backTag = "Introduce";
            switchFragment(Introduce);
        } else if (id == R.id.nav_galgame) {
            Toast.makeText(getApplicationContext(), "GAL GAME專區", Toast.LENGTH_SHORT).show();
            Fragment displayGame = new DisplayGame();
            backTag = "DisplayGame";
            switchFragment(displayGame);
        } else if (id == R.id.nav_sdk) {
            try{
                Intent it = getPackageManager().getLaunchIntentForPackage("com.platform7725.baijin.nvwang.ipay99");
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }catch (NullPointerException e){
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.platform7725.baijin.nvwang.ipay99"));
                startActivity(it);
            }catch (ActivityNotFoundException e){
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.platform7725.baijin.nvwang.ipay99"));
                startActivity(it);
            }
        } else if (id == R.id.nav_gamearea) {

            try{
                Intent it = getPackageManager().getLaunchIntentForPackage("com.ipart.android");
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }catch (NullPointerException e){
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.ipart.android"));
                startActivity(it);
            }catch (ActivityNotFoundException e){
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.ipart.android"));
                startActivity(it);
            }

        } /*else if (id == R.id.nav_about) {
            Toast.makeText(getApplicationContext(), "關於我 目前尚未完工", Toast.LENGTH_SHORT).show();
        }*/


        //contentView.setText(item.getTitle());

        item.setChecked(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchFragment(Fragment targetFragment) {

        Tools.debug("測試", 3);

        if (targetFragment != null) {
            //切換Fragment
            FragmentManager fragmentManager = getFragmentManager();

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_container, targetFragment, backTag);
            //transaction.addToBackStack(null);
            transaction.commit();
        } else {
            // error in creating fragment
            //Log.e("MainActivity", "Error in creating fragment");
        }
    }


    private void navigateTo(MenuItem menuItem){

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





    @Override
    public void onBackPressed() {
        FragmentManager fm = this.getFragmentManager();

        Tools.debug("backStack有多少個:" + fm.getBackStackEntryCount(), 3);

        if (fm.getBackStackEntryCount() == 0) {
            new AlertDialog.Builder(this)
                    .setMessage("是否離開真エロGame")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Tools.debug("維持原狀", 3);
                        }
                    }).show();
        } else {
            fm.popBackStack();
        }
    }
}
