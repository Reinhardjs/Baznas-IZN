package com.karyastudio.izn;

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
import com.karyastudio.izn.dao.generateSchema.City;
import com.karyastudio.izn.dao.generateSchema.IZN;
import com.karyastudio.izn.dao.generateSchema.KDZ;
import com.karyastudio.izn.dao.generateSchema.Provinsi;
import com.karyastudio.izn.dao.managerSchema.CityManager;
import com.karyastudio.izn.dao.managerSchema.IZNManager;
import com.karyastudio.izn.dao.managerSchema.KDZFormQueueManager;
import com.karyastudio.izn.dao.managerSchema.KDZManager;
import com.karyastudio.izn.dao.managerSchema.ProvinsiManager;
import com.karyastudio.izn.model.api.city.DataCity;
import com.karyastudio.izn.model.api.dataizn.DataIZN;
import com.karyastudio.izn.model.api.datakdz.DataKDZ;
import com.karyastudio.izn.model.api.province.DataProvinsi;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.LoginActivity;
import com.karyastudio.izn.views.dialogs.CustomDialogClass;
import com.karyastudio.izn.views.fragments.FragmentDashboard;
import com.karyastudio.izn.views.fragments.FragmentData;
import com.karyastudio.izn.views.fragments.FragmentIsiSurvey;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public FragmentManager fms = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    fms.beginTransaction().replace(R.id.content_frames, new FragmentDashboard()).addToBackStack("1").commit();
                    return true;
                case R.id.navigation_data:
                    fms.beginTransaction().replace(R.id.content_frames, new FragmentData()).addToBackStack("2").commit();
                    return true;
                case R.id.navigation_survey:
                    fms.beginTransaction().replace(R.id.content_frames, new FragmentIsiSurvey()).addToBackStack("2").commit();
                    return true;
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Toast.makeText(getApplicationContext(),"Configuration Changed", Toast.LENGTH_SHORT).show();

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.inizializePrefs(this, getPackageName());
        checkId();
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
                .doOnError(error -> Utils.log("ERROR RX NETWORK : " + error.toString()))
                .subscribe(isConnected -> {

                    if (isConnected){

                        Utils.log("KDZ Query Form Size : " + KDZFormQueueManager.loadAll(this).size());

                        Utils.sendModulKeluarga(this);
                        Log.d("MYAPP", "SEND MODUL KELUARGA");

                        if (Utils.isOnline(getApplicationContext())){
                            Log.d("MYAPP", "SEND MODUL 1");
                            // StaticStrings.readyToSendViaOfflineModul1 = false;
                            Utils.sendModul1(getApplicationContext());
                        }

                        if (StaticStrings.readyToSendViaOfflineModul2 && Utils.isOnline(getApplicationContext())){
                            Log.d("MYAPP", "SEND MODUL 2");
                            StaticStrings.readyToSendViaOfflineModul2 = false;
                            Utils.sendModul2(getApplicationContext());
                        }
                    }

                }, error -> Utils.log("ERROR RX NETWORK : " + error.getMessage()));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Utils.isOnline(this)) {
            getDataM1();
            getDataM2();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }



    private void checkId() {
        if (Prefs.getString("UID", "none").equals("none")) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
    }

    private void getDataM1() {
        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<DataKDZ> result = apiService.kdzData(StaticStrings.API_KEY, Prefs.getString("UID","kosong"));
        result.enqueue(new Callback<DataKDZ>() {
            @Override
            public void onResponse(Call<DataKDZ> call, Response<DataKDZ> response) {
                try {
                    List<KDZ> list = new ArrayList<>();

                    for (int i = 0; i < response.body().getData().getKajianDampakZakat().size(); i++) {
                        list.add(new KDZ(
                                (String) response.body().getData().getKajianDampakZakat().get(i).getFk_id(),
                                response.body().getData().getKajianDampakZakat().get(i).getFk_date_created(),
                                response.body().getData().getKajianDampakZakat().get(i).getFk_date_updated(),
                                response.body().getData().getKajianDampakZakat().get(i).getFk_nama(),
                                response.body().getData().getKajianDampakZakat().get(i).getCountKeluarga()
                        ));

                    }

                    KDZManager.removeAll(getApplicationContext());
                    KDZManager.insertOrReplaceArray(getApplicationContext(), list);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("tesapia->" + e);
                }

            }

            @Override
            public void onFailure(Call<DataKDZ> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void getDataM2() {

        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<DataIZN> result = apiService.iznData(StaticStrings.API_KEY,Prefs.getString("UID","kosong"));
        result.enqueue(new Callback<DataIZN>() {
            @Override
            public void onResponse(Call<DataIZN> call, Response<DataIZN> response) {
                try {
                    List<IZN> list = new ArrayList<>();
                    for (int i = 0; i < response.body().getData().getIndeksZakatNasional().size(); i++) {
                        list.add(new IZN(
                                response.body().getData().getIndeksZakatNasional().get(i).getFiId(),
                                response.body().getData().getIndeksZakatNasional().get(i).getFiCode(),
                                response.body().getData().getIndeksZakatNasional().get(i).getFiDateCreated(),
                                response.body().getData().getIndeksZakatNasional().get(i).getFiDateUpdated(),
                                (String) response.body().getData().getIndeksZakatNasional().get(i).getFiJenisLembaga()
                        ));

                    }
                    IZNManager.removeAll(getApplicationContext());
                    IZNManager.insertOrReplaceArray(getApplicationContext(), list);

                } catch (Exception e) {
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<DataIZN> call, Throwable t) {
                t.printStackTrace();
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
