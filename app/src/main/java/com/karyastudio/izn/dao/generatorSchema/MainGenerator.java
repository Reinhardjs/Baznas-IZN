package com.karyastudio.izn.dao.generatorSchema;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MainGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(3, "com.karyastudio.baznas.dao.generateSchema");
        addPetugas(schema);
        addIZN(schema);
        addKDZ(schema);
        addProvince(schema);
        addCity(schema);
        addKeluarga(schema);
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
        entity.addStringProperty("fi_id").notNull();
        entity.addStringProperty("fi_code").notNull();
        entity.addStringProperty("fi_date_created").notNull();
        entity.addStringProperty("fi_date_updated").notNull();
        entity.addStringProperty("fi_jenis_lembaga").notNull();

    }
    private static void addKDZ(Schema schema) {
        Entity entity = schema.addEntity("KDZ");
        entity.addStringProperty("fk_id").notNull();
        entity.addStringProperty("fk_date_created").notNull();
        entity.addStringProperty("fk_date_updated").notNull();
        entity.addStringProperty("fk_nama").notNull();
        entity.addStringProperty("countKeluarga").notNull();

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
