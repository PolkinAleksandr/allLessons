package aleksandrpolkin.ru.lesson7.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjectsData implements Parcelable {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_eng")
    @Expose
    private String descriptionEng;
    @SerializedName("divorces")
    @Expose
    private List<Divorces> divorces = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_eng")
    @Expose
    private String nameEng;
    @SerializedName("photo_close")
    @Expose
    private String photoClose;
    @SerializedName("photo_open")
    @Expose
    private String photoOpen;
    @SerializedName("public")
    @Expose
    private Boolean publicBool;
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public List<Divorces> getDivorces() {
        return divorces;
    }

    public void setDivorces(List<Divorces> divorces) {
        this.divorces = divorces;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getPhotoClose() {
        return photoClose;
    }

    public void setPhotoClose(String photoClose) {
        this.photoClose = photoClose;
    }

    public String getPhotoOpen() {
        return photoOpen;
    }

    public void setPhotoOpen(String photoOpen) {
        this.photoOpen = photoOpen;
    }

    public Boolean getPublic() {
        return publicBool;
    }

    public void setPublic(Boolean publicBool) {
        this.publicBool = publicBool;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }


    public ObjectsData() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.descriptionEng);
       // dest.writeList(this.divorces);
        dest.writeValue(this.id);
        dest.writeValue(this.lat);
        dest.writeValue(this.lng);
        dest.writeString(this.name);
        dest.writeString(this.nameEng);
        dest.writeString(this.photoClose);
        dest.writeString(this.photoOpen);
        dest.writeValue(this.publicBool);
        dest.writeString(this.resourceUri);
    }

    protected ObjectsData(Parcel in) {
        this.description = in.readString();
        this.descriptionEng = in.readString();
      //  this.divorces = new ArrayList<Divorces>();
      //  in.readList(this.divorces, Divorces.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.lat = (Double) in.readValue(Double.class.getClassLoader());
        this.lng = (Double) in.readValue(Double.class.getClassLoader());
        this.name = in.readString();
        this.nameEng = in.readString();
        this.photoClose = in.readString();
        this.photoOpen = in.readString();
        this.publicBool = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.resourceUri = in.readString();
    }

    public static final Creator<ObjectsData> CREATOR = new Creator<ObjectsData>() {
        @Override
        public ObjectsData createFromParcel(Parcel source) {
            return new ObjectsData(source);
        }

        @Override
        public ObjectsData[] newArray(int size) {
            return new ObjectsData[size];
        }
    };
}