package com.example.user.navbartemplatejava.data;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Userinspector implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("inspector_number")
    @Expose
    private String inspectorNumber;
    @SerializedName("inspector_group_id")
    @Expose
    private Integer inspectorGroupId;
    @SerializedName("pekerjaan")
    @Expose
    private String pekerjaan;
    @SerializedName("kompetensi")
    @Expose
    private String kompetensi;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getInspectorNumber() {
        return inspectorNumber;
    }

    public void setInspectorNumber(String inspectorNumber) {
        this.inspectorNumber = inspectorNumber;
    }

    public Integer getInspectorGroupId() {
        return inspectorGroupId;
    }

    public void setInspectorGroupId(Integer inspectorGroupId) {
        this.inspectorGroupId = inspectorGroupId;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getKompetensi() {
        return kompetensi;
    }

    public void setKompetensi(String kompetensi) {
        this.kompetensi = kompetensi;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

}