package com.app.huru.activity.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Adapteur de fragment utilisé pour la HomeActivity
 * Permet de mettre en place les différents fragments
 *
 * */
public class FragmentAdapter extends FragmentPagerAdapter {

    private int numberOfPages;

    public FragmentAdapter(@NonNull FragmentManager manager, int numberOfPages) {

        super(manager);
        this.numberOfPages = numberOfPages;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new Fragment();

        switch (position){
            case 0:

                fragment = new HomeFragment();

            break;
            case 1:

                fragment = new HobbieFragment();

                break;
            case 2:

                fragment = new ActivityFragment();

                break;
            case 3:

                fragment = new MoodFragment();

                break;
            case 4:

                fragment = new StatsFragment();

                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return this.numberOfPages;
    }

}
