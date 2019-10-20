
package com.example.myfirstapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Repo {

    @SerializedName("id")
    @Expose
    private double id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Repo() {
    }

    /**
     * 
     * @param name
     * @param id
     * @param url
     */
    public Repo(Integer id, String name, String url) {
        super();
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
