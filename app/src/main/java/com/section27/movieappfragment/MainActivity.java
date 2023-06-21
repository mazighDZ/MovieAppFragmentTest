package com.section27.movieappfragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.section27.movieappfragment.fragment.PopularMovieFragment;
import com.section27.movieappfragment.fragment.PopularSeriesFragment;
import com.section27.movieappfragment.fragment.TopMovieFragment;
//import com.section27.movieappfragment.fragment.TopMovieFragment;
//import com.section27.movieappfragment.fragment.PopularSeriesFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int selectedTabPosition = tab.getPosition();
                FragmentManager fragmentManager= getSupportFragmentManager();

                switch (selectedTabPosition){
                    case 0:
                        // Handle the first tab selection
                        fragmentManager.beginTransaction()
                                .replace(R.id.mainFragment , new PopularMovieFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 1:
                        // Handle the second tab selection
                        fragmentManager.beginTransaction()
                                .replace(R.id.mainFragment , new TopMovieFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 2:
                        // Handle the second tab selection
                        fragmentManager.beginTransaction()
                                .replace(R.id.mainFragment , new PopularSeriesFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}
