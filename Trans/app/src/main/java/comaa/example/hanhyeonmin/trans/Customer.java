package comaa.example.hanhyeonmin.trans;

import java.util.ArrayList;

public class Customer {
    private String customer_id;
    private String pw;
    private String name;
    private String nickname;
    private ArrayList<String> favorite_buddy_id_list;
    private ArrayList<String> favorite_feed_item_id_list;

    public String getCustomer_id() {
        return customer_id;
    }

    public Customer(String customer_id, String pw, String name, String nickname, ArrayList<String> favorite_buddy_id_list, ArrayList<String> favorite_feed_item_id_list) {
        this.customer_id = customer_id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.favorite_buddy_id_list = favorite_buddy_id_list;
        this.favorite_feed_item_id_list = favorite_feed_item_id_list;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;

    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ArrayList<String> getFavorite_buddy_id_list() {
        return favorite_buddy_id_list;
    }

    public void setFavorite_buddy_id_list(ArrayList<String> favorite_buddy_id_list) {
        this.favorite_buddy_id_list = favorite_buddy_id_list;
    }

    public ArrayList<String> getFavorite_feed_item_id_list() {
        return favorite_feed_item_id_list;
    }

    public void setFavorite_feed_item_id_list(ArrayList<String> favorite_feed_item_id_list) {
        this.favorite_feed_item_id_list = favorite_feed_item_id_list;
    }
}
