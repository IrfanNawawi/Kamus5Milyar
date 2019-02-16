package com.example.irfannawawi.kamus5milyar.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Irfan Nawawi on 20/01/2019.
 */

public class KamusItem implements Parcelable {

    public static final Parcelable.Creator<KamusItem> CREATOR = new Parcelable.Creator<KamusItem>() {
        @Override
        public KamusItem createFromParcel(Parcel source) {
            return new KamusItem(source);
        }

        @Override
        public KamusItem[] newArray(int size) {
            return new KamusItem[size];
        }
    };
    private int id;
    private String kata;
    private String arti;

    public KamusItem(String kata, String arti) {
        this.kata = kata;
        this.arti = arti;
    }

    public KamusItem() {
    }

    protected KamusItem(Parcel in) {
        this.id = in.readInt();
        this.kata = in.readString();
        this.arti = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kata);
        dest.writeString(this.arti);
    }
}
