package com.section27.movieappfragment.fragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.section27.movieappfragment.MainActivity;
import com.section27.movieappfragment.R;
import com.section27.movieappfragment.adapter.MovieAdapter;
import com.section27.movieappfragment.databinding.FragmentPopulareMovieBinding;
import com.section27.movieappfragment.model.Movie;
import com.section27.movieappfragment.model.MovieRepository;
import com.section27.movieappfragment.view.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopularMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularMovieFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<Movie> movieArrayList = new ArrayList<>();

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private FragmentPopulareMovieBinding fragmentPopulareMovieBinding;
    private MovieViewModel movieViewModel;
    private  int page =1;


    private String mParam1;
    private String mParam2;

    public PopularMovieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment populareMovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PopularMovieFragment newInstance(String param1, String param2) {
        PopularMovieFragment fragment = new PopularMovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPopulareMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_populare_movie, container, false);
        View view = fragmentPopulareMovieBinding.getRoot();
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        recyclerView = fragmentPopulareMovieBinding.rvMovies;
        setupRecyclerView();
        getPopularMovies(page);
        return view;
    }

    private void getPopularMovies(int pageTemp) {

        movieViewModel.getMutableLiveData(MovieRepository.POPULAR_MOVIE , pageTemp).observe(getActivity(), new Observer<List<Movie>>() {
        @Override
        public void onChanged(List<Movie> moviesFromLiveData) {
            if (page == 1) {
                movieArrayList.clear();
            }
            removeDuplicate(moviesFromLiveData, movieArrayList);
            movieAdapter.notifyDataSetChanged();
            page++;
        }
    });

    }

    private List<Movie> removeDuplicate(List<Movie> duplicateList, ArrayList<Movie> noneDuplicateList) {
        for (Movie movie : duplicateList) {
            if (!noneDuplicateList.contains(movie)) {
                noneDuplicateList.add(movie);
            }
        }
        return noneDuplicateList;
    }

    private void setupRecyclerView() {
        movieAdapter = new MovieAdapter(getContext().getApplicationContext(), movieArrayList);

        int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 2 : 4;
        fragmentPopulareMovieBinding.rvMovies.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        fragmentPopulareMovieBinding.rvMovies.setItemAnimator(new DefaultItemAnimator());
        fragmentPopulareMovieBinding.rvMovies.setAdapter(movieAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                        getPopularMovies(page);
                }
            }}
        );
    }


}