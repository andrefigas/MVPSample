package devfigas.com.mvpsample.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import devfigas.com.mvpsample.BuildConfig;
import devfigas.com.mvpsample.model.domain.Movie;


public class DatabaseHelper<E> extends OrmLiteSqliteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, BuildConfig.DB_NAME, null, BuildConfig.DB_VERSION);
        getWritableDatabase().close();
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Movie.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            try {
                TableUtils.dropTable(connectionSource,Movie.class,true);
                onCreate(database,connectionSource);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
