package com.karyastudio.izn.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.karyastudio.izn.dao.generateSchema.Keluarga;
import com.karyastudio.izn.dao.managerSchema.KeluargaManager;
import com.karyastudio.izn.model.api.izn.IndeksZakatNasional;
import com.karyastudio.izn.model.api.kdz.KajianDampakZakat;
import com.karyastudio.izn.model.api.kdz.KajianDampakZakatKeluarga;
import com.karyastudio.izn.network.BaseApi;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {


    public static boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
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


    public static <T> List<T> removeDuplicates(List<T> list)
    {

        // Create a new ArrayList
        List<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }
    public static void sendModulKeluarga(Context context) {

        List<Keluarga> list = KeluargaManager.loadAll(context);
        List<Keluarga> newList = removeDuplicates(list);
        for (int i = 0; i < newList.size(); i++){
            saveAndNextKeluarga(context,
                    "NULL",
                    Prefs.getString("parent_id","kososng"),
                    newList.get(i).getFk_202_nama(),
                    newList.get(i).getFk_202_nik(),
                    newList.get(i).getFk_203(),
                    newList.get(i).getFk_204(),
                    newList.get(i).getFk_205(),
                    newList.get(i).getFk_206(),
                    newList.get(i).getFk_207(),
                    newList.get(i).getFk_208(),
                    newList.get(i).getFk_209(),
                    newList.get(i).getFk_210(),
                    newList.get(i).getFk_303(),
                    newList.get(i).getFk_304(),
                    newList.get(i).getFk_305(),
                    newList.get(i).getFk_306(),
                    newList.get(i).getFk_307(),
                    newList.get(i).getFk_308()

            );
        }
        KeluargaManager.removeAll(context);

    }

    public static void sendModul1(Context context) {
        saveAndNextKajian(context,
                "NULL",
                Prefs.getString("UID", "kosong"),
                Prefs.getString(StaticStrings.M1_created_at, "kosong"),
                Prefs.getString(StaticStrings.M1_update_at, "kosong"),
                Prefs.getString(StaticStrings.M1_nama, "kosong"),
                Prefs.getString(StaticStrings.M1_101, "kosong"),
                Prefs.getString(StaticStrings.M1_102, "kosong"),
                Prefs.getString(StaticStrings.M1_103, "kosong"),
                Prefs.getString(StaticStrings.M1_104, "kosong"),
                Prefs.getString(StaticStrings.M1_105, "kosong"),
                Prefs.getString(StaticStrings.M1_106, "kosong"),
                Prefs.getString(StaticStrings.M1_107, "kosong"),
                Prefs.getString(StaticStrings.M1_108, "kosong"),
                Prefs.getString(StaticStrings.M1_109, "kosong"),
                Prefs.getString(StaticStrings.M1_110, "kosong"),
                Prefs.getString(StaticStrings.M1_401, "kosong"),
                Prefs.getString(StaticStrings.M1_402, "kosong"),
                Prefs.getString(StaticStrings.M1_403, "kosong"),
                Prefs.getString(StaticStrings.M1_404, "kosong"),
                Prefs.getString(StaticStrings.M1_405, "kosong"),
                Prefs.getString(StaticStrings.M1_406, "kosong"),
                Prefs.getString(StaticStrings.M1_407, "kosong"),
                Prefs.getString(StaticStrings.M1_501, "kosong"),
                Prefs.getString(StaticStrings.M1_502, "kosong"),
                Prefs.getString(StaticStrings.M1_503, "kosong"),
                Prefs.getString(StaticStrings.M1_504, "kosong"),
                Prefs.getString(StaticStrings.M1_505, "kosong"),
                Prefs.getString(StaticStrings.M1_506, "kosong"),
                Prefs.getString(StaticStrings.M1_507, "kosong"),
                Prefs.getString(StaticStrings.M1_508, "kosong"),
                Prefs.getString(StaticStrings.M1_509, "kosong"),
                Prefs.getString(StaticStrings.M1_510, "kosong"),
                Prefs.getString(StaticStrings.M1_601, "kosong"),
                "",
                Prefs.getString(StaticStrings.M1_602, "kosong"),
                "",
                Prefs.getString(StaticStrings.M1_603, "kosong"),
                "",
                Prefs.getString(StaticStrings.M1_604, "kosong"),
                Prefs.getString(StaticStrings.M1_605, "kosong"),
                Prefs.getString(StaticStrings.M1_606, "kosong"),
                Prefs.getString(StaticStrings.M1_607, "kosong"),
                Prefs.getString(StaticStrings.M1_608, "kosong"),
                Prefs.getString(StaticStrings.M1_609, "kosong"),
                Prefs.getString(StaticStrings.M1_610, "kosong"),
                Prefs.getString(StaticStrings.M1_611, "kosong"),
                Prefs.getString(StaticStrings.M1_612, "kosong"),
                Prefs.getString(StaticStrings.M1_613, "kosong"),
                Prefs.getString(StaticStrings.M1_614, "kosong"),
                Prefs.getString(StaticStrings.M1_615, "kosong"),
                Prefs.getString(StaticStrings.M1_616, "kosong"),
                Prefs.getString(StaticStrings.M1_617, "kosong"),
                Prefs.getString(StaticStrings.M1_618, "kosong"),
                Prefs.getString(StaticStrings.M1_701, "kosong"),
                Prefs.getString(StaticStrings.M1_702, "kosong"),
                Prefs.getString(StaticStrings.M1_703, "kosong"),
                Prefs.getString(StaticStrings.M1_801, "kosong"),
                Prefs.getString(StaticStrings.M1_802, "kosong"),
                Prefs.getString(StaticStrings.M1_803, "kosong"),
                Prefs.getString(StaticStrings.M1_804, "kosong"),
                Prefs.getString(StaticStrings.M1_805, "kosong"),
                Prefs.getString(StaticStrings.M1_806, "kosong"),
                Prefs.getString(StaticStrings.M1_807, "kosong"),
                Prefs.getString(StaticStrings.M1_808, "kosong"),
                Prefs.getString(StaticStrings.M1_809, "kosong"),
                Prefs.getString(StaticStrings.M1_810, "kosong"),
                Prefs.getString(StaticStrings.M1_811, "kosong"),
                Prefs.getString(StaticStrings.M1_812, "kosong"),
                Prefs.getString(StaticStrings.M1_813, "kosong"),
                Prefs.getString(StaticStrings.M1_814, "kosong"),
                Prefs.getString(StaticStrings.M1_815, "kosong"),
                Prefs.getString(StaticStrings.M1_lik1, "kosong"),
                Prefs.getString(StaticStrings.M1_lik1, "kosong"),
                Prefs.getString(StaticStrings.M1_lik2, "kosong"),
                Prefs.getString(StaticStrings.M1_lik2, "kosong"),
                Prefs.getString(StaticStrings.M1_lik3, "kosong"),
                Prefs.getString(StaticStrings.M1_lik3, "kosong"),
                Prefs.getString(StaticStrings.M1_lik4, "kosong"),
                Prefs.getString(StaticStrings.M1_lik4, "kosong"),
                Prefs.getString(StaticStrings.M1_lik5, "kosong"),
                Prefs.getString(StaticStrings.M1_lik5, "kosong"),
                Prefs.getString(StaticStrings.M1_lik1B, "kosong"),
                Prefs.getString(StaticStrings.M1_lik1B, "kosong"),
                Prefs.getString(StaticStrings.M1_lik2B, "kosong"),
                Prefs.getString(StaticStrings.M1_lik2B, "kosong"),
                Prefs.getString(StaticStrings.M1_lik3B, "kosong"),
                Prefs.getString(StaticStrings.M1_lik3B, "kosong"),
                Prefs.getString(StaticStrings.M1_lik4B, "kosong"),
                Prefs.getString(StaticStrings.M1_lik4B, "kosong"),
                Prefs.getString(StaticStrings.M1_lik5B, "kosong"),
                Prefs.getString(StaticStrings.M1_lik5B, "kosong")
        );
    }

    public static void sendModul2(Context context) {
        saveAndNext(context,
                "NULL",
                Prefs.getString("UID", "kosong"),
                Prefs.getString(StaticStrings.M2_created_at, "kosong"),
                Prefs.getString(StaticStrings.M2_update_at, "kosong"),
                Prefs.getString(StaticStrings.M2_101, "kosong"),
                Prefs.getString(StaticStrings.M2_102, "kosong"),
                Prefs.getString(StaticStrings.M2_103, "kosong"),
                Prefs.getString(StaticStrings.M2_104, "kosong"),
                Prefs.getString(StaticStrings.M2_201yt, "kosong"),
                Prefs.getString(StaticStrings.M2_201, "kosong"),
                Prefs.getString(StaticStrings.M2_301yt, "kosong"),
                Prefs.getString(StaticStrings.M2_301, "kosong"),
                Prefs.getString(StaticStrings.M2_302yt, "kosong"),
                Prefs.getString(StaticStrings.M2_302, "kosong"),
                Prefs.getString(StaticStrings.M2_401yt, "kosong"),
                Prefs.getString(StaticStrings.M2_401, "kosong"),
                Prefs.getString(StaticStrings.M2_402, "kosong"),
                Prefs.getString(StaticStrings.M2_403yt1, "kosong"),
                Prefs.getString(StaticStrings.M2_403yt2, "kosong"),
                Prefs.getString(StaticStrings.M2_404, "kosong"),
                Prefs.getString(StaticStrings.M2_405, "kosong"),
                Prefs.getString(StaticStrings.M2_406, "kosong"),

                Prefs.getString(StaticStrings.M2_501, "kosong"),
                Prefs.getString(StaticStrings.M2_502, "kosong"),
                Prefs.getString(StaticStrings.M2_601yt, "kosong"),
                Prefs.getString(StaticStrings.M2_602yt, "kosong"),
                Prefs.getString(StaticStrings.M2_603yt, "kosong"),
                Prefs.getString(StaticStrings.M2_603, "kosong"),
                Prefs.getString(StaticStrings.M2_604yt, "kosong"),
                Prefs.getString(StaticStrings.M2_604, "kosong"),
                Prefs.getString(StaticStrings.M2_701, "kosong"),
                Prefs.getString(StaticStrings.M2_702yt, "kosong"),
                Prefs.getString(StaticStrings.M2_702, "kosong"),
                Prefs.getString(StaticStrings.M2_7031, "kosong"),
                Prefs.getString(StaticStrings.M2_7032, "kosong"),
                Prefs.getString(StaticStrings.M2_7041, "kosong"),
                Prefs.getString(StaticStrings.M2_7042, "kosong"),
                Prefs.getString(StaticStrings.M2_801yt, "kosong"),
                Prefs.getString(StaticStrings.M2_802yt1, "kosong"),
                Prefs.getString(StaticStrings.M2_802yt2, "kosong"),
                Prefs.getString(StaticStrings.M2_803yt, "kosong"),
                Prefs.getString(StaticStrings.M2_804yt, "kosong"),
                Prefs.getString(StaticStrings.M2_901, "kosong")
        );
    }


    private static void saveAndNext(final Context context,
                                    String fi_id,
                                    String fi_u_id,
                                    String fi_date_created,
                                    String fi_date_updated,
                                    String fi_101_jenis_lembaga,
                                    String fi_102_nama_laz,
                                    String fi_103_provinsi,
                                    String fi_104_kabupaten,
                                    String fi_201_regulasi_ada,
                                    String fi_201_regulasi,
                                    String fi_301_alokasi_apbn_2_tahun_lalu_ada,
                                    String fi_301_alokasi_apbn_2_tahun_lalu,
                                    String fi_302_alokasi_apbn_1_tahun_lalu_ada,
                                    String fi_302_alokasi_apbn_1_tahun_lalu,
                                    String fi_401_lembaga_zakat_resmi_ada,
                                    String fi_401_lembaga_zakat_resmi,
                                    String fi_402_jumlah_mustahik,
                                    String fi_403_mustahik_kabupaten,
                                    String fi_403_mustahik_kecamatan,
                                    String fi_404_jumlah_muzakki,
                                    String fi_405_jumlah_munsafik,
                                    String fi_406_jumlah_muzakki_badan_usaha,
                                    String fi_501_total_himpunan_tahun_2,
                                    String fi_502_total_himpunan_tahun_1,
                                    String fi_601_program_kerja,
                                    String fi_602_rencana_strategis,
                                    String fi_603_sop_ada,
                                    String fi_603_sop,
                                    String fi_604_iso_ada,
                                    String fi_604_iso,
                                    String fi_701_total_dana_zis,
                                    String fi_702_dana_zis_dakwah_ada,
                                    String fi_702_dana_zis_dakwah,
                                    String fi_703_penyaluran_zis_produktif_rencana,
                                    String fi_703_penyaluran_zis_produktif_realisasi,
                                    String fi_704_penyaluran_zis_sosial_rencana,
                                    String fi_704_penyaluran_zis_sosial_realisasi,
                                    String fi_801_laporan_keuangan,
                                    String fi_802_laporan_keuangan_teraudit,
                                    String fi_802_laporan_keuangan_wtp,
                                    String fi_803_laporan_keuangan_publikasi,
                                    String fi_804_laporan_audit_syariah,
                                    String fi_805_biaya_operasional) {


        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<IndeksZakatNasional> result =
                apiService.izn(StaticStrings.API_KEY,
                        fi_id,
                        fi_u_id,
                        fi_date_created,
                        fi_date_updated,
                        fi_101_jenis_lembaga,
                        fi_102_nama_laz,
                        fi_103_provinsi,
                        fi_104_kabupaten,
                        fi_201_regulasi_ada,
                        fi_201_regulasi,
                        fi_301_alokasi_apbn_2_tahun_lalu_ada,
                        fi_301_alokasi_apbn_2_tahun_lalu,
                        fi_302_alokasi_apbn_1_tahun_lalu_ada,
                        fi_302_alokasi_apbn_1_tahun_lalu,
                        fi_401_lembaga_zakat_resmi_ada,
                        fi_401_lembaga_zakat_resmi,
                        fi_402_jumlah_mustahik,
                        fi_403_mustahik_kabupaten,
                        fi_403_mustahik_kecamatan,
                        fi_404_jumlah_muzakki,
                        fi_405_jumlah_munsafik,
                        fi_406_jumlah_muzakki_badan_usaha,
                        fi_501_total_himpunan_tahun_2,
                        fi_502_total_himpunan_tahun_1,
                        fi_601_program_kerja,
                        fi_602_rencana_strategis,
                        fi_603_sop_ada,
                        fi_603_sop,
                        fi_604_iso_ada,
                        fi_604_iso,
                        fi_701_total_dana_zis,
                        fi_702_dana_zis_dakwah_ada,
                        fi_702_dana_zis_dakwah,
                        fi_703_penyaluran_zis_produktif_rencana,
                        fi_703_penyaluran_zis_produktif_realisasi,
                        fi_704_penyaluran_zis_sosial_rencana,
                        fi_704_penyaluran_zis_sosial_realisasi,
                        fi_801_laporan_keuangan,
                        fi_802_laporan_keuangan_teraudit,
                        fi_802_laporan_keuangan_wtp,
                        fi_803_laporan_keuangan_publikasi,
                        fi_804_laporan_audit_syariah,
                        fi_805_biaya_operasional
                );
        result.enqueue(new Callback<IndeksZakatNasional>() {
            @Override
            public void onResponse(@NonNull Call<IndeksZakatNasional> call, @NonNull Response<IndeksZakatNasional> response) {
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        Toast.makeText(context, "Berhasil Dimasukan", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Data anda telah tersimpan offline, data menunggu online", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<IndeksZakatNasional> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private static void saveAndNextKajian(final Context context,
                                          String fk_id,
                                          String fk_u_id,
                                          String fk_date_created,
                                          String fk_date_updated,
                                          String fk_nama,
                                          String fk_101_provinsi,
                                          String fk_102_kabupaten,
                                          String fk_103_kecamatan,
                                          String fk_104_desa,
                                          String fk_105_klasisfikasi_desan,
                                          String fk_106_kode_rumah,
                                          String fk_107_nama_kepala_rumah,
                                          String fk_108_jumlah_anggota_rumah,
                                          String fk_109_nomor_hp,
                                          String fk_110_alamat_lengkap,
                                          String fk_401_tabungan_bank_konvensional,
                                          String fk_402_tabungan_bank_syariah,
                                          String fk_403_tabungan_koeprasi_konvensional,
                                          String fk_404_tabungan_koeprasi_syariah,
                                          String fk_405_tabungan_lembaga_zakat,
                                          String fk_406_arisan,
                                          String fk_407_simpanan_di_rumah,
                                          String fk_501_memiliki_atap,
                                          String fk_502_memiliki_dinding,
                                          String fk_503_memiliki_listrik,
                                          String fk_504_memiliki_lantai,
                                          String fk_505_memiliki_air,
                                          String fk_506_memiliki_sanitasi,
                                          String fk_507_memiliki_penyakit,
                                          String fk_508_tidak_memiliki_cacat,
                                          String fk_509_memiliki_bpjs,
                                          String fk_510_tidak_memiliki_rokok,
                                          String fk_601_penerima_zakat,
                                          String fk_601_kode,
                                          String fk_602_jenis_lembaga,
                                          String fk_602_kode,
                                          String fk_603_jenis_lembaga,
                                          String fk_603_kode,
                                          String fk_604_tanggal_menerima,
                                          String fk_605_pendapatan,
                                          String fk_606_berapa_kali,
                                          String fk_607_jenis_zakat,
                                          String fk_608_pangan,
                                          String fk_609_kesehatan,
                                          String fk_610_pendidikan,
                                          String fk_611_lainnya,
                                          String fk_612_total_bantuan,
                                          String fk_613_bantuan_modal,
                                          String fk_614_bantuan_alat,
                                          String fk_615_bantuan_lain,
                                          String fk_616_total_bantuan,
                                          String fk_617,
                                          String fk_618,
                                          String fk_701,
                                          String fk_702,
                                          String fk_703,
                                          String fk_801,
                                          String fk_802,
                                          String fk_803,
                                          String fk_804,
                                          String fk_805,
                                          String fk_806,
                                          String fk_807,
                                          String fk_808,
                                          String fk_809,
                                          String fk_810,
                                          String fk_811,
                                          String fk_812,
                                          String fk_813,
                                          String fk_814,
                                          String fk_815,
                                          String fk_shalat,
                                          String fk_shalat_keterangan,
                                          String fk_puasa,
                                          String fk_puasa_keterangan,
                                          String fk_zakat,
                                          String fk_zakat_keterangan,
                                          String fk_lingkungan,
                                          String fk_lingkungan_keterangan,
                                          String fk_kebijakan,
                                          String fk_kebijakan_keterangan,
                                          String fk_shalat2,
                                          String fk_shalat2_keterangan,
                                          String fk_puasa2,
                                          String fk_puasa2_keterangan,
                                          String fk_zakat2,
                                          String fk_zakat2_keterangan,
                                          String fk_lingkungan2,
                                          String fk_lingkungan2_keterangan,
                                          String fk_kebijakan2,
                                          String fk_kebijakan2_keterangan) {
        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<KajianDampakZakat> result =
                apiService.kdz(StaticStrings.API_KEY,
                        fk_id,
                        fk_u_id,
                        fk_date_created,
                        fk_date_updated,
                        fk_nama,
                        fk_101_provinsi,
                        fk_102_kabupaten,
                        fk_103_kecamatan,
                        fk_104_desa,
                        fk_105_klasisfikasi_desan,
                        fk_106_kode_rumah,
                        fk_107_nama_kepala_rumah,
                        fk_108_jumlah_anggota_rumah,
                        fk_109_nomor_hp,
                        fk_110_alamat_lengkap,
                        fk_401_tabungan_bank_konvensional,
                        fk_402_tabungan_bank_syariah,
                        fk_403_tabungan_koeprasi_konvensional,
                        fk_404_tabungan_koeprasi_syariah,
                        fk_405_tabungan_lembaga_zakat,
                        fk_406_arisan,
                        fk_407_simpanan_di_rumah,
                        fk_501_memiliki_atap,
                        fk_502_memiliki_dinding,
                        fk_503_memiliki_listrik,
                        fk_504_memiliki_lantai,
                        fk_505_memiliki_air,
                        fk_506_memiliki_sanitasi,
                        fk_507_memiliki_penyakit,
                        fk_508_tidak_memiliki_cacat,
                        fk_509_memiliki_bpjs,
                        fk_510_tidak_memiliki_rokok,
                        fk_601_penerima_zakat,
                        fk_601_kode,
                        fk_602_jenis_lembaga,
                        fk_602_kode,
                        fk_603_jenis_lembaga,
                        fk_603_kode,
                        fk_604_tanggal_menerima,
                        fk_605_pendapatan,
                        fk_606_berapa_kali,
                        fk_607_jenis_zakat,
                        fk_608_pangan,
                        fk_609_kesehatan,
                        fk_610_pendidikan,
                        fk_611_lainnya,
                        fk_612_total_bantuan,
                        fk_613_bantuan_modal,
                        fk_614_bantuan_alat,
                        fk_615_bantuan_lain,
                        fk_616_total_bantuan,
                        fk_617,
                        fk_618,
                        fk_701,
                        fk_702,
                        fk_703,
                        fk_801,
                        fk_802,
                        fk_803,
                        fk_804,
                        fk_805,
                        fk_806,
                        fk_807,
                        fk_808,
                        fk_809,
                        fk_810,
                        fk_811,
                        fk_812,
                        fk_813,
                        fk_814,
                        fk_815,
                        fk_shalat,
                        fk_shalat_keterangan,
                        fk_puasa,
                        fk_puasa_keterangan,
                        fk_zakat,
                        fk_zakat_keterangan,
                        fk_lingkungan,
                        fk_lingkungan_keterangan,
                        fk_kebijakan,
                        fk_kebijakan_keterangan,
                        fk_shalat2,
                        fk_shalat2_keterangan,
                        fk_puasa2,
                        fk_puasa2_keterangan,
                        fk_zakat2,
                        fk_zakat2_keterangan,
                        fk_lingkungan2,
                        fk_lingkungan2_keterangan,
                        fk_kebijakan2,
                        fk_kebijakan2_keterangan
                );
        result.enqueue(new Callback<KajianDampakZakat>() {
            @Override
            public void onResponse(@NonNull Call<KajianDampakZakat> call, @NonNull Response<KajianDampakZakat> response) {
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        Prefs.putString("parent_id", response.body().getIdParent().toString());
                        Toast.makeText(context, "Berhasil Dimasukan", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Data anda telah tersimpan offline, data menunggu online", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<KajianDampakZakat> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

    }


    private static void saveAndNextKeluarga(final Context context,
                                            String fki_id,
                                            String fki_fk_id,
                                            String fk_202_nama,
                                            String fk_202_nik,
                                            String fk_203,
                                            String fk_204,
                                            String fk_205,
                                            String fk_206,
                                            String fk_207,
                                            String fk_208,
                                            String fk_209,
                                            String fk_210,
                                            String fk_303,
                                            String fk_304,
                                            String fk_305,
                                            String fk_306,
                                            String fk_307,
                                            String fk_308) {


        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);
        Call<KajianDampakZakatKeluarga> result =
                apiService.kdzKeluarga(StaticStrings.API_KEY,
                         fki_id,
                         fki_fk_id,
                         fk_202_nama,
                         fk_202_nik,
                         fk_203,
                         fk_204,
                         fk_205,
                         fk_206,
                         fk_207,
                         fk_208,
                         fk_209,
                         fk_210,
                         fk_303,
                         fk_304,
                         fk_305,
                         fk_306,
                         fk_307,
                         fk_308
                );
        result.enqueue(new Callback<KajianDampakZakatKeluarga>() {
            @Override
            public void onResponse(@NonNull Call<KajianDampakZakatKeluarga> call, @NonNull Response<KajianDampakZakatKeluarga> response) {
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
//                        Toast.makeText(context, "Berhasil Dimasukan", Toast.LENGTH_LONG).show();
                    } else {
//                        Toast.makeText(context, "Data anda telah tersimpan", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<KajianDampakZakatKeluarga> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
