package com.def.max.moviedesk.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.def.max.moviedesk.Models.MovieChild;
import com.def.max.moviedesk.Models.MovieParent;
import com.def.max.moviedesk.R;
import com.def.max.moviedesk.ViewHolders.ChildPopularMovieViewHolder;
import com.def.max.moviedesk.ViewHolders.ParentPopularMovieViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ParentPopularMovieAdapter extends ExpandableRecyclerViewAdapter<ParentPopularMovieViewHolder,ChildPopularMovieViewHolder>
{
    private Activity activity;

    public ParentPopularMovieAdapter(Activity activity,List<? extends ExpandableGroup> groups)
    {
        super(groups);

        this.activity = activity;
    }

    @Override
    public ParentPopularMovieViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType)
    {
        View view =  LayoutInflater.from(activity).inflate(R.layout.parent_popular_movie_layout_items,parent,false);

        return new ParentPopularMovieViewHolder(view);
    }

    @Override
    public ChildPopularMovieViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType)
    {
        View view =  LayoutInflater.from(activity).inflate(R.layout.child_popular_movie_layout_items,parent,false);

        return new ChildPopularMovieViewHolder(activity,view);
    }

    @Override
    public void onBindGroupViewHolder(ParentPopularMovieViewHolder holder, int flatPosition, ExpandableGroup group)
    {
        holder.setMoviePosterImageView(activity,group);
    }

    @Override
    public void onBindChildViewHolder(ChildPopularMovieViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex)
    {
        MovieChild movieChild = ((MovieParent)group).getItems().get(childIndex);

        holder.onBind(movieChild);
    }
}
