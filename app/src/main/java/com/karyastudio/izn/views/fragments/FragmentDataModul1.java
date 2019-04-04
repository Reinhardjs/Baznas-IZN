package com.karyastudio.izn.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karyastudio.izn.R;
import com.karyastudio.izn.adapter.AdapterDampakKajianZakat;
import com.karyastudio.izn.dao.generateSchema.KDZ;
import com.karyastudio.izn.dao.managerSchema.KDZManager;
import com.karyastudio.izn.model.api.datakdz.DataKDZ;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDataModul1 extends Fragment {

    RecyclerView mRecyclerView;
    AdapterDampakKajianZakat mAdapter;

    public FragmentDataModul1() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_data_modul1, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        if (Utils.isOnline(rootView.getContext())){
            showDataOnline();
        }else{
            showDataOffline();
        }
        return rootView;
    }


    private void showDataOffline() {

        mAdapter = new AdapterDampakKajianZakat(KDZManager.loadAll(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void showDataOnline() {

        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<DataKDZ> result = apiService.kdzData(StaticStrings.API_KEY, Prefs.getString("UID","kosong"));
        result.enqueue(new Callback<DataKDZ>() {
            @Override
            public void onResponse(Call<DataKDZ> call, Response<DataKDZ> response) {
                try {
                    List<KDZ> list = new ArrayList<>();

                    for (int i = 0; i < response.body().getData().getIndeksZakatNasional().size(); i++) {
                        list.add(new KDZ(
                                (String) response.body().getData().getIndeksZakatNasional().get(i).getFkId(),
                                response.body().getData().getIndeksZakatNasional().get(i).getFkDateCreated(),
                                response.body().getData().getIndeksZakatNasional().get(i).getFkDateUpdated(),
                                response.body().getData().getIndeksZakatNasional().get(i).getFkNama(),
                                response.body().getData().getIndeksZakatNasional().get(i).getCountKeluarga()
                        ));

                    }
                    mAdapter = new AdapterDampakKajianZakat(list);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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

}
