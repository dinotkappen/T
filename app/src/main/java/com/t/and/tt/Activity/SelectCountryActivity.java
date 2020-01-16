package com.t.and.tt.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.t.and.tt.R;

public class SelectCountryActivity extends AppCompatActivity {
Button btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        btnConfirm=(Button)findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectCountryActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}
