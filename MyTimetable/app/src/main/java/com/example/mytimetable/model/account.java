package com.example.mytimetable.model;

public class account {
    public  int ID;
    public  String username;
    public  String password;

    public account()
    {}
    public account(int ID,String username,String password)
    {
        this.ID=ID;
        this.username= username;
        this.password=password;
    }
    // ham get
    public  String getUsername()
    {
        return username;
    }

    public String getPassword() {
        return password;
    }
// ham set
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //hamhuy
    protected void finalize( ){ }
}
