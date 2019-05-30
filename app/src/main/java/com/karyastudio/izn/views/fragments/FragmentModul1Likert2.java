package com.karyastudio.izn.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyKDZActivity;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FragmentModul1Likert2 extends Fragment implements Step {
    private FragmentManager fms;
    private Spinner edt_lik1;
    private Spinner edt_lik2;
    private Spinner edt_lik3;
    private Spinner edt_lik4;
    private Spinner edt_lik5;
    private Button btnNext;
    private boolean isSuccess;
    private View rootView;

    public FragmentModul1Likert2() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_modul1_likert2, container, false);
//        fms = ((MainActivity) getActivity()).fms;
        edt_lik1 = rootView.findViewById(R.id.edt1_lik_1);
        edt_lik2 = rootView.findViewById(R.id.edt1_lik_2);
        edt_lik3 = rootView.findViewById(R.id.edt1_lik_3);
        edt_lik4 = rootView.findViewById(R.id.edt1_lik_4);
        edt_lik5 = rootView.findViewById(R.id.edt1_lik_5);



        String[] plants = new String[]{
                "1. Melarang orang lain shalat",
                "2. Menolak konsep shalat",
                "3. Melaksanakan shalat wajib tidak rutin",
                "4. Melaksanakan shalat rutin wajib tapi tidak selalu berjamaah",
                "5. Melaksanakan shalat wajib rutin berjamaah dan melakukan shalat sunnah"
        };
        String[] plants1 = new String[]{
                "1. Melarang orang lain berpuasa",
                "2. Menolak konsep puasa",
                "3. Melaksanakan puasa wajib tidak penuh",
                "4. Melaksanakan puasa wajib secara penuh",
                "5. Melaksanakan puasa wajib penuh dan puasa sunah"
        };
        String[] plants2 = new String[]{
                "1. Melarang orang lain berzakat dan infak",
                "2. Menolak zakat dan infak",
                "3. Tidak pernah berinfak walau sekali dalam setahun",
                "4. Membayar zakat fitrah dan zakat harta (mal)",
                "5. Membayar zakat fitrah, zakat harta, dan infak/sedekah"
        };
        String[] plants3 = new String[]{
                "1. Melarang anggota keluarga ibadah",
                "2. Menolak pelaksanaan ibadah",
                "3. Menganggap ibadah sebagai urusan pribadi anggota keluarga",
                "4. Mendukung ibadah anggota keluarga",
                "5. Membangun suasana keluarga yang mendukung ibadah secara bersama-sama"
        };
        String[] plants4 = new String[]{
                "1. Melarang ibadah untuk setiap keluarga",
                "2. Menolak pelaksanaan ibadah",
                "3. Menganggap ibadah sebagai urusan pribadi masyarakat",
                "4. Mendukung ibadah",
                "5. Menciptakan lingkungan yang kondusif untuk ibadah"
        };

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        edt_lik1.setAdapter(spinnerArrayAdapter);

        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants1
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        edt_lik2.setAdapter(spinnerArrayAdapter1);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants2
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        edt_lik3.setAdapter(spinnerArrayAdapter2);

        ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants3
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        edt_lik4.setAdapter(spinnerArrayAdapter3);

        ArrayAdapter<String> spinnerArrayAdapter4 = new ArrayAdapter<String>(
                rootView.getContext(),R.layout.spinner_style,plants4
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_style);
        edt_lik5.setAdapter(spinnerArrayAdapter4);

        btnNext = rootView.findViewById(R.id.btn_next1_lik_1);
//        btnNext.setVisibility(View.GONE);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
//                fms= getActivity().getSupportFragmentManager();
//                for(int i = 0; i < fms.getBackStackEntryCount(); ++i) {
//                    fms.popBackStack();
//                }
//                fms.beginTransaction().replace(R.id.content_frames, new FragmentDashboard()).commit();
            }
        });

        return rootView;
    }


    private VerificationError saveAndNext(View view){
//        try {
            Prefs.remove(StaticStrings.M1_lik1B);
            Prefs.remove(StaticStrings.M1_lik2B);
            Prefs.remove(StaticStrings.M1_lik3B);
            Prefs.remove(StaticStrings.M1_lik4B);
            Prefs.remove(StaticStrings.M1_lik5B);

            Prefs.putString(StaticStrings.M1_lik1B, edt_lik1.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M1_lik2B, edt_lik2.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M1_lik3B, edt_lik3.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M1_lik4B, edt_lik4.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M1_lik5B, edt_lik5.getSelectedItem().toString());
            Prefs.putString(StaticStrings.M1_update_at, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(Calendar.getInstance().getTime()));


            Utils.addQueueModul1(view.getContext(), SurveyKDZActivity.form_request_type);
            Utils.log("FORM REQUEST TYPE : " + SurveyKDZActivity.form_request_type);


            if (Utils.isOnline(getActivity())) {
                 Utils.sendModul1(view.getContext());
                 Utils.sendModulKeluarga(view.getContext());
                 Toast.makeText(getActivity(), "Berhasil Dimasukan Data Telah Tersimpan", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Tidak ada koneksi internet, data akan dikirim otomatis saat terhubung ke internet", Toast.LENGTH_LONG).show();
                StaticStrings.readyToSendViaOfflineModul1 = true;
            }

            isSuccess = true;
//        } catch (Exception e){
//            Utils.log("ERRROR AT END FORM : " + e.getMessage());
//            return new VerificationError("Mohon lengkapi form pada halaman ini");
//        }

        return null;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (isSuccess){
            return null;
        }

        return saveAndNext(rootView);

    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}