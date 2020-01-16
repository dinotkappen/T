package com.t.and.tt.Fragment;

import android.content.Intent;
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
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.orhanobut.hawk.Hawk;
import com.t.and.tt.API.API;
import com.t.and.tt.API.APIClient;
import com.t.and.tt.Activity.LogInActivity;
import com.t.and.tt.Adapter.ViewPagerAdapter_banner;
import com.t.and.tt.Model.Home.Banner.BannerHomeModel;
import com.t.and.tt.Model.Home.Banner.ContentHomeBanner;
import com.t.and.tt.Model.Home.Services.ContentHomeServiceModel;
import com.t.and.tt.Model.Home.Services.HomeServicesModel;
import com.t.and.tt.Model.SearchBusiness.Content;
import com.t.and.tt.Model.SearchBusiness.SearchBusinessModel;
import com.t.and.tt.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.t.and.tt.Activity.MainActivity.toggle;
import static com.t.and.tt.Util.util.rotate;


public class Home extends Fragment {

    int logedIn;
    String user_id;
    String userToken;
    API apiInterface;
    ImageView actionBarProfile;
    ViewPager mViewPager;
    FrameLayout framLoading;
    int resturentID,sportsID,fitnessHealthID;
    List<ContentHomeBanner> listHomeBanners= new ArrayList();
    List<ContentHomeServiceModel> listHomeService= new ArrayList();
    List<Content> contentSearchBusinessList =new ArrayList<>();
    List<Content> sportsSearchBusinessList =new ArrayList<>();
    List<Content> resturantsSearchBusinessList =new ArrayList<>();
    List<Content> fitnessHealthSearchBusinessList =new ArrayList<>();
    private static int NUM_PAGES_BANNER = 0;
    private static int currentPage_Banner = 0;
    ImageView imgMemu,imgResturant,imgLoader,imgSports,imgFitness;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        apiInterface = APIClient.getClient().create(API.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.activity_home, container, false);

        imgMemu         = rootView.findViewById(R.id.img_menu);
        mViewPager      = rootView.findViewById(R.id.pager_banner);
        imgResturant    = rootView.findViewById(R.id.img_restaurant);
        imgSports    = rootView.findViewById(R.id.img_sports);
        imgFitness    = rootView.findViewById(R.id.img_fitness);
        imgLoader       = rootView.findViewById(R.id.img_loader);
        framLoading     = rootView.findViewById(R.id.len_loding);
        apiInterface = APIClient.getClient().create(API.class);
        actionBarProfile=rootView.findViewById(R.id.actionBarProfile);

        user_id=Hawk.get("user_id",user_id);
        logedIn=Hawk.get("logedIn",logedIn);
        userToken=Hawk.get("userToken",userToken);
        Hawk.put("resturentID",16);
        Hawk.put("sportsID",17);
        Hawk.get("fitnessHealthID",12);


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

        imgResturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hawk.put("Page",1);
                Fragment fragment =  HotelListing.newInstance(1);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                transaction.addToBackStack(null).commit();
            }
        });

        imgSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.put("Page",2);
                Fragment fragment =  HotelListing.newInstance(2);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                transaction.addToBackStack(null).commit();
            }
        });
        imgFitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.put("Page",3);
                Fragment fragment =  HotelListing.newInstance(3);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                transaction.addToBackStack(null).commit();
            }
        });

        actionBarProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(logedIn==1) {
                    Fragment fragment = new Profile();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contaner, fragment);
                    transaction.addToBackStack(null).commit();
                }
                else
                {
                    Intent intent=new Intent(getActivity(), LogInActivity.class);
                    startActivity(intent);
                }
            }
        });
        getBanner();
       // getService();
        getSearchBusiness();

//        list_iamg.add(R.drawable.viewpager1);
//        list_iamg.add(R.drawable.viewpager2);
//        list_iamg.add(R.drawable.viewpager3);
//        banner_load();
        return rootView;

    }

    public void banner_load(){
        ViewPagerAdapter_banner adapter = new ViewPagerAdapter_banner(getActivity(), listHomeBanners);
        mViewPager.setAdapter(adapter);

        NUM_PAGES_BANNER = listHomeBanners.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage_Banner == NUM_PAGES_BANNER) {
                    currentPage_Banner = 0;
                }
                mViewPager.setCurrentItem(currentPage_Banner++, true);
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


    public void getBanner(){
        Call<BannerHomeModel> call = apiInterface.getBanner(user_id,userToken,"application/json");
        call.enqueue(new Callback<BannerHomeModel>() {
            @Override
            public void onResponse(Call<BannerHomeModel> call, Response<BannerHomeModel> response) {

                BannerHomeModel bannerHomeModel = new BannerHomeModel();
                Gson gson = new Gson();
                JsonElement jsonElement = gson.toJsonTree(response.body());
                bannerHomeModel = gson.fromJson(jsonElement, BannerHomeModel.class);
                Log.e("resultHomeBanner",jsonElement.toString());
                List<ContentHomeBanner> contentHomeBanner = bannerHomeModel.getContent();
                String status=bannerHomeModel.getStatus();
                if (status.equals("success")) {
                    listHomeBanners=contentHomeBanner;
                }

                banner_load();

            }

            @Override
            public void onFailure(Call<BannerHomeModel> call, Throwable t) {
                Log.e("BagResponse", call.toString());
            }
        });
    }

    public void getService(){
        Call<HomeServicesModel> call = apiInterface.getService();
        call.enqueue(new Callback<HomeServicesModel>() {
            @Override
            public void onResponse(Call<HomeServicesModel> call, Response<HomeServicesModel> response) {

                HomeServicesModel homeServicesModel = new HomeServicesModel();
                Gson gson = new Gson();
                JsonElement jsonElement = gson.toJsonTree(response.body());
                homeServicesModel = gson.fromJson(jsonElement, HomeServicesModel.class);
                Log.e("resultHomeService",jsonElement.toString());
                List<ContentHomeServiceModel> contentHomeServiceModel = homeServicesModel.getContent();
                String status=homeServicesModel.getStatus();
                if (status.equals("success")) {
                    listHomeService=contentHomeServiceModel;
                }

               for(int i=0;i<listHomeService.size();i++)
               {
                   String name=listHomeService.get(i).getName();
                   if(name.equals("Fitness & health"))
                   {
                       fitnessHealthID=listHomeService.get(i).getId();
                       Hawk.put("fitnessHealthID",fitnessHealthID);
                   }


                   if(name.equals("Restaurant"))
                   {
                       resturentID=listHomeService.get(i).getId();
                       Hawk.put("resturentID",resturentID);
                   }

                   if(name.equals("Sports"))
                   {
                       sportsID=listHomeService.get(i).getId();
                       Hawk.put("sportsID",sportsID);
                       Log.v("sportsID",""+sportsID);
                   }
               }

            }

            @Override
            public void onFailure(Call<HomeServicesModel> call, Throwable t) {
                Log.e("BagResponse", call.toString());
            }
        });
    }

    public void getSearchBusiness(){
        Call<SearchBusinessModel> call = null;
      //  call = apiInterface.getSearchBusiness();
      //  call = apiInterface.getSearchBusiness(user_id,userToken,"application/json");
        if (user_id != null && !user_id.isEmpty() && !user_id.equals("null")) {
            call = apiInterface.getSearchBusiness(user_id,userToken,"application/json");
        }
        else
        {
            call = apiInterface.getSearchBusiness("0","","application/json");
        }

        call.enqueue(new Callback<SearchBusinessModel>() {
            @Override
            public void onResponse(Call<SearchBusinessModel> call, Response<SearchBusinessModel> response) {
                contentSearchBusinessList.clear();
                fitnessHealthSearchBusinessList.clear();
                sportsSearchBusinessList.clear();
                resturantsSearchBusinessList.clear();
                SearchBusinessModel searchBusinessModel = new SearchBusinessModel();
                Gson gson = new Gson();
                JsonElement jsonElement = gson.toJsonTree(response.body());
                searchBusinessModel = gson.fromJson(jsonElement, SearchBusinessModel.class);
                Log.e("resultSearchBusiness",jsonElement.toString());
                contentSearchBusinessList=searchBusinessModel.getContent();
                Hawk.put("contentSearchBusinessList",contentSearchBusinessList);
                String status=searchBusinessModel.getStatus();
                if (status.equals("success")) {

                   for(int i=0;i<contentSearchBusinessList.size();i++)
                   {
                       String serviceIDs=contentSearchBusinessList.get(i).getServiceIds();
                       Log.e("serviceIDs",serviceIDs);
                       if(serviceIDs.contains("17"))
                       {
                           fitnessHealthSearchBusinessList.add(contentSearchBusinessList.get(i));
                           String img=contentSearchBusinessList.get(i).getPhoto();
                           Log.v("img",img);
                       }
                    if(serviceIDs.contains("12"))
                       {
                           resturantsSearchBusinessList.add(contentSearchBusinessList.get(i));
                       }
                      if(serviceIDs.contains("16"))
                       {
                           sportsSearchBusinessList.add(contentSearchBusinessList.get(i));
                       }

                   }
                    Log.e("healthSize",""+fitnessHealthSearchBusinessList.size());
                    Hawk.put("fitnessHealthSearchBusinessList",fitnessHealthSearchBusinessList);
                    Hawk.put("resturantsSearchBusinessList",resturantsSearchBusinessList);
                    Hawk.put("sportsSearchBusinessList",sportsSearchBusinessList);

                }



            }

            @Override
            public void onFailure(Call<SearchBusinessModel> call, Throwable t) {
                Log.e("BagResponse", call.toString());
            }
        });
    }

}
