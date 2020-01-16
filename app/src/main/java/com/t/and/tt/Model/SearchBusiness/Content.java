
package com.t.and.tt.Model.SearchBusiness;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_ar")
    @Expose
    private String nameAr;
    @SerializedName("business_name")
    @Expose
    private String businessName;
    @SerializedName("business_name_ar")
    @Expose
    private String businessNameAr;
    @SerializedName("business_type")
    @Expose
    private Integer businessType;
    @SerializedName("specialization")
    @Expose
    private String specialization;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("contact_no")
    @Expose
    private String contactNo;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_ip")
    @Expose
    private Integer createdIp;
    @SerializedName("modified_ip")
    @Expose
    private Integer modifiedIp;
    @SerializedName("service_ids")
    @Expose
    private String serviceIds;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("bio_ar")
    @Expose
    private String bioAr;
    @SerializedName("street_name")
    @Expose
    private String streetName;
    @SerializedName("street_name_ar")
    @Expose
    private String streetNameAr;
    @SerializedName("street_number")
    @Expose
    private String streetNumber;
    @SerializedName("zone_number")
    @Expose
    private String zoneNumber;
    @SerializedName("building_number")
    @Expose
    private String buildingNumber;
    @SerializedName("minimum_price")
    @Expose
    private String minimumPrice;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("isfavourite")
    @Expose
    private String isfavourite;
    @SerializedName("locations")
    @Expose
    private String locations;
    @SerializedName("locations_ar")
    @Expose
    private String locationsAr;
    @SerializedName("businessservices")
    @Expose
    private List<Businessservice> businessservices = null;
    @SerializedName("business_categories")
    @Expose
    private BusinessCategories businessCategories;
    @SerializedName("business_services")
    @Expose
    private List<Business_Service> businessServices = null;
    @SerializedName("business_time")
    @Expose
    private List<BusinessTime> businessTime = null;
    @SerializedName("business_images")
    @Expose
    private List<BusinessImage> businessImages = null;
    @SerializedName("business_staff")
    @Expose
    private List<BusinessStaff> businessStaff = null;
    @SerializedName("coupons")
    @Expose
    private List<Coupon> coupons = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessNameAr() {
        return businessNameAr;
    }

    public void setBusinessNameAr(String businessNameAr) {
        this.businessNameAr = businessNameAr;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCreatedIp() {
        return createdIp;
    }

    public void setCreatedIp(Integer createdIp) {
        this.createdIp = createdIp;
    }

    public Integer getModifiedIp() {
        return modifiedIp;
    }

    public void setModifiedIp(Integer modifiedIp) {
        this.modifiedIp = modifiedIp;
    }

    public String getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String serviceIds) {
        this.serviceIds = serviceIds;
    }
//
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
//
    public String getBioAr() {
        return bioAr;
    }

    public void setBioAr(String bioAr) {
        this.bioAr = bioAr;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNameAr() {
        return streetNameAr;
    }

    public void setStreetNameAr(String streetNameAr) {
        this.streetNameAr = streetNameAr;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZoneNumber() {
        return zoneNumber;
    }

    public void setZoneNumber(String zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(String minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIsfavourite() {
        return isfavourite;
    }

    public void setIsfavourite(String isfavourite) {
        this.isfavourite = isfavourite;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getLocationsAr() {
        return locationsAr;
    }

    public void setLocationsAr(String locationsAr) {
        this.locationsAr = locationsAr;
    }
//
    public List<Businessservice> getBusinessservices() {
        return businessservices;
    }

    public void setBusinessservices(List<Businessservice> businessservices) {
        this.businessservices = businessservices;
    }
//
    public BusinessCategories getBusinessCategories() {
        return businessCategories;
    }

    public void setBusinessCategories(BusinessCategories businessCategories) {
        this.businessCategories = businessCategories;
    }
//
    public List<Business_Service> getBusinessServices() {
        return businessServices;
    }

    public void setBusinessServices(List<Business_Service> businessServices) {
        this.businessServices = businessServices;
    }
//
    public List<BusinessTime> getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(List<BusinessTime> businessTime) {
        this.businessTime = businessTime;
    }

    public List<BusinessImage> getBusinessImages() {
        return businessImages;
    }

    public void setBusinessImages(List<BusinessImage> businessImages) {
        this.businessImages = businessImages;
    }

    public List<BusinessStaff> getBusinessStaff() {
        return businessStaff;
    }

    public void setBusinessStaff(List<BusinessStaff> businessStaff) {
        this.businessStaff = businessStaff;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

}
