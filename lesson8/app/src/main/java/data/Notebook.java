package data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import aleksandrpolkin.ru.lesson8.MyColor;
import aleksandrpolkin.ru.lesson8.R;

@Entity(tableName = "Notebook")
public class Notebook implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    public long idNote;
    @ColumnInfo(name = "Title")
    private String titleNote;
    @ColumnInfo(name = "Note")
    private String note;
    @ColumnInfo(name = "Rar")
    private boolean rar;
    @ColumnInfo(name = "Color_background")
    private int colorBackground = R.color.blue_grey;

    public int getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(int color) {
        this.colorBackground = color;
    }

    public long getIdNote() {
        return idNote;
    }

    public void setIdNote(long idNote) {
        this.idNote = idNote;
    }

    public String getTitleNote() {
        return titleNote;
    }

    public void setTitleNote(String titleNote) {
        this.titleNote = titleNote;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean getRar() {
        return rar;
    }

    public void setRar(boolean rar) {
        this.rar = rar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.idNote);
        dest.writeString(this.titleNote);
        dest.writeString(this.note);
        dest.writeByte(this.rar ? (byte) 1 : (byte) 0);
        dest.writeInt(this.colorBackground);
    }

    public Notebook() {
    }

    protected Notebook(Parcel in) {
        this.idNote = in.readLong();
        this.titleNote = in.readString();
        this.note = in.readString();
        this.rar = in.readByte() != 0;
        this.colorBackground = in.readInt();
    }

    public static final Parcelable.Creator<Notebook> CREATOR = new Parcelable.Creator<Notebook>() {
        @Override
        public Notebook createFromParcel(Parcel source) {
            return new Notebook(source);
        }

        @Override
        public Notebook[] newArray(int size) {
            return new Notebook[size];
        }
    };
}
