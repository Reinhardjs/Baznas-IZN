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

public class FragmentIsiSurvey extends Fragment {
    private FragmentManager fms;
    public FragmentIsiSurvey(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_isi_survey, container, false);
        fms = ((MainActivity) getActivity()).fms;
        CardView linkToSurvey = rootView.findViewById(R.id.cardview2);
        CardView linkToSurvey1 = rootView.findViewById(R.id.cardview1);

        linkToSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul2101()).addToBackStack("3").commit();
            }
        });

        linkToSurvey1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul1101()).addToBackStack("3").commit();
            }
        });
        return rootView;
    }

}
