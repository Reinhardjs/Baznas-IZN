package com.karyastudio.izn.views.fragments;

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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.City;
import com.karyastudio.izn.dao.generateSchema.Provinsi;
import com.karyastudio.izn.dao.managerSchema.CityManager;
import com.karyastudio.izn.dao.managerSchema.ProvinsiManager;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyKDZActivity;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FragmentModul1101 extends Fragment implements Step {
    private FragmentManager fms;
    private EditText edt1_nama;
    private Spinner edt1_101;
    private Spinner edt1_102;
    private EditText edt1_103;
    private EditText edt1_104;
    private RadioGroup edt1_105;
    private EditText edt1_107;
    private EditText edt1_108;
    private EditText edt1_109;
    private EditText edt1_110;



    private Button btnNext;
    private View rootView;
    private boolean isSuccess;

    public FragmentModul1101() {

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
        rootView = inflater.inflate(R.layout.fragment_modul1_101, container, false);
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
        edt1_101 = rootView.findViewById(R.id.edt1_101);
        edt1_102 = rootView.findViewById(R.id.edt1_102);
        edt1_103 = rootView.findViewById(R.id.edt1_103);
        edt1_104 = rootView.findViewById(R.id.edt1_104);
        edt1_105 = rootView.findViewById(R.id.edt1_105);
        edt1_107 = rootView.findViewById(R.id.edt1_107);
        edt1_108 = rootView.findViewById(R.id.edt1_108);
        edt1_109 = rootView.findViewById(R.id.edt1_109);
        edt1_110 = rootView.findViewById(R.id.edt1_110);
        btnNext = rootView.findViewById(R.id.btn_next1_101);

        if (SurveyKDZActivity.data != null){
            edt1_nama.setText(SurveyKDZActivity.data.getFk_nama());
            edt1_103.setText(SurveyKDZActivity.data.getFk_103_kecamatan());
            edt1_104.setText(SurveyKDZActivity.data.getFk_104_desa());
            edt1_107.setText(SurveyKDZActivity.data.getFk_107_nama_kepala_rumah());
            edt1_109.setText(SurveyKDZActivity.data.getFk_109_nomor_hp());
            edt1_108.setText(SurveyKDZActivity.data.getFk_108_jumlah_anggota_rumah());
            edt1_110.setText(SurveyKDZActivity.data.getFk_110_alamat_lengkap());
        }

        //provinsi
        String[] provinsiTemp = new String[ProvinsiManager.loadAll(getActivity()).size()+1];
        List<Provinsi> provinsiList = ProvinsiManager.loadAll(getActivity());

        provinsiTemp[0] = "Pilih Provinsi";
        for (int x = 1; x < ProvinsiManager.loadAll(getActivity()).size()+1; x++){
            provinsiTemp[x] = provinsiList.get(x-1).getPro_code()+". "+provinsiList.get(x-1).getPro_province();
            // provinsiTemp[x] = provinsiList.get(x).getPro_province();
        }
        ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style, provinsiTemp
        );

        spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_style);
        edt1_101.setAdapter(spinnerArrayAdapter3);
        edt1_101.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem() != null){
//                if (!edt1_101.getSelectedItem().toString().equals("Pilih Provinsi")) {
                    List<City> cityList = CityManager.loadByProCode(getContext(), parent.getSelectedItem().toString().substring(0, 2));
                    String[] cityTemp = new String[cityList.size() + 1];

                    cityTemp[0] = "Pilih Kota";
                    for (int x = 1; x < cityList.size()+1; x++) {
                        cityTemp[x] = cityList.get(x-1).getCit_code() + ". " + cityList.get(x-1).getPro_city();
                        // cityTemp[x] = cityList.get(x).getPro_city();
                    }

                    ArrayAdapter<String> spinnerArrayAdapter4 = new ArrayAdapter<String>(
                            rootView.getContext(), R.layout.spinner_style, cityTemp
                    );

                    spinnerArrayAdapter4.setDropDownViewResource(R.layout.spinner_style);
                    edt1_102.setAdapter(spinnerArrayAdapter4);
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
                if (edt1_108.getText().toString().matches("")){
                    Toast.makeText(getContext(),"Harus Mengisi jumlah Keluarga",Toast.LENGTH_SHORT).show();
                }else {
                    saveAndNext(rootView);
//                    fms.beginTransaction().replace(R.id.content_frames, new FragmentModul1201()).addToBackStack("4").commit();
                }
            }
        });

        return rootView;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {

        if (isSuccess){
            return null;
        }

        if (edt1_108.getText().toString().matches("")){
            return new VerificationError("Harus Mengisi jumlah Keluarga");
        } else {
            return saveAndNext(rootView);
//                    fms.beginTransaction().replace(R.id.content_frames, new FragmentModul1201()).addToBackStack("4").commit();
        }
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    private VerificationError saveAndNext(View view){
        try {
            Prefs.remove(StaticStrings.M1_nama);
            Prefs.remove(StaticStrings.M1_101);
            Prefs.remove(StaticStrings.M1_102);
            Prefs.remove(StaticStrings.M1_102);
            Prefs.remove(StaticStrings.M1_102);
            Prefs.remove(StaticStrings.M1_102);
            Prefs.remove(StaticStrings.M1_102);
            Prefs.remove(StaticStrings.M1_102);


            Prefs.putString(StaticStrings.M1_nama, edt1_nama.getText().toString());
            Prefs.putString(StaticStrings.M1_101, edt1_101.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M1_102, edt1_102.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M1_103, edt1_103.getText().toString());
            Prefs.putString(StaticStrings.M1_104, edt1_104.getText().toString());

            int selectedId = edt1_105.getCheckedRadioButtonId();
            RadioButton M1_105 = view.findViewById(selectedId);
            Prefs.putString(StaticStrings.M1_105, M1_105.getText().toString());
            Prefs.putString(StaticStrings.M1_107, edt1_107.getText().toString());
            Prefs.putString(StaticStrings.M1_108, edt1_108.getText().toString());
            Prefs.putString(StaticStrings.M1_109, edt1_109.getText().toString());
            Prefs.putString(StaticStrings.M1_110, edt1_110.getText().toString());

            Prefs.putString(StaticStrings.M1_created_at, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(Calendar.getInstance().getTime()));

            isSuccess = true;
            Utils.Toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN).show();
        } catch (Exception e){
            return new VerificationError("Mohon lengkapi form pada halaman ini");
        }

        return null;
    }
}
