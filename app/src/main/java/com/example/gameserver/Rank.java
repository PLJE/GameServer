package com.example.gameserver;

import com.google.gson.annotations.SerializedName;

public class Rank {
    private String score;
    private String email;
    private String password;
    private String nickName;
    private String _id;
    private String __v;

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }

    public String getScore() {
        return score;
    }
}
