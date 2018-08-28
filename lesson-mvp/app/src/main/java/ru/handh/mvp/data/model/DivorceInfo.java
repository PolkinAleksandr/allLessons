package ru.handh.mvp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public class DivorceInfo {

    @SerializedName("end")
    private String end;

    @SerializedName("id")
    private int id;

    @SerializedName("start")
    private String start;

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
