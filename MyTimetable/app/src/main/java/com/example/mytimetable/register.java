package com.example.mytimetable;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mytimetable.DAL.AccDAL;
import com.example.mytimetable.model.account;

import java.util.ArrayList;
import java.util.List;


public class register extends AppCompatActivity {
    private Context context;
    EditText username,password,repassword;
    Button register;
    private AccDAL accDAL;
    private List<account> accountList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        register = (Button) findViewById(R.id.register);
        accDAL=new AccDAL(this);
        accountList=new ArrayList<account>();
        accountList=accDAL.allaccount();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag=true;
                for(account a:accountList)
                {
                   if( a.username.equals(username.getText().toString()))
                   {
                       Toast.makeText(getApplicationContext(),
                               "Username Exists!", Toast.LENGTH_SHORT).show();
                       flag=false;
                       break;
                   }
                }
                if(flag==true) {
                    if (password.getText().toString().length() >= 8 && password.getText().toString().equals(repassword.getText().toString())) {
                        try {

                            accDAL.CreateAccount(username.getText().toString(), password.getText().toString());
                            Toast.makeText(getApplicationContext(),
                                    "Register Compelete!", Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(getIntent());
                        } catch (Exception e) {

                            Toast.makeText(getApplicationContext(),
                                    "Register Fail!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "passwords are 8 characters or more long or are not the same!", Toast.LENGTH_LONG).show();

                    }
                }

                accountList=accDAL.allaccount();
            }

        });

    }
}
