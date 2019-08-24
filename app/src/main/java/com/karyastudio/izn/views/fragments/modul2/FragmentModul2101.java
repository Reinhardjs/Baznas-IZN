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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.City;
import com.karyastudio.izn.dao.generateSchema.Provinsi;
import com.karyastudio.izn.dao.managerSchema.CityManager;
import com.karyastudio.izn.dao.managerSchema.ProvinsiManager;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyIZNActivity;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class FragmentModul2101 extends Fragment implements BlockingStep {

    private FragmentManager fms;
    private Spinner spinner_101;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private EasyTextInputLayout edt_102;
    private Spinner edt_103;
    private Spinner edt_104;
    private Button btnNext;
    private View rootView;
    private boolean isSuccess;

    private EasyForm easyForm;
    private ArrayList<String> typeList;
    private ArrayList<String> provinsiTemp;
    private ArrayList<String> cityTemp;

    public static String gotos;

    public FragmentModul2101(){

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
        rootView = inflater.inflate(R.layout.fragment_modul2_101, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar2);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        easyForm = rootView.findViewById(R.id.easyForm);

        spinner_101 = rootView.findViewById(R.id.edt_101);
        textView5 = rootView.findViewById(R.id.textView5);
        textView6 = rootView.findViewById(R.id.textView6);
        textView7 = rootView.findViewById(R.id.textView7);
        edt_102 = rootView.findViewById(R.id.edt_102);
        edt_103 = rootView.findViewById(R.id.edt_103);
        edt_104 = rootView.findViewById(R.id.edt_104);
        btnNext = rootView.findViewById(R.id.btn_next_101);
        textView5.setVisibility(View.GONE);
        textView6.setVisibility(View.GONE);
        textView7.setVisibility(View.GONE);
        edt_102.setVisibility(View.GONE);
        edt_103.setVisibility(View.GONE);
        edt_104.setVisibility(View.GONE);

        //lembaga
        String[] plants = new String[]{
                "0. Pilih",
                "1. BAZNAS Pusat",
                "2. BAZNAS Provinsi",
                "3. BAZNAS Kab/Kota",
                "4. LAZ Nasional",
                "5. LAZ Provinsi",
                "6. LAZ Kab/Kota"
        };
        typeList = new ArrayList<>(Arrays.asList(plants));

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        spinner_101.setAdapter(spinnerArrayAdapter);

        spinner_101.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = parent.getItemAtPosition(position).toString();

                if (temp.equals("1. BAZNAS Pusat") || temp.equals(typeList.get(0))){
                    textView5.setVisibility(View.GONE);
                    textView6.setVisibility(View.GONE);
                    textView7.setVisibility(View.GONE);
                    edt_102.setVisibility(View.GONE);
                    edt_103.setVisibility(View.GONE);
                    edt_104.setVisibility(View.GONE);
                }else  if(temp.equals("2. BAZNAS Provinsi")){
                    textView5.setVisibility(View.GONE);
                    textView6.setVisibility(View.VISIBLE);
                    textView7.setVisibility(View.GONE);
                    edt_102.setVisibility(View.GONE);
                    edt_103.setVisibility(View.VISIBLE);
                    edt_104.setVisibility(View.GONE);
                }else  if(temp.equals("3. BAZNAS Kab/Kota")){
                    textView5.setVisibility(View.GONE);
                    textView6.setVisibility(View.VISIBLE);
                    textView7.setVisibility(View.VISIBLE);
                    edt_102.setVisibility(View.GONE);
                    edt_103.setVisibility(View.VISIBLE);
                    edt_104.setVisibility(View.VISIBLE);
                }else  if(temp.equals("4. LAZ Nasional")){
                    textView5.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.GONE);
                    textView7.setVisibility(View.GONE);
                    edt_102.setVisibility(View.VISIBLE);
                    edt_103.setVisibility(View.GONE);
                    edt_104.setVisibility(View.GONE);
                }else  if(temp.equals("5. LAZ Provinsi")){
                    textView5.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.VISIBLE);
                    textView7.setVisibility(View.GONE);
                    edt_102.setVisibility(View.VISIBLE);
                    edt_103.setVisibility(View.VISIBLE);
                    edt_104.setVisibility(View.GONE);

                }else{
                    textView5.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.VISIBLE);
                    textView7.setVisibility(View.VISIBLE);
                    edt_102.setVisibility(View.VISIBLE);
                    edt_103.setVisibility(View.VISIBLE);
                    edt_104.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    private VerificationError saveAndNext(View rootView){
        isSuccess = false;

        try {

            //Pref disini (101,102,103,104) )di clear semua
            Prefs.remove(StaticStrings.M2_101);
            Prefs.remove(StaticStrings.M2_102);
            Prefs.remove(StaticStrings.M2_103);
            Prefs.remove(StaticStrings.M2_104);

            if (spinner_101.getSelectedItem().toString().equals(typeList.get(0)))
                throw new Exception();

            if (spinner_101.getSelectedItem().toString().contains("LAZ")){
                gotos = "true";
            } else {
                gotos = "false";
            }

            Prefs.putString(StaticStrings.M2_101, spinner_101.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M2_created_at, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(Calendar.getInstance().getTime()));

            if (edt_102.getVisibility() == View.VISIBLE) {
                easyForm.validate();

                if (!easyForm.isValid()) {
                    throw new Exception();
                }

                Prefs.putString(StaticStrings.M2_102, edt_102.getEditText().getText().toString());
            } else {
                Prefs.putString(StaticStrings.M2_102, "");
            }

            if (edt_103.getVisibility() == View.VISIBLE) {
                if (edt_103.getSelectedItem().toString().equals(provinsiTemp.get(0)))
                    throw new Exception();

                Prefs.putString(StaticStrings.M2_103, edt_103.getSelectedItem().toString());
            } else {
                Prefs.putString(StaticStrings.M2_103, "-1. Nochoice");
            }

            if (edt_104.getVisibility() == View.VISIBLE) {
                if (edt_104.getSelectedItem().toString().equals(cityTemp.get(0)))
                    throw new Exception();

                Prefs.putString(StaticStrings.M2_104, edt_104.getSelectedItem().toString());
            } else {
                Prefs.putString(StaticStrings.M2_104, "-1. Nochoice");
            }

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

        String m2_101 = Prefs.getString(StaticStrings.M2_101, "");
        Utils.log("SELECTED " + m2_101);

        if (!m2_101.isEmpty()) {
            spinner_101.setSelection(typeList.indexOf(m2_101));
            Utils.log("SELECTED " + m2_101);
        }

        String m2_102 = Prefs.getString(StaticStrings.M2_102, "");
        if (!m2_102.equals("")){
            edt_102.setVisibility(View.VISIBLE);
            edt_102.getEditText().setText(m2_102);
        }

        //provinsi
        provinsiTemp = new ArrayList<>();
        provinsiTemp.add("0. Pilih");

        List<Provinsi> provinsiList = ProvinsiManager.loadAll(getActivity());

        for (int x = 0; x < ProvinsiManager.loadAll(getActivity()).size(); x++){
            provinsiTemp.add(provinsiList.get(x).getPro_code()+". "+provinsiList.get(x).getPro_province());
        }

        ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style, provinsiTemp
        );

        spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_style);
        edt_103.setAdapter(spinnerArrayAdapter3);
        edt_103.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<City> cityList = CityManager.loadByProCode(getContext(),  parent.getSelectedItem().toString().substring(0, 2));
                cityTemp = new ArrayList<>();
                cityTemp.add("00. Pilih");

                for (int x = 0; x < cityList.size(); x++){
                    cityTemp.add(cityList.get(x).getCit_code()+". "+cityList.get(x).getPro_city());
                }

                ArrayAdapter<String> spinnerArrayAdapter4 = new ArrayAdapter<String>(
                        rootView.getContext(),R.layout.spinner_style,cityTemp
                );

                spinnerArrayAdapter4.setDropDownViewResource(R.layout.spinner_style);
                edt_104.setAdapter(spinnerArrayAdapter4);

                String m2_104 = Prefs.getString(StaticStrings.M2_104, "");
                if (!m2_104.isEmpty()){
                    edt_104.setSelection(cityTemp.indexOf(m2_104));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String m2_103 = Prefs.getString(StaticStrings.M2_103, "");
        if (!m2_103.isEmpty()){
            edt_103.setSelection(provinsiTemp.indexOf(m2_103));
        }
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        if (gotos.equals("true")){
            Prefs.remove(StaticStrings.M2_201);
            Prefs.remove(StaticStrings.M2_201yt);

            Prefs.remove(StaticStrings.M2_301);
            Prefs.remove(StaticStrings.M2_301yt);
            Prefs.remove(StaticStrings.M2_302);
            Prefs.remove(StaticStrings.M2_302yt);

            Prefs.remove(StaticStrings.M2_401);
            Prefs.remove(StaticStrings.M2_401yt);
            SurveyIZNActivity.getInstance().mStepperLayout.setCurrentStepPosition(3);
        } else {
            callback.goToNextStep();
        }
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onError(@NonNull VerificationError error) {
        Utils.toast(getContext(), error.getErrorMessage());
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