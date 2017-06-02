package io.github.xunuosi.tb.utils;

import android.database.sqlite.SQLiteDatabase;

import io.github.xunuosi.tb.TBApplication;
import io.github.xunuosi.tb.data.db.DaoMaster;
import io.github.xunuosi.tb.data.db.DaoSession;

/**
 * Created by admin on 2017/6/3.
 * 数据库的工具类
 */

public class GreenDaoHelper {
    private static DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase db;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    public static void initDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(TBApplication.getContext(), "tb-db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
    public static SQLiteDatabase getDb() {
        return db;
    }

}
