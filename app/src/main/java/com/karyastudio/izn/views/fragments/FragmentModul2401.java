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

public class FragmentModul2401 extends Fragment {
    private FragmentManager fms;
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup3;
    private EditText edt_iya_401;
    private EditText edt_402;
    private EditText edt_404;
    private EditText edt_405;
    private EditText edt_406;

    private Button btnNext;

    public FragmentModul2401() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul2_401, container, false);
        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar5);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        radioGroup2 = rootView.findViewById(R.id.radioGrup4031);
        radioGroup3 = rootView.findViewById(R.id.radioGrup4032);

        edt_402 = rootView.findViewById(R.id.edt_402);

        edt_404 = rootView.findViewById(R.id.edt_404);
        edt_405 = rootView.findViewById(R.id.edt_405);
        edt_406 = rootView.findViewById(R.id.edt_406);

        btnNext = rootView.findViewById(R.id.btn_next_401);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul2501()).addToBackStack("7").commit();
            }
        });


        return rootView;
    }

    private void saveAndNext(View view){
        //Pref disini (401, 401yt,402, 403yt, 404, 405, 406) )di clear semua
        Prefs.remove(StaticStrings.M2_401);
        Prefs.remove(StaticStrings.M2_401yt);
        Prefs.remove(StaticStrings.M2_402);
        Prefs.remove(StaticStrings.M2_403yt1);
        Prefs.remove(StaticStrings.M2_403yt2);
        Prefs.remove(StaticStrings.M2_404);
        Prefs.remove(StaticStrings.M2_405);
        Prefs.remove(StaticStrings.M2_406);

        int selectedId2 = radioGroup2.getCheckedRadioButtonId();
        RadioButton M2_403yt1 = (RadioButton) view.findViewById(selectedId2);
        int selectedId3 = radioGroup2.getCheckedRadioButtonId();
        RadioButton M2_403yt2 = (RadioButton) view.findViewById(selectedId3);


        Prefs.putString(StaticStrings.M2_403yt1, M2_403yt1.getText().toString());
        Prefs.putString(StaticStrings.M2_403yt2, M2_403yt2.getText().toString());

//
        Prefs.putString(StaticStrings.M2_402, edt_402.getText().toString());
        Prefs.putString(StaticStrings.M2_404, edt_404.getText().toString());
        Prefs.putString(StaticStrings.M2_405, edt_405.getText().toString());
        Prefs.putString(StaticStrings.M2_406, edt_406.getText().toString());
        Utils.Toast(getContext(),StaticStrings.TOAST_SUKSES_SIMPAN).show();
    }
}
