package com.gridelayout.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatListModel implements Serializable {

    @SerializedName("contact_name")
    public String contact_name;

    @SerializedName("contact_mobile")
    public String contact_mobile;

    @SerializedName("contact_email")
    public String contact_email;

    @SerializedName("contact_city")
    public String contact_city;

    @SerializedName("contact_state")
    public String contact_state;

    @SerializedName("contact_country")
    public String contact_country;


}
