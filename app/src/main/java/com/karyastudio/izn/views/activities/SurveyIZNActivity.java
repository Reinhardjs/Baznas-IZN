package com.karyastudio.izn.views.activities;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.karyastudio.izn.R;
import com.karyastudio.izn.adapter.MyStepperAdapter;
import com.karyastudio.izn.model.api.dataizn.IndeksZakatNasionalPojo;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.fragments.modul2.FragmentDataModul2;
import com.karyastudio.izn.views.fragments.modul2.FragmentModul2101;
import com.karyastudio.izn.views.fragments.modul2.FragmentModul2201;
import com.karyastudio.izn.views.fragments.modul2.FragmentModul2301;
import com.karyastudio.izn.views.fragments.modul2.FragmentModul2401;
import com.karyastudio.izn.views.fragments.modul2.FragmentModul2501;
import com.karyastudio.izn.views.fragments.modul2.FragmentModul2601;
import com.karyastudio.izn.views.fragments.modul2.FragmentModul2701;
import com.karyastudio.izn.views.fragments.modul2.FragmentModul2801;
import com.karyastudio.izn.views.fragments.modul2.FragmentModul2901;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SurveyIZNActivity extends AppCompatActivity implements StepperLayout.StepperListener {

    private static MutableLiveData<Boolean> isComplete = new MutableLiveData<>();

    public static MutableLiveData<Boolean> getIsComplete(){
        return isComplete;
    }

    public static String form_input_id;

    public static String form_request_type;
    public static final String FORM_REQUEST_TYPE_DATA = "FORM_REQUEST_TYPE_DATA";
    public static final String REQUEST_TYPE_INSERT = "INSERT";
    public static final String REQUEST_TYPE_UPDATE = "UPDATE";

    public static IndeksZakatNasionalPojo data = null;

    public StepperLayout mStepperLayout;
    public ArrayList<Step> steps;

    public static SurveyIZNActivity INSTANCE;
    public static SurveyIZNActivity getInstance(){
        return INSTANCE;
    }

    // Callback OnCreate ini tidak akan dipanggil utk kedua kali saat perubahan konfigurasi (rotation/screen changes)
    // Karena pada manifest, activity ini diset supaya tidak merubah informasi & tampilan rotation
    // Dengan tidak memanggil onCreate mungkin
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_izn);

        INSTANCE = this;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        form_input_id = "GREENDAO-KDZ" + formatter.format(date);

        Intent intent = getIntent();
        form_request_type = intent.getStringExtra(FORM_REQUEST_TYPE_DATA);
        data = (IndeksZakatNasionalPojo) intent.getSerializableExtra("data");

        clearPrefs();
        if (data != null) {
            initPrefs(data);
        }


        if (data != null)
        if (!data.getFi_id().contains("GREENDAO-KDZ")) {
            if (form_request_type.equals(REQUEST_TYPE_UPDATE)){
                assert data != null;
                form_input_id = data.getFi_id();
            }
        } else {
            // Jika mengedit data yang mau baru saja mau diinsert, tapi belum dikirim
            // Artinya menupdate data yang sedang pending insert, jangan buat request type update
            // request type nya tetap buat menjadi insert. Karena masih memakai id random (date)
            form_request_type = REQUEST_TYPE_INSERT;
        }


        Utils.log("INPUT ID : " + form_input_id);

        steps = new ArrayList<>();
        steps.add(new FragmentModul2101());
        steps.add(new FragmentModul2201());
        steps.add(new FragmentModul2301());
        steps.add(new FragmentModul2401());
        steps.add(new FragmentModul2501());
        steps.add(new FragmentModul2601());
        steps.add(new FragmentModul2701());
        steps.add(new FragmentModul2801());
        steps.add(new FragmentModul2901());

        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this, steps));
        mStepperLayout.setListener(this);
    }

    @Override
    public void onBackPressed(){
        // super.onBackPressed();
        int stepPos = mStepperLayout.getCurrentStepPosition();

        if (stepPos >= 1){
            if (FragmentModul2101.gotos.equals("true")){
                mStepperLayout.setCurrentStepPosition(0);
            } else {
                mStepperLayout.setCurrentStepPosition(mStepperLayout.getCurrentStepPosition() - 1);
            }
        } else {
            finish();
        }

    }


    public void initPrefs(IndeksZakatNasionalPojo data){
        Prefs.putString(StaticStrings.M2_101, (String) data.getFi_101_jenis_lembaga());
        Prefs.putString(StaticStrings.M2_102, (String) data.getFi_102_nama_laz());
        Prefs.putString(StaticStrings.M2_103, (String) data.getFi_103_provinsi());
        Prefs.putString(StaticStrings.M2_104, (String) data.getFi_104_kabupaten());

        Prefs.putString(StaticStrings.M2_201, (String) data.getFi_201_regulasi());
        Prefs.putString(StaticStrings.M2_201yt, (String) data.getFi_201_regulasi_ada());

        Prefs.putString(StaticStrings.M2_301, (String) data.getFi_301_alokasi_apbn_2_tahun_lalu());
        Prefs.putString(StaticStrings.M2_301yt, (String) data.getFi_301_alokasi_apbn_2_tahun_lalu_ada());
        Prefs.putString(StaticStrings.M2_302, (String) data.getFi_302_alokasi_apbn_1_tahun_lalu());
        Prefs.putString(StaticStrings.M2_302yt, (String) data.getFi_302_alokasi_apbn_1_tahun_lalu_ada());

        Utils.log("MUSTAHIK KECAMATAN : " + (String) data.getFi_403_mustahik_kecamatan());

        Prefs.putString(StaticStrings.M2_401, (String) data.getFi_401_lembaga_zakat_resmi());
        Prefs.putString(StaticStrings.M2_401yt, (String) data.getFi_401_lembaga_zakat_resmi_ada());
        Prefs.putString(StaticStrings.M2_402, (String) data.getFi_402_jumlah_mustahik());
        Prefs.putString(StaticStrings.M2_403yt1, (String) data.getFi_403_mustahik_kabupaten());
        Prefs.putString(StaticStrings.M2_403yt2, (String) data.getFi_403_mustahik_kecamatan());
        Prefs.putString(StaticStrings.M2_404, (String) data.getFi_404_jumlah_muzakki());
        Prefs.putString(StaticStrings.M2_405, (String) data.getFi_405_jumlah_munsafik());
        Prefs.putString(StaticStrings.M2_406, (String) data.getFi_406_jumlah_muzakki_badan_usaha());

        Prefs.putString(StaticStrings.M2_501, (String) data.getFi_501_total_himpunan_tahun_2());
        Prefs.putString(StaticStrings.M2_502, (String) data.getFi_502_total_himpunan_tahun_1());

        Prefs.putString(StaticStrings.M2_601yt, (String) data.getFi_601_program_kerja());
        Prefs.putString(StaticStrings.M2_602yt, (String) data.getFi_602_rencana_strategis());
        Prefs.putString(StaticStrings.M2_603yt, (String) data.getFi_603_sop_ada());
        Prefs.putString(StaticStrings.M2_603, (String) data.getFi_603_sop());
        Prefs.putString(StaticStrings.M2_604yt, (String) data.getFi_604_iso_ada());
        Prefs.putString(StaticStrings.M2_604, (String) data.getFi_604_iso());

        Prefs.putString(StaticStrings.M2_701, (String) data.getFi_701_total_dana_zis());
        Prefs.putString(StaticStrings.M2_702yt, (String) data.getFi_702_dana_zis_dakwah_ada());
        Prefs.putString(StaticStrings.M2_702, (String) data.getFi_702_dana_zis_dakwah());
        Prefs.putString(StaticStrings.M2_7031, (String) data.getFi_703_penyaluran_zis_produktif_rencana());
        Prefs.putString(StaticStrings.M2_7032, (String) data.getFi_703_penyaluran_zis_produktif_realisasi());
        Prefs.putString(StaticStrings.M2_7041, (String) data.getFi_704_penyaluran_zis_sosial_rencana());
        Prefs.putString(StaticStrings.M2_7042, (String) data.getFi_704_penyaluran_zis_sosial_realisasi());

        Prefs.putString(StaticStrings.M2_801yt, (String) data.getFi_801_laporan_keuangan());
        Prefs.putString(StaticStrings.M2_802yt1, (String) data.getFi_802_laporan_keuangan_teraudit());
        Prefs.putString(StaticStrings.M2_802yt2, (String) data.getFi_802_laporan_keuangan_wtp());
        Prefs.putString(StaticStrings.M2_803yt, (String) data.getFi_803_laporan_keuangan_publikasi());
        Prefs.putString(StaticStrings.M2_804yt, (String) data.getFi_804_laporan_audit_syariah());

        Prefs.putString(StaticStrings.M2_901, (String) data.getFi_805_biaya_operasional());
    }

    public void clearPrefs(){
        Prefs.remove(StaticStrings.M2_101);
        Prefs.remove(StaticStrings.M2_102);
        Prefs.remove(StaticStrings.M2_103);
        Prefs.remove(StaticStrings.M2_104);

        Prefs.remove(StaticStrings.M2_201);
        Prefs.remove(StaticStrings.M2_201yt);

        Prefs.remove(StaticStrings.M2_301);
        Prefs.remove(StaticStrings.M2_301yt);
        Prefs.remove(StaticStrings.M2_302);
        Prefs.remove(StaticStrings.M2_302yt);

        Prefs.remove(StaticStrings.M2_401);
        Prefs.remove(StaticStrings.M2_401yt);
        Prefs.remove(StaticStrings.M2_402);
        Prefs.remove(StaticStrings.M2_403yt1);
        Prefs.remove(StaticStrings.M2_403yt2);
        Prefs.remove(StaticStrings.M2_404);
        Prefs.remove(StaticStrings.M2_405);
        Prefs.remove(StaticStrings.M2_406);

        Prefs.remove(StaticStrings.M2_501);
        Prefs.remove(StaticStrings.M2_502);

        Prefs.remove(StaticStrings.M2_601yt);
        Prefs.remove(StaticStrings.M2_602yt);
        Prefs.remove(StaticStrings.M2_603yt);
        Prefs.remove(StaticStrings.M2_604yt);
        Prefs.remove(StaticStrings.M2_603);
        Prefs.remove(StaticStrings.M2_604);

        Prefs.remove(StaticStrings.M2_701);
        Prefs.remove(StaticStrings.M2_702yt);
        Prefs.remove(StaticStrings.M2_702);
        Prefs.remove(StaticStrings.M2_7031);
        Prefs.remove(StaticStrings.M2_7032);
        Prefs.remove(StaticStrings.M2_7041);
        Prefs.remove(StaticStrings.M2_7042);

        Prefs.remove(StaticStrings.M2_801yt);
        Prefs.remove(StaticStrings.M2_802yt1);
        Prefs.remove(StaticStrings.M2_802yt2);
        Prefs.remove(StaticStrings.M2_803yt);
        Prefs.remove(StaticStrings.M2_804yt);

        Prefs.remove(StaticStrings.M2_901);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // toast.makeText(getApplicationContext(),"Configuration Changed", toast.LENGTH_SHORT).show();

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // toast.makeText(this, "landscape", toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            // toast.makeText(this, "portrait", toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCompleted(View completeButton) {
        // toast.makeText(this, "onCompleted!", toast.LENGTH_SHORT).show();
        if (FragmentDataModul2.actionMode != null){
            FragmentDataModul2.actionMode.finish();

            // FragmentDataModul2.getInstance().populate();
        }

        Toast.makeText(getApplicationContext(), "Berhasil ditambahkan ke antrian", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onError(VerificationError verificationError) {
        // toast.makeText(this, "onError! -> " + verificationError.getErrorMessage(), toast.LENGTH_SHORT).show();
        Toast.makeText(this, verificationError.getErrorMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStepSelected(int newStepPosition) {
        // toast.makeText(this, "onStepSelected! -> " + newStepPosition, toast.LENGTH_SHORT).show();
        hideKeyboard(this);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onReturn() {
        Toast.makeText(this, "on return", Toast.LENGTH_SHORT).show();
        finish();
    }
}
