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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyIZNActivity;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class FragmentModul2401 extends Fragment implements BlockingStep {
    private FragmentManager fms;
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup3;
    private EasyTextInputLayout edt_iya_401;
    private EasyTextInputLayout edt_402;
    private EasyTextInputLayout edt_404;
    private EasyTextInputLayout edt_405;
    private EasyTextInputLayout edt_406;
    private TextView txt_401;

    private Button btnNext;

    private boolean isSuccess;
    private View rootView;

    private EasyForm easyForm;
    private RadioGroup radioGroup1;
    private EditText edt_401;

    public FragmentModul2401() {

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
        rootView = inflater.inflate(R.layout.fragment_modul2_401, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar5);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        radioGroup1 = rootView.findViewById(R.id.radioGrup401);
        radioGroup2 = rootView.findViewById(R.id.radioGrup4031);
        radioGroup3 = rootView.findViewById(R.id.radioGrup4032);

        txt_401 = rootView.findViewById(R.id.txt_401);
        edt_401 = rootView.findViewById(R.id.edt_401);
        edt_402 = rootView.findViewById(R.id.edt_402);
        edt_404 = rootView.findViewById(R.id.edt_404);
        edt_405 = rootView.findViewById(R.id.edt_405);
        edt_406 = rootView.findViewById(R.id.edt_406);
        easyForm = rootView.findViewById(R.id.easyForm);

        btnNext = rootView.findViewById(R.id.btn_next_401);
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

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa401:
                        edt_401.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak401:
                        edt_401.setVisibility(View.GONE);
                        break;
                }
            }
        });

        return rootView;
    }

    private VerificationError saveAndNext(View view){
        isSuccess = false;

        try {
            //Pref disini (401, 401yt,402, 403yt, 404, 405, 406) )di clear semua
            Prefs.remove(StaticStrings.M2_401);
            Prefs.remove(StaticStrings.M2_401yt);
            Prefs.remove(StaticStrings.M2_402);
            Prefs.remove(StaticStrings.M2_403yt1);
            Prefs.remove(StaticStrings.M2_403yt2);
            Prefs.remove(StaticStrings.M2_404);
            Prefs.remove(StaticStrings.M2_405);
            Prefs.remove(StaticStrings.M2_406);

            int selectedId = radioGroup1.getCheckedRadioButtonId();
            RadioButton M2_401 = view.findViewById(selectedId);
            int selectedId2 = radioGroup2.getCheckedRadioButtonId();
            RadioButton M2_403yt1 = (RadioButton) view.findViewById(selectedId2);
            int selectedId3 = radioGroup3.getCheckedRadioButtonId();
            RadioButton M2_403yt2 = (RadioButton) view.findViewById(selectedId3);

            if (radioGroup1.getVisibility() == View.VISIBLE) {
                Prefs.putString(StaticStrings.M2_401yt, M2_401.getText().toString());
            } else {
                Prefs.putString(StaticStrings.M2_401yt, "-1. Nochoice");
            }

            if (edt_401.getVisibility() == View.VISIBLE){
                if (edt_401.getText().toString().isEmpty()){
                    throw new Exception();
                }

                Prefs.putString(StaticStrings.M2_401, edt_401.getText().toString());
            } else {
                Prefs.putString(StaticStrings.M2_401, "0");
            }

            Prefs.putString(StaticStrings.M2_402, edt_402.getEditText().getText().toString());
            Prefs.putString(StaticStrings.M2_403yt1, M2_403yt1.getText().toString());
            Prefs.putString(StaticStrings.M2_403yt2, M2_403yt2.getText().toString());
            Prefs.putString(StaticStrings.M2_404, edt_404.getEditText().getText().toString());
            Prefs.putString(StaticStrings.M2_405, edt_405.getEditText().getText().toString());
            Prefs.putString(StaticStrings.M2_406, edt_406.getEditText().getText().toString());
            Utils.toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN);

            easyForm.validate();
            if (!easyForm.isValid()){
                throw new Exception();
            }

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

        if (FragmentModul2101.gotos.equals("true")){
            txt_401.setVisibility(View.GONE);
            radioGroup1.setVisibility(View.GONE);
            radioGroup1.check(R.id.radioTidak401);
        } else {
            edt_401.setVisibility(View.VISIBLE);
            radioGroup1.setVisibility(View.VISIBLE);

            ((RadioGroup)rootView.findViewById(R.id.radioGrup401)).clearCheck();
            String m2_401_yt = Prefs.getString(StaticStrings.M2_401yt, "");
            if (!m2_401_yt.isEmpty() && !m2_401_yt.equals("kosong")){
                if (m2_401_yt.equals("1. Ada")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa401)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak401)).setChecked(true);
                }
            }

            String m2_401 = Prefs.getString(StaticStrings.M2_401, "");
            if (!m2_401.isEmpty()){
                edt_401.setText(m2_401);
            }
        }

        // Setiap ada pilihan radio button, yang jenisnya dua pilihan.
        // Selalu check bahwa nilainya tidak sama dengan "kosong"
        // kalau "kosong" nanti selalu kena ke logika else nya.


        String m2_402 = Prefs.getString(StaticStrings.M2_402, "");
        if (!m2_402.isEmpty()){
            edt_402.getEditText().setText(m2_402);
        }

        String m2_404 = Prefs.getString(StaticStrings.M2_404, "");
        if (!m2_404.isEmpty()){
            edt_404.getEditText().setText(m2_404);
        }

        ((RadioGroup)rootView.findViewById(R.id.radioGrup4031)).clearCheck();
        String m2_403_yt1 = Prefs.getString(StaticStrings.M2_403yt1, "");
        if (!m2_403_yt1.isEmpty() && !m2_403_yt1.equals("kosong"))
        if (m2_403_yt1.equals("1. Ada")){
            ((RadioButton)rootView.findViewById(R.id.radioYa4031)).setChecked(true);
        } else {
            ((RadioButton)rootView.findViewById(R.id.radioTidak4031)).setChecked(true);
        }

        ((RadioGroup)rootView.findViewById(R.id.radioGrup4032)).clearCheck();
        String m2_403_yt2 = Prefs.getString(StaticStrings.M2_403yt2, "");
        if (!m2_403_yt2.isEmpty() && !m2_403_yt2.equals("kosong"))
        if (m2_403_yt2.equals("1. Ada")){
            ((RadioButton)rootView.findViewById(R.id.radioYa4032)).setChecked(true);
        } else {
            ((RadioButton)rootView.findViewById(R.id.radioTidak4032)).setChecked(true);
        }

        String m2_405 = Prefs.getString(StaticStrings.M2_405, "");
        if (!m2_405.isEmpty()){
            edt_405.getEditText().setText(m2_405);
        }

        String m2_406 = Prefs.getString(StaticStrings.M2_406, "");
        if (!m2_406.isEmpty()){
            edt_406.getEditText().setText(m2_406);
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

            if (FragmentModul2101.gotos.equals("true")){
                SurveyIZNActivity.getInstance().mStepperLayout.setCurrentStepPosition(0);
            } else {
                callback.goToPrevStep();
            }

        } else {
            onError(verificationError);
        }
    }
}
