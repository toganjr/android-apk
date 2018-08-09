package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MasterProject implements Serializable{
    @SerializedName("id_project")
    @Expose
    private Integer idProject;
    private String label;

    public Integer getIdProject() {
        return idProject;
    }

    public String getLabel() {
        return label;
    }
}
