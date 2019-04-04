package com.karyastudio.izn.dao.managerSchema;

import android.content.Context;

import com.karyastudio.izn.dao.dbdao.DbDao;
import com.karyastudio.izn.dao.generateSchema.DaoSession;
import com.karyastudio.izn.dao.generateSchema.IZN;
import com.karyastudio.izn.dao.generateSchema.IZNDao;

import java.util.ArrayList;
import java.util.List;

public class IZNManager {
    public static List<IZN> loadAll(Context ctx) {
        return getIZNDao(ctx).loadAll();
    }

    public static List<IZN> loadByID(Context ctx) {
        return getIZNDao(ctx).queryBuilder().orderDesc(IZNDao.Properties.Fi_id).list();
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


    public static long insertOrReplace(Context ctx, IZN IZN) {
        return getIZNDao(ctx).insertOrReplace(IZN);
    }

    public static void addNewList(List<IZN> listLama, List<IZN> listBaru, Context ctx){
        List<IZN> newList = new ArrayList<IZN>(listLama);
        newList.addAll(listBaru);
        getIZNDao(ctx).deleteAll();
        getIZNDao(ctx).insertOrReplaceInTx(newList);
    }
    public static void insertOrReplaceArray(Context ctx, List<IZN> IZN) {
        getIZNDao(ctx).insertOrReplaceInTx(IZN);
    }
    public static void remove(Context ctx, IZN IZN) {
        getIZNDao(ctx).delete(IZN);
    }

    public static void removeAll(Context ctx) {
        getIZNDao(ctx).deleteAll();
    }

    private static IZNDao getIZNDao(Context c) {
        DaoSession daoSession = DbDao.getDaoSession(c);
        return daoSession.getIZNDao();
    }
}
