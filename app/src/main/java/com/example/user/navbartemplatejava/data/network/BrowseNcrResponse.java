package com.example.user.navbartemplatejava.data.network;

import com.example.user.navbartemplatejava.data.NcrRegistration;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BrowseNcrResponse {
    @SerializedName("ncr")
    @Expose
    private List<NcrRegistration> ncr = null;
    @SerializedName("inspector_access")
    @Expose
    private Boolean inspectorAccess;
    public List<NcrRegistration> getNcr() {
        return ncr;
    }

    public Boolean getInspectorAccess() {
        return inspectorAccess;
    }
}
