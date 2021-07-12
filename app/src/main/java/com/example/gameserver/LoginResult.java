package com.example.gameserver;

import com.google.gson.annotations.SerializedName;

public class LoginResult {

    private String nickName;

    private String email;

    public String getName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }
}