package aleksandrpolkin.ru.lesson7.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dataset {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("objects")
    @Expose
    private List<ObjectsData> objects = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<ObjectsData> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectsData> objects) {
        this.objects = objects;
    }

}