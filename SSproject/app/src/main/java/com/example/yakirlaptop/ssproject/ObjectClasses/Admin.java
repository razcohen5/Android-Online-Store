package com.example.yakirlaptop.ssproject.ObjectClasses;

import com.example.yakirlaptop.ssproject.Singletons.Server;

public class Admin extends User {

    public Admin(String username, String password, int admin, String name)
    {
        super(username, password, admin, name);
    }

    public Admin(User user)
    {
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setAdmin(user.getAdmin());
        setName(user.getName());
    }

    public void deleteUser(String username)
    {
        Server.getServer().deleteUser(username);
    }

    public void deleteAllUsers()
    {
        Server.getServer().deleteAllUsers();
    }

    public void deleteSupplier(int s_id)
    {
        Server.getServer().deleteSupplier(s_id);
    }

    public void deleteAllSuppliers() { Server.getServer().deleteAllSuppliers(); }

    public void deleteProduct(int p_id)
    {
        Server.getServer().deleteProduct(p_id);
    }

    public void deleteAllProducts()
    {
        Server.getServer().deleteAllProducts();
    }

    public boolean addSupplier(String name,String phone,String email,String company)
    {
        return Server.getServer().addSupplier(name,phone,email,company);
    }

    public boolean addProduct(int s_id, String name, int price, int quantity, String image)
    {
        return Server.getServer().addProduct(s_id,name,price,quantity,image);
    }

    public void order(int p_id,int quantity)
    {
        Server.getServer().afterOrderUpdate(p_id,quantity);
    }

}
