package com.example.onthejourney.Data;

public class Feed {
    private String buddy_id;
    private String image_path;
    private String favorite_num;
    private String picture_taken_location;
    private String hashtag;

    public Feed(String buddy_id, String image_path, String favorite_num, String picture_taken_location, String hashtag) {
        this.buddy_id = buddy_id;
        this.image_path = image_path;
        this.favorite_num = favorite_num;
        this.picture_taken_location = picture_taken_location;
        this.hashtag = hashtag;
    }

    public String getBuddy_id() { return buddy_id; }

    public String getImage_path() { return image_path; }

    public String getFavorite_num() { return favorite_num; }

    public String getPicture_taken_location() { return picture_taken_location; }

    public String getHashtag() { return hashtag; }

    public void setBuddy_id(String buddy_id) { this.buddy_id = buddy_id; }

    public void setImage_path(String image_path) { this.image_path = image_path; }

    public void setFavorite_num(String favorite_num) { this.favorite_num = favorite_num; }

    public void setPicture_taken_location(String picture_taken_location) { this.picture_taken_location = picture_taken_location; }

    public void setHashtag(String hashtag) { this.hashtag = hashtag; }

}