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

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.adapter.AdapterKeluarga;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.pixplicity.easyprefs.library.Prefs;

public class FragmentModul1201 extends Fragment {
    private FragmentManager fms;
    private RecyclerView mRecyclerView;

    private Button btnNext;
    private AdapterKeluarga mAdapter;

    public FragmentModul1201() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul1_201, container, false);
        fms = ((MainActivity) getActivity()).fms;
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
        mAdapter = new AdapterKeluarga(getActivity(),Integer.parseInt(Prefs.getString(StaticStrings.M1_108,"1")));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
                fms= getActivity().getSupportFragmentManager();
                for(int i = 0; i < fms.getBackStackEntryCount(); ++i) {
                    fms.popBackStack();
                }
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul1401()).addToBackStack("comm").commit();
            }
        });

        return rootView;
    }


    private void saveAndNext(View view){
        Utils.sendModulKeluarga(view.getContext());
    }
}
