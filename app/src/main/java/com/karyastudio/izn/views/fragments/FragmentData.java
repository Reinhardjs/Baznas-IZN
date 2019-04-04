package com.karyastudio.izn.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;

public class FragmentData extends Fragment {
    private FragmentManager fms;

    public FragmentData(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_data, container, false);
        fms = ((MainActivity) getActivity()).fms;
        CardView linkToModul2 = rootView.findViewById(R.id.cardview2);
        linkToModul2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fms.beginTransaction().replace(R.id.content_frames, new FragmentDataModul2()).addToBackStack("3").commit();
            }
        });
        CardView linkToModul1 = rootView.findViewById(R.id.cardview1);
        linkToModul1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fms.beginTransaction().replace(R.id.content_frames, new FragmentDataModul1()).addToBackStack("3").commit();
            }
        });
        return rootView;
    }

}
