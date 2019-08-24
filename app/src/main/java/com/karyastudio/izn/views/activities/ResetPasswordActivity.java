package com.karyastudio.izn.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.karyastudio.izn.R;
import com.karyastudio.izn.model.api.simple.SimpleResponse;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText emailEditText;
    Button buttonChangePass;
    TextView textView;
    TextView successTextView;
    private MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        emailEditText = findViewById(R.id.email);
        buttonChangePass = findViewById(R.id.btn_change_pass);
        successTextView = findViewById(R.id.successMessage);
        textView = findViewById(R.id.forgotTextView);

        dialog = new MaterialDialog.Builder(this)
                .title("Ganti Password")
                .content("sedang melakukan pengiriman email")
                .progress(true, 0)
                .canceledOnTouchOutside(false).build();

        buttonChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                Retrofit retrofit;
                retrofit = new Retrofit.Builder()
                        .baseUrl(BaseApi.BaseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                BaseApi apiService = retrofit.create(BaseApi.class);
                Call<SimpleResponse> result = apiService.resetPassword(StaticStrings.API_KEY, emailEditText.getText().toString());
                result.enqueue(new Callback<SimpleResponse>() {
                    @Override
                    public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> simpleResponse) {

                        try {
                            if (simpleResponse.body().getStatus().equalsIgnoreCase("success")) {

                                emailEditText.setVisibility(View.GONE);
                                buttonChangePass.setVisibility(View.GONE);
                                textView.setVisibility(View.GONE);
                                successTextView.setVisibility(View.VISIBLE);
                                successTextView.setText(simpleResponse.body().getMessage());


                            } else {
                                Toast.makeText(getApplicationContext(), simpleResponse.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Request ke server bermasalah, pastikan Anda terhubung ke internet", Toast.LENGTH_LONG).show();
                            Utils.log("ON EXCEPTION " + e.toString());
                        }

                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<SimpleResponse> call, Throwable t) {
                        dialog.dismiss();
                        t.printStackTrace();
                        Utils.log("ON FAILURE " + t.toString());
                    }
                });

            }
        });
    }
}
