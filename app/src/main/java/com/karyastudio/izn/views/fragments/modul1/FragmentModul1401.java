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
import android.widget.TextView;

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

public class FragmentModul1401 extends Fragment implements BlockingStep {
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

        Utils.log("FRAGMENT PAGE 4 ONCREATEVIEW");

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
            Prefs.remove(StaticStrings.M1_401);
            Prefs.remove(StaticStrings.M1_402);
            Prefs.remove(StaticStrings.M1_403);
            Prefs.remove(StaticStrings.M1_404);
            Prefs.remove(StaticStrings.M1_405);
            Prefs.remove(StaticStrings.M1_406);
            Prefs.remove(StaticStrings.M1_407);

            int selectedId1 = edt_401.getCheckedRadioButtonId();
            RadioButton M1_401 = view.findViewById(selectedId1);

            int selectedId2 = edt_402.getCheckedRadioButtonId();
            RadioButton M1_402 = view.findViewById(selectedId2);

            int selectedId3 = edt_403.getCheckedRadioButtonId();
            RadioButton M1_403 = view.findViewById(selectedId3);

            int selectedId4 = edt_404.getCheckedRadioButtonId();
            RadioButton M1_404 = view.findViewById(selectedId4);

            int selectedId5 = edt_405.getCheckedRadioButtonId();
            RadioButton M1_405 = view.findViewById(selectedId5);

            int selectedId6 = edt_406.getCheckedRadioButtonId();
            RadioButton M1_406 = view.findViewById(selectedId6);

            int selectedId7 = edt_407.getCheckedRadioButtonId();
            RadioButton M1_407 = view.findViewById(selectedId7);

            if (selectedId1 == -1) throw new FormError("Opsi pilihan 1 tidak boleh kosong.");
            else if (selectedId2 == -1) throw new FormError("Opsi pilihan 2 tidak boleh kosong.");
            else if (selectedId3 == -1) throw new FormError("Opsi pilihan 3 tidak boleh kosong.");
            else if (selectedId4 == -1) throw new FormError("Opsi pilihan 4 tidak boleh kosong.");
            else if (selectedId5 == -1) throw new FormError("Opsi pilihan 5 tidak boleh kosong.");
            else if (selectedId6 == -1) throw new FormError("Opsi pilihan 6 tidak boleh kosong.");
            else if (selectedId7 == -1) throw new FormError("Opsi pilihan 7 tidak boleh kosong.");

            Prefs.putString(StaticStrings.M1_401, M1_401.getText().toString());
            Prefs.putString(StaticStrings.M1_402, M1_402.getText().toString());
            Prefs.putString(StaticStrings.M1_403, M1_403.getText().toString());
            Prefs.putString(StaticStrings.M1_404, M1_404.getText().toString());
            Prefs.putString(StaticStrings.M1_405, M1_405.getText().toString());
            Prefs.putString(StaticStrings.M1_406, M1_406.getText().toString());
            Prefs.putString(StaticStrings.M1_407, M1_407.getText().toString());
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

        isSuccess = false;

        Utils.log("FRAGMENT PAGE 4 ONSELECTED");

        new Handler().postDelayed(() -> {

            //if (SurveyKDZActivity.data != null){
                KajianDampakZakatPojo data = SurveyKDZActivity.data;

            // Setiap ada pilihan radio button, yang jenisnya dua pilihan.
            // Selalu check bahwa nilainya tidak sama dengan "kosong"
            // kalau "kosong" nanti selalu kena ke logika else nya.

                ((RadioGroup)rootView.findViewById(R.id.edt1_401)).clearCheck();
                String m1_401 = Prefs.getString(StaticStrings.M1_401, "");
                if (!m1_401.isEmpty() && !m1_401.equals("kosong"))
                if (m1_401.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa401)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak401)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_402)).clearCheck();
                String m1_402 = Prefs.getString(StaticStrings.M1_402, "");
                if (!m1_402.isEmpty() && !m1_402.equals("kosong"))
                if (m1_402.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa402)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak402)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_403)).clearCheck();
                String m1_403 = Prefs.getString(StaticStrings.M1_403, "");
                if (!m1_403.isEmpty() && !m1_403.equals("kosong"))
                if (m1_403.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa403)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak403)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_404)).clearCheck();
                String m1_404 = Prefs.getString(StaticStrings.M1_404, "");
                if (!m1_404.isEmpty() && !m1_404.equals("kosong"))
                if (m1_404.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa404)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak404)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_405)).clearCheck();
                String m1_405 = Prefs.getString(StaticStrings.M1_405, "");
                if (!m1_405.isEmpty() && !m1_405.equals("kosong"))
                if (m1_405.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa405)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak405)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_406)).clearCheck();
                String m1_406 = Prefs.getString(StaticStrings.M1_406, "");
                if (!m1_406.isEmpty() && !m1_406.equals("kosong"))
                if (m1_406.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa406)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak406)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_407)).clearCheck();
                String m1_407 = Prefs.getString(StaticStrings.M1_407, "");
                if (!m1_407.isEmpty() && !m1_407.equals("kosong"))
                if (m1_407.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa407)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak407)).setChecked(true);
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
