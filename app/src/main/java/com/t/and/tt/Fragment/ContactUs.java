package com.t.and.tt.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.t.and.tt.R;

import static com.t.and.tt.Activity.MainActivity.toggle;


public class ContactUs extends Fragment {


    TextView txtTitle;
    ImageView imgMemu,imgBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        apiInterface = APIClient.getClient().create(API.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.contact_us, container, false);

        imgMemu = rootView.findViewById(R.id.img_menu);
        imgBack = rootView.findViewById(R.id.img_back);
        txtTitle = rootView.findViewById(R.id.txt_title);

        imgMemu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        txtTitle.setText(getResources().getString(R.string.contact));

        return rootView;

    }



}
