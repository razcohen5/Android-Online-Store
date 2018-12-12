package com.example.yakirlaptop.ssproject.ObjectClasses;

public class Customer extends User {
    private String email;
    private String creditcard;
    private Cart cart;
    public Customer(String username,String password,int admin,String name,String email,String creditcard)
    {
        super(username,password,admin,name);
        this.email = email;
        this.creditcard = creditcard;
        cart = new Cart();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
