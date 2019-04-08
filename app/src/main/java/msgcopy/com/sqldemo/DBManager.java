package msgcopy.com.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static DBManager mInstance;

    private Context mContext;

    public synchronized static DBManager getInstance() {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager();
                }
            }
        }
        return mInstance;
    }

    public DBManager() {
        mContext = MyApplication.getInstance();
    }

    public void insertData(UserInfo userInfo) {
        DBHelper dbHelper = DBHelper.getInstance(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", userInfo.getName());
        values.put("age", userInfo.getAge());
        values.put("sex", userInfo.getSex());
        values.put("info", userInfo.getInfo());
        db.insert("user_list", null, values);
        db.close();
    }

    public void deleteData(String name) {
        DBHelper dbHelper = DBHelper.getInstance(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("user_list", "name=?", new String[]{name});
        db.close();
    }

    public void deleteAllData() {
        DBHelper dbHelper = DBHelper.getInstance(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("user_list", null, null);
        db.close();
    }

    public void updateData(String name, UserInfo userInfo) {
        DBHelper dbHelper = DBHelper.getInstance(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("age", userInfo.getAge());
        values.put("sex", userInfo.getSex());
        values.put("info", userInfo.getInfo());
        db.update("user_list", values, "name=?", new String[]{name});
        db.close();
    }

    public List<UserInfo> queryData(String sex) {
        List<UserInfo> list = new ArrayList<>();
        DBHelper dbHelper = DBHelper.getInstance(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("user_list", null, "sex=?", new String[]{sex}, null, null, null);
        while (cursor.moveToNext()) {
            UserInfo userInfo = new UserInfo.Builder()
                    .name(cursor.getString(cursor.getColumnIndex("name")))
                    .age(cursor.getInt(cursor.getColumnIndex("age")))
                    .sex(cursor.getInt(cursor.getColumnIndex("sex")))
                    .info(cursor.getString(cursor.getColumnIndex("info")))
                    .build();
            list.add(userInfo);
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<UserInfo> queryAllData() {
        List<UserInfo> list = new ArrayList<>();
        DBHelper dbHelper = DBHelper.getInstance(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("user_list", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            UserInfo userInfo = new UserInfo.Builder()
                    .name(cursor.getString(cursor.getColumnIndex("name")))
                    .age(cursor.getInt(cursor.getColumnIndex("age")))
                    .sex(cursor.getInt(cursor.getColumnIndex("sex")))
                    .info(cursor.getString(cursor.getColumnIndex("info")))
                    .build();
            list.add(userInfo);
        }
        cursor.close();
        db.close();
        return list;
    }

}
