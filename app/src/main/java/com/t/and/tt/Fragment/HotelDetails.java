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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.t.and.tt.Adapter.HotelDetials.ViewPagerAdapterHotelDetails;
import com.t.and.tt.Adapter.ViewPagerAdapter_banner;
import com.t.and.tt.Model.SearchBusiness.Content;
import com.t.and.tt.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static com.t.and.tt.Activity.MainActivity.toggle;
import static com.t.and.tt.Util.util.rotate;


public class HotelDetails extends Fragment {

    int page;
    Button btBookTable;
    TextView txtTitle,txtServiceTitle;
    ViewPager viewpager;
    FrameLayout framLoading;
    CircleIndicator indicator;
    ImageView imgMemu, imgBack, imgLoader;
    ArrayList list_iamg = new ArrayList();
    private static int NUM_PAGES_BANNER = 0;
    private static int currentPage_Banner = 0;
    List<Content> selectedcontentSearchModel;
    List<Content> selectedData;
    int selectedServiceID;
    String serviceTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        apiInterface = APIClient.getClient().create(API.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.hotel_details, container, false);

        txtServiceTitle=rootView.findViewById(R.id.txtServiceTitle);
        imgMemu = rootView.findViewById(R.id.img_menu);
        imgBack = rootView.findViewById(R.id.img_back);
        viewpager = rootView.findViewById(R.id.pager_banner);
        indicator = rootView.findViewById(R.id.indicator);
        btBookTable = rootView.findViewById(R.id.btn_book);
        imgLoader = rootView.findViewById(R.id.img_loader);
        framLoading = rootView.findViewById(R.id.len_loding);
        selectedcontentSearchModel=new ArrayList<>();
        selectedData=new ArrayList<>();
        selectedServiceID=Hawk.get("selectedServiceID",selectedServiceID);
        selectedcontentSearchModel=Hawk.get("selectedcontentSearchModel",selectedcontentSearchModel);
        Log.v("selectedServiceID",""+selectedServiceID);
        Log.v("selectedcontent",""+selectedcontentSearchModel.size());
        for(int i=0;i<selectedcontentSearchModel.size();i++)
        {
           // String serviceID=selectedcontentSearchModel.get(i).getId();
        }
        framLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rotate(getContext(), imgLoader, false, framLoading);
        page = Hawk.get("Page", 0);
        btBookTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hawk.get("Page", 1) == 3) {
                    Fragment fragment = new FitnessPackage();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contaner, fragment);
                    transaction.addToBackStack(null).commit();
                } else {
                    Fragment fragment = new HotelBookAreaSelectFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contaner, fragment);
                    transaction.addToBackStack(null).commit();
                }

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

        if (Hawk.get("Page", 1) == 1) {

            list_iamg.add(R.drawable.hotel_details);
            list_iamg.add(R.drawable.hotel_details);
            list_iamg.add(R.drawable.hotel_details);
            list_iamg.add(R.drawable.hotel_details);
            list_iamg.add(R.drawable.hotel_details);
            btBookTable.setText(getResources().getString(R.string.find_a_table));
        } else if (Hawk.get("Page", 2) == 2) {
            list_iamg.add(R.drawable.sports_big);
            list_iamg.add(R.drawable.sports_big);
            list_iamg.add(R.drawable.sports_big);
            list_iamg.add(R.drawable.sports_big);
            list_iamg.add(R.drawable.sports_big);
            btBookTable.setText(getResources().getString(R.string.find_ground));
        } else if (Hawk.get("Page", 3) == 3) {
            list_iamg.add(R.drawable.gym_big);
            list_iamg.add(R.drawable.gym_big);
            list_iamg.add(R.drawable.gym_big);
            list_iamg.add(R.drawable.gym_big);
            list_iamg.add(R.drawable.gym_big);
            btBookTable.setText(getResources().getString(R.string.find_gym));
        }


        banner_load();

        return rootView;

    }

    public void banner_load() {
        ViewPagerAdapterHotelDetails adapter = new ViewPagerAdapterHotelDetails(getActivity(), list_iamg);
        viewpager.setAdapter(adapter);

        indicator.setViewPager(viewpager);
//        mShimmerViewContainer.stopShimmerAnimation();
//        mShimmerViewContainer.setVisibility(View.GONE);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage_Banner = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

        NUM_PAGES_BANNER = list_iamg.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage_Banner == NUM_PAGES_BANNER) {
                    currentPage_Banner = 0;
                }
                viewpager.setCurrentItem(currentPage_Banner++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 6000, 6000);


    }


}
