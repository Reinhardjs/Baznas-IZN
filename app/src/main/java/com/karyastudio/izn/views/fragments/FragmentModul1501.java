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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.pixplicity.easyprefs.library.Prefs;

public class FragmentModul1501 extends Fragment {
    private FragmentManager fms;

    private RadioGroup edt_501;
    private RadioGroup edt_502;
    private RadioGroup edt_503;
    private RadioGroup edt_504;
    private RadioGroup edt_505;
    private RadioGroup edt_506;
    private RadioGroup edt_507;
    private RadioGroup edt_508;
    private RadioGroup edt_509;
    private RadioGroup edt_510;

    private Button btnNext;

    public FragmentModul1501() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul1_501, container, false);
        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar6);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        edt_501 = rootView.findViewById(R.id.edt1_501);
        edt_502 = rootView.findViewById(R.id.edt1_502);
        edt_503 = rootView.findViewById(R.id.edt1_503);
        edt_504 = rootView.findViewById(R.id.edt1_504);
        edt_505 = rootView.findViewById(R.id.edt1_505);
        edt_506 = rootView.findViewById(R.id.edt1_506);
        edt_507 = rootView.findViewById(R.id.edt1_507);
        edt_508 = rootView.findViewById(R.id.edt1_508);
        edt_509 = rootView.findViewById(R.id.edt1_509);
        edt_510 = rootView.findViewById(R.id.edt1_510);

        btnNext = rootView.findViewById(R.id.btn_next1_501);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul1601()).addToBackStack("6").commit();
            }
        });

        return rootView;
    }


    private void saveAndNext(View view){
        Prefs.remove(StaticStrings.M1_501);
        Prefs.remove(StaticStrings.M1_502);
        Prefs.remove(StaticStrings.M1_503);
        Prefs.remove(StaticStrings.M1_504);
        Prefs.remove(StaticStrings.M1_505);
        Prefs.remove(StaticStrings.M1_506);
        Prefs.remove(StaticStrings.M1_507);
        Prefs.remove(StaticStrings.M1_508);
        Prefs.remove(StaticStrings.M1_509);
        Prefs.remove(StaticStrings.M1_510);


        int selectedId1 = edt_501.getCheckedRadioButtonId();
        RadioButton M1_501 = view.findViewById(selectedId1);
        Prefs.putString(StaticStrings.M1_501, M1_501.getText().toString());

        int selectedId2 = edt_502.getCheckedRadioButtonId();
        RadioButton M1_502 = view.findViewById(selectedId2);
        Prefs.putString(StaticStrings.M1_502, M1_502.getText().toString());

        int selectedId3 = edt_503.getCheckedRadioButtonId();
        RadioButton M1_503 = view.findViewById(selectedId3);
        Prefs.putString(StaticStrings.M1_503, M1_503.getText().toString());

        int selectedId4 = edt_504.getCheckedRadioButtonId();
        RadioButton M1_504 = view.findViewById(selectedId4);
        Prefs.putString(StaticStrings.M1_504, M1_504.getText().toString());

        int selectedId5 = edt_505.getCheckedRadioButtonId();
        RadioButton M1_505 = view.findViewById(selectedId5);
        Prefs.putString(StaticStrings.M1_505, M1_505.getText().toString());

        int selectedId6 = edt_506.getCheckedRadioButtonId();
        RadioButton M1_506 = view.findViewById(selectedId6);
        Prefs.putString(StaticStrings.M1_506, M1_506.getText().toString());

        int selectedId7 = edt_507.getCheckedRadioButtonId();
        RadioButton M1_507 = view.findViewById(selectedId7);
        Prefs.putString(StaticStrings.M1_507, M1_507.getText().toString());


        int selectedId8 = edt_508.getCheckedRadioButtonId();
        RadioButton M1_508 = view.findViewById(selectedId8);
        Prefs.putString(StaticStrings.M1_508, M1_508.getText().toString());


        int selectedId9 = edt_509.getCheckedRadioButtonId();
        RadioButton M1_509 = view.findViewById(selectedId9);
        Prefs.putString(StaticStrings.M1_509, M1_509.getText().toString());


        int selectedId10 = edt_510.getCheckedRadioButtonId();
        RadioButton M1_510 = view.findViewById(selectedId10);
        Prefs.putString(StaticStrings.M1_510, M1_510.getText().toString());
    }
}