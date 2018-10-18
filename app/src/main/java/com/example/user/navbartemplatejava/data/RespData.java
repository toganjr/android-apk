package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RespData implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("ncr_id")
    @Expose
    private Integer ncrId;
    @SerializedName("disp_unit_id")
    @Expose
    private Integer dispUnitId;
    @SerializedName("mrb_id")
    @Expose
    private Object mrbId;
    @SerializedName("problem_description")
    @Expose
    private String problemDescription;
    @SerializedName("corrective_act")
    @Expose
    private String correctiveAct;
    @SerializedName("corrective_est_date")
    @Expose
    private String correctiveEstDate;
    @SerializedName("preventive_act")
    @Expose
    private String preventiveAct;
    @SerializedName("preventive_est_date")
    @Expose
    private String preventiveEstDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("unit_disposition")
    @Expose
    private UnitDisposition unitDisposition;
    @SerializedName("mrb_disposition")
    @Expose
    private Object mrbDisposition;
    @SerializedName("ncr_registration")
    @Expose
    private NcrRegistration ncrRegistration;

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

    public Integer getNcrId() {
        return ncrId;
    }

    public void setNcrId(Integer ncrId) {
        this.ncrId = ncrId;
    }

    public Integer getDispUnitId() {
        return dispUnitId;
    }

    public void setDispUnitId(Integer dispUnitId) {
        this.dispUnitId = dispUnitId;
    }

    public Object getMrbId() {
        return mrbId;
    }

    public void setMrbId(Object mrbId) {
        this.mrbId = mrbId;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getCorrectiveAct() {
        return correctiveAct;
    }

    public void setCorrectiveAct(String correctiveAct) {
        this.correctiveAct = correctiveAct;
    }

    public String getCorrectiveEstDate() {
        return correctiveEstDate;
    }

    public void setCorrectiveEstDate(String correctiveEstDate) {
        this.correctiveEstDate = correctiveEstDate;
    }

    public String getPreventiveAct() {
        return preventiveAct;
    }

    public void setPreventiveAct(String preventiveAct) {
        this.preventiveAct = preventiveAct;
    }

    public String getPreventiveEstDate() {
        return preventiveEstDate;
    }

    public void setPreventiveEstDate(String preventiveEstDate) {
        this.preventiveEstDate = preventiveEstDate;
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

    public UnitDisposition getUnitDisposition() {
        return unitDisposition;
    }

    public void setUnitDisposition(UnitDisposition unitDisposition) {
        this.unitDisposition = unitDisposition;
    }

    public Object getMrbDisposition() {
        return mrbDisposition;
    }

    public void setMrbDisposition(Object mrbDisposition) {
        this.mrbDisposition = mrbDisposition;
    }

    public NcrRegistration getNcrRegistration() {
        return ncrRegistration;
    }

    public void setNcrRegistration(NcrRegistration ncrRegistration) {
        this.ncrRegistration = ncrRegistration;
    }

}