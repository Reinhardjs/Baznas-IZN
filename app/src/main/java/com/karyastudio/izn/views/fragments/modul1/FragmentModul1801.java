package com.karyastudio.izn.views.fragments.modul1;

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

import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.FormError;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class FragmentModul1801 extends Fragment implements BlockingStep {
    private FragmentManager fms;
    private EditText edt_801;
    private EditText edt_802;
    private EditText edt_803;
    private EditText edt_804;
    private EditText edt_805;
    private EditText edt_806;
    private EditText edt_807;
    private EditText edt_808;
    private EditText edt_809;
    private EditText edt_810;
    private EditText edt_811;
    private EditText edt_812;
    private EditText edt_813;
    private EditText edt_814;
    private EditText edt_815;

    private Button btnNext;
    private boolean isSuccess;
    private View rootView;

    public FragmentModul1801() {

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
        rootView = inflater.inflate(R.layout.fragment_modul1_801, container, false);

        Utils.log("FRAGMENT PAGE 8 ONCREATEVIEW");

//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar6);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        btnNext = rootView.findViewById(R.id.btn_next1_801);
        edt_801 = rootView.findViewById(R.id.edt1_801);
        edt_802 = rootView.findViewById(R.id.edt1_802);
        edt_803 = rootView.findViewById(R.id.edt1_803);
        edt_804 = rootView.findViewById(R.id.edt1_804);
        edt_805 = rootView.findViewById(R.id.edt1_805);
        edt_806 = rootView.findViewById(R.id.edt1_806);
        edt_807 = rootView.findViewById(R.id.edt1_807);
        edt_808 = rootView.findViewById(R.id.edt1_808);
        edt_809 = rootView.findViewById(R.id.edt1_809);
        edt_810 = rootView.findViewById(R.id.edt1_810);
        edt_811 = rootView.findViewById(R.id.edt1_811);
        edt_812 = rootView.findViewById(R.id.edt1_812);
        edt_813 = rootView.findViewById(R.id.edt1_813);
        edt_814 = rootView.findViewById(R.id.edt1_814);
        edt_815 = rootView.findViewById(R.id.edt1_815);


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
            Prefs.remove(StaticStrings.M1_801);
            Prefs.remove(StaticStrings.M1_802);
            Prefs.remove(StaticStrings.M1_803);
            Prefs.remove(StaticStrings.M1_804);
            Prefs.remove(StaticStrings.M1_805);
            Prefs.remove(StaticStrings.M1_806);
            Prefs.remove(StaticStrings.M1_807);
            Prefs.remove(StaticStrings.M1_808);
            Prefs.remove(StaticStrings.M1_809);
            Prefs.remove(StaticStrings.M1_810);
            Prefs.remove(StaticStrings.M1_811);
            Prefs.remove(StaticStrings.M1_812);
            Prefs.remove(StaticStrings.M1_813);
            Prefs.remove(StaticStrings.M1_814);
            Prefs.remove(StaticStrings.M1_815);


            if (edt_801.getText().toString().isEmpty())
                throw new FormError("Form input 1 tidak boleh kosong");
            else if (edt_802.getText().toString().isEmpty())
                throw new FormError("Form input 2 tidak boleh kosong");
            else if (edt_803.getText().toString().isEmpty())
                throw new FormError("Form input 3 tidak boleh kosong");
            else if (edt_804.getText().toString().isEmpty())
                throw new FormError("Form input 4 tidak boleh kosong");
            else if (edt_805.getText().toString().isEmpty())
                throw new FormError("Form input 5 tidak boleh kosong");
            else if (edt_806.getText().toString().isEmpty())
                throw new FormError("Form input 6 tidak boleh kosong");
            else if (edt_807.getText().toString().isEmpty())
                throw new FormError("Form input 7 tidak boleh kosong");
            else if (edt_808.getText().toString().isEmpty())
                throw new FormError("Form input 8 tidak boleh kosong");
            else if (edt_809.getText().toString().isEmpty())
                throw new FormError("Form input 9 tidak boleh kosong");
            else if (edt_810.getText().toString().isEmpty())
                throw new FormError("Form input 10 tidak boleh kosong");
            else if (edt_811.getText().toString().isEmpty())
                throw new FormError("Form input 11 tidak boleh kosong");
            else if (edt_812.getText().toString().isEmpty())
                throw new FormError("Form input 12 tidak boleh kosong");
            else if (edt_813.getText().toString().isEmpty())
                throw new FormError("Form input 13 tidak boleh kosong");
            else if (edt_814.getText().toString().isEmpty())
                throw new FormError("Form input 14 tidak boleh kosong");
            else if (edt_815.getText().toString().isEmpty())
                throw new FormError("Form input 15 tidak boleh kosong");


            Prefs.putString(StaticStrings.M1_801, edt_801.getText().toString());
            Prefs.putString(StaticStrings.M1_802, edt_802.getText().toString());
            Prefs.putString(StaticStrings.M1_803, edt_803.getText().toString());
            Prefs.putString(StaticStrings.M1_804, edt_804.getText().toString());
            Prefs.putString(StaticStrings.M1_805, edt_805.getText().toString());
            Prefs.putString(StaticStrings.M1_806, edt_806.getText().toString());
            Prefs.putString(StaticStrings.M1_807, edt_807.getText().toString());
            Prefs.putString(StaticStrings.M1_808, edt_808.getText().toString());
            Prefs.putString(StaticStrings.M1_809, edt_809.getText().toString());
            Prefs.putString(StaticStrings.M1_810, edt_810.getText().toString());
            Prefs.putString(StaticStrings.M1_811, edt_811.getText().toString());
            Prefs.putString(StaticStrings.M1_812, edt_812.getText().toString());
            Prefs.putString(StaticStrings.M1_813, edt_813.getText().toString());
            Prefs.putString(StaticStrings.M1_814, edt_814.getText().toString());
            Prefs.putString(StaticStrings.M1_815, edt_815.getText().toString());
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

        edt_801.setText(Prefs.getString(StaticStrings.M1_801, ""));
        edt_802.setText(Prefs.getString(StaticStrings.M1_802, ""));
        edt_803.setText(Prefs.getString(StaticStrings.M1_803, ""));
        edt_804.setText(Prefs.getString(StaticStrings.M1_804, ""));
        edt_805.setText(Prefs.getString(StaticStrings.M1_805, ""));
        edt_806.setText(Prefs.getString(StaticStrings.M1_806, ""));
        edt_807.setText(Prefs.getString(StaticStrings.M1_807, ""));
        edt_808.setText(Prefs.getString(StaticStrings.M1_808, ""));
        edt_809.setText(Prefs.getString(StaticStrings.M1_809, ""));
        edt_810.setText(Prefs.getString(StaticStrings.M1_810, ""));
        edt_811.setText(Prefs.getString(StaticStrings.M1_811, ""));
        edt_812.setText(Prefs.getString(StaticStrings.M1_812, ""));
        edt_813.setText(Prefs.getString(StaticStrings.M1_813, ""));
        edt_814.setText(Prefs.getString(StaticStrings.M1_814, ""));
        edt_815.setText(Prefs.getString(StaticStrings.M1_815, ""));

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
