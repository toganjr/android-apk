package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class NcrRegistration implements Serializable {
    Integer id;
    String no_reg_ncr;
    Integer user_id;
    String process_name;
    String reference_inspection;
    String person_in_charge;
    Integer incompatibility_category_id;
    String description_incompatibility;
    Integer product_identity_id;
    Integer master_project_id;
    Integer division_id;
    String vendor_name;
    Integer master_product_id;
    Integer disposition_inspector_id;
    Integer ui_code_id;
    Integer is_response;
    Integer is_ver_inspector;
    Integer is_ver_auditor;
    Date publish_date;
    @SerializedName("lat")
    Double latitude;
    @SerializedName("long")
    Double longitude;
    Date completion_target;
    Date created_at;
    Date updated_at;
    Date deleted_at;
    Integer is_cancel;
    Integer id_pic_respon;
    Integer doc_reference_id;
    Division division;
    User user;
    Project project;
    Product product;
}
