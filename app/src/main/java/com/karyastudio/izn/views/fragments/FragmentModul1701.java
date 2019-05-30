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

import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

public class FragmentModul1701 extends Fragment implements Step {
    private FragmentManager fms;
    private RadioGroup edt_701;
    private RadioGroup edt_702;
    private RadioGroup edt_703;
    private Button btnNext;
    private boolean isSuccess;
    private View rootView;

    public FragmentModul1701() {

    }

    // method ini hanya dipanggil sekali untuk fragment ini
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // jangan hapus fragment ini saat activity dibuat ulang.
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_modul1_701, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar6);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        btnNext = rootView.findViewById(R.id.btn_next1_701);
        edt_701 = rootView.findViewById(R.id.edt1_701);
        edt_702 = rootView.findViewById(R.id.edt1_702);
        edt_703 = rootView.findViewById(R.id.edt1_703);

//        btnNext.setVisibility(View.GONE);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
//                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul1801()).addToBackStack("8").commit();
            }
        });

        return rootView;
    }


    private VerificationError saveAndNext(View view){

        try {
            Prefs.remove(StaticStrings.M1_701);
            Prefs.remove(StaticStrings.M1_702);
            Prefs.remove(StaticStrings.M1_703);

            int selectedId1 = edt_701.getCheckedRadioButtonId();
            RadioButton M1_701 = view.findViewById(selectedId1);
            Prefs.putString(StaticStrings.M1_701, M1_701.getText().toString());

            int selectedId2 = edt_702.getCheckedRadioButtonId();
            RadioButton M1_702 = view.findViewById(selectedId2);
            Prefs.putString(StaticStrings.M1_702, M1_702.getText().toString());

            int selectedId3 = edt_703.getCheckedRadioButtonId();
            RadioButton M1_703 = view.findViewById(selectedId3);
            Prefs.putString(StaticStrings.M1_703, M1_703.getText().toString());

            isSuccess = true;
            Utils.Toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN).show();
        } catch (Exception e){
            return new VerificationError("Mohon lengkapi form pada halaman ini");
        }

        return null;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (isSuccess){
            return null;
        }

        return saveAndNext(rootView);
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
