package com.donggu.diary;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        db.execSQL("INSERT INTO DiraryInfo (title, content, weatherType, userDate, writeDate) " +
                "VALUES ('" + _title + "', '" + _content + "', '" + _weatherType + "', '" + _userDate + "', '" + _writeDate + "')");
    }
}
