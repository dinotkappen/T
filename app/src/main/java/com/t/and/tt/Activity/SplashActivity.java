package com.t.and.tt.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.t.and.tt.R;

import java.util.Locale;


public class SplashActivity extends AppCompatActivity {

    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 500;
    int logedIn = 0;
    String countryID = "0",selectedLang="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.splach);

            Hawk.init(this)
                    .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                    .setStorage(HawkBuilder.newSqliteStorage(this))
                    .setLogLevel(LogLevel.FULL)
                    .build();

//            Hawk.init(getApplicationContext())
//                    .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
//                    .setStorage(HawkBuilder.newSqliteStorage(getApplicationContext()))
//                    .setLogLevel(LogLevel.FULL)
//                    .build();

            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    selectedLang=Hawk.get("selectedLang",selectedLang);
                    if (selectedLang != null && !selectedLang.isEmpty() && !selectedLang.equals("null"))
                    {
                        languageSelection();
                    }
                    else
                    {
                        Intent i = new Intent(SplashActivity.this, SelectLanguageActivity.class);
                        startActivity(i);
                        finish();

                    }


//
                }
            }, SPLASH_DISPLAY_LENGTH);
        } catch (Exception ex) {
            String ms = ex.getMessage().toString();
            String y = ms;
        }
    }

    public void languageSelection() {
        try {
            Locale locale = new Locale(selectedLang);
            Resources resources = getResources();
            Configuration config = resources.getConfiguration();
            config.locale = locale;
            if (Build.VERSION.SDK_INT >= 17) {
                config.setLayoutDirection(locale);
            }

            resources.updateConfiguration(config, resources.getDisplayMetrics());
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();

//            countryID = Hawk.get("countryID",countryID);
//            if (countryID.equals("0")) {
//                Intent i = new Intent(SplashActivity.this, SelectCountryActivity.class);
//                startActivity(i);
//                finish();
//            } else {
//                Intent i = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(i);
//                finish();
//            }

        } catch (Exception ex) {
            Log.v("languageSelection", ex.getMessage().toString());
        }
    }
}
