
package com.t.and.tt.Model.LogIn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("content")
    @Expose
    private ContentLogInModel content;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ContentLogInModel getContent() {
        return content;
    }

    public void setContent(ContentLogInModel content) {
        this.content = content;
    }

}
