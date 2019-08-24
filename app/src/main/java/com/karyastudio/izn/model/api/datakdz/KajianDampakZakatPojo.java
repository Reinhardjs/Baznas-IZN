package com.karyastudio.izn.model.api.datakdz;

import android.arch.lifecycle.MutableLiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KajianDampakZakatPojo implements Serializable {

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

    @SerializedName("fk_id")
    @Expose
    private String fk_id;

    @SerializedName("fk_u_id")
    @Expose
    private String fk_u_id;

    @SerializedName("fk_date_created")
    @Expose
    private String fk_date_created;

    @SerializedName("fk_date_updated")
    @Expose
    private String fk_date_updated;

    @SerializedName("fk_code")
    @Expose
    private String fk_code;

    @SerializedName("fk_nama")
    @Expose
    private String fk_nama;

    @SerializedName("fk_101_provinsi")
    @Expose
    private String fk_101_provinsi;

    @SerializedName("fk_102_kabupaten")
    @Expose
    private String fk_102_kabupaten;

    @SerializedName("fk_103_kecamatan")
    @Expose
    private String fk_103_kecamatan;

    @SerializedName("fk_104_desa")
    @Expose
    private String fk_104_desa;

    @SerializedName("fk_105_klasisfikasi_desan")
    @Expose
    private String fk_105_klasisfikasi_desan;

    @SerializedName("fk_106_kode_rumah")
    @Expose
    private String fk_106_kode_rumah;

    @SerializedName("fk_107_nama_kepala_rumah")
    @Expose
    private String fk_107_nama_kepala_rumah;

    @SerializedName("fk_108_jumlah_anggota_rumah")
    @Expose
    private String fk_108_jumlah_anggota_rumah ;

    @SerializedName("fk_109_nomor_hp")
    @Expose
    private String fk_109_nomor_hp;

    @SerializedName("fk_110_alamat_lengkap")
    @Expose
    private String fk_110_alamat_lengkap;

    @SerializedName("fk_401_tabungan_bank_konvensional")
    @Expose
    private String fk_401_tabungan_bank_konvensional;

    @SerializedName("fk_402_tabungan_bank_syariah")
    @Expose
    private String fk_402_tabungan_bank_syariah;

    @SerializedName("fk_403_tabungan_koeprasi_konvensional")
    @Expose
    private String fk_403_tabungan_koeprasi_konvensional;

    @SerializedName("fk_404_tabungan_koeprasi_syariah")
    @Expose
    private String fk_404_tabungan_koeprasi_syariah;

    @SerializedName("fk_405_tabungan_lembaga_zakat")
    @Expose
    private String fk_405_tabungan_lembaga_zakat;

    @SerializedName("fk_406_arisan")
    @Expose
    private String fk_406_arisan;

    @SerializedName("fk_407_simpanan_di_rumah")
    @Expose
    private String fk_407_simpanan_di_rumah;

    @SerializedName("fk_501_memiliki_atap")
    @Expose
    private String fk_501_memiliki_atap;

    @SerializedName("fk_502_memiliki_dinding")
    @Expose
    private String fk_502_memiliki_dinding;

    @SerializedName("fk_503_memiliki_listrik")
    @Expose
    private String fk_503_memiliki_listrik;

    @SerializedName("fk_504_memiliki_lantai")
    @Expose
    private String fk_504_memiliki_lantai;

    @SerializedName("fk_505_memiliki_air")
    @Expose
    private String fk_505_memiliki_air;

    @SerializedName("fk_506_memiliki_sanitasi")
    @Expose
    private String fk_506_memiliki_sanitai;

    @SerializedName("fk_507_memiliki_penyakit")
    @Expose
    private String fk_507_memiliki_penyakit;

    @SerializedName("fk_508_tidak_memiliki_cacat")
    @Expose
    private String fk_508_tidak_memiliki_cacat;

    @SerializedName("fk_509_memiliki_bpjs")
    @Expose
    private String fk_509_memiliki_bpjs;

    @SerializedName("fk_510_tidak_memiliki_rokok")
    @Expose
    private String fk_510_tidak_memiliki_rokok;

    @SerializedName("fk_601_penerima_zakat")
    @Expose
    private String fk_601_penerima_zakat;

    @SerializedName("fk_601_kode")
    @Expose
    private String fk_601_kode;

    @SerializedName("fk_602_jenis_lembaga")
    @Expose
    private String fk_602_jenis_lembaga;

    @SerializedName("fk_602_kode")
    @Expose
    private String fk_602_kode;

    @SerializedName("fk_603_jenis_lembaga")
    @Expose
    private String fk_603_jenis_lembaga;

    @SerializedName("fk_603_kode")
    @Expose
    private String fk_603_kode;

    @SerializedName("fk_604_tanggal_menerima")
    @Expose
    private String fk_604_tanggal_menerima;

    @SerializedName("fk_605_pendapatan")
    @Expose
    private String fk_605_pendapatan;

    @SerializedName("fk_606_berapa_kali")
    @Expose
    private String fk_606_berapa_kali;

    @SerializedName("fk_607_jenis_zakat")
    @Expose
    private String fk_607_jenis_zakat;

    @SerializedName("fk_608_pangan")
    @Expose
    private String fk_608_pangan;

    @SerializedName("fk_609_kesehatan")
    @Expose
    private String fk_609_kesehatan;

    @SerializedName("fk_610_pendidikan")
    @Expose
    private String fk_610_pendidikan;

    @SerializedName("fk_611_lainnya")
    @Expose
    private String fk_611_lainnya;

    @SerializedName("fk_612_total_bantuan")
    @Expose
    private String fk_612_total_bantuan;

    @SerializedName("fk_613_bantuan_modal")
    @Expose
    private String fk_613_bantuan_modal;

    @SerializedName("fk_614_bantuan_alat")
    @Expose
    private String fk_614_bantuan_alat;

    @SerializedName("fk_615_bantuan_lain")
    @Expose
    private String fk_615_bantuan_lain;

    @SerializedName("fk_616_total_bantuan")
    @Expose
    private String fk_616_total_bantuan;

    @SerializedName("fk_617")
    @Expose
    private String fk_617;

    @SerializedName("fk_618")
    @Expose
    private String fk_618;

    @SerializedName("fk_701")
    @Expose
    private String fk_701;

    @SerializedName("fk_702")
    @Expose
    private String fk_702;

    @SerializedName("fk_703")
    @Expose
    private String fk_703;

    @SerializedName("fk_801")
    @Expose
    private String fk_801;

    @SerializedName("fk_802")
    @Expose
    private String fk_802;

    @SerializedName("fk_803")
    @Expose
    private String fk_803;

    @SerializedName("fk_804")
    @Expose
    private String fk_804;

    @SerializedName("fk_805")
    @Expose
    private String fk_805;

    @SerializedName("fk_806")
    @Expose
    private String fk_806;

    @SerializedName("fk_807")
    @Expose
    private String fk_807;

    @SerializedName("fk_808")
    @Expose
    private String fk_808;

    @SerializedName("fk_809")
    @Expose
    private String fk_809;

    @SerializedName("fk_810")
    @Expose
    private String fk_810;

    @SerializedName("fk_811")
    @Expose
    private String fk_811;

    @SerializedName("fk_812")
    @Expose
    private String fk_812;

    @SerializedName("fk_813")
    @Expose
    private String fk_813;

    @SerializedName("fk_814")
    @Expose
    private String fk_814;

    @SerializedName("fk_815")
    @Expose
    private String fk_815;

    @SerializedName("fk_shalat")
    @Expose
    private String fk_shalat;

    @SerializedName("fk_shalat_keterangan")
    @Expose
    private String fk_shalat_keterangan;

    @SerializedName("fk_puasa")
    @Expose
    private String fk_puasa;

    @SerializedName("fk_puasa_keterangan")
    @Expose
    private String fk_puasa_keterangan;

    @SerializedName("fk_zakat")
    @Expose
    private String fk_zakat;

    @SerializedName("fk_zakat_keterangan")
    @Expose
    private String fk_zakat_keterangan;

    @SerializedName("fk_lingkungan")
    @Expose
    private String fk_lingkungan;

    @SerializedName("fk_lingkungan_keterangan")
    @Expose
    private String fk_lingkungan_keterangan;

    @SerializedName("fk_kebijakan")
    @Expose
    private String fk_kebijakan;

    @SerializedName("fk_kebijakan_keterangan")
    @Expose
    private String fk_kebijakan_keterangan;

    @SerializedName("fk_shalat2")
    @Expose
    private String fk_shalat2;

    @SerializedName("fk_shalat2_keterangan")
    @Expose
    private String fk_shalat2_keterangan;

    @SerializedName("fk_puasa2")
    @Expose
    private String fk_puasa2;

    @SerializedName("fk_puasa2_keterangan")
    @Expose
    private String fk_puasa2_ketarangan;

    @SerializedName("fk_zakat2")
    @Expose
    private String fk_zakat2;

    @SerializedName("fk_zakat2_keterangan")
    @Expose
    private String fk_zakat2_keterangan;

    @SerializedName("fk_lingkungan2")
    @Expose
    private String fk_lingkungan2;

    @SerializedName("fk_lingkungan2_keterangan")
    @Expose
    private String fk_lingkungan2_keterangan;

    @SerializedName("fk_kebijakan2")
    @Expose
    private String fk_kebijakan2;

    @SerializedName("fk_kebijakan2_keterangan")
    @Expose
    private String fk_kebijakan2_keterangan;




    @SerializedName("countKeluarga")
    @Expose
    private String countKeluarga;

    public String getFk_id() {
        return fk_id;
    }

    public void setFk_id(String fk_id) {
        this.fk_id = fk_id;
    }

    public String getFk_date_created() {
        return fk_date_created;
    }

    public void setFk_date_created(String fk_date_created) {
        this.fk_date_created = fk_date_created;
    }

    public String getFk_date_updated() {
        return fk_date_updated;
    }

    public void setFk_date_updated(String fk_date_updated) {
        this.fk_date_updated = fk_date_updated;
    }

    public String getFk_nama() {
        return fk_nama;
    }

    public void setFk_nama(String fk_nama) {
        this.fk_nama = fk_nama;
    }

    public String getCountKeluarga() {
        return countKeluarga;
    }

    public void setCountKeluarga(String countKeluarga) {
        this.countKeluarga = countKeluarga;
    }

    public String getFk_u_id() {
        return fk_u_id;
    }

    public void setFk_u_id(String fk_u_id) {
        this.fk_u_id = fk_u_id;
    }

    public String getFk_code() {
        return fk_code;
    }

    public void setFk_code(String fk_code) {
        this.fk_code = fk_code;
    }

    public String getFk_101_provinsi() {
        return fk_101_provinsi;
    }

    public void setFk_101_provinsi(String fk_101_provinsi) {
        this.fk_101_provinsi = fk_101_provinsi;
    }

    public String getFk_102_kabupaten() {
        return fk_102_kabupaten;
    }

    public void setFk_102_kabupaten(String fk_102_kabupaten) {
        this.fk_102_kabupaten = fk_102_kabupaten;
    }

    public String getFk_103_kecamatan() {
        return fk_103_kecamatan;
    }

    public void setFk_103_kecamatan(String fk_103_kecamatan) {
        this.fk_103_kecamatan = fk_103_kecamatan;
    }

    public String getFk_104_desa() {
        return fk_104_desa;
    }

    public void setFk_104_desa(String fk_104_desa) {
        this.fk_104_desa = fk_104_desa;
    }

    public String getFk_105_klasisfikasi_desan() {
        return fk_105_klasisfikasi_desan;
    }

    public void setFk_105_klasisfikasi_desan(String fk_105_klasisfikasi_desan) {
        this.fk_105_klasisfikasi_desan = fk_105_klasisfikasi_desan;
    }

    public String getFk_106_kode_rumah() {
        return fk_106_kode_rumah;
    }

    public void setFk_106_kode_rumah(String fk_106_kode_rumah) {
        this.fk_106_kode_rumah = fk_106_kode_rumah;
    }

    public String getFk_107_nama_kepala_rumah() {
        return fk_107_nama_kepala_rumah;
    }

    public void setFk_107_nama_kepala_rumah(String fk_107_nama_kepala_rumah) {
        this.fk_107_nama_kepala_rumah = fk_107_nama_kepala_rumah;
    }

    public String getFk_108_jumlah_anggota_rumah() {
        return fk_108_jumlah_anggota_rumah;
    }

    public void setFk_108_jumlah_anggota_rumah(String fk_108_jumlah_anggota_rumah) {
        this.fk_108_jumlah_anggota_rumah = fk_108_jumlah_anggota_rumah;
    }

    public String getFk_109_nomor_hp() {
        return fk_109_nomor_hp;
    }

    public void setFk_109_nomor_hp(String fk_109_nomor_hp) {
        this.fk_109_nomor_hp = fk_109_nomor_hp;
    }

    public String getFk_110_alamat_lengkap() {
        return fk_110_alamat_lengkap;
    }

    public void setFk_110_alamat_lengkap(String fk_110_alamat_lengkap) {
        this.fk_110_alamat_lengkap = fk_110_alamat_lengkap;
    }

    public String getFk_401_tabungan_bank_konvensional() {
        return fk_401_tabungan_bank_konvensional;
    }

    public void setFk_401_tabungan_bank_konvensional(String fk_401_tabungan_bank_konvensional) {
        this.fk_401_tabungan_bank_konvensional = fk_401_tabungan_bank_konvensional;
    }

    public String getFk_402_tabungan_bank_syariah() {
        return fk_402_tabungan_bank_syariah;
    }

    public void setFk_402_tabungan_bank_syariah(String fk_402_tabungan_bank_syariah) {
        this.fk_402_tabungan_bank_syariah = fk_402_tabungan_bank_syariah;
    }

    public String getFk_403_tabungan_koeprasi_konvensional() {
        return fk_403_tabungan_koeprasi_konvensional;
    }

    public void setFk_403_tabungan_koeprasi_konvensional(String fk_403_tabungan_koeprasi_konvensional) {
        this.fk_403_tabungan_koeprasi_konvensional = fk_403_tabungan_koeprasi_konvensional;
    }

    public String getFk_404_tabungan_koeprasi_syariah() {
        return fk_404_tabungan_koeprasi_syariah;
    }

    public void setFk_404_tabungan_koeprasi_syariah(String fk_404_tabungan_koeprasi_syariah) {
        this.fk_404_tabungan_koeprasi_syariah = fk_404_tabungan_koeprasi_syariah;
    }

    public String getFk_405_tabungan_lembaga_zakat() {
        return fk_405_tabungan_lembaga_zakat;
    }

    public void setFk_405_tabungan_lembaga_zakat(String fk_405_tabungan_lembaga_zakat) {
        this.fk_405_tabungan_lembaga_zakat = fk_405_tabungan_lembaga_zakat;
    }

    public String getFk_406_arisan() {
        return fk_406_arisan;
    }

    public void setFk_406_arisan(String fk_406_arisan) {
        this.fk_406_arisan = fk_406_arisan;
    }

    public String getFk_407_simpanan_di_rumah() {
        return fk_407_simpanan_di_rumah;
    }

    public void setFk_407_simpanan_di_rumah(String fk_407_simpanan_di_rumah) {
        this.fk_407_simpanan_di_rumah = fk_407_simpanan_di_rumah;
    }

    public String getFk_501_memiliki_atap() {
        return fk_501_memiliki_atap;
    }

    public void setFk_501_memiliki_atap(String fk_501_memiliki_atap) {
        this.fk_501_memiliki_atap = fk_501_memiliki_atap;
    }

    public String getFk_502_memiliki_dinding() {
        return fk_502_memiliki_dinding;
    }

    public void setFk_502_memiliki_dinding(String fk_502_memiliki_dinding) {
        this.fk_502_memiliki_dinding = fk_502_memiliki_dinding;
    }

    public String getFk_503_memiliki_listrik() {
        return fk_503_memiliki_listrik;
    }

    public void setFk_503_memiliki_listrik(String fk_503_memiliki_listrik) {
        this.fk_503_memiliki_listrik = fk_503_memiliki_listrik;
    }

    public String getFk_504_memiliki_lantai() {
        return fk_504_memiliki_lantai;
    }

    public void setFk_504_memiliki_lantai(String fk_504_memiliki_lantai) {
        this.fk_504_memiliki_lantai = fk_504_memiliki_lantai;
    }

    public String getFk_505_memiliki_air() {
        return fk_505_memiliki_air;
    }

    public void setFk_505_memiliki_air(String fk_505_memiliki_air) {
        this.fk_505_memiliki_air = fk_505_memiliki_air;
    }

    public String getFk_506_memiliki_sanitai() {
        return fk_506_memiliki_sanitai;
    }

    public void setFk_506_memiliki_sanitai(String fk_506_memiliki_sanitai) {
        this.fk_506_memiliki_sanitai = fk_506_memiliki_sanitai;
    }

    public String getFk_507_memiliki_penyakit() {
        return fk_507_memiliki_penyakit;
    }

    public void setFk_507_memiliki_penyakit(String fk_507_memiliki_penyakit) {
        this.fk_507_memiliki_penyakit = fk_507_memiliki_penyakit;
    }

    public String getFk_508_tidak_memiliki_cacat() {
        return fk_508_tidak_memiliki_cacat;
    }

    public void setFk_508_tidak_memiliki_cacat(String fk_508_tidak_memiliki_cacat) {
        this.fk_508_tidak_memiliki_cacat = fk_508_tidak_memiliki_cacat;
    }

    public String getFk_509_memiliki_bpjs() {
        return fk_509_memiliki_bpjs;
    }

    public void setFk_509_memiliki_bpjs(String fk_509_memiliki_bpjs) {
        this.fk_509_memiliki_bpjs = fk_509_memiliki_bpjs;
    }

    public String getFk_510_tidak_memiliki_rokok() {
        return fk_510_tidak_memiliki_rokok;
    }

    public void setFk_510_tidak_memiliki_rokok(String fk_510_tidak_memiliki_rokok) {
        this.fk_510_tidak_memiliki_rokok = fk_510_tidak_memiliki_rokok;
    }

    public String getFk_601_penerima_zakat() {
        return fk_601_penerima_zakat;
    }

    public void setFk_601_penerima_zakat(String fk_601_penerima_zakat) {
        this.fk_601_penerima_zakat = fk_601_penerima_zakat;
    }

    public String getFk_602_jenis_lembaga() {
        return fk_602_jenis_lembaga;
    }

    public void setFk_602_jenis_lembaga(String fk_602_jenis_lembaga) {
        this.fk_602_jenis_lembaga = fk_602_jenis_lembaga;
    }

    public String getFk_602_kode() {
        return fk_602_kode;
    }

    public void setFk_602_kode(String fk_602_kode) {
        this.fk_602_kode = fk_602_kode;
    }

    public String getFk_603_jenis_lembaga() {
        return fk_603_jenis_lembaga;
    }

    public void setFk_603_jenis_lembaga(String fk_603_jenis_lembaga) {
        this.fk_603_jenis_lembaga = fk_603_jenis_lembaga;
    }

    public String getFk_603_kode() {
        return fk_603_kode;
    }

    public void setFk_603_kode(String fk_603_kode) {
        this.fk_603_kode = fk_603_kode;
    }

    public String getFk_604_tanggal_menerima() {
        return fk_604_tanggal_menerima;
    }

    public void setFk_604_tanggal_menerima(String fk_604_tanggal_menerima) {
        this.fk_604_tanggal_menerima = fk_604_tanggal_menerima;
    }

    public String getFk_605_pendapatan() {
        return fk_605_pendapatan;
    }

    public void setFk_605_pendapatan(String fk_605_pendapatan) {
        this.fk_605_pendapatan = fk_605_pendapatan;
    }

    public String getFk_606_berapa_kali() {
        return fk_606_berapa_kali;
    }

    public void setFk_606_berapa_kali(String fk_606_berapa_kali) {
        this.fk_606_berapa_kali = fk_606_berapa_kali;
    }

    public String getFk_607_jenis_zakat() {
        return fk_607_jenis_zakat;
    }

    public void setFk_607_jenis_zakat(String fk_607_jenis_zakat) {
        this.fk_607_jenis_zakat = fk_607_jenis_zakat;
    }

    public String getFk_608_pangan() {
        return fk_608_pangan;
    }

    public void setFk_608_pangan(String fk_608_pangan) {
        this.fk_608_pangan = fk_608_pangan;
    }

    public String getFk_609_kesehatan() {
        return fk_609_kesehatan;
    }

    public void setFk_609_kesehatan(String fk_609_kesehatan) {
        this.fk_609_kesehatan = fk_609_kesehatan;
    }

    public String getFk_610_pendidikan() {
        return fk_610_pendidikan;
    }

    public void setFk_610_pendidikan(String fk_610_pendidikan) {
        this.fk_610_pendidikan = fk_610_pendidikan;
    }

    public String getFk_611_lainnya() {
        return fk_611_lainnya;
    }

    public void setFk_611_lainnya(String fk_611_lainnya) {
        this.fk_611_lainnya = fk_611_lainnya;
    }

    public String getFk_612_total_bantuan() {
        return fk_612_total_bantuan;
    }

    public void setFk_612_total_bantuan(String fk_612_total_bantuan) {
        this.fk_612_total_bantuan = fk_612_total_bantuan;
    }

    public String getFk_613_bantuan_modal() {
        return fk_613_bantuan_modal;
    }

    public void setFk_613_bantuan_modal(String fk_613_bantuan_modal) {
        this.fk_613_bantuan_modal = fk_613_bantuan_modal;
    }

    public String getFk_614_bantuan_alat() {
        return fk_614_bantuan_alat;
    }

    public void setFk_614_bantuan_alat(String fk_614_bantuan_alat) {
        this.fk_614_bantuan_alat = fk_614_bantuan_alat;
    }

    public String getFk_615_bantuan_lain() {
        return fk_615_bantuan_lain;
    }

    public void setFk_615_bantuan_lain(String fk_615_bantuan_lain) {
        this.fk_615_bantuan_lain = fk_615_bantuan_lain;
    }

    public String getFk_616_total_bantuan() {
        return fk_616_total_bantuan;
    }

    public void setFk_616_total_bantuan(String fk_616_total_bantuan) {
        this.fk_616_total_bantuan = fk_616_total_bantuan;
    }

    public String getFk_617() {
        return fk_617;
    }

    public void setFk_617(String fk_617) {
        this.fk_617 = fk_617;
    }

    public String getFk_618() {
        return fk_618;
    }

    public void setFk_618(String fk_618) {
        this.fk_618 = fk_618;
    }

    public String getFk_701() {
        return fk_701;
    }

    public void setFk_701(String fk_701) {
        this.fk_701 = fk_701;
    }

    public String getFk_702() {
        return fk_702;
    }

    public void setFk_702(String fk_702) {
        this.fk_702 = fk_702;
    }

    public String getFk_703() {
        return fk_703;
    }

    public void setFk_703(String fk_703) {
        this.fk_703 = fk_703;
    }

    public String getFk_801() {
        return fk_801;
    }

    public void setFk_801(String fk_801) {
        this.fk_801 = fk_801;
    }

    public String getFk_802() {
        return fk_802;
    }

    public void setFk_802(String fk_802) {
        this.fk_802 = fk_802;
    }

    public String getFk_803() {
        return fk_803;
    }

    public void setFk_803(String fk_803) {
        this.fk_803 = fk_803;
    }

    public String getFk_804() {
        return fk_804;
    }

    public void setFk_804(String fk_804) {
        this.fk_804 = fk_804;
    }

    public String getFk_805() {
        return fk_805;
    }

    public void setFk_805(String fk_805) {
        this.fk_805 = fk_805;
    }

    public String getFk_806() {
        return fk_806;
    }

    public void setFk_806(String fk_806) {
        this.fk_806 = fk_806;
    }

    public String getFk_807() {
        return fk_807;
    }

    public void setFk_807(String fk_807) {
        this.fk_807 = fk_807;
    }

    public String getFk_808() {
        return fk_808;
    }

    public void setFk_808(String fk_808) {
        this.fk_808 = fk_808;
    }

    public String getFk_809() {
        return fk_809;
    }

    public void setFk_809(String fk_809) {
        this.fk_809 = fk_809;
    }

    public String getFk_810() {
        return fk_810;
    }

    public void setFk_810(String fk_810) {
        this.fk_810 = fk_810;
    }

    public String getFk_811() {
        return fk_811;
    }

    public void setFk_811(String fk_811) {
        this.fk_811 = fk_811;
    }

    public String getFk_812() {
        return fk_812;
    }

    public void setFk_812(String fk_812) {
        this.fk_812 = fk_812;
    }

    public String getFk_813() {
        return fk_813;
    }

    public void setFk_813(String fk_813) {
        this.fk_813 = fk_813;
    }

    public String getFk_814() {
        return fk_814;
    }

    public void setFk_814(String fk_814) {
        this.fk_814 = fk_814;
    }

    public String getFk_815() {
        return fk_815;
    }

    public void setFk_815(String fk_815) {
        this.fk_815 = fk_815;
    }

    public String getFk_shalat() {
        return fk_shalat;
    }

    public void setFk_shalat(String fk_shalat) {
        this.fk_shalat = fk_shalat;
    }

    public String getFk_shalat_keterangan() {
        return fk_shalat_keterangan;
    }

    public void setFk_shalat_keterangan(String fk_shalat_keterangan) {
        this.fk_shalat_keterangan = fk_shalat_keterangan;
    }

    public String getFk_puasa() {
        return fk_puasa;
    }

    public void setFk_puasa(String fk_puasa) {
        this.fk_puasa = fk_puasa;
    }

    public String getFk_puasa_keterangan() {
        return fk_puasa_keterangan;
    }

    public void setFk_puasa_keterangan(String fk_puasa_keterangan) {
        this.fk_puasa_keterangan = fk_puasa_keterangan;
    }

    public String getFk_zakat() {
        return fk_zakat;
    }

    public void setFk_zakat(String fk_zakat) {
        this.fk_zakat = fk_zakat;
    }

    public String getFk_zakat_keterangan() {
        return fk_zakat_keterangan;
    }

    public void setFk_zakat_keterangan(String fk_zakat_keterangan) {
        this.fk_zakat_keterangan = fk_zakat_keterangan;
    }

    public String getFk_lingkungan() {
        return fk_lingkungan;
    }

    public void setFk_lingkungan(String fk_lingkungan) {
        this.fk_lingkungan = fk_lingkungan;
    }

    public String getFk_lingkungan_keterangan() {
        return fk_lingkungan_keterangan;
    }

    public void setFk_lingkungan_keterangan(String fk_lingkungan_keterangan) {
        this.fk_lingkungan_keterangan = fk_lingkungan_keterangan;
    }

    public String getFk_kebijakan() {
        return fk_kebijakan;
    }

    public void setFk_kebijakan(String fk_kebijakan) {
        this.fk_kebijakan = fk_kebijakan;
    }

    public String getFk_kebijakan_keterangan() {
        return fk_kebijakan_keterangan;
    }

    public void setFk_kebijakan_keterangan(String fk_kebijakan_keterangan) {
        this.fk_kebijakan_keterangan = fk_kebijakan_keterangan;
    }

    public String getFk_shalat2() {
        return fk_shalat2;
    }

    public void setFk_shalat2(String fk_shalat2) {
        this.fk_shalat2 = fk_shalat2;
    }

    public String getFk_shalat2_keterangan() {
        return fk_shalat2_keterangan;
    }

    public void setFk_shalat2_keterangan(String fk_shalat2_keterangan) {
        this.fk_shalat2_keterangan = fk_shalat2_keterangan;
    }

    public String getFk_puasa2() {
        return fk_puasa2;
    }

    public void setFk_puasa2(String fk_puasa2) {
        this.fk_puasa2 = fk_puasa2;
    }

    public String getFk_puasa2_ketarangan() {
        return fk_puasa2_ketarangan;
    }

    public void setFk_puasa2_ketarangan(String fk_puasa2_ketarangan) {
        this.fk_puasa2_ketarangan = fk_puasa2_ketarangan;
    }

    public String getFk_zakat2() {
        return fk_zakat2;
    }

    public void setFk_zakat2(String fk_zakat2) {
        this.fk_zakat2 = fk_zakat2;
    }

    public String getFk_zakat2_keterangan() {
        return fk_zakat2_keterangan;
    }

    public void setFk_zakat2_keterangan(String fk_zakat2_keterangan) {
        this.fk_zakat2_keterangan = fk_zakat2_keterangan;
    }

    public String getFk_lingkungan2() {
        return fk_lingkungan2;
    }

    public void setFk_lingkungan2(String fk_lingkungan2) {
        this.fk_lingkungan2 = fk_lingkungan2;
    }

    public String getFk_lingkungan2_keterangan() {
        return fk_lingkungan2_keterangan;
    }

    public void setFk_lingkungan2_keterangan(String fk_lingkungan2_keterangan) {
        this.fk_lingkungan2_keterangan = fk_lingkungan2_keterangan;
    }

    public String getFk_kebijakan2() {
        return fk_kebijakan2;
    }

    public void setFk_kebijakan2(String fk_kebijakan2) {
        this.fk_kebijakan2 = fk_kebijakan2;
    }

    public String getFk_kebijakan2_keterangan() {
        return fk_kebijakan2_keterangan;
    }

    public void setFk_kebijakan2_keterangan(String fk_kebijakan2_keterangan) {
        this.fk_kebijakan2_keterangan = fk_kebijakan2_keterangan;
    }

    public String getFk_601_kode() {
        return fk_601_kode;
    }

    public void setFk_601_kode(String fk_601_kode) {
        this.fk_601_kode = fk_601_kode;
    }
}