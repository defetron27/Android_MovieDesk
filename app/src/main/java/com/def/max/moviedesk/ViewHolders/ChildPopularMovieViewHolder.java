package com.def.max.moviedesk.ViewHolders;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.RelativeLayout;

import com.def.max.moviedesk.Models.MovieChild;
import com.def.max.moviedesk.MovieDetailActivity;
import com.def.max.moviedesk.R;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ChildPopularMovieViewHolder extends ChildViewHolder
{
    private AppCompatTextView title,originalTitle,originalLanguage,releaseDate,overView,adult,more;
    private LinearLayoutCompat originalTitleLayout,originalLanguageLayout,releaseDateLayout,adultLayout;
    private RelativeLayout overViewLayout;
    private ArcProgress arcProgressBar;

    private Activity activity;

    public ChildPopularMovieViewHolder(Activity activity, View itemView)
    {
        super(itemView);

        this.activity = activity;

        title = itemView.findViewById(R.id.movie_title);
        originalTitle = itemView.findViewById(R.id.original_title);
        originalLanguage = itemView.findViewById(R.id.original_language);
        releaseDate = itemView.findViewById(R.id.release_date);
        overView = itemView.findViewById(R.id.overview);
        adult = itemView.findViewById(R.id.adult);
        arcProgressBar = itemView.findViewById(R.id.rating_bar);
        originalTitleLayout = itemView.findViewById(R.id.original_title_layout);
        originalLanguageLayout = itemView.findViewById(R.id.original_language_layout);
        releaseDateLayout = itemView.findViewById(R.id.release_date_layout);
        adultLayout = itemView.findViewById(R.id.adult_layout);
        overViewLayout = itemView.findViewById(R.id.over_view_layout);
        more = itemView.findViewById(R.id.more);
    }

    public void onBind(final MovieChild movieChild)
    {
        final String description = movieChild.getOverview();
        final String original_title = movieChild.getOriginal_title();
        final String original_language = movieChild.getOriginal_language();
        final String release_date = movieChild.getRelease_date();
        final String movieTitle = movieChild.getTitle();
        final String id = String.valueOf(movieChild.getId());

        Double ratingAverage = movieChild.getVote_average() * 10;

        final int ratingValue = ratingAverage.intValue();

        boolean adultMovie = movieChild.isAdult();

        title.setText(movieTitle);

        if (original_title.length() > 0)
        {
            originalTitle.setText(original_title);
            originalTitleLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            originalTitleLayout.setVisibility(View.GONE);
        }

        if (original_language.length() > 0)
        {
            originalLanguage.setText(original_language);
            originalLanguageLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            originalLanguageLayout.setVisibility(View.GONE);
        }

        if (release_date.length() > 0)
        {
            releaseDate.setText(release_date);
            releaseDateLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            releaseDateLayout.setVisibility(View.GONE);
        }
        
        if (adultMovie)
        {
            adult.setText(R.string.yes);
            adultLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            adult.setText(R.string.no);
            adultLayout.setVisibility(View.VISIBLE);
        }

        if (description.length() > 0)
        {
            if (description.length() > 100)
            {
                String shortDescription = description.substring(0,100) + "...";

                overView.setText(shortDescription);

                overViewLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                overView.setText(description);

                overViewLayout.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            overViewLayout.setVisibility(View.GONE);
        }

        arcProgressBar.setProgress(ratingValue);

        more.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(activity, MovieDetailActivity.class);
                intent.putExtra("id",id);
                activity.startActivity(intent);

                activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
    }
}
