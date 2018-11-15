package comaa.example.hanhyeonmin.trans;

import java.io.Serializable;
import java.util.ArrayList;

public class Buddy implements Serializable {
    private String buddy_id;
    private String buddy_pwd;
    private String name;
    private ArrayList<String> price_list;

    public Buddy(String buddy_id, String buddy_pw, String name, ArrayList<String> price_list) {
        this.buddy_id = buddy_id;
        this.buddy_pwd = buddy_pw;
        this.name = name;
        this.price_list = price_list;
    }

    public String getBuddy_id() {
        return buddy_id;
    }

    public void setBuddy_id(String buddy_id) {
        this.buddy_id = buddy_id;
    }

    public String getBuddy_pwd() {
        return buddy_pwd;
    }

    public void setBuddy_pwd(String buddy_pw) {
        this.buddy_pwd = buddy_pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPrice_list() {
        return price_list;
    }

    public void setPrice_list(ArrayList<String> price_list) {
        this.price_list = price_list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("feedItem{");
        sb.append("buddy_id='").append(buddy_id).append('\'');
        sb.append(", buddy_pwd='").append(buddy_pwd).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", price_list='").append(price_list).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
