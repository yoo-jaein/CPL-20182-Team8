package com.example.immmy.myapplication.Data;

import java.util.ArrayList;

public class Customer {
    private String nickname;
    private String customer_id;
    private String name;

    public Customer(String nickname, String customer_id, String name) {
        this.nickname = nickname;
        this.customer_id = customer_id;
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getName() {
        return name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setName(String name) {
        this.name = name;
    }



}
