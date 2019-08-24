package com.karyastudio.izn.views.fragments.modul1;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
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
import com.karyastudio.izn.model.api.datakdz.KajianDampakZakatPojo;
import com.karyastudio.izn.utils.FormError;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyKDZActivity;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class FragmentModul1701 extends Fragment implements BlockingStep {
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

        Utils.log("FRAGMENT PAGE 7 ONCREATEVIEW");

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
                VerificationError verificationError = saveAndNext(rootView);
                if (verificationError != null){
                    String message = verificationError.getErrorMessage();
                    Utils.toast(getContext(), message);
                }
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

            int selectedId2 = edt_702.getCheckedRadioButtonId();
            RadioButton M1_702 = view.findViewById(selectedId2);

            int selectedId3 = edt_703.getCheckedRadioButtonId();
            RadioButton M1_703 = view.findViewById(selectedId3);


            if (selectedId1 == -1)
                throw new FormError("Opsi pilihan 1 tidak boleh kosong.");
            else if (selectedId2 == -1)
                throw new FormError("Opsi pilihan 2 tidak boleh kosong.");
            else if (selectedId3 == -1)
                throw new FormError("Opsi pilihan 3 tidak boleh kosong.");


            Prefs.putString(StaticStrings.M1_701, M1_701.getText().toString());
            Prefs.putString(StaticStrings.M1_702, M1_702.getText().toString());
            Prefs.putString(StaticStrings.M1_703, M1_703.getText().toString());
        } catch (FormError e){
            return new VerificationError(e.getMessage() + " Mohon lengkapi form pada halaman ini");
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

        VerificationError verificationError = saveAndNext(rootView);

        if (verificationError == null) {
            isSuccess = true;
            Utils.toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN);
        } else {
            isSuccess = false;
        }

        return verificationError;
    }

    @Override
    public void onSelected() {
        Utils.log("FRAGMENT PAGE 7 ONSELECTED");

        isSuccess = false;

        new Handler().postDelayed(() -> {

            //if (SurveyKDZActivity.data != null) {
                KajianDampakZakatPojo data = SurveyKDZActivity.data;

            // Setiap ada pilihan radio button, yang jenisnya dua pilihan.
            // Selalu check bahwa nilainya tidak sama dengan "kosong"
            // kalau "kosong" nanti selalu kena ke logika else nya.

                ((RadioGroup)rootView.findViewById(R.id.edt1_701)).clearCheck();
                String m1_701 = Prefs.getString(StaticStrings.M1_701, "");
                if (!m1_701.isEmpty() && !m1_701.equals("kosong"))
                if (m1_701.equals("1. Ya")){
                    ((RadioButton) rootView.findViewById(R.id.radioYa701)).setChecked(true);
                } else {
                    ((RadioButton) rootView.findViewById(R.id.radioTidak701)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_702)).clearCheck();
                String m1_702 = Prefs.getString(StaticStrings.M1_702, "");
                if (!m1_702.isEmpty() && !m1_702.equals("kosong"))
                if (m1_702.equals("1. Ya")){
                    ((RadioButton) rootView.findViewById(R.id.radioYa702)).setChecked(true);
                } else {
                    ((RadioButton) rootView.findViewById(R.id.radioTidak702)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_703)).clearCheck();
                String m1_703 = Prefs.getString(StaticStrings.M1_703, "");
                if (!m1_703.isEmpty() && !m1_703.equals("kosong"))
                if (m1_703.equals("1. Ya")){
                    ((RadioButton) rootView.findViewById(R.id.radioYa703)).setChecked(true);
                } else {
                    ((RadioButton) rootView.findViewById(R.id.radioTidak703)).setChecked(true);
                }
            //}

        }, 200);
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        Utils.toast(getContext(), error.getErrorMessage());
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.goToNextStep();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

        VerificationError verificationError = verifyStep();

        if (verificationError == null) {
            callback.goToPrevStep();
        } else {
            onError(verificationError);
        }
    }
}
