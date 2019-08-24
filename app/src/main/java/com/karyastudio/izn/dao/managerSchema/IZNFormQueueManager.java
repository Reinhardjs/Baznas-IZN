package com.karyastudio.izn.dao.managerSchema;

import android.content.Context;

import com.karyastudio.izn.dao.dbdao.DbDao;
import com.karyastudio.izn.dao.generateSchema.DaoSession;
import com.karyastudio.izn.dao.generateSchema.Iznformqueue;
import com.karyastudio.izn.dao.generateSchema.IznformqueueDao;

import java.util.ArrayList;
import java.util.List;

public class IZNFormQueueManager {

    public static List<Iznformqueue> loadAll(Context ctx) {
        return getIZNFormQueueDao(ctx).loadAll();
    }

    public static List<Iznformqueue> loadByID(Context ctx) {
        return getIZNFormQueueDao(ctx).queryBuilder().orderDesc(IznformqueueDao.Properties.Fi_id).list();
    }

    public static boolean checkByID(Context ctx, String matchCase) {
        List<Iznformqueue> list = getIZNFormQueueDao(ctx).loadAll();
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
        return getIZNFormQueueDao(ctx).count();
    }


    public static long insertOrReplace(Context ctx, Iznformqueue IZNFORMQUEUE) {
        return getIZNFormQueueDao(ctx).insertOrReplace(IZNFORMQUEUE);
    }

    public static void addNewList(List<Iznformqueue> listLama, List<Iznformqueue> listBaru, Context ctx){
        List<Iznformqueue> newList = new ArrayList<Iznformqueue>(listLama);
        newList.addAll(listBaru);
        getIZNFormQueueDao(ctx).deleteAll();
        getIZNFormQueueDao(ctx).insertOrReplaceInTx(newList);
    }
    public static void insertOrReplaceArray(Context ctx, List<Iznformqueue> IZNFORMQUEUE) {
        getIZNFormQueueDao(ctx).insertOrReplaceInTx(IZNFORMQUEUE);
    }
    public static void remove(Context ctx, Iznformqueue IZNFORMQUEUE) {
        getIZNFormQueueDao(ctx).delete(IZNFORMQUEUE);
    }

    public static void removeAll(Context ctx) {
        getIZNFormQueueDao(ctx).deleteAll();
    }

    private static IznformqueueDao getIZNFormQueueDao(Context c) {
        DaoSession daoSession = DbDao.getDaoSession(c);
        return daoSession.getIznformqueueDao();
    }

}
