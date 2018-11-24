package com.example.onthejourney.Data;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class CheckList {
    private String customer_id=null;
    private String buddy_id = null;
    private String state = null;
    private Date start_time = null;
    private Date end_time = null;
    private String location = null;
    private int people_number = 0;
    private ArrayList<String> requirement_list = null;
    private int suggested_price = 0;



    public CheckList(){

    }
    public CheckList(String customer_id, String buddy_id, String state, Date start_time, Date end_time, String location, int people_number, ArrayList<String> requirement_list, int suggested_price) {
        this.customer_id = customer_id;
        this.buddy_id = buddy_id;
        this.state = state;
        this.start_time = start_time;
        this.end_time = end_time;
        this.location = location;
        this.people_number = people_number;
        this.requirement_list = requirement_list;
        this.suggested_price = suggested_price;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getBuddy_id() {
        return buddy_id;
    }

    public void setBuddy_id(String buddy_id) {
        this.buddy_id = buddy_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPeople_number() {
        return people_number;
    }

    public void setPeople_number(int people_number) {
        this.people_number = people_number;
    }

    public ArrayList<String> getRequirement_list() {
        return requirement_list;
    }

    public void setRequirement_list(ArrayList<String> requirement_list) {
        this.requirement_list = requirement_list;
    }

    public int getSuggested_price() {
        return suggested_price;
    }

    public void setSuggested_price(int suggested_price) {
        this.suggested_price = suggested_price;
    }

    public JSONObject getJsonObject(String customer_id, String buddy_id, String state, Date start_time, Date end_time, LatLng location, int people_number, int suggested_price, JSONArray array){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("customer_id", customer_id);
            jsonObject.put("buddy_id", buddy_id);
            jsonObject.put("state", state);
            jsonObject.put("start_time", start_time);
            jsonObject.put("end_time", end_time);
            jsonObject.put("location",location);
            jsonObject.put("people_number", people_number);
            jsonObject.put("suggested_price", suggested_price);
            jsonObject.put("requirement_list", array);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject getJsonObject(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("customer_id", customer_id);
            jsonObject.put("buddy_id", buddy_id);
            jsonObject.put("state", state);
            jsonObject.put("start_time", start_time);
            jsonObject.put("end_time", end_time);
            jsonObject.put("location",location);
            jsonObject.put("people_number", people_number);
            jsonObject.put("suggested_price", suggested_price);
            jsonObject.put("requirement_list", requirement_list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public String toString() {
        return "CheckList{" +
                "customer_id='" + customer_id + '\'' +
                ", buddy_id='" + buddy_id + '\'' +
                ", state='" + state + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", location=" + location +
                ", people_number=" + people_number +
                ", requirement_list=" + requirement_list +
                ", suggested_price=" + suggested_price +
                '}';
    }
}
