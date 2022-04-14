package com.example.mytimetable.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class timetable {
    public int ID;
    public String subject;
    public String classroom;
    public  String Teacher;
    public String datestart;
    public String dateend;
    public String time;
    public List<Integer> lesson;


    public timetable()
    {}
    public timetable(int ID,String subject,String classroom,String Teacher,String datestart,String dateend,String time,List<Integer> Numberlesson)
    {
        this.ID=ID;
        this.subject= subject;
        this.classroom=classroom;
        this.Teacher=Teacher;
        this.datestart=datestart;
        this.dateend=dateend;
        this.time=time;
        this.lesson=Numberlesson;



    }
    // Hàm get()
    public int getID() {
        return ID;
    }
    public String getSubjectsubject() {
        return subject;
    }
    public String getclassroom() {
        return classroom;
    }
    public String getTeacherTeacher() {
        return Teacher;
    }
    public String getdatestart() { return datestart; }
    public String getDateend() { return dateend; }
    public String getTime() {
        return time;
    }
    public List<Integer> getNumberlesson() {
        return lesson;
    }
    // Hàm Set
    public  void setSubject(String subject)
    {
        this.subject=subject;
    }
    public  void setClassroom(String classroom) { this.classroom=classroom; }
    public  void setTeacher(String Teacher ){this.Teacher=Teacher;}
    public  void setdatestart (String date){this.datestart=date;}
    public  void setDateend (String date){this.dateend=date;}
    public  void setTime(String time){this.time=time;}
    public void setNumberlesson(List<Integer> x){
        this.lesson=x;
    }

    // Hàm hủy;
    protected void finalize( ){ }
}

