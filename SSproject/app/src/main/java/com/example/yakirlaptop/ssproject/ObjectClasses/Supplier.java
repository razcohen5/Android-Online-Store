package com.example.yakirlaptop.ssproject.ObjectClasses;

public class Supplier {
    private int s_id;
    private String name;
    private String phone;
    private String email;
    private String company;


    public Supplier(String name, String phone, String email, String company) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.company = company;
    }

    public Supplier(int s_id, String name, String phone, String email, String company) {
        this.s_id = s_id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.company = company;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
