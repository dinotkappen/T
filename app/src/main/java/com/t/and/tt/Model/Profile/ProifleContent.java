
package com.t.and.tt.Model.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProifleContent {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("street_name")
    @Expose
    private Object streetName;
    @SerializedName("street_number")
    @Expose
    private Object streetNumber;
    @SerializedName("zone_number")
    @Expose
    private Object zoneNumber;
    @SerializedName("building_number")
    @Expose
    private Object buildingNumber;
    @SerializedName("location")
    @Expose
    private Object location;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getStreetName() {
        return streetName;
    }

    public void setStreetName(Object streetName) {
        this.streetName = streetName;
    }

    public Object getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Object streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Object getZoneNumber() {
        return zoneNumber;
    }

    public void setZoneNumber(Object zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    public Object getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Object buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

}
