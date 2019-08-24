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

import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class FragmentModul2501 extends Fragment implements BlockingStep {
    private FragmentManager fms;
    private Button btnNext;
    private EasyTextInputLayout edt_501;
    private EasyTextInputLayout edt_502;

    private boolean isSuccess;
    private View rootView;

    private EasyForm easyForm;

    public FragmentModul2501() {

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
        rootView = inflater.inflate(R.layout.fragment_modul2_501, container, false);
//        fms = ((MainActivity) getActivity()).fms;

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
                VerificationError verificationError = saveAndNext(rootView);
                if (verificationError != null){
                    String message = verificationError.getErrorMessage();
                    Utils.toast(getContext(), message);
                }
            }
        });

        easyForm = rootView.findViewById(R.id.easyForm);

        return rootView;
    }

    private VerificationError saveAndNext(View view){
        isSuccess = false;

        try {
            //Pref disini (501, 502) )di clear semua
            Prefs.remove(StaticStrings.M2_501);
            Prefs.remove(StaticStrings.M2_502);

            Prefs.putString(StaticStrings.M2_501, edt_501.getEditText().getText().toString());
            Prefs.putString(StaticStrings.M2_502, edt_502.getEditText().getText().toString());
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

        String m2_501 = Prefs.getString(StaticStrings.M2_501, "");
        if (!m2_501.isEmpty()){
            edt_501.getEditText().setText(m2_501);
        }
        Utils.log("M2_501 : " + m2_501);

        String m2_502 = Prefs.getString(StaticStrings.M2_502, "");
        if (!m2_502.isEmpty()){
            edt_502.getEditText().setText(m2_502);
        }
        Utils.log("M2_502 : " + m2_502);
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
