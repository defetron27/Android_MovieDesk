package com.def.max.moviedesk.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.def.max.moviedesk.Utils.Constants;
import com.google.gson.annotations.SerializedName;

public class MovieDetailSpokenLanguages implements Parcelable
{
    @SerializedName(Constants.iso_639_1)
    private String iso_639_1;

    @SerializedName(Constants.name)
    private String name;

    public MovieDetailSpokenLanguages()
    {
    }

    public MovieDetailSpokenLanguages(String iso_639_1, String name) {
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }

    private MovieDetailSpokenLanguages(Parcel in) {
        iso_639_1 = in.readString();
        name = in.readString();
    }

    public static final Creator<MovieDetailSpokenLanguages> CREATOR = new Creator<MovieDetailSpokenLanguages>() {
        @Override
        public MovieDetailSpokenLanguages createFromParcel(Parcel in) {
            return new MovieDetailSpokenLanguages(in);
        }

        @Override
        public MovieDetailSpokenLanguages[] newArray(int size) {
            return new MovieDetailSpokenLanguages[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iso_639_1);
        dest.writeString(name);
    }
}
