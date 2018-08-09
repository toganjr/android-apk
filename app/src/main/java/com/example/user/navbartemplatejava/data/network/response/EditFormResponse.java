package com.example.user.navbartemplatejava.data.network.response;

import com.example.user.navbartemplatejava.data.Division;
import com.example.user.navbartemplatejava.data.MasterProject;
import com.example.user.navbartemplatejava.data.NcrRegistration;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditFormResponse {
    @SerializedName("list_project")
    @Expose
    private List<MasterProject> listProjects;
    @SerializedName("ui_code")
    @Expose
    private String uiCode;
    @SerializedName("ncr_reg")
    @Expose
    private NcrRegistration ncr;
    @SerializedName("list_unit")
    @Expose
    private List<Division> listUnit;

    public List<MasterProject> getListProjects() {
        return listProjects;
    }

    public String getUiCode() {
        return uiCode;
    }

    public NcrRegistration getNcr() {
        return ncr;
    }

    public List<Division> getListUnit() {
        return listUnit;
    }
}
