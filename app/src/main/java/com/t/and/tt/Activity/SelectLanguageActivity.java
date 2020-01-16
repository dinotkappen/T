package com.t.and.tt.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.orhanobut.hawk.Hawk;
import com.t.and.tt.R;

import java.util.Locale;

public class SelectLanguageActivity extends AppCompatActivity {
    Button btnEnglish,btnArabic;
    String selectedLang;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        btnEnglish = (Button) findViewById(R.id.btnEnglish);
        btnArabic= (Button) findViewById(R.id.btnArabic);
        btnSignUp= (Button) findViewById(R.id.btnSignUp);
        selectedLang="en";
        btnEnglish.setBackgroundResource(R.drawable.blue_button);
        btnArabic.setBackgroundResource(R.drawable.curved_rectangle_trans_white_back);
        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedLang="en";
                Hawk.put("selectedLang",selectedLang);
                btnEnglish.setBackgroundResource(R.drawable.blue_button);
                btnArabic.setBackgroundResource(R.drawable.curved_rectangle_trans_white_back);

            }
        });

        btnArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedLang="ar";
                Hawk.put("selectedLang",selectedLang);
                btnEnglish.setBackgroundResource(R.drawable.curved_rectangle_trans_white_back);
                btnArabic.setBackgroundResource(R.drawable.blue_button);


            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageSelection(selectedLang);

            }
        });
    }


    public void languageSelection(String lang) {
        try {
            Locale locale = new Locale(lang);
            Resources resources = getResources();
            Configuration config = resources.getConfiguration();
            config.locale = locale;
            if (Build.VERSION.SDK_INT >= 17) {
                config.setLayoutDirection(locale);
            }

            resources.updateConfiguration(config, resources.getDisplayMetrics());
            Intent i = new Intent(SelectLanguageActivity.this, MainActivity.class);
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
