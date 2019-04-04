package com.karyastudio.izn.network;

import com.karyastudio.izn.model.api.city.DataCity;
import com.karyastudio.izn.model.api.daftar.Daftar;
import com.karyastudio.izn.model.api.dataizn.DataIZN;
import com.karyastudio.izn.model.api.datakdz.DataKDZ;
import com.karyastudio.izn.model.api.izn.IndeksZakatNasional;
import com.karyastudio.izn.model.api.kdz.KajianDampakZakat;
import com.karyastudio.izn.model.api.kdz.KajianDampakZakatKeluarga;
import com.karyastudio.izn.model.api.login.Login;
import com.karyastudio.izn.model.api.province.DataProvinsi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BaseApi {
    String BaseUrl = "https://baznas.playon-id.com/webservice/v1/";

    //login
    @FormUrlEncoded
    @POST("authPetugasProcess")
    Call<Login> login(@Header("key") String apikey, @Field("username") String username, @Field("password") String password);

    //data getProvince
    @GET("getProvince")
    Call<DataProvinsi> getProvince(@Header("key") String apikey);

    //data getCity
    @FormUrlEncoded
    @POST("getCity")
    Call<DataCity> getCity(@Header("key") String apikey, @Field("pro_code") String pro_code);


    //data izn
    @FormUrlEncoded
    @POST("data/indeksZakatNasional")
    Call<DataIZN> iznData(@Header("key") String apikey,  @Field("idUser") String idUser);


    //data kdz
    @FormUrlEncoded
    @POST("data/kajianDampakZakat")
    Call<DataKDZ> kdzData(@Header("key") String apikey,  @Field("idUser") String idUser);

    //daftar
    @FormUrlEncoded
    @POST("registerPetugasProcess")
    Call<Daftar> daftar(@Header("key") String apikey,
                        @Field("username") String username,
                        @Field("email") String email,
                        @Field("password") String password,
                        @Field("address") String address,
                        @Field("phone") String phone,
                        @Field("name") String name);

    //izn
    @FormUrlEncoded
    @POST("input/indeksZakatNasional")
    Call<IndeksZakatNasional> izn(@Header("key") String apikey,
                                  @Field("fi_id") String fi_id,
                                  @Field("fi_u_id") String fi_u_id,
                                  @Field("fi_date_created") String fi_date_created,
                                  @Field("fi_date_updated") String fi_date_updated,
                                  @Field("fi_101_jenis_lembaga") String fi_101_jenis_lembaga,
                                  @Field("fi_102_nama_laz") String fi_102_nama_laz,

                                  @Field("fi_103_provinsi") String fi_103_provinsi,
                                  @Field("fi_104_kabupaten") String fi_104_kabupaten,
                                  @Field("fi_201_regulasi_ada") String fi_201_regulasi_ada,
                                  @Field("fi_201_regulasi") String fi_201_regulasi,
                                  @Field("fi_301_alokasi_apbn_2_tahun_lalu_ada") String fi_301_alokasi_apbn_2_tahun_lalu_ada,
                                  @Field("fi_301_alokasi_apbn_2_tahun_lalu") String fi_301_alokasi_apbn_2_tahun_lalu,

                                  @Field("fi_302_alokasi_apbn_1_tahun_lalu_ada") String fi_302_alokasi_apbn_1_tahun_lalu_ada,
                                  @Field("fi_302_alokasi_apbn_1_tahun_lalu") String fi_302_alokasi_apbn_1_tahun_lalu,
                                  @Field("fi_401_lembaga_zakat_resmi_ada") String fi_401_lembaga_zakat_resmi_ada,

                                  @Field("fi_401_lembaga_zakat_resmi") String fi_401_lembaga_zakat_resmi,
                                  @Field("fi_402_jumlah_mustahik") String fi_402_jumlah_mustahik,
                                  @Field("fi_403_mustahik_kabupaten") String fi_403_mustahik_kabupaten,
                                  @Field("fi_403_mustahik_kecamatan") String fi_403_mustahik_kecamatan,

                                  @Field("fi_404_jumlah_muzakki") String fi_404_jumlah_muzakki,
                                  @Field("fi_405_jumlah_munsafik") String fi_405_jumlah_munsafik,
                                  @Field("fi_406_jumlah_muzakki_badan_usaha") String fi_406_jumlah_muzakki_badan_usaha,
                                  @Field("fi_501_total_himpunan_tahun_2") String fi_501_total_himpunan_tahun_2,
                                  @Field("fi_502_total_himpunan_tahun_1") String fi_502_total_himpunan_tahun_1,
                                  @Field("fi_601_program_kerja") String fi_601_program_kerja,

                                  @Field("fi_602_rencana_strategis") String fi_602_rencana_strategis,
                                  @Field("fi_603_sop_ada") String fi_603_sop_ada,
                                  @Field("fi_603_sop") String fi_603_sop,
                                  @Field("fi_604_iso_ada") String fi_604_iso_ada,
                                  @Field("fi_604_iso") String fi_604_iso,
                                  @Field("fi_701_total_dana_zis") String fi_701_total_dana_zis,

                                  @Field("fi_702_dana_zis_dakwah_ada") String fi_702_dana_zis_dakwah_ada,
                                  @Field("fi_702_dana_zis_dakwah") String fi_702_dana_zis_dakwah,
                                  @Field("fi_703_penyaluran_zis_produktif_rencana") String fi_703_penyaluran_zis_produktif_rencana,
                                  @Field("fi_703_penyaluran_zis_produktif_realisasi") String fi_703_penyaluran_zis_produktif_realisasi,
                                  @Field("fi_704_penyaluran_zis_sosial_rencana") String fi_704_penyaluran_zis_sosial_rencana,
                                  @Field("fi_704_penyaluran_zis_sosial_realisasi") String fi_704_penyaluran_zis_sosial_realisasi,


                                  @Field("fi_801_laporan_keuangan") String fi_801_laporan_keuangan,
                                  @Field("fi_802_laporan_keuangan_teraudit") String fi_802_laporan_keuangan_teraudit,
                                  @Field("fi_802_laporan_keuangan_wtp") String fi_802_laporan_keuangan_wtp,
                                  @Field("fi_803_laporan_keuangan_publikasi") String fi_803_laporan_keuangan_publikasi,
                                  @Field("fi_804_laporan_audit_syariah") String fi_804_laporan_audit_syariah,
                                  @Field("fi_805_biaya_operasional") String fi_805_biaya_operasional);

    //keluarga
    @FormUrlEncoded
    @POST("input/kajianDampakZakatItems")
    Call<KajianDampakZakatKeluarga> kdzKeluarga(@Header("key") String apikey,
                                                @Field("fki_id") String fki_id,
                                                @Field("fki_fk_id") String fki_fk_id,

                                                @Field("fk_202_nama") String fk_202_nama,
                                                @Field("fk_202_nik") String fk_202_nik,

                                                @Field("fk_203") String fk_203,
                                                @Field("fk_204") String fk_204,

                                                @Field("fk_205") String fk_205,
                                                @Field("fk_206") String fk_206,

                                                @Field("fk_207") String fk_207,
                                                @Field("fk_208") String fk_208,
                                                @Field("fk_209") String fk_209,
                                                @Field("fk_210") String fk_210,
                                                @Field("fk_303") String fk_303,
                                                @Field("fk_304") String fk_304,
                                                @Field("fk_305") String fk_305,
                                                @Field("fk_306") String fk_306,
                                                @Field("fk_307") String fk_307,
                                                @Field("fk_308") String fk_308);

    @FormUrlEncoded
    @POST("input/kajianDampakZakat")
    Call<KajianDampakZakat> kdz(@Header("key") String apikey,
                                @Field("fk_id") String fk_id,
                                @Field("fk_u_id") String fk_u_id,
                                @Field("fk_date_created") String fk_date_created,
                                @Field("fk_date_updated") String fk_date_updated,
                                @Field("fk_nama") String fk_nama,
                                @Field("fk_101_provinsi") String fk_101_provinsi,
                                @Field("fk_102_kabupaten") String fk_102_kabupaten,
                                @Field("fk_103_kecamatan") String fk_103_kecamatan,
                                @Field("fk_104_desa") String fk_104_desa,
                                @Field("fk_105_klasisfikasi_desan") String fk_105_klasisfikasi_desan,
                                @Field("fk_106_kode_rumah") String fk_106_kode_rumah,
                                @Field("fk_107_nama_kepala_rumah") String fk_107_nama_kepala_rumah,
                                @Field("fk_108_jumlah_anggota_rumah") String fk_108_jumlah_anggota_rumah,
                                @Field("fk_109_nomor_hp") String fk_109_nomor_hp,
                                @Field("fk_110_alamat_lengkap") String fk_110_alamat_lengkap,
                                @Field("fk_401_tabungan_bank_konvensional") String fk_401_tabungan_bank_konvensional,
                                @Field("fk_402_tabungan_bank_syariah") String fk_402_tabungan_bank_syariah,
                                @Field("fk_403_tabungan_koeprasi_konvensional") String fk_403_tabungan_koeprasi_konvensional,
                                @Field("fk_404_tabungan_koeprasi_syariah") String fk_404_tabungan_koeprasi_syariah,
                                @Field("fk_405_tabungan_lembaga_zakat") String fk_405_tabungan_lembaga_zakat,
                                @Field("fk_406_arisan") String fk_406_arisan,
                                @Field("fk_407_simpanan_di_rumah") String fk_407_simpanan_di_rumah,
                                @Field("fk_501_memiliki_atap") String fk_501_memiliki_atap,
                                @Field("fk_502_memiliki_dinding") String fk_502_memiliki_dinding,
                                @Field("fk_503_memiliki_listrik") String fk_503_memiliki_listrik,
                                @Field("fk_504_memiliki_lantai") String fk_504_memiliki_lantai,
                                @Field("fk_505_memiliki_air") String fk_505_memiliki_air,
                                @Field("fk_506_memiliki_sanitasi") String fk_506_memiliki_sanitasi,
                                @Field("fk_507_memiliki_penyakit") String fk_507_memiliki_penyakit,
                                @Field("fk_508_tidak_memiliki_cacat") String fk_508_tidak_memiliki_cacat,
                                @Field("fk_509_memiliki_bpjs") String fk_509_memiliki_bpjs,
                                @Field("fk_510_tidak_memiliki_rokok") String fk_510_tidak_memiliki_rokok,
                                @Field("fk_601_penerima_zakat") String fk_601_penerima_zakat,
                                @Field("fk_601_kode") String fk_601_kode,
                                @Field("fk_602_jenis_lembaga") String fk_602_jenis_lembaga,

                                @Field("fk_602_kode") String fk_602_kode,
                                @Field("fk_603_jenis_lembaga") String fk_603_jenis_lembaga,
                                @Field("fk_603_kode") String fk_603_kode,
                                @Field("fk_604_tanggal_menerima") String fk_604_tanggal_menerima,
                                @Field("fk_605_pendapatan") String fk_605_pendapatan,
                                @Field("fk_606_berapa_kali") String fk_606_berapa_kali,
                                @Field("fk_607_jenis_zakat") String fk_607_jenis_zakat,
                                @Field("fk_608_pangan") String fk_608_pangan,
                                @Field("fk_609_kesehatan") String fk_609_kesehatan,
                                @Field("fk_610_pendidikan") String fk_610_pendidikan,
                                @Field("fk_611_lainnya") String fk_611_lainnya,
                                @Field("fk_612_total_bantuan") String fk_612_total_bantuan,
                                @Field("fk_613_bantuan_modal") String fk_613_bantuan_modal,
                                @Field("fk_614_bantuan_alat") String fk_614_bantuan_alat,
                                @Field("fk_615_bantuan_lain") String fk_615_bantuan_lain,
                                @Field("fk_616_total_bantuan") String fk_616_total_bantuan,
                                @Field("fk_617") String fk_617,
                                @Field("fk_618") String fk_618,
                                @Field("fk_701") String fk_701,
                                @Field("fk_702") String fk_702,
                                @Field("fk_703") String fk_703,
                                @Field("fk_801") String fk_801,
                                @Field("fk_802") String fk_802,
                                @Field("fk_803") String fk_803,
                                @Field("fk_804") String fk_804,
                                @Field("fk_805") String fk_805,
                                @Field("fk_806") String fk_806,
                                @Field("fk_807") String fk_807,
                                @Field("fk_808") String fk_808,
                                @Field("fk_809") String fk_809,
                                @Field("fk_810") String fk_810,
                                @Field("fk_811") String fk_811,
                                @Field("fk_812") String fk_812,
                                @Field("fk_813") String fk_813,
                                @Field("fk_814") String fk_814,
                                @Field("fk_815") String fk_815,
                                @Field("fk_shalat") String fk_shalat,
                                @Field("fk_shalat_keterangan") String fk_shalat_keterangan,
                                @Field("fk_puasa") String fk_puasa,
                                @Field("fk_puasa_keterangan") String fk_puasa_keterangan,
                                @Field("fk_zakat") String fk_zakat,
                                @Field("fk_zakat_keterangan") String fk_zakat_keterangan,
                                @Field("fk_lingkungan") String fk_lingkungan,
                                @Field("fk_lingkungan_keterangan") String fk_lingkungan_keterangan,
                                @Field("fk_kebijakan") String fk_kebijakan,
                                @Field("fk_kebijakan_keterangan") String fk_kebijakan_keterangan,
                                @Field("fk_shalat2") String fk_shalat2,
                                @Field("fk_shalat2_keterangan") String fk_shalat2_keterangan,
                                @Field("fk_puasa2") String fk_puasa2,
                                @Field("fk_puasa2_keterangan") String fk_puasa2_keterangan,
                                @Field("fk_zakat2") String fk_zakat2,
                                @Field("fk_zakat2_keterangan") String fk_zakat2_keterangan,
                                @Field("fk_lingkungan2") String fk_lingkungan2,
                                @Field("fk_lingkungan2_keterangan") String fk_lingkungan2_keterangan,
                                @Field("fk_kebijakan2") String fk_kebijakan2,
                                @Field("fk_kebijakan2_keterangan") String fk_kebijakan2_keterangan
    );

}
