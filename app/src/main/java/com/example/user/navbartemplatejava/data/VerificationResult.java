package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VerificationResult implements Serializable {
    private Integer id;
    private String description;

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
