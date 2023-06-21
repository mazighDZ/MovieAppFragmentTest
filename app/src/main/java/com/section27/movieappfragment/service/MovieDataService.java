package com.section27.movieappfragment.service;

import com.section27.movieappfragment.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  MovieDataService {

    @GET("3/movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apikey , @Query("page") int page);// we will set apikey in Result Class

    @GET("3/movie/top_rated")
    Call<MovieResponse>  getTopMovies(@Query("api_key") String apikey, @Query("page") int page);

    @GET("3/tv/popular")
    Call<MovieResponse> getPopularSeries(@Query("api_key") String apikey,  @Query("page") int page);
    @GET("3/search/movie")
    Call<MovieResponse> getSearchedMovie(@Query("api_key") String apikey, @Query("query") String query ,@Query("page") int page );

}

