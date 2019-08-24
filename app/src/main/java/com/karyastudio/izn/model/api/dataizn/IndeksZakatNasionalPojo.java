package com.karyastudio.izn.model.api.dataizn;

import android.arch.lifecycle.MutableLiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IndeksZakatNasionalPojo implements Serializable {

    public boolean isSending = false;

    private transient MutableLiveData<String> liveStatus = new MutableLiveData<>();

    public MutableLiveData<String> getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(MutableLiveData<String> liveStatus) {
        this.liveStatus = liveStatus;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String request_type;

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    @SerializedName("fi_id")
    @Expose
    private String fi_id;

    @SerializedName("fi_u_id")
    @Expose
    private String fi_u_id;

    @SerializedName("fi_date_created")
    @Expose
    private String fi_date_created;

    @SerializedName("fi_date_updated")
    @Expose
    private String fi_date_updated;

    @SerializedName("fi_code")
    @Expose
    private String fi_code;

    @SerializedName("fi_101_jenis_lembaga")
    @Expose
    private String fi_101_jenis_lembaga;

    @SerializedName("fi_102_nama_laz")
    @Expose
    private String fi_102_nama_laz;

    @SerializedName("fi_103_provinsi")
    @Expose
    private String fi_103_provinsi;

    @SerializedName("fi_104_kabupaten")
    @Expose
    private String fi_104_kabupaten;

    @SerializedName("fi_201_regulasi_ada")
    @Expose
    private String fi_201_regulasi_ada;

    @SerializedName("fi_201_regulasi")
    @Expose
    private String fi_201_regulasi;

    @SerializedName("fi_301_alokasi_apbn_2_tahun_lalu_ada")
    @Expose
    private String fi_301_alokasi_apbn_2_tahun_lalu_ada;

    @SerializedName("fi_301_alokasi_apbn_2_tahun_lalu")
    @Expose
    private String fi_301_alokasi_apbn_2_tahun_lalu;

    @SerializedName("fi_302_alokasi_apbn_1_tahun_lalu_ada")
    @Expose
    private String fi_302_alokasi_apbn_1_tahun_lalu_ada;

    @SerializedName("fi_302_alokasi_apbn_1_tahun_lalu")
    @Expose
    private String fi_302_alokasi_apbn_1_tahun_lalu;

    @SerializedName("fi_401_lembaga_zakat_resmi_ada")
    @Expose
    private String fi_401_lembaga_zakat_resmi_ada;

    @SerializedName("fi_401_lembaga_zakat_resmi")
    @Expose
    private String fi_401_lembaga_zakat_resmi;

    @SerializedName("fi_402_jumlah_mustahik")
    @Expose
    private String fi_402_jumlah_mustahik;

    @SerializedName("fi_403_mustahik_kabupaten")
    @Expose
    private String fi_403_mustahik_kabupaten;

    @SerializedName("fi_403_mustahik_kecamatan")
    @Expose
    private String fi_403_mustahik_kecamatan;

    @SerializedName("fi_404_jumlah_muzakki")
    @Expose
    private String fi_404_jumlah_muzakki;

    @SerializedName("fi_405_jumlah_munsafik")
    @Expose
    private String fi_405_jumlah_munsafik;

    @SerializedName("fi_406_jumlah_muzakki_badan_usaha")
    @Expose
    private String fi_406_jumlah_muzakki_badan_usaha;

    @SerializedName("fi_501_total_himpunan_tahun_2")
    @Expose
    private String fi_501_total_himpunan_tahun_2;

    @SerializedName("fi_502_total_himpunan_tahun_1")
    @Expose
    private String fi_502_total_himpunan_tahun_1;

    @SerializedName("fi_601_program_kerja")
    @Expose
    private String fi_601_program_kerja;

    @SerializedName("fi_602_rencana_strategis")
    @Expose
    private String fi_602_rencana_strategis;

    @SerializedName("fi_603_sop_ada")
    @Expose
    private String fi_603_sop_ada;

    @SerializedName("fi_603_sop")
    @Expose
    private String fi_603_sop;

    @SerializedName("fi_604_iso_ada")
    @Expose
    private String fi_604_iso_ada;

    @SerializedName("fi_604_iso")
    @Expose
    private String fi_604_iso;

    @SerializedName("fi_701_total_dana_zis")
    @Expose
    private String fi_701_total_dana_zis;

    @SerializedName("fi_702_dana_zis_dakwah_ada")
    @Expose
    private String fi_702_dana_zis_dakwah_ada;

    @SerializedName("fi_702_dana_zis_dakwah")
    @Expose
    private String fi_702_dana_zis_dakwah;

    @SerializedName("fi_703_penyaluran_zis_produktif_rencana")
    @Expose
    private String fi_703_penyaluran_zis_produktif_rencana;

    @SerializedName("fi_703_penyaluran_zis_produktif_realisasi")
    @Expose
    private String fi_703_penyaluran_zis_produktif_realisasi;

    @SerializedName("fi_704_penyaluran_zis_sosial_rencana")
    @Expose
    private String fi_704_penyaluran_zis_sosial_rencana;

    @SerializedName("fi_704_penyaluran_zis_sosial_realisasi")
    @Expose
    private String fi_704_penyaluran_zis_sosial_realisasi;

    @SerializedName("fi_801_laporan_keuangan")
    @Expose
    private String fi_801_laporan_keuangan;

    @SerializedName("fi_802_laporan_keuangan_teraudit")
    @Expose
    private String fi_802_laporan_keuangan_teraudit;

    @SerializedName("fi_802_laporan_keuangan_wtp")
    @Expose
    private String fi_802_laporan_keuangan_wtp;

    @SerializedName("fi_803_laporan_keuangan_publikasi")
    @Expose
    private String fi_803_laporan_keuangan_publikasi;

    @SerializedName("fi_804_laporan_audit_syariah")
    @Expose
    private String fi_804_laporan_audit_syariah;

    @SerializedName("fi_805_biaya_operasional")
    @Expose
    private String fi_805_biaya_operasional;



    public String getFi_id() {
        return fi_id;
    }

    public void setFi_id(String fi_id) {
        this.fi_id = fi_id;
    }

    public String getFi_date_created() {
        return fi_date_created;
    }

    public void setFi_date_created(String fi_date_created) {
        this.fi_date_created = fi_date_created;
    }

    public String getFi_date_updated() {
        return fi_date_updated;
    }

    public void setFi_date_updated(String fi_date_updated) {
        this.fi_date_updated = fi_date_updated;
    }

    public String getFi_code() {
        return fi_code;
    }

    public void setFi_code(String fi_code) {
        this.fi_code = fi_code;
    }

    public String getFi_u_id() {
        return fi_u_id;
    }

    public void setFi_u_id(String fi_u_id) {
        this.fi_u_id = fi_u_id;
    }

    public String getFi_101_jenis_lembaga() {
        return fi_101_jenis_lembaga;
    }

    public void setFi_101_jenis_lembaga(String fi_101_jenis_lembaga) {
        this.fi_101_jenis_lembaga = fi_101_jenis_lembaga;
    }

    public String getFi_102_nama_laz() {
        return fi_102_nama_laz;
    }

    public void setFi_102_nama_laz(String fi_102_nama_laz) {
        this.fi_102_nama_laz = fi_102_nama_laz;
    }

    public String getFi_103_provinsi() {
        return fi_103_provinsi;
    }

    public void setFi_103_provinsi(String fi_103_provinsi) {
        this.fi_103_provinsi = fi_103_provinsi;
    }

    public String getFi_104_kabupaten() {
        return fi_104_kabupaten;
    }

    public void setFi_104_kabupaten(String fi_104_kabupaten) {
        this.fi_104_kabupaten = fi_104_kabupaten;
    }

    public String getFi_201_regulasi_ada() {
        return fi_201_regulasi_ada;
    }

    public void setFi_201_regulasi_ada(String fi_201_regulasi_ada) {
        this.fi_201_regulasi_ada = fi_201_regulasi_ada;
    }

    public String getFi_201_regulasi() {
        return fi_201_regulasi;
    }

    public void setFi_201_regulasi(String fi_201_regulasi) {
        this.fi_201_regulasi = fi_201_regulasi;
    }

    public String getFi_301_alokasi_apbn_2_tahun_lalu_ada() {
        return fi_301_alokasi_apbn_2_tahun_lalu_ada;
    }

    public void setFi_301_alokasi_apbn_2_tahun_lalu_ada(String fi_301_alokasi_apbn_2_tahun_lalu_ada) {
        this.fi_301_alokasi_apbn_2_tahun_lalu_ada = fi_301_alokasi_apbn_2_tahun_lalu_ada;
    }

    public String getFi_301_alokasi_apbn_2_tahun_lalu() {
        return fi_301_alokasi_apbn_2_tahun_lalu;
    }

    public void setFi_301_alokasi_apbn_2_tahun_lalu(String fi_301_alokasi_apbn_2_tahun_lalu) {
        this.fi_301_alokasi_apbn_2_tahun_lalu = fi_301_alokasi_apbn_2_tahun_lalu;
    }

    public String getFi_302_alokasi_apbn_1_tahun_lalu_ada() {
        return fi_302_alokasi_apbn_1_tahun_lalu_ada;
    }

    public void setFi_302_alokasi_apbn_1_tahun_lalu_ada(String fi_302_alokasi_apbn_1_tahun_lalu_ada) {
        this.fi_302_alokasi_apbn_1_tahun_lalu_ada = fi_302_alokasi_apbn_1_tahun_lalu_ada;
    }

    public String getFi_302_alokasi_apbn_1_tahun_lalu() {
        return fi_302_alokasi_apbn_1_tahun_lalu;
    }

    public void setFi_302_alokasi_apbn_1_tahun_lalu(String fi_302_alokasi_apbn_1_tahun_lalu) {
        this.fi_302_alokasi_apbn_1_tahun_lalu = fi_302_alokasi_apbn_1_tahun_lalu;
    }

    public String getFi_401_lembaga_zakat_resmi_ada() {
        return fi_401_lembaga_zakat_resmi_ada;
    }

    public void setFi_401_lembaga_zakat_resmi_ada(String fi_401_lembaga_zakat_resmi_ada) {
        this.fi_401_lembaga_zakat_resmi_ada = fi_401_lembaga_zakat_resmi_ada;
    }

    public String getFi_401_lembaga_zakat_resmi() {
        return fi_401_lembaga_zakat_resmi;
    }

    public void setFi_401_lembaga_zakat_resmi(String fi_401_lembaga_zakat_resmi) {
        this.fi_401_lembaga_zakat_resmi = fi_401_lembaga_zakat_resmi;
    }

    public String getFi_402_jumlah_mustahik() {
        return fi_402_jumlah_mustahik;
    }

    public void setFi_402_jumlah_mustahik(String fi_402_jumlah_mustahik) {
        this.fi_402_jumlah_mustahik = fi_402_jumlah_mustahik;
    }

    public String getFi_403_mustahik_kabupaten() {
        return fi_403_mustahik_kabupaten;
    }

    public void setFi_403_mustahik_kabupaten(String fi_403_mustahik_kabupaten) {
        this.fi_403_mustahik_kabupaten = fi_403_mustahik_kabupaten;
    }

    public String getFi_403_mustahik_kecamatan() {
        return fi_403_mustahik_kecamatan;
    }

    public void setFi_403_mustahik_kecamatan(String fi_403_mustahik_kecamatan) {
        this.fi_403_mustahik_kecamatan = fi_403_mustahik_kecamatan;
    }

    public String getFi_404_jumlah_muzakki() {
        return fi_404_jumlah_muzakki;
    }

    public void setFi_404_jumlah_muzakki(String fi_404_jumlah_muzakki) {
        this.fi_404_jumlah_muzakki = fi_404_jumlah_muzakki;
    }

    public String getFi_405_jumlah_munsafik() {
        return fi_405_jumlah_munsafik;
    }

    public void setFi_405_jumlah_munsafik(String fi_405_jumlah_munsafik) {
        this.fi_405_jumlah_munsafik = fi_405_jumlah_munsafik;
    }

    public String getFi_406_jumlah_muzakki_badan_usaha() {
        return fi_406_jumlah_muzakki_badan_usaha;
    }

    public void setFi_406_jumlah_muzakki_badan_usaha(String fi_406_jumlah_muzakki_badan_usaha) {
        this.fi_406_jumlah_muzakki_badan_usaha = fi_406_jumlah_muzakki_badan_usaha;
    }

    public String getFi_501_total_himpunan_tahun_2() {
        return fi_501_total_himpunan_tahun_2;
    }

    public void setFi_501_total_himpunan_tahun_2(String fi_501_total_himpunan_tahun_2) {
        this.fi_501_total_himpunan_tahun_2 = fi_501_total_himpunan_tahun_2;
    }

    public String getFi_502_total_himpunan_tahun_1() {
        return fi_502_total_himpunan_tahun_1;
    }

    public void setFi_502_total_himpunan_tahun_1(String fi_502_total_himpunan_tahun_1) {
        this.fi_502_total_himpunan_tahun_1 = fi_502_total_himpunan_tahun_1;
    }

    public String getFi_601_program_kerja() {
        return fi_601_program_kerja;
    }

    public void setFi_601_program_kerja(String fi_601_program_kerja) {
        this.fi_601_program_kerja = fi_601_program_kerja;
    }

    public String getFi_602_rencana_strategis() {
        return fi_602_rencana_strategis;
    }

    public void setFi_602_rencana_strategis(String fi_602_rencana_strategis) {
        this.fi_602_rencana_strategis = fi_602_rencana_strategis;
    }

    public String getFi_603_sop_ada() {
        return fi_603_sop_ada;
    }

    public void setFi_603_sop_ada(String fi_603_sop_ada) {
        this.fi_603_sop_ada = fi_603_sop_ada;
    }

    public String getFi_603_sop() {
        return fi_603_sop;
    }

    public void setFi_603_sop(String fi_603_sop) {
        this.fi_603_sop = fi_603_sop;
    }

    public String getFi_604_iso_ada() {
        return fi_604_iso_ada;
    }

    public void setFi_604_iso_ada(String fi_604_iso_ada) {
        this.fi_604_iso_ada = fi_604_iso_ada;
    }

    public String getFi_604_iso() {
        return fi_604_iso;
    }

    public void setFi_604_iso(String fi_604_iso) {
        this.fi_604_iso = fi_604_iso;
    }

    public String getFi_701_total_dana_zis() {
        return fi_701_total_dana_zis;
    }

    public void setFi_701_total_dana_zis(String fi_701_total_dana_zis) {
        this.fi_701_total_dana_zis = fi_701_total_dana_zis;
    }

    public String getFi_702_dana_zis_dakwah_ada() {
        return fi_702_dana_zis_dakwah_ada;
    }

    public void setFi_702_dana_zis_dakwah_ada(String fi_702_dana_zis_dakwah_ada) {
        this.fi_702_dana_zis_dakwah_ada = fi_702_dana_zis_dakwah_ada;
    }

    public String getFi_702_dana_zis_dakwah() {
        return fi_702_dana_zis_dakwah;
    }

    public void setFi_702_dana_zis_dakwah(String fi_702_dana_zis_dakwah) {
        this.fi_702_dana_zis_dakwah = fi_702_dana_zis_dakwah;
    }

    public String getFi_703_penyaluran_zis_produktif_rencana() {
        return fi_703_penyaluran_zis_produktif_rencana;
    }

    public void setFi_703_penyaluran_zis_produktif_rencana(String fi_703_penyaluran_zis_produktif_rencana) {
        this.fi_703_penyaluran_zis_produktif_rencana = fi_703_penyaluran_zis_produktif_rencana;
    }

    public String getFi_703_penyaluran_zis_produktif_realisasi() {
        return fi_703_penyaluran_zis_produktif_realisasi;
    }

    public void setFi_703_penyaluran_zis_produktif_realisasi(String fi_703_penyaluran_zis_produktif_realisasi) {
        this.fi_703_penyaluran_zis_produktif_realisasi = fi_703_penyaluran_zis_produktif_realisasi;
    }

    public String getFi_704_penyaluran_zis_sosial_rencana() {
        return fi_704_penyaluran_zis_sosial_rencana;
    }

    public void setFi_704_penyaluran_zis_sosial_rencana(String fi_704_penyaluran_zis_sosial_rencana) {
        this.fi_704_penyaluran_zis_sosial_rencana = fi_704_penyaluran_zis_sosial_rencana;
    }

    public String getFi_704_penyaluran_zis_sosial_realisasi() {
        return fi_704_penyaluran_zis_sosial_realisasi;
    }

    public void setFi_704_penyaluran_zis_sosial_realisasi(String fi_704_penyaluran_zis_sosial_realisasi) {
        this.fi_704_penyaluran_zis_sosial_realisasi = fi_704_penyaluran_zis_sosial_realisasi;
    }

    public String getFi_801_laporan_keuangan() {
        return fi_801_laporan_keuangan;
    }

    public void setFi_801_laporan_keuangan(String fi_801_laporan_keuangan) {
        this.fi_801_laporan_keuangan = fi_801_laporan_keuangan;
    }

    public String getFi_802_laporan_keuangan_teraudit() {
        return fi_802_laporan_keuangan_teraudit;
    }

    public void setFi_802_laporan_keuangan_teraudit(String fi_802_laporan_keuangan_teraudit) {
        this.fi_802_laporan_keuangan_teraudit = fi_802_laporan_keuangan_teraudit;
    }

    public String getFi_802_laporan_keuangan_wtp() {
        return fi_802_laporan_keuangan_wtp;
    }

    public void setFi_802_laporan_keuangan_wtp(String fi_802_laporan_keuangan_wtp) {
        this.fi_802_laporan_keuangan_wtp = fi_802_laporan_keuangan_wtp;
    }

    public String getFi_803_laporan_keuangan_publikasi() {
        return fi_803_laporan_keuangan_publikasi;
    }

    public void setFi_803_laporan_keuangan_publikasi(String fi_803_laporan_keuangan_publikasi) {
        this.fi_803_laporan_keuangan_publikasi = fi_803_laporan_keuangan_publikasi;
    }

    public String getFi_804_laporan_audit_syariah() {
        return fi_804_laporan_audit_syariah;
    }

    public void setFi_804_laporan_audit_syariah(String fi_804_laporan_audit_syariah) {
        this.fi_804_laporan_audit_syariah = fi_804_laporan_audit_syariah;
    }

    public String getFi_805_biaya_operasional() {
        return fi_805_biaya_operasional;
    }

    public void setFi_805_biaya_operasional(String fi_805_biaya_operasional) {
        this.fi_805_biaya_operasional = fi_805_biaya_operasional;
    }
}