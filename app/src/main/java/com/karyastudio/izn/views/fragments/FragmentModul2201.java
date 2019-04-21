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

public class FragmentModul2201 extends Fragment {
    private FragmentManager fms;
    private RadioGroup radioGroup;
    private EditText edt_iya_201;

    private Button btnNext;

    public FragmentModul2201() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul2_201, container, false);
        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar3);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        radioGroup = rootView.findViewById(R.id.radioGrup201);
        edt_iya_201 = rootView.findViewById(R.id.edt_iya_201);
        btnNext = rootView.findViewById(R.id.btn_next_201);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa201:
                        edt_iya_201.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak201:
                        edt_iya_201.setVisibility(View.GONE);
                        break;

                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul2301()).addToBackStack("5").commit();
            }
        });

        return rootView;
    }


    private void saveAndNext(View view){
        //Pref disini (201, 201yt) )di clear semua
        Prefs.remove(StaticStrings.M2_201);
        Prefs.remove(StaticStrings.M2_201yt);

        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton M2_201yt = (RadioButton) view.findViewById(selectedId);

        Prefs.putString(StaticStrings.M2_201yt, M2_201yt.getText().toString());
        if (edt_iya_201.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_201, edt_iya_201.getText().toString());
        }
        Utils.Toast(getContext(),StaticStrings.TOAST_SUKSES_SIMPAN).show();
      }
}
