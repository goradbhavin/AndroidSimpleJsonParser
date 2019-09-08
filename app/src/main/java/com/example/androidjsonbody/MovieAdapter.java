package com.example.androidjsonbody;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList=new ArrayList<>();
    private Context context;

    public MovieAdapter(Context context,List<Movie> movieList){

        this.context = context;
        this.movieList = movieList;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movies,viewGroup,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.tvMovieName.setText(movieList.get(position).getMovie());
        Glide.with(context).load(movieList.get(position).getImage()).into(holder.ivMovie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

     class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView ivMovie;
        TextView tvMovieName;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMovie = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvMovieName = (TextView) itemView.findViewById(R.id.tv_movie_name);
        }
    }
}
