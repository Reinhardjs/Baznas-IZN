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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class FragmentModul2801 extends Fragment implements BlockingStep {

    private FragmentManager fms;
    private Button btnNext;
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup22;
    private RadioGroup radioGroup3;
    private RadioGroup radioGroup4;
    private TextView txt_802;
    private TextView txt_804;
    private LinearLayout linearLayout;

    private boolean isSuccess;
    private View rootView;

    public FragmentModul2801() {

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
        rootView = inflater.inflate(R.layout.fragment_modul2_801, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar9);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        radioGroup1 = rootView.findViewById(R.id.radioGrup801);
        radioGroup2 = rootView.findViewById(R.id.radioGrup802);
        radioGroup22 = rootView.findViewById(R.id.radioGrup8022);
        radioGroup3 = rootView.findViewById(R.id.radioGrup803);
        radioGroup4 = rootView.findViewById(R.id.radioGrup804);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                txt_802.setVisibility(View.GONE);
                radioGroup2.setVisibility(View.GONE);
                radioGroup22.setVisibility(View.GONE);

                radioGroup3.setVisibility(View.GONE);

                txt_804.setVisibility(View.GONE);
                radioGroup4.setVisibility(View.GONE);
                radioGroup4.clearCheck();

                switch(checkedId) {
                    case R.id.radioYa801:
                        txt_802.setVisibility(View.VISIBLE);
                        radioGroup2.setVisibility(View.VISIBLE);
                        radioGroup2.clearCheck();
                        break;
                    case R.id.radioTidak801:
                        txt_804.setVisibility(View.VISIBLE);
                        radioGroup4.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                radioGroup22.setVisibility(View.GONE);
                radioGroup3.setVisibility(View.GONE);

                txt_804.setVisibility(View.GONE);
                radioGroup4.setVisibility(View.GONE);
                radioGroup4.clearCheck();

                switch(checkedId) {
                    case R.id.radioYa802:
                        radioGroup22.setVisibility(View.VISIBLE);
                        radioGroup3.setVisibility(View.GONE);
                        radioGroup22.clearCheck();
                        break;

                    case R.id.radioTidak802:
                        radioGroup22.setVisibility(View.GONE);
                        radioGroup3.setVisibility(View.VISIBLE);
                        radioGroup3.clearCheck();
                        break;
                }
            }
        });

        radioGroup22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioGroup3.setVisibility(View.GONE);

                if (checkedId != -1) {
                    radioGroup3.setVisibility(View.VISIBLE);
                    radioGroup3.clearCheck();
                }

                radioGroup4.clearCheck();
                txt_804.setVisibility(View.GONE);
                radioGroup4.setVisibility(View.GONE);
            }
        });

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    txt_804.setVisibility(View.VISIBLE);
                    radioGroup4.setVisibility(View.VISIBLE);
                    radioGroup4.clearCheck();
                }
            }
        });


        txt_802 = rootView.findViewById(R.id.txt_802);
        txt_804 = rootView.findViewById(R.id.txt_804);
        linearLayout = rootView.findViewById(R.id.ll_801);

        btnNext = rootView.findViewById(R.id.btn_next_801);
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
            Prefs.remove(StaticStrings.M2_801yt);
            Prefs.remove(StaticStrings.M2_802yt1);
            Prefs.remove(StaticStrings.M2_802yt2);
            Prefs.remove(StaticStrings.M2_803yt);
            Prefs.remove(StaticStrings.M2_804yt);


            int selectedId1 = radioGroup1.getCheckedRadioButtonId();
            RadioButton M2_801yt = (RadioButton) view.findViewById(selectedId1);
            int selectedId2 = radioGroup2.getCheckedRadioButtonId();
            RadioButton M2_802yt1 = (RadioButton) view.findViewById(selectedId2);
            int selectedId3 = radioGroup22.getCheckedRadioButtonId();
            RadioButton M2_802yt2 = (RadioButton) view.findViewById(selectedId3);
            int selectedId4 = radioGroup3.getCheckedRadioButtonId();
            RadioButton M2_803yt = (RadioButton) view.findViewById(selectedId4);
            int selectedId5 = radioGroup4.getCheckedRadioButtonId();
            RadioButton M2_804yt = (RadioButton) view.findViewById(selectedId5);


            if (linearLayout.getVisibility() == View.VISIBLE) {
                if (radioGroup2.getVisibility() == View.VISIBLE) {
                    Prefs.putString(StaticStrings.M2_802yt1, M2_802yt1.getText().toString());
                } else {
                    Prefs.putString(StaticStrings.M2_802yt1, "-1. Nochoice");
                }

                if (radioGroup22.getVisibility() == View.VISIBLE) {
                    Prefs.putString(StaticStrings.M2_802yt2, M2_802yt2.getText().toString());
                } else {
                    Prefs.putString(StaticStrings.M2_802yt2, "-1. Nochoice");
                }

                if (radioGroup3.getVisibility() == View.VISIBLE) {
                    Prefs.putString(StaticStrings.M2_803yt, M2_803yt.getText().toString());
                } else {
                    Prefs.putString(StaticStrings.M2_803yt, "-1. Nochoice");
                }
            }

            Prefs.putString(StaticStrings.M2_801yt, M2_801yt.getText().toString());
            Prefs.putString(StaticStrings.M2_804yt, M2_804yt.getText().toString());

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

        String m2_801_yt = Prefs.getString(StaticStrings.M2_801yt, "");
        if (!m2_801_yt.isEmpty() && !m2_801_yt.equals("kosong") && !m2_801_yt.equals("-1. Nochoice")){
            ((RadioGroup)rootView.findViewById(R.id.radioGrup801)).clearCheck();

            if (m2_801_yt.equals("1. Ada")){
                ((RadioButton)rootView.findViewById(R.id.radioYa801)).setChecked(true);
            } else {
                ((RadioButton)rootView.findViewById(R.id.radioTidak801)).setChecked(true);
            }
        }

        // radiogroup2
        String m2_802_yt1 = Prefs.getString(StaticStrings.M2_802yt1, "");
        if (!m2_802_yt1.isEmpty() && !m2_802_yt1.equals("kosong") && !m2_802_yt1.equals("-1. Nochoice")){
            ((RadioGroup)rootView.findViewById(R.id.radioGrup802)).clearCheck();

            if (m2_802_yt1.equals("1. Teraudit")){
                ((RadioButton)rootView.findViewById(R.id.radioYa802)).setChecked(true);
            } else {
                ((RadioButton)rootView.findViewById(R.id.radioTidak802)).setChecked(true);
            }
        }

        // radiogroup3
        String m2_802_yt2 = Prefs.getString(StaticStrings.M2_802yt2, "");
        if (!m2_802_yt2.isEmpty() && !m2_802_yt2.equals("kosong") && !m2_802_yt2.equals("-1. Nochoice")){
            ((RadioGroup)rootView.findViewById(R.id.radioGrup8022)).clearCheck();

            if (m2_802_yt2.equals("1. Opini WTP")){
                ((RadioButton)rootView.findViewById(R.id.radioYa8022)).setChecked(true);
            } else {
                ((RadioButton)rootView.findViewById(R.id.radioTidak8022)).setChecked(true);
            }
        }

        // radiogroup4
        String m2_803_yt = Prefs.getString(StaticStrings.M2_803yt, "");
        if (!m2_803_yt.isEmpty() && !m2_803_yt.equals("kosong") && !m2_803_yt.equals("-1. Nochoice")){
            ((RadioGroup)rootView.findViewById(R.id.radioGrup803)).clearCheck();

            if (m2_803_yt.equals("1. Dipublikasikan")){
                ((RadioButton)rootView.findViewById(R.id.radioYa803)).setChecked(true);
            } else {
                ((RadioButton)rootView.findViewById(R.id.radioTidak803)).setChecked(true);
            }
        }

        // radiogroup5
        String m2_804_yt = Prefs.getString(StaticStrings.M2_804yt, "");
        if (!m2_804_yt.isEmpty() && !m2_804_yt.equals("kosong") && !m2_804_yt.equals("-1. Nochoice")){
            ((RadioGroup)rootView.findViewById(R.id.radioGrup804)).clearCheck();

            if (m2_804_yt.equals("1. Ada")){
                ((RadioButton)rootView.findViewById(R.id.radioYa804)).setChecked(true);
            } else {
                ((RadioButton)rootView.findViewById(R.id.radioTidak804)).setChecked(true);
            }
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

