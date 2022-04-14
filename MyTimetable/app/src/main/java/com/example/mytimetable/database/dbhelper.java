package com.example.mytimetable.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbhelper extends SQLiteOpenHelper {
    public dbhelper(@Nullable Context context) {
        super(context, "TimeTable.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlAccount="CREATE TABLE IF NOT EXISTS ACCOUNT( IDACC INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME CHAR(30) NOT NULL, PASSWORD CHAR(30) NOT NULL)" ;
        db.execSQL(sqlAccount);
        String sqlTimetable="CREATE TABLE IF NOT EXISTS TIMETALBE( ID INTEGER PRIMARY KEY AUTOINCREMENT, SUBJECT CHAR(30) NOT NULL, CLASSROOM CHAR(30) NOT NULL,TEACHER CHAR(30) NOT NULL ,DATESTART date NOT NULL,DATEEND date NOT NULL,TIMETB time NOT NULL,IDACC INTEGER NOT NULL, CONSTRAINT fk1 FOREIGN KEY (IDACC)  REFERENCES ACCOUNT(IDACC) )" ;
        db.execSQL(sqlTimetable);
        String sqlweekday="CREATE TABLE IF NOT EXISTS WEEKDAY( ID INTEGER,Numberlesson INTEGER,CONSTRAINT fk2_WEEKDAY FOREIGN KEY (ID)  REFERENCES TIMETALBE(ID))" ;
        db.execSQL(sqlweekday);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
