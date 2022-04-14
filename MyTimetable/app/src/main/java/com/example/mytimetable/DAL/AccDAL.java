package com.example.mytimetable.DAL;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.mytimetable.database.dbhelper;
import com.example.mytimetable.model.account;

public class AccDAL {
    private dbhelper csdl;

    public AccDAL(Context context) {
        csdl = new dbhelper(context);
    }

    public List<account> allaccount() {
        String sql = " SELECT * FROM ACCOUNT";
        List<account> acclist = new ArrayList<account>();
        SQLiteDatabase database = csdl.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            account acc = new account(ID, username, password);
            acclist.add(acc);
            cursor.moveToNext();
        }
        return acclist;
    }
    public void CreateAccount(String username,String password)
    {
        String sql="INSERT INTO ACCOUNT VALUES"+ "("+"null"+","+"'"+username+"'"+","+"'"+password+"'"+")";
        SQLiteDatabase database = csdl.getWritableDatabase();
        database.execSQL(sql);
    }
}
