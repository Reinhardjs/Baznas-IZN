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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

public class FragmentModul2601  extends Fragment {

    private FragmentManager fms;
    private Button btnNext;
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup3;
    private RadioGroup radioGroup4;
    private EditText edt_iya_603;
    private EditText edt_iya_604;

    public FragmentModul2601() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul2_601, container, false);
        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar7);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);


        radioGroup1 = rootView.findViewById(R.id.radioGrup601);
        radioGroup2 = rootView.findViewById(R.id.radioGrup602);
        radioGroup3 = rootView.findViewById(R.id.radioGrup603);
        radioGroup4 = rootView.findViewById(R.id.radioGrup604);

        edt_iya_603 = rootView.findViewById(R.id.edt_iya_603);
        edt_iya_604 = rootView.findViewById(R.id.edt_iya_604);
        btnNext = rootView.findViewById(R.id.btn_next_601);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul2701()).addToBackStack("9").commit();
            }
        });



        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa603:
                        edt_iya_603.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak603:
                        edt_iya_603.setVisibility(View.GONE);
                        break;
                }
            }
        });

        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa604:
                        edt_iya_604.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak604:
                        edt_iya_604.setVisibility(View.GONE);
                        break;
                }
            }
        });


        return rootView;
    }


    private void saveAndNext(View view){

        //Pref disini (601yt, 602yt, 603, 603yt, 604, 604yt)di clear semua
        Prefs.remove(StaticStrings.M2_601yt);
        Prefs.remove(StaticStrings.M2_602yt);
        Prefs.remove(StaticStrings.M2_603yt);
        Prefs.remove(StaticStrings.M2_604yt);
        Prefs.remove(StaticStrings.M2_603);
        Prefs.remove(StaticStrings.M2_604);



        int selectedId1 = radioGroup1.getCheckedRadioButtonId();
        RadioButton M2_601yt = (RadioButton) view.findViewById(selectedId1);
        int selectedId2 = radioGroup2.getCheckedRadioButtonId();
        RadioButton M2_602yt = (RadioButton) view.findViewById(selectedId2);
        int selectedId3 = radioGroup3.getCheckedRadioButtonId();
        RadioButton M2_603yt = (RadioButton) view.findViewById(selectedId3);
        int selectedId4 = radioGroup4.getCheckedRadioButtonId();
        RadioButton M2_604yt = (RadioButton) view.findViewById(selectedId4);

        Prefs.putString(StaticStrings.M2_601yt, M2_601yt.getText().toString());
        Prefs.putString(StaticStrings.M2_602yt, M2_602yt.getText().toString());
        Prefs.putString(StaticStrings.M2_603yt, M2_603yt.getText().toString());
        Prefs.putString(StaticStrings.M2_604yt, M2_604yt.getText().toString());

        if (edt_iya_603.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_603, edt_iya_603.getText().toString());
        }
        if (edt_iya_604.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_604, edt_iya_604.getText().toString());
        }
        Utils.Toast(getContext(),StaticStrings.TOAST_SUKSES_SIMPAN).show();
    }
}
