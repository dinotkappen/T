
package com.t.and.tt.Model.Home.Services;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeServicesModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("content")
    @Expose
    private List<ContentHomeServiceModel> content = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ContentHomeServiceModel> getContent() {
        return content;
    }

    public void setContent(List<ContentHomeServiceModel> content) {
        this.content = content;
    }

}
