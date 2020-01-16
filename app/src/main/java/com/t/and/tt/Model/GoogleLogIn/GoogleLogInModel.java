
package com.t.and.tt.Model.GoogleLogIn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoogleLogInModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("content")
    @Expose
    private ContentGoogle content;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ContentGoogle getContent() {
        return content;
    }

    public void setContent(ContentGoogle content) {
        this.content = content;
    }

}
