package main.fb.suren.com.myhibernatetestproject;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by suren on 14/7/17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "helloAndroid.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    public Dao<SimpleData, Integer> getSimpleDao() throws SQLException {
        if(simpleDao==null){
            Log.i("DatabaseHelper","in getSimpleDao");
            simpleDao = getDao(SimpleData.class);
        }
        return simpleDao;
    }

    // the DAO object we use to access the SimpleData table
    private static Dao<SimpleData, Integer> simpleDao = null;
    private RuntimeExceptionDao<SimpleData, Integer> simpleRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource,SimpleData.class);
            Log.i(DatabaseHelper.class.getName(), "onCreate");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i(DatabaseHelper.class.getName(), "Cant create table", e);
            throw new RuntimeException(e);
        }
// here we try inserting data in the on-create as a test
        RuntimeExceptionDao<SimpleData, Integer> dao = getSimpleDataDao();
/*
        SimpleData simpleData = new SimpleData(System.currentTimeMillis());
        dao.create(simpleData);
*/


    }

    private RuntimeExceptionDao<SimpleData, Integer> getSimpleDataDao() {

        if(simpleRuntimeDao!=null){
            simpleRuntimeDao = getRuntimeExceptionDao(SimpleData.class);
        }
        return simpleRuntimeDao;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}
