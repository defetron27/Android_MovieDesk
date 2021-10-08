package com.def.max.moviedesk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.def.max.moviedesk.Models.MovieVideoResults;
import com.def.max.moviedesk.R;
import com.def.max.moviedesk.ViewHolders.MovieVideoViewHolder;

import java.util.List;

public class MovieVideoAdapter extends RecyclerView.Adapter<MovieVideoViewHolder>
{
    private Context context;
    private List<MovieVideoResults> movieVideoResultsList;

    public MovieVideoAdapter(Context context, List<MovieVideoResults> movieVideoResultsList)
    {
        this.context = context;
        this.movieVideoResultsList = movieVideoResultsList;
    }

    @NonNull
    @Override
    public MovieVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_video_layout_items,parent,false);

        return new MovieVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVideoViewHolder holder, int position)
    {
        MovieVideoResults movieVideoResults = movieVideoResultsList.get(position);

        holder.videoName.setText(movieVideoResults.getName());
        holder.setVideoImage(context,movieVideoResults.getKey());
    }

    @Override
    public int getItemCount()
    {
        return movieVideoResultsList.size();
    }
}
