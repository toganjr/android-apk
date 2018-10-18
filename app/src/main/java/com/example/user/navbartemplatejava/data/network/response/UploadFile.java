package com.example.user.navbartemplatejava.data.network.response;

import com.example.user.navbartemplatejava.data.NcrRegistration;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadFile {
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }
}
