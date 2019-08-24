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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentModul1601 extends Fragment implements BlockingStep {
    private FragmentManager fms;
    private Button btnNext;
    private Spinner edt_601;
    private Spinner spn_605;
    private Spinner edt_602;
    private TextView txt_603;
    private Spinner edt_603;
    private EditText edt_604;
    private TextView txt_604_lj;
    private EditText edt_604_lanjut;
    private EditText edt_605;
    private EditText edt_606;
    private Spinner edt_607;
    private TextView txt_608;
    private EditText edt_608;
    private TextView txt_609;
    private EditText edt_609;
    private TextView txt_610;
    private EditText edt_610;
    private TextView txt_611;
    private EditText edt_611;
    private TextView txt_612;
    private EditText edt_612;
    private TextView txt_613;
    private EditText edt_613;
    private TextView txt_614;
    private EditText edt_614;
    private TextView txt_615;
    private EditText edt_615;
    private TextView txt_616;
    private EditText edt_616;
    private TextView txt_617;
    private RadioGroup edt_617;
    private TextView txt_618;
    private EditText edt_618;
    private boolean isSuccess;
    private View rootView;


    public FragmentModul1601() {

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
        rootView = inflater.inflate(R.layout.fragment_modul1_601, container, false);

        Utils.log("FRAGMENT PAGE 6 ONCREATEVIEW");

//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar6);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        btnNext = rootView.findViewById(R.id.btn_next1_601);
        edt_601 = rootView.findViewById(R.id.edt1_601);
        edt_602 = rootView.findViewById(R.id.edt1_602);
        edt_603 = rootView.findViewById(R.id.edt1_603);
        edt_604 = rootView.findViewById(R.id.edt1_604);
        edt_604_lanjut = rootView.findViewById(R.id.edt1_604_lanjut);
        edt_605 = rootView.findViewById(R.id.edt1_605);
        spn_605 = rootView.findViewById(R.id.spin1_605);
        edt_606 = rootView.findViewById(R.id.edt1_606);
        edt_607 = rootView.findViewById(R.id.edt1_607);
        edt_608 = rootView.findViewById(R.id.edt1_608);
        edt_609 = rootView.findViewById(R.id.edt1_609);
        edt_610 = rootView.findViewById(R.id.edt1_610);
        edt_611 = rootView.findViewById(R.id.edt1_611);
        edt_612 = rootView.findViewById(R.id.edt1_612);
        edt_613 = rootView.findViewById(R.id.edt1_613);
        edt_614 = rootView.findViewById(R.id.edt1_614);
        edt_615 = rootView.findViewById(R.id.edt1_615);
        edt_616 = rootView.findViewById(R.id.edt1_616);
        edt_617 = rootView.findViewById(R.id.edt1_617);
        edt_618 = rootView.findViewById(R.id.edt1_618);

        txt_603 = rootView.findViewById(R.id.txt1_603);
        txt_604_lj = rootView.findViewById(R.id.txt_604lj);
        txt_608 = rootView.findViewById(R.id.txt1_608);
        txt_609 = rootView.findViewById(R.id.txt1_609);
        txt_610 = rootView.findViewById(R.id.txt1_610);
        txt_611 = rootView.findViewById(R.id.txt1_611);
        txt_612 = rootView.findViewById(R.id.txt1_612);
        txt_613 = rootView.findViewById(R.id.txt1_613);
        txt_614 = rootView.findViewById(R.id.txt1_614);
        txt_615 = rootView.findViewById(R.id.txt1_615);
        txt_616 = rootView.findViewById(R.id.txt1_616);
        txt_617 = rootView.findViewById(R.id.txt1_617);
        txt_618 = rootView.findViewById(R.id.txt1_618);

        txt_616.setVisibility(View.GONE);
        edt_616.setVisibility(View.GONE);
        txt_612.setVisibility(View.GONE);
        edt_612.setVisibility(View.GONE);


        edt_602.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = parent.getItemAtPosition(position).toString();
                if (temp.equals("1. BAZNAS Pusat")) {
                    txt_603.setVisibility(View.VISIBLE);
                    edt_603.setVisibility(View.VISIBLE);
                } else {
                    txt_603.setVisibility(View.GONE);
                    edt_603.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edt_607.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = parent.getItemAtPosition(position).toString();

                if (temp.equals("1. Bantuan Konsumtif")) {
                    txt_608.setVisibility(View.VISIBLE);
                    edt_608.setVisibility(View.VISIBLE);
                    txt_609.setVisibility(View.VISIBLE);
                    edt_609.setVisibility(View.VISIBLE);
                    txt_610.setVisibility(View.VISIBLE);
                    edt_610.setVisibility(View.VISIBLE);
                    txt_611.setVisibility(View.VISIBLE);
                    edt_611.setVisibility(View.VISIBLE);
//                    txt_612.setVisibility(View.VISIBLE);
//                    edt_612.setVisibility(View.VISIBLE);

                    txt_613.setVisibility(View.GONE);
                    edt_613.setVisibility(View.GONE);
                    txt_614.setVisibility(View.GONE);
                    edt_614.setVisibility(View.GONE);
                    txt_615.setVisibility(View.GONE);
                    edt_615.setVisibility(View.GONE);
                    txt_616.setVisibility(View.GONE);
                    edt_616.setVisibility(View.GONE);

                    txt_617.setVisibility(View.GONE);
                    edt_617.setVisibility(View.GONE);
                    txt_618.setVisibility(View.GONE);
                    edt_618.setVisibility(View.GONE);

                } else if (temp.equals("2. Bantuan Produktif")) {
                    txt_608.setVisibility(View.GONE);
                    edt_608.setVisibility(View.GONE);
                    txt_609.setVisibility(View.GONE);
                    edt_609.setVisibility(View.GONE);
                    txt_610.setVisibility(View.GONE);
                    edt_610.setVisibility(View.GONE);
                    txt_611.setVisibility(View.GONE);
                    edt_611.setVisibility(View.GONE);
                    txt_612.setVisibility(View.GONE);
                    edt_612.setVisibility(View.GONE);

                    txt_613.setVisibility(View.VISIBLE);
                    edt_613.setVisibility(View.VISIBLE);
                    txt_614.setVisibility(View.VISIBLE);
                    edt_614.setVisibility(View.VISIBLE);
                    txt_615.setVisibility(View.VISIBLE);
                    edt_615.setVisibility(View.VISIBLE);
//                    txt_616.setVisibility(View.VISIBLE);
//                    edt_616.setVisibility(View.VISIBLE);
                    txt_617.setVisibility(View.VISIBLE);
                    edt_617.setVisibility(View.VISIBLE);
                    txt_618.setVisibility(View.VISIBLE);
                    edt_618.setVisibility(View.VISIBLE);
                } else {
                    txt_608.setVisibility(View.VISIBLE);
                    edt_608.setVisibility(View.VISIBLE);
                    txt_609.setVisibility(View.VISIBLE);
                    edt_609.setVisibility(View.VISIBLE);
                    txt_610.setVisibility(View.VISIBLE);
                    edt_610.setVisibility(View.VISIBLE);
                    txt_611.setVisibility(View.VISIBLE);
                    edt_611.setVisibility(View.VISIBLE);
//                    txt_612.setVisibility(View.VISIBLE);
//                    edt_612.setVisibility(View.VISIBLE);

                    txt_613.setVisibility(View.VISIBLE);
                    edt_613.setVisibility(View.VISIBLE);
                    txt_614.setVisibility(View.VISIBLE);
                    edt_614.setVisibility(View.VISIBLE);
                    txt_615.setVisibility(View.VISIBLE);
                    edt_615.setVisibility(View.VISIBLE);
                    // txt_616.setVisibility(View.VISIBLE);
                    // edt_616.setVisibility(View.VISIBLE);
                    txt_617.setVisibility(View.VISIBLE);
                    edt_617.setVisibility(View.VISIBLE);
                    txt_618.setVisibility(View.VISIBLE);
                    edt_618.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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


    private VerificationError saveAndNext(View view) {
        try {
            Prefs.remove(StaticStrings.M1_601);
            Prefs.remove(StaticStrings.M1_602);
            Prefs.remove(StaticStrings.M1_603);
            Prefs.remove(StaticStrings.M1_604);
            Prefs.remove(StaticStrings.M1_605);
            Prefs.remove(StaticStrings.M1_606);
            Prefs.remove(StaticStrings.M1_607);
            Prefs.remove(StaticStrings.M1_608);
            Prefs.remove(StaticStrings.M1_609);
            Prefs.remove(StaticStrings.M1_610);
            Prefs.remove(StaticStrings.M1_611);
            Prefs.remove(StaticStrings.M1_612);
            Prefs.remove(StaticStrings.M1_613);
            Prefs.remove(StaticStrings.M1_614);
            Prefs.remove(StaticStrings.M1_615);
            Prefs.remove(StaticStrings.M1_616);
            Prefs.remove(StaticStrings.M1_617);
            Prefs.remove(StaticStrings.M1_618);

            Prefs.putString(StaticStrings.M1_601, edt_601.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M1_602, edt_602.getSelectedItem().toString());

            if (edt_603.getVisibility() == View.VISIBLE) {
                Prefs.putString(StaticStrings.M1_603, edt_603.getSelectedItem().toString());
            }

            if (edt_604.getText().toString().isEmpty() || edt_604_lanjut.getText().toString().isEmpty())
                throw new FormError("Mohon lengkapi bulan dan tahun.");

            if (edt_604_lanjut.getText().toString().length() < 4)
                throw new FormError("Pengisian tahun harus 4 digit.");

            if (edt_605.getText().toString().isEmpty())
                throw new FormError("Pendapatan keluarga tidak boleh kosong.");

            if (edt_606.getText().toString().isEmpty())
                throw new FormError("Jumlah menerima bantuan zakat tidak boleh kosong.");

            Prefs.putString(StaticStrings.M1_604, edt_604.getText().toString() + "/" + edt_604_lanjut.getText().toString());
            Prefs.putString(StaticStrings.M1_605, edt_605.getText().toString() + " " + spn_605.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M1_606, edt_606.getText().toString());

            Prefs.putString(StaticStrings.M1_607, edt_607.getSelectedItem().toString());

            if (edt_605.getText().length() < 4) {
                throw new FormError("Nominal harus diisi lebih dari 4 angka");
            } else if (edt_607.getSelectedItem().toString().equals("1. Bantuan Konsumtif")){
                if (edt_608.getText().length() < 4 || edt_609.getText().length() < 4
                        || edt_610.getText().length() < 4 || edt_611.getText().length() < 4) {
                    throw new FormError("Nominal harus diisi lebih dari 4 angka");
                }
            } else if (edt_607.getSelectedItem().toString().equals("2. Bantuan Produktif")){
                if (edt_613.getText().length() < 4 || edt_614.getText().length() < 4
                        || edt_615.getText().length() < 4 || edt_618.getText().length() < 4) {
                    throw new FormError("Nominal harus diisi lebih dari 4 angka");
                }
            } else if (edt_607.getSelectedItem().toString().equals("3. Keduanya")){
                if (edt_608.getText().length() < 4 || edt_609.getText().length() < 4
                        || edt_610.getText().length() < 4 || edt_611.getText().length() < 4 ||
                        edt_613.getText().length() < 4 || edt_614.getText().length() < 4
                        || edt_615.getText().length() < 4 || edt_618.getText().length() < 4) {
                    throw new FormError("Nominal harus diisi lebih dari 4 angka");
                }
            }

            Prefs.putString(StaticStrings.M1_608, edt_608.getVisibility() == View.VISIBLE ? edt_608.getText().toString() : "0");
            Prefs.putString(StaticStrings.M1_609, edt_609.getVisibility() == View.VISIBLE ? edt_609.getText().toString() : "0");
            Prefs.putString(StaticStrings.M1_610, edt_610.getVisibility() == View.VISIBLE ? edt_610.getText().toString() : "0");
            Prefs.putString(StaticStrings.M1_611, edt_611.getVisibility() == View.VISIBLE ? edt_611.getText().toString() : "0");
            Prefs.putString(StaticStrings.M1_612, edt_612.getVisibility() == View.VISIBLE ? edt_612.getText().toString() : "0");
            Prefs.putString(StaticStrings.M1_613, edt_613.getVisibility() == View.VISIBLE ? edt_613.getText().toString() : "0");
            Prefs.putString(StaticStrings.M1_614, edt_614.getVisibility() == View.VISIBLE ? edt_614.getText().toString() : "0");
            Prefs.putString(StaticStrings.M1_615, edt_615.getVisibility() == View.VISIBLE ? edt_615.getText().toString() : "0");
            Prefs.putString(StaticStrings.M1_616, edt_616.getVisibility() == View.VISIBLE ? edt_616.getText().toString() : "0");

            if (edt_617.getVisibility() == View.VISIBLE) {
                int selectedId = edt_617.getCheckedRadioButtonId();
                RadioButton M1_617 = view.findViewById(selectedId);

                if (selectedId == -1){
                    throw new FormError("Opsi ya/tidak pada pilihan 17 harus dipilih.");
                }

                Prefs.putString(StaticStrings.M1_617, M1_617.getText().toString());
            } else {
                Prefs.putString(StaticStrings.M1_617, "-1. Nochoice");
            }

            Prefs.putString(StaticStrings.M1_618, edt_618.getVisibility() == View.VISIBLE ? edt_618.getText().toString() : "0");

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
        Utils.log("FRAGMENT PAGE 6 ONSELECTED");

        isSuccess = false;

        new Handler().postDelayed(() -> {

            if (SurveyKDZActivity.data != null) {
                KajianDampakZakatPojo data = SurveyKDZActivity.data;

                List<String> list601 = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.items_modul1_601)));
                edt_601.setSelection(list601.indexOf(Prefs.getString(StaticStrings.M1_601, "")));

                List<String> list602 = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.items_modul1_602)));
                edt_602.setSelection(list602.indexOf(Prefs.getString(StaticStrings.M1_602, "")));

                List<String> list603 = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.items_modul1_603)));
                edt_603.setSelection(list603.indexOf(Prefs.getString(StaticStrings.M1_603, "")));

                Utils.log(("PREF PREF " + Prefs.getString(StaticStrings.M1_604, "")));

                edt_604.setText(Prefs.getString(StaticStrings.M1_604, "").split("/")[0]);
                edt_604_lanjut.setText(Prefs.getString(StaticStrings.M1_604, "").split("/")[1]);

                List<String> list605 = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.items_per)));
                String per = "";
                String m1_605 = Prefs.getString(StaticStrings.M1_605, "");
                if (m1_605.contains("per hari")){
                    per = "per hari";
                } else if (m1_605.contains("per bulan")) {
                    per = "per bulan";
                } else if (m1_605.contains("per tahun")){
                    per = "per tahun";
                }

                spn_605.setSelection(list605.indexOf(per));

                edt_605.setText(m1_605.split(" " + per)[0]);

                edt_606.setText(Prefs.getString(StaticStrings.M1_606, ""));

                List<String> list607 = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.items_modul1_607)));
                edt_607.setSelection(list607.indexOf(Prefs.getString(StaticStrings.M1_607, "")));

                edt_608.setText(Prefs.getString(StaticStrings.M1_608, ""));
                edt_609.setText(Prefs.getString(StaticStrings.M1_609, ""));
                edt_610.setText(Prefs.getString(StaticStrings.M1_610, ""));
                edt_611.setText(Prefs.getString(StaticStrings.M1_611, ""));
                edt_612.setText(Prefs.getString(StaticStrings.M1_612, ""));
                edt_613.setText(Prefs.getString(StaticStrings.M1_613, ""));
                edt_614.setText(Prefs.getString(StaticStrings.M1_614, ""));
                edt_615.setText(Prefs.getString(StaticStrings.M1_615, ""));
                edt_616.setText(Prefs.getString(StaticStrings.M1_616, ""));

                ((RadioGroup)rootView.findViewById(R.id.edt1_617)).clearCheck();
                String m1_617 = Prefs.getString(StaticStrings.M1_617, "");
                if (!m1_617.isEmpty())
                if (m1_617.equals("1. Ya")){
                    ((RadioButton) rootView.findViewById(R.id.radioYa617)).setChecked(true);
                } else {
                    ((RadioButton) rootView.findViewById(R.id.radioTidak617)).setChecked(true);
                }

                edt_618.setText(Prefs.getString(StaticStrings.M1_618, ""));
            }

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