package com.example.user.navbartemplatejava.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerNcr {
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }
}
