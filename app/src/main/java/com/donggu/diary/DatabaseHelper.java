package com.donggu.diary;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
* 데이터베이스 관리 클래스
* */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "diary.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
     // database create
        db.execSQL("CREATE TABLE IF NOT EXISTS DiaryInfo (id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", title TEXT NOT NULL" +
                ", content TEXT NOT NULL" +
                ", weatherType INTEGER NOT NULL" +
                ", userDate TEXT NOT NULL" +
                ", writeDate TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int olbVersion, int newVersion) {

        onCreate(db);
    }

    /**
     * 다이어리 작성 데이터를 DB에 저장한다 (INSERT)
     */
    public void setInsertDiaryList(String _title, String _content, int _weatherType, String _userDate, String _writeDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO DiaryInfo (title, content, weatherType, userDate, writeDate) " +
                "VALUES ('" + _title + "', '" + _content + "', '" + _weatherType + "', '" + _userDate + "', '" + _writeDate + "')");
    }

    /***
     * 기존 작성 데이터를 수정한다 (UPDATE)
     *
     */
    public void setUpdateDiaryList(String _title, String _content, int _weatherType, String _userDate, String _writeDate, String _beforeDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DiaryInfo SET title  = '" + _title + "', content = '" + _content + "', weatherType = '" + _weatherType + "', userDate = '" + _userDate + "', writeDate = '" + _writeDate + "'" +
                " WHERE writeDate = '" + _beforeDate + "'");
    }

    /**
     * 기존 작성 데이터를 삭제한다. (DELETE)
     */
    public void setDeleteDiaryList(String _writeDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM DiaryInfo WHERE writeDate = '" + _writeDate + "'");
    }

    /***
     *
     * 다이어리 작성 데이털르 조회하여 가지고 옴
     */
    public ArrayList<DiaryModel> getDiaryListFromDB() {
        ArrayList<DiaryModel> lstDiary = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DiaryInfo ORDER BY writeDate DESC", null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                int weatherType = cursor.getInt(cursor.getColumnIndexOrThrow("weatherType"));
                String userDate = cursor.getString(cursor.getColumnIndexOrThrow("userDate"));
                String writeDate = cursor.getString(cursor.getColumnIndexOrThrow("writeDate"));

                DiaryModel diaryModel = new DiaryModel(id, title, content, weatherType, userDate, writeDate);

                lstDiary.add(diaryModel);

            }
        }

        cursor.close();
        return lstDiary;
    }

}
