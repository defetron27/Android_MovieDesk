package com.def.max.moviedesk.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.def.max.moviedesk.Utils.Constants;
import com.google.gson.annotations.SerializedName;

public class MovieDetailBelongsToCollection implements Parcelable
{
    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.name)
    private String name;

    @SerializedName(Constants.poster_path)
    private String poster_path;

    @SerializedName(Constants.backdrop_path)
    private String backdrop_path;

    public MovieDetailBelongsToCollection()
    {
    }

    public MovieDetailBelongsToCollection(Integer id, String name, String poster_path, String backdrop_path) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    private MovieDetailBelongsToCollection(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
    }

    public static final Creator<MovieDetailBelongsToCollection> CREATOR = new Creator<MovieDetailBelongsToCollection>() {
        @Override
        public MovieDetailBelongsToCollection createFromParcel(Parcel in) {
            return new MovieDetailBelongsToCollection(in);
        }

        @Override
        public MovieDetailBelongsToCollection[] newArray(int size) {
            return new MovieDetailBelongsToCollection[size];
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
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
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
    }
}
