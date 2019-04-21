package com.karyastudio.izn.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.City;
import com.karyastudio.izn.dao.generateSchema.Provinsi;
import com.karyastudio.izn.dao.managerSchema.CityManager;
import com.karyastudio.izn.dao.managerSchema.ProvinsiManager;
import com.karyastudio.izn.model.api.city.DataCity;
import com.karyastudio.izn.model.api.login.Login;
import com.karyastudio.izn.model.api.province.DataProvinsi;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnSignIn;
    private Button btnGoToDaftar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btn_exe_login);
        btnGoToDaftar = findViewById(R.id.btn_goto_daftar);
        Utils.inizializePrefs(this,getPackageName());

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(txtUsername.getText().toString(), txtPassword.getText().toString());
            }
        });

        btnGoToDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

    }

    private void login(String user , String pass) {
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
            Utils.Toast(getApplicationContext(), StaticStrings.ISI_SEMUA).show();
        } else {
            BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
            Call<Login> result = apiService.login(StaticStrings.API_KEY, user, pass);
            result.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    try {
                        if (response.body().getStatus().equalsIgnoreCase("success")) {
                            if (response.body().getData().getPetugas().getUStatus().equals("Tidak Aktif")) {
                                Toast.makeText(getApplicationContext(), "Akun Anda belum aktif. Silahkan hubungi admin untuk aktivasi!", Toast.LENGTH_LONG).show();
                            } else {
                                getDataProvince();
                                getDataCity();
                                Prefs.putString("UID", String.valueOf(response.body().getData().getPetugas().getUId()));
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }


                        } else {
                            Toast.makeText(getApplicationContext(), "Email dan password salah", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Cek sekali lagi koneksi anda", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    private void getDataProvince(){
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

                    ProvinsiManager.removeAll(getApplicationContext());
                    ProvinsiManager.insertOrReplaceArray(getApplicationContext(), list);
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
    private void getDataCity(){
        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<DataCity> result = apiService.getCityAll(StaticStrings.API_KEY);
        result.enqueue(new Callback<DataCity>() {
            @Override
            public void onResponse(Call<DataCity> call, Response<DataCity> response) {
                try {
                    List<City> list = new ArrayList<>();
                    for (int i = 0; i < response.body().getData().getProvince().size(); i++) {
                        list.add(new City(
                                response.body().getData().getProvince().get(i).getProId(),
                                response.body().getData().getProvince().get(i).getProCode(),
                                response.body().getData().getProvince().get(i).getCitCode(),
                                response.body().getData().getProvince().get(i).getProProvince(),
                                response.body().getData().getProvince().get(i).getProCity()
                        ));
                    }

                    CityManager.removeAll(getApplicationContext());
                    CityManager.insertOrReplaceArray(getApplicationContext(), list);
                    Log.d("testes", "onResponse: "+list);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("testes", "onResponse: what"+e);
                }

            }

            @Override
            public void onFailure(Call<DataCity> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
