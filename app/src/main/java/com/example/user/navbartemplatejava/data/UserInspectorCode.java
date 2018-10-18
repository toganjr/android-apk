package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserInspectorCode implements Serializable {

    @SerializedName("ui_code")
    @Expose
    private String ui_code;

    public String getUi_code() { return ui_code;}
}
