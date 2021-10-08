package com.def.max.moviedesk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.def.max.moviedesk.Adapters.MovieVideoAdapter;
import com.def.max.moviedesk.Adapters.ProductionCompanyAdapter;
import com.def.max.moviedesk.Client.MoviesRetrofitClient;
import com.def.max.moviedesk.Interfaces.MovieService;
import com.def.max.moviedesk.Models.MovieDetailProductionCompanies;
import com.def.max.moviedesk.Models.MovieDetailProductionCountries;
import com.def.max.moviedesk.Models.MovieDetails;
import com.def.max.moviedesk.Models.MovieDetailsGenres;
import com.def.max.moviedesk.Models.MovieVideoResults;
import com.def.max.moviedesk.Models.MovieVideos;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity
{
    private final static String TAG = MovieDetailActivity.class.getSimpleName();

    private KenBurnsView kenBurnsView;

    private ArcProgress detailRatingBar;

    private LinearLayoutCompat detailOriginalTitleLayout;
    private LinearLayoutCompat detailOriginalLanguageLayout;
    private LinearLayoutCompat detailAdultLayout;
    private LinearLayoutCompat detailReleaseDateLayout;
    private LinearLayoutCompat detailOverviewLayout;
    private LinearLayoutCompat detailStatusLayout;
    private LinearLayoutCompat detailBudgetLayout;
    private LinearLayoutCompat detailRevenueLayout;
    private LinearLayoutCompat detailRuntimeLayout;
    private LinearLayoutCompat detailHomePageLayout;
    private LinearLayoutCompat detailGenreLayout;
    private LinearLayoutCompat detailProductionCompanyLayout;
    private LinearLayoutCompat detailProductionCountryLayout;
    private LinearLayoutCompat detailVideoLayout;

    private AppCompatTextView detailOriginalLanguage;
    private AppCompatTextView detailOriginalTitle;
    private AppCompatTextView detailMovieTitle;
    private AppCompatTextView detailAdult;
    private AppCompatTextView detailReleaseDate;
    private AppCompatTextView detailOverview;
    private AppCompatTextView detailStatus;
    private AppCompatTextView detailBudget;
    private AppCompatTextView detailRevenue;
    private AppCompatTextView detailRuntime;
    private AppCompatTextView detailHomePage;
    private AppCompatTextView detailGenre;
    private AppCompatTextView detailProductionCountry;

    private RecyclerView detailProductionCompanyRecyclerView;
    private RecyclerView detailVideoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();

        Toolbar toolbar = findViewById(R.id.detail_tool_bar);
        setSupportActionBar(toolbar);

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.detail_collapsing_tool_bar);
        collapsingToolbarLayout.setTitle(" ");

        detailOriginalTitleLayout = findViewById(R.id.detail_original_title_layout);
        detailOriginalLanguageLayout = findViewById(R.id.detail_original_language_layout);
        detailAdultLayout = findViewById(R.id.detail_adult_layout);
        detailReleaseDateLayout = findViewById(R.id.detail_release_date_layout);
        detailOverviewLayout = findViewById(R.id.detail_overview_layout);
        detailStatusLayout = findViewById(R.id.detail_status_layout);
        detailRuntimeLayout = findViewById(R.id.detail_runtime_layout);
        detailBudgetLayout = findViewById(R.id.detail_budget_layout);
        detailHomePageLayout = findViewById(R.id.detail_homepage_layout);
        detailRevenueLayout = findViewById(R.id.detail_revenue_layout);
        detailGenreLayout = findViewById(R.id.detail_genre_layout);
        detailProductionCompanyLayout = findViewById(R.id.detail_production_company_layout);
        detailProductionCountryLayout = findViewById(R.id.detail_production_country_layout);
        detailVideoLayout = findViewById(R.id.detail_video_layout);

        kenBurnsView = findViewById(R.id.detail_poster_image_view);
        detailMovieTitle = findViewById(R.id.detail_movie_title);
        detailRatingBar = findViewById(R.id.detail_rating_bar);
        detailOriginalTitle = findViewById(R.id.detail_original_title);
        detailOriginalLanguage = findViewById(R.id.detail_original_language);
        detailAdult = findViewById(R.id.detail_adult);
        detailReleaseDate = findViewById(R.id.detail_release_date);
        detailOverview = findViewById(R.id.detail_overview);
        detailStatus = findViewById(R.id.detail_status);
        detailRevenue = findViewById(R.id.detail_revenue);
        detailHomePage = findViewById(R.id.detail_homepage);
        detailBudget = findViewById(R.id.detail_budget);
        detailRuntime = findViewById(R.id.detail_runtime);
        detailGenre = findViewById(R.id.detail_genre);
        detailProductionCountry = findViewById(R.id.detail_production_country);

        detailProductionCompanyRecyclerView = findViewById(R.id.detail_production_company_recycler_view);
        detailProductionCompanyRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        detailVideoRecyclerView = findViewById(R.id.detail_video_recycler_view);
        detailVideoRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        MovieService movieService = MoviesRetrofitClient.getClient().create(MovieService.class);
        MovieService service = MoviesRetrofitClient.getClient().create(MovieService.class);

        ThumbnailLoader.initialize(BuildConfig.GOOGLE_API_KEY);

        if (intent != null && intent.getExtras() != null)
        {
            int id = Integer.parseInt(intent.getExtras().getString("id"));

            Call<MovieDetails> movieDetailsCall = movieService.getMovieDetails(id,BuildConfig.THE_MOVIE_DB_API_KEY);

            movieDetailsCall.enqueue(new retrofit2.Callback<MovieDetails>()
            {
                @Override
                public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response)
                {
                    MovieDetails movieDetailsResponse = response.body();

                    if (movieDetailsResponse != null)
                    {
                        prepareMovieDetails(movieDetailsResponse);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t)
                {
                    Log.e(TAG,t.getLocalizedMessage());
                }
            });

            Call<MovieVideos> movieVideosCall = service.getMovieVideos(id,BuildConfig.THE_MOVIE_DB_API_KEY);

            movieVideosCall.enqueue(new retrofit2.Callback<MovieVideos>()
            {
                @Override
                public void onResponse(@NonNull Call<MovieVideos> call, @NonNull Response<MovieVideos> response)
                {
                    MovieVideos movieVideosResponse = response.body();

                    if (movieVideosResponse != null)
                    {
                        List<MovieVideoResults> resultsList = movieVideosResponse.getResults();

                        if (resultsList != null && resultsList.size() > 0)
                        {
                            detailVideoLayout.setVisibility(View.VISIBLE);

                            MovieVideoAdapter videoAdapter = new MovieVideoAdapter(MovieDetailActivity.this,resultsList);
                            detailVideoRecyclerView.setAdapter(videoAdapter);

                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this,R.anim.layout_slide_from_right);

                            detailVideoRecyclerView.setLayoutAnimation(controller);
                            detailVideoRecyclerView.scheduleLayoutAnimation();
                        }
                        else
                        {
                            detailVideoLayout.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<MovieVideos> call, @NonNull Throwable t)
                {
                    Log.e(TAG,t.getLocalizedMessage());
                }
            });
        }
    }

    private void prepareMovieDetails(MovieDetails movieDetails)
    {
        String posterPath = movieDetails.getPoster_path();
        String movieTitle = movieDetails.getTitle();
        String originalTitle = movieDetails.getOriginal_title();
        String originalLanguage = movieDetails.getOriginal_language();
        String releaseDate = movieDetails.getRelease_date();
        String overview = movieDetails.getOverview();
        String status = movieDetails.getStatus();
        final String homepage = movieDetails.getHomepage();

        List<MovieDetailsGenres> movieDetailsGenresList = movieDetails.getGenres();
        List<MovieDetailProductionCompanies> movieDetailProductionCompaniesList = movieDetails.getProduction_companies();
        List<MovieDetailProductionCountries> movieDetailProductionCountriesList = movieDetails.getProduction_countries();

        boolean adult = movieDetails.isAdult();

        Long budget = movieDetails.getBudget();
        Long revenue = movieDetails.getRevenue();
        Long runtime = movieDetails.getRuntime();

        Double ratingAverage = movieDetails.getVote_average() * 10;

        Picasso.with(this).load(posterPath).into(kenBurnsView, new Callback()
        {
            @Override
            public void onSuccess()
            {

            }

            @Override
            public void onError()
            {
                Picasso.with(MovieDetailActivity.this).load("null").into(kenBurnsView);
            }
        });

        if (movieDetailProductionCompaniesList != null && movieDetailProductionCompaniesList.size() > 0)
        {
            detailProductionCompanyLayout.setVisibility(View.VISIBLE);

            ProductionCompanyAdapter productionCompanyAdapter = new ProductionCompanyAdapter(MovieDetailActivity.this, movieDetailProductionCompaniesList);
            detailProductionCompanyRecyclerView.setAdapter(productionCompanyAdapter);

            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this,R.anim.layout_slide_from_right);

            detailProductionCompanyRecyclerView.setLayoutAnimation(controller);
            detailProductionCompanyRecyclerView.scheduleLayoutAnimation();
        }
        else
        {
            detailProductionCompanyLayout.setVisibility(View.GONE);
        }

        int rating = ratingAverage.intValue();
        detailRatingBar.setProgress(rating);

        detailMovieTitle.setText(movieTitle);

        if (originalTitle != null)
        {
            if (originalTitle.length() > 0)
            {
                detailOriginalTitle.setText(originalTitle);
                detailOriginalTitleLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                detailOriginalTitleLayout.setVisibility(View.GONE);
            }
        }

        if (originalLanguage != null)
        {
            if (originalLanguage.length() > 0)
            {
                detailOriginalLanguage.setText(originalLanguage);
                detailOriginalLanguageLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                detailOriginalLanguageLayout.setVisibility(View.GONE);
            }
        }

        if (releaseDate != null)
        {
            if (releaseDate.length() > 0)
            {
                detailReleaseDate.setText(releaseDate);
                detailReleaseDateLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                detailReleaseDateLayout.setVisibility(View.GONE);
            }
        }

        if (adult)
        {
            detailAdult.setText(R.string.yes);
            detailAdultLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            detailAdult.setText(R.string.no);
            detailAdultLayout.setVisibility(View.VISIBLE);
        }

        if (overview != null)
        {
            if (overview.length() > 0)
            {

                detailOverview.setText(overview);
                detailOverviewLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                detailOverviewLayout.setVisibility(View.GONE);
            }
        }

        if (status != null)
        {
            if (status.length() > 0)
            {
                detailStatus.setText(status);
                detailStatusLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                detailStatusLayout.setVisibility(View.GONE);
            }
        }

        if (homepage != null)
        {
            if (homepage.length() > 0)
            {
                detailHomePage.setText(homepage);
                detailHomePageLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                detailHomePageLayout.setVisibility(View.GONE);
            }
        }

        if (revenue != null)
        {
            if (revenue !=  0)
            {
                detailRevenue.setText(String.valueOf(revenue));
                detailRevenueLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                detailRevenueLayout.setVisibility(View.GONE);
            }
        }

        if (budget != null)
        {
            if (budget !=  0)
            {
                detailBudget.setText(String.valueOf(budget));
                detailBudgetLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                detailBudgetLayout.setVisibility(View.GONE);
            }
        }

        if (runtime != null)
        {
            if (runtime !=  0)
            {
                detailRuntime.setText(String.valueOf(runtime));
                detailRuntimeLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                detailRuntimeLayout.setVisibility(View.GONE);
            }
        }

        if (movieDetailsGenresList != null && movieDetailsGenresList.size() > 0)
        {
            StringBuilder genres = new StringBuilder();

            for (int i=0; i<movieDetailsGenresList.size(); i++)
            {
                if (i == movieDetailsGenresList.size()-1)
                {
                    genres.append(movieDetailsGenresList.get(i).getName());
                }
                else
                {
                    genres.append(movieDetailsGenresList.get(i).getName()).append(",");
                }
            }

            detailGenre.setText(genres.toString());
            detailGenreLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            detailGenreLayout.setVisibility(View.GONE);
        }

        if (movieDetailProductionCountriesList != null && movieDetailProductionCountriesList.size() > 0)
        {
            StringBuilder genres = new StringBuilder();

            for (int i=0; i<movieDetailProductionCountriesList.size(); i++)
            {
                if (i == movieDetailProductionCountriesList.size()-1)
                {
                    genres.append(movieDetailProductionCountriesList.get(i).getName());
                }
                else
                {
                    genres.append(movieDetailProductionCountriesList.get(i).getName()).append(",");
                }
            }

            detailProductionCountry.setText(genres.toString());
            detailProductionCountryLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            detailProductionCountryLayout.setVisibility(View.GONE);
        }

        detailHomePage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent webIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(homepage));
                startActivity(webIntent);
            }
        });
    }

    @Override
    public void finish()
    {
        super.finish();

        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
