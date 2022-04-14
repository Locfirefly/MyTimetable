package com.example.mytimetable.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.mytimetable.database.dbhelper;
import com.example.mytimetable.model.account;
import com.example.mytimetable.model.timetable;
import com.example.mytimetable.today_timetable;


import java.text.ParseException;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TimetableDAL {


//    public String main(Date date) {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        String dateFormat = formatter.format(date);
//        return  dateFormat;
//
//    }
//
//    public  Date convertstringtodate(String  dateInString  ) {
//DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        try {
//            Date date = formatter.parse(dateInString);
//            return date;
//        } catch (ParseException e)
//        {
//            e.printStackTrace();
//        }
//    }


    private dbhelper csdl;

    public TimetableDAL(Context context) {
        csdl = new dbhelper(context);
    }

    public List<timetable> listtimetable(int IDacc) {
        String sql = " SELECT ID , SUBJECT, CLASSROOM ,TEACHER,DATESTART,DATEEND,TIMETB  FROM TIMETALBE WHERE TIMETALBE.IDACC='" + Integer.toString(IDacc) + "'";
        List<timetable> TBlist = new ArrayList<timetable>();
        SQLiteDatabase database = csdl.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String subject = cursor.getString(1);
            String classroom = cursor.getString(2);
            String teacher = cursor.getString(3);
            String dates = cursor.getString(4);
            String datestart = cursor.getString(4);
            String dateend = cursor.getString(5);
            String time = cursor.getString(6);
            timetable acc = new timetable(ID, subject, classroom, teacher, datestart, dateend, time, listWeekday(ID));
            TBlist.add(acc);
            cursor.moveToNext();
        }
        return TBlist;
    }

    public void CreateTimetable(String subject, String classroom, String teacher, String datestart, String dateend, String time, List<Integer> list, int id) {

        String sql = "INSERT INTO TIMETALBE VALUES(" + "null" + ",'" + subject + "','" + classroom + "','" + teacher + "','" + datestart + "','" + dateend + "','" + time + "'," + id + ")";
        SQLiteDatabase database = csdl.getWritableDatabase();
        database.execSQL(sql);
        String sqlweekday;
        String l = " SELECT ID  FROM TIMETALBE WHERE IDACC=" + id;
        Cursor cursor = database.rawQuery(l, null);
        cursor.moveToLast();
        int ID = cursor.getInt(0);
        for (int i : list) {
            sqlweekday = "INSERT INTO WEEKDAY  VALUES (" + ID + "," + i + ")";
            database.execSQL(sqlweekday);
        }
    }


    public List<Integer> listWeekday(int IDTB) {
        String sql = " SELECT Numberlesson FROM WEEKDAY WHERE ID='"+IDTB+"'";
        List<Integer> list = new ArrayList<Integer>();
        SQLiteDatabase database = csdl.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            list.add(ID);
            cursor.moveToNext();
        }
        return list;
    }
}

