package com.example.user.navbartemplatejava.data.network.response;

        import com.example.user.navbartemplatejava.data.DispositionInspector;
        import com.example.user.navbartemplatejava.data.VerificationResult;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.io.Serializable;
        import java.util.List;

public class AddInspecFormResponse implements Serializable {
    @SerializedName("verificationresult")
    @Expose
    private List<VerificationResult> verificationresult;

    public List<VerificationResult> getVerificationresult() {
        return verificationresult;
    }
}
