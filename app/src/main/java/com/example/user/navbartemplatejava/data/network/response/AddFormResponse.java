package com.example.user.navbartemplatejava.data.network.response;

import com.example.user.navbartemplatejava.data.DispositionInspector;
import com.example.user.navbartemplatejava.data.DocRefDivision;
import com.example.user.navbartemplatejava.data.IncompatibilityCategory;
import com.example.user.navbartemplatejava.data.ListUnit;
import com.example.user.navbartemplatejava.data.MasterProject;
import com.example.user.navbartemplatejava.data.UserInspectorCode;
import com.example.user.navbartemplatejava.data.uiCode;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AddFormResponse implements Serializable{
    @SerializedName("list_project")
    @Expose
    private List<MasterProject> masterProjects;
    @SerializedName("list_unit")
    @Expose
    private List<ListUnit> units;
    @SerializedName("incompatibility_categories")
    @Expose
    private List<IncompatibilityCategory> categories;
    @SerializedName("disposisi_inspector")
    @Expose
    private List<DispositionInspector> dispositions;
    @SerializedName("doc_reference_division")
    @Expose
    private List<DocRefDivision> docDivisions;
    @SerializedName("ui_code")
    @Expose
    private UserInspectorCode ui_codes;

    public List<MasterProject> getMasterProjects() {
        return masterProjects;
    }

    public List<ListUnit> getUnits(){  return units; }

    public List<IncompatibilityCategory> getCategories() {
        return categories;
    }

    public List<DispositionInspector> getDispositions() {
        return dispositions;
    }

    public List<DocRefDivision> getDocDivisions() {
        return docDivisions;
    }

    public UserInspectorCode getUi_codes() { return ui_codes;}
}
