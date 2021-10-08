package com.def.max.moviedesk.ViewHolders;

import android.content.Context;
import android.view.View;

import com.def.max.moviedesk.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class ParentPopularMovieViewHolder extends GroupViewHolder
{
    private KenBurnsView moviePosterImageView;

    public ParentPopularMovieViewHolder(View itemView)
    {
        super(itemView);

        moviePosterImageView = itemView.findViewById(R.id.ken_burns_image_view);
    }

    public void setMoviePosterImageView(final Context context, final ExpandableGroup group)
    {
        Picasso.with(context).load(group.getTitle()).into(moviePosterImageView, new Callback()
        {
            @Override
            public void onSuccess()
            {

            }

            @Override
            public void onError()
            {
                Picasso.with(context).load("null").into(moviePosterImageView);
            }
        });
    }
}
