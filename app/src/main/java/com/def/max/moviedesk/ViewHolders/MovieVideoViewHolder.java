package com.def.max.moviedesk.ViewHolders;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codewaves.youtubethumbnailview.ImageLoader;
import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.def.max.moviedesk.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MovieVideoViewHolder extends RecyclerView.ViewHolder
{
    public AppCompatTextView videoName;

    public View view;

    public MovieVideoViewHolder(View itemView)
    {
        super(itemView);

        view = itemView;

        videoName = view.findViewById(R.id.video_name);
    }

    public void setVideoImage(final Context context, String url)
    {
        final ThumbnailView videoThumbnailView = view.findViewById(R.id.video_image_view);

        String baseUrl = "https://www.youtube.com/watch?v=";

        videoThumbnailView.loadThumbnail(baseUrl + url, new ImageLoader()
        {
            @Nullable
            @Override
            public Bitmap load(String url) throws IOException
            {
                return Picasso.with(context).load(url).get();
            }
        });
    }
}
