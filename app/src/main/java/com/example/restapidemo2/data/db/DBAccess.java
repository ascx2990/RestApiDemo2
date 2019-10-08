package com.example.restapidemo2.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import timber.log.Timber;

/**
 * @author Will
 * @version 1.0
 * @data today
 */
public class DBAccess extends SQLiteOpenHelper {
    public final static String DB_TableName = "RestAipDemo.db";
    public final static int DB_Version = 1;


    public final static class ALBUM {
        public final static String Table_Name = "album";
        public final static String ID = "_id";
        public final static String UserId = "userId";
        public final static String Title = "title";

    }

    public static final String CREATE_TABLE_ALBUM = "CREATE TABLE " + ALBUM.Table_Name + "" +
            "(" + ALBUM.ID + " INTEGER PRIMARY KEY not null," +
            ALBUM.UserId + " INTEGER not null," +
            ALBUM.Title + " TEXT)";

    public final static class PHOTO {
        public final static String Table_Name = "photo";
        public final static String ID = "_id";
        public final static String AlbumId = "albumId";
        public final static String Url = "url";
        public final static String ThumbnailUrl = "thumbnailUrl";
        public final static String Title = "title";
    }

    public static final String CREATE_TABLE_PHOTO = "CREATE TABLE " + PHOTO.Table_Name + "" +
            "(" + PHOTO.ID + " INTEGER PRIMARY KEY not null," +
            PHOTO.AlbumId + " INTEGER not null," +
            PHOTO.Title + " TEXT," +
            PHOTO.Url + " TEXT," +
            PHOTO.ThumbnailUrl + " TEXT)";

    public DBAccess(Context context) {
        // TODO Auto-generated constructor stub
        super(context, DB_TableName, null, DB_Version);
    }

    public DBAccess(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_TableName, null, DB_Version);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(CREATE_TABLE_ALBUM);
        db.execSQL(CREATE_TABLE_PHOTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        if (newVersion > oldVersion) {
            db.beginTransaction();// 建立交易
            Log.i("DB onUpgrade", "oldVersion:" + oldVersion);
            boolean success = false;// 判斷參數 // 由之前不用的版本，可做不同的動作
            switch (oldVersion) {
                case 1:
                    break;

            }
            if (success) {
                db.setTransactionSuccessful();// 正確交易才成功
            }
            db.endTransaction();
        }
    }

    /**
     * 寫入指令
     */
    public void WrCmd(String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(s);
    }

    /**
     * 查詢指令
     */
    public Cursor ReCmd(String s) {
        SQLiteDatabase db = this.getReadableDatabase();// 取得讀寫資料表物件
        Cursor c = db.rawQuery(s, null);
        return c;
    }

    public long add_Album(int id, int userId, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ALBUM.ID, id);
        values.put(ALBUM.UserId, userId);
        values.put(ALBUM.Title, title);
        long result = 0;
        try {
            result = db.insert(ALBUM.Table_Name, null, values);
        } catch (Exception e) {
            Timber.e(e.toString());
        }finally {
            db.close();
        }

        return result;// 回傳新資料的row ID,若為-1表示新增失敗
    }

    public long add_Photo(int id, int albumId, String title, String url, String thumbnailUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PHOTO.ID, id);
        values.put(PHOTO.AlbumId, albumId);
        values.put(PHOTO.Title, title);
        values.put(PHOTO.Url, url);
        values.put(PHOTO.ThumbnailUrl, thumbnailUrl);
        long result = 0;
        try {
            result = db.insert(PHOTO.Table_Name, null, values);
        } catch (Exception e) {
            Timber.e(e.toString());
        }finally {
            db.close();
        }
        return result;// 回傳新資料的row ID,若為-1表示新增失敗
    }

}
