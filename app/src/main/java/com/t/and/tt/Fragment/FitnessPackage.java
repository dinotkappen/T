package com.t.and.tt.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.t.and.tt.Adapter.FitnessPackageAdapter;
import com.t.and.tt.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitnessPackage extends Fragment {

    RecyclerView recy_packages;
    FitnessPackageAdapter adapter;
    Button btn_book;
    ArrayList listHotel = new ArrayList<>();


    public FitnessPackage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(
                R.layout.fragment_fitness_package, container, false);
        btn_book=(Button)rootView.findViewById(R.id.btn_book);
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new BookingSummerySports();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contaner, fragment);
                transaction.addToBackStack(null).commit();
            }
        });
        for (int i = 0; i < 2; i++) {
            listHotel.add(R.drawable.img_booking);

        }

        recy_packages = rootView.findViewById(R.id.recy_packages);
        recy_packages.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new FitnessPackageAdapter(getActivity(), listHotel);
        recy_packages.setAdapter(adapter);
        return rootView;

    }
}
