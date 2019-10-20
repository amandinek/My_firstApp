
package com.example.myfirstapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GitSearchAppResponse {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("public")
    @Expose
    private Boolean _public;
    @SerializedName("payload")
    @Expose
    private Payload payload;
    @SerializedName("repo")
    @Expose
    private Repo repo;
    @SerializedName("actor")
    @Expose
    private Actor actor;
    @SerializedName("org")
    @Expose
    private Org org;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private String id;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GitSearchAppResponse() {
    }

    /**
     * 
     * @param actor
     * @param createdAt
     * @param _public
     * @param payload
     * @param org
     * @param repo
     * @param id
     * @param type
     */
    public GitSearchAppResponse(String type, Boolean _public, Payload payload, Repo repo, Actor actor, Org org, String createdAt, String id) {
        super();
        this.type = type;
        this._public = _public;
        this.payload = payload;
        this.repo = repo;
        this.actor = actor;
        this.org = org;
        this.createdAt = createdAt;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getPublic() {
        return _public;
    }

    public void setPublic(Boolean _public) {
        this._public = _public;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
