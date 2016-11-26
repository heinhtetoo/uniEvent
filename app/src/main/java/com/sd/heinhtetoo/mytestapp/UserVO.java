package com.sd.heinhtetoo.mytestapp;

/**
 * Created by Administrator's user on 25-Nov-16.
 */

public class UserVO {
    private final String username;
    private final String email;
    private final String profile;

    public UserVO(String username, String email, String profile) {
        this.username = username;
        this.email = email;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getProfile() {
        return profile;
    }

}
