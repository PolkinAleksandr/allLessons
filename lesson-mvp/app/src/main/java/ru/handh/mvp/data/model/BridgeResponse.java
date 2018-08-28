package ru.handh.mvp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public class BridgeResponse {

    @SerializedName("objects")
    private List<Bridge> bridges = new ArrayList<>();

    public List<Bridge> getBridges() {
        return bridges;
    }
}
