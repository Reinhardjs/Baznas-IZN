package com.karyastudio.izn.dao.managerSchema;

import android.content.Context;

import com.karyastudio.izn.dao.dbdao.DbDao;
import com.karyastudio.izn.dao.generateSchema.DaoSession;
import com.karyastudio.izn.dao.generateSchema.Provinsi;
import com.karyastudio.izn.dao.generateSchema.ProvinsiDao;

import java.util.ArrayList;
import java.util.List;

public class ProvinsiManager {
    public static List<Provinsi> loadAll(Context ctx) {
        return getProvinsiDao(ctx).loadAll();
    }

    public static List<Provinsi> loadByID(Context ctx) {
        return getProvinsiDao(ctx).queryBuilder().orderDesc(ProvinsiDao.Properties.Pro_id).list();
    }

    public static boolean checkByID(Context ctx, String matchCase) {
        List<Provinsi> list = getProvinsiDao(ctx).loadAll();
        boolean ret = false;
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getPro_id().equalsIgnoreCase(matchCase) ){
                ret = true;
            }else{
                ret = false;
            }
        }
        return ret;
    }

    public static long count(Context ctx) {
        return getProvinsiDao(ctx).count();
    }


    public static long insertOrReplace(Context ctx, Provinsi Provinsi) {
        return getProvinsiDao(ctx).insertOrReplace(Provinsi);
    }

    public static void addNewList(List<Provinsi> listLama, List<Provinsi> listBaru, Context ctx){
        List<Provinsi> newList = new ArrayList<Provinsi>(listLama);
        newList.addAll(listBaru);
        getProvinsiDao(ctx).deleteAll();
        getProvinsiDao(ctx).insertOrReplaceInTx(newList);
    }
    public static void insertOrReplaceArray(Context ctx, List<Provinsi> Provinsi) {
        getProvinsiDao(ctx).insertOrReplaceInTx(Provinsi);
    }
    public static void remove(Context ctx, Provinsi Provinsi) {
        getProvinsiDao(ctx).delete(Provinsi);
    }

    public static void removeAll(Context ctx) {
        getProvinsiDao(ctx).deleteAll();
    }

    private static ProvinsiDao getProvinsiDao(Context c) {
        DaoSession daoSession = DbDao.getDaoSession(c);
        return daoSession.getProvinsiDao();
    }
}

