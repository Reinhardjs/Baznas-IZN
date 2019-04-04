package com.karyastudio.izn.dao.dbdao;

import android.app.Application;
import android.content.Context;

import com.karyastudio.izn.dao.generateSchema.DaoMaster;
import com.karyastudio.izn.dao.generateSchema.DaoSession;

import org.greenrobot.greendao.database.Database;

public class DbDao extends Application {

    public static final boolean ENCRYPTED = false;
    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, ENCRYPTED ? "bazarnas-db-encrypted" : "bazarnas-db");
            Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
            daoSession= new DaoMaster(db).newSession();
        }
        return daoSession;
    }
}
