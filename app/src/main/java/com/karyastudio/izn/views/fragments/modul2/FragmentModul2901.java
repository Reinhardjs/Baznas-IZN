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
import android.widget.Toast;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FragmentModul2901 extends Fragment implements BlockingStep {
    private FragmentManager fms;
    private Button btnNext;
    private EasyTextInputLayout edt_901;

    private boolean isSuccess;
    private View rootView;

    private EasyForm easyForm;

    public FragmentModul2901() {

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
        rootView = inflater.inflate(R.layout.fragment_modul2_901, container, false);
//        fms = ((MainActivity) getActivity()).fms;
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

    private VerificationError saveAndNext(View rootView){
        isSuccess = false;

        try {
            Prefs.remove(StaticStrings.M2_901);

            Prefs.putString(StaticStrings.M2_901, edt_901.getEditText().getText().toString());
            Prefs.putString(StaticStrings.M2_update_at, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(Calendar.getInstance().getTime()));

            easyForm.validate();
            if (!easyForm.isValid()){
                throw new Exception();
            }

            Utils.toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN);
        } catch (Exception e){
            if (e.getMessage() != null) {
                Utils.log(e.getMessage());
            }
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

            Utils.addQueueModul2(rootView.getContext(), SurveyIZNActivity.form_request_type);
            Utils.log("FORM REQUEST TYPE : " + SurveyIZNActivity.form_request_type);

            if (Utils.isOnline(getActivity())) {
                // Utils.sendModul2(getActivity());
                Utils.log("CALL SEND MODUL 2 901");
            } else {
                Toast.makeText(getActivity(), "Koneksi anda tidak ada, data anda akan dikirim setelah anda online", Toast.LENGTH_LONG).show();
                StaticStrings.readyToSendViaOfflineModul2 = true;
            }
        } else {
            isSuccess = false;
        }

        return verificationError;
    }

    @Override
    public void onSelected() {
        isSuccess = false;

        String m2_901 = Prefs.getString(StaticStrings.M2_901, "");
        if (!m2_901.isEmpty()){
            edt_901.getEditText().setText(m2_901);
        }
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        Utils.toast(getContext(), error.getErrorMessage());
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        callback.complete();
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
