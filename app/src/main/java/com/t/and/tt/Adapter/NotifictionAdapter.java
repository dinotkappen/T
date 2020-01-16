package com.t.and.tt.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.t.and.tt.Fragment.HotelDetails;
import com.t.and.tt.R;

import java.util.ArrayList;

public class NotifictionAdapter extends RecyclerView.Adapter<NotifictionAdapter.MyViewHolder> {

    private FragmentActivity mContext;
    private ArrayList categoryList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    LinearLayout lenClick;
    public TextView title,price;
    ImageView imgCategory,imgFavourite;

    public MyViewHolder(View view) {
        super(view);

//        title           = view.findViewById(R.id.txt_title);
//        price           = view.findViewById(R.id.txt_price);
//        imgCategory     = view.findViewById(R.id.img_product);
//        imgFavourite    = view.findViewById(R.id.img_favourite);


    }
}

    public NotifictionAdapter(FragmentActivity mContext, ArrayList categoryList) {
        this.mContext = mContext;
        this.categoryList = categoryList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);


        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        final HotelListing itemMainModel = categoryList.get(position);

//        holder.title.setText(itemMainModel.getName());
//        holder.price.setText(itemMainModel.getPrice()+" QAR");
//
//
//
//        Glide.with(mContext)
//                .load(util.Imageurl+itemMainModel.getPhoto())
//                .into(holder.imgCategory);
//        holder.lenClick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new HotelDetails();
//                FragmentTransaction transaction = mContext.getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_contaner, fragment);
//                transaction.addToBackStack(null).commit();
//            }
//        });


//        holder.imgCategory.setImageDrawable(mContext.getResources().getDrawable(itemMainModel.drawble));
//        Glide.with(mContext).load(itemMainModel.getCover()).into(holder.imgCategory);



    }


    @Override
    public int getItemCount() {
        if (categoryList != null)
            return categoryList.size();
        else
            return 0;
    }
}
