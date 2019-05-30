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
import android.widget.TextView;

import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

public class FragmentModul1401 extends Fragment implements Step {
    private FragmentManager fms;
    private TextView txt_401;
    private RadioGroup edt_401;
    private RadioGroup edt_402;
    private RadioGroup edt_403;
    private RadioGroup edt_404;
    private RadioGroup edt_405;
    private RadioGroup edt_406;
    private RadioGroup edt_407;


    private Button btnNext;
    private boolean isSuccess;
    private View rootView;

    public FragmentModul1401() {

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
        rootView = inflater.inflate(R.layout.fragment_modul1_401, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar6);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        btnNext = rootView.findViewById(R.id.btn_next1_401);
        edt_401 = rootView.findViewById(R.id.edt1_401);
        edt_402 = rootView.findViewById(R.id.edt1_402);
        edt_403 = rootView.findViewById(R.id.edt1_403);
        edt_404 = rootView.findViewById(R.id.edt1_404);
        edt_405 = rootView.findViewById(R.id.edt1_405);
        edt_406 = rootView.findViewById(R.id.edt1_406);
        edt_407 = rootView.findViewById(R.id.edt1_407);

//        btnNext.setVisibility(View.GONE);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
                // fms.beginTransaction().replace(R.id.content_frames, new FragmentModul1501()).addToBackStack("5").commit();
            }
        });

        return rootView;
    }


    private VerificationError saveAndNext(View view){

        try {
            Prefs.remove(StaticStrings.M1_401);
            Prefs.remove(StaticStrings.M1_402);
            Prefs.remove(StaticStrings.M1_403);
            Prefs.remove(StaticStrings.M1_404);
            Prefs.remove(StaticStrings.M1_405);
            Prefs.remove(StaticStrings.M1_406);
            Prefs.remove(StaticStrings.M1_407);


            int selectedId1 = edt_401.getCheckedRadioButtonId();
            RadioButton M1_401 = view.findViewById(selectedId1);
            Prefs.putString(StaticStrings.M1_401, M1_401.getText().toString());

            int selectedId2 = edt_402.getCheckedRadioButtonId();
            RadioButton M1_402 = view.findViewById(selectedId2);
            Prefs.putString(StaticStrings.M1_402, M1_402.getText().toString());

            int selectedId3 = edt_403.getCheckedRadioButtonId();
            RadioButton M1_403 = view.findViewById(selectedId3);
            Prefs.putString(StaticStrings.M1_403, M1_403.getText().toString());

            int selectedId4 = edt_404.getCheckedRadioButtonId();
            RadioButton M1_404 = view.findViewById(selectedId4);
            Prefs.putString(StaticStrings.M1_404, M1_404.getText().toString());

            int selectedId5 = edt_405.getCheckedRadioButtonId();
            RadioButton M1_405 = view.findViewById(selectedId5);
            Prefs.putString(StaticStrings.M1_405, M1_405.getText().toString());

            int selectedId6 = edt_406.getCheckedRadioButtonId();
            RadioButton M1_406 = view.findViewById(selectedId6);
            Prefs.putString(StaticStrings.M1_406, M1_406.getText().toString());

            int selectedId7 = edt_407.getCheckedRadioButtonId();
            RadioButton M1_407 = view.findViewById(selectedId7);
            Prefs.putString(StaticStrings.M1_407, M1_407.getText().toString());

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
        if (isSuccess) {
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
