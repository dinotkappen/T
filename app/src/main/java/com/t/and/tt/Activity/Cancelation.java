package com.t.and.tt.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.t.and.tt.R;

import static com.t.and.tt.Activity.MainActivity.toggle;
import static com.t.and.tt.Util.util.rotate;


public class Cancelation extends AppCompatActivity {

    /**
     * Duration of wait
     **/
    TextView txtTitle;
    FrameLayout framLoading;
    ImageView imgBack,imgLoader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.about_us);
        imgBack     = findViewById(R.id.img_back);
        imgLoader   = findViewById(R.id.img_loader);
        txtTitle    = findViewById(R.id.txt_title);
        framLoading = findViewById(R.id.len_loding);

        txtTitle.setText(getResources().getString(R.string.cancellation_policy));
        framLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rotate(this,imgLoader,true,framLoading);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });



    }

}
