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

public class FragmentModul2201 extends Fragment implements BlockingStep {
    private FragmentManager fms;
    private RadioGroup radioGroup;
    private EasyTextInputLayout edt_iya_201;

    private Button btnNext;

    private boolean isSuccess;
    private View rootView;

    private EasyForm easyForm;

    public FragmentModul2201() {

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
        rootView = inflater.inflate(R.layout.fragment_modul2_201, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar3);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        easyForm = rootView.findViewById(R.id.easyForm);
        radioGroup = rootView.findViewById(R.id.radioGrup201);
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

        edt_iya_201 = rootView.findViewById(R.id.edt_iya_201);
        btnNext = rootView.findViewById(R.id.btn_next_201);
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
        isSuccess = false;

        try {
            //Pref disini (201, 201yt) )di clear semua
            Prefs.remove(StaticStrings.M2_201);
            Prefs.remove(StaticStrings.M2_201yt);

            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton M2_201yt = (RadioButton) view.findViewById(selectedId);

            Prefs.putString(StaticStrings.M2_201yt, M2_201yt.getText().toString());
            if (edt_iya_201.getVisibility() == View.VISIBLE) {
                Prefs.putString(StaticStrings.M2_201, edt_iya_201.getEditText().getText().toString());

                easyForm.validate();
                if (!easyForm.isValid()){
                    return new VerificationError("Mohon lengkapi form pada halaman ini");
                }
            } else {
                Prefs.putString(StaticStrings.M2_201, "");
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

        ((RadioGroup)rootView.findViewById(R.id.radioGrup201)).clearCheck();

        // Setiap ada pilihan radio button, yang jenisnya dua pilihan.
        // Selalu check bahwa nilainya tidak sama dengan "kosong"
        // kalau "kosong" nanti selalu kena ke logika else nya.
        String m2_201_yt = Prefs.getString(StaticStrings.M2_201yt, "");
        if (!m2_201_yt.isEmpty() && !m2_201_yt.equals("kosong"))
        if (m2_201_yt.equals("1. Ada")){
            ((RadioButton)rootView.findViewById(R.id.radioYa201)).setChecked(true);
        } else {
            ((RadioButton)rootView.findViewById(R.id.radioTidak201)).setChecked(true);
        }

        String m2_201 = Prefs.getString(StaticStrings.M2_201, "");
        if (!m2_201.isEmpty()){
            edt_iya_201.getEditText().setText(m2_201);
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
