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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentModul2701 extends Fragment implements BlockingStep {

    private FragmentManager fms;
    private Button btnNext;
    private RadioGroup radioGroup_702;
    private EasyTextInputLayout edt_iya_702;
    private Spinner edt_7031;
    private Spinner edt_7032;
    private Spinner edt_7041;
    private Spinner edt_7042;
    private EasyTextInputLayout edt_701;

    private boolean isSuccess;
    private View rootView;

    private EasyForm easyForm, easyFormYa;
    private List<String> bulan;

    public FragmentModul2701() {

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
        rootView = inflater.inflate(R.layout.fragment_modul2_701, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar8);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);


        edt_701 = rootView.findViewById(R.id.edt_701);

        radioGroup_702 = rootView.findViewById(R.id.radioGrup702);
        edt_iya_702 = rootView.findViewById(R.id.edt_iya_702);

        edt_7031 = rootView.findViewById(R.id.edt_7031);
        edt_7032 = rootView.findViewById(R.id.edt_7032);
        edt_7041 = rootView.findViewById(R.id.edt_7041);
        edt_7042 = rootView.findViewById(R.id.edt_7042);

        easyForm = rootView.findViewById(R.id.easyform_701);
        easyFormYa = rootView.findViewById(R.id.easyform_iya_702);


        radioGroup_702.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioYa702:
                        edt_iya_702.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioTidak702:
                        edt_iya_702.setVisibility(View.GONE);
                        break;
                }
            }
        });

        btnNext = rootView.findViewById(R.id.btn_next_702);
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
            //Pref disini (701, 702yt, 702, 7031, 7032, 7041, 7042)di clear semua
            Prefs.remove(StaticStrings.M2_701);
            Prefs.remove(StaticStrings.M2_702yt);
            Prefs.remove(StaticStrings.M2_702);
            Prefs.remove(StaticStrings.M2_7031);
            Prefs.remove(StaticStrings.M2_7032);
            Prefs.remove(StaticStrings.M2_7041);
            Prefs.remove(StaticStrings.M2_7042);


            int selectedId1 = radioGroup_702.getCheckedRadioButtonId();
            RadioButton M2_702yt = (RadioButton) view.findViewById(selectedId1);

            if (edt_7031.getSelectedItem().toString().equals(bulan.get(0)))
                throw new Exception("Silakan pilih bulan yang tersedia");
            else if (edt_7032.getSelectedItem().toString().equals(bulan.get(0)))
                throw new Exception("Silakan pilih bulan yang tersedia");
            else if (edt_7041.getSelectedItem().toString().equals(bulan.get(0)))
                throw new Exception("Silakan pilih bulan yang tersedia");
            else if (edt_7042.getSelectedItem().toString().equals(bulan.get(0)))
                throw new Exception("Silakan pilih bulan yang tersedia");


            Prefs.putString(StaticStrings.M2_701, edt_701.getEditText().getText().toString());
            Prefs.putString(StaticStrings.M2_702yt, M2_702yt.getText().toString());

            Prefs.putString(StaticStrings.M2_7031, edt_7031.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M2_7032, edt_7032.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M2_7041, edt_7041.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M2_7042, edt_7042.getSelectedItem().toString());

            if (edt_iya_702.getVisibility() == View.VISIBLE) {
                Prefs.putString(StaticStrings.M2_702, edt_iya_702.getEditText().getText().toString());

                easyFormYa.validate();
                if (!easyFormYa.isValid()){
                    throw new Exception();
                }
            } else {
                Prefs.putString(StaticStrings.M2_702, "0");
            }

            easyForm.validate();
            if (!easyForm.isValid()){
                throw new Exception();
            }

            Utils.toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN);
        } catch (Exception e){
            if (e.getMessage() != null){
                return new VerificationError(e.getMessage() + " Mohon lengkapi form pada halaman ini");
            } else {
                return new VerificationError("Mohon lengkapi form pada halaman ini");
            }
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

        String m2_701 = Prefs.getString(StaticStrings.M2_701, "");
        if (!m2_701.isEmpty()){
            edt_701.getEditText().setText(m2_701);
        }

        // Setiap ada pilihan radio button, yang jenisnya dua pilihan.
        // Selalu check bahwa nilainya tidak sama dengan "kosong"
        // kalau "kosong" nanti selalu kena ke logika else nya.

        ((RadioGroup)rootView.findViewById(R.id.radioGrup702)).clearCheck();
        String m2_702_yt = Prefs.getString(StaticStrings.M2_702yt, "");
        if (!m2_702_yt.isEmpty() && !m2_701.equals("kosong"))
            if (m2_702_yt.equals("1. Ada")){
                ((RadioButton)rootView.findViewById(R.id.radioYa702)).setChecked(true);
            } else {
                ((RadioButton)rootView.findViewById(R.id.radioTidak702)).setChecked(true);
            }

        String m2_702 = Prefs.getString(StaticStrings.M2_702, "");
        if (!m2_702.isEmpty() && !m2_702.equals("0")){
            edt_iya_702.getEditText().setText(m2_702);
        }

        bulan = new ArrayList<>(Arrays.asList(
                "Pilih", "Setiap Saat", "Januari","Februari","Maret","April","Mei","Juni","Juli",
                "Agustus", "September","Oktober","November","Desember"));

        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,bulan
        );
        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,bulan
        );
        ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,bulan
        );
        ArrayAdapter<String> spinnerArrayAdapter4 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,bulan
        );

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_style);
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_style);
        spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_style);
        spinnerArrayAdapter4.setDropDownViewResource(R.layout.spinner_style);

        edt_7031.setAdapter(spinnerArrayAdapter1);
        edt_7032.setAdapter(spinnerArrayAdapter2);
        edt_7041.setAdapter(spinnerArrayAdapter3);
        edt_7042.setAdapter(spinnerArrayAdapter4);

        String m2_7031 = Prefs.getString(StaticStrings.M2_7031, "");
        if (!m2_7031.isEmpty()){
            edt_7031.setSelection(bulan.indexOf(m2_7031));
        }

        String m2_7032 = Prefs.getString(StaticStrings.M2_7032, "");
        if (!m2_7032.isEmpty()){
            edt_7032.setSelection(bulan.indexOf(m2_7032));
        }

        String m2_7041 = Prefs.getString(StaticStrings.M2_7041, "");
        if (!m2_7041.isEmpty()){
            edt_7041.setSelection(bulan.indexOf(m2_7041));
        }

        String m2_7042 = Prefs.getString(StaticStrings.M2_7042, "");
        if (!m2_7042.isEmpty()){
            edt_7042.setSelection(bulan.indexOf(m2_7042));
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
