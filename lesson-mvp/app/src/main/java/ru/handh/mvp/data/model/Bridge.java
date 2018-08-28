package ru.handh.mvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO реализовать все поля
 * Created by Igor Glushkov on 19.08.18.
 */
public class Bridge{

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_eng")
    @Expose
    private String descriptionEng;
    @SerializedName("divorces")
    @Expose
    private List<DivorceInfo> divorces = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_eng")
    @Expose
    private String nameEng;
    @SerializedName("photo_close")
    @Expose
    private String photoClose;
    @SerializedName("photo_open")
    @Expose
    private String photoOpen;
    @SerializedName("public")
    @Expose
    private Boolean publicBool;
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public List<DivorceInfo> getDivorces() {
        return divorces;
    }

    public void setDivorces(List<DivorceInfo> divorces) {
        this.divorces = divorces;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getPhotoClose() {
        return photoClose;
    }

    public void setPhotoClose(String photoClose) {
        this.photoClose = photoClose;
    }

    public String getPhotoOpen() {
        return photoOpen;
    }

    public void setPhotoOpen(String photoOpen) {
        this.photoOpen = photoOpen;
    }

    public Boolean getPublic() {
        return publicBool;
    }

    public void setPublic(Boolean publicBool) {
        this.publicBool = publicBool;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }


    public Bridge() {
    }

    }
