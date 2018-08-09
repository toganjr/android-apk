package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class NcrRegistration implements Serializable {
    private Integer id;
    @SerializedName("no_reg_ncr")
    @Expose
    private String noRegNcr;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("process_name")
    @Expose
    private String processName;
    @SerializedName("reference_inspection")
    @Expose
    private String referenceInspection;
    @SerializedName("person_in_charge")
    @Expose
    private String personInCharge;
    @SerializedName("incompatibility_category_id")
    @Expose
    private Integer incompatibilityCategoryId;
    @SerializedName("description_incompatibility")
    @Expose
    private String descriptionIncompatibility;
    @SerializedName("product_identity_id")
    @Expose
    private Integer productIdentityId;
    @SerializedName("master_project_id")
    @Expose
    private Integer masterProjectId;
    @SerializedName("division_id")
    @Expose
    private Integer divisionId;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("master_product_id")
    @Expose
    private Integer masterProductId;
    @SerializedName("disposition_inspector_id")
    @Expose
    private Integer dispositionInspectorId;
    @SerializedName("ui_code_id")
    @Expose
    private Integer uiCodeId;
    @SerializedName("is_response")
    @Expose
    private Integer isResponse;
    @SerializedName("is_ver_inspector")
    @Expose
    private Integer isVerInspector;
    @SerializedName("is_ver_auditor")
    @Expose
    private Integer isVerAuditor;
    @SerializedName("publish_date")
    @Expose
    private Date publishDate;
    @SerializedName("lat")
    @Expose
    private Double latitude;
    @SerializedName("long")
    @Expose
    private Double longitude;
    @SerializedName("completion_target")
    @Expose
    private Date completionTarget;
    @SerializedName("created_at")
    @Expose
    private Date createdAt;
    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Date deletedAt;
    @SerializedName("is_cancel")
    @Expose
    private Integer isCancel;
    @SerializedName("id_pic_respon")
    @Expose
    private Integer idPicRespon;
    @SerializedName("doc_reference_id")
    @Expose
    private Integer doc_reference_id;
    private Division division;
    private User user;
    private Project project;
    private Product product;

    public Integer getId() {
        return id;
    }

    public String getNoRegNcr() {
        return noRegNcr;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getProcessName() {
        return processName;
    }

    public String getReferenceInspection() {
        return referenceInspection;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public Integer getIncompatibilityCategoryId() {
        return incompatibilityCategoryId;
    }

    public String getDescriptionIncompatibility() {
        return descriptionIncompatibility;
    }

    public Integer getProductIdentityId() {
        return productIdentityId;
    }

    public Integer getMasterProjectId() {
        return masterProjectId;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public Integer getMasterProductId() {
        return masterProductId;
    }

    public Integer getDispositionInspectorId() {
        return dispositionInspectorId;
    }

    public Integer getUiCodeId() {
        return uiCodeId;
    }

    public Integer getIsResponse() {
        return isResponse;
    }

    public Integer getIsVerInspector() {
        return isVerInspector;
    }

    public Integer getIsVerAuditor() {
        return isVerAuditor;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Date getCompletionTarget() {
        return completionTarget;
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

    public Integer getIsCancel() {
        return isCancel;
    }

    public Integer getIdPicRespon() {
        return idPicRespon;
    }

    public Integer getDoc_reference_id() {
        return doc_reference_id;
    }

    public Division getDivision() {
        return division;
    }

    public User getUser() {
        return user;
    }

    public Project getProject() {
        return project;
    }

    public Product getProduct() {
        return product;
    }
}
