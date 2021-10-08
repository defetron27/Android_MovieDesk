package com.def.max.moviedesk.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.def.max.moviedesk.Utils.Constants;
import com.google.gson.annotations.SerializedName;

public class MovieDetailsGenres implements Parcelable
{
    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.name)
    private String name;

    public MovieDetailsGenres()
    {

    }

    public MovieDetailsGenres(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private MovieDetailsGenres(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
    }

    public static final Creator<MovieDetailsGenres> CREATOR = new Creator<MovieDetailsGenres>() {
        @Override
        public MovieDetailsGenres createFromParcel(Parcel in) {
            return new MovieDetailsGenres(in);
        }

        @Override
        public MovieDetailsGenres[] newArray(int size) {
            return new MovieDetailsGenres[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
    }
}
