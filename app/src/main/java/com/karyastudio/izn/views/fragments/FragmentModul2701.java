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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

public class FragmentModul2701 extends Fragment {


    private FragmentManager fms;
    private Button btnNext;
    private RadioGroup radioGroup;
    private EditText edt_iya_702;
    private Spinner edt_7031;
    private Spinner edt_7032;
    private Spinner edt_7041;
    private Spinner edt_7042;
    private EditText edt_701;

    public FragmentModul2701() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul2_701, container, false);
        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar8);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);


        radioGroup = rootView.findViewById(R.id.radioGrup702);
        edt_iya_702 = rootView.findViewById(R.id.edt_iya_702);
        edt_701 = rootView.findViewById(R.id.edt_701);
        edt_7031 = rootView.findViewById(R.id.edt_7031);
        edt_7032 = rootView.findViewById(R.id.edt_7032);
        edt_7041 = rootView.findViewById(R.id.edt_7041);
        edt_7042 = rootView.findViewById(R.id.edt_7042);


        String[] bulan = new String[]{
                "Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus",
                "September","Oktober","November","Desember"
        };

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,bulan
        );

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        edt_7031.setAdapter(spinnerArrayAdapter);
        edt_7032.setAdapter(spinnerArrayAdapter);
        edt_7041.setAdapter(spinnerArrayAdapter);
        edt_7042.setAdapter(spinnerArrayAdapter);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa702:
                        edt_iya_702.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak702:
                        edt_iya_702.setVisibility(View.GONE);
                        break;
                }
            }
        });
        btnNext = rootView.findViewById(R.id.btn_next_702);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul2801()).addToBackStack("10").commit();
            }
        });


        return rootView;
    }


    private void saveAndNext(View view){

        //Pref disini (701, 702yt, 702, 7031, 7032, 7041, 7042)di clear semua
        Prefs.remove(StaticStrings.M2_701);
        Prefs.remove(StaticStrings.M2_702yt);
        Prefs.remove(StaticStrings.M2_702);
        Prefs.remove(StaticStrings.M2_7031);
        Prefs.remove(StaticStrings.M2_7032);
        Prefs.remove(StaticStrings.M2_7041);
        Prefs.remove(StaticStrings.M2_7042);


        int selectedId1 = radioGroup.getCheckedRadioButtonId();
        RadioButton M2_702yt = (RadioButton) view.findViewById(selectedId1);

        Prefs.putString(StaticStrings.M2_702yt, M2_702yt.getText().toString());
        Prefs.putString(StaticStrings.M2_701, edt_701.getText().toString());

        Prefs.putString(StaticStrings.M2_7031, edt_7031.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M2_7032, edt_7032.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M2_7041, edt_7041.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M2_7042, edt_7042.getSelectedItem().toString());

        if (edt_iya_702.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_702, edt_iya_702.getText().toString());
        }
        Utils.Toast(getContext(),StaticStrings.TOAST_SUKSES_SIMPAN).show();
    }
}
