package com.example.user.navbartemplatejava.data.network.response;

import com.example.user.navbartemplatejava.data.User;
import com.example.user.navbartemplatejava.data.UserInspectorCode;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("accessToken")
    @Expose
    private String token;

    @SerializedName("userInspectorCode")
    @Expose
    private UserInspectorCode userInspectorCode;

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public UserInspectorCode getUserInspectorCode() { return userInspectorCode; }
}
