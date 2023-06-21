package com.section27.movieappfragment.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.section27.movieappfragment.model.Movie;
import com.section27.movieappfragment.model.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    private LiveData<List<Movie>> movies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public  LiveData<List<Movie>> getMutableLiveData(int typeMovie , int page ){
        return movieRepository.getMutableLiveData(typeMovie ,page);
    }
}
