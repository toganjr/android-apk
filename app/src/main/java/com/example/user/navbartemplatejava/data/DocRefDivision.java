package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DocRefDivision implements Serializable{
    private Integer id;
    @SerializedName("doc_number_head")
    @Expose
    private String docNumberHead;
    @SerializedName("unit_id")
    @Expose
    private Integer unitId;
    private String description;

    public Integer getId() {
        return id;
    }

    public String getDocNumberHead() {
        return docNumberHead;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return docNumberHead;
    }
}
