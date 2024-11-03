package com.tutorial.travel.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.tutorial.travel.Activity.DetailActivity;
import com.tutorial.travel.DAOs.PopularDAO;
import com.tutorial.travel.Database.AppDatabase;
import com.tutorial.travel.Domain.PopularDomain;
import com.tutorial.travel.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<PopularDomain> items;
    // DecimalFormat formatter;

    public PopularAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
        // formatter = new DecimalFormat("###,###,###,###");
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.viewholder_popular,
                parent,
                false
        );
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        holder.placeNameTxt.setText(items.get(position).getPlaceName());
        holder.locationTxt.setText(items.get(position).getLocation());
        holder.categoryTxt.setText(""+items.get(position).getCategoryId());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(
                items.get(position).getImageUrl(),
                "drawable",
                holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new CenterCrop(), new GranularRoundedCorners(40,40,40,40))
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("object", items.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView placeNameTxt, locationTxt, categoryTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            placeNameTxt = itemView.findViewById(R.id.placeNameTxt);
            locationTxt = itemView.findViewById(R.id.locationTxt);
            categoryTxt = itemView.findViewById(R.id.categoryTxt);
            
            pic = itemView.findViewById(R.id.picImg);
        }
    }
}
