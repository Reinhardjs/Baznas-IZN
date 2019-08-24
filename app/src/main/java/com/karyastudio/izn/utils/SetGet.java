package com.karyastudio.izn.utils;

import com.karyastudio.izn.dao.generateSchema.IZN;
import com.karyastudio.izn.dao.generateSchema.KDZ;
import com.karyastudio.izn.model.api.dataizn.IndeksZakatNasionalPojo;
import com.karyastudio.izn.model.api.datakdz.KajianDampakZakatPojo;

public class SetGet {

    public static KDZ getKDZFromResponse(KajianDampakZakatPojo inputData){
        KDZ returnData = new KDZ();
        returnData.setRequest_type(inputData.getRequest_type());
        returnData.setCountKeluarga(inputData.getFk_108_jumlah_anggota_rumah());
        returnData.setFk_id(inputData.getFk_id());
        returnData.setUID(inputData.getFk_u_id());
        returnData.setM1_created_at(inputData.getFk_date_created());
        returnData.setM1_updated_at(inputData.getFk_date_updated());
        returnData.setM1_nama(inputData.getFk_nama());
        returnData.setM1_101(inputData.getFk_101_provinsi());
        returnData.setM1_102(inputData.getFk_102_kabupaten());
        returnData.setM1_103(inputData.getFk_103_kecamatan());
        returnData.setM1_104(inputData.getFk_104_desa());
        returnData.setM1_105(inputData.getFk_105_klasisfikasi_desan());
        returnData.setM1_106(inputData.getFk_106_kode_rumah());
        returnData.setM1_107(inputData.getFk_107_nama_kepala_rumah());
        returnData.setM1_108(inputData.getFk_108_jumlah_anggota_rumah());
        returnData.setM1_109(inputData.getFk_109_nomor_hp());
        returnData.setM1_110(inputData.getFk_110_alamat_lengkap());
        returnData.setM1_401(inputData.getFk_401_tabungan_bank_konvensional());
        returnData.setM1_402(inputData.getFk_402_tabungan_bank_syariah());
        returnData.setM1_403(inputData.getFk_403_tabungan_koeprasi_konvensional());
        returnData.setM1_404(inputData.getFk_404_tabungan_koeprasi_syariah());
        returnData.setM1_405(inputData.getFk_405_tabungan_lembaga_zakat());
        returnData.setM1_406(inputData.getFk_406_arisan());
        returnData.setM1_407(inputData.getFk_407_simpanan_di_rumah());
        returnData.setM1_501(inputData.getFk_501_memiliki_atap());
        returnData.setM1_502(inputData.getFk_502_memiliki_dinding());
        returnData.setM1_503(inputData.getFk_503_memiliki_listrik());
        returnData.setM1_504(inputData.getFk_504_memiliki_lantai());
        returnData.setM1_505(inputData.getFk_505_memiliki_air());
        returnData.setM1_506(inputData.getFk_506_memiliki_sanitai());
        returnData.setM1_507(inputData.getFk_507_memiliki_penyakit());
        returnData.setM1_508(inputData.getFk_508_tidak_memiliki_cacat());
        returnData.setM1_509(inputData.getFk_509_memiliki_bpjs());
        returnData.setM1_510(inputData.getFk_510_tidak_memiliki_rokok());
        returnData.setM1_601(inputData.getFk_601_penerima_zakat());
        returnData.setM1_601_kode(inputData.getFk_601_kode());
        returnData.setM1_602(inputData.getFk_602_jenis_lembaga());
        returnData.setM1_602_kode(inputData.getFk_602_kode());
        returnData.setM1_603(inputData.getFk_603_jenis_lembaga());
        returnData.setM1_603_kode(inputData.getFk_603_kode());
        returnData.setM1_604(inputData.getFk_604_tanggal_menerima());
        returnData.setM1_605(inputData.getFk_605_pendapatan());
        returnData.setM1_606(inputData.getFk_606_berapa_kali());
        returnData.setM1_607(inputData.getFk_607_jenis_zakat());
        returnData.setM1_608(inputData.getFk_608_pangan());
        returnData.setM1_609(inputData.getFk_609_kesehatan());
        returnData.setM1_610(inputData.getFk_610_pendidikan());
        returnData.setM1_611(inputData.getFk_611_lainnya());
        returnData.setM1_612(inputData.getFk_612_total_bantuan());
        returnData.setM1_613(inputData.getFk_613_bantuan_modal());
        returnData.setM1_614(inputData.getFk_614_bantuan_alat());
        returnData.setM1_615(inputData.getFk_615_bantuan_lain());
        returnData.setM1_616(inputData.getFk_616_total_bantuan());
        returnData.setM1_617(inputData.getFk_617());
        returnData.setM1_618(inputData.getFk_618());
        returnData.setM1_701(inputData.getFk_701());
        returnData.setM1_702(inputData.getFk_702());
        returnData.setM1_703(inputData.getFk_703());
        returnData.setM1_801(inputData.getFk_801());
        returnData.setM1_802(inputData.getFk_802());
        returnData.setM1_803(inputData.getFk_803());
        returnData.setM1_804(inputData.getFk_804());
        returnData.setM1_805(inputData.getFk_805());
        returnData.setM1_806(inputData.getFk_806());
        returnData.setM1_807(inputData.getFk_807());
        returnData.setM1_808(inputData.getFk_808());
        returnData.setM1_809(inputData.getFk_809());
        returnData.setM1_810(inputData.getFk_810());
        returnData.setM1_811(inputData.getFk_811());
        returnData.setM1_812(inputData.getFk_812());
        returnData.setM1_813(inputData.getFk_813());
        returnData.setM1_814(inputData.getFk_814());
        returnData.setM1_815(inputData.getFk_815());
        returnData.setM1_lik1(inputData.getFk_shalat());
        returnData.setM1_lik1B(inputData.getFk_shalat2());
        returnData.setM1_lik2(inputData.getFk_puasa());
        returnData.setM1_lik2B(inputData.getFk_puasa2());
        returnData.setM1_lik3(inputData.getFk_zakat());
        returnData.setM1_lik3B(inputData.getFk_zakat2());
        returnData.setM1_lik4(inputData.getFk_lingkungan());
        returnData.setM1_lik4B(inputData.getFk_lingkungan2());
        returnData.setM1_lik5(inputData.getFk_kebijakan());
        returnData.setM1_lik5B(inputData.getFk_kebijakan2());

        return returnData;
    }

    public static KajianDampakZakatPojo getResponseFromKDZ(KDZ data){
        KajianDampakZakatPojo output = new KajianDampakZakatPojo();
        output.setRequest_type(data.getRequest_type());
        output.setFk_id(data.getFk_id());
        output.setFk_u_id(data.getUID());
        output.setFk_date_created(data.getM1_created_at());
        output.setFk_date_updated(data.getM1_updated_at());
        output.setFk_nama(data.getM1_nama());
        output.setFk_101_provinsi(data.getM1_101());
        output.setFk_102_kabupaten(data.getM1_102());
        output.setFk_103_kecamatan(data.getM1_103());
        output.setFk_104_desa(data.getM1_104());
        output.setFk_105_klasisfikasi_desan(data.getM1_105());
        output.setFk_106_kode_rumah(data.getM1_106());
        output.setFk_107_nama_kepala_rumah(data.getM1_107());
        output.setFk_108_jumlah_anggota_rumah(data.getM1_108());
        output.setFk_109_nomor_hp(data.getM1_109());
        output.setFk_110_alamat_lengkap(data.getM1_110());
        output.setFk_401_tabungan_bank_konvensional(data.getM1_401());
        output.setFk_402_tabungan_bank_syariah(data.getM1_402());
        output.setFk_403_tabungan_koeprasi_konvensional(data.getM1_403());
        output.setFk_404_tabungan_koeprasi_syariah(data.getM1_404());
        output.setFk_405_tabungan_lembaga_zakat(data.getM1_405());
        output.setFk_406_arisan(data.getM1_406());
        output.setFk_407_simpanan_di_rumah(data.getM1_407());
        output.setFk_501_memiliki_atap(data.getM1_501());
        output.setFk_502_memiliki_dinding(data.getM1_502());
        output.setFk_503_memiliki_listrik(data.getM1_503());
        output.setFk_504_memiliki_lantai(data.getM1_504());
        output.setFk_505_memiliki_air(data.getM1_505());
        output.setFk_506_memiliki_sanitai(data.getM1_506());
        output.setFk_507_memiliki_penyakit(data.getM1_507());
        output.setFk_508_tidak_memiliki_cacat(data.getM1_508());
        output.setFk_509_memiliki_bpjs(data.getM1_509());
        output.setFk_510_tidak_memiliki_rokok(data.getM1_510());
        output.setFk_601_penerima_zakat(data.getM1_601());
        output.setFk_601_kode(data.getM1_601_kode());
        output.setFk_602_jenis_lembaga(data.getM1_602());
        output.setFk_602_kode(data.getM1_602_kode());
        output.setFk_603_jenis_lembaga(data.getM1_603());
        output.setFk_603_kode(data.getM1_603_kode());
        output.setFk_604_tanggal_menerima(data.getM1_604());
        output.setFk_605_pendapatan(data.getM1_605());
        output.setFk_606_berapa_kali(data.getM1_606());
        output.setFk_607_jenis_zakat(data.getM1_607());
        output.setFk_608_pangan(data.getM1_608());
        output.setFk_609_kesehatan(data.getM1_609());
        output.setFk_610_pendidikan(data.getM1_610());
        output.setFk_611_lainnya(data.getM1_611());
        output.setFk_612_total_bantuan(data.getM1_612());
        output.setFk_613_bantuan_modal(data.getM1_613());
        output.setFk_614_bantuan_alat(data.getM1_614());
        output.setFk_615_bantuan_lain(data.getM1_615());
        output.setFk_616_total_bantuan(data.getM1_616());
        output.setFk_617(data.getM1_617());
        output.setFk_618(data.getM1_618());
        output.setFk_701(data.getM1_701());
        output.setFk_702(data.getM1_702());
        output.setFk_703(data.getM1_703());
        output.setFk_801(data.getM1_801());
        output.setFk_802(data.getM1_802());
        output.setFk_803(data.getM1_803());
        output.setFk_804(data.getM1_804());
        output.setFk_805(data.getM1_805());
        output.setFk_806(data.getM1_806());
        output.setFk_807(data.getM1_807());
        output.setFk_808(data.getM1_808());
        output.setFk_809(data.getM1_809());
        output.setFk_810(data.getM1_810());
        output.setFk_811(data.getM1_811());
        output.setFk_812(data.getM1_812());
        output.setFk_813(data.getM1_813());
        output.setFk_814(data.getM1_814());
        output.setFk_815(data.getM1_815());
        output.setFk_shalat(data.getM1_lik1());
        output.setFk_shalat2(data.getM1_lik1B());
        output.setFk_puasa(data.getM1_lik2());
        output.setFk_puasa2(data.getM1_lik2B());
        output.setFk_zakat(data.getM1_lik3());
        output.setFk_zakat2(data.getM1_lik3B());
        output.setFk_lingkungan(data.getM1_lik4());
        output.setFk_lingkungan2(data.getM1_lik4B());
        output.setFk_kebijakan(data.getM1_lik5());
        output.setFk_kebijakan2(data.getM1_lik5B());

        return output;
    }

    public static IZN getIZNFromResponse(IndeksZakatNasionalPojo data){
        IZN output = new IZN();
        output.setRequest_type(data.getRequest_type());
        output.setFi_id(data.getFi_id());
        output.setFi_u_id(data.getFi_u_id());
        output.setFi_date_created(data.getFi_date_created());
        output.setFi_date_updated(data.getFi_date_updated());
        output.setFi_code(data.getFi_code());
        output.setFi_101_jenis_lembaga(data.getFi_101_jenis_lembaga());
        output.setFi_102_nama_laz(data.getFi_102_nama_laz());
        output.setFi_103_provinsi(data.getFi_103_provinsi());
        output.setFi_104_kabupaten(data.getFi_104_kabupaten());
        output.setFi_201_regulasi(data.getFi_201_regulasi());
        output.setFi_201_regulasi_ada(data.getFi_201_regulasi_ada());
        output.setFi_301_alokasi_apbn_2_tahun_lalu(data.getFi_301_alokasi_apbn_2_tahun_lalu());
        output.setFi_301_alokasi_apbn_2_tahun_lalu_ada(data.getFi_302_alokasi_apbn_1_tahun_lalu_ada());
        output.setFi_302_alokasi_apbn_1_tahun_lalu(data.getFi_302_alokasi_apbn_1_tahun_lalu());
        output.setFi_302_alokasi_apbn_1_tahun_lalu_ada(data.getFi_302_alokasi_apbn_1_tahun_lalu_ada());
        output.setFi_401_lembaga_zakat_resmi(data.getFi_401_lembaga_zakat_resmi());
        output.setFi_401_lembaga_zakat_resmi_ada(data.getFi_401_lembaga_zakat_resmi_ada());
        output.setFi_402_jumlah_mustahik(data.getFi_402_jumlah_mustahik());
        output.setFi_403_mustahik_kabupaten(data.getFi_403_mustahik_kabupaten());
        output.setFi_403_mustahik_kecamatan(data.getFi_403_mustahik_kecamatan());
        output.setFi_404_jumlah_muzakki(data.getFi_404_jumlah_muzakki());
        output.setFi_405_jumlah_munsafki(data.getFi_405_jumlah_munsafik());
        output.setFi_406_jumlah_muzakki_badan_usaha(data.getFi_406_jumlah_muzakki_badan_usaha());
        output.setFi_501_total_himpunan_tahun_2(data.getFi_501_total_himpunan_tahun_2());
        output.setFi_502_total_himpunan_tahun_1(data.getFi_502_total_himpunan_tahun_1());
        output.setFi_601_program_kerja(data.getFi_601_program_kerja());
        output.setFi_602_rencana_strategis(data.getFi_602_rencana_strategis());
        output.setFi_603_sop(data.getFi_603_sop());
        output.setFi_603_sop_ada(data.getFi_603_sop_ada());
        output.setFi_604_iso(data.getFi_604_iso());
        output.setFi_604_iso_ada(data.getFi_604_iso_ada());
        output.setFi_701_total_dana_zis(data.getFi_701_total_dana_zis());
        output.setFi_702_dana_zis_dakwah(data.getFi_702_dana_zis_dakwah());
        output.setFi_702_dana_zis_dakwah_ada(data.getFi_702_dana_zis_dakwah_ada());
        output.setFi_703_penyaluran_zis_produktif_rencana(data.getFi_703_penyaluran_zis_produktif_rencana());
        output.setFi_703_penyaluran_zis_produktif_realisasi(data.getFi_703_penyaluran_zis_produktif_realisasi());
        output.setFi_704_penyaluran_zis_sosial_rencana(data.getFi_704_penyaluran_zis_sosial_rencana());
        output.setFi_704_penyaluran_zis_sosial_realisasi(data.getFi_704_penyaluran_zis_sosial_realisasi());
        output.setFi_801_laporan_keuangan(data.getFi_801_laporan_keuangan());
        output.setFi_802_laporan_keuangan_teraudit(data.getFi_802_laporan_keuangan_teraudit());
        output.setFi_802_laporan_keuangan_wtp(data.getFi_802_laporan_keuangan_wtp());
        output.setFi_803_laporan_keuangan_publikasi(data.getFi_803_laporan_keuangan_publikasi());
        output.setFi_804_laporan_audit_syariah(data.getFi_804_laporan_audit_syariah());
        output.setFi_901_biaya_operasional(data.getFi_805_biaya_operasional());

        return output;
    }


    public static IndeksZakatNasionalPojo getResponseFromIzn(IZN data){
        IndeksZakatNasionalPojo output = new IndeksZakatNasionalPojo();
        output.setRequest_type(data.getRequest_type());
        output.setFi_id(data.getFi_id());
        output.setFi_u_id(data.getFi_u_id());
        output.setFi_date_created(data.getFi_date_created());
        output.setFi_date_updated(data.getFi_date_updated());
        output.setFi_code(data.getFi_code());
        output.setFi_101_jenis_lembaga(data.getFi_101_jenis_lembaga());
        output.setFi_102_nama_laz(data.getFi_102_nama_laz());
        output.setFi_103_provinsi(data.getFi_103_provinsi());
        output.setFi_104_kabupaten(data.getFi_104_kabupaten());
        output.setFi_201_regulasi(data.getFi_201_regulasi());
        output.setFi_201_regulasi_ada(data.getFi_201_regulasi_ada());
        output.setFi_301_alokasi_apbn_2_tahun_lalu(data.getFi_301_alokasi_apbn_2_tahun_lalu());
        output.setFi_301_alokasi_apbn_2_tahun_lalu_ada(data.getFi_302_alokasi_apbn_1_tahun_lalu_ada());
        output.setFi_302_alokasi_apbn_1_tahun_lalu(data.getFi_302_alokasi_apbn_1_tahun_lalu());
        output.setFi_302_alokasi_apbn_1_tahun_lalu_ada(data.getFi_302_alokasi_apbn_1_tahun_lalu_ada());
        output.setFi_401_lembaga_zakat_resmi(data.getFi_401_lembaga_zakat_resmi());
        output.setFi_401_lembaga_zakat_resmi_ada(data.getFi_401_lembaga_zakat_resmi_ada());
        output.setFi_402_jumlah_mustahik(data.getFi_402_jumlah_mustahik());
        output.setFi_403_mustahik_kabupaten(data.getFi_403_mustahik_kabupaten());
        output.setFi_403_mustahik_kecamatan(data.getFi_403_mustahik_kecamatan());
        output.setFi_404_jumlah_muzakki(data.getFi_404_jumlah_muzakki());
        output.setFi_405_jumlah_munsafik(data.getFi_405_jumlah_munsafki());
        output.setFi_406_jumlah_muzakki_badan_usaha(data.getFi_406_jumlah_muzakki_badan_usaha());
        output.setFi_501_total_himpunan_tahun_2(data.getFi_501_total_himpunan_tahun_2());
        output.setFi_502_total_himpunan_tahun_1(data.getFi_502_total_himpunan_tahun_1());
        output.setFi_601_program_kerja(data.getFi_601_program_kerja());
        output.setFi_602_rencana_strategis(data.getFi_602_rencana_strategis());
        output.setFi_603_sop(data.getFi_603_sop());
        output.setFi_603_sop_ada(data.getFi_603_sop_ada());
        output.setFi_604_iso(data.getFi_604_iso());
        output.setFi_604_iso_ada(data.getFi_604_iso_ada());
        output.setFi_701_total_dana_zis(data.getFi_701_total_dana_zis());
        output.setFi_702_dana_zis_dakwah(data.getFi_702_dana_zis_dakwah());
        output.setFi_702_dana_zis_dakwah_ada(data.getFi_702_dana_zis_dakwah_ada());
        output.setFi_703_penyaluran_zis_produktif_rencana(data.getFi_703_penyaluran_zis_produktif_rencana());
        output.setFi_703_penyaluran_zis_produktif_realisasi(data.getFi_703_penyaluran_zis_produktif_realisasi());
        output.setFi_704_penyaluran_zis_sosial_rencana(data.getFi_704_penyaluran_zis_sosial_rencana());
        output.setFi_704_penyaluran_zis_sosial_realisasi(data.getFi_704_penyaluran_zis_sosial_realisasi());
        output.setFi_801_laporan_keuangan(data.getFi_801_laporan_keuangan());
        output.setFi_802_laporan_keuangan_teraudit(data.getFi_802_laporan_keuangan_teraudit());
        output.setFi_802_laporan_keuangan_wtp(data.getFi_802_laporan_keuangan_wtp());
        output.setFi_803_laporan_keuangan_publikasi(data.getFi_803_laporan_keuangan_publikasi());
        output.setFi_804_laporan_audit_syariah(data.getFi_804_laporan_audit_syariah());
        output.setFi_805_biaya_operasional(data.getFi_901_biaya_operasional());

        return output;
    }





















}
