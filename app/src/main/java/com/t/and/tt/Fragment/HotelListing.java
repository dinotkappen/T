package com.t.and.tt.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.t.and.tt.Adapter.HotelListingAdapter;
import com.t.and.tt.Model.SearchBusiness.Content;
import com.t.and.tt.R;

import java.util.ArrayList;
import java.util.List;

import static com.t.and.tt.Activity.MainActivity.toggle;
import static com.t.and.tt.Util.util.rotate;


public class HotelListing extends Fragment {


    TextView txtTitle;
    public static int page;
    RecyclerView recyHotel;
    FrameLayout framLoading;
    HotelListingAdapter adapter;
    ImageView imgMemu, imgBack, imgLoader;
    ArrayList listHotel = new ArrayList<>();
    List<Content> sportsSearchBusinessList = new ArrayList<>();
    List<Content> resturantsSearchBusinessList = new ArrayList<>();
    List<Content> fitnessHealthSearchBusinessList = new ArrayList<>();
    private static int NUM_PAGES_BANNER = 0;

    public static HotelListing newInstance(int pageType) {
        HotelListing fragment = new HotelListing();
        page = pageType;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        apiInterface = APIClient.getClient().create(API.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.hotel_listing, container, false);

        imgMemu = rootView.findViewById(R.id.img_menu);
        imgBack = rootView.findViewById(R.id.img_back);
        txtTitle = rootView.findViewById(R.id.txt_title);
        recyHotel = rootView.findViewById(R.id.recy_hotel);
        imgLoader = rootView.findViewById(R.id.img_loader);
        framLoading = rootView.findViewById(R.id.len_loding);
        fitnessHealthSearchBusinessList = Hawk.get("fitnessHealthSearchBusinessList", fitnessHealthSearchBusinessList);
        sportsSearchBusinessList = Hawk.get("sportsSearchBusinessList", sportsSearchBusinessList);
        resturantsSearchBusinessList = Hawk.get("resturantsSearchBusinessList", resturantsSearchBusinessList);

        framLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rotate(getContext(), imgLoader, false, framLoading);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        if (page == 1) {

            txtTitle.setText(getResources().getString(R.string.table_booking));
            if (resturantsSearchBusinessList.size() > 0) {
                recyHotel.setLayoutManager(new GridLayoutManager(getContext(), 1));
                adapter = new HotelListingAdapter(getActivity(), resturantsSearchBusinessList);
                recyHotel.setAdapter(adapter);
            }
        } else if (page == 2) {

            if (sportsSearchBusinessList.size() > 0) {
                recyHotel.setLayoutManager(new GridLayoutManager(getContext(), 1));
                adapter = new HotelListingAdapter(getActivity(), sportsSearchBusinessList);
                recyHotel.setAdapter(adapter);
            }
            txtTitle.setText(getResources().getString(R.string.sports_booking));
        } else if (page == 3) {

            txtTitle.setText(getResources().getString(R.string.gym_booking));
            Log.v("fitnessHealthSize", "" + fitnessHealthSearchBusinessList.size());
            if (fitnessHealthSearchBusinessList.size() > 0) {
                recyHotel.setLayoutManager(new GridLayoutManager(getContext(), 1));
                adapter = new HotelListingAdapter(getActivity(), fitnessHealthSearchBusinessList);
                recyHotel.setAdapter(adapter);
            }
        }


        imgMemu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });


        return rootView;

    }


}
