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
import com.karyastudio.izn.adapter.AdapterIndeksZakatNasional;
import com.karyastudio.izn.dao.generateSchema.IZN;
import com.karyastudio.izn.dao.managerSchema.IZNManager;
import com.karyastudio.izn.model.api.dataizn.DataIZN;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDataModul2 extends Fragment {

    RecyclerView mRecyclerView;
    AdapterIndeksZakatNasional mAdapter;

    public FragmentDataModul2() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_data_modul2, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclerView2);
        if (Utils.isOnline(rootView.getContext())){
            showDataOnline();
        }else{
            showDataOffline();
        }
        return rootView;
    }


    private void showDataOffline() {
        System.out.println("tesaa->" + IZNManager.loadAll(getActivity()).size());
        mAdapter = new AdapterIndeksZakatNasional(IZNManager.loadAll(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    private void showDataOnline() {

        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<DataIZN> result = apiService.iznData(StaticStrings.API_KEY, Prefs.getString("UID","kosong"));
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
                    mAdapter = new AdapterIndeksZakatNasional(list);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("tesapia->" + e);
                }

            }

            @Override
            public void onFailure(Call<DataIZN> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
