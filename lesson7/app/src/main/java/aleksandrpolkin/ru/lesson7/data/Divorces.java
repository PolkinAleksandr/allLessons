package aleksandrpolkin.ru.lesson7.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Divorces {

    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("start")
    @Expose
    private String start;

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

}
