package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DispositionInspector implements Serializable {
    private Integer id;
    @SerializedName("disposisi_description")
    @Expose
    private String disposisiDescription;

    public Integer getId() {
        return id;
    }

    public String getDisposisiDescription() {
        return disposisiDescription;
    }
}
