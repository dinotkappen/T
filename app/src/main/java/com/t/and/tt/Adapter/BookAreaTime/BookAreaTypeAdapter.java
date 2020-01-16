package com.t.and.tt.Adapter.BookAreaTime;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.t.and.tt.Fragment.HotelDetails;
import com.t.and.tt.Model.BookArearModel.BookAreaModel;
import com.t.and.tt.R;

import java.util.ArrayList;
import java.util.List;

public class BookAreaTypeAdapter extends RecyclerView.Adapter<BookAreaTypeAdapter.MyViewHolder> {

    private FragmentActivity mContext;
    private List<BookAreaModel> categoryList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    LinearLayout lenClick;
    public TextView title,price;
    ImageView imgCategory,imgFavourite;
    RecyclerView recyTime;
    BookAreaTimeAdapter adapter;
    ArrayList listTime = new ArrayList();

    public MyViewHolder(View view) {
        super(view);

        title           = view.findViewById(R.id.txt_type);
//        price           = view.findViewById(R.id.txt_price);
//        imgCategory     = view.findViewById(R.id.img_product);
        recyTime    = view.findViewById(R.id.recy_time);
//        lenClick        = view.findViewById(R.id.len_click);

    }
}

    public BookAreaTypeAdapter(FragmentActivity mContext,  List<BookAreaModel> categoryList) {
        this.mContext = mContext;
        this.categoryList = categoryList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_arear_type_item, parent, false);


        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final BookAreaModel itemMainModel = categoryList.get(position);

        holder.title.setText(itemMainModel.getTitle());

        holder.recyTime.setLayoutManager(new GridLayoutManager(mContext, 3));
        holder.adapter = new BookAreaTimeAdapter(mContext, itemMainModel.getTime());
        holder.recyTime.setAdapter(holder.adapter);
//        holder.price.setText(itemMainModel.getPrice()+" QAR");
//
//
//
//        Glide.with(mContext)
//                .load(util.Imageurl+itemMainModel.getPhoto())
//                .into(holder.imgCategory);



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
