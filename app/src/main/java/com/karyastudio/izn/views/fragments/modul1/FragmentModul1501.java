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

public class FragmentModul1501 extends Fragment implements BlockingStep {
    private FragmentManager fms;

    private RadioGroup edt_501;
    private RadioGroup edt_502;
    private RadioGroup edt_503;
    private RadioGroup edt_504;
    private RadioGroup edt_505;
    private RadioGroup edt_506;
    private RadioGroup edt_507;
    private RadioGroup edt_508;
    private RadioGroup edt_509;
    private RadioGroup edt_510;

    private Button btnNext;
    private boolean isSuccess;
    private View rootView;

    public FragmentModul1501() {

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
        rootView = inflater.inflate(R.layout.fragment_modul1_501, container, false);

        Utils.log("FRAGMENT PAGE 5 ONCREATEVIEW");

//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar6);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        edt_501 = rootView.findViewById(R.id.edt1_501);
        edt_502 = rootView.findViewById(R.id.edt1_502);
        edt_503 = rootView.findViewById(R.id.edt1_503);
        edt_504 = rootView.findViewById(R.id.edt1_504);
        edt_505 = rootView.findViewById(R.id.edt1_505);
        edt_506 = rootView.findViewById(R.id.edt1_506);
        edt_507 = rootView.findViewById(R.id.edt1_507);
        edt_508 = rootView.findViewById(R.id.edt1_508);
        edt_509 = rootView.findViewById(R.id.edt1_509);
        edt_510 = rootView.findViewById(R.id.edt1_510);


        btnNext = rootView.findViewById(R.id.btn_next1_501);
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
            Prefs.remove(StaticStrings.M1_501);
            Prefs.remove(StaticStrings.M1_502);
            Prefs.remove(StaticStrings.M1_503);
            Prefs.remove(StaticStrings.M1_504);
            Prefs.remove(StaticStrings.M1_505);
            Prefs.remove(StaticStrings.M1_506);
            Prefs.remove(StaticStrings.M1_507);
            Prefs.remove(StaticStrings.M1_508);
            Prefs.remove(StaticStrings.M1_509);
            Prefs.remove(StaticStrings.M1_510);


            int selectedId1 = edt_501.getCheckedRadioButtonId();
            RadioButton M1_501 = view.findViewById(selectedId1);

            int selectedId2 = edt_502.getCheckedRadioButtonId();
            RadioButton M1_502 = view.findViewById(selectedId2);

            int selectedId3 = edt_503.getCheckedRadioButtonId();
            RadioButton M1_503 = view.findViewById(selectedId3);

            int selectedId4 = edt_504.getCheckedRadioButtonId();
            RadioButton M1_504 = view.findViewById(selectedId4);

            int selectedId5 = edt_505.getCheckedRadioButtonId();
            RadioButton M1_505 = view.findViewById(selectedId5);

            int selectedId6 = edt_506.getCheckedRadioButtonId();
            RadioButton M1_506 = view.findViewById(selectedId6);

            int selectedId7 = edt_507.getCheckedRadioButtonId();
            RadioButton M1_507 = view.findViewById(selectedId7);

            int selectedId8 = edt_508.getCheckedRadioButtonId();
            RadioButton M1_508 = view.findViewById(selectedId8);

            int selectedId9 = edt_509.getCheckedRadioButtonId();
            RadioButton M1_509 = view.findViewById(selectedId9);

            int selectedId10 = edt_510.getCheckedRadioButtonId();
            RadioButton M1_510 = view.findViewById(selectedId10);

            if (selectedId1 == -1) throw  new FormError("Opsi pilihan 1 tidak boleh kosong.");
            else if (selectedId2 == -1) throw  new FormError("Opsi pilihan 2 tidak boleh kosong.");
            else if (selectedId3 == -1) throw  new FormError("Opsi pilihan 3 tidak boleh kosong.");
            else if (selectedId4 == -1) throw  new FormError("Opsi pilihan 4 tidak boleh kosong.");
            else if (selectedId5 == -1) throw  new FormError("Opsi pilihan 5 tidak boleh kosong.");
            else if (selectedId6 == -1) throw  new FormError("Opsi pilihan 6 tidak boleh kosong.");
            else if (selectedId7 == -1) throw  new FormError("Opsi pilihan 7 tidak boleh kosong.");
            else if (selectedId8 == -1) throw  new FormError("Opsi pilihan 8 tidak boleh kosong.");
            else if (selectedId9 == -1) throw  new FormError("Opsi pilihan 9 tidak boleh kosong.");
            else if (selectedId10 == -1) throw  new FormError("Opsi pilihan 10 tidak boleh kosong.");


            Prefs.putString(StaticStrings.M1_501, M1_501.getText().toString());
            Prefs.putString(StaticStrings.M1_502, M1_502.getText().toString());
            Prefs.putString(StaticStrings.M1_503, M1_503.getText().toString());
            Prefs.putString(StaticStrings.M1_504, M1_504.getText().toString());
            Prefs.putString(StaticStrings.M1_505, M1_505.getText().toString());
            Prefs.putString(StaticStrings.M1_506, M1_506.getText().toString());
            Prefs.putString(StaticStrings.M1_507, M1_507.getText().toString());
            Prefs.putString(StaticStrings.M1_508, M1_508.getText().toString());
            Prefs.putString(StaticStrings.M1_509, M1_509.getText().toString());
            Prefs.putString(StaticStrings.M1_510, M1_510.getText().toString());
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
        Utils.log("FRAGMENT PAGE 5 ONSELECTED");

        isSuccess = false;

        new Handler().postDelayed(() -> {

            //if (SurveyKDZActivity.data != null){
                KajianDampakZakatPojo data = SurveyKDZActivity.data;

            // Setiap ada pilihan radio button, yang jenisnya dua pilihan.
            // Selalu check bahwa nilainya tidak sama dengan "kosong"
            // kalau "kosong" nanti selalu kena ke logika else nya.

                ((RadioGroup)rootView.findViewById(R.id.edt1_501)).clearCheck();
                String m1_501 = Prefs.getString(StaticStrings.M1_501, "");
                if (!m1_501.isEmpty() && !m1_501.equals("kosong"))
                if (m1_501.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa501)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak501)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_502)).clearCheck();
                String m1_502 = Prefs.getString(StaticStrings.M1_502, "");
                if (!m1_502.isEmpty() && !m1_502.equals("kosong"))
                if (m1_502.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa502)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak502)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_503)).clearCheck();
                String m1_503 = Prefs.getString(StaticStrings.M1_503, "");
                if (!m1_503.isEmpty() && !m1_503.equals("kosong"))
                if (m1_503.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa503)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak503)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_504)).clearCheck();
                String m1_504 = Prefs.getString(StaticStrings.M1_504, "");
                if (!m1_504.isEmpty() && !m1_504.equals("kosong"))
                if (m1_504.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa504)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak504)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_505)).clearCheck();
                String m1_505 = Prefs.getString(StaticStrings.M1_505, "");
                if (!m1_505.isEmpty() && !m1_505.equals("kosong"))
                if (m1_505.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa505)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak505)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_506)).clearCheck();
                String m1_506 = Prefs.getString(StaticStrings.M1_506, "");
                if (!m1_506.isEmpty() && !m1_506.equals("kosong"))
                if (m1_506.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa506)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak506)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_507)).clearCheck();
                String m1_507 = Prefs.getString(StaticStrings.M1_507, "");
                if (!m1_507.isEmpty() && !m1_507.equals("kosong"))
                if (m1_507.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa507)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak507)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_508)).clearCheck();
                String m1_508 = Prefs.getString(StaticStrings.M1_508, "");
                if (!m1_508.isEmpty() && !m1_508.equals("kosong"))
                if (m1_508.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa508)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak508)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_509)).clearCheck();
                String m1_509 = Prefs.getString(StaticStrings.M1_509, "");
                if (!m1_509.isEmpty() && !m1_509.equals("kosong"))
                if (m1_509.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa509)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak509)).setChecked(true);
                }

                ((RadioGroup)rootView.findViewById(R.id.edt1_510)).clearCheck();
                String m1_510 = Prefs.getString(StaticStrings.M1_510, "");
                if (!m1_510.isEmpty() && !m1_510.equals("kosong"))
                if (m1_510.equals("1. Ya")){
                    ((RadioButton)rootView.findViewById(R.id.radioYa510)).setChecked(true);
                } else {
                    ((RadioButton)rootView.findViewById(R.id.radioTidak510)).setChecked(true);
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