package com.karyastudio.izn.dao.generateSchema;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PROVINCE".
*/
public class ProvinceDao extends AbstractDao<Province, Void> {

    public static final String TABLENAME = "PROVINCE";

    /**
     * Properties of entity Province.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Pro_id = new Property(0, String.class, "pro_id", false, "PRO_ID");
        public final static Property Pro_code = new Property(1, String.class, "pro_code", false, "PRO_CODE");
        public final static Property Pro_province = new Property(2, String.class, "pro_province", false, "PRO_PROVINCE");
    }


    public ProvinceDao(DaoConfig config) {
        super(config);
    }
    
    public ProvinceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PROVINCE\" (" + //
                "\"PRO_ID\" TEXT NOT NULL ," + // 0: pro_id
                "\"PRO_CODE\" TEXT NOT NULL ," + // 1: pro_code
                "\"PRO_PROVINCE\" TEXT NOT NULL );"); // 2: pro_province
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PROVINCE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Province entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getPro_id());
        stmt.bindString(2, entity.getPro_code());
        stmt.bindString(3, entity.getPro_province());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Province entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getPro_id());
        stmt.bindString(2, entity.getPro_code());
        stmt.bindString(3, entity.getPro_province());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Province readEntity(Cursor cursor, int offset) {
        Province entity = new Province( //
            cursor.getString(offset + 0), // pro_id
            cursor.getString(offset + 1), // pro_code
            cursor.getString(offset + 2) // pro_province
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Province entity, int offset) {
        entity.setPro_id(cursor.getString(offset + 0));
        entity.setPro_code(cursor.getString(offset + 1));
        entity.setPro_province(cursor.getString(offset + 2));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Province entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Province entity) {
        return null;
    }

    @Override
    public boolean hasKey(Province entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
