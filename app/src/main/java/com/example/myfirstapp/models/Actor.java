
package com.example.myfirstapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Actor {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("gravatar_id")
    @Expose
    private String gravatarId;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Actor() {
    }

    /**
     * 
     * @param gravatarId
     * @param avatarUrl
     * @param id
     * @param login
     * @param url
     */
    public Actor(Integer id, String login, String gravatarId, String avatarUrl, String url) {
        super();
        this.id = id;
        this.login = login;
        this.gravatarId = gravatarId;
        this.avatarUrl = avatarUrl;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
