package com.karyastudio.izn.dao.managerSchema;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.karyastudio.izn.dao.dbdao.DbDao;
import com.karyastudio.izn.dao.generateSchema.DaoSession;
import com.karyastudio.izn.dao.generateSchema.KDZ;
import com.karyastudio.izn.dao.generateSchema.KDZDao;
import com.karyastudio.izn.utils.StaticStrings;

import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;

import java.util.ArrayList;
import java.util.List;

public class KDZManager {

    public static List<KDZ> loadAllByStatus(Context ctx, String status){
//        List<KDZ> list = getKDZDao(ctx).loadAll();
//        List<KDZ> newList = new ArrayList<>();
//
//        for (KDZ kdz : list){
//            if (kdz.getStatus().trim().equalsIgnoreCase(status.trim())){
//                newList.add(kdz);
//            }
//        }
//
//        return newList;
        return getKDZDao(ctx).queryBuilder().where(KDZDao.Properties.Status.eq(status.trim())).list();
    }

    public static List<KDZ> loadAll(Context ctx) {
        return getKDZDao(ctx).loadAll();
    }

    public static List<KDZ> loadByID(Context ctx, String fk_id) {
        // return getKDZDao(ctx).queryBuilder().orderDesc(KDZDao.Properties.Fk_id).list();
        return getKDZDao(ctx).queryBuilder().where(KDZDao.Properties.Fk_id.eq(fk_id)).list();
    }

    public static boolean checkByID(Context ctx, String matchCase) {
        List<KDZ> list = getKDZDao(ctx).loadAll();
        boolean ret = false;
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getFk_id().equalsIgnoreCase(matchCase) ){
                ret = true;
            } else{
                ret = false;
            }
        }
        return ret;
    }

    public static long count(Context ctx) {
        return getKDZDao(ctx).count();
    }


    public static void addNewList(List<KDZ> listLama, List<KDZ> listBaru, Context ctx){
        List<KDZ> newList = new ArrayList<KDZ>(listLama);
        newList.addAll(listBaru);
        getKDZDao(ctx).deleteAll();
        getKDZDao(ctx).insertOrReplaceInTx(newList);
    }

    public static MutableLiveData<Boolean> insertOrReplace(Context ctx, KDZ kdz, MutableLiveData<Boolean> liveData) {
        // return getKDZDao(ctx).insertOrReplace(IZN);
        if (liveData != null)
            liveData.setValue(false);

        MutableLiveData<Boolean> isComplete = new MutableLiveData<>();

        AsyncSession asyncSession = DbDao.getDaoSession(ctx).startAsyncSession();
        asyncSession.setListener( new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                ((Activity)ctx).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (liveData != null)
                            liveData.setValue(true);

                        isComplete.setValue(true);
                    }
                });
            }
        });
        asyncSession.insertOrReplace(kdz);

        return isComplete;
    }

    public static MutableLiveData<Boolean> insertOrReplaceArray(Context ctx, List<KDZ> kdz, MutableLiveData<Boolean> liveData) {
        // getKDZDao(ctx).insertOrReplaceInTx(IZN);
        if (liveData != null)
            liveData.setValue(false);

        MutableLiveData<Boolean> isComplete = new MutableLiveData<>();

        AsyncSession asyncSession = DbDao.getDaoSession(ctx).startAsyncSession();
        asyncSession.setListener( new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                ((Activity)ctx).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (liveData != null)
                            liveData.setValue(true);

                        isComplete.setValue(true);
                    }
                });
            }
        });
        asyncSession.insertOrReplaceInTx(KDZ.class, kdz);

        return isComplete;
    }

    public static void remove(Context ctx, KDZ KDZ) {
        getKDZDao(ctx).delete(KDZ);
    }

    public static void removeAll(Context ctx) {
        getKDZDao(ctx).deleteAll();
    }

    public static void removeAllSent(Context ctx){
        List<KDZ> kdzToDelete = loadAllByStatus(ctx, StaticStrings.KDZ_STATUS_SENT);

        getKDZDao(ctx).deleteInTx(kdzToDelete);
    }

    private static KDZDao getKDZDao(Context c) {
        DaoSession daoSession = DbDao.getDaoSession(c);
        return daoSession.getKDZDao();
    }
}

