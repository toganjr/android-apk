package com.example.user.navbartemplatejava.data.network.response;

import com.example.user.navbartemplatejava.data.ListResults;
import com.example.user.navbartemplatejava.data.NcrRegistration;
import com.example.user.navbartemplatejava.data.NcrVerification;
import com.example.user.navbartemplatejava.data.RespData;
import com.example.user.navbartemplatejava.data.Userinspector;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NCRVerification implements Serializable{

    @SerializedName("ncr_data")
    @Expose
    private NcrVerification ncrData;
    @SerializedName("userinspector")
    @Expose
    private Userinspector userinspector;
    @SerializedName("ncr_id")
    @Expose
    private Integer ncrId;
    @SerializedName("ncr_no")
    @Expose
    private String ncrNo;
    @SerializedName("ncr_incompatibility_id")
    @Expose
    private Integer ncrIncompatibilityId;
    @SerializedName("list_results")
    @Expose
    private ListResults listResults;
    @SerializedName("resp_data")
    @Expose
    private RespData respData;
    @SerializedName("resp_problem")
    @Expose
    private List<Object> respProblem = null;

    public NcrVerification getNcrData() {
        return ncrData;
    }
    public Userinspector getUserinspector() {
        return userinspector;
    }

    public Integer getNcrId() {
        return ncrId;
    }

    public String getNcrNo() {
        return ncrNo;
    }

    public Integer getNcrIncompatibilityId() {
        return ncrIncompatibilityId;
    }

    public ListResults getListResults() {
        return listResults;
    }

    public RespData getRespData() {
        return respData;
    }

    public List<Object> getRespProblem() {
        return respProblem;
    }


}
