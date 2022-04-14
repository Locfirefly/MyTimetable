package com.example.mytimetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mytimetable.DAL.AccDAL;

import com.example.mytimetable.model.account;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button Login ,Register;
    EditText username,password;
    private  AccDAL accDAL=new AccDAL(MainActivity.this);
    private  Context context;
    private List<account> accountList=new ArrayList<account>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        Login=(Button)findViewById(R.id.Btn_login);
        Register=(Button)findViewById(R.id.register);
        accountList=accDAL.allaccount();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean flag=true;
                for (account a:accountList) {
                    if (username.getText().toString().equals(a.username) && password.getText().toString().equals(a.password)) {
                        Toast.makeText(getApplicationContext(),
                                "Login Compelete!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, today_timetable.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("ID",a.ID);
                        intent.putExtra("ID", bundle);
                        flag=false;
                        startActivity(intent);
                        break;
                    }
                }
                if (flag)
                {
                    Toast.makeText(getApplicationContext(),
                            "invalid password account", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onRestart(){
        super.onRestart();
        accountList=accDAL.allaccount();

    }
}