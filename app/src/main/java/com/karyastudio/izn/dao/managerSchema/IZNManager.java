package com.karyastudio.izn.dao.managerSchema;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.karyastudio.izn.dao.dbdao.DbDao;
import com.karyastudio.izn.dao.generateSchema.DaoSession;
import com.karyastudio.izn.dao.generateSchema.IZN;
import com.karyastudio.izn.dao.generateSchema.IZNDao;
import com.karyastudio.izn.utils.StaticStrings;

import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;

import java.util.ArrayList;
import java.util.List;

public class IZNManager {

    public static List<IZN> loadAllByStatus(Context ctx, String status){
        List<IZN> list = getIZNDao(ctx).loadAll();
        List<IZN> newList = new ArrayList<>();

        for (IZN izn : list){
            if (izn.getStatus().trim().equalsIgnoreCase(status.trim())){
                newList.add(izn);
            }
        }

        return newList;
    }

    public static List<IZN> loadAll(Context ctx) {
        return getIZNDao(ctx).loadAll();
    }

    public static List<IZN> loadByID(Context ctx, String fi_id) {
//        return getIZNDao(ctx).queryBuilder().orderDesc(IZNDao.Properties.Fi_id).list();
        return getIZNDao(ctx).queryBuilder().where(IZNDao.Properties.Fi_id.eq(fi_id)).list();
    }

    public static boolean checkByID(Context ctx, String matchCase) {
        List<IZN> list = getIZNDao(ctx).loadAll();
        boolean ret = false;
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getFi_id().equalsIgnoreCase(matchCase) ){
                ret = true;
            }else{
                ret = false;
            }
        }
        return ret;
    }

    public static long count(Context ctx) {
        return getIZNDao(ctx).count();
    }


    public static void addNewList(List<IZN> listLama, List<IZN> listBaru, Context ctx){
        List<IZN> newList = new ArrayList<IZN>(listLama);
        newList.addAll(listBaru);
        getIZNDao(ctx).deleteAll();
        getIZNDao(ctx).insertOrReplaceInTx(newList);
    }

    public static MutableLiveData<Boolean> insertOrReplace(Context ctx, IZN IZN, MutableLiveData<Boolean> liveData) {
        // return getIZNDao(ctx).insertOrReplace(IZN);
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
        asyncSession.insertOrReplace(IZN);

        return isComplete;
    }

    public static MutableLiveData<Boolean> insertOrReplaceArray(Context ctx, List<IZN> IZN, MutableLiveData<Boolean> liveData) {
        // getIZNDao(ctx).insertOrReplaceInTx(IZN);
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
        asyncSession.insertOrReplaceInTx(IZN.class, IZN);

        return isComplete;
    }

    public static void remove(Context ctx, IZN IZN) {
        getIZNDao(ctx).delete(IZN);
    }

    public static void removeAll(Context ctx) {
        getIZNDao(ctx).deleteAll();
    }

    public static void removeAllSent(Context ctx){
        List<IZN> iznToDelete = loadAllByStatus(ctx, StaticStrings.IZN_STATUS_SENT);

        getIZNDao(ctx).deleteInTx(iznToDelete);
    }

    private static IZNDao getIZNDao(Context c) {
        DaoSession daoSession = DbDao.getDaoSession(c);
        return daoSession.getIZNDao();
    }
}
