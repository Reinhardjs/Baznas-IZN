package com.karyastudio.izn.views.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.karyastudio.izn.R;
import com.karyastudio.izn.adapter.AdapterKeluarga;
import com.karyastudio.izn.dao.generateSchema.Keluarga;
import com.karyastudio.izn.dao.managerSchema.KeluargaManager;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

public class FragmentModul1201 extends Fragment implements Step {
    private FragmentManager fms;
    private RecyclerView mRecyclerView;

    private Button btnNext;
    private AdapterKeluarga mAdapter;
    private boolean isSuccess;
    private View rootView;
    private ArrayList<Keluarga> list = new ArrayList<>();

    public FragmentModul1201() {

    }

    // method ini hanya dipanggil sekali untuk fragment ini
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // jangan hapus fragment ini saat activity dibuat ulang.
        setRetainInstance(true);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putSerializable("list", list);
//    }
//
//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//
//        if (savedInstanceState != null) {
//            list = (ArrayList<Keluarga>) savedInstanceState.getSerializable("list");
//
//            if (mAdapter != null) {
//                mAdapter.setList(list);
//                mAdapter.notifyDataSetChanged();
//            }
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_modul1_201, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar6);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        mRecyclerView = rootView.findViewById(R.id.recyclerView3);
        btnNext = rootView.findViewById(R.id.btn_next1_201);

        if (mAdapter == null) {
            mAdapter = new AdapterKeluarga(getActivity());
            mAdapter.setList(list);
        }

//        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

//        btnNext.setVisibility(View.GONE);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
//                fms= getActivity().getSupportFragmentManager();
//                for(int i = 0; i < fms.getBackStackEntryCount(); ++i) {
//                    fms.popBackStack();
//                }
//                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul1401()).addToBackStack("comm").commit();
            }
        });

        return rootView;
    }

    private VerificationError saveAndNext(View view){
        try {
            isSuccess = true;
            Utils.Toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN).show();
        } catch (Exception e){
            return new VerificationError("Mohon lengkapi form pada halaman ini");
        }

        return null;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (isSuccess){
            return null;
        }

        if (KeluargaManager.loadAll(getActivity()).size() == 0){
            return new VerificationError("Data keluarga harus diisi");
        }

        return saveAndNext(rootView);
    }

    @Override
    public void onSelected() {
        Utils.log("(ON SELECTED) ADAPTER KELUARGA ITEM COUNT : " + Integer.parseInt(Prefs.getString(StaticStrings.M1_108, "0")));
        mAdapter.setCount(Integer.parseInt(Prefs.getString(StaticStrings.M1_108, "0")));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
