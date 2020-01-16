package com.t.and.tt.Fragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.t.and.tt.Adapter.BookingAdapter;
import com.t.and.tt.Adapter.NotifictionAdapter;
import com.t.and.tt.Other.SwipeController;
import com.t.and.tt.Other.SwipeControllerActions;
import com.t.and.tt.R;

import java.util.ArrayList;

import static com.t.and.tt.Activity.MainActivity.toggle;


public class Booking extends Fragment {


    TextView txtTitle;
    ImageView imgMemu,imgBack;
    RecyclerView recyHotel;
    BookingAdapter adapter;
    private SwipeController swipeController;
    ArrayList listHotel = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        apiInterface = APIClient.getClient().create(API.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.notification, container, false);

        imgMemu = rootView.findViewById(R.id.img_menu);
        imgBack = rootView.findViewById(R.id.img_back);
        txtTitle = rootView.findViewById(R.id.txt_title);
        recyHotel = rootView.findViewById(R.id.recyclerView_notification);

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

        txtTitle.setText(getResources().getString(R.string.booking));

        listHotel.add("qwer");
        listHotel.add("asd");
        listHotel.add("asdd");
        listHotel.add("xxc");
        listHotel.add("sdfg");
        listHotel.add("sfdg");
        listHotel.add("fgfd");

        recyHotel.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new BookingAdapter(getActivity(), listHotel);
        recyHotel.setAdapter(adapter);

        swipeController = new SwipeController(getActivity(), new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position, RecyclerView.ViewHolder viewHolder)
            {
                if (viewHolder instanceof NotifictionAdapter.MyViewHolder) {
                   // deleteProductFromCart(position);
                }
            }
        });
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyHotel);

        recyHotel.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });


        return rootView;

    }



}
