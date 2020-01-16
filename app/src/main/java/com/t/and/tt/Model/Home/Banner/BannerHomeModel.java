
package com.t.and.tt.Model.Home.Banner;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerHomeModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("content")
    @Expose
    private List<ContentHomeBanner> content = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ContentHomeBanner> getContent() {
        return content;
    }

    public void setContent(List<ContentHomeBanner> content) {
        this.content = content;
    }

}
