package com.karyastudio.izn.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.google.android.play.core.install.model.ActivityResult;
import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.model.api.login.Login;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.karyastudio.izn.utils.Utils.log;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnSignIn;
    private TextView btnGoToDaftar;
    private boolean connectInternet = false;
    private Disposable networkDisposable;
    private MaterialDialog dialog;
    private TextView forgotTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btn_change_pass);
        btnGoToDaftar = findViewById(R.id.btn_goto_daftar);
        forgotTextView = findViewById(R.id.forgotTextView);

        Utils.inizializePrefs(this,getPackageName());

//        AppUpdater appUpdater = new AppUpdater(this);
//        appUpdater.start();


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


        networkDisposable = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> Utils.log("ERROR RX NETWORK : " + error.toString()))
                .subscribe(isConnected -> {

                    Utils.log("ON NETWORK CHANGE : " + isConnected);

                    if (isConnected){
                        connectInternet = true;
                    } else {
                        connectInternet = false;
                    }

                }, error -> Utils.log("ERROR RX NETWORK : " + error.getMessage()));

        dialog = new MaterialDialog.Builder(this)
                .title("Login")
                .content("sedang melakukan autentikasi")
                .progress(true, 0)
                .canceledOnTouchOutside(false).build();

        forgotTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        Utils.checkAppUpdates(LoginActivity.this);
    }

    @Override
    public void onDestroy(){
        networkDisposable.dispose();
        super.onDestroy();
    }

    private void login(String user , String pass) {
        if (!connectInternet && !Utils.isOnline(getApplicationContext())){
            Toast.makeText(this, "Maaf, Anda tidak tersambung ke internet", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
            Utils.toast(getApplicationContext(), StaticStrings.ISI_SEMUA);
        } else {
            dialog.show();

            BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
            Call<Login> result = apiService.login(StaticStrings.API_KEY, user, pass);
            result.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {

                    try {
                        if (response.body().getStatus().equalsIgnoreCase("success")) {

                            if (response.body().getData().getPetugas().getUStatus().equals("Tidak Aktif")) {
                                Toast.makeText(getApplicationContext(), "Akun Anda belum aktif. Silakan hubungi admin untuk aktivasi", Toast.LENGTH_LONG).show();
                            } else {
                                Utils.log("LOGIN SUCCESS");
                                Prefs.putString("UID", String.valueOf(response.body().getData().getPetugas().getUId()));
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                Prefs.putBoolean(StaticStrings.SHOWCASE_OPEN1, true);
                                Prefs.putBoolean(StaticStrings.SHOWCASE_OPEN2, true);
                                finish();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Login bermasalah, pastikan Anda terhubung ke internet", Toast.LENGTH_LONG).show();
                        Utils.log("ON EXCEPTION " + e.toString());
                    }

                    dialog.dismiss();
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    dialog.dismiss();
                    t.printStackTrace();
                    Utils.log("ON FAILURE " + t.toString());
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            if (resultCode != RESULT_OK) {
                log("Update flow failed! Result code: " + resultCode);

                // If the update is cancelled or fails,
                // you can request to start the update again.

                switch (resultCode){
                    case RESULT_CANCELED :
                        // do when result canceled
                        break;
                    case ActivityResult.RESULT_IN_APP_UPDATE_FAILED :
                        // do when result update failed;
                        break;
                }

                Utils.manualUpdateRequest(LoginActivity.this);
            }
        }
    }

}
