package com.def.max.moviedesk.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.def.max.moviedesk.Utils.Constants;

public class MovieChild implements Parcelable
{
    private Integer id;
    private String title;
    private String original_language;
    private String original_title;
    private boolean adult;
    private String overview;
    private String release_date;
    private Double vote_average;

    public MovieChild()
    {
    }

    public MovieChild(Integer id, String title, String original_language, String original_title, boolean adult, String overview, String release_date, Double vote_average) {
        this.id = id;
        this.title = title;
        this.original_language = original_language;
        this.original_title = original_title;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    protected MovieChild(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        release_date = in.readString();
        if (in.readByte() == 0) {
            vote_average = null;
        } else {
            vote_average = in.readDouble();
        }
    }

    public static final Creator<MovieChild> CREATOR = new Creator<MovieChild>() {
        @Override
        public MovieChild createFromParcel(Parcel in) {
            return new MovieChild(in);
        }

        @Override
        public MovieChild[] newArray(int size) {
            return new MovieChild[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
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
        dest.writeString(title);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(release_date);
        if (vote_average == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(vote_average);
        }
    }
}
