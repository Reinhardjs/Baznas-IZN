package com.karyastudio.izn.views.fragments.modul1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyKDZActivity;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class FragmentModul1Likert2 extends Fragment implements BlockingStep {
    private FragmentManager fms;
    private Spinner edt_lik1;
    private Spinner edt_lik2;
    private Spinner edt_lik3;
    private Spinner edt_lik4;
    private Spinner edt_lik5;
    private Button btnNext;
    private boolean isSuccess;
    private View rootView;

    private List<String> plants, plants1, plants2, plants3, plants4;

    public FragmentModul1Likert2() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_modul1_likert2, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        edt_lik1 = rootView.findViewById(R.id.edt1_lik_1);
        edt_lik2 = rootView.findViewById(R.id.edt1_lik_2);
        edt_lik3 = rootView.findViewById(R.id.edt1_lik_3);
        edt_lik4 = rootView.findViewById(R.id.edt1_lik_4);
        edt_lik5 = rootView.findViewById(R.id.edt1_lik_5);


        plants = new ArrayList<>(Arrays.asList(getContext().getResources().getStringArray(R.array.items_likert_sholat)));
        plants1 = new ArrayList<>(Arrays.asList(getContext().getResources().getStringArray(R.array.items_likert_puasa)));
        plants2 = new ArrayList<>(Arrays.asList(getContext().getResources().getStringArray(R.array.items_likert_zakat)));
        plants3 = new ArrayList<>(Arrays.asList(getContext().getResources().getStringArray(R.array.items_likert_lingkungan)));
        plants4 = new ArrayList<>(Arrays.asList(getContext().getResources().getStringArray(R.array.items_likert_kebijakan)));


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        edt_lik1.setAdapter(spinnerArrayAdapter);

        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants1
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        edt_lik2.setAdapter(spinnerArrayAdapter1);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants2
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        edt_lik3.setAdapter(spinnerArrayAdapter2);

        ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants3
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        edt_lik4.setAdapter(spinnerArrayAdapter3);

        ArrayAdapter<String> spinnerArrayAdapter4 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants4
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        edt_lik5.setAdapter(spinnerArrayAdapter4);


        btnNext = rootView.findViewById(R.id.btn_next1_lik_1);
//        btnNext.setVisibility(View.GONE);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
            }
        });

        return rootView;
    }


    private VerificationError saveAndNext(View view){
//        try {
        Prefs.remove(StaticStrings.M1_lik1B);
        Prefs.remove(StaticStrings.M1_lik2B);
        Prefs.remove(StaticStrings.M1_lik3B);
        Prefs.remove(StaticStrings.M1_lik4B);
        Prefs.remove(StaticStrings.M1_lik5B);


        if (edt_lik1.getSelectedItem().toString().equals(plants.get(0)) ||
                edt_lik2.getSelectedItem().toString().equals(plants1.get(0)) ||
                edt_lik3.getSelectedItem().toString().equals(plants2.get(0)) ||
                edt_lik4.getSelectedItem().toString().equals(plants3.get(0)) ||
                edt_lik5.getSelectedItem().toString().equals(plants4.get(0))){
            return new VerificationError("Form harus dilengkapi");
        }

        Prefs.putString(StaticStrings.M1_lik1B, edt_lik1.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M1_lik2B, edt_lik2.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M1_lik3B, edt_lik3.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M1_lik4B, edt_lik4.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M1_lik5B, edt_lik5.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M1_update_at, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(Calendar.getInstance().getTime()));


        Utils.log("FORM REQUEST TYPE : " + SurveyKDZActivity.form_request_type);

        if (Utils.isOnline(getActivity())) {
             //Utils.sendModul1(view.getContext());
             // Utils.sendModulKeluarga(view.getContext());
        } else {
            Toast.makeText(getActivity(), "Tidak ada koneksi internet, data akan dikirim otomatis saat terhubung ke internet", Toast.LENGTH_LONG).show();
            StaticStrings.readyToSendViaOfflineModul1 = true;
        }

//        } catch (Exception e){
//            Utils.log("ERRROR AT END FORM : " + e.getMessage());
//            return new VerificationError("Mohon lengkapi form pada halaman ini");
//        }

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
            Utils.addQueueModul1(getContext(), SurveyKDZActivity.form_request_type);

            Utils.toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN);
        } else {
            isSuccess = false;
        }

        return verificationError;
    }

    @Override
    public void onSelected() {

        isSuccess = false;

//        if (SurveyKDZActivity.data != null) {
//            KajianDampakZakatPojo data = SurveyKDZActivity.data;

        List<String> listlik1 = plants;
        int selection1 = listlik1.indexOf(Prefs.getString(StaticStrings.M1_lik1B, ""));
        if (selection1 != -1)
        edt_lik1.setSelection(selection1);

        List<String> listLik2 = plants1;
        int selection2 = listLik2.indexOf(Prefs.getString(StaticStrings.M1_lik2B, ""));
        if (selection2 != -1)
        edt_lik2.setSelection(selection2);

        List<String> listLik3 = plants2;
        int selection3 = listLik3.indexOf(Prefs.getString(StaticStrings.M1_lik3B, ""));
        if (selection3 != -1)
        edt_lik3.setSelection(selection3);

        List<String> listLik4 = plants3;
        int selection4 = listLik4.indexOf(Prefs.getString(StaticStrings.M1_lik4B, ""));
        if (selection4 != -1)
        edt_lik4.setSelection(selection4);

        List<String> listLik5 = plants4;
        int selection5 = listLik5.indexOf(Prefs.getString(StaticStrings.M1_lik5B, ""));
        if (selection5 != -1)
        edt_lik5.setSelection(selection5);

//        }
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