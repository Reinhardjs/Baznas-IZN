package com.karyastudio.izn;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.google.android.play.core.install.model.ActivityResult;
import com.karyastudio.izn.dao.generateSchema.City;
import com.karyastudio.izn.dao.generateSchema.IZN;
import com.karyastudio.izn.dao.generateSchema.Provinsi;
import com.karyastudio.izn.dao.managerSchema.CityManager;
import com.karyastudio.izn.dao.managerSchema.IZNManager;
import com.karyastudio.izn.dao.managerSchema.ProvinsiManager;
import com.karyastudio.izn.model.api.city.DataCity;
import com.karyastudio.izn.model.api.dataizn.DataIZN;
import com.karyastudio.izn.model.api.province.DataProvinsi;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.SetGet;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.LoginActivity;
import com.karyastudio.izn.views.dialogs.CustomDialogClass;
import com.karyastudio.izn.views.fragments.FragmentDashboard;
import com.karyastudio.izn.views.fragments.modul1.FragmentDataModul1;
import com.karyastudio.izn.views.fragments.modul2.FragmentDataModul2;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.karyastudio.izn.utils.Utils.log;

public class MainActivity extends AppCompatActivity {

    public FragmentManager fms = getSupportFragmentManager();
    private static MainActivity INSTANCE;
    public static MainActivity getInstance(){
        return INSTANCE;
    }
    boolean isForceUpdate = true;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:

                    FragmentManager fm = MainActivity.this
                            .getSupportFragmentManager();

                    // Fungsi di bawah untuk menghapus semua back stack, dan menyisakan 1 stack terakhir
                    fm.popBackStack (null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    fms.beginTransaction().replace(R.id.content_frames, new FragmentDashboard()).addToBackStack("1").commit();

                    // Fungsi di bawah untuk menghapus semua back stack, dan menyisakan 1 stack terakhir
                    fm.popBackStack (null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;

//                case R.id.navigation_data:
//                    fms.beginTransaction().replace(R.id.content_frames, new FragmentData()).addToBackStack("2").commit();
//                    return true;
//                case R.id.navigation_survey:
//                    fms.beginTransaction().replace(R.id.content_frames, new FragmentIsiSurvey()).addToBackStack("2").commit();
//                    return true;
                case R.id.navigation_logout:
                    CustomDialogClass cdd=new CustomDialogClass(MainActivity.this);
                    cdd.show();
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        if (FragmentDataModul1.showCaseView != null) {
            FragmentDataModul1.showCaseView.hide();
        }

        if (FragmentDataModul2.showCaseView != null) {
            FragmentDataModul2.showCaseView.hide();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // toast.makeText(getApplicationContext(),"Configuration Changed", toast.LENGTH_SHORT).show();

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // toast.makeText(this, "landscape", toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // toast.makeText(this, "portrait", toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.inizializePrefs(this, getPackageName());
        INSTANCE = this;

        boolean isLoggedIn = checkId();
        if (isLoggedIn){
            Utils.checkAppUpdates(MainActivity.this);
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fms.beginTransaction().replace(R.id.content_frames, new FragmentDashboard()).commit();

        if (ProvinsiManager.loadAll(this).size() == 0 && Utils.isOnline(this)){
            getDataProvince();
            getDataCity();
        }

        Disposable internetDisposable = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> log("ERROR RX NETWORK : " + error.toString()))
                .subscribe(isConnected -> {

                    if (isConnected){

                        // Utils.log("KDZ Query Form Size : " + KDZFormQueueManager.loadAll(this).size());

                        // Utils.sendModulKeluarga(this);
                        // Log.d("MYAPP", "SEND MODUL KELUARGA");

                        if (Utils.isOnline(getApplicationContext())){
                            // Log.d("MYAPP", "SEND MODUL 1");
                            // StaticStrings.readyToSendViaOfflineModul1 = false;
                            // Utils.sendModul1(getApplicationContext());
                        }

                        if (Utils.isOnline(getApplicationContext())){
                            // Log.d("MYAPP", "SEND MODUL 2");
                            // StaticStrings.readyToSendViaOfflineModul2 = false;
                            // Utils.sendModul2(getApplicationContext());
                            // Utils.log("CALL SEND MODUL 2 MAIN ACTIVE");
                        }
                    }

                }, error -> log("ERROR RX NETWORK : " + error.getMessage()));
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

                Utils.manualUpdateRequest(MainActivity.this);
            }
        }
    }


    private boolean checkId() {
        if (Prefs.getString("UID", "none").equals("none")) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            return false;
        } else {
            return true;
        }
    }


    // Method ini digunakan untuk meng-sinkronkan data di server
    // dengan data sqllite(green-dao) yang ditampilkan kalau sedang offline
    public static void getDataM2(Context context) {
        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<DataIZN> result = apiService.iznData(StaticStrings.API_KEY,Prefs.getString("UID","kosong"));
        result.enqueue(new Callback<DataIZN>() {
            @Override
            public void onResponse(Call<DataIZN> call, Response<DataIZN> response) {
                List<IZN> list = null;

                try {
                    list = new ArrayList<>();

                    if (response.body().getData() != null)
                    for (int i = 0; i < response.body().getData().getIndeksZakatNasional().size(); i++) {

                        IZN izn = SetGet.getIZNFromResponse(response.body().getData().getIndeksZakatNasional().get(i));
                        izn.setRequest_type("NONE");
                        izn.setStatus(StaticStrings.IZN_STATUS_SENT);
                        list.add(izn);

                    }

                    // Backup dulu pending list nya
                    List<IZN> pendingList = IZNManager.loadAllByStatus(context, StaticStrings.IZN_STATUS_PENDING);
                    Collections.reverse(pendingList);

                    // Jangan remove All, karena data yang pending (tambah baru) harus di keep
                    // supaya nanti dikirim ke server
                    IZNManager.removeAllSent(context);
                    IZNManager.insertOrReplaceArray(context, list, null).observe(MainActivity.getInstance(), isComplete -> {
                        if (isComplete != null && isComplete){

                            // Setelah pending List kereplace
                            // restore lagi pendingList ke sqlite db
                            IZNManager.insertOrReplaceArray(context, pendingList, null);
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                    log(MainActivity.class, "EXCEPTION GET DATA M2, " + e.toString());
                }

            }

            @Override
            public void onFailure(Call<DataIZN> call, Throwable t) {
                t.printStackTrace();
                log(MainActivity.class, "FAILURE GET DATA M2, " + t.toString());
            }
        });
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
