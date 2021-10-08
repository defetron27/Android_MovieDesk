package com.def.max.moviedesk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.def.max.moviedesk.Models.MovieDetailProductionCompanies;
import com.def.max.moviedesk.R;
import com.def.max.moviedesk.ViewHolders.ProductionCompanyViewHolder;

import java.util.List;

public class ProductionCompanyAdapter extends RecyclerView.Adapter<ProductionCompanyViewHolder>
{
    private Context context;
    private List<MovieDetailProductionCompanies> movieDetailProductionCompaniesList;

    public ProductionCompanyAdapter(Context context, List<MovieDetailProductionCompanies> movieDetailProductionCompaniesList)
    {
        this.context = context;
        this.movieDetailProductionCompaniesList = movieDetailProductionCompaniesList;
    }

    @NonNull
    @Override
    public ProductionCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_production_company_layout_items,parent,false);

        return new ProductionCompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductionCompanyViewHolder holder, int position)
    {
        MovieDetailProductionCompanies productionCompanies = movieDetailProductionCompaniesList.get(position);

        holder.productionCompanyName.setText(productionCompanies.getName());
        holder.setProductionCompanyImageView(context,productionCompanies.getLogo_path());
    }

    @Override
    public int getItemCount()
    {
        return movieDetailProductionCompaniesList.size();
    }
}
