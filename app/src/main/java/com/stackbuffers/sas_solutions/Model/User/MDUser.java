package com.stackbuffers.sas_solutions.Model.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MDUser {
    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("message")
    @Expose
    private String message;


    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    @SerializedName("user")
    @Expose
    private UserModel userModel;
}
