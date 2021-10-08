package com.def.max.moviedesk.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.def.max.moviedesk.Utils.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse implements Parcelable
{
    @SerializedName(Constants.page)
    private int page;

    @SerializedName(Constants.results)
    private List<MovieResults> results;

    @SerializedName(Constants.total_results)
    private int total_results;

    @SerializedName(Constants.total_pages)
    private int total_pages;

    public MovieResponse() {
    }

    public MovieResponse(int page, List<MovieResults> results, int total_results, int total_pages) {
        this.page = page;
        this.results = results;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }

    private MovieResponse(Parcel in) {
        page = in.readInt();
        results = in.createTypedArrayList(MovieResults.CREATOR);
        total_results = in.readInt();
        total_pages = in.readInt();
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieResults> getResults() {
        return results;
    }

    public void setResults(List<MovieResults> results) {
        this.results = results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeTypedList(results);
        dest.writeInt(total_results);
        dest.writeInt(total_pages);
    }
}
