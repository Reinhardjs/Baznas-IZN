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
import android.widget.TextView;

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

public class FragmentModul2301 extends Fragment {
    private FragmentManager fms;
    private RadioGroup radioGroup;
    private RadioGroup radioGroup2;
    private TextView text1;
    private TextView text2;

    private EditText edt_iya_301;
    private EditText edt_iya_3012;
    private Button btnNext;

    public FragmentModul2301() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul2_301, container, false);
        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar4);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        text1 = rootView.findViewById(R.id.txt_301);
        text2 = rootView.findViewById(R.id.txt_302);
        radioGroup = rootView.findViewById(R.id.radioGrup301);
        radioGroup2 = rootView.findViewById(R.id.radioGrup3012);
        edt_iya_301 = rootView.findViewById(R.id.edt_iya_301);
        edt_iya_3012 = rootView.findViewById(R.id.edt_iya_3012);
        btnNext = rootView.findViewById(R.id.btn_next_301);
        if (Prefs.getString(StaticStrings.M2_101,"none").equals("1. BAZNAS Pusat")){
            text1.setText("1. Alokasi APBN untuk BAZNAS Pusat /Provinsi/Kabupaten/Kota 2 tahun terakhir");
            text2.setText("2. Alokasi APBN untuk BAZNAS Pusat /Provinsi/Kabupaten/Kota 1 tahun terakhir");
        }else{

            text1.setText("1. Alokasi APBD untuk BAZNAS Pusat /Provinsi/Kabupaten/Kota 2 tahun terakhir");
            text2.setText("2. Alokasi APBD untuk BAZNAS Pusat /Provinsi/Kabupaten/Kota 1 tahun terakhir");
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa301:
                        edt_iya_301.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak301:
                        edt_iya_301.setVisibility(View.GONE);
                        break;

                }
            }
        });
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa3012:
                        edt_iya_3012.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak3012:
                        edt_iya_3012.setVisibility(View.GONE);
                        break;

                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul2401()).addToBackStack("6").commit();
            }
        });


        return rootView;
    }

    private void saveAndNext(View view){
        //Pref disini (301, 301yt,302, 302yt) )di clear semua
        Prefs.remove(StaticStrings.M2_301);
        Prefs.remove(StaticStrings.M2_301yt);
        Prefs.remove(StaticStrings.M2_302);
        Prefs.remove(StaticStrings.M2_302yt);

        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton M2_301yt = (RadioButton) view.findViewById(selectedId);
        int selectedId2 = radioGroup2.getCheckedRadioButtonId();
        RadioButton M2_302yt = (RadioButton) view.findViewById(selectedId2);

        Prefs.putString(StaticStrings.M2_301yt, M2_301yt.getText().toString());
        Prefs.putString(StaticStrings.M2_302yt, M2_302yt.getText().toString());

        if (edt_iya_301.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_301, edt_iya_301.getText().toString());
        }
        if (edt_iya_3012.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_302, edt_iya_3012.getText().toString());
        }
        Utils.Toast(getContext(),StaticStrings.TOAST_SUKSES_SIMPAN).show();
    }

}
