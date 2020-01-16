
package com.t.and.tt.Model.SearchBusiness;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessImage {

    @SerializedName("business_id")
    @Expose
    private Integer businessId;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
