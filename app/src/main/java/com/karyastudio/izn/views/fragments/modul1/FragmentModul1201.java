package com.karyastudio.izn.views.fragments.modul1;

import android.arch.lifecycle.Observer;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.karyastudio.izn.R;
import com.karyastudio.izn.adapter.AdapterKeluarga;
import com.karyastudio.izn.dao.generateSchema.Keluarga;
import com.karyastudio.izn.dao.managerSchema.KeluargaManager;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyKDZActivity;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentModul1201 extends Fragment implements BlockingStep {
    private FragmentManager fms;
    private RecyclerView mRecyclerView;

    private Button btnNext;
    private AdapterKeluarga mAdapter;
    private boolean isSuccess;
    private View rootView;
    public List<Keluarga> keluargaList = new ArrayList<>();
    private SparseArray<Keluarga> keluargaSparseArray = new SparseArray<>();

    public FragmentModul1201() {

    }

    // method ini hanya dipanggil sekali untuk fragment ini
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // jangan hapus fragment ini saat activity dibuat ulang.
        setRetainInstance(true);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putSerializable("keluargaList", keluargaList);
//    }
//
//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//
//        if (savedInstanceState != null) {
//            keluargaList = (ArrayList<Keluarga>) savedInstanceState.getSerializable("keluargaList");
//
//            if (mAdapter != null) {
//                mAdapter.setList(keluargaList);
//                mAdapter.notifyDataSetChanged();
//            }
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_modul1_201, container, false);

        Utils.log("FRAGMENT PAGE 2 ONCREATEVIEW");

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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
            }
        });

        if (mAdapter == null){
            mAdapter = new AdapterKeluarga(getActivity());
        }

        return rootView;
    }

    private VerificationError saveAndNext(View view){
        try {



        } catch (Exception e){
            return new VerificationError("Mohon lengkapi form pada halaman ini");
        }

        return null;
    }


    @Nullable
    @Override
    public VerificationError verifyStep() {
//        if (isSuccess){
//            return null;
//        }

        VerificationError verificationError = saveAndNext(rootView);

//        if (verificationError == null) {
//            isSuccess = true;
//            Utils.toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN);
//        } else {
//            isSuccess = false;
//        }

        return verificationError;
    }

    @Override
    public void onSelected() {

        isSuccess = false;

        new Handler().postDelayed(() -> {

            if (SurveyKDZActivity.form_request_type.equals(SurveyKDZActivity.REQUEST_TYPE_UPDATE)){
                keluargaList = KeluargaManager.loadAllwithFKID(getContext(), SurveyKDZActivity.form_input_id);
                keluargaSparseArray = new SparseArray<>();

                for (Keluarga item : keluargaList){
                    item.setStatus(StaticStrings.KDZ_STATUS_PENDING);
                    item.setRequest_type(SurveyKDZActivity.form_request_type);
                    KeluargaManager.insertOrReplace(getActivity(), item);
                }

                for (int i = 0; i < keluargaList.size(); i++){
                    keluargaSparseArray.put(i, keluargaList.get(i));
                }

                mAdapter.setList(keluargaSparseArray);
                mRecyclerView.setNestedScrollingEnabled(false);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setAdapter(mAdapter);

            } else {
                mAdapter.setList(keluargaSparseArray);
                mRecyclerView.setNestedScrollingEnabled(false);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setAdapter(mAdapter);
            }

            mAdapter.setCount(Integer.parseInt(Prefs.getString(StaticStrings.M1_108, "0")));
            mAdapter.notifyDataSetChanged();


        }, 200);

        Utils.log("FRAGMENT PAGE 2 ONSELECTED");
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        Toast.makeText(getContext(), error.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        startBlocking(callback);
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    private void startBlocking(StepperLayout.AbstractOnButtonClickedCallback callback){
        int count = Integer.parseInt(Prefs.getString(StaticStrings.M1_108, "0"));

        List<Keluarga> list = new ArrayList<>();

        for (int i = 0; i < count; i++){
            Keluarga keluarga = keluargaSparseArray.get(i);
            Utils.log("KELUARGA isnull? -> " + keluarga);

            if (keluarga == null){
                onError(new VerificationError("Data personal ke " + (i+1) + " belum diisi/disimpan"));
                isSuccess = false;


//                for (int j = 0; j < keluargaSparseArray.size(); j++){
//                    int key = keluargaSparseArray.keyAt(j);
//                    Keluarga value = keluargaSparseArray.get(key);
//                    KeluargaManager.remove(getContext(), value);
//                }
                return;
            }

            list.add(keluarga);
        }

        if (keluargaSparseArray.size() < count){
            onError(new VerificationError("Data keluarga harus diisi"));
            isSuccess = false;
        } else {
            KeluargaManager.removeAllWithFKID(getContext(), SurveyKDZActivity.form_input_id);
            KeluargaManager.insertOrReplaceArray(getContext(), list).observe(FragmentModul1201.this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean isComplete) {

                    if (isComplete != null && isComplete){
                        if (callback instanceof StepperLayout.OnNextClickedCallback){
                            ((StepperLayout.OnNextClickedCallback) callback).goToNextStep();
                        } else if (callback instanceof  StepperLayout.OnBackClickedCallback){
                            ((StepperLayout.OnBackClickedCallback) callback).goToPrevStep();
                        }
                        isSuccess = true;
                        Utils.toast(getContext(), StaticStrings.TOAST_SUKSES_SIMPAN);
                    }
                }
            });
        }
    }
}
