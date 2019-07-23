package com.strontech.imgautam.handycaft.model;

public class UserInfo {

    private String user_id;
    private String user_name;
    private String user_email;
    private String user_profile_pic;

    public UserInfo() {
    }

    public UserInfo(String user_id, String user_name, String user_email, String user_profile_pic) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_profile_pic = user_profile_pic;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_profile_pic() {
        return user_profile_pic;
    }

    public void setUser_profile_pic(String user_profile_pic) {
        this.user_profile_pic = user_profile_pic;
    }
}

