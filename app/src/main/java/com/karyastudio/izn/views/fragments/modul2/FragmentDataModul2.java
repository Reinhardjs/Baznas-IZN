package com.karyastudio.izn.views.fragments.modul2;

import android.app.Activity;
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
import com.karyastudio.izn.adapter.AdapterIndeksZakatNasional;
import com.karyastudio.izn.adapter.IZNActionModeCallback;
import com.karyastudio.izn.dao.generateSchema.IZN;
import com.karyastudio.izn.dao.managerSchema.IZNManager;
import com.karyastudio.izn.model.api.dataizn.DataIZN;
import com.karyastudio.izn.model.api.dataizn.IndeksZakatNasionalPojo;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.SetGet;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyIZNActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.listener.OnViewInflateListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDataModul2 extends Fragment {

    RecyclerView mRecyclerView;
    AdapterIndeksZakatNasional mAdapter;

    private View rootView;
    public static ActionMode actionMode = null;
    public static FragmentDataModul2 INSTANCE = null;

    public static Context context;
    private Toolbar toolbar;
    public static FancyShowCaseView showCaseView;

    public FragmentDataModul2() {
        INSTANCE = this;
    }

    public static FragmentDataModul2 getInstance(){
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_data_modul2, container, false);
        context = rootView.getContext();
        mRecyclerView = rootView.findViewById(R.id.recyclerView2);



        toolbar = rootView.findViewById(R.id.toolbarModul2);
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

                    Intent intent = new Intent(getActivity(), SurveyIZNActivity.class);
                    intent.putExtra(SurveyIZNActivity.FORM_REQUEST_TYPE_DATA, SurveyIZNActivity.REQUEST_TYPE_INSERT);
                    startActivity(intent);

                    return true;
                } else if (menuItem.getItemId() == R.id.info){
                    showCaseView.show();
                }
                return false;
            }
        });

        if (Prefs.getBoolean(StaticStrings.SHOWCASE_OPEN2, true)) {
            showCaseView.show();

            Prefs.putBoolean(StaticStrings.SHOWCASE_OPEN2, false);
        }

        populate();

        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title("Data KDZ")
                .content("sedang menambahkan ke antrian")
                .progress(true, 0)
                .canceledOnTouchOutside(false).build();

        SurveyIZNActivity.getIsComplete().observe(FragmentDataModul2.getInstance(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isComplete) {
                dialog.show();

                if (isComplete) {
                    dialog.dismiss();
                    FragmentDataModul2.getInstance().populate();
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
            MainActivity.getDataM2(rootView.getContext());
        }else{
            showDataOffline();
        }
    }

    private void showDataOffline() {


        List<IZN> list = IZNManager.loadAll(context);
        Collections.reverse(list);

//        List<IZN> pendingList = IZNManager.loadAllByStatus(context, StaticStrings.IZN_STATUS_PENDING);
//        Collections.reverse(pendingList);
//
//        list.addAll(0, pendingList);


        List<IndeksZakatNasionalPojo> newList = new ArrayList<>();
        for (IZN izn : list){
            IndeksZakatNasionalPojo data = SetGet.getResponseFromIzn(izn);

            if (izn.getStatus().equals(StaticStrings.IZN_STATUS_SENT)){
                data.setStatus(StaticStrings.IZN_STATUS_SENT);
            } else {
                data.setStatus(StaticStrings.IZN_STATUS_PENDING);
            }

            newList.add(data);
        }

        mAdapter = new AdapterIndeksZakatNasional(newList, new AdapterIndeksZakatNasional.ClickListener() {
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
                actionMode = IZNActionModeCallback.startActionMode(getActivity(), mAdapter);
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


    private void showDataOnline() {

        Utils.log("ALL SIZE : " + IZNManager.loadAll(context).size());

        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<DataIZN> result = apiService.iznData(StaticStrings.API_KEY, Prefs.getString("UID","kosong"));
        result.enqueue(new Callback<DataIZN>() {
            @Override
            public void onResponse(Call<DataIZN> call, Response<DataIZN> response) {

                Utils.log("DATA MODUL 2 ONLINE ON RESPONSE : " + response.body().getMessage());

                try {
                    List<IZN> list = new ArrayList<>();

                    if (response.body().getData() != null)
                    for (int i = 0; i < response.body().getData().getIndeksZakatNasional().size(); i++) {
                        IndeksZakatNasionalPojo data = response.body().getData().getIndeksZakatNasional().get(i);
                        Utils.log("ADA ? " + data.getFi_702_dana_zis_dakwah_ada());

                        IZN izn = SetGet.getIZNFromResponse(data);
                        izn.setRequest_type("NONE");
                        izn.setStatus(StaticStrings.KDZ_STATUS_SENT);
                        list.add(izn);
                    }
                    Collections.reverse(list);


                    List<IZN> pendingList = IZNManager.loadAllByStatus(context, StaticStrings.IZN_STATUS_PENDING);
                    Collections.reverse(pendingList);

                    // Replace semua data dari server dengan data pending update
                    // Untuk pending "insert new data" taruh di bagian paling atas.
                    // list.addAll(0, pendingList);  --> code before replace2 etc
                    for (IZN pendingElement : pendingList) {
                        boolean isFound = Utils.replaceOldIZN(list, pendingElement);
                        if (!isFound){
                            list.add(0, pendingElement);
                        }
                    }



                    List<IndeksZakatNasionalPojo> newList = new ArrayList<>();

                    for (IZN izn : list){
                        IndeksZakatNasionalPojo data = SetGet.getResponseFromIzn(izn);

                        if (izn.getStatus().equals(StaticStrings.IZN_STATUS_SENT)){
                            data.setStatus(StaticStrings.IZN_STATUS_SENT);
                        } else {
                            data.setStatus(StaticStrings.IZN_STATUS_PENDING);
                        }

                        newList.add(data);
                    }


                    mAdapter = new AdapterIndeksZakatNasional(newList, new AdapterIndeksZakatNasional.ClickListener() {
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
                            actionMode = IZNActionModeCallback.startActionMode(getActivity(), mAdapter);
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

                } catch (Exception e) {
                    Utils.log("ERROR DATA MODUL 2 : " + e.getMessage());
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<DataIZN> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
