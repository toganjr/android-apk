package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {
    private Integer id;
    @SerializedName("project_code")
            @Expose
    private String projectCode;
    @SerializedName("project_description")
    @Expose
    private String projectDescription;
    @SerializedName("is_close")
    @Expose
    private Integer isClose;
    @SerializedName("created_at")
    @Expose
    private Date createdAt;
    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Date deletedAt;

    public Integer getId() {
        return id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public Integer getIsClose() {
        return isClose;
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
}
