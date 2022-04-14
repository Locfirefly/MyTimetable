package com.example.mytimetable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytimetable.DAL.TimetableDAL;
import com.example.mytimetable.model.timetable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class today_timetable extends AppCompatActivity {
    private Context context;
    TimetableAdapter timetableAdapter;
    Button addtimetable, showinfo;
    CalendarView calendarView;
    ListView listView;
    TimetableDAL timetableDAL;
    List<timetable> timetableList = new ArrayList<timetable>();


        public String main(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormat = formatter.format(date);
        return  dateFormat;

    }
    public void anhxa() {
        addtimetable = (Button) findViewById(R.id.addtimetable);
        calendarView = (CalendarView) findViewById(R.id.calendar);
        listView = (ListView) (findViewById(R.id.listview));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            showinfo = (Button) findViewById(R.id.Btn_edit);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_timetable);
        getSupportActionBar().hide();
        Bundle data = getIntent().getBundleExtra("ID");
        int iD = data.getInt("ID");
        anhxa();
        timetableDAL = new TimetableDAL(this);
        timetableList = timetableDAL.listtimetable(iD);
        timetableAdapter = new TimetableAdapter(today_timetable.this, R.layout.adapter, timetableList);

        listView.setAdapter(timetableAdapter);
        addtimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(today_timetable.this, add.class);
                Bundle bundle = new Bundle();
                bundle.putInt("IDadd", iD);
                intent.putExtra("IDadd", bundle);
                startActivity(intent);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                List<timetable> list=new ArrayList<timetable>();



                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                String datenow=Integer.toString(month)+"/"+Integer.toString( dayOfMonth) +"/"+Integer.toString(year);
                DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Boolean check=true;
                for(timetable i:timetableList)
                {

                    Date date = new Date();
                    Date date2 = new Date();
                    Date date3 = new Date();
                    try {
                        date = formatter.parse(datenow);
                        date2=formatter.parse(i.datestart);
                        date3=formatter.parse(i.dateend);
                    } catch (ParseException e)
                    {
                        e.printStackTrace();
                    }
                    if ((date.compareTo(date2)!=-1)&&(date.compareTo(date3)!=1))
                    {
                    for(int x:i.lesson)
                    {
                        if(x==dayOfWeek)list.add(i);
                        timetableAdapter = new TimetableAdapter(today_timetable.this, R.layout.adapter, list);

                        listView.setAdapter(timetableAdapter);
                        check=false;
                    }
                    }
                    if(check) {
                        View view1 =null ;
                        listView.setEmptyView(view1);
                    }
                }


//                Toast.makeText(getApplicationContext(),
//                        Integer.toString(c)+  "-"+Integer.toString(t)+"-"+x3, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Bundle data = getIntent().getBundleExtra("ID");
        int iD = data.getInt("ID");
        timetableDAL = new TimetableDAL(this);
        timetableList = timetableDAL.listtimetable(iD);
        timetableAdapter = new TimetableAdapter(today_timetable.this, R.layout.adapter, timetableList);

        listView.setAdapter(timetableAdapter);
    }

}
