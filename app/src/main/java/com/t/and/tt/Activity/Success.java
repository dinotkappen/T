package com.t.and.tt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.t.and.tt.Fragment.Home;
import com.t.and.tt.R;


public class Success extends AppCompatActivity {

    /**
     * Duration of wait
     **/
   TextView txtBackHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.booking_success);

            txtBackHome = findViewById(R.id.txt_home);

            txtBackHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent home = new Intent(Success.this,MainActivity.class);
                    startActivity(home);
                }
            });

//            Hawk.init(getApplicationContext())
//                    .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
//                    .setStorage(HawkBuilder.newSqliteStorage(getApplicationContext()))
//                    .setLogLevel(LogLevel.FULL)
//                    .build();

            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/

        } catch (Exception ex) {
            String ms = ex.getMessage().toString();
            String y = ms;
        }
    }

    @Override
    public void onBackPressed() {
        Intent home = new Intent(Success.this,MainActivity.class);
        startActivity(home);
    }
}
