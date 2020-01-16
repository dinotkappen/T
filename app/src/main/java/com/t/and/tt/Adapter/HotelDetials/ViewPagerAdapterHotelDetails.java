package com.t.and.tt.Adapter.HotelDetials;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.t.and.tt.R;

import java.util.ArrayList;

public class ViewPagerAdapterHotelDetails extends PagerAdapter {
    LayoutInflater inflater;
    int position = 3;
    ArrayList banner_list;
    FragmentActivity activity;
    ProgressBar progressloading;
    ArrayList images = new ArrayList();


    public ViewPagerAdapterHotelDetails(FragmentActivity activity, ArrayList banner_list) {
        this.activity = activity;
        this.banner_list = banner_list;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return banner_list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final ImageView imgBanner;


        View imageLayout = inflater.inflate(R.layout.hotel_banner, container, false);

        assert imageLayout != null;

         imgBanner=(ImageView)imageLayout.findViewById(R.id.img_add);

//        Glide.with(activity)
//                .load(banner_list.get(position).getBannersImage())
//                .into(imgBanner);
//        imgBanner.setAdjustViewBounds(true);

//        progressloading.setVisibility(View.VISIBLE);

//        container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                images.add(banner_list.get(position).getBannersImage());
//                FullImageView fragment = FullImageView.newInstance(images);
//                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_contaner, fragment);
//                transaction.addToBackStack(null).commit();
//            }
//        });

        Glide.with(activity)
                .load(banner_list.get(position))
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
                .into(imgBanner);


        container.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        container.removeView((View) object);
    }
}
