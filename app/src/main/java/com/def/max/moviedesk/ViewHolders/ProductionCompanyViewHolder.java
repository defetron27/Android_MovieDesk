package com.def.max.moviedesk.ViewHolders;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.def.max.moviedesk.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ProductionCompanyViewHolder extends RecyclerView.ViewHolder
{
    public AppCompatTextView productionCompanyName;

    public ProductionCompanyViewHolder(View itemView)
    {
        super(itemView);

        productionCompanyName = itemView.findViewById(R.id.production_company_name);
    }

    public void setProductionCompanyImageView(final Context context, String url)
    {
        final AppCompatImageView productionCompanyImageView = itemView.findViewById(R.id.production_company_image_view);

        Picasso.with(context).load(url).into(productionCompanyImageView, new Callback()
        {
            @Override
            public void onSuccess()
            {

            }

            @Override
            public void onError()
            {
                Picasso.with(context).load("null").into(productionCompanyImageView);
            }
        });
    }
}
