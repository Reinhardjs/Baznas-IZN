package com.karyastudio.izn.views.fragments.modul2;

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

import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class FragmentModul2601  extends Fragment implements BlockingStep {

    private FragmentManager fms;
    private Button btnNext;
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup3;
    private RadioGroup radioGroup4;
    private EasyTextInputLayout edt_iya_603;
    private EasyTextInputLayout edt_iya_604;

    private boolean isSuccess;
    private View rootView;

    private EasyForm easyForm1, easyForm2;

    public FragmentModul2601() {

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
        rootView = inflater.inflate(R.layout.fragment_modul2_601, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar7);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);


        easyForm1 = rootView.findViewById(R.id.easyform_iya_603);
        easyForm2 = rootView.findViewById(R.id.easyform_iya_604);

        radioGroup1 = rootView.findViewById(R.id.radioGrup601);
        radioGroup2 = rootView.findViewById(R.id.radioGrup602);
        radioGroup3 = rootView.findViewById(R.id.radioGrup603);
        radioGroup4 = rootView.findViewById(R.id.radioGrup604);

        edt_iya_603 = rootView.findViewById(R.id.edt_iya_603);
        edt_iya_604 = rootView.findViewById(R.id.edt_iya_604);

        btnNext = rootView.findViewById(R.id.btn_next_601);
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



        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa603:
                        edt_iya_603.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak603:
                        edt_iya_603.setVisibility(View.GONE);
                        break;
                }
            }
        });

        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa604:
                        edt_iya_604.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak604:
                        edt_iya_604.setVisibility(View.GONE);
                        break;
                }
            }
        });


        return rootView;
    }


    private VerificationError saveAndNext(View view){
        isSuccess = false;

        try {

            //Pref disini (601yt, 602yt, 603, 603yt, 604, 604yt)di clear semua
            Prefs.remove(StaticStrings.M2_601yt);
            Prefs.remove(StaticStrings.M2_602yt);
            Prefs.remove(StaticStrings.M2_603yt);
            Prefs.remove(StaticStrings.M2_604yt);
            Prefs.remove(StaticStrings.M2_603);
            Prefs.remove(StaticStrings.M2_604);


            int selectedId1 = radioGroup1.getCheckedRadioButtonId();
            RadioButton M2_601yt = (RadioButton) view.findViewById(selectedId1);
            int selectedId2 = radioGroup2.getCheckedRadioButtonId();
            RadioButton M2_602yt = (RadioButton) view.findViewById(selectedId2);
            int selectedId3 = radioGroup3.getCheckedRadioButtonId();
            RadioButton M2_603yt = (RadioButton) view.findViewById(selectedId3);
            int selectedId4 = radioGroup4.getCheckedRadioButtonId();
            RadioButton M2_604yt = (RadioButton) view.findViewById(selectedId4);

            Prefs.putString(StaticStrings.M2_601yt, M2_601yt.getText().toString());
            Prefs.putString(StaticStrings.M2_602yt, M2_602yt.getText().toString());
            Prefs.putString(StaticStrings.M2_603yt, M2_603yt.getText().toString());
            Prefs.putString(StaticStrings.M2_604yt, M2_604yt.getText().toString());

            if (edt_iya_603.getVisibility() == View.VISIBLE) {
                Prefs.putString(StaticStrings.M2_603, edt_iya_603.getEditText().getText().toString());

                easyForm1.validate();
                if (!easyForm1.isValid()){
                    throw new Exception();
                }
            } else {
                Prefs.putString(StaticStrings.M2_603, "");
            }

            if (edt_iya_604.getVisibility() == View.VISIBLE) {
                Prefs.putString(StaticStrings.M2_604, edt_iya_604.getEditText().getText().toString());

                easyForm2.validate();
                if (!easyForm2.isValid()){
                    throw new Exception();
                }
            } else {
                Prefs.putString(StaticStrings.M2_604, "");
            }

            Utils.toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN);
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
        } else {
            isSuccess = false;
        }

        return verificationError;
    }

    @Override
    public void onSelected() {
        isSuccess = false;

        // Setiap ada pilihan radio button, yang jenisnya dua pilihan.
        // Selalu check bahwa nilainya tidak sama dengan "kosong"
        // kalau "kosong" nanti selalu kena ke logika else nya.

        ((RadioGroup)rootView.findViewById(R.id.radioGrup601)).clearCheck();
        String m2_601_yt = Prefs.getString(StaticStrings.M2_601yt, "");
        if (!m2_601_yt.isEmpty() && !m2_601_yt.equals("kosong"))
            if (m2_601_yt.equals("1. Ada")){
                ((RadioButton)rootView.findViewById(R.id.radioYa601)).setChecked(true);
            } else {
                ((RadioButton)rootView.findViewById(R.id.radioTidak601)).setChecked(true);
            }

        ((RadioGroup)rootView.findViewById(R.id.radioGrup602)).clearCheck();
        String m2_602_yt = Prefs.getString(StaticStrings.M2_602yt, "");
        if (!m2_602_yt.isEmpty() && !m2_602_yt.equals("kosong"))
            if (m2_602_yt.equals("1. Ada")){
                ((RadioButton)rootView.findViewById(R.id.radioYa602)).setChecked(true);
            } else {
                ((RadioButton)rootView.findViewById(R.id.radioTidak602)).setChecked(true);
            }

        ((RadioGroup)rootView.findViewById(R.id.radioGrup603)).clearCheck();
        String m2_603_yt = Prefs.getString(StaticStrings.M2_603yt, "");
        if (!m2_603_yt.isEmpty() && !m2_603_yt.equals("kosong"))
            if (m2_603_yt.equals("1. Ada")){
                ((RadioButton)rootView.findViewById(R.id.radioYa603)).setChecked(true);
            } else {
                ((RadioButton)rootView.findViewById(R.id.radioTidak603)).setChecked(true);
            }

        String m2_603 = Prefs.getString(StaticStrings.M2_603, "");
        if (!m2_603.isEmpty() && !m2_603.equals("kosong")){
            edt_iya_603.getEditText().setText(m2_603);
        }

        ((RadioGroup)rootView.findViewById(R.id.radioGrup604)).clearCheck();
        String m2_604_yt = Prefs.getString(StaticStrings.M2_604yt, "");
        if (!m2_604_yt.isEmpty() && !m2_604_yt.equals("kosong"))
            if (m2_604_yt.equals("1. Ada")){
                ((RadioButton)rootView.findViewById(R.id.radioYa604)).setChecked(true);
            } else {
                ((RadioButton)rootView.findViewById(R.id.radioTidak604)).setChecked(true);
            }

        String m2_604 = Prefs.getString(StaticStrings.M2_604, "");
        if (!m2_604.isEmpty() && !m2_604.equals("kosong")){
            Utils.log("M2_604 2 : " + m2_604);
            edt_iya_604.getEditText().setText(m2_604);
        }
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
