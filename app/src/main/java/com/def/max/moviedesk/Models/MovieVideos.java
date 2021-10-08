package com.def.max.moviedesk.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.def.max.moviedesk.Utils.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideos implements Parcelable
{
    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.results)
    private List<MovieVideoResults> results;

    public MovieVideos()
    {

    }

    public MovieVideos(Integer id, List<MovieVideoResults> results) {
        this.id = id;
        this.results = results;
    }

    protected MovieVideos(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        results = in.createTypedArrayList(MovieVideoResults.CREATOR);
    }

    public static final Creator<MovieVideos> CREATOR = new Creator<MovieVideos>() {
        @Override
        public MovieVideos createFromParcel(Parcel in) {
            return new MovieVideos(in);
        }

        @Override
        public MovieVideos[] newArray(int size) {
            return new MovieVideos[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieVideoResults> getResults() {
        return results;
    }

    public void setResults(List<MovieVideoResults> results) {
        this.results = results;
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
        dest.writeTypedList(results);
    }
}
