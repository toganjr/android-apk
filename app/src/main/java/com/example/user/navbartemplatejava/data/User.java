package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private Integer id;
    private String name;
    private String nip;
    private String email;
    private String photo;
    @SerializedName("jabatan_id")
    @Expose
    private Integer jabatanId;
    @SerializedName("divisi_id")
    @Expose
    private Integer divisiId;
    @SerializedName("created_at")
    @Expose
    private Date createdAt;
    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNip() {
        return nip;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public Integer getJabatanId() {
        return jabatanId;
    }

    public Integer getDivisiId() {
        return divisiId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
