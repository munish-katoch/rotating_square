package com.katoch.rotatingsquare.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
{"datetime": "2018-11-23 18:07:51.703481"}
 */
public class DateTime implements Serializable {
    @SerializedName("datetime")
    @Expose
    public String time;

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
