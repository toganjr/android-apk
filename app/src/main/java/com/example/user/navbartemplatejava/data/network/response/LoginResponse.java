package com.example.user.navbartemplatejava.data.network.response;

import com.example.user.navbartemplatejava.data.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("accessToken")
    @Expose
    private String token;

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
