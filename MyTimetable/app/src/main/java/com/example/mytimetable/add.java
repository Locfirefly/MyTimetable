package com.example.mytimetable;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytimetable.DAL.TimetableDAL;
import com.example.mytimetable.model.timetable;

import java.util.ArrayList;
import java.util.List;

public class add extends AppCompatActivity {
    Context context;
    Button Datestart;
    TimetableAdapter timetableAdapter;
    Button Dateend;
    EditText subjectET;
    EditText classroomET;
    EditText teacherET;
    TimePicker timePicker;
    Button addTB;
    TimetableDAL timetableDAL;
    CheckBox T2, T3, T4, T5, T6, T7, CN;

    String Dstart;
    String Dend;
    String time;
    String subject;
    String classroom;
    String teacher;
    List<Integer> listLS = new ArrayList<Integer>();

    List<timetable> timetableList = new ArrayList<timetable>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_even);
        getSupportActionBar().hide();

        timetableDAL = new TimetableDAL(this);
        Bundle idadd = getIntent().getBundleExtra("IDadd");
        int id = idadd.getInt("IDadd");

        timetableDAL = new TimetableDAL(this);
        timetableAdapter = new TimetableAdapter(add.this, R.layout.adapter, timetableList);

        Datestart = (Button) findViewById(R.id.datestart);
        Dateend = (Button) findViewById(R.id.dateend);
        subjectET = findViewById(R.id.subject);
        classroomET = findViewById(R.id.classroom);
        teacherET = findViewById(R.id.teacher);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        addTB = (Button) findViewById(R.id.addTB);
        T2 = (CheckBox) findViewById(R.id.T2);
        T3 = (CheckBox) findViewById(R.id.T3);
        T4 = (CheckBox) findViewById(R.id.T4);
        T5 = (CheckBox) findViewById(R.id.T5);
        T6 = (CheckBox) findViewById(R.id.T6);
        T7 = (CheckBox) findViewById(R.id.T7);
        CN = (CheckBox) findViewById(R.id.CN);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                time = Integer.toString(timePicker.getHour()) + ":" + Integer.toString(timePicker.getMinute()) + ":00";
                Toast.makeText(getApplicationContext(),
                        time, Toast.LENGTH_SHORT).show();
            }
        });
        Datestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialog, null);
                DatePicker datePicker = (DatePicker) alertLayout.findViewById(R.id.datePicker);
                Button add = (Button) alertLayout.findViewById(R.id.button);
                AlertDialog.Builder alert = new AlertDialog.Builder(add.this);
                alert.setView(alertLayout);
                AlertDialog dialog = alert.create();
                dialog.show();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dstart = Integer.toString(datePicker.getMonth()) + "/" + Integer.toString(datePicker.getDayOfMonth()) + "/" + Integer.toString(datePicker.getYear());
                        Toast.makeText(getApplicationContext(),
                                Dstart, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        Dateend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialog, null);
                DatePicker datePicker = (DatePicker) alertLayout.findViewById(R.id.datePicker);
                Button add = (Button) alertLayout.findViewById(R.id.button);
                AlertDialog.Builder alert = new AlertDialog.Builder(add.this);
                alert.setView(alertLayout);
                AlertDialog dialog = alert.create();
                dialog.show();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dend = Integer.toString(datePicker.getMonth()) + "/" + Integer.toString(datePicker.getDayOfMonth()) + "/" + Integer.toString(datePicker.getYear());
                        Toast.makeText(getApplicationContext(),
                                Dend, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


        addTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (T2.isChecked() == true) listLS.add(1);
                if (T3.isChecked() == true) listLS.add(2);
                if (T4.isChecked() == true) listLS.add(3);
                if (T5.isChecked() == true) listLS.add(4);
                if (T6.isChecked() == true) listLS.add(5);
                if (T7.isChecked() == true) listLS.add(6);
                if (CN.isChecked() == true) listLS.add(7);
                subject = subjectET.getText().toString();
                classroom = classroomET.getText().toString();
                teacher = teacherET.getText().toString();

               boolean check =true;
               String ck="";
                if (subject.equals(ck)||classroom.equals(ck)||teacher.equals(ck)|| Dstart==null||Dend==null||time==null) check = false;
                if (check) {
                    try {
                        timetableDAL.CreateTimetable(subject, classroom, teacher, Dstart, Dend, time, listLS, id);
                        Toast.makeText(getApplicationContext(),
                                "Add compelete!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(),
                                "add Fail!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Have empty attributes!", Toast.LENGTH_SHORT).show();
                }
            }

            });
        }

    }
