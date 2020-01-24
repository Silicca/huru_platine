package com.app.huru.activity.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Adapteur de fragment utilisé pour la HomeActivity
 * Permet de mettre en place les différentes fragments
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

        Fragment fragment = null;

        switch (position){
            case 0:

                fragment = new HomeFragment();

            break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return this.numberOfPages;
    }
}
