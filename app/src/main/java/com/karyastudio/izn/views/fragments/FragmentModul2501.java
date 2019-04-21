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

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

public class FragmentModul2501 extends Fragment {
    private FragmentManager fms;
    private Button btnNext;
    private EditText edt_501;
    private EditText edt_502;

    public FragmentModul2501() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul2_501, container, false);
        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar6);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        edt_501 = rootView.findViewById(R.id.edt_501);
        edt_502 = rootView.findViewById(R.id.edt_502);
        btnNext = rootView.findViewById(R.id.btn_next_501);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul2601()).addToBackStack("8").commit();
            }
        });
        return rootView;
    }
    private void saveAndNext(View view){
        //Pref disini (501, 502) )di clear semua
        Prefs.remove(StaticStrings.M2_501);
        Prefs.remove(StaticStrings.M2_502);
        Prefs.putString(StaticStrings.M2_501, edt_501.getText().toString());
        Prefs.putString(StaticStrings.M2_502, edt_502.getText().toString());
        Utils.Toast(getContext(),StaticStrings.TOAST_SUKSES_SIMPAN).show();
    }
}
