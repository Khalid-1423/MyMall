package com.example.mymall;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.PluralsRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener  {



    private static final  int HOME_FRAGMENT = 0;
    private static final  int CART_FRAGMENT = 1;
    private static final  int ORDERS_FRAGMENT = 2;
    private static final  int WISHLIST_FRAGMENT = 3;
    private static final  int REWARDS_FRAGMENT = 4;
    private static final  int  ACCOUNT_FRAGMENT= 5;

    private FrameLayout frameLayout;
    private ImageView actionBarLogo;

    private static int currentFragment=-1;

    NavigationView navigationView;

    private Window window;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = findViewById(R.id.toolbar);
        actionBarLogo = findViewById(R.id.actionbar_logo);

        setSupportActionBar(toolbar);

        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);






        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open
                ,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        drawer.addDrawerListener(toggle);

        toggle.syncState();





         navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        navigationView.getMenu().getItem(0).setChecked(true);

        frameLayout = findViewById(R.id.main_framelayout);

        setFragment(new HomeFragment(),HOME_FRAGMENT);

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            if(currentFragment == HOME_FRAGMENT)
            {
                currentFragment =-1;
                super.onBackPressed();

            }
            else
            {
                actionBarLogo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu(); //onCreateOptionsMenu() will run again
                setFragment(new HomeFragment(),HOME_FRAGMENT);
                navigationView.getMenu().getItem(0).setChecked(true);

            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(currentFragment == HOME_FRAGMENT)
        {
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            getMenuInflater().inflate(R.menu.main,menu);

        }

        return  true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.main_search_icon)
        {
            //todo :search

            return true;
        }
        else if(id==R.id.main_notification_icon)
        {
            //todo :notification

            return true;


        }
        else if(id==R.id.main_cart_icon)
        {
            final Dialog signInDialog = new Dialog(MainActivity.this);
            signInDialog.setContentView(R.layout.sign_in_dialog);

            signInDialog.setCancelable(true);

            signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

            Button dialogSignInBtn = signInDialog.findViewById(R.id.sign_in_btn);
            Button dialogSignUpBtn = signInDialog.findViewById(R.id.sign_up_btn);

            final Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);

            dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    signInDialog.dismiss();

                    RegisterActivity.setSignUpFragment = false;
                    startActivity(registerIntent);


                }
            });

            dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signInDialog.dismiss();
                    RegisterActivity.setSignUpFragment = true;
                    startActivity(registerIntent);


                }
            });

            signInDialog.show();


            //gotoFragment("My Cart",new MyCartFragment(),CART_FRAGMENT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void gotoFragment(String title, Fragment fragment,int fragmentNo) {
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);

        invalidateOptionsMenu();

        setFragment(fragment,fragmentNo);

        if(fragmentNo == CART_FRAGMENT)
        {
            navigationView.getMenu().getItem(3).setChecked(true);

        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.nav_my_mall)
        {
            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu(); //onCreateOptionsMenu() will run again
            setFragment(new HomeFragment(),HOME_FRAGMENT);
        }

       else if(id==R.id.nav_my_orders)
        {
            gotoFragment("My Orders",new MyOrdersFragment(),ORDERS_FRAGMENT);


        }
        else if(id==R.id.nav_my_rewards)
        {
            gotoFragment("My Orders",new MyRewardsFragment(),REWARDS_FRAGMENT);

        }
        else if(id==R.id.nav_my_cart)
        {
            gotoFragment("My Cart",new MyCartFragment(),CART_FRAGMENT);
        }
        else if(id==R.id.nav_my_wishlist)
        {
            gotoFragment("My Rewards",new MyRewardsFragment(),REWARDS_FRAGMENT);


        }
        else if(id==R.id.nav_my_account)
        {
            gotoFragment("My Account",new MyAccountFragment(),ACCOUNT_FRAGMENT);

        }
        else if(id==R.id.nav_sign_out)
        {

        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);



        return true;
    }

   private void setFragment(Fragment fragment,int fragmentNo)
   {

       if(fragmentNo!=currentFragment)
       {
           if(fragmentNo == REWARDS_FRAGMENT)
           {
               window.setStatusBarColor(Color.parseColor("#5B04B1"));
               toolbar.setBackgroundColor(Color.parseColor("#5B04B1"));
           }
           else
           {
               window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
               toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
           }

           currentFragment = fragmentNo;

           FragmentTransaction fragmentTransaction  =getSupportFragmentManager().beginTransaction();
           fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
           fragmentTransaction.replace(frameLayout.getId(), fragment);
           fragmentTransaction.commit();

       }

   }








}
