package msgcopy.com.sqldemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liang on 2017/5/9.
 */

public class DBHelper extends SQLiteOpenHelper{

    private final static String DB_NAME = "User.db";
    private final static int DB_VERSION = 1;
    private static volatile DBHelper sInstance;

    private final Context mContext;

    public DBHelper(final Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    public static DBHelper getsInstance(Context context){
        if (sInstance == null){
            synchronized (DBHelper.class){
                if (sInstance == null){
                    sInstance = new DBHelper(context);
                }
            }
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE music_center (_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,songid VARCHAR,artist VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
