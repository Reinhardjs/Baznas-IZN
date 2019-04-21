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
import android.widget.Toast;

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FragmentModul2901 extends Fragment {
    private FragmentManager fms;
    private Button btnNext;
    private EditText edt_901;

    public FragmentModul2901() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul2_901, container, false);
        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar10);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        edt_901 = rootView.findViewById(R.id.edt_901);
        btnNext = rootView.findViewById(R.id.btn_next_901);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext();
                fms= getActivity().getSupportFragmentManager();
                for(int i = 0; i < fms.getBackStackEntryCount(); ++i) {
                    fms.popBackStack();
                }
                fms.beginTransaction().replace(R.id.content_frames, new FragmentDashboard()).commit();
            }
        });
        return rootView;
    }

    private void saveAndNext(){
        Prefs.remove(StaticStrings.M2_901);
        Prefs.putString(StaticStrings.M2_901, edt_901.getText().toString());
        Prefs.putString(StaticStrings.M2_update_at, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(Calendar.getInstance().getTime()));

        if (Utils.isOnline(getActivity())){
            Utils.sendModul2(getActivity());
        }else{
            Toast.makeText(getActivity(),"Koneksi anda tidak ada, data anda akan dikirim setelah anda online", Toast.LENGTH_LONG).show();
            StaticStrings.readyToSendViaOffline = true;
        }
    }
}
