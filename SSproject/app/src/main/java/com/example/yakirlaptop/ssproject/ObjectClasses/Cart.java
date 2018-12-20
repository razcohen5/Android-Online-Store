package com.example.yakirlaptop.ssproject.ObjectClasses;

import com.example.yakirlaptop.ssproject.Singletons.Server;
import com.example.yakirlaptop.ssproject.Singletons.UserHolder;

import java.util.ArrayList;
import java.util.Date;

public class Cart {

    private ArrayList<Product> products;
    private Date date;
    private int totalprice;

    public Cart()
    {
        products = new ArrayList<>();
        date = new Date();
        totalprice = 0;
    }

    public void calculateTotalPrice()
    {
        totalprice = 0;
        for(Product p: products)
            totalprice+=p.getQuantity()*p.getPrice();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean Contains(int p_id)
    {
        for(Product p: products)
            if(p.getP_id()==p_id)
                return true;
        return false;
    }

    public boolean isEmpty()
    {
        return products.isEmpty();
    }

    public void emptyCart() { products.removeAll(products);}

    public void deleteById(int p_id){
        for (Product p:products){
            if (p.getP_id() == p_id){
                products.remove(p);
                break;
            }
        }
    }
}
