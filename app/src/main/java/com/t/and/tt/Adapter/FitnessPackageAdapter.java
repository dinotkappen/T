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
import com.t.and.tt.Fragment.BookingSummerySports;
import com.t.and.tt.Fragment.HotelDetails;
import com.t.and.tt.Model.SearchBusiness.Content;
import com.t.and.tt.R;

import java.util.ArrayList;
import java.util.List;

public class FitnessPackageAdapter extends RecyclerView.Adapter<FitnessPackageAdapter.MyViewHolder> {

    private FragmentActivity mContext;
    private ArrayList categoryList;
    List<Content> contentSearchBusinessModel;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lenClick;


        public MyViewHolder(View view) {
            super(view);



            lenClick = view.findViewById(R.id.len_click);

        }
    }

    public FitnessPackageAdapter(FragmentActivity mContext, List<Content> contentSearchBusinessModel) {
        this.mContext = mContext;
        this.categoryList = categoryList;
        this.contentSearchBusinessModel = contentSearchBusinessModel;
    }

    @Override
    public FitnessPackageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_fittness_package_adapter, parent, false);


        return new FitnessPackageAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final FitnessPackageAdapter.MyViewHolder holder, int position) {


//        holder.lenClick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new BookingSummerySports();
//                FragmentTransaction transaction = mContext.getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_contaner, fragment);
//                transaction.addToBackStack(null).commit();
//            }
//        });






    }


    @Override
    public int getItemCount() {
        if (contentSearchBusinessModel != null)
            return contentSearchBusinessModel.size();
        else
            return 0;
    }
}
