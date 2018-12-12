package com.example.yakirlaptop.ssproject.ObjectClasses;

import android.content.Context;
import android.widget.ListView;

import com.example.yakirlaptop.ssproject.DatabaseAPI.DatabaseOpenHelper;
import com.example.yakirlaptop.ssproject.R;

public class User {
    private String name;
    private String username;
    private String password;
    private int admin;
    private Context context;

    public User()
    {

    }

    public User(String username,String password,int admin,String name)
    {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
