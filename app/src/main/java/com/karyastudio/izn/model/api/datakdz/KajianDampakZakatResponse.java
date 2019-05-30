package com.karyastudio.izn.model.api.datakdz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KajianDampakZakatResponse implements Serializable {

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

    @SerializedName("fk_108_jumlah_anggota_rumah ")
    @Expose
    private String fk_108_jumlah_anggota_rumah ;

    @SerializedName("fk_109_nomor_hp")
    @Expose
    private String fk_109_nomor_hp;

    @SerializedName("fk_110_alamat_lengkap")
    @Expose
    private String fk_110_alamat_lengkap;


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
}