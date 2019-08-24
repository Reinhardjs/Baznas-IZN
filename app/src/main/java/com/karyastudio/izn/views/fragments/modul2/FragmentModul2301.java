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
import android.widget.TextView;

import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class FragmentModul2301 extends Fragment implements BlockingStep {
    private FragmentManager fms;
    private RadioGroup radioGroup;
    private RadioGroup radioGroup2;
    private TextView text1;
    private TextView text2;

    private EasyTextInputLayout edt_iya_301;
    private EasyTextInputLayout edt_iya_3012;
    private Button btnNext;

    private boolean isSuccess;
    private View rootView;

    EasyForm easyForm1, easyForm2;

    public FragmentModul2301() {

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
        rootView = inflater.inflate(R.layout.fragment_modul2_301, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar4);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        easyForm1 = rootView.findViewById(R.id.easyform_iya_301);
        easyForm2 = rootView.findViewById(R.id.easyform_iya_3012);

        text1 = rootView.findViewById(R.id.txt_301);
        text2 = rootView.findViewById(R.id.txt_302);
        radioGroup = rootView.findViewById(R.id.radioGrup301);
        radioGroup2 = rootView.findViewById(R.id.radioGrup3012);
        edt_iya_301 = rootView.findViewById(R.id.edt_iya_301);
        edt_iya_3012 = rootView.findViewById(R.id.edt_iya_3012);
        btnNext = rootView.findViewById(R.id.btn_next_301);

        if (Prefs.getString(StaticStrings.M2_101,"none").equals("1. BAZNAS Pusat")){
            text1.setText("1. Alokasi APBN untuk BAZNAS Pusat /Provinsi/Kabupaten/Kota 2 tahun terakhir");
            text2.setText("2. Alokasi APBN untuk BAZNAS Pusat /Provinsi/Kabupaten/Kota 1 tahun terakhir");
        }else{
            text1.setText("1. Alokasi APBD untuk BAZNAS Pusat /Provinsi/Kabupaten/Kota 2 tahun terakhir");
            text2.setText("2. Alokasi APBD untuk BAZNAS Pusat /Provinsi/Kabupaten/Kota 1 tahun terakhir");
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa301:
                        edt_iya_301.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak301:
                        edt_iya_301.setVisibility(View.GONE);
                        break;

                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa3012:
                        edt_iya_3012.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak3012:
                        edt_iya_3012.setVisibility(View.GONE);
                        break;

                }
            }
        });

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

        VerificationError verificationError = null;

        try {
            //Pref disini (301, 301yt,302, 302yt) )di clear semua
            Prefs.remove(StaticStrings.M2_301);
            Prefs.remove(StaticStrings.M2_301yt);
            Prefs.remove(StaticStrings.M2_302);
            Prefs.remove(StaticStrings.M2_302yt);

            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton M2_301yt = (RadioButton) view.findViewById(selectedId);
            int selectedId2 = radioGroup2.getCheckedRadioButtonId();
            RadioButton M2_302yt = (RadioButton) view.findViewById(selectedId2);

            Prefs.putString(StaticStrings.M2_301yt, M2_301yt.getText().toString());
            Prefs.putString(StaticStrings.M2_302yt, M2_302yt.getText().toString());

            if (edt_iya_301.getVisibility() == View.VISIBLE) {
                Prefs.putString(StaticStrings.M2_301, edt_iya_301.getEditText().getText().toString());
                easyForm1.validate();
                if (!easyForm1.isValid()){
                    verificationError = new VerificationError("Mohon lengkapi form pada halaman ini");
                }
            } else {
                Prefs.putString(StaticStrings.M2_301, "0");
            }

            if (edt_iya_3012.getVisibility() == View.VISIBLE) {
                Prefs.putString(StaticStrings.M2_302, edt_iya_3012.getEditText().getText().toString());
                easyForm2.validate();
                if (!easyForm2.isValid()){
                    verificationError = new VerificationError("Mohon lengkapi form pada halaman ini");
                }
            } else {
                Prefs.putString(StaticStrings.M2_302, "0");
            }

            if (verificationError == null) {
                Utils.toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN);
            }

            return verificationError;

        } catch (Exception e){
            return new VerificationError("Mohon lengkapi form pada halaman ini");
        }

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

        ((RadioGroup)rootView.findViewById(R.id.radioGrup301)).clearCheck();
        String m2_301_yt = Prefs.getString(StaticStrings.M2_301yt, "");
        Utils.log("m2_301_yt : " + m2_301_yt);
        if (!m2_301_yt.isEmpty() && !m2_301_yt.equals("kosong"))
        if (m2_301_yt.equals("1. Ada")){
            ((RadioButton)rootView.findViewById(R.id.radioYa301)).setChecked(true);
        } else {
            ((RadioButton)rootView.findViewById(R.id.radioTidak301)).setChecked(true);
        }

        String m2_301 = Prefs.getString(StaticStrings.M2_301, "");
        if (!m2_301.isEmpty()) {
            edt_iya_301.getEditText().setText(m2_301);
        }

        ((RadioGroup)rootView.findViewById(R.id.radioGrup3012)).clearCheck();
        String m2_302_yt = Prefs.getString(StaticStrings.M2_302yt, "");
        Utils.log("m2_302_yt : " + m2_302_yt);
        if (!m2_302_yt.isEmpty() && !m2_302_yt.equals("kosong"))
            if (m2_302_yt.equals("1. Ada")){
                ((RadioButton)rootView.findViewById(R.id.radioYa3012)).setChecked(true);
            } else {
                ((RadioButton)rootView.findViewById(R.id.radioTidak3012)).setChecked(true);
            }

        String m2_302 = Prefs.getString(StaticStrings.M2_302, "");
        if (!m2_302.isEmpty()){
            edt_iya_3012.getEditText().setText(m2_302);
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
