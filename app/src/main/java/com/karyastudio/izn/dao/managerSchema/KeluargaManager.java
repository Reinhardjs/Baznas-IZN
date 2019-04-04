package com.karyastudio.izn.dao.managerSchema;

import android.content.Context;

import com.karyastudio.izn.dao.dbdao.DbDao;
import com.karyastudio.izn.dao.generateSchema.DaoSession;
import com.karyastudio.izn.dao.generateSchema.Keluarga;
import com.karyastudio.izn.dao.generateSchema.KeluargaDao;

import java.util.ArrayList;
import java.util.List;

public class KeluargaManager  {
    public static List<Keluarga> loadAll(Context ctx) {
        return getKeluargaDao(ctx).loadAll();
    }

    public static List<Keluarga> loadByID(Context ctx) {
        return getKeluargaDao(ctx).queryBuilder().orderDesc(KeluargaDao.Properties.Fk_202_nik).list();
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

    public static long count(Context ctx) {
        return getKeluargaDao(ctx).count();
    }


    public static long insertOrReplace(Context ctx, Keluarga Keluarga) {
        return getKeluargaDao(ctx).insertOrReplace(Keluarga);
    }

    public static void addNewList(List<Keluarga> listLama, List<Keluarga> listBaru, Context ctx){
        List<Keluarga> newList = new ArrayList<Keluarga>(listLama);
        newList.addAll(listBaru);
        getKeluargaDao(ctx).deleteAll();
        getKeluargaDao(ctx).insertOrReplaceInTx(newList);
    }
    public static void insertOrReplaceArray(Context ctx, List<Keluarga> Keluarga) {
        getKeluargaDao(ctx).insertOrReplaceInTx(Keluarga);
    }
    public static void remove(Context ctx, Keluarga Keluarga) {
        getKeluargaDao(ctx).delete(Keluarga);
    }

    public static void removeAll(Context ctx) {
        getKeluargaDao(ctx).deleteAll();
    }

    private static KeluargaDao getKeluargaDao(Context c) {
        DaoSession daoSession = DbDao.getDaoSession(c);
        return daoSession.getKeluargaDao();
    }
}

