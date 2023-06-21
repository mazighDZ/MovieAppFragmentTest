package com.section27.movieappfragment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.section27.movieappfragment.R;
import com.section27.movieappfragment.databinding.MovieListItemBinding;
import com.section27.movieappfragment.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyMovieViewHolder> {
private Context context;
private ArrayList<Movie> movieArrayList;


    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MyMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.movie_list_item,
                parent,
                false);

        return new MyMovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMovieViewHolder holder, int position) {

            Movie movie = movieArrayList.get(position);
            holder.movieListItemBinding.setMovie(movie);

    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MyMovieViewHolder extends RecyclerView.ViewHolder{

        private MovieListItemBinding movieListItemBinding;

        public MyMovieViewHolder( MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;


        }



    }

}
