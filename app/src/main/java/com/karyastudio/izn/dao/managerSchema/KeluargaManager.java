package com.karyastudio.izn.dao.managerSchema;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.karyastudio.izn.dao.dbdao.DbDao;
import com.karyastudio.izn.dao.generateSchema.DaoSession;
import com.karyastudio.izn.dao.generateSchema.Keluarga;
import com.karyastudio.izn.dao.generateSchema.KeluargaDao;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;

import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;
import org.greenrobot.greendao.query.DeleteQuery;

import java.util.ArrayList;
import java.util.List;

public class KeluargaManager {

    public static List<Keluarga> loadAll(Context ctx) {
        return getKeluargaDao(ctx).loadAll();
    }

    public static List<Keluarga> loadAllByStatus(Context ctx, String status){
        return getKeluargaDao(ctx).queryBuilder().where(KeluargaDao.Properties.Status.eq(status)).list();
    }

    public static List<Keluarga> loadByID(Context ctx, String id) {
        // return getKeluargaDao(ctx).queryBuilder().orderDesc(KeluargaDao.Properties.Fk_202_nik).list();
        return getKeluargaDao(ctx).queryBuilder().where(KeluargaDao.Properties.Fk_202_nik.eq(id)).list();
    }

    public static boolean checkByID(Context ctx, String matchCase) {
        List<Keluarga> list = getKeluargaDao(ctx).loadAll();
        boolean ret = false;
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getFk_202_nik().equalsIgnoreCase(matchCase) ){
                ret = true;
            }else{
                ret = false;
            }
        }
        return ret;
    }

    public static List<Keluarga> loadAllwithFKID(Context context, String fk_id){
        return getKeluargaDao(context).queryBuilder()
                .where(KeluargaDao.Properties.Fki_fk_id.eq(fk_id))
                .orderAsc(KeluargaDao.Properties.Posisi)
                .list();
    }

    public static long count(Context ctx) {
        return getKeluargaDao(ctx).count();
    }


    public static MutableLiveData insertOrReplace(Context ctx, Keluarga keluarga) {
        // return getKeluargaDao(ctx).insertOrReplace(Keluarga);

        MutableLiveData<Boolean> isComplete = new MutableLiveData<>();
        AsyncSession asyncSession = DbDao.getDaoSession(ctx).startAsyncSession();
        asyncSession.setListener( new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                ((Activity)ctx).runOnUiThread(() -> isComplete.setValue(true));
            }
        });
        asyncSession.insertOrReplace(keluarga);

        return isComplete;
    }

    public static MutableLiveData<Boolean> insertOrReplaceArray(Context ctx, List<Keluarga> keluarga) {
        // getKeluargaDao(ctx).insertOrReplaceInTx(Keluarga);
        MutableLiveData<Boolean> isComplete = new MutableLiveData<>();
        AsyncSession asyncSession = DbDao.getDaoSession(ctx).startAsyncSession();
        asyncSession.setListener( new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                ((Activity)ctx).runOnUiThread(() -> isComplete.setValue(true));
            }
        });
        asyncSession.insertOrReplaceInTx(Keluarga.class, keluarga);

        return isComplete;
    }

    public static void addNewList(List<Keluarga> listLama, List<Keluarga> listBaru, Context ctx){
        List<Keluarga> newList = new ArrayList<Keluarga>(listLama);
        newList.addAll(listBaru);
        getKeluargaDao(ctx).deleteAll();
        getKeluargaDao(ctx).insertOrReplaceInTx(newList);
    }

    public static void remove(Context ctx, Keluarga Keluarga) {
        getKeluargaDao(ctx).delete(Keluarga);
    }

    public static void removeAll(Context ctx) {
        getKeluargaDao(ctx).deleteAll();
    }

    public static void removeAllSent(Context ctx){
        final DeleteQuery<Keluarga> keluargaDeleteQuery = getKeluargaDao(ctx)
                .queryBuilder()
                .where(KeluargaDao.Properties.Status.eq(StaticStrings.KDZ_STATUS_SENT))
        .buildDelete();

        keluargaDeleteQuery.executeDeleteWithoutDetachingEntities();
        DbDao.getDaoSession(ctx).clear();
    }

    public static void removeAllWithFKID(Context ctx, String fk_id){
        Utils.log("DELETEING KELURAGA FK ID " + fk_id);
        final DeleteQuery<Keluarga> keluargaDeleteQuery = getKeluargaDao(ctx).queryBuilder()
                .where(KeluargaDao.Properties.Fki_fk_id.eq(fk_id))
                .buildDelete();

        keluargaDeleteQuery.executeDeleteWithoutDetachingEntities();
        DbDao.getDaoSession(ctx).clear();
    }

    private static KeluargaDao getKeluargaDao(Context c) {
        DaoSession daoSession = DbDao.getDaoSession(c);
        return daoSession.getKeluargaDao();
    }
}

