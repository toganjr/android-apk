package com.example.user.navbartemplatejava.data.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateNcrRequest implements Serializable {
    @SerializedName("incompatibility_category_id")
    @Expose
    private Integer incompatiblityCategoryId;
    @SerializedName("master_project_id")
    @Expose
    private Integer masterProjectId;
    @SerializedName("disposition_inspector_id")
    @Expose
    private Integer dispositionInspectorId;
    @SerializedName("division_id")
    @Expose
    private Integer divisionId;
    @SerializedName("process_name")
    @Expose
    private String processName;
    @SerializedName("description_incompatibility")
    @Expose
    private String descriptionIncompatibility;
    @SerializedName("lat")
    @Expose
    private Double latitude;
    @SerializedName("long")
    @Expose
    private Double longitude;
    @SerializedName("acuan_id")
    @Expose
    private Integer acuanId;
    @SerializedName("acuan_po")
    @Expose
    private String acuanPo;
    @SerializedName("person_in_charge")
    @Expose
    private String personInCharge;

    public UpdateNcrRequest(Integer incompatiblityCategoryId,
                            Integer masterProjectId,
                            Integer dispositionInspectorId,
                            Integer divisionId,
                            String processName,
                            String descriptionIncompatibility,
                            Double latitude,
                            Double longitude,
                            Integer acuanId,
                            String acuanPo,
                            String personInCharge) {
        this.incompatiblityCategoryId = incompatiblityCategoryId;
        this.masterProjectId = masterProjectId;
        this.dispositionInspectorId = dispositionInspectorId;
        this.divisionId = divisionId;
        this.processName = processName;
        this.descriptionIncompatibility = descriptionIncompatibility;
        this.latitude = latitude;
        this.longitude = longitude;
        this.acuanId = acuanId;
        this.acuanPo = acuanPo;
        this.personInCharge = personInCharge;
    }
    public static class Builder {
        private Integer incompatiblityCategoryId;
        private Integer masterProjectId;
        private Integer dispositionInspectorId;
        private Integer divisionId;
        private String processName;
        private String descriptionIncompatibility;
        private Double latitude;
        private Double longitude;
        private Integer acuanId;
        private String acuanPo;
        private String personInCharge;

        public Builder setIncompatiblityCategoryId(Integer incompatiblityCategoryId) {
            this.incompatiblityCategoryId = incompatiblityCategoryId;
            return this;
        }

        public Builder setMasterProjectId(Integer masterProjectId) {
            this.masterProjectId = masterProjectId;
            return this;
        }

        public Builder setDispositionInspectorId(Integer dispositionInspectorId) {
            this.dispositionInspectorId = dispositionInspectorId;
            return this;
        }

        public Builder setDivisionId(Integer divisionId) {
            this.divisionId = divisionId;
            return this;
        }

        public Builder setProcessName(String processName) {
            this.processName = processName;
            return this;
        }

        public Builder setDescriptionIncompatibility(String descriptionIncompatibility) {
            this.descriptionIncompatibility = descriptionIncompatibility;
            return this;
        }

        public Builder setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setAcuanId(Integer acuanId) {
            this.acuanId = acuanId;
            return this;
        }

        public Builder setAcuanPo(String acuanPo) {
            this.acuanPo = acuanPo;
            return this;
        }

        public Builder setPersonInCharge(String personInCharge) {
            this.personInCharge = personInCharge;
            return this;
        }

        public UpdateNcrRequest build() {
            return new UpdateNcrRequest(incompatiblityCategoryId, masterProjectId, dispositionInspectorId, divisionId, processName, descriptionIncompatibility, latitude, longitude, acuanId, acuanPo, personInCharge);
        }
    }
}
