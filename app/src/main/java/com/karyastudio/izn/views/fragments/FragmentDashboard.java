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
import com.karyastudio.izn.views.fragments.modul1.FragmentDataModul1;
import com.karyastudio.izn.views.fragments.modul2.FragmentDataModul2;

public class FragmentDashboard extends Fragment {
    private FragmentManager fms;

    public FragmentDashboard(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        fms = ((MainActivity) getActivity()).fms;

        CardView linkToData = rootView.findViewById(R.id.link_to_data);
        CardView linkToSurvey = rootView.findViewById(R.id.link_to_survey);

        linkToData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fms.beginTransaction().replace(R.id.content_frames, new FragmentDataModul1()).addToBackStack("2").commit();
            }
        });

        linkToSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fms.beginTransaction().replace(R.id.content_frames, new FragmentDataModul2()).addToBackStack("2").commit();
            }
        });

        return rootView;
    }
}
