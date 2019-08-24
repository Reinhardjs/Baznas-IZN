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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.City;
import com.karyastudio.izn.dao.generateSchema.Provinsi;
import com.karyastudio.izn.dao.managerSchema.CityManager;
import com.karyastudio.izn.dao.managerSchema.ProvinsiManager;
import com.karyastudio.izn.utils.FormError;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class FragmentModul1101 extends Fragment implements BlockingStep {
    private FragmentManager fms;
    private EditText edt1_nama;
    private Spinner spinner_provinsi;
    private Spinner spinner_kota;
    private EditText et_kecamatan;
    private EditText et_kelurahan;
    private RadioGroup radio_kota_desa;
    private EditText et_nama_kepala;
    private EditText et_jlh_keluarga;
    private EditText et_no_hp;
    private EditText et_alamat;

    private Button btnNext;
    private View rootView;
    private boolean isSuccess;
    private ArrayList<String> provinsiItems;
    private ArrayList<String> cityItems;
    private ArrayAdapter<String> spinnerArrayAdapter3;

    public FragmentModul1101() {

    }

    // method ini hanya dipanggil sekali untuk fragment ini
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // jangan hapus fragment ini saat activity dibuat ulang.
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_modul1_101, container, false);

        Utils.log("FRAGMENT PAGE 1 ONCREATEVIEW");

//        fms = ((MainActivity) getActivity()).fms;
        // add back arrow to toolbar

        Toolbar toolbar = rootView.findViewById(R.id.toolbar6);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        edt1_nama = rootView.findViewById(R.id.edt1_100);
        spinner_provinsi = rootView.findViewById(R.id.spinner_provinsi);
        spinner_kota = rootView.findViewById(R.id.spinner_kota);
        et_kecamatan = rootView.findViewById(R.id.et_kecamatan);
        et_kelurahan = rootView.findViewById(R.id.et_kelurahan);
        radio_kota_desa = rootView.findViewById(R.id.radio_kota_desa);
        et_nama_kepala = rootView.findViewById(R.id.et_nama_kepala);
        et_jlh_keluarga = rootView.findViewById(R.id.et_jlh_keluraga);
        et_no_hp = rootView.findViewById(R.id.et_no_hp);
        et_alamat = rootView.findViewById(R.id.et_alamat);
        btnNext = rootView.findViewById(R.id.btn_next1_101);


//        btnNext.setVisibility(View.GONE);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.log("CLICKED");

                VerificationError verificationError = saveAndNext(rootView);
                if (verificationError != null){
                    String message = verificationError.getErrorMessage();
                    Utils.toast(getContext(), message);
                }

            }
        });

        //provinsi
        // String[] provinsiItems = new String[ProvinsiManager.loadAll(getActivity()).size()+1];
        provinsiItems = new ArrayList<>();
        List<Provinsi> provinsiList = ProvinsiManager.loadAll(getActivity());

        provinsiItems.add("Pilih Provinsi");

        for (int x = 1; x < ProvinsiManager.loadAll(getActivity()).size()+1; x++){
            provinsiItems.add(provinsiList.get(x-1).getPro_code()+". "+provinsiList.get(x-1).getPro_province());
            // provinsiTemp[x] = provinsiList.get(x).getPro_province();
        }

        spinnerArrayAdapter3 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style, provinsiItems
        );

        spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_style);
        if (spinner_provinsi.getAdapter() == null) {
            spinner_provinsi.setAdapter(spinnerArrayAdapter3);
            spinner_provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (parent.getSelectedItem() != null) {
//                if (!spinner_provinsi.getSelectedItem().toString().equals("Pilih Provinsi")) {
                        List<City> cityList = CityManager.loadByProCode(getContext(), parent.getSelectedItem().toString().substring(0, 2));
                        // String[] cityItems = new String[cityList.size() + 1];
                        cityItems = new ArrayList<>();

                        cityItems.add("Pilih Kota");
                        for (int x = 1; x < cityList.size() + 1; x++) {
                            cityItems.add(cityList.get(x - 1).getCit_code() + ". " + cityList.get(x - 1).getPro_city());
                            // cityTemp[x] = cityList.get(x).getPro_city();
                        }

                        ArrayAdapter<String> spinnerArrayAdapter4 = new ArrayAdapter<String>(
                                rootView.getContext(), R.layout.spinner_style, cityItems
                        );

                        spinnerArrayAdapter4.setDropDownViewResource(R.layout.spinner_style);
                        spinner_kota.setAdapter(spinnerArrayAdapter4);

                        spinner_kota.setSelection(cityItems.indexOf(Prefs.getString(StaticStrings.M1_102, "")));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        return rootView;
    }

    private VerificationError saveAndNext(View view){

        try {
            Prefs.remove(StaticStrings.M1_nama);
            Prefs.remove(StaticStrings.M1_101);
            Prefs.remove(StaticStrings.M1_102);
            Prefs.remove(StaticStrings.M1_103);
            Prefs.remove(StaticStrings.M1_104);
            Prefs.remove(StaticStrings.M1_105);
            Prefs.remove(StaticStrings.M1_106);
            Prefs.remove(StaticStrings.M1_107);
            Prefs.remove(StaticStrings.M1_108);
            Prefs.remove(StaticStrings.M1_109);
            Prefs.remove(StaticStrings.M1_110);

            int selectedId = radio_kota_desa.getCheckedRadioButtonId();
            RadioButton M1_105 = view.findViewById(selectedId);

            if (edt1_nama.getText().toString().isEmpty()) throw new FormError("Nama tidak boleh kosong.");
            else if (spinner_provinsi.getSelectedItem().toString().equals(provinsiItems.get(0)))
                throw new FormError("Provinsi harus dipilih.");
            else if (spinner_kota.getSelectedItem().toString().equals(cityItems.get(0)))
                throw new FormError("Kota harus dipilih.");
            else if (et_kecamatan.getText().toString().isEmpty()) throw new FormError("Kecamatan tidak boleh kosong.");
            else if (et_kelurahan.getText().toString().isEmpty()) throw new FormError("Kelurahan tidak boleh kosong.");
            else if (selectedId == -1) throw new Exception("Silakan pilih opsi klasifikasi desa/kota");
            else if (et_nama_kepala.getText().toString().isEmpty()) throw new FormError("Nama krt tidak boleh kosong.");
            else if (et_jlh_keluarga.getText().toString().isEmpty()) throw new FormError("Jumlah keluarga tidak boleh kosong.");
            // else if (et_no_hp.getText().toString().isEmpty()) throw new FormError("No telp tidak boleh kosong.");
            else if (et_alamat.getText().toString().isEmpty()) throw new FormError("Alamat tidak boleh kosong.");

            String no_hptelp = et_no_hp.getText().toString();
            if (!no_hptelp.isEmpty() && !no_hptelp.equals("0")){
                if (et_no_hp.getText().toString().length() < 10) throw new FormError("No telp harus diisi 10 digit atau lebih.");
            }

            Prefs.putString(StaticStrings.M1_nama, edt1_nama.getText().toString());
            Prefs.putString(StaticStrings.M1_101, spinner_provinsi.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M1_102, spinner_kota.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M1_103, et_kecamatan.getText().toString());
            Prefs.putString(StaticStrings.M1_104, et_kelurahan.getText().toString());

            Prefs.putString(StaticStrings.M1_105, M1_105.getText().toString());
            Prefs.putString(StaticStrings.M1_107, et_nama_kepala.getText().toString());
            Prefs.putString(StaticStrings.M1_108, et_jlh_keluarga.getText().toString());
            Prefs.putString(StaticStrings.M1_109, no_hptelp.isEmpty() ? "0" : et_no_hp.getText().toString());
            Prefs.putString(StaticStrings.M1_110, et_alamat.getText().toString());

            Prefs.putString(StaticStrings.M1_created_at, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(Calendar.getInstance().getTime()));
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
        Utils.log("FRAGMENT PAGE 1 ONSELECTED");

        isSuccess = false;

        new Handler().postDelayed(() -> {

            //if (SurveyKDZActivity.data != null){
            edt1_nama.setText(Prefs.getString(StaticStrings.M1_nama, ""));
            et_kecamatan.setText(Prefs.getString(StaticStrings.M1_103, ""));
            et_kelurahan.setText(Prefs.getString(StaticStrings.M1_104, ""));

            et_nama_kepala.setText(Prefs.getString(StaticStrings.M1_107, ""));
            et_jlh_keluarga.setText(Prefs.getString(StaticStrings.M1_108, ""));
            et_no_hp.setText(Prefs.getString(StaticStrings.M1_109, ""));

            et_alamat.setText(Prefs.getString(StaticStrings.M1_110, ""));

            String m1_105 = Prefs.getString(StaticStrings.M1_105, "");
            ((RadioGroup)rootView.findViewById(R.id.radio_kota_desa)).clearCheck();

            // Setiap ada pilihan radio button, yang jenisnya dua pilihan.
            // Selalu check bahwa nilainya tidak sama dengan "kosong"
            // kalau "kosong" nanti selalu kena ke logika else nya.
            if (!m1_105.isEmpty() && !m1_105.equals("kosong"))
            if (m1_105.equals("1. Perkotaan")){
                ((RadioButton)radio_kota_desa.findViewById(R.id.radioYa)).setChecked(true);
            } else {
                // radio_kota_desa.check(R.id.radioTidak);
                ((RadioButton)radio_kota_desa.findViewById(R.id.radioTidak)).setChecked(true);
            }

            spinner_provinsi.setSelection(provinsiItems.indexOf(Prefs.getString(StaticStrings.M1_101, "")));
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
