package com.def.max.moviedesk.Interfaces;

import com.def.max.moviedesk.Models.MovieDetails;
import com.def.max.moviedesk.Models.MovieResponse;
import com.def.max.moviedesk.Models.MovieVideos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService
{
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key")String apiKey);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key")String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") int id, @Query("api_key")String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<MovieVideos> getMovieVideos(@Path("movie_id") int id, @Query("api_key")String apiKey);
}
