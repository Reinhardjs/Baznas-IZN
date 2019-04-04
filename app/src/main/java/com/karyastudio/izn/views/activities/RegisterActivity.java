package com.karyastudio.izn.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karyastudio.izn.R;
import com.karyastudio.izn.model.api.daftar.Daftar;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtEmail;
    private EditText txtPhone;
    private EditText txtNama;
    private Button btnSignUp;
    private Button btnGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtUsername = findViewById(R.id.username_reg);
        txtPassword = findViewById(R.id.password_reg);
        txtEmail = findViewById(R.id.email);
        txtPhone = findViewById(R.id.phone);
        txtNama = findViewById(R.id.name);

        btnSignUp = findViewById(R.id.btn_exe_daftar);
        btnGoToLogin = findViewById(R.id.btn_goto_login);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(txtUsername.getText().toString(),
                        txtPassword.getText().toString(),
                        txtEmail.getText().toString(),
                        txtNama.getText().toString(),
                        "",
                        txtPhone.getText().toString()
                );
            }
        });

        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private void register(String username,
                          String password,
                          String email,
                          String name,
                          String alamat,
                          String phone) {
        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<Daftar> result = apiService.daftar(StaticStrings.API_KEY, username, email, password, alamat, phone, name);
        result.enqueue(new Callback<Daftar>() {
            @Override
            public void onResponse(Call<Daftar> call, Response<Daftar> response) {
                try {

                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                            Toast.makeText(getApplicationContext(), "Berhasil Terdaftar", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Daftar> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}