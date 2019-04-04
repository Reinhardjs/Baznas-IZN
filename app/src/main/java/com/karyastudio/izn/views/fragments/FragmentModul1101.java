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

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.Provinsi;
import com.karyastudio.izn.dao.managerSchema.ProvinsiManager;
import com.karyastudio.izn.model.api.city.DataCity;
import com.karyastudio.izn.model.api.province.DataProvinsi;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentModul1101 extends Fragment {
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

    public FragmentModul1101() {

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul1_101, container, false);
        fms = ((MainActivity) getActivity()).fms;
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

        //provinsi
        getDataProvinsi();

        String [] provinsiTemp = new String[ProvinsiManager.loadAll(getActivity()).size()];
        List<Provinsi> provinsiList = ProvinsiManager.loadAll(getActivity());
        for (int x = 0; x < ProvinsiManager.loadAll(getActivity()).size(); x++){
            provinsiTemp [x] = provinsiList.get(x).getPro_code()+". "+provinsiList.get(x).getPro_province();
        }

        ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,provinsiTemp
        );

        spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_style);

        edt1_101.setAdapter(spinnerArrayAdapter3);

        edt1_101.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDataCity( parent.getSelectedItem().toString().substring(0, 2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt1_108.getText().toString().matches("")){
                    Toast.makeText(getContext(),"Harus Mengisi jumlah Keluarga",Toast.LENGTH_SHORT).show();
                }else {
                    saveAndNext(rootView);
                    fms.beginTransaction().replace(R.id.content_frames, new FragmentModul1201()).addToBackStack("4").commit();

                }
            }
        });
        return rootView;
    }


    private void saveAndNext(View view){
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

    }

    private void getDataProvinsi(){
        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<DataProvinsi> result = apiService.getProvince(StaticStrings.API_KEY);
        result.enqueue(new Callback<DataProvinsi>() {
            @Override
            public void onResponse(Call<DataProvinsi> call, Response<DataProvinsi> response) {
                try {
                    List<Provinsi> list = new ArrayList<>();
                    for (int i = 0; i < response.body().getData().getProvince().size(); i++) {
                        list.add(new Provinsi(
                                response.body().getData().getProvince().get(i).getProId(),
                                response.body().getData().getProvince().get(i).getProCode(),
                                response.body().getData().getProvince().get(i).getProProvince()
                        ));
                    }

                    ProvinsiManager.removeAll(getActivity());
                    ProvinsiManager.insertOrReplaceArray(getActivity(), list);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<DataProvinsi> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getDataCity(String idCity){
        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<DataCity> result = apiService.getCity(StaticStrings.API_KEY, idCity);
        result.enqueue(new Callback<DataCity>() {
            @Override
            public void onResponse(Call<DataCity> call, Response<DataCity> response) {
                try {
                    String cityTemp [] = new String[response.body().getData().getProvince().size()];
                    for (int i = 0; i < response.body().getData().getProvince().size(); i++) {
                        cityTemp [i] = response.body().getData().getProvince().get(i).getCitCode()+". "+response.body().getData().getProvince().get(i).getProCity();
                    }

                    ArrayAdapter<String> spinnerArrayAdapter4 = new ArrayAdapter<String>(
                            getActivity(),R.layout.spinner_style,cityTemp
                    );
                    spinnerArrayAdapter4.setDropDownViewResource(R.layout.spinner_style);

                    spinnerArrayAdapter4.notifyDataSetChanged();
                    edt1_102.setAdapter(spinnerArrayAdapter4);
                    spinnerArrayAdapter4.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<DataCity> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
