package com.t.and.tt.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.t.and.tt.Adapter.HotelDetials.ViewPagerAdapterHotelDetails;
import com.t.and.tt.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static com.t.and.tt.Activity.MainActivity.toggle;
import static com.t.and.tt.Util.util.rotate;


public class HotelBookTableFragment extends Fragment {

    Button btFindTable;
    TextView txtTitle;
    LinearLayout lenSports,lenHotel;
    FrameLayout framLoading;
    ArrayList spinner = new ArrayList();
    Spinner spinCousion,spinDining,spinDress;
    ImageView imgMemu,imgBack,imgLoader,imgService;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        apiInterface = APIClient.getClient().create(API.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.hotel_table_book, container, false);

        imgMemu     = rootView.findViewById(R.id.img_menu);
        imgBack     = rootView.findViewById(R.id.img_back);
        imgLoader   = rootView.findViewById(R.id.img_loader);
        txtTitle    = rootView.findViewById(R.id.txt_title);
        framLoading = rootView.findViewById(R.id.len_loding);
        spinCousion = rootView.findViewById(R.id.spin_cousin);
        spinDining  = rootView.findViewById(R.id.spin_dining);
        spinDress   = rootView.findViewById(R.id.spin_dress);
        btFindTable = rootView.findViewById(R.id.btn_find_table);
        lenSports = rootView.findViewById(R.id.len_sports);
        lenHotel = rootView.findViewById(R.id.len_hotel);
        imgService = rootView.findViewById(R.id.img_service);

        if (Hawk.get("Page",1)==1){
            lenHotel.setVisibility(View.VISIBLE);
            lenSports.setVisibility(View.GONE);
            imgService.setImageDrawable(getResources().getDrawable(R.drawable.img_booking));
            btFindTable.setText(getResources().getString(R.string.find_a_table));
        }
        else {
            lenHotel.setVisibility(View.GONE);
            lenSports.setVisibility(View.VISIBLE);
            imgService.setImageDrawable(getResources().getDrawable(R.drawable.sports));
            btFindTable.setText(getResources().getString(R.string.find_ground));
        }

        if (Hawk.get("Page",1)==1) {
            txtTitle.setText(getResources().getString(R.string.book_a_table));
        }
        else {
            txtTitle.setText(getResources().getString(R.string.book_a_ground));
        }
        framLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rotate(getContext(),imgLoader,false,framLoading);

        spinner.add("Select");
        spinner.add("Select");
        spinner.add("Select");
        spinner.add("Select");
        spinner.add("Select");
        ArrayAdapter aa = new ArrayAdapter(getActivity(),R.layout.spinner_text,spinner);
        aa.setDropDownViewResource(R.layout.spinner_text);
        spinCousion.setAdapter(aa);

        ArrayAdapter aa1 = new ArrayAdapter(getActivity(),R.layout.spinner_text,spinner);
        aa.setDropDownViewResource(R.layout.spinner_text);
        spinDining.setAdapter(aa1);

        ArrayAdapter aa11 = new ArrayAdapter(getActivity(),R.layout.spinner_text,spinner);
        aa.setDropDownViewResource(R.layout.spinner_text);
        spinDress.setAdapter(aa11);
        spinCousion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){

                }
//                    txtCity.setText(listCity.get(position).getCity());
//                cityId = listCity.get(position).getId();
//                }
//                flageFirstLoading=1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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

        btFindTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HotelBookAreaSelectFragment()  ;
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                transaction.addToBackStack(null).commit();
            }
        });


        return rootView;

    }





}
