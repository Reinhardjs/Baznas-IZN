package com.karyastudio.izn.dao.managerSchema;

import android.content.Context;

import com.karyastudio.izn.dao.dbdao.DbDao;
import com.karyastudio.izn.dao.generateSchema.DaoSession;
import com.karyastudio.izn.dao.generateSchema.City;
import com.karyastudio.izn.dao.generateSchema.CityDao;

import java.util.ArrayList;
import java.util.List;

public class CityManager{
    public static List<City> loadAll(Context ctx) {
        return getCityDao(ctx).loadAll();
    }

    public static List<City> loadByID(Context ctx) {
        return getCityDao(ctx).queryBuilder().orderDesc(CityDao.Properties.Pro_id).list();
    }

    public static boolean checkByID(Context ctx, String matchCase) {
        List<City> list = getCityDao(ctx).loadAll();
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
        return getCityDao(ctx).count();
    }


    public static long insertOrReplace(Context ctx, City City) {
        return getCityDao(ctx).insertOrReplace(City);
    }

    public static void addNewList(List<City> listLama, List<City> listBaru, Context ctx){
        List<City> newList = new ArrayList<City>(listLama);
        newList.addAll(listBaru);
        getCityDao(ctx).deleteAll();
        getCityDao(ctx).insertOrReplaceInTx(newList);
    }
    public static void insertOrReplaceArray(Context ctx, List<City> City) {
        getCityDao(ctx).insertOrReplaceInTx(City);
    }
    public static void remove(Context ctx, City City) {
        getCityDao(ctx).delete(City);
    }

    public static void removeAll(Context ctx) {
        getCityDao(ctx).deleteAll();
    }

    private static CityDao getCityDao(Context c) {
        DaoSession daoSession = DbDao.getDaoSession(c);
        return daoSession.getCityDao();
    }
}
