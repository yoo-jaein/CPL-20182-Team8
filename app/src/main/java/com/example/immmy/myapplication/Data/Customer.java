package com.example.immmy.myapplication.Data;

import java.util.ArrayList;

public class Customer {
    private String nickname;
    private String customer_id;
    private String name;
    private ArrayList<String> favorite_feed_id_list;
    private ArrayList<String> favorite_buddy_id_list;

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

    public ArrayList<String> getFavorite_feed_id_list() {
        return favorite_feed_id_list;
    }

    public ArrayList<String> getFavorite_buddy_id_list() {
        return favorite_buddy_id_list;
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

    public void setFavorite_feed_id_list(ArrayList<String> favorite_feed_id_list) {
        this.favorite_feed_id_list = favorite_feed_id_list;
    }

    public void setFavorite_buddy_id_list(ArrayList<String> favorite_buddy_id_list) {
        this.favorite_buddy_id_list = favorite_buddy_id_list;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "nickname='" + nickname + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", name='" + name + '\'' +
                ", favorite_feed_id_list=" + favorite_feed_id_list +
                ", favorite_buddy_id_list=" + favorite_buddy_id_list +
                '}';
    }
}
