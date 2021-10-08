package com.def.max.moviedesk.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.def.max.moviedesk.Utils.Constants;
import com.google.gson.annotations.SerializedName;

public class MovieDetailProductionCompanies implements Parcelable
{
    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.logo_path)
    private String logo_path;

    @SerializedName(Constants.name)
    private String name;

    @SerializedName(Constants.origin_country)
    private String origin_country;

    public MovieDetailProductionCompanies()
    {
    }

    public MovieDetailProductionCompanies(Integer id, String logo_path, String name, String origin_country) {
        this.id = id;
        this.logo_path = logo_path;
        this.name = name;
        this.origin_country = origin_country;
    }

    private MovieDetailProductionCompanies(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        logo_path = in.readString();
        name = in.readString();
        origin_country = in.readString();
    }

    public static final Creator<MovieDetailProductionCompanies> CREATOR = new Creator<MovieDetailProductionCompanies>() {
        @Override
        public MovieDetailProductionCompanies createFromParcel(Parcel in) {
            return new MovieDetailProductionCompanies(in);
        }

        @Override
        public MovieDetailProductionCompanies[] newArray(int size) {
            return new MovieDetailProductionCompanies[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogo_path()
    {
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";
        return baseImageUrl + logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
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
        dest.writeString(logo_path);
        dest.writeString(name);
        dest.writeString(origin_country);
    }
}
