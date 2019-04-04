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

public class FragmentDashboard extends Fragment {
    private FragmentManager fms;
    public FragmentDashboard(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        fms = ((MainActivity) getActivity()).fms;

        CardView linkToSurvey = rootView.findViewById(R.id.link_to_survey);
        CardView linkToData = rootView.findViewById(R.id.link_to_data);
        linkToSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fms.beginTransaction().replace(R.id.content_frames, new FragmentIsiSurvey()).addToBackStack("2").commit();
            }
        });
        linkToData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fms.beginTransaction().replace(R.id.content_frames, new FragmentData()).addToBackStack("2").commit();
            }
        });
        return rootView;
    }
}
