package com.karyastudio.izn.dao.managerSchema;

import android.content.Context;

import com.karyastudio.izn.dao.dbdao.DbDao;
import com.karyastudio.izn.dao.generateSchema.DaoSession;
import com.karyastudio.izn.dao.generateSchema.Kdzformqueue;
import com.karyastudio.izn.dao.generateSchema.KdzformqueueDao;

import java.util.ArrayList;
import java.util.List;

public class KDZFormQueueManager {
    public static List<Kdzformqueue> loadAll(Context ctx) {
        return getKDZFormQueueDao(ctx).loadAll();
    }

    public static List<Kdzformqueue> loadByID(Context ctx) {
        return getKDZFormQueueDao(ctx).queryBuilder().orderDesc(KdzformqueueDao.Properties.Fk_id).list();
    }

    public static boolean checkByID(Context ctx, String matchCase) {
        List<Kdzformqueue> list = getKDZFormQueueDao(ctx).loadAll();
        boolean ret = false;
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getFk_id().equalsIgnoreCase(matchCase) ){
                ret = true;
            }else{
                ret = false;
            }
        }
        return ret;
    }

    public static long count(Context ctx) {
        return getKDZFormQueueDao(ctx).count();
    }


    public static long insertOrReplace(Context ctx, Kdzformqueue KDZFORMQUEUE) {
        return getKDZFormQueueDao(ctx).insertOrReplace(KDZFORMQUEUE);
    }

    public static void addNewList(List<Kdzformqueue> listLama, List<Kdzformqueue> listBaru, Context ctx){
        List<Kdzformqueue> newList = new ArrayList<Kdzformqueue>(listLama);
        newList.addAll(listBaru);
        getKDZFormQueueDao(ctx).deleteAll();
        getKDZFormQueueDao(ctx).insertOrReplaceInTx(newList);
    }
    public static void insertOrReplaceArray(Context ctx, List<Kdzformqueue> KDZFORMQUEUE) {
        getKDZFormQueueDao(ctx).insertOrReplaceInTx(KDZFORMQUEUE);
    }
    public static void remove(Context ctx, Kdzformqueue KDZFORMQUEUE) {
        getKDZFormQueueDao(ctx).delete(KDZFORMQUEUE);
    }

    public static void removeAll(Context ctx) {
        getKDZFormQueueDao(ctx).deleteAll();
    }

    private static KdzformqueueDao getKDZFormQueueDao(Context c) {
        DaoSession daoSession = DbDao.getDaoSession(c);
        return daoSession.getKdzformqueueDao();
    }
}


