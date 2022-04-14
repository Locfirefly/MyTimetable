package com.example.mytimetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytimetable.model.timetable;

import java.util.ArrayList;
import java.util.List;

public class TimetableAdapter extends BaseAdapter {
    Context Mycontext;
    int mylayout;
    List<timetable> listData=new ArrayList<timetable>();

    public  TimetableAdapter(Context context,int layout,  List<timetable> listTB)
    {
        Mycontext=context;
        mylayout=layout;
        listData=listTB;
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


       LayoutInflater inflater= (LayoutInflater) Mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(mylayout,null);
        TextView subject=(TextView)convertView.findViewById(R.id.subjectadp);
        subject.setText(listData.get(position).subject);
        TextView classroom=(TextView)convertView.findViewById(R.id.classroomadp);
        classroom.setText(listData.get(position).classroom);
        TextView teacher=(TextView)convertView.findViewById(R.id.teacheradp);
        teacher.setText(listData.get(position).Teacher);
         TextView time=(TextView)convertView.findViewById(R.id.timeadp);
         time.setText(listData.get(position).time);

         return convertView;
    }


}
