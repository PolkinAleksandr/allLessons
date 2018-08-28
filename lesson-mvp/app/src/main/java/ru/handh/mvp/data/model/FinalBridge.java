package ru.handh.mvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FinalBridge implements Parcelable {

    private String time;
    private int pic;
    private String name;
    private String description;
    private int id;
    private String photoClose;
    private String photoOpen;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.time);
        dest.writeInt(this.pic);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.id);
        dest.writeString(this.photoClose);
        dest.writeString(this.photoOpen);
    }

    public FinalBridge() {
    }

    protected FinalBridge(Parcel in) {
        this.time = in.readString();
        this.pic = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.id = in.readInt();
        this.photoClose = in.readString();
        this.photoOpen = in.readString();
    }

    public static final Parcelable.Creator<FinalBridge> CREATOR = new Parcelable.Creator<FinalBridge>() {
        @Override
        public FinalBridge createFromParcel(Parcel source) {
            return new FinalBridge(source);
        }

        @Override
        public FinalBridge[] newArray(int size) {
            return new FinalBridge[size];
        }
    };
}
