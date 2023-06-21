package com.section27.movieappfragment.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.section27.movieappfragment.R;
import com.section27.movieappfragment.service.MovieDataService;
import com.section27.movieappfragment.service.RetrofiteInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    public  static  int POPULAR_MOVIE = 1;
    public  static  int TOP_MOVIE = 2;
    public  static  int POPULAR_SERIES = 3;
    // for GetHub
    private static final String API_KEY = System.getenv("themoviedb_API_KEY");
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Movie> foundMovies = new ArrayList<>();


    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> mutableLiveDataSearch = new MutableLiveData<>();

    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData (int  typeMovie , int page){
        MovieDataService movieDataService = RetrofiteInstance.getService();
        Call<MovieResponse> call;
        if(typeMovie == POPULAR_SERIES){
            call = movieDataService.getPopularSeries(API_KEY,page);
        }else if (typeMovie == TOP_MOVIE){
            call = movieDataService.getTopMovies(API_KEY,page);
        }else {
            call = movieDataService.getPopularMovies(API_KEY,page);

        }
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse result = response.body();

                if(result != null && result.getMovies() != null){
                    movies = (ArrayList<Movie>) result.getMovies();
                    mutableLiveData.setValue(movies);

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.i("MyTAG" ,"response Failed ");
            }
        });

        return mutableLiveData;
    }

    // data call when searching Movie
    public  MutableLiveData<List<Movie>> getMutableLiveDataSearch(String movieName , int page){
        MovieDataService movieSearchService = RetrofiteInstance.getService();
        Call<MovieResponse> call = movieSearchService.getSearchedMovie(API_KEY,movieName,page);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse result = response.body();
                if(result!= null && result.getMovies()!= null){
                    foundMovies = (ArrayList<Movie>) result.getMovies();
                    mutableLiveDataSearch.setValue(foundMovies);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
        return mutableLiveDataSearch;
    }
}
