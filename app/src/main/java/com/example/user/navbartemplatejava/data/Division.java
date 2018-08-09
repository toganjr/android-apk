package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Division implements Serializable {
    private Integer id;
    @SerializedName("division_name")
    @Expose
    private String divisionName;
    private Integer parent;
    @SerializedName("created_at")
    @Expose
    private Date createdAt;
    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Date deletedAt;
    @SerializedName("kadiv_nip")
    @Expose
    private String kadivNip;

    public Integer getId() {
        return id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public Integer getParent() {
        return parent;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public String getKadivNip() {
        return kadivNip;
    }
}
