package com.karyastudio.izn.dao.generatorSchema;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MainGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.karyastudio.izn.dao.generateSchema");
        addPetugas(schema);
        addIZN(schema);
        addIZNFormQueue(schema);
        addKDZ(schema);
        addKDZFormQueue(schema);
        addProvince(schema);
        addCity(schema);
        addKeluarga(schema);
        addKeluargaQueue(schema);
        new DaoGenerator().generateAll(schema, "../Bazarnas/app/src/main/java/");
    }

    //user when login
    private static void addPetugas(Schema schema) {
        Entity entity = schema.addEntity("Petugas");
        entity.addStringProperty("u_id").notNull();
        entity.addStringProperty("u_username").notNull();
        entity.addStringProperty("u_name").notNull();
        entity.addStringProperty("u_email").notNull();
        entity.addStringProperty("u_phone").notNull();
        entity.addStringProperty("u_rule").notNull();
        entity.addStringProperty("u_status").notNull();
    }

    private static void addIZN(Schema schema) {
        Entity entity = schema.addEntity("IZN");
//        entity.addStringProperty("fi_id").notNull();
//        entity.addStringProperty("fi_code").notNull();
//        entity.addStringProperty("fi_date_created").notNull();
//        entity.addStringProperty("fi_date_updated").notNull();
//        entity.addStringProperty("fi_jenis_lembaga").notNull();
        IZN(entity);
    }

    private static void addIZNFormQueue(Schema schema) {
        Entity entity = schema.addEntity("Iznformqueue");
        IZN(entity);
    }

    private static void IZN(Entity entity){
        entity.addStringProperty("status").notNull();
        entity.addStringProperty("request_type").notNull();
        entity.addStringProperty("fi_id").notNull().primaryKey();
        entity.addStringProperty("fi_u_id");
        entity.addStringProperty("fi_date_created");
        entity.addStringProperty("fi_date_updated");
        entity.addStringProperty("fi_code");
        entity.addStringProperty("fi_101_jenis_lembaga");
        entity.addStringProperty("fi_102_nama_laz");
        entity.addStringProperty("fi_103_provinsi");
        entity.addStringProperty("fi_104_kabupaten");
        entity.addStringProperty("fi_201_regulasi_ada");
        entity.addStringProperty("fi_201_regulasi");
        entity.addStringProperty("fi_301_alokasi_apbn_2_tahun_lalu_ada");
        entity.addStringProperty("fi_301_alokasi_apbn_2_tahun_lalu");
        entity.addStringProperty("fi_302_alokasi_apbn_1_tahun_lalu_ada");
        entity.addStringProperty("fi_302_alokasi_apbn_1_tahun_lalu");
        entity.addStringProperty("fi_401_lembaga_zakat_resmi_ada");
        entity.addStringProperty("fi_401_lembaga_zakat_resmi");
        entity.addStringProperty("fi_402_jumlah_mustahik");
        entity.addStringProperty("fi_403_mustahik_kabupaten");
        entity.addStringProperty("fi_403_mustahik_kecamatan");
        entity.addStringProperty("fi_404_jumlah_muzakki");
        entity.addStringProperty("fi_405_jumlah_munsafki");
        entity.addStringProperty("fi_406_jumlah_muzakki_badan_usaha");
        entity.addStringProperty("fi_501_total_himpunan_tahun_2");
        entity.addStringProperty("fi_502_total_himpunan_tahun_1");
        entity.addStringProperty("fi_601_program_kerja");
        entity.addStringProperty("fi_602_rencana_strategis");
        entity.addStringProperty("fi_603_sop_ada");
        entity.addStringProperty("fi_603_sop");
        entity.addStringProperty("fi_604_iso_ada");
        entity.addStringProperty("fi_604_iso");
        entity.addStringProperty("fi_701_total_dana_zis");
        entity.addStringProperty("fi_702_dana_zis_dakwah_ada");
        entity.addStringProperty("fi_702_dana_zis_dakwah");
        entity.addStringProperty("fi_703_penyaluran_zis_produktif_rencana");
        entity.addStringProperty("fi_703_penyaluran_zis_produktif_realisasi");
        entity.addStringProperty("fi_704_penyaluran_zis_sosial_rencana");
        entity.addStringProperty("fi_704_penyaluran_zis_sosial_realisasi");
        entity.addStringProperty("fi_801_laporan_keuangan");
        entity.addStringProperty("fi_802_laporan_keuangan_teraudit");
        entity.addStringProperty("fi_802_laporan_keuangan_wtp");
        entity.addStringProperty("fi_803_laporan_keuangan_publikasi");
        entity.addStringProperty("fi_804_laporan_audit_syariah");
        entity.addStringProperty("fi_901_biaya_operasional");
    }

    private static void addKDZ(Schema schema) {
        Entity entity = schema.addEntity("KDZ");
        entity.addStringProperty("countKeluarga").notNull();
        KDZ(entity);

    }

    private static void addKDZFormQueue(Schema schema) {
        Entity entity = schema.addEntity("Kdzformqueue");
        KDZ(entity);
    }

    private static void KDZ(Entity entity){
        entity.addStringProperty("status").notNull();
        entity.addStringProperty("request_type").notNull();
        entity.addStringProperty("fk_id").notNull().primaryKey();
        entity.addStringProperty("UID").notNull();
        entity.addStringProperty("M1_created_at").notNull();
        entity.addStringProperty("M1_updated_at").notNull();
        entity.addStringProperty("M1_nama").notNull();
        entity.addStringProperty("M1_101").notNull();
        entity.addStringProperty("M1_102").notNull();
        entity.addStringProperty("M1_103").notNull();
        entity.addStringProperty("M1_104").notNull();
        entity.addStringProperty("M1_105").notNull();
        entity.addStringProperty("M1_106").notNull();
        entity.addStringProperty("M1_107").notNull();
        entity.addStringProperty("M1_108").notNull();
        entity.addStringProperty("M1_109").notNull();
        entity.addStringProperty("M1_110").notNull();
        entity.addStringProperty("M1_401").notNull();
        entity.addStringProperty("M1_402").notNull();
        entity.addStringProperty("M1_403").notNull();
        entity.addStringProperty("M1_404").notNull();
        entity.addStringProperty("M1_405").notNull();
        entity.addStringProperty("M1_406").notNull();
        entity.addStringProperty("M1_407").notNull();
        entity.addStringProperty("M1_501").notNull();
        entity.addStringProperty("M1_502").notNull();
        entity.addStringProperty("M1_503").notNull();
        entity.addStringProperty("M1_504").notNull();
        entity.addStringProperty("M1_505").notNull();
        entity.addStringProperty("M1_506").notNull();
        entity.addStringProperty("M1_507").notNull();
        entity.addStringProperty("M1_508").notNull();
        entity.addStringProperty("M1_509").notNull();
        entity.addStringProperty("M1_510").notNull();
        entity.addStringProperty("M1_601").notNull();
        entity.addStringProperty("M1_601_kode").notNull();
        entity.addStringProperty("M1_602_kode").notNull();
        entity.addStringProperty("M1_603_kode").notNull();
        entity.addStringProperty("M1_602").notNull();
        entity.addStringProperty("M1_603").notNull();
        entity.addStringProperty("M1_604").notNull();
        entity.addStringProperty("M1_605").notNull();
        entity.addStringProperty("M1_606").notNull();
        entity.addStringProperty("M1_607").notNull();
        entity.addStringProperty("M1_608").notNull();
        entity.addStringProperty("M1_609").notNull();
        entity.addStringProperty("M1_610").notNull();
        entity.addStringProperty("M1_611").notNull();
        entity.addStringProperty("M1_612").notNull();
        entity.addStringProperty("M1_613").notNull();
        entity.addStringProperty("M1_614").notNull();
        entity.addStringProperty("M1_615").notNull();
        entity.addStringProperty("M1_616").notNull();
        entity.addStringProperty("M1_617").notNull();
        entity.addStringProperty("M1_618").notNull();
        entity.addStringProperty("M1_701").notNull();
        entity.addStringProperty("M1_702").notNull();
        entity.addStringProperty("M1_703").notNull();
        entity.addStringProperty("M1_801").notNull();
        entity.addStringProperty("M1_802").notNull();
        entity.addStringProperty("M1_803").notNull();
        entity.addStringProperty("M1_804").notNull();
        entity.addStringProperty("M1_805").notNull();
        entity.addStringProperty("M1_806").notNull();
        entity.addStringProperty("M1_807").notNull();
        entity.addStringProperty("M1_808").notNull();
        entity.addStringProperty("M1_809").notNull();
        entity.addStringProperty("M1_810").notNull();
        entity.addStringProperty("M1_811").notNull();
        entity.addStringProperty("M1_812").notNull();
        entity.addStringProperty("M1_813").notNull();
        entity.addStringProperty("M1_814").notNull();
        entity.addStringProperty("M1_815").notNull();
        entity.addStringProperty("M1_lik1").notNull();
        entity.addStringProperty("M1_lik2").notNull();
        entity.addStringProperty("M1_lik3").notNull();
        entity.addStringProperty("M1_lik4").notNull();
        entity.addStringProperty("M1_lik5").notNull();
        entity.addStringProperty("M1_lik1B").notNull();
        entity.addStringProperty("M1_lik2B").notNull();
        entity.addStringProperty("M1_lik3B").notNull();
        entity.addStringProperty("M1_lik4B").notNull();
        entity.addStringProperty("M1_lik5B").notNull();
    }

    private static void addProvince(Schema schema) {
        Entity entity = schema.addEntity("Provinsi");
        entity.addStringProperty("pro_id").notNull();
        entity.addStringProperty("pro_code").notNull();
        entity.addStringProperty("pro_province").notNull();
    }

    private static void addCity(Schema schema) {
        Entity entity = schema.addEntity("City");
        entity.addStringProperty("pro_id").notNull();
        entity.addStringProperty("pro_code").notNull();
        entity.addStringProperty("cit_code").notNull();
        entity.addStringProperty("pro_province").notNull();
        entity.addStringProperty("pro_city").notNull();
    }

    private static void addKeluarga(Schema schema){
        Entity entity = schema.addEntity("Keluarga");
        entity.addIntProperty("posisi");
        entity.addStringProperty("status");
        entity.addStringProperty("request_type").notNull();
        entity.addStringProperty("fki_id").notNull().primaryKey();
        entity.addStringProperty("fki_fk_id").notNull();
        entity.addStringProperty("fk_202_nama").notNull();
        entity.addStringProperty("fk_202_nik").notNull();
        entity.addStringProperty("fk_203").notNull();
        entity.addStringProperty("fk_204").notNull();
        entity.addStringProperty("fk_205").notNull();
        entity.addStringProperty("fk_206").notNull();
        entity.addStringProperty("fk_207").notNull();
        entity.addStringProperty("fk_208").notNull();
        entity.addStringProperty("fk_209").notNull();
        entity.addStringProperty("fk_210").notNull();
        entity.addStringProperty("fk_303").notNull();
        entity.addStringProperty("fk_304").notNull();
        entity.addStringProperty("fk_305").notNull();
        entity.addStringProperty("fk_306").notNull();
        entity.addStringProperty("fk_307").notNull();
        entity.addStringProperty("fk_308").notNull();
    }

    private static void addKeluargaQueue(Schema schema){
        Entity entity = schema.addEntity("KeluargaQueue");
        entity.addStringProperty("fki_id").notNull();
        entity.addStringProperty("fki_fk_id").notNull();
        entity.addStringProperty("fk_202_nama").notNull();
        entity.addStringProperty("fk_202_nik").notNull();
        entity.addStringProperty("fk_203").notNull();
        entity.addStringProperty("fk_204").notNull();
        entity.addStringProperty("fk_205").notNull();
        entity.addStringProperty("fk_206").notNull();
        entity.addStringProperty("fk_207").notNull();
        entity.addStringProperty("fk_208").notNull();
        entity.addStringProperty("fk_209").notNull();
        entity.addStringProperty("fk_210").notNull();
        entity.addStringProperty("fk_303").notNull();
        entity.addStringProperty("fk_304").notNull();
        entity.addStringProperty("fk_305").notNull();
        entity.addStringProperty("fk_306").notNull();
        entity.addStringProperty("fk_307").notNull();
        entity.addStringProperty("fk_308").notNull();
    }


}
