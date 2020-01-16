
package com.t.and.tt.Model.Home.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentHomeServiceModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_ar")
    @Expose
    private Object nameAr;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("icon")
    @Expose
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getNameAr() {
        return nameAr;
    }

    public void setNameAr(Object nameAr) {
        this.nameAr = nameAr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
