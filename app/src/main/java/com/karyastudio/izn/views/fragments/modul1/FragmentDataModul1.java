package com.karyastudio.izn.views.fragments.modul1;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.adapter.AdapterDampakKajianZakat;
import com.karyastudio.izn.adapter.KDZActionModeCallback;
import com.karyastudio.izn.dao.generateSchema.KDZ;
import com.karyastudio.izn.dao.generateSchema.Keluarga;
import com.karyastudio.izn.dao.managerSchema.KDZManager;
import com.karyastudio.izn.dao.managerSchema.KeluargaManager;
import com.karyastudio.izn.model.api.datakdz.DataKDZ;
import com.karyastudio.izn.model.api.datakdz.KajianDampakZakatPojo;
import com.karyastudio.izn.model.api.datakdzkeluarga.DataKDZKeluarga;
import com.karyastudio.izn.model.api.datakdzkeluarga.DataKDZKeluargaPojo;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.SetGet;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyKDZActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.listener.OnViewInflateListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDataModul1 extends Fragment {

    RecyclerView mRecyclerView;
    public static AdapterDampakKajianZakat mAdapter;

    private View rootView;
    public static ActionMode actionMode = null;
    public static FragmentDataModul1 INSTANCE = null;
    private Toolbar toolbar;

    public FragmentDataModul1() {
        INSTANCE = this;
    }

    public static FragmentDataModul1 getInstance(){
        return INSTANCE;
    }

    public static Context context;

    public static FancyShowCaseView showCaseView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_data_modul1, container, false);
        context = rootView.getContext();
        mRecyclerView = rootView.findViewById(R.id.recyclerView);

        toolbar = rootView.findViewById(R.id.toolbarModul1);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        showCaseView = new FancyShowCaseView.Builder((Activity) rootView.getContext())
                .focusOn(toolbar.findViewById(R.id.add))
//                .titleSize(17, TypedValue.COMPLEX_UNIT_SP)
//                    .title("Click tombol \"+\" untuk menambah data baru")
//                    .titleStyle(R.style.ShowCaseStyle, Gravity.TOP | Gravity.CENTER)
                .customView(R.layout.showcase_layout, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        ((TextView)view.findViewById(R.id.text)).setText("Klik tombol \"+\" untuk menambah data baru");
                    }
                })
                .closeOnTouch(true)
                .build();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.add) {

                    Intent intent = new Intent(getActivity(), SurveyKDZActivity.class);
                    intent.putExtra(SurveyKDZActivity.FORM_REQUEST_TYPE_DATA, SurveyKDZActivity.REQUEST_TYPE_INSERT);
                    startActivity(intent);

                    return true;

                } else if (menuItem.getItemId() == R.id.info){
                    showCaseView.show();
                    return true;
                }
                return false;
            }
        });


        if (Prefs.getBoolean(StaticStrings.SHOWCASE_OPEN1, true)) {
            showCaseView.show();

            Prefs.putBoolean(StaticStrings.SHOWCASE_OPEN1, false);
        }

        populate();

        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title("Data KDZ")
                .content("sedang menambahkan ke antrian")
                .progress(true, 0)
                .canceledOnTouchOutside(false).build();

        SurveyKDZActivity.getIsComplete().observe(FragmentDataModul1.getInstance(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isComplete) {
                dialog.show();

                if (isComplete) {
                    dialog.dismiss();
                    FragmentDataModul1.getInstance().populate();
                }
            }
        });

        return rootView;
    }

    public void populate(){
        Utils.log("START POPULATING");

        if (mAdapter != null){
            mAdapter.getArrayList().clear();
            mAdapter.notifyDataSetChanged();
        }

        if (Utils.isOnline(rootView.getContext())){
            showDataOnline();
            // MainActivity.getDataM1(rootView.getContext());
        }else{
            showDataOffline();
        }

    }

    private void showDataOffline() {
        //list.addAll(KDZManager.loadAllByStatus(context, StaticStrings.KDZ_STATUS_PENDING));
        List<KDZ> list = KDZManager.loadAll(context);
        Collections.reverse(list);

//        List<KDZ> pendingList = KDZManager.loadAllByStatus(context, StaticStrings.KDZ_STATUS_PENDING);
//        Collections.reverse(pendingList);
//
//        list.addAll(0, pendingList);


        List<KajianDampakZakatPojo> newList = new ArrayList<>();

        for (KDZ kdz : list){
            KajianDampakZakatPojo data = SetGet.getResponseFromKDZ(kdz);

            Utils.log(FragmentDataModul1.class, "Show data offline, 602_kode : " + kdz.getM1_602_kode());

            if (kdz.getStatus().equalsIgnoreCase(StaticStrings.KDZ_STATUS_SENT)) {
                data.setStatus(StaticStrings.KDZ_STATUS_SENT);
            } else {
                data.setStatus(StaticStrings.KDZ_STATUS_PENDING);
            }

            newList.add(data);
        }

        Collections.reverse(newList);

        populateAdapter(newList);
    }


    // Di method ini ada mekanisme, ambil data dari online, jika ada data yang sama, tapi di local green dao itu pending
    // maka backup dulu pending, insert yang dari server, kemudian restore and replace yg dari server dengan pending data.
    private void showDataOnline() {
        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<DataKDZ> result = apiService.kdzData(StaticStrings.API_KEY, Prefs.getString("UID","kosong"));
        result.enqueue(new Callback<DataKDZ>() {
            @Override
            public void onResponse(Call<DataKDZ> call, Response<DataKDZ> response) {

                try {
                    List<KDZ> list = new ArrayList<>();

                    Utils.log("RESPONSE MESSAGE : " + response.body().getMessage());

                    if (response.body().getData() != null)
                    for (int i = 0; i < response.body().getData().getKajianDampakZakat().size(); i++) {
                        KajianDampakZakatPojo data = response.body().getData().getKajianDampakZakat().get(i);

                        KDZ kdz = SetGet.getKDZFromResponse(data);
                        kdz.setRequest_type("NONE");
                        kdz.setStatus(StaticStrings.KDZ_STATUS_SENT);
                        list.add(kdz);
                    }
                    Collections.reverse(list);

                    List<KDZ> pendingList = KDZManager.loadAllByStatus(context, StaticStrings.KDZ_STATUS_PENDING);
                    Collections.reverse(pendingList);




                    // Replace semua data dari server dengan data pending update
                    // Untuk pending "insert new data" taruh di bagian paling atas.
                    // list.addAll(0, pendingList);  --> code before replace2 etc
                    for (KDZ pendingElement : pendingList) {
                        boolean isFound = Utils.replaceOldKDZ(list, pendingElement);
                        if (!isFound){
                            list.add(0, pendingElement);
                        }
                    }



                    List<KajianDampakZakatPojo> newList = new ArrayList<>();

                    for (KDZ kdz : list){
                        KajianDampakZakatPojo data = SetGet.getResponseFromKDZ(kdz);

                        if (kdz.getStatus().equalsIgnoreCase(StaticStrings.KDZ_STATUS_SENT)) {
                            data.setStatus(StaticStrings.KDZ_STATUS_SENT);
                            Utils.log("DATA MODUL 1 SENT, TYPE : " + data.getRequest_type());
                        } else {
                            data.setStatus(StaticStrings.KDZ_STATUS_PENDING);
                            Utils.log("DATA MODUL 1 PENDING, TYPE : " + data.getRequest_type());
                        }

                        newList.add(data);
                    }



                    KDZManager.removeAll(context);
                    KDZManager.insertOrReplaceArray(context, list, null).observe(MainActivity.getInstance(), isComplete -> {

                        if (isComplete != null && isComplete) {

                            Utils.log("ON DATA M1 UP TO DATE COMPLETE");
                            List<KDZ> allKDZ = KDZManager.loadAll(context);
                            Utils.log("ALL KDZ SIZE : " + allKDZ.size());

                            MutableLiveData<Integer> count = new MutableLiveData<>();
                            count.setValue(0);

                            for (KDZ kdz : allKDZ){
                                String fk_id = kdz.getFk_id();
                                String status = kdz.getStatus();

                                if (status.equals(StaticStrings.KDZ_STATUS_SENT)){
                                    // Hapus semua yang sent (itupun kalau ada) (seharusnya ga ada)
                                    Utils.log("REMOVING ALL WITH THE FK ID " + fk_id);
                                    KeluargaManager.removeAllWithFKID(context, fk_id);
                                    // KeluargaManager.removeAll(context);
                                }

                                // Get data keluarga untuk setiap data kdz (punya fk_id unik keluarga)
                                Utils.log("GETTING DATA KELUARGA with fk ID : " + fk_id);
                                if (!fk_id.contains("-") || !status.equals(StaticStrings.KDZ_STATUS_PENDING)) {
                                    // getDataKeluarga hanya untuk data yang benar2 dari server
                                    // tanpa adanya request (pending) data yang sama dari user
                                    getDataKeluarga(context, fk_id, status, count);
                                } else {
                                    count.setValue(count.getValue() + 1);
                                    Utils.log("CANCEL GET DATA KELUARGA : " + fk_id);
                                }
                            }

//                            count.observe(FragmentDataModul1.getInstance(), sizeCount -> {
//
//                                if (sizeCount != null && sizeCount.equals(allKDZ.size())){
//                                    populateAdapter(newList);
//                                }
//
//                            });


                            populateAdapter(newList);

                        }
                    });

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

    private void populateAdapter(List<KajianDampakZakatPojo> listKdz){
        mAdapter = new AdapterDampakKajianZakat(listKdz, new AdapterDampakKajianZakat.ClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (mAdapter.getIsInChoiceMode()) {
                    mAdapter.switchSelectedState(position);
                } else {
                    // normal click handling
                    // ...
                    Utils.log("Normal click");
                }
            }

            @Override
            public boolean onItemLongClicked(int position) {
                Utils.log("ON LONG CLICK");
                mAdapter.beginChoiceMode(position);
                actionMode = KDZActionModeCallback.startActionMode(getActivity(), mAdapter);
                return true;
            }
        });

        if (mRecyclerView.getAdapter() == null) {
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mRecyclerView.swapAdapter(mAdapter, true);
        }
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter.notifyDataSetChanged();
    }

    public void getDataKeluarga(Context context, String fk_id, String status, MutableLiveData<Integer> count){
        Utils.log("HEY GET DATA KELUARGA");

        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<DataKDZKeluarga> result = apiService.kdzDataKeluarga(StaticStrings.API_KEY, fk_id);

        Utils.log("Start getting keluarga list with fk id : " + fk_id);

        if (fk_id.contains("-")) return;

        result.enqueue(new Callback<DataKDZKeluarga>() {
            @Override
            public void onResponse(Call<DataKDZKeluarga> call, Response<DataKDZKeluarga> response) {
                try {

                    Utils.log("RESPONSE STATUS : " + response.body().getStatus());
                    Utils.log("RESPONES MESSAGE : " + response.body().getMessage());

                    List<DataKDZKeluargaPojo> list = response.body().getData().getKajianDampakZakatItems();
                    List<Keluarga> keluargaList = new ArrayList<>();
                    List<Keluarga> pendingKeluargaList = KeluargaManager.loadAllByStatus(context, StaticStrings.KDZ_STATUS_PENDING);

                    for (DataKDZKeluargaPojo item : list){
                        Keluarga keluarga = new Keluarga();
                        keluarga.setPosisi(item.getPosisi());
                        keluarga.setStatus(StaticStrings.KDZ_STATUS_SENT);
                        keluarga.setRequest_type("NONE");
                        keluarga.setFki_id(item.getFki_id());
                        keluarga.setFki_fk_id(item.getFki_fk_id());
                        keluarga.setFk_202_nik(item.getFk_202_nik());
                        keluarga.setFk_202_nama(item.getFk_202_nama());
                        keluarga.setFk_203(item.getFk_203());
                        keluarga.setFk_204(item.getFk_204());
                        keluarga.setFk_205(item.getFk_205());
                        keluarga.setFk_206(item.getFk_206());
                        keluarga.setFk_207(item.getFk_207());
                        keluarga.setFk_208(item.getFk_208());
                        keluarga.setFk_209(item.getFk_209());
                        keluarga.setFk_210(item.getFk_210());
                        keluarga.setFk_303(item.getFk_303());
                        keluarga.setFk_304(item.getFk_304());
                        keluarga.setFk_305(item.getFk_305());
                        keluarga.setFk_306(item.getFk_306());
                        keluarga.setFk_307(item.getFk_307());
                        keluarga.setFk_308(item.getFk_308());

                        keluargaList.add(keluarga);
                        Utils.log("KELUARGA NIK GET : " + keluarga.getFk_202_nik());
                    }

                    KeluargaManager.insertOrReplaceArray(context, keluargaList).observe(FragmentDataModul1.getInstance(), isComplete -> {
                        if (isComplete != null && isComplete){

                            // Backup kembali data pending keluarga
                            // Karena harus ada yang diupdate data keluarganya dari data kdz yang sedang diupdate
                            // pending bisa karena insert atau update
                            if (status.equals(StaticStrings.KDZ_STATUS_PENDING)) {
                                KeluargaManager.insertOrReplaceArray(context, pendingKeluargaList).observe(FragmentDataModul1.getInstance(), isComplete2 -> {
                                    if (isComplete2 != null && isComplete2){

                                    }
                                });
                            }

                            count.setValue(count.getValue() + 1);

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("tesapia->" + e);
                    Utils.log("EXCEPTION GET KELUARGAA : " + e.toString());
                    Utils.log("EXCEPTION GET KELUARGAA : " + e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<DataKDZKeluarga> call, Throwable t) {
                t.printStackTrace();
                Utils.log("ON FAILURE GET KELUARGA : " + t.toString());
                Utils.log("ON FAILURE GET KELUARGA : " + t.getMessage());
            }
        });
    }

}
