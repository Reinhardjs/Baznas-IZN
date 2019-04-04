package com.karyastudio.izn.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.model.api.login.Login;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

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
        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<Login> result = apiService.login(StaticStrings.API_KEY,user,pass);
        result.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")){
                        if (response.body().getData().getPetugas().getUStatus().equals("Tidak Aktif")) {
                            Toast.makeText(getApplicationContext(),"Akun Anda belum aktif. Silahkan hubungi admin untuk aktivasi!", Toast.LENGTH_LONG).show();
                        } else{
                            Prefs.putString("UID", String.valueOf(response.body().getData().getPetugas().getUId()));
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Username dan password salah", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
