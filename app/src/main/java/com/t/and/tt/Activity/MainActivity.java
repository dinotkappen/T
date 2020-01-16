package com.t.and.tt.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.t.and.tt.CustomView.RoundImageView;
import com.t.and.tt.Fragment.Booking;
import com.t.and.tt.Fragment.ContactUs;
import com.t.and.tt.Fragment.Home;
import com.t.and.tt.Fragment.HotelListing;
import com.t.and.tt.Fragment.Notification;
import com.t.and.tt.Fragment.Profile;
import com.t.and.tt.Fragment.Settings;
import com.t.and.tt.Other.NetworkChangeReceiver;
import com.t.and.tt.R;

import static com.t.and.tt.Activity.NoInternetActivity.closeActivity;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    static Activity mContext;
    private AppBarConfiguration mAppBarConfiguration;
    Button btn;
    static Runnable MainRunnable;
    static DrawerLayout drawer;
    NavigationView navigationView;
    static Runnable home_fragment,notification_fragment,profile_fragment,contact_fragment,settings_fragment,booking_fragment,sports_fragment,resturant_fragment,gym_fragment;
    static BubbleNavigationConstraintView bottom_navigation;
    int logedIn;
    TextView txtLogOut;
    RoundImageView profNav;
    static Boolean flagNoInternetActivity = false;
    private NetworkChangeReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, filter);

        Hawk.init(getApplicationContext())
                    .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                    .setStorage(HawkBuilder.newSqliteStorage(getApplicationContext()))
                    .setLogLevel(LogLevel.FULL)
                    .build();

        mContext = MainActivity.this;

        drawer          = findViewById(R.id.drawer_layout);
        bottom_navigation          = findViewById(R.id.bottom_navigation);
        navigationView = findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        logedIn=Hawk.get("logedIn",logedIn);

        profNav=(RoundImageView) drawer.findViewById(R.id.profNav);
        txtLogOut=drawer.findViewById(R.id.txtLogOut);

        profNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
                if(logedIn==1) {
                    Fragment fragment = new Profile();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contaner, fragment);
                    transaction.addToBackStack(null).commit();
                }
                else
                {
                    Intent intent=new Intent(MainActivity.this,LogInActivity.class);
                    startActivity(intent);
                }

            }
        });

        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.put("logedIn",0);
                Hawk.put("userToken","");
                Hawk.put("user_id","");
                Intent login = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(login);
            }
        });

        home_fragment = new Runnable() {
            public void run() {
                Fragment fragment = new Home();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                transaction.addToBackStack(null).commit();

            }
        };
        settings_fragment=new Runnable() {
            @Override
            public void run() {
                Fragment fragment = new Settings();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                transaction.addToBackStack(null).commit();
            }
        };
        notification_fragment = new Runnable() {
            public void run() {
                if(logedIn==1) {
                    Fragment fragment = new Notification();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contaner, fragment);
                    transaction.addToBackStack(null).commit();
                }
                else
                {
                    Intent intent=new Intent(MainActivity.this,LogInActivity.class);
                    startActivity(intent);
                }

            }
        };
        profile_fragment = new Runnable() {
            public void run() {
                if(logedIn==1) {
                    Fragment fragment = new Profile();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contaner, fragment);
                    transaction.addToBackStack(null).commit();
                }
                else
                {
                    Intent intent=new Intent(MainActivity.this,LogInActivity.class);
                    startActivity(intent);
                }

            }
        };
        contact_fragment = new Runnable() {
            public void run() {
                Fragment fragment = new ContactUs();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                transaction.addToBackStack(null).commit();

            }
        };
        booking_fragment = new Runnable() {
            public void run() {
                if(logedIn==1) {
                    Fragment fragment = new Booking();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contaner, fragment);
                    transaction.addToBackStack(null).commit();
                }
                else
                {
                    Intent intent=new Intent(MainActivity.this,LogInActivity.class);
                    startActivity(intent);
                }

            }
        };
        sports_fragment = new Runnable() {
            public void run() {
                Hawk.put("Page",2);
                Fragment fragment =  HotelListing.newInstance(2);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                transaction.addToBackStack(null).commit();

            }
        };
        resturant_fragment = new Runnable() {
            public void run() {
                Hawk.put("Page",1);
                Fragment fragment =  HotelListing.newInstance(1);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                transaction.addToBackStack(null).commit();

            }
        };
        gym_fragment = new Runnable() {
            public void run() {
                Hawk.put("Page",3);
                Fragment fragment =  HotelListing.newInstance(3);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                transaction.addToBackStack(null).commit();

            }
        };

        MainRunnable = home_fragment;
        fragment_open();

        bottom_navigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                //navigation changed, do something

                if (position==0){
                    MainRunnable = home_fragment;
                    fragment_open();
                }
                else if (position==1){
                    if(logedIn==1)
                    {
                        MainRunnable = notification_fragment;
                        fragment_open();
                    }
                    else
                    {
                        Intent intent=new Intent(MainActivity.this,LogInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else if (position==2){

                    if(logedIn==1)
                    {
                        MainRunnable = booking_fragment;
                        fragment_open();
                    }
                    else
                    {
                        Intent intent=new Intent(MainActivity.this,LogInActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
                else if (position==3){
                    if(logedIn==1)
                    {
                        MainRunnable = profile_fragment;
                        fragment_open();
                    }
                    else
                    {
                        Intent intent=new Intent(MainActivity.this,LogInActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        });



    }

    public static void fragment_open(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainRunnable.run();

            }
        }, 350);
    }

    public static void toggle() {
        if (drawer.isDrawerVisible(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment_back = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_contaner);
        int id = item.getItemId();

        if (id == R.id.nav_home) {



            if (fragment_back instanceof Home) {

            }
            else {
                MainRunnable = home_fragment;
                fragment_open();
            }
        }
        else if (id == R.id.nav_contact) {
            MainRunnable = contact_fragment;
            fragment_open();
        }
        else if (id == R.id.nav_setting) {
            MainRunnable = settings_fragment;
            fragment_open();
        } else if (id == R.id.nav_about_us) {
            Intent about = new Intent(MainActivity.this,AboutUs.class);
            startActivity(about);
        }else if (id == R.id.nav_terms) {
            Intent about = new Intent(MainActivity.this,TermsAndCondiction.class);
            startActivity(about);
        }else if (id == R.id.nav_privacy) {
            Intent about = new Intent(MainActivity.this,Privacy.class);
            startActivity(about);
        }else if (id == R.id.nav_cancellation) {
            Intent about = new Intent(MainActivity.this,Cancelation.class);
            startActivity(about);
        }else if (id == R.id.nav_sports) {
            MainRunnable = sports_fragment;
            fragment_open();
        }else if (id == R.id.nav_resturamt) {
            MainRunnable = resturant_fragment;
            fragment_open();
        }else if (id == R.id.nav_health) {
            MainRunnable = gym_fragment;
            fragment_open();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        Fragment fragment_back = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_contaner);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (fragment_back instanceof Home){
                mContext.moveTaskToBack(true);
            }

            else {
                super.onBackPressed();
            }
        }


    }

    public static void bottomActive(int position){
        bottom_navigation.setCurrentActiveItem(position);
    }


    public static void no_internet(boolean status, Context context) {

        if (status) {
            if (flagNoInternetActivity == true) {
                flagNoInternetActivity = false;
                closeActivity();

            }

        } else {
            if (flagNoInternetActivity != true) {
                Intent intent = new Intent(context, NoInternetActivity.class);
                context.startActivity(intent);
                flagNoInternetActivity = true;
            }
        }


    }

}
