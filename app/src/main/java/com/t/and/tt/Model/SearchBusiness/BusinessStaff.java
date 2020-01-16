
package com.t.and.tt.Model.SearchBusiness;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessStaff {

    @SerializedName("business_id")
    @Expose
    private Integer businessId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("staff_name")
    @Expose
    private String staffName;
    @SerializedName("staff_name_ar")
    @Expose
    private String staffNameAr;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffNameAr() {
        return staffNameAr;
    }

    public void setStaffNameAr(String staffNameAr) {
        this.staffNameAr = staffNameAr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
