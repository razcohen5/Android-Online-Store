package com.example.yakirlaptop.ssproject.Singletons;

import com.example.yakirlaptop.ssproject.ObjectClasses.Admin;
import com.example.yakirlaptop.ssproject.ObjectClasses.Customer;
import com.example.yakirlaptop.ssproject.ObjectClasses.User;

public class UserHolder {
    private static final UserHolder userholder = new UserHolder();
    private User user;

    private UserHolder() {}
    public static UserHolder getUserHolder() {
        return userholder;
    }
    public User getUser(){return user;}
    public Admin getAdmin(){return (Admin)user;}
    public Customer getCustomer(){return (Customer)user;}
    public void setUser(User user)
    {
        this.user=user;
    }
}
