package com.example.user.navbartemplatejava.data.network.response;

import com.example.user.navbartemplatejava.data.DispositionInspector;
import com.example.user.navbartemplatejava.data.DocRefDivision;
import com.example.user.navbartemplatejava.data.IncompatibilityCategory;
import com.example.user.navbartemplatejava.data.MasterProject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddFormResponse {
    @SerializedName("list_project")
    @Expose
    private List<MasterProject> masterProjects;
    @SerializedName("incompatibility_categories")
    @Expose
    private List<IncompatibilityCategory> categories;
    @SerializedName("disposisi_inspector")
    @Expose
    private List<DispositionInspector> dispositions;
    @SerializedName("doc_reference_division")
    @Expose
    private List<DocRefDivision> docDivisions;

    public List<MasterProject> getMasterProjects() {
        return masterProjects;
    }

    public List<IncompatibilityCategory> getCategories() {
        return categories;
    }

    public List<DispositionInspector> getDispositions() {
        return dispositions;
    }

    public List<DocRefDivision> getDocDivisions() {
        return docDivisions;
    }
}
