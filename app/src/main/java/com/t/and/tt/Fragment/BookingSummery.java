package com.t.and.tt.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.t.and.tt.Activity.MainActivity;
import com.t.and.tt.Activity.Success;
import com.t.and.tt.Application;
import com.t.and.tt.R;

import java.util.ArrayList;

import static com.t.and.tt.Activity.MainActivity.toggle;
import static com.t.and.tt.Util.util.rotate;


public class BookingSummery extends Fragment {

    Button btBookNow;
    TextView txtTitle;
    FrameLayout framLoading;
    ImageView imgMemu,imgBack,imgLoader;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        apiInterface = APIClient.getClient().create(API.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.booking_summery, container, false);

        imgMemu     = rootView.findViewById(R.id.img_menu);
        imgBack     = rootView.findViewById(R.id.img_back);
        imgLoader   = rootView.findViewById(R.id.img_loader);
        txtTitle    = rootView.findViewById(R.id.txt_title);
        framLoading = rootView.findViewById(R.id.len_loding);
        btBookNow = rootView.findViewById(R.id.btn_book);

        txtTitle.setText(getResources().getString(R.string.book_a_table));
        framLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rotate(getContext(),imgLoader,false,framLoading);



        imgMemu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });

        btBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Application.getContext1(),Success.class);
                startActivity(home);
            }
        });


        return rootView;

    }





}
