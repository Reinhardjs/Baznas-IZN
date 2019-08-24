package com.karyastudio.izn.views.fragments;

import android.content.Intent;
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
import com.karyastudio.izn.views.activities.SurveyIZNActivity;
import com.karyastudio.izn.views.activities.SurveyKDZActivity;

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
                // fms.beginTransaction().replace(R.id.content_frames, new FragmentModul2101()).addToBackStack("3").commit();
                Intent intent = new Intent(getActivity(), SurveyIZNActivity.class);
                intent.putExtra(SurveyIZNActivity.FORM_REQUEST_TYPE_DATA, SurveyIZNActivity.REQUEST_TYPE_INSERT);
                startActivity(intent);
            }
        });

        linkToSurvey1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fms.beginTransaction().replace(R.id.content_frames, new FragmentModul1101()).addToBackStack("3").commit();
                Intent intent = new Intent(getActivity(), SurveyKDZActivity.class);
                intent.putExtra(SurveyKDZActivity.FORM_REQUEST_TYPE_DATA, SurveyKDZActivity.REQUEST_TYPE_INSERT);
                startActivity(intent);
            }
        });
        return rootView;
    }

}
