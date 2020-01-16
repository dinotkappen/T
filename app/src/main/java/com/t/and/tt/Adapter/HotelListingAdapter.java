package com.t.and.tt.Adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.orhanobut.hawk.Hawk;
import com.t.and.tt.Activity.Navigation;
import com.t.and.tt.Fragment.HotelDetails;
import com.t.and.tt.Model.SearchBusiness.Content;
import com.t.and.tt.R;

import java.util.ArrayList;
import java.util.List;

public class HotelListingAdapter extends RecyclerView.Adapter<HotelListingAdapter.MyViewHolder> {

    private FragmentActivity mContext;
    private ArrayList categoryList;
    List<Content> contentSearchBusinessModel;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lenClick;
        public TextView txt_title, txtAdrz;
        ImageView img_buss, imgMap,img_fav;
        RatingBar ratingBar;
        String subListAdrz = "";

        public MyViewHolder(View view) {
            super(view);

//        title           = view.findViewById(R.id.txt_title);
//        price           = view.findViewById(R.id.txt_price);
//        imgCategory     = view.findViewById(R.id.img_product);
            img_buss = view.findViewById(R.id.img_buss);
            lenClick = view.findViewById(R.id.len_click);
            imgMap = view.findViewById(R.id.img_map);
            img_fav= view.findViewById(R.id.img_fav);
            txt_title= view.findViewById(R.id.txt_title);
            txtAdrz= view.findViewById(R.id.txtAdrz);
            ratingBar= view.findViewById(R.id.ratingBar);

        }
    }

    public HotelListingAdapter(FragmentActivity mContext, List<Content> contentSearchBusinessModel) {
        this.mContext = mContext;
        this.categoryList = categoryList;
        this.contentSearchBusinessModel = contentSearchBusinessModel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_listing_item, parent, false);


        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Content itemList = contentSearchBusinessModel.get(position);
        Log.v("itemList",""+contentSearchBusinessModel.size());
        String title=itemList.getName();
        String imgUrl=itemList.getPhoto();
        String rating=itemList.getRating();
        String isfavourite=itemList.getIsfavourite();

        String building_number = itemList.getBuildingNumber();
        String street_number = itemList.getStreetNumber();
        String street_name = itemList.getStreetName();
        final String locations = itemList.getLocations();
        final String longitude = itemList.getLongitude();
        final String latitude = itemList.getLatitude();

        if (isfavourite != null && !isfavourite.isEmpty() && !isfavourite.equals("null")) {
            Log.v("isfavourite",isfavourite);
            if (isfavourite.equals("1")) {
                holder.img_fav.setBackgroundResource(R.drawable.fav_selected);
            } else {
                holder.img_fav.setBackgroundResource(R.drawable.fav);
            }
        }

        if (building_number != null && !building_number.isEmpty() && !building_number.equals("null")) {
            if (holder.subListAdrz.length() > 0) {
                holder.subListAdrz = holder.subListAdrz + "," + building_number;
            } else {
                holder.subListAdrz = holder.subListAdrz + building_number;
            }

        }
        if (street_number != null && !street_number.isEmpty() && !street_number.equals("null")) {
            if (holder.subListAdrz.length() > 0) {
                holder.subListAdrz = holder.subListAdrz + "," + street_number;
            } else {
                holder.subListAdrz = holder.subListAdrz + street_number;
            }

        }
        if (street_name != null && !street_name.isEmpty() && !street_name.equals("null")) {
            if (holder.subListAdrz.length() > 0) {
                holder.subListAdrz = holder.subListAdrz + "," + street_name;
            } else {
                holder.subListAdrz = holder.subListAdrz + street_name;
            }

        }
        if (locations != null && !locations.isEmpty() && !locations.equals("null")) {
            if (holder.subListAdrz.length() > 0) {
                holder.subListAdrz = holder.subListAdrz + "," + locations;
            } else {
                holder.subListAdrz = holder.subListAdrz + locations;
            }

        }

        if (holder.subListAdrz != null && !holder.subListAdrz.isEmpty() && !holder.subListAdrz.equals("null")) {
            holder.txtAdrz.setText(holder.subListAdrz);
        }
        if (title != null && !title.isEmpty() && !title.equals("null"))
        {
            holder.txt_title.setText(title);
        }
        if (imgUrl != null && !imgUrl.isEmpty() && !imgUrl.equals("null"))
        {
            Log.v("imgUrl",""+imgUrl);
            Glide.with(mContext)
                    .load(imgUrl)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        progressloading.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        progressloading.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.img_buss);
        }

        if (rating != null && !rating.isEmpty() && !rating.equals("null")) {

            String[] separated = rating.split(",");
            String singleRating = separated[0]; // this will contain "Fruit"
            String reviews = separated[1]; // this will contain " they taste good"
            if (singleRating != null && !singleRating.isEmpty() && !singleRating.equals("null")) {
                holder.ratingBar.setRating(Float.parseFloat(singleRating));
            } else {
                holder.ratingBar.setRating(Float.parseFloat("0"));
            }

        } else {

            holder.ratingBar.setRating(Float.parseFloat("0"));


        }

//        holder.title.setText(itemMainModel.getName());
//        holder.price.setText(itemMainModel.getPrice()+" QAR");
//
//
//
//        Glide.with(mContext)
//                .load(util.Imageurl+itemMainModel.getPhoto())
//                .into(holder.imgCategory);
        holder.lenClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HotelDetails();
                FragmentTransaction transaction = mContext.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                Hawk.put("selectedServiceID",itemList.getId());
                Hawk.put("selectedcontentSearchModel",contentSearchBusinessModel);
                transaction.addToBackStack(null).commit();
            }
        });
        holder.imgMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (latitude != null && !latitude.isEmpty() && !latitude.equals("null")) {
                    if (longitude != null && !latitude.isEmpty() && !latitude.equals("null")) {
                        Intent map = new Intent(mContext, Navigation.class);
                        map.putExtra("lattittude",latitude);
                        map.putExtra("longittude",longitude);
                        mContext.startActivity(map);
                    }
                    else
                    {
                        Toast.makeText(mContext,"Lccation not available",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(mContext,"Lccation not available",Toast.LENGTH_SHORT).show();
                }

            }
        });




//        holder.imgCategory.setImageDrawable(mContext.getResources().getDrawable(itemMainModel.drawble));
//        Glide.with(mContext).load(itemMainModel.getCover()).into(holder.imgCategory);


    }


    @Override
    public int getItemCount() {
        if (contentSearchBusinessModel != null)
            return contentSearchBusinessModel.size();
        else
            return 0;
    }
}
