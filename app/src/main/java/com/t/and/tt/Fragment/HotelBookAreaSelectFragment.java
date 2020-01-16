package com.t.and.tt.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.t.and.tt.Adapter.BookAreaTime.BookAreaTypeAdapter;
import com.t.and.tt.Adapter.HotelListingAdapter;
import com.t.and.tt.Model.BookArearModel.BookAreaModel;
import com.t.and.tt.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Formatter;

import static com.t.and.tt.Activity.MainActivity.toggle;
import static com.t.and.tt.Util.util.rotate;


public class HotelBookAreaSelectFragment extends Fragment {

    Button brBooktable;
    CalendarView calender;
    RecyclerView recycler;
    TextView txtTitle,txtDate;
    FrameLayout framLoading;
    ImageView imgMemu,imgBack,imgLoader;
    ArrayList spinner = new ArrayList();
    BookAreaTypeAdapter adapter;
    List<BookAreaModel> listType = new ArrayList<>();
    ArrayList time = new ArrayList();
    Calendar dateSelected = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        apiInterface = APIClient.getClient().create(API.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.hotel_area_select, container, false);

        imgMemu     = rootView.findViewById(R.id.img_menu);
        imgBack     = rootView.findViewById(R.id.img_back);
        imgLoader   = rootView.findViewById(R.id.img_loader);
        txtTitle    = rootView.findViewById(R.id.txt_title);
        framLoading = rootView.findViewById(R.id.len_loding);
        recycler    = rootView.findViewById(R.id.recy);
        txtDate    = rootView.findViewById(R.id.txt_date);
        calender    = rootView.findViewById(R.id.calendarView);
        brBooktable    = rootView.findViewById(R.id.btn_book);

        brBooktable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hawk.get("Page",1)==1) {
                    Fragment fragment = new BookingSummery();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contaner, fragment);
                    transaction.addToBackStack(null).commit();
                }
                else {
                    Fragment fragment = new BookingSummerySports();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contaner, fragment);
                    transaction.addToBackStack(null).commit();
                }


            }
        });

        txtTitle.setText(getResources().getString(R.string.area_selection));
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

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });

        if (Hawk.get("Page",1)==1){
            brBooktable.setText(getResources().getString(R.string.book_a_table));
            time.add("07 : 00 PM");
            time.add("08 : 00 PM");
            time.add("09 : 00 PM");
            time.add("08 : 00 PM");
            BookAreaModel areaType = new BookAreaModel();
            BookAreaModel areaType1 = new BookAreaModel();

            areaType.settitle("Restaurant Smoking");
            areaType.setTime(time);
            areaType1.settitle("Restaurant Non Smoking");
            areaType1.setTime(time);

            listType.add(areaType);
            listType.add(areaType1);
        }
        if (Hawk.get("Page",1)==2){
            brBooktable.setText(getResources().getString(R.string.find_ground));
            time.add("07 : 00 PM");
            time.add("08 : 00 PM");
            time.add("09 : 00 PM");
            time.add("08 : 00 PM");
            time.add("08 : 00 PM");
            time.add("07 : 00 PM");
            time.add("08 : 00 PM");
            BookAreaModel areaType = new BookAreaModel();

            areaType.settitle("Time slots");
            areaType.setTime(time);


            listType.add(areaType);
        } if (Hawk.get("Page",1)==3){
            brBooktable.setText(getResources().getString(R.string.find_gym));
            time.add("07 : 00 PM");
            time.add("08 : 00 PM");
            time.add("09 : 00 PM");
            time.add("08 : 00 PM");
            time.add("08 : 00 PM");
            time.add("07 : 00 PM");
            time.add("08 : 00 PM");
            BookAreaModel areaType = new BookAreaModel();

            areaType.settitle("Time slots");
            areaType.setTime(time);


            listType.add(areaType);
        }




        recycler.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new BookAreaTypeAdapter(getActivity(), listType);
        recycler.setAdapter(adapter);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calender.setVisibility(View.VISIBLE);
            }
        });

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calender.setVisibility(View.GONE);
            }
        });

        return rootView;

    }
//    private void setDatePicker ( View dialogView, TextView tv_arrival )
//    {
//        DatePicker datePicker = ( DatePicker ) dialogView.findViewById( R.id.date_picker );
//        Calendar calendar;
//        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy", Locale.ENGLISH );
//        calendar = new GregorianCalendar( datePicker.getYear( ), datePicker.getMonth( ), datePicker.getDayOfMonth( ) );
//        tv_arrival.setText( sdf.format( calendar.getTime( ) ) );
//    }





}
