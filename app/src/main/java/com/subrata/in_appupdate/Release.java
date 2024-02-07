package com.subrata.in_appupdate;

import com.google.gson.annotations.SerializedName;

public class Release {
    @SerializedName("tag_name")
    private String tagName;

    public String getTagName() {
        return tagName;
    }
}
