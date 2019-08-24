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
import com.karyastudio.izn.model.api.datakdz.KajianDampakZakatPojo;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.fragments.modul1.FragmentDataModul1;
import com.karyastudio.izn.views.fragments.modul1.FragmentModul1101;
import com.karyastudio.izn.views.fragments.modul1.FragmentModul1201;
import com.karyastudio.izn.views.fragments.modul1.FragmentModul1401;
import com.karyastudio.izn.views.fragments.modul1.FragmentModul1501;
import com.karyastudio.izn.views.fragments.modul1.FragmentModul1601;
import com.karyastudio.izn.views.fragments.modul1.FragmentModul1701;
import com.karyastudio.izn.views.fragments.modul1.FragmentModul1801;
import com.karyastudio.izn.views.fragments.modul1.FragmentModul1Likert1;
import com.karyastudio.izn.views.fragments.modul1.FragmentModul1Likert2;
import com.pixplicity.easyprefs.library.Prefs;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SurveyKDZActivity extends AppCompatActivity implements StepperLayout.StepperListener {

    private static MutableLiveData<Boolean> isComplete = new MutableLiveData<>();

    public static MutableLiveData<Boolean> getIsComplete(){
        return isComplete;
    }

    private StepperLayout mStepperLayout;
    private ArrayList<Step> steps;
    public static String form_input_id;

    public static String form_request_type;
    public static final String FORM_REQUEST_TYPE_DATA = "FORM_REQUEST_TYPE_DATA";
    public static final String REQUEST_TYPE_INSERT = "INSERT";
    public static final String REQUEST_TYPE_UPDATE = "UPDATE";

    public static KajianDampakZakatPojo data = null;

    // Callback OnCreate ini tidak akan dipanggil utk kedua kali saat perubahan konfigurasi (rotation/screen changes)
    // Karena pada manifest, activity ini diset supaya tidak merubah informasi & tampilan rotation
    // Dengan tidak memanggil onCreate mungkin
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_kdz);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        form_input_id = "GREENDAO-KDZ" + formatter.format(date);

        Intent intent = getIntent();
        form_request_type = intent.getStringExtra(FORM_REQUEST_TYPE_DATA);
        data = (KajianDampakZakatPojo) intent.getSerializableExtra("data");


        clearPrefs();
        if (data != null){
            initPrefs();
        }


        if (data != null)
        if (!data.getFk_id().contains("GREENDAO-KDZ")) {
            if (form_request_type.equals(REQUEST_TYPE_UPDATE)) {
                assert data != null;
                form_input_id = data.getFk_id();
            }
        } else {
            // Jika mengedit data yang mau baru saja mau diinsert, tapi belum dikirim
            // Artinya menupdate data yang sedang pending insert, jangan buat request type update
            // request type nya tetap buat menjadi insert. Karena masih memakai id random (date)
            form_request_type = REQUEST_TYPE_INSERT;
        }


        Utils.log("INPUT ID : " + form_input_id);

        steps = new ArrayList<>();
        steps.add(new FragmentModul1101());
        steps.add(new FragmentModul1201());
        steps.add(new FragmentModul1401());
        steps.add(new FragmentModul1501());
        steps.add(new FragmentModul1601());
        steps.add(new FragmentModul1701());
        steps.add(new FragmentModul1801());
        steps.add(new FragmentModul1Likert1());
        steps.add(new FragmentModul1Likert2());

        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        if (mStepperLayout.getAdapter() != null){
            Utils.log("ADAPTER IS NOT NULL");
        } else {
            Utils.log("ADAPTER IS NULL");
        }

        mStepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this, steps));
        mStepperLayout.setListener(this);
    }

    private void clearPrefs(){
        Prefs.remove(StaticStrings.M1_nama);
        Prefs.remove(StaticStrings.M1_101);
        Prefs.remove(StaticStrings.M1_102);
        Prefs.remove(StaticStrings.M1_103);
        Prefs.remove(StaticStrings.M1_104);
        Prefs.remove(StaticStrings.M1_105);
        Prefs.remove(StaticStrings.M1_106);
        Prefs.remove(StaticStrings.M1_107);
        Prefs.remove(StaticStrings.M1_108);
        Prefs.remove(StaticStrings.M1_109);
        Prefs.remove(StaticStrings.M1_110);

        Prefs.remove(StaticStrings.M1_401);
        Prefs.remove(StaticStrings.M1_402);
        Prefs.remove(StaticStrings.M1_403);
        Prefs.remove(StaticStrings.M1_404);
        Prefs.remove(StaticStrings.M1_405);
        Prefs.remove(StaticStrings.M1_406);
        Prefs.remove(StaticStrings.M1_407);

        Prefs.remove(StaticStrings.M1_501);
        Prefs.remove(StaticStrings.M1_502);
        Prefs.remove(StaticStrings.M1_503);
        Prefs.remove(StaticStrings.M1_504);
        Prefs.remove(StaticStrings.M1_505);
        Prefs.remove(StaticStrings.M1_506);
        Prefs.remove(StaticStrings.M1_507);
        Prefs.remove(StaticStrings.M1_508);
        Prefs.remove(StaticStrings.M1_509);
        Prefs.remove(StaticStrings.M1_510);

        Prefs.remove(StaticStrings.M1_601);
        Prefs.remove(StaticStrings.M1_602);
        Prefs.remove(StaticStrings.M1_603);
        Prefs.remove(StaticStrings.M1_604);
        Prefs.remove(StaticStrings.M1_605);
        Prefs.remove(StaticStrings.M1_606);
        Prefs.remove(StaticStrings.M1_607);
        Prefs.remove(StaticStrings.M1_608);
        Prefs.remove(StaticStrings.M1_609);
        Prefs.remove(StaticStrings.M1_610);
        Prefs.remove(StaticStrings.M1_611);
        Prefs.remove(StaticStrings.M1_612);
        Prefs.remove(StaticStrings.M1_613);
        Prefs.remove(StaticStrings.M1_614);
        Prefs.remove(StaticStrings.M1_615);
        Prefs.remove(StaticStrings.M1_616);
        Prefs.remove(StaticStrings.M1_617);
        Prefs.remove(StaticStrings.M1_618);

        Prefs.remove(StaticStrings.M1_701);
        Prefs.remove(StaticStrings.M1_702);
        Prefs.remove(StaticStrings.M1_703);

        Prefs.remove(StaticStrings.M1_801);
        Prefs.remove(StaticStrings.M1_802);
        Prefs.remove(StaticStrings.M1_803);
        Prefs.remove(StaticStrings.M1_804);
        Prefs.remove(StaticStrings.M1_805);
        Prefs.remove(StaticStrings.M1_806);
        Prefs.remove(StaticStrings.M1_807);
        Prefs.remove(StaticStrings.M1_808);
        Prefs.remove(StaticStrings.M1_809);
        Prefs.remove(StaticStrings.M1_810);
        Prefs.remove(StaticStrings.M1_811);
        Prefs.remove(StaticStrings.M1_812);
        Prefs.remove(StaticStrings.M1_813);
        Prefs.remove(StaticStrings.M1_814);
        Prefs.remove(StaticStrings.M1_815);

        Prefs.remove(StaticStrings.M1_lik1);
        Prefs.remove(StaticStrings.M1_lik2);
        Prefs.remove(StaticStrings.M1_lik3);
        Prefs.remove(StaticStrings.M1_lik4);
        Prefs.remove(StaticStrings.M1_lik5);

        Prefs.remove(StaticStrings.M1_lik1B);
        Prefs.remove(StaticStrings.M1_lik2B);
        Prefs.remove(StaticStrings.M1_lik3B);
        Prefs.remove(StaticStrings.M1_lik4B);
        Prefs.remove(StaticStrings.M1_lik5B);
    }

    private void initPrefs(){
        Prefs.putString(StaticStrings.M1_nama, data.getFk_nama());
        Prefs.putString(StaticStrings.M1_101, data.getFk_101_provinsi());
        Prefs.putString(StaticStrings.M1_102, data.getFk_102_kabupaten());
        Prefs.putString(StaticStrings.M1_103, data.getFk_103_kecamatan());
        Prefs.putString(StaticStrings.M1_104, data.getFk_104_desa());
        Prefs.putString(StaticStrings.M1_105, data.getFk_105_klasisfikasi_desan());
        Prefs.putString(StaticStrings.M1_106, data.getFk_106_kode_rumah());
        Prefs.putString(StaticStrings.M1_107, data.getFk_107_nama_kepala_rumah());
        Prefs.putString(StaticStrings.M1_108, data.getFk_108_jumlah_anggota_rumah());
        Prefs.putString(StaticStrings.M1_109, data.getFk_109_nomor_hp());
        Prefs.putString(StaticStrings.M1_110, data.getFk_110_alamat_lengkap());

        Prefs.putString(StaticStrings.M1_401, data.getFk_401_tabungan_bank_konvensional());
        Prefs.putString(StaticStrings.M1_402, data.getFk_402_tabungan_bank_syariah());
        Prefs.putString(StaticStrings.M1_403, data.getFk_403_tabungan_koeprasi_konvensional());
        Prefs.putString(StaticStrings.M1_404, data.getFk_404_tabungan_koeprasi_syariah());
        Prefs.putString(StaticStrings.M1_405, data.getFk_405_tabungan_lembaga_zakat());
        Prefs.putString(StaticStrings.M1_406, data.getFk_406_arisan());
        Prefs.putString(StaticStrings.M1_407, data.getFk_407_simpanan_di_rumah());

        Prefs.putString(StaticStrings.M1_501, data.getFk_501_memiliki_atap());
        Prefs.putString(StaticStrings.M1_502, data.getFk_502_memiliki_dinding());
        Prefs.putString(StaticStrings.M1_503, data.getFk_503_memiliki_listrik());
        Prefs.putString(StaticStrings.M1_504, data.getFk_504_memiliki_lantai());
        Prefs.putString(StaticStrings.M1_505, data.getFk_505_memiliki_air());
        Prefs.putString(StaticStrings.M1_506, data.getFk_506_memiliki_sanitai());
        Prefs.putString(StaticStrings.M1_507, data.getFk_507_memiliki_penyakit());
        Prefs.putString(StaticStrings.M1_508, data.getFk_508_tidak_memiliki_cacat());
        Prefs.putString(StaticStrings.M1_509, data.getFk_509_memiliki_bpjs());
        Prefs.putString(StaticStrings.M1_510, data.getFk_510_tidak_memiliki_rokok());

        Prefs.putString(StaticStrings.M1_601, data.getFk_601_kode());
        Prefs.putString(StaticStrings.M1_602, data.getFk_602_jenis_lembaga());
        Prefs.putString(StaticStrings.M1_603, data.getFk_603_jenis_lembaga());
        Prefs.putString(StaticStrings.M1_604, data.getFk_604_tanggal_menerima());
        Prefs.putString(StaticStrings.M1_605, data.getFk_605_pendapatan());
        Prefs.putString(StaticStrings.M1_606, data.getFk_606_berapa_kali());
        Prefs.putString(StaticStrings.M1_607, data.getFk_607_jenis_zakat());
        Prefs.putString(StaticStrings.M1_608, data.getFk_608_pangan());
        Prefs.putString(StaticStrings.M1_609, data.getFk_609_kesehatan());
        Prefs.putString(StaticStrings.M1_610, data.getFk_610_pendidikan());
        Prefs.putString(StaticStrings.M1_611, data.getFk_611_lainnya());
        Prefs.putString(StaticStrings.M1_612, data.getFk_612_total_bantuan());
        Prefs.putString(StaticStrings.M1_613, data.getFk_613_bantuan_modal());
        Prefs.putString(StaticStrings.M1_614, data.getFk_614_bantuan_alat());
        Prefs.putString(StaticStrings.M1_615, data.getFk_615_bantuan_lain());
        Prefs.putString(StaticStrings.M1_616, data.getFk_616_total_bantuan());
        Prefs.putString(StaticStrings.M1_617, data.getFk_617());
        Prefs.putString(StaticStrings.M1_618, data.getFk_618());

        Prefs.putString(StaticStrings.M1_701, data.getFk_701());
        Prefs.putString(StaticStrings.M1_702, data.getFk_702());
        Prefs.putString(StaticStrings.M1_703, data.getFk_703());

        Prefs.putString(StaticStrings.M1_801, data.getFk_801());
        Prefs.putString(StaticStrings.M1_802, data.getFk_802());
        Prefs.putString(StaticStrings.M1_803, data.getFk_803());
        Prefs.putString(StaticStrings.M1_804, data.getFk_804());
        Prefs.putString(StaticStrings.M1_805, data.getFk_805());
        Prefs.putString(StaticStrings.M1_806, data.getFk_806());
        Prefs.putString(StaticStrings.M1_807, data.getFk_807());
        Prefs.putString(StaticStrings.M1_808, data.getFk_808());
        Prefs.putString(StaticStrings.M1_809, data.getFk_809());
        Prefs.putString(StaticStrings.M1_810, data.getFk_810());
        Prefs.putString(StaticStrings.M1_811, data.getFk_811());
        Prefs.putString(StaticStrings.M1_812, data.getFk_812());
        Prefs.putString(StaticStrings.M1_813, data.getFk_813());
        Prefs.putString(StaticStrings.M1_814, data.getFk_814());
        Prefs.putString(StaticStrings.M1_815, data.getFk_815());

        Prefs.putString(StaticStrings.M1_lik1, data.getFk_shalat());
        Prefs.putString(StaticStrings.M1_lik2, data.getFk_puasa());
        Prefs.putString(StaticStrings.M1_lik3, data.getFk_zakat());
        Prefs.putString(StaticStrings.M1_lik4, data.getFk_lingkungan());
        Prefs.putString(StaticStrings.M1_lik5, data.getFk_kebijakan());

        Prefs.putString(StaticStrings.M1_lik1B, data.getFk_shalat2());
        Prefs.putString(StaticStrings.M1_lik2B, data.getFk_puasa2());
        Prefs.putString(StaticStrings.M1_lik3B, data.getFk_zakat2());
        Prefs.putString(StaticStrings.M1_lik4B, data.getFk_lingkungan2());
        Prefs.putString(StaticStrings.M1_lik5B, data.getFk_kebijakan2());
    }

    @Override
    public void onBackPressed(){
        // super.onBackPressed();
        int stepPos = mStepperLayout.getCurrentStepPosition();

        if (stepPos >= 1){
            mStepperLayout.setCurrentStepPosition(mStepperLayout.getCurrentStepPosition() - 1);
        } else {
            finish();
        }

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
        //Toast.makeText(getApplicationContext(), "ONCOMPLETED", Toast.LENGTH_SHORT).show();

        if (FragmentDataModul1.actionMode != null){
            FragmentDataModul1.actionMode.finish();

            // FragmentDataModul1.getInstance().populate();
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
