package com.def.max.moviedesk.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.def.max.moviedesk.Utils.Constants;
import com.google.gson.annotations.SerializedName;

public class MovieDetailProductionCountries implements Parcelable
{
    @SerializedName(Constants.iso_3166_1)
    private String iso_3166_1;

    @SerializedName(Constants.name)
    private String name;

    public MovieDetailProductionCountries()
    {

    }

    public MovieDetailProductionCountries(String iso_3166_1, String name)
    {
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
    }

    private MovieDetailProductionCountries(Parcel in) {
        iso_3166_1 = in.readString();
        name = in.readString();
    }

    public static final Creator<MovieDetailProductionCountries> CREATOR = new Creator<MovieDetailProductionCountries>() {
        @Override
        public MovieDetailProductionCountries createFromParcel(Parcel in) {
            return new MovieDetailProductionCountries(in);
        }

        @Override
        public MovieDetailProductionCountries[] newArray(int size) {
            return new MovieDetailProductionCountries[size];
        }
    };

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
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
        dest.writeString(iso_3166_1);
        dest.writeString(name);
    }
}
