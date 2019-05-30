package com.karyastudio.izn.dao.managerSchema;

import android.content.Context;

import com.karyastudio.izn.dao.dbdao.DbDao;
import com.karyastudio.izn.dao.generateSchema.DaoSession;
import com.karyastudio.izn.dao.generateSchema.KDZ;
import com.karyastudio.izn.dao.generateSchema.KDZDao;

import java.util.ArrayList;
import java.util.List;

public class KDZManager {
        public static List<KDZ> loadAll(Context ctx) {
            return getKDZDao(ctx).loadAll();
        }

        public static List<KDZ> loadByID(Context ctx) {
            return getKDZDao(ctx).queryBuilder().orderDesc(KDZDao.Properties.Fk_id).list();
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


        public static long insertOrReplace(Context ctx, KDZ KDZ) {
            return getKDZDao(ctx).insertOrReplace(KDZ);
        }

        public static void addNewList(List<KDZ> listLama, List<KDZ> listBaru, Context ctx){
            List<KDZ> newList = new ArrayList<KDZ>(listLama);
            newList.addAll(listBaru);
            getKDZDao(ctx).deleteAll();
            getKDZDao(ctx).insertOrReplaceInTx(newList);
        }
        public static void insertOrReplaceArray(Context ctx, List<KDZ> KDZ) {
            getKDZDao(ctx).insertOrReplaceInTx(KDZ);
        }
        public static void remove(Context ctx, KDZ KDZ) {
            getKDZDao(ctx).delete(KDZ);
        }

        public static void removeAll(Context ctx) {
            getKDZDao(ctx).deleteAll();
        }

        private static KDZDao getKDZDao(Context c) {
            DaoSession daoSession = DbDao.getDaoSession(c);
            return daoSession.getKDZDao();
        }
}

