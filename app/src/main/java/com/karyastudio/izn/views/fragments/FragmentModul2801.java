package com.karyastudio.izn.views.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.pixplicity.easyprefs.library.Prefs;

public class FragmentModul2801 extends Fragment {

    private FragmentManager fms;
    private Button btnNext;
    private RadioGroup radioGroup;
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup3;
    private RadioGroup radioGroup4;
    private RadioGroup radioGroup5;
    private TextView txt_802;
    private LinearLayout linearLayout;
    public FragmentModul2801() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul2_801, container, false);
        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar9);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        radioGroup = rootView.findViewById(R.id.radioGrup801);
        radioGroup2 = rootView.findViewById(R.id.radioGrup802);
        radioGroup3 = rootView.findViewById(R.id.radioGrup8022);
        radioGroup4 = rootView.findViewById(R.id.radioGrup803);
        radioGroup5 = rootView.findViewById(R.id.radioGrup804);

        txt_802 = rootView.findViewById(R.id.txt_802);
        linearLayout = rootView.findViewById(R.id.ll_801);

        btnNext = rootView.findViewById(R.id.btn_next_801);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul2901()).addToBackStack("11").commit();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa801:
                        txt_802.setVisibility(View.VISIBLE);
                        radioGroup2.setVisibility(View.VISIBLE);
                        radioGroup3.setVisibility(View.VISIBLE);
                        radioGroup4.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak801:
                        txt_802.setVisibility(View.GONE);
                        radioGroup2.setVisibility(View.GONE);
                        radioGroup3.setVisibility(View.GONE);
                        radioGroup4.setVisibility(View.GONE);
                        break;
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa802:
                        radioGroup3.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak802:
                        radioGroup3.setVisibility(View.GONE);
                        break;
                }
            }
        });

        return rootView;
    }


    private void saveAndNext(View view){

        Prefs.remove(StaticStrings.M2_801yt);
        Prefs.remove(StaticStrings.M2_802yt1);
        Prefs.remove(StaticStrings.M2_802yt2);
        Prefs.remove(StaticStrings.M2_803yt);
        Prefs.remove(StaticStrings.M2_804yt);



        int selectedId1 = radioGroup.getCheckedRadioButtonId();
        RadioButton M2_801yt = (RadioButton) view.findViewById(selectedId1);
        int selectedId2 = radioGroup2.getCheckedRadioButtonId();
        RadioButton M2_802yt1 = (RadioButton) view.findViewById(selectedId2);
        int selectedId3 = radioGroup3.getCheckedRadioButtonId();
        RadioButton M2_802yt2 = (RadioButton) view.findViewById(selectedId3);
        int selectedId4 = radioGroup4.getCheckedRadioButtonId();
        RadioButton M2_803yt = (RadioButton) view.findViewById(selectedId4);
        int selectedId5 = radioGroup4.getCheckedRadioButtonId();
        RadioButton M2_804yt = (RadioButton) view.findViewById(selectedId5);


        if (radioGroup2.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_802yt1, M2_802yt1.getText().toString());
        }
        if (radioGroup3.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_802yt2, M2_802yt2.getText().toString());
        }

        if (radioGroup4.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_803yt, M2_803yt.getText().toString());
        }
        Prefs.putString(StaticStrings.M2_801yt, M2_801yt.getText().toString());
        Prefs.putString(StaticStrings.M2_804yt, M2_804yt.getText().toString());

    }
}

