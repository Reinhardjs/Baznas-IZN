package com.karyastudio.izn.dao.managerSchema;

import android.content.Context;

import com.karyastudio.izn.dao.dbdao.DbDao;
import com.karyastudio.izn.dao.generateSchema.DaoSession;
import com.karyastudio.izn.dao.generateSchema.Petugas;
import com.karyastudio.izn.dao.generateSchema.PetugasDao;

import java.util.ArrayList;
import java.util.List;

public class PetugasManager {
    public static List<Petugas> loadAll(Context ctx) {
        return getPetugasDao(ctx).loadAll();
    }

    public static List<Petugas> loadByID(Context ctx) {
        return getPetugasDao(ctx).queryBuilder().orderDesc(PetugasDao.Properties.U_id).list();
    }

    public static boolean checkByID(Context ctx, String matchCase) {
        List<Petugas> list = getPetugasDao(ctx).loadAll();
        boolean ret = false;
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getU_id().equalsIgnoreCase(matchCase) ){
                ret = true;
            }else{
                ret = false;
            }
        }
        return ret;
    }

    public static long count(Context ctx) {
        return getPetugasDao(ctx).count();
    }


    public static long insertOrReplace(Context ctx, Petugas Petugas) {
        return getPetugasDao(ctx).insertOrReplace(Petugas);
    }

    public static void addNewList(List<Petugas> listLama, List<Petugas> listBaru, Context ctx){
        List<Petugas> newList = new ArrayList<Petugas>(listLama);
        newList.addAll(listBaru);
        getPetugasDao(ctx).deleteAll();
        getPetugasDao(ctx).insertOrReplaceInTx(newList);
    }
    public static void insertOrReplaceArray(Context ctx, List<Petugas> Petugas) {
        getPetugasDao(ctx).insertOrReplaceInTx(Petugas);
    }
    public static void remove(Context ctx, Petugas Petugas) {
        getPetugasDao(ctx).delete(Petugas);
    }

    public static void removeAll(Context ctx) {
        getPetugasDao(ctx).deleteAll();
    }

    private static PetugasDao getPetugasDao(Context c) {
        DaoSession daoSession = DbDao.getDaoSession(c);
        return daoSession.getPetugasDao();
    }
}
