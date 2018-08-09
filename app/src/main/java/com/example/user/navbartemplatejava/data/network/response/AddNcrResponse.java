package com.example.user.navbartemplatejava.data.network.response;

import com.example.user.navbartemplatejava.data.NcrRegistration;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNcrResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ncr")
    @Expose
    private NcrRegistration ncr;

    public String getMessage() {
        return message;
    }

    public NcrRegistration getNcr() {
        return ncr;
    }
}
