package com.def.max.moviedesk.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.def.max.moviedesk.Adapters.ParentPopularMovieAdapter;
import com.def.max.moviedesk.BuildConfig;
import com.def.max.moviedesk.Client.MoviesRetrofitClient;
import com.def.max.moviedesk.Interfaces.MovieService;
import com.def.max.moviedesk.Models.MovieChild;
import com.def.max.moviedesk.Models.MovieParent;
import com.def.max.moviedesk.Models.MovieResponse;
import com.def.max.moviedesk.Models.MovieResults;
import com.def.max.moviedesk.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMoviesFragment extends Fragment
{
    private View view;

    private MovieService movieService;

    private SwipeRefreshLayout popularMovieSwipe;

    private RecyclerView popularMovieRecyclerView;

    private ParentPopularMovieAdapter popularMovieAdapter;

    private Activity activity;

    public PopularMoviesFragment()
    {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_popular_movies, container, false);

        activity = getActivity();

        Paper.init(view.getContext());

        movieService = MoviesRetrofitClient.getClient().create(MovieService.class);

        popularMovieSwipe = view.findViewById(R.id.popular_movies_swipe_refresh);

        popularMovieRecyclerView = view.findViewById(R.id.popular_movie_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        popularMovieRecyclerView.setLayoutManager(linearLayoutManager);

        popularMovieSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                loadPopularMovie(true);
            }
        });

        loadPopularMovie(false);

        return view;
    }

    private void loadPopularMovie(boolean isRefreshed)
    {
        if (!isRefreshed)
        {
            String cache = Paper.book().read("cache");

            if (cache != null && !cache.isEmpty())
            {
                MovieResponse movieResponse = new Gson().fromJson(cache,MovieResponse.class);

                if (movieResponse != null)
                {
                    List<MovieResults> movieResultsList = movieResponse.getResults();

                    if (movieResultsList != null && movieResultsList.size() > 0)
                    {
                        List<MovieParent> movieParentList = new ArrayList<>();

                        for (MovieResults results : movieResultsList)
                        {
                            MovieChild movieChild = new MovieChild(results.getId(),results.getTitle(),results.getOriginal_language(),results.getOriginal_title(),results.isAdult(),results.getOverview(),results.getRelease_date(),results.getVote_average());

                            List<MovieChild> movieChildList = new ArrayList<>();

                            movieChildList.add(movieChild);

                            MovieParent movieParent = new MovieParent(results.getPoster_path(),movieChildList);

                            movieParentList.add(movieParent);
                        }

                        popularMovieAdapter = new ParentPopularMovieAdapter(activity, movieParentList);
                        popularMovieRecyclerView.setAdapter(popularMovieAdapter);

                        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(view.getContext(),R.anim.layout_falldown);

                        popularMovieRecyclerView.setLayoutAnimation(controller);
                        popularMovieRecyclerView.scheduleLayoutAnimation();
                    }
                }
            }
            else
            {
                Call<MovieResponse> responseCall = movieService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_KEY);

                responseCall.enqueue(new Callback<MovieResponse>()
                {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response)
                    {
                        MovieResponse movieResponse = response.body();

                        if (movieResponse != null)
                        {
                            List<MovieResults> movieResultsList = movieResponse.getResults();

                            if (movieResultsList != null && movieResultsList.size() > 0)
                            {
                                List<MovieParent> movieParentList = new ArrayList<>();

                                for (MovieResults results : movieResultsList)
                                {
                                    MovieChild movieChild = new MovieChild(results.getId(),results.getTitle(),results.getOriginal_language(),results.getOriginal_title(),results.isAdult(),results.getOverview(),results.getRelease_date(),results.getVote_average());

                                    List<MovieChild> movieChildList = new ArrayList<>();

                                    movieChildList.add(movieChild);

                                    MovieParent movieParent = new MovieParent(results.getPoster_path(),movieChildList);

                                    movieParentList.add(movieParent);
                                }

                                popularMovieAdapter = new ParentPopularMovieAdapter(activity, movieParentList);
                                popularMovieRecyclerView.setAdapter(popularMovieAdapter);

                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(view.getContext(),R.anim.layout_falldown);

                                popularMovieRecyclerView.setLayoutAnimation(controller);
                                popularMovieRecyclerView.scheduleLayoutAnimation();

                                Paper.book().write("cache",new Gson().toJson(movieResponse));
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t)
                    {
                        Log.e("PopularMovieFragment",t.getLocalizedMessage());
                    }
                });
            }
        }
        else
        {
            Call<MovieResponse> responseCall = movieService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_KEY);

            responseCall.enqueue(new Callback<MovieResponse>()
            {
                @Override
                public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response)
                {
                    MovieResponse movieResponse = response.body();

                    if (movieResponse != null)
                    {
                        List<MovieResults> movieResultsList = movieResponse.getResults();

                        if (movieResultsList != null && movieResultsList.size() > 0)
                        {
                            List<MovieParent> movieParentList = new ArrayList<>();

                            for (MovieResults results : movieResultsList)
                            {
                                MovieChild movieChild = new MovieChild(results.getId(),results.getTitle(),results.getOriginal_language(),results.getOriginal_title(),results.isAdult(),results.getOverview(),results.getRelease_date(),results.getVote_average());

                                List<MovieChild> movieChildList = new ArrayList<>();

                                movieChildList.add(movieChild);

                                MovieParent movieParent = new MovieParent(results.getPoster_path(),movieChildList);

                                movieParentList.add(movieParent);
                            }

                            popularMovieAdapter = new ParentPopularMovieAdapter(activity, movieParentList);
                            popularMovieRecyclerView.setAdapter(popularMovieAdapter);

                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(view.getContext(),R.anim.layout_falldown);

                            popularMovieRecyclerView.setLayoutAnimation(controller);
                            popularMovieRecyclerView.scheduleLayoutAnimation();

                            Paper.book().write("cache",new Gson().toJson(movieResponse));

                            popularMovieSwipe.setRefreshing(false);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t)
                {
                    Log.e("PopularMovieFragment",t.getLocalizedMessage());
                }
            });


        }
    }
}
