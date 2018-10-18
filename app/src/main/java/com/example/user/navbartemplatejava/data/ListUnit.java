package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListUnit implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer idUnit;
    private String division_name;

    public Integer getIdProject() {
        return idUnit;
    }

    public String getLabel() {
        return division_name;
    }

    @Override
    public String toString() {
        return division_name;
    }

}
