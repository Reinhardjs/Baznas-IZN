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
import android.widget.Spinner;
import android.widget.TextView;

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

public class FragmentModul2101 extends Fragment {
    private FragmentManager fms;
    private Spinner spinner_101;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private EditText edt_102;
    private Spinner edt_103;
    private Spinner edt_104;
    private Button btnNext;
    private String gotos;
    public FragmentModul2101(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_modul2_101, container, false);
        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar2);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

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
    gotos = "false";
        //lembaga
        String[] plants = new String[]{
                "1. BAZNAS Pusat",
                "2. BAZNAS Provinsi",
                "3. BAZNAS Kab/Kota",
                "4. LAZ Nasional",
                "5. LAZ Provinsi",
                "6. LAZ Kab/Kota"
        };

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        spinner_101.setAdapter(spinnerArrayAdapter);


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

        edt_103.setAdapter(spinnerArrayAdapter3);


        //getCity
        edt_103.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("apa"+parent.getSelectedItem().toString().substring(Math.max(parent.getSelectedItem().toString().length() - 2, 0)));
                getDataCity( parent.getSelectedItem().toString().substring(0, 2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });






        spinner_101.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = parent.getItemAtPosition(position).toString();
                if (temp.equals("1. BAZNAS Pusat")){
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
                    gotos = "true";
                }else  if(temp.equals("5. LAZ Provinsi")){
                    textView5.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.VISIBLE);
                    textView7.setVisibility(View.GONE);
                    edt_102.setVisibility(View.VISIBLE);
                    edt_103.setVisibility(View.VISIBLE);
                    edt_104.setVisibility(View.GONE);
                    gotos = "true";
                }else{
                    textView5.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.VISIBLE);
                    textView7.setVisibility(View.VISIBLE);
                    edt_102.setVisibility(View.VISIBLE);
                    edt_103.setVisibility(View.VISIBLE);
                    edt_104.setVisibility(View.VISIBLE);
                    gotos = "true";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext();
                if (gotos.equals("true")){
                    fms.beginTransaction().replace(R.id.content_frames, new FragmentModul2401()).addToBackStack("4").commit();
                }else {
                    fms.beginTransaction().replace(R.id.content_frames, new FragmentModul2201()).addToBackStack("4").commit();
                }
            }
        });
        return rootView;
    }

    private void saveAndNext(){
        //Pref disini (101,102,103,104) )di clear semua
        Prefs.remove(StaticStrings.M2_101);
        Prefs.remove(StaticStrings.M2_102);
        Prefs.remove(StaticStrings.M2_103);
        Prefs.remove(StaticStrings.M2_104);

        Prefs.putString(StaticStrings.M2_101,spinner_101.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M2_created_at, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(Calendar.getInstance().getTime()));

        if (edt_102.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_102,edt_102.getText().toString());
        }
        if (edt_103.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_103,edt_103.getSelectedItem().toString());
        }
        if (edt_104.getVisibility() == View.VISIBLE){
            Prefs.putString(StaticStrings.M2_104,edt_104.getSelectedItem().toString());
        }
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
                    edt_104.setAdapter(spinnerArrayAdapter4);
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