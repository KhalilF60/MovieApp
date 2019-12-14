package com.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    ArrayList<Movie> movies;
    Context context;
    final String imageURL = "https://image.tmdb.org/t/p/w500";

    public MovieAdapter(ArrayList<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_movie,parent, false);
       MyViewHolder viewHolder= new MyViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);

        holder.titleView.setText(currentMovie.getTitle());
        holder.descrptionView.setText(currentMovie.getOverview());
        String tempURL = imageURL + currentMovie.getPoster_path();

        Picasso.get().load(tempURL).resize(100,100).centerCrop().into(holder.posterView);



    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView descrptionView;
        ImageView posterView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.textView_title);
            descrptionView = itemView.findViewById(R.id.textView_description);
            posterView = itemView.findViewById(R.id.imageView_poster);
        }
    }
}
