package com.t.and.tt.Adapter.BookAreaTime;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.t.and.tt.Model.BookArearModel.BookAreaModel;
import com.t.and.tt.R;

import java.util.ArrayList;
import java.util.List;

public class BookAreaTimeAdapter extends RecyclerView.Adapter<BookAreaTimeAdapter.MyViewHolder> {

    private FragmentActivity mContext;
    private ArrayList categoryList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView time,price;

    public MyViewHolder(View view) {
        super(view);

        time           = view.findViewById(R.id.txt_time);
    }
}

    public BookAreaTimeAdapter(FragmentActivity mContext, ArrayList categoryList) {
        this.mContext = mContext;
        this.categoryList = categoryList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_arear_time_item, parent, false);


        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        final BookAreaModel itemMainModel = categoryList.get(position);

        holder.time.setText(categoryList.get(position).toString());



    }


    @Override
    public int getItemCount() {
        if (categoryList != null)
            return categoryList.size();
        else
            return 0;
    }
}
