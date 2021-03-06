package com.example.yakirlaptop.ssproject.Singletons;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yakirlaptop.ssproject.DatabaseAPI.DatabaseOpenHelper;
import com.example.yakirlaptop.ssproject.ObjectClasses.Cart;
import com.example.yakirlaptop.ssproject.ObjectClasses.Customer;
import com.example.yakirlaptop.ssproject.ObjectClasses.Product;
import com.example.yakirlaptop.ssproject.ObjectClasses.Supplier;
import com.example.yakirlaptop.ssproject.ObjectClasses.User;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Server {
    private static final Server server = new Server();
    private DatabaseOpenHelper db;

    private Server() { }//create admin default account
    public static Server getServer() {
        return server;
    }
    public void setContext(Context context) {
        db = new DatabaseOpenHelper(context);
        createDefaultAdmin("admin","admin","admin");
    }

    private void createDefaultAdmin(String adminUsername,String adminPassword,String adminName)
    {
       register(adminUsername,adminPassword,1,adminName,"","");
    }

    public User login(String username, String password)//login if password is correct and username exists
    {
        User user = db.getUserByUsername(username);
        if(user!=null)
        {
            if(user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public boolean register(String username, String password ,int admin, String name , String email , String creditcard)//add user if username is not taken
    {
        if (server.db.getUserByUsername(username)!=null) //already exists user with that username
            return false;
        server.db.addUser(username,password,admin,name,email,creditcard);
        return true;
    }

    public ArrayList<String> getTableList(String tablename)//return list for listview
    {
        ArrayList<String> listdata = new ArrayList<>();
        if(tablename.equals("users"))
        {
            Cursor data = db.getUsersTable();
            while(data.moveToNext())
                listdata.add("Username: "+data.getString(0)+"\nPassword: "+data.getString(1)+"\nAdmin: "+data.getInt(2)+"\nName: "+data.getString(3)+"\nEmail: " +data.getString(4)+"\nCredit Card: "+data.getString(5));
        }
        else if(tablename.equals("products"))
        {
            Cursor data = db.getProductsTable();
            while(data.moveToNext())
                listdata.add("Product_id: "+data.getInt(0)+"\nSupplier_id: "+data.getInt(1)+"\nName: "+data.getString(2)+"\nPrice: "+data.getInt(3)+"\nQuantity: " +data.getInt(4)+"\nImage: "+data.getString(5));
        }
        else
        {
            Cursor data = db.getSuppliersTable();
            while(data.moveToNext())
                listdata.add("Supplier_id: "+data.getInt(0)+"\nName: "+data.getString(1)+"\nPhone: "+data.getString(2)+"\nEmail: "+data.getString(3)+"\nCompany: " +data.getString(4));
        }
        return listdata;
    }
  
    public ArrayList<String> getSuppliersNames(){
        ArrayList<String> listdata = new ArrayList<>();;
        Cursor data = db.getSuppliersTable();
        while(data.moveToNext())
            listdata.add(data.getString(1)+", "+data.getString(4));
        return listdata;
    }

    public ArrayList<String> getAllImgPath(){
        ArrayList<String> listdata = new ArrayList<>();;
        Cursor data = db.getAllImgPath();
        while(data.moveToNext())
            listdata.add(data.getString(0));
        return listdata;
    }

    public Supplier getSupplierByName(String name)
    {
        return db.getSupplierByName(name);
    }

    public boolean addSupplier(String name,String phone,String email,String company)
    {
        if (server.db.getSupplierByName(name)!=null) //already exists user with that username
            return false;
        server.db.addSupplier(name,phone,email,company);
        return true;
    }

    public boolean addProduct(int s_id, String name, int price, int quantity, String image)
    {
        if (server.db.getProductByName(name)!=null) //already exists user with that username
            return false;
        server.db.addProduct(s_id,name,price,quantity,image);
        return true;
    }
  
    public void deleteUser(String username)
    {
        db.deleteUser(username);
    }

    public void deleteAllUsers()
    {
        db.deleteAllUsers();
    }

    public boolean deleteSupplier(int s_id)//delete only if there are no products supplied by this supplier in the store
    {
        Cursor cursor = db.getProductsTable();
        while(cursor.moveToNext())
            if(cursor.getInt(1)==s_id)
                return false;
        db.deleteSupplier(s_id);
        return true;
    }

    public boolean deleteAllSuppliers()
    {
        Cursor cursor = db.getSuppliersTable();
        while(cursor.moveToNext())
            if(!deleteSupplier(cursor.getInt(0)))
                return false;
        return true;
    }

    public void deleteProduct(int p_id)
    {
        File file = new File(db.getImgPathById(p_id));
        file.delete();
        db.deleteProduct(p_id);
    }

    public void deleteAllProducts() {
        ArrayList<String> imgPaths = getAllImgPath();
        File file;
        for (String img : imgPaths) {
            file = new File(img);
            file.delete();
        }
        db.deleteAllProducts();

    }

    public ArrayList<String> getShopProductsList()//return list for listview
    {
        ArrayList<String> listdata = new ArrayList<>();
        Cursor data = db.getProductsTable();
        while(data.moveToNext())
            listdata.add("Product_id: "+data.getInt(0)+"\nName: "+data.getString(2)+"\nPrice: "+data.getInt(3)+"\nQuantity: " +data.getInt(4));
        return listdata;
    }

    public Product getProductById(int p_id)
    {
        return db.getProductById(p_id);
    }

    public void afterPurchaseUpdate(Cart cart)
    {
        ArrayList<Product> products = cart.getProducts();
        for(Product product: products)
        {
            int prequantity = db.getProductById(product.getP_id()).getQuantity();
            db.deleteProduct(product.getP_id());
            db.addProduct(product.getS_id(),product.getName(),product.getPrice(),prequantity-product.getQuantity(),product.getImage());
        }
    }

    public Supplier getSupplierById(int s_id)
    {
        return db.getSupplierById(s_id);
    }

    public void afterOrderUpdate(int p_id,int quantity)
    {
        Product product = db.getProductById(p_id);
        db.deleteProduct(p_id);
        db.addProduct(product.getS_id(),product.getName(),product.getPrice(),product.getQuantity()+quantity,product.getImage());
    }
}
