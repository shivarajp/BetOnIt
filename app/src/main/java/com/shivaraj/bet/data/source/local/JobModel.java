package com.shivaraj.bet.data.source.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "jobs_table")
public class JobModel {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @SerializedName("type")
    @Expose
    @ColumnInfo(name = "type")
    private String type;

    @SerializedName("url")
    @Expose
    @ColumnInfo(name = "url")
    private String url;

    @SerializedName("created_at")
    @Expose
    @ColumnInfo(name = "created_at")
    private String createdAt;
    @SerializedName("company")
    @Expose
    @ColumnInfo(name = "company")
    private String company;
    @SerializedName("company_url")
    @Expose
    @ColumnInfo(name = "company_url")
    private String companyUrl;

    @SerializedName("location")
    @Expose
    @ColumnInfo(name = "location")
    private String location;
    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;
    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    private String description;
    @SerializedName("how_to_apply")
    @Expose
    @ColumnInfo(name = "howToApply")
    private String howToApply;
    @SerializedName("company_logo")
    @Expose
    @ColumnInfo(name = "companyLogo")
    private String companyLogo;

    @ColumnInfo(name = "pageNumber")
    private int pageNumber;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHowToApply() {
        return howToApply;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public JobModel(@NonNull String id, String type, String url, String createdAt,
                    String company, String companyUrl, String location, String title, String description,
                    String howToApply, String companyLogo, int pageNumber) {
        this.id = id;
        this.type = type;
        this.url = url;
        this.createdAt = createdAt;
        this.company = company;
        this.companyUrl = companyUrl;
        this.location = location;
        this.title = title;
        this.description = description;
        this.howToApply = howToApply;
        this.companyLogo = companyLogo;
        this.pageNumber = pageNumber;
    }
}
