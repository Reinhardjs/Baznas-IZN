package com.karyastudio.izn.utils;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.karyastudio.izn.dao.generateSchema.IZN;
import com.karyastudio.izn.dao.generateSchema.Iznformqueue;
import com.karyastudio.izn.dao.generateSchema.KDZ;
import com.karyastudio.izn.dao.generateSchema.Keluarga;
import com.karyastudio.izn.dao.managerSchema.IZNFormQueueManager;
import com.karyastudio.izn.dao.managerSchema.IZNManager;
import com.karyastudio.izn.dao.managerSchema.KDZManager;
import com.karyastudio.izn.dao.managerSchema.KeluargaManager;
import com.karyastudio.izn.model.api.dataizn.IndeksZakatNasionalPojo;
import com.karyastudio.izn.model.api.datakdz.KajianDampakZakatPojo;
import com.karyastudio.izn.model.api.izn.IndeksZakatNasional;
import com.karyastudio.izn.model.api.kdz.KajianDampakZakat;
import com.karyastudio.izn.model.api.kdz.KajianDampakZakatKeluarga;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.views.activities.SurveyIZNActivity;
import com.karyastudio.izn.views.activities.SurveyKDZActivity;
import com.karyastudio.izn.views.fragments.modul1.FragmentDataModul1;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    static String debug_else = "";

    private static List<Toast> toasts = new ArrayList<>();

    public static void log(Class anyClass, String message){
        Log.d("MYAPP", anyClass.getSimpleName() + ", " + message);
    }

    public static void log(String message){
        Log.d("MYAPP", message);
    }

    public static void toast(Context context, String msg) {
        for (Toast toast : Utils.toasts){
            toast.cancel();
        }

        toasts.clear();

        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toasts.add(toast);
        toast.show();
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        if (netInfo == null) return false;

        for (NetworkInfo networkInfo : netInfo) {
            if (networkInfo == null)
                continue;

            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }

        //should check null because in airplane mode it will be null

        boolean have_WIFI = false;
        boolean have_MobileData = false;

        for (NetworkInfo info : netInfo){
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    have_WIFI = true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                    have_MobileData = true;
        }
        return have_MobileData | have_WIFI;
    }

    public static Retrofit initializeRetrofit() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl(StaticStrings.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static void inizializePrefs(Context context, String packageName) {
        new Prefs.Builder()
                .setContext(context)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build();
    }


    public static void checkAppUpdates(Context context){
        // AppUpdater appUpdater = new AppUpdater(this);
        // appUpdater.start();

        // Creates instance of the manager.
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(context);

        // Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        Utils.log("REQUEST UPDATE ?");


        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {

            Utils.log("REQUEST UPDATE RESPONES ?");

            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // For a flexible update, use AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                // Request the update.

                Utils.log("REQUEST UPDATE YES");

                try {
                    appUpdateManager.startUpdateFlowForResult(
                            // Pass the intent that is returned by 'getAppUpdateInfo()'.
                            appUpdateInfo,
                            // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                            AppUpdateType.FLEXIBLE,
                            // The current activity making the update request.
                            (Activity) context,
                            // Include a request code to later monitor this update request.
                            123);
                } catch (IntentSender.SendIntentException e) {
                    log("UPDATE MANAGER EXCEPTION : " + e.toString());
                    // Toast.makeText(context, "Checking updates, (1)", Toast.LENGTH_SHORT).show();
                    manualUpdateRequest(context);
                    debug_else = "(1)";
                    e.printStackTrace();
                }
            }

            else {
                // Toast.makeText(context, "Checking updates, (2)", Toast.LENGTH_SHORT).show();
                manualUpdateRequest(context);
                debug_else = "(2)";
            }

        });
    }

    public static void manualUpdateRequest(Context context){
        WSCallerVersionListener listener = new WSCallerVersionListener() {
            @Override
            public void onGetResponse(boolean isUpdateAvailable, String versionName) {
                Log.e("ResultAPPMAIN", String.valueOf(isUpdateAvailable));
                if (isUpdateAvailable) {
                    showUpdateDialog(versionName, context);
                }
            }
        };

        new GooglePlayStoreAppVersionNameLoader(context, listener).execute();
    }

    /**
     * Method to show update dialog
     */
    private static void showUpdateDialog(String versionName, Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // alertDialogBuilder.setTitle(context.getString(R.string.app_name));
        // alertDialogBuilder.setMessage(MainActivity.this.getString(R.string.update_message));
        alertDialogBuilder.setTitle("Pembaruan aplikasi " + debug_else);
        alertDialogBuilder.setMessage("Versi terbaru (v" + versionName + ") aplikasi tersedia di playstore. Update sekarang?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
                dialog.cancel();
            }
        });
//        alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (isForceUpdate) {
//                    finish();
//                }
//                dialog.dismiss();
//            }
//        });

        alertDialogBuilder.show();
    }



    public static <T> List<T> removeDuplicates(List<T> list)
    {

        // Create a new ArrayList
        List<T> newList = new ArrayList<T>();

        // Traverse through the first keluargaList
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new keluargaList
        return newList;
    }

    public static List<IZN> removeDuplicatesIZN(List<IZN> list){
        List<IZN> newList = new ArrayList<>();

        for (IZN element : list){
            boolean found = false;

            for (IZN newElement : newList){
                if (newElement.getFi_id().equals(element.getFi_id())){
                    found = true;
                }
            }

            if (!found){
                newList.add(element);
            }
        }

        return newList;
    }

    public static List<KDZ> removeDuplicatesKDZ(List<KDZ> list){
        List<KDZ> newList = new ArrayList<>();

        for (KDZ element : list){
            boolean found = false;

            for (KDZ newElement : newList){
                if (newElement.getFk_id().equals(element.getFk_id())){
                    found = true;
                }
            }

            if (!found){
                newList.add(element);
            }
        }

        return newList;
    }

    public static boolean replaceOldIZN(List<IZN> list, IZN newElement){
        boolean isFound = false;

        try {
            for (IZN element : list) {
                if (element.getFi_id().equals(newElement.getFi_id())) {
                    isFound = true;
                    int index = list.indexOf(element);

                    list.remove(index);
                    list.add(index, newElement);
                    break;
                }
            }
        } catch (Exception e){
            Utils.log("replace old IZN EXCEPTION : " + e.toString());
        }

        return isFound;
    }

    public static boolean replaceOldKDZ(List<KDZ> list, KDZ newElement){
        boolean isFound = false;

        try {
            for (KDZ element : list) {
                if (element.getFk_id().equals(newElement.getFk_id())) {
                    isFound = true;
                    int index = list.indexOf(element);

                    list.remove(index);
                    list.add(index, newElement);
                    break;
                }
            }
        } catch (Exception e){
            Utils.log("replace old KDZ EXCEPTION : " + e.toString());
        }

        return isFound;
    }

    public static void sendModulKeluarga(Context context, String fk_id, String type) {

        List<Keluarga> queue = KeluargaManager.loadAllwithFKID(context, fk_id);
        // List<Keluarga> queue = removeDuplicates(list);

        Utils.log("SEND MODUL KELUARGA COUNT : " + queue.size());

        for (int i = 0; i < queue.size(); i++){
            Utils.log("KELUARGA FKI ID : " + queue.get(i).getFki_id());
            Utils.log("Keluarga FKI FK ID : " + queue.get(i).getFki_fk_id());
            Utils.log("KELUARGA NAMA : " + queue.get(i).getFk_202_nama());
            Utils.log("KELUARGA POS : " + i);

            saveAndNextKeluarga(context,
                    type,
                    queue.get(i)
            );
        }

        // KeluargaManager.removeAll(context);
    }


    public static void addQueueModul1(Context context, String type) {
//        Kdzformqueue KDZFORMQUEUE = new Kdzformqueue();
        KDZ KDZFORMQUEUE = new KDZ();
        KDZFORMQUEUE.setCountKeluarga(Prefs.getString(StaticStrings.M1_108, "0"));
        KDZFORMQUEUE.setStatus(StaticStrings.KDZ_STATUS_PENDING);
        KDZFORMQUEUE.setRequest_type(type);
        KDZFORMQUEUE.setFk_id(SurveyKDZActivity.form_input_id);
        KDZFORMQUEUE.setUID(Prefs.getString("UID", "kosong"));
        KDZFORMQUEUE.setM1_created_at(Prefs.getString(StaticStrings.M1_created_at, "kosong"));
        KDZFORMQUEUE.setM1_updated_at(Prefs.getString(StaticStrings.M1_update_at, "kosong"));
        KDZFORMQUEUE.setM1_nama(Prefs.getString(StaticStrings.M1_nama, "kosong"));
        KDZFORMQUEUE.setM1_101(Prefs.getString(StaticStrings.M1_101, "kosong"));
        KDZFORMQUEUE.setM1_102(Prefs.getString(StaticStrings.M1_102, "kosong"));
        KDZFORMQUEUE.setM1_103(Prefs.getString(StaticStrings.M1_103, "kosong"));
        KDZFORMQUEUE.setM1_104(Prefs.getString(StaticStrings.M1_104, "kosong"));
        KDZFORMQUEUE.setM1_105(Prefs.getString(StaticStrings.M1_105, "kosong"));
        KDZFORMQUEUE.setM1_106(Prefs.getString(StaticStrings.M1_106, "kosong"));
        KDZFORMQUEUE.setM1_107(Prefs.getString(StaticStrings.M1_107, "kosong"));
        KDZFORMQUEUE.setM1_108(Prefs.getString(StaticStrings.M1_108, "0"));
        KDZFORMQUEUE.setM1_109(Prefs.getString(StaticStrings.M1_109, "kosong"));
        KDZFORMQUEUE.setM1_110(Prefs.getString(StaticStrings.M1_110, "kosong"));
        KDZFORMQUEUE.setM1_401(Prefs.getString(StaticStrings.M1_401, "kosong"));
        KDZFORMQUEUE.setM1_402(Prefs.getString(StaticStrings.M1_402, "kosong"));
        KDZFORMQUEUE.setM1_403(Prefs.getString(StaticStrings.M1_403, "kosong"));
        KDZFORMQUEUE.setM1_404(Prefs.getString(StaticStrings.M1_404, "kosong"));
        KDZFORMQUEUE.setM1_405(Prefs.getString(StaticStrings.M1_405, "kosong"));
        KDZFORMQUEUE.setM1_406(Prefs.getString(StaticStrings.M1_406, "kosong"));
        KDZFORMQUEUE.setM1_407(Prefs.getString(StaticStrings.M1_407, "kosong"));
        KDZFORMQUEUE.setM1_501(Prefs.getString(StaticStrings.M1_501, "kosong"));
        KDZFORMQUEUE.setM1_502(Prefs.getString(StaticStrings.M1_502, "kosong"));
        KDZFORMQUEUE.setM1_503(Prefs.getString(StaticStrings.M1_503, "kosong"));
        KDZFORMQUEUE.setM1_504(Prefs.getString(StaticStrings.M1_504, "kosong"));
        KDZFORMQUEUE.setM1_505(Prefs.getString(StaticStrings.M1_505, "kosong"));
        KDZFORMQUEUE.setM1_506(Prefs.getString(StaticStrings.M1_506, "kosong"));
        KDZFORMQUEUE.setM1_507(Prefs.getString(StaticStrings.M1_507, "kosong"));
        KDZFORMQUEUE.setM1_508(Prefs.getString(StaticStrings.M1_508, "kosong"));
        KDZFORMQUEUE.setM1_509(Prefs.getString(StaticStrings.M1_509, "kosong"));
        KDZFORMQUEUE.setM1_510(Prefs.getString(StaticStrings.M1_510, "kosong"));
        KDZFORMQUEUE.setM1_601(Prefs.getString(StaticStrings.M1_601, "kosong"));
        KDZFORMQUEUE.setM1_601_kode("");
        KDZFORMQUEUE.setM1_602(Prefs.getString(StaticStrings.M1_602, "kosong"));
        KDZFORMQUEUE.setM1_602_kode("");
        KDZFORMQUEUE.setM1_603(Prefs.getString(StaticStrings.M1_603, "kosong"));
        KDZFORMQUEUE.setM1_603_kode("");
        KDZFORMQUEUE.setM1_604(Prefs.getString(StaticStrings.M1_604, "kosong"));
        KDZFORMQUEUE.setM1_605(Prefs.getString(StaticStrings.M1_605, "kosong"));
        KDZFORMQUEUE.setM1_606(Prefs.getString(StaticStrings.M1_606, "kosong"));
        KDZFORMQUEUE.setM1_607(Prefs.getString(StaticStrings.M1_607, "kosong"));
        KDZFORMQUEUE.setM1_608(Prefs.getString(StaticStrings.M1_608, "kosong"));
        KDZFORMQUEUE.setM1_609(Prefs.getString(StaticStrings.M1_609, "kosong"));
        KDZFORMQUEUE.setM1_610(Prefs.getString(StaticStrings.M1_610, "kosong"));
        KDZFORMQUEUE.setM1_611(Prefs.getString(StaticStrings.M1_611, "kosong"));
        KDZFORMQUEUE.setM1_612(Prefs.getString(StaticStrings.M1_612, "kosong"));
        KDZFORMQUEUE.setM1_613(Prefs.getString(StaticStrings.M1_613, "kosong"));
        KDZFORMQUEUE.setM1_614(Prefs.getString(StaticStrings.M1_614, "kosong"));
        KDZFORMQUEUE.setM1_615(Prefs.getString(StaticStrings.M1_615, "kosong"));
        KDZFORMQUEUE.setM1_616(Prefs.getString(StaticStrings.M1_616, "kosong"));
        KDZFORMQUEUE.setM1_617(Prefs.getString(StaticStrings.M1_617, "kosong"));
        KDZFORMQUEUE.setM1_618(Prefs.getString(StaticStrings.M1_618, "kosong"));
        KDZFORMQUEUE.setM1_701(Prefs.getString(StaticStrings.M1_701, "kosong"));
        KDZFORMQUEUE.setM1_702(Prefs.getString(StaticStrings.M1_702, "kosong"));
        KDZFORMQUEUE.setM1_703(Prefs.getString(StaticStrings.M1_703, "kosong"));
        KDZFORMQUEUE.setM1_801(Prefs.getString(StaticStrings.M1_801, "kosong"));
        KDZFORMQUEUE.setM1_802(Prefs.getString(StaticStrings.M1_802, "kosong"));
        KDZFORMQUEUE.setM1_803(Prefs.getString(StaticStrings.M1_803, "kosong"));
        KDZFORMQUEUE.setM1_804(Prefs.getString(StaticStrings.M1_804, "kosong"));
        KDZFORMQUEUE.setM1_805(Prefs.getString(StaticStrings.M1_805, "kosong"));
        KDZFORMQUEUE.setM1_806(Prefs.getString(StaticStrings.M1_806, "kosong"));
        KDZFORMQUEUE.setM1_807(Prefs.getString(StaticStrings.M1_807, "kosong"));
        KDZFORMQUEUE.setM1_808(Prefs.getString(StaticStrings.M1_808, "kosong"));
        KDZFORMQUEUE.setM1_809(Prefs.getString(StaticStrings.M1_809, "kosong"));
        KDZFORMQUEUE.setM1_810(Prefs.getString(StaticStrings.M1_810, "kosong"));
        KDZFORMQUEUE.setM1_811(Prefs.getString(StaticStrings.M1_811, "kosong"));
        KDZFORMQUEUE.setM1_812(Prefs.getString(StaticStrings.M1_812, "kosong"));
        KDZFORMQUEUE.setM1_813(Prefs.getString(StaticStrings.M1_813, "kosong"));
        KDZFORMQUEUE.setM1_814(Prefs.getString(StaticStrings.M1_814, "kosong"));
        KDZFORMQUEUE.setM1_815(Prefs.getString(StaticStrings.M1_815, "kosong"));
        KDZFORMQUEUE.setM1_lik1(Prefs.getString(StaticStrings.M1_lik1, "kosong"));
        KDZFORMQUEUE.setM1_lik2(Prefs.getString(StaticStrings.M1_lik2, "kosong"));
        KDZFORMQUEUE.setM1_lik3(Prefs.getString(StaticStrings.M1_lik3, "kosong"));
        KDZFORMQUEUE.setM1_lik4(Prefs.getString(StaticStrings.M1_lik4, "kosong"));
        KDZFORMQUEUE.setM1_lik5(Prefs.getString(StaticStrings.M1_lik5, "kosong"));
        KDZFORMQUEUE.setM1_lik1B(Prefs.getString(StaticStrings.M1_lik1B, "kosong"));
        KDZFORMQUEUE.setM1_lik2B(Prefs.getString(StaticStrings.M1_lik2B, "kosong"));
        KDZFORMQUEUE.setM1_lik3B(Prefs.getString(StaticStrings.M1_lik3B, "kosong"));
        KDZFORMQUEUE.setM1_lik4B(Prefs.getString(StaticStrings.M1_lik4B, "kosong"));
        KDZFORMQUEUE.setM1_lik5B(Prefs.getString(StaticStrings.M1_lik5B, "kosong"));

        //KDZFormQueueManager.insertOrReplace(context, KDZFORMQUEUE);
        KDZManager.insertOrReplace(context, KDZFORMQUEUE, SurveyKDZActivity.getIsComplete());
    }

    public static void addQueueModul2(Context context, String type){
        IZN iznformqueue = new IZN();
        iznformqueue.setStatus(StaticStrings.IZN_STATUS_PENDING);
        iznformqueue.setRequest_type(type);
        iznformqueue.setFi_id(SurveyIZNActivity.form_input_id);
        iznformqueue.setFi_u_id(Prefs.getString("UID", "kosong"));
        iznformqueue.setFi_date_created(Prefs.getString(StaticStrings.M2_created_at, "kosong"));
        iznformqueue.setFi_date_updated(Prefs.getString(StaticStrings.M2_update_at, "kosong"));
        iznformqueue.setFi_101_jenis_lembaga(Prefs.getString(StaticStrings.M2_101, "kosong"));
        iznformqueue.setFi_102_nama_laz(Prefs.getString(StaticStrings.M2_102, "kosong"));
        iznformqueue.setFi_103_provinsi(Prefs.getString(StaticStrings.M2_103, "kosong"));
        iznformqueue.setFi_104_kabupaten(Prefs.getString(StaticStrings.M2_104, "kosong"));
        iznformqueue.setFi_201_regulasi_ada(Prefs.getString(StaticStrings.M2_201yt, "-1. Nochoice"));
        iznformqueue.setFi_201_regulasi(Prefs.getString(StaticStrings.M2_201, ""));
        iznformqueue.setFi_301_alokasi_apbn_2_tahun_lalu_ada(Prefs.getString(StaticStrings.M2_301yt, "-1. Nochoice"));
        iznformqueue.setFi_301_alokasi_apbn_2_tahun_lalu(Prefs.getString(StaticStrings.M2_301, "0"));
        iznformqueue.setFi_302_alokasi_apbn_1_tahun_lalu_ada(Prefs.getString(StaticStrings.M2_302yt, "-1. Nochoice"));
        iznformqueue.setFi_302_alokasi_apbn_1_tahun_lalu(Prefs.getString(StaticStrings.M2_302, "0"));
        iznformqueue.setFi_401_lembaga_zakat_resmi_ada(Prefs.getString(StaticStrings.M2_401yt, "-1. Nochoice"));
        iznformqueue.setFi_401_lembaga_zakat_resmi(Prefs.getString(StaticStrings.M2_401, "0"));
        iznformqueue.setFi_402_jumlah_mustahik(Prefs.getString(StaticStrings.M2_402, "kosong"));
        iznformqueue.setFi_403_mustahik_kabupaten(Prefs.getString(StaticStrings.M2_403yt1, "kosong"));
        iznformqueue.setFi_403_mustahik_kecamatan(Prefs.getString(StaticStrings.M2_403yt2, "kosong"));
        iznformqueue.setFi_404_jumlah_muzakki(Prefs.getString(StaticStrings.M2_404, "kosong"));
        iznformqueue.setFi_405_jumlah_munsafki(Prefs.getString(StaticStrings.M2_405, "kosong"));
        iznformqueue.setFi_406_jumlah_muzakki_badan_usaha(Prefs.getString(StaticStrings.M2_406, "kosong"));
        iznformqueue.setFi_501_total_himpunan_tahun_2(Prefs.getString(StaticStrings.M2_501, "kosong"));
        iznformqueue.setFi_502_total_himpunan_tahun_1(Prefs.getString(StaticStrings.M2_502, "kosong"));
        iznformqueue.setFi_601_program_kerja(Prefs.getString(StaticStrings.M2_601yt, "kosong"));
        iznformqueue.setFi_602_rencana_strategis(Prefs.getString(StaticStrings.M2_602yt, "kosong"));
        iznformqueue.setFi_603_sop_ada(Prefs.getString(StaticStrings.M2_603yt, "kosong"));
        iznformqueue.setFi_603_sop(Prefs.getString(StaticStrings.M2_603, "kosong"));
        iznformqueue.setFi_604_iso_ada(Prefs.getString(StaticStrings.M2_604yt, "kosong"));
        iznformqueue.setFi_604_iso(Prefs.getString(StaticStrings.M2_604, "kosong"));
        iznformqueue.setFi_701_total_dana_zis(Prefs.getString(StaticStrings.M2_701, "kosong"));
        iznformqueue.setFi_702_dana_zis_dakwah_ada(Prefs.getString(StaticStrings.M2_702yt, "kosong"));
        iznformqueue.setFi_702_dana_zis_dakwah(Prefs.getString(StaticStrings.M2_702, "kosong"));
        iznformqueue.setFi_703_penyaluran_zis_produktif_rencana(Prefs.getString(StaticStrings.M2_7031, "kosong"));
        iznformqueue.setFi_703_penyaluran_zis_produktif_realisasi(Prefs.getString(StaticStrings.M2_7032, "kosong"));
        iznformqueue.setFi_704_penyaluran_zis_sosial_rencana(Prefs.getString(StaticStrings.M2_7041, "kosong"));
        iznformqueue.setFi_704_penyaluran_zis_sosial_realisasi(Prefs.getString(StaticStrings.M2_7042, "kosong"));
        iznformqueue.setFi_801_laporan_keuangan(Prefs.getString(StaticStrings.M2_801yt, "kosong"));
        iznformqueue.setFi_802_laporan_keuangan_teraudit(Prefs.getString(StaticStrings.M2_802yt1, "kosong"));
        iznformqueue.setFi_802_laporan_keuangan_wtp(Prefs.getString(StaticStrings.M2_802yt2, "kosong"));
        iznformqueue.setFi_803_laporan_keuangan_publikasi(Prefs.getString(StaticStrings.M2_803yt, "kosong"));
        iznformqueue.setFi_804_laporan_audit_syariah(Prefs.getString(StaticStrings.M2_804yt, "kosong"));
        iznformqueue.setFi_901_biaya_operasional(Prefs.getString(StaticStrings.M2_901, "kosong"));

        // IZNFormQueueManager.insertOrReplace(context, iznformqueue);
        IZNManager.insertOrReplace(context, iznformqueue, SurveyIZNActivity.getIsComplete());
        Utils.log("INSERT M2 SUCCESS");
    }

    public static void sendModul1(Context context){
        List<KDZ> list = KDZManager.loadAll(context);
        List<KDZ> newList = removeDuplicates(list);

        for (KDZ queue : newList) {
            saveAndNextKajian(context,
                    SetGet.getResponseFromKDZ(queue)
            );
        }

        KDZManager.removeAll(context);
    }

    public static void sendModul2(Context context) {
        List<Iznformqueue> list = IZNFormQueueManager.loadAll(context);
        List<Iznformqueue> newList = removeDuplicates(list);

        Utils.log("SEND MODULE QUEUE SIZE : " + list.size());

//        for (Iznformqueue queue : newList) {
//            saveAndNext(context,
//                    queue.getRequest_type(),
//                    queue.getFi_id(),
//                    queue.getFi_u_id(),
//                    queue.getFi_date_created(),
//                    queue.getFi_date_updated(),
//                    queue.getFi_101_jenis_lembaga(),
//                    queue.getFi_102_nama_laz(),
//                    queue.getFi_103_provinsi(),
//                    queue.getFi_104_kabupaten(),
//                    queue.getFi_201_regulasi_ada(),
//                    queue.getFi_201_regulasi(),
//                    queue.getFi_301_alokasi_apbn_2_tahun_lalu_ada(),
//                    queue.getFi_301_alokasi_apbn_2_tahun_lalu(),
//                    queue.getFi_302_alokasi_apbn_1_tahun_lalu_ada(),
//                    queue.getFi_302_alokasi_apbn_1_tahun_lalu(),
//                    queue.getFi_401_lembaga_zakat_resmi_ada(),
//                    queue.getFi_401_lembaga_zakat_resmi(),
//                    queue.getFi_402_jumlah_mustahik(),
//                    queue.getFi_403_mustahik_kabupaten(),
//                    queue.getFi_403_mustahik_kecamatan(),
//                    queue.getFi_404_jumlah_muzakki(),
//                    queue.getFi_405_jumlah_munsafki(),
//                    queue.getFi_406_jumlah_muzakki_badan_usaha(),
//                    queue.getFi_501_total_himpunan_tahun_2(),
//                    queue.getFi_502_total_himpunan_tahun_1(),
//                    queue.getFi_601_program_kerja(),
//                    queue.getFi_602_rencana_strategis(),
//                    queue.getFi_603_sop_ada(),
//                    queue.getFi_603_sop(),
//                    queue.getFi_604_iso_ada(),
//                    queue.getFi_604_iso(),
//                    queue.getFi_701_total_dana_zis(),
//                    queue.getFi_702_dana_zis_dakwah_ada(),
//                    queue.getFi_702_dana_zis_dakwah(),
//                    queue.getFi_703_penyaluran_zis_produktif_rencana(),
//                    queue.getFi_703_penyaluran_zis_produktif_realisasi(),
//                    queue.getFi_704_penyaluran_zis_sosial_rencana(),
//                    queue.getFi_704_penyaluran_zis_sosial_realisasi(),
//                    queue.getFi_801_laporan_keuangan(),
//                    queue.getFi_802_laporan_keuangan_teraudit(),
//                    queue.getFi_802_laporan_keuangan_wtp(),
//                    queue.getFi_803_laporan_keuangan_publikasi(),
//                    queue.getFi_804_laporan_audit_syariah(),
//                    queue.getFi_901_biaya_operasional()
//            );
//        }

        IZNFormQueueManager.removeAll(context);
    }


    public static void saveAndNext(final Context context, IndeksZakatNasionalPojo data) {

        Utils.log("SENDING MODUL 2 TO SERVER");

        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<IndeksZakatNasional> result = null;

        IZN queue = SetGet.getIZNFromResponse(data);

        if (queue.getRequest_type().equals(SurveyIZNActivity.REQUEST_TYPE_INSERT)) {
            result =
                    apiService.izn(StaticStrings.API_KEY,
                            queue.getFi_id(),
                            queue.getFi_u_id(),
                            queue.getFi_date_created(),
                            queue.getFi_date_updated(),
                            queue.getFi_101_jenis_lembaga(),
                            queue.getFi_102_nama_laz(),
                            queue.getFi_103_provinsi(),
                            queue.getFi_104_kabupaten(),
                            queue.getFi_201_regulasi_ada(),
                            queue.getFi_201_regulasi(),
                            queue.getFi_301_alokasi_apbn_2_tahun_lalu_ada(),
                            queue.getFi_301_alokasi_apbn_2_tahun_lalu(),
                            queue.getFi_302_alokasi_apbn_1_tahun_lalu_ada(),
                            queue.getFi_302_alokasi_apbn_1_tahun_lalu(),
                            queue.getFi_401_lembaga_zakat_resmi_ada(),
                            queue.getFi_401_lembaga_zakat_resmi(),
                            queue.getFi_402_jumlah_mustahik(),
                            queue.getFi_403_mustahik_kabupaten(),
                            queue.getFi_403_mustahik_kecamatan(),
                            queue.getFi_404_jumlah_muzakki(),
                            queue.getFi_405_jumlah_munsafki(),
                            queue.getFi_406_jumlah_muzakki_badan_usaha(),
                            queue.getFi_501_total_himpunan_tahun_2(),
                            queue.getFi_502_total_himpunan_tahun_1(),
                            queue.getFi_601_program_kerja(),
                            queue.getFi_602_rencana_strategis(),
                            queue.getFi_603_sop_ada(),
                            queue.getFi_603_sop(),
                            queue.getFi_604_iso_ada(),
                            queue.getFi_604_iso(),
                            queue.getFi_701_total_dana_zis(),
                            queue.getFi_702_dana_zis_dakwah_ada(),
                            queue.getFi_702_dana_zis_dakwah(),
                            queue.getFi_703_penyaluran_zis_produktif_rencana(),
                            queue.getFi_703_penyaluran_zis_produktif_realisasi(),
                            queue.getFi_704_penyaluran_zis_sosial_rencana(),
                            queue.getFi_704_penyaluran_zis_sosial_realisasi(),
                            queue.getFi_801_laporan_keuangan(),
                            queue.getFi_802_laporan_keuangan_teraudit(),
                            queue.getFi_802_laporan_keuangan_wtp(),
                            queue.getFi_803_laporan_keuangan_publikasi(),
                            queue.getFi_804_laporan_audit_syariah(),
                            queue.getFi_901_biaya_operasional()
                    );
        } else if (queue.getRequest_type().equals(SurveyKDZActivity.REQUEST_TYPE_UPDATE)){
            result =
                    apiService.iznUpdate(StaticStrings.API_KEY,
                            queue.getFi_id(),
                            queue.getFi_u_id(),
                            queue.getFi_date_created(),
                            queue.getFi_date_updated(),
                            queue.getFi_101_jenis_lembaga(),
                            queue.getFi_102_nama_laz(),
                            queue.getFi_103_provinsi(),
                            queue.getFi_104_kabupaten(),
                            queue.getFi_201_regulasi_ada(),
                            queue.getFi_201_regulasi(),
                            queue.getFi_301_alokasi_apbn_2_tahun_lalu_ada(),
                            queue.getFi_301_alokasi_apbn_2_tahun_lalu(),
                            queue.getFi_302_alokasi_apbn_1_tahun_lalu_ada(),
                            queue.getFi_302_alokasi_apbn_1_tahun_lalu(),
                            queue.getFi_401_lembaga_zakat_resmi_ada(),
                            queue.getFi_401_lembaga_zakat_resmi(),
                            queue.getFi_402_jumlah_mustahik(),
                            queue.getFi_403_mustahik_kabupaten(),
                            queue.getFi_403_mustahik_kecamatan(),
                            queue.getFi_404_jumlah_muzakki(),
                            queue.getFi_405_jumlah_munsafki(),
                            queue.getFi_406_jumlah_muzakki_badan_usaha(),
                            queue.getFi_501_total_himpunan_tahun_2(),
                            queue.getFi_502_total_himpunan_tahun_1(),
                            queue.getFi_601_program_kerja(),
                            queue.getFi_602_rencana_strategis(),
                            queue.getFi_603_sop_ada(),
                            queue.getFi_603_sop(),
                            queue.getFi_604_iso_ada(),
                            queue.getFi_604_iso(),
                            queue.getFi_701_total_dana_zis(),
                            queue.getFi_702_dana_zis_dakwah_ada(),
                            queue.getFi_702_dana_zis_dakwah(),
                            queue.getFi_703_penyaluran_zis_produktif_rencana(),
                            queue.getFi_703_penyaluran_zis_produktif_realisasi(),
                            queue.getFi_704_penyaluran_zis_sosial_rencana(),
                            queue.getFi_704_penyaluran_zis_sosial_realisasi(),
                            queue.getFi_801_laporan_keuangan(),
                            queue.getFi_802_laporan_keuangan_teraudit(),
                            queue.getFi_802_laporan_keuangan_wtp(),
                            queue.getFi_803_laporan_keuangan_publikasi(),
                            queue.getFi_804_laporan_audit_syariah(),
                            queue.getFi_901_biaya_operasional()
                    );
        }

        result.enqueue(new Callback<IndeksZakatNasional>() {
            @Override
            public void onResponse(@NonNull Call<IndeksZakatNasional> call, @NonNull Response<IndeksZakatNasional> response) {
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {

                        String familyID;
                        if (queue.getRequest_type().equals(SurveyIZNActivity.REQUEST_TYPE_INSERT)) {
                            Prefs.putString("parent_id_izn", response.body().getFi_id().toString());
                            Utils.log("MESSAGE : " + response.body().getMessage());

                            Utils.log("Send Modul 2 Berhasil, PARENT/FAMILY ID : " + response.body().getFi_id().toString());

                            familyID = response.body().getFi_id().toString();

                            // Karena id nya itu random (date) maka harus diupdate jadi id yang beneran
                            data.setFi_id(familyID);
                        } else {
                            familyID = queue.getFi_id();
                        }

                        Toast.makeText(context, "Berhasil dikirim ke server", Toast.LENGTH_LONG).show();

                        queue.setStatus(StaticStrings.IZN_STATUS_SENT);
                        IZNManager.remove(context, queue);

                        queue.setFi_id(familyID);
                        IZNManager.insertOrReplace(context, queue, null);

                        data.setStatus(StaticStrings.IZN_STATUS_SENT);
                        data.getLiveStatus().setValue(StaticStrings.IZN_STATUS_SENT);

                    } else {
                        Toast.makeText(context, "Data gagal dikirim, silakan periksa data Anda", Toast.LENGTH_LONG).show();
                    }

                    Utils.log("RESPONSE FROM SERVER SEND MODUL 2 : " + response.body().getMessage());
                } catch (Exception e) {
                    Utils.log("CATCH EXCEPTION : " + e.toString());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<IndeksZakatNasional> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static void saveAndNextKajian(final Context context,
                                          KajianDampakZakatPojo data) {

        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<KajianDampakZakat> result = null;

        KDZ queue = SetGet.getKDZFromResponse(data);

        // inisialisasi object result ada di dalam blok if berikut
        if (queue.getRequest_type().equals(SurveyKDZActivity.REQUEST_TYPE_INSERT)) {
            result = apiService.kdz(StaticStrings.API_KEY,
                            queue.getFk_id(),
                            queue.getUID(),
                            queue.getM1_created_at(),
                            queue.getM1_updated_at(),
                            queue.getM1_nama(),
                            queue.getM1_101(),
                            queue.getM1_102(),
                            queue.getM1_103(),
                            queue.getM1_104(),
                            queue.getM1_105(),
                            queue.getM1_106(),
                            queue.getM1_107(),
                            queue.getM1_108(),
                            queue.getM1_109(),
                            queue.getM1_110(),
                            queue.getM1_401(),
                            queue.getM1_402(),
                            queue.getM1_403(),
                            queue.getM1_404(),
                            queue.getM1_405(),
                            queue.getM1_406(),
                            queue.getM1_407(),
                            queue.getM1_501(),
                            queue.getM1_502(),
                            queue.getM1_503(),
                            queue.getM1_504(),
                            queue.getM1_505(),
                            queue.getM1_506(),
                            queue.getM1_507(),
                            queue.getM1_508(),
                            queue.getM1_509(),
                            queue.getM1_510(),
                            queue.getM1_601(),
                            queue.getM1_601_kode(),
                            queue.getM1_602(),
                            queue.getM1_602_kode(),
                            queue.getM1_603(),
                            queue.getM1_603_kode(),
                            queue.getM1_604(),
                            queue.getM1_605(),
                            queue.getM1_606(),
                            queue.getM1_607(),
                            queue.getM1_608(),
                            queue.getM1_609(),
                            queue.getM1_610(),
                            queue.getM1_611(),
                            queue.getM1_612(),
                            queue.getM1_613(),
                            queue.getM1_614(),
                            queue.getM1_615(),
                            queue.getM1_616(),
                            queue.getM1_617(),
                            queue.getM1_618(),
                            queue.getM1_701(),
                            queue.getM1_702(),
                            queue.getM1_703(),
                            queue.getM1_801(),
                            queue.getM1_802(),
                            queue.getM1_803(),
                            queue.getM1_804(),
                            queue.getM1_805(),
                            queue.getM1_806(),
                            queue.getM1_807(),
                            queue.getM1_808(),
                            queue.getM1_809(),
                            queue.getM1_810(),
                            queue.getM1_811(),
                            queue.getM1_812(),
                            queue.getM1_813(),
                            queue.getM1_814(),
                            queue.getM1_815(),
                            queue.getM1_lik1(),
                            queue.getM1_lik1(),
                            queue.getM1_lik2(),
                            queue.getM1_lik2(),
                            queue.getM1_lik3(),
                            queue.getM1_lik3(),
                            queue.getM1_lik4(),
                            queue.getM1_lik4(),
                            queue.getM1_lik5(),
                            queue.getM1_lik5(),
                            queue.getM1_lik1B(),
                            queue.getM1_lik1B(),
                            queue.getM1_lik2B(),
                            queue.getM1_lik2B(),
                            queue.getM1_lik3B(),
                            queue.getM1_lik3B(),
                            queue.getM1_lik4B(),
                            queue.getM1_lik4B(),
                            queue.getM1_lik5B(),
                            queue.getM1_lik5B()
                    );
        }
        else if (queue.getRequest_type().equals(SurveyKDZActivity.REQUEST_TYPE_UPDATE)){
                    result = apiService.kdzUpdate(StaticStrings.API_KEY,
                            queue.getFk_id(),
                            queue.getUID(),
                            queue.getM1_created_at(),
                            queue.getM1_updated_at(),
                            queue.getM1_nama(),
                            queue.getM1_101(),
                            queue.getM1_102(),
                            queue.getM1_103(),
                            queue.getM1_104(),
                            queue.getM1_105(),
                            queue.getM1_106(),
                            queue.getM1_107(),
                            queue.getM1_108(),
                            queue.getM1_109(),
                            queue.getM1_110(),
                            queue.getM1_401(),
                            queue.getM1_402(),
                            queue.getM1_403(),
                            queue.getM1_404(),
                            queue.getM1_405(),
                            queue.getM1_406(),
                            queue.getM1_407(),
                            queue.getM1_501(),
                            queue.getM1_502(),
                            queue.getM1_503(),
                            queue.getM1_504(),
                            queue.getM1_505(),
                            queue.getM1_506(),
                            queue.getM1_507(),
                            queue.getM1_508(),
                            queue.getM1_509(),
                            queue.getM1_510(),
                            queue.getM1_601(),
                            queue.getM1_601_kode(),
                            queue.getM1_602(),
                            queue.getM1_602_kode(),
                            queue.getM1_603(),
                            queue.getM1_603_kode(),
                            queue.getM1_604(),
                            queue.getM1_605(),
                            queue.getM1_606(),
                            queue.getM1_607(),
                            queue.getM1_608(),
                            queue.getM1_609(),
                            queue.getM1_610(),
                            queue.getM1_611(),
                            queue.getM1_612(),
                            queue.getM1_613(),
                            queue.getM1_614(),
                            queue.getM1_615(),
                            queue.getM1_616(),
                            queue.getM1_617(),
                            queue.getM1_618(),
                            queue.getM1_701(),
                            queue.getM1_702(),
                            queue.getM1_703(),
                            queue.getM1_801(),
                            queue.getM1_802(),
                            queue.getM1_803(),
                            queue.getM1_804(),
                            queue.getM1_805(),
                            queue.getM1_806(),
                            queue.getM1_807(),
                            queue.getM1_808(),
                            queue.getM1_809(),
                            queue.getM1_810(),
                            queue.getM1_811(),
                            queue.getM1_812(),
                            queue.getM1_813(),
                            queue.getM1_814(),
                            queue.getM1_815(),
                            queue.getM1_lik1(),
                            queue.getM1_lik1(),
                            queue.getM1_lik2(),
                            queue.getM1_lik2(),
                            queue.getM1_lik3(),
                            queue.getM1_lik3(),
                            queue.getM1_lik4(),
                            queue.getM1_lik4(),
                            queue.getM1_lik5(),
                            queue.getM1_lik5(),
                            queue.getM1_lik1B(),
                            queue.getM1_lik1B(),
                            queue.getM1_lik2B(),
                            queue.getM1_lik2B(),
                            queue.getM1_lik3B(),
                            queue.getM1_lik3B(),
                            queue.getM1_lik4B(),
                            queue.getM1_lik4B(),
                            queue.getM1_lik5B(),
                            queue.getM1_lik5B()
                    );
        }

        result.enqueue(new Callback<KajianDampakZakat>() {
            @Override
            public void onResponse(@NonNull Call<KajianDampakZakat> call, @NonNull Response<KajianDampakZakat> response) {
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {

                        String familyID;

                        if (queue.getRequest_type().equals(SurveyKDZActivity.REQUEST_TYPE_INSERT)) {
                            Prefs.putString("parent_id", response.body().getFk_id().toString());

                            Utils.log("Send Modul 1 Berhasil, PARENT/FAMILY ID : " + response.body().getFk_id().toString());
                            familyID = response.body().getFk_id().toString();

                            // Karena id nya itu random (date) maka harus diupdate jadi id yang beneran
                            data.setFk_id(familyID);
                        } else {
                            familyID = queue.getFk_id();
                        }

                        List<Keluarga> keluargaList = KeluargaManager.loadAllwithFKID(context, queue.getFk_id());

                        for (Keluarga keluarga : keluargaList){
                            keluarga.setFki_fk_id(familyID);
                        }

                        KeluargaManager.insertOrReplaceArray(context, keluargaList).observe(FragmentDataModul1.getInstance(), new Observer<Boolean>() {
                            @Override
                            public void onChanged(@Nullable Boolean aBoolean) {
                                if (aBoolean){
                                    Utils.sendModulKeluarga(context, familyID, queue.getRequest_type());

                                    queue.setStatus(StaticStrings.KDZ_STATUS_SENT);
                                    KDZManager.remove(context, queue);

                                    queue.setFk_id(familyID);
                                    KDZManager.insertOrReplace(context, queue, null);

                                    data.setStatus(StaticStrings.KDZ_STATUS_SENT);
                                    data.getLiveStatus().setValue(StaticStrings.KDZ_STATUS_SENT);

                                    Toast.makeText(context, "Berhasil dikirim ke server", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    } else {
                        // Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        Utils.log("RESPONSE :  " + response.body().getMessage());
                        Toast.makeText(context, "Data gagal dikirim, silakan periksa data Anda", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Utils.log("EXCEPTION SAVE NEXT : " + e.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<KajianDampakZakat> call, @NonNull Throwable t) {
                t.printStackTrace();
                Utils.log("ON FAILURE : " + t.toString());
            }
        });

    }


    private static void saveAndNextKeluarga(final Context context,
                                            String type,
                                            Keluarga queue) {

        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<KajianDampakZakatKeluarga> result = null;

        if (type.equals(SurveyKDZActivity.REQUEST_TYPE_INSERT)) {
            result =
                    apiService.kdzKeluarga(StaticStrings.API_KEY,
                            queue.getPosisi(),
                            queue.getFki_id(),
                            queue.getFki_fk_id(),
                            queue.getFk_202_nama(),
                            queue.getFk_202_nik(),
                            queue.getFk_203(),
                            queue.getFk_204(),
                            queue.getFk_205(),
                            queue.getFk_206(),
                            queue.getFk_207(),
                            queue.getFk_208(),
                            queue.getFk_209(),
                            queue.getFk_210(),
                            queue.getFk_303(),
                            queue.getFk_304(),
                            queue.getFk_305(),
                            queue.getFk_306(),
                            queue.getFk_307(),
                            queue.getFk_308()
                    );
        } else if (type.equals(SurveyKDZActivity.REQUEST_TYPE_UPDATE)) {
            result =
                    apiService.kdzKeluargaUpdate(StaticStrings.API_KEY,
                            queue.getFki_id(),
                            queue.getFki_fk_id(),
                            queue.getFk_202_nama(),
                            queue.getFk_202_nik(),
                            queue.getFk_203(),
                            queue.getFk_204(),
                            queue.getFk_205(),
                            queue.getFk_206(),
                            queue.getFk_207(),
                            queue.getFk_208(),
                            queue.getFk_209(),
                            queue.getFk_210(),
                            queue.getFk_303(),
                            queue.getFk_304(),
                            queue.getFk_305(),
                            queue.getFk_306(),
                            queue.getFk_307(),
                            queue.getFk_308()
                    );
        }

        Utils.log("ID FK DARI KELUARGA : " + queue.getFki_fk_id());

        result.enqueue(new Callback<KajianDampakZakatKeluarga>() {
            @Override
            public void onResponse(@NonNull Call<KajianDampakZakatKeluarga> call, @NonNull Response<KajianDampakZakatKeluarga> response) {
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        Utils.log("SUCCESS KELUARGA, message : " + response.body().getMessage());
                        Utils.log("SUCCESS WITH FKI ID : " + response.body().getFki_id());

                        String fki_id = response.body().getFki_id();

                        queue.setStatus(StaticStrings.KDZ_STATUS_SENT);

                        if (fki_id != null) {
                            KeluargaManager.remove(context, queue);
                            queue.setFki_id(response.body().getFki_id());
                        }

                        KeluargaManager.insertOrReplace(context, queue);

                    } else {
                        Utils.log("FAILED KELUARGA, message : " + response.body().getMessage());
                    }
                } catch (Exception e) {
                    Utils.log("ON EXCEPTION KELUARGA " + e.toString());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<KajianDampakZakatKeluarga> call, @NonNull Throwable t) {
                Utils.log("ON FAILURE KELUARGA " + t.toString());
                t.printStackTrace();
            }
        });
    }
}
