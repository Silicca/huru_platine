package com.app.huru.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.app.huru.R;
import com.app.huru.activity.fragment.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * Activité principale de l'application
 * */
public class HomeActivity extends AppCompatActivity implements ActivityGUI{

    private static final int NUMBER_OF_PAGES = 5;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.homepage_activity_layout);

        this.setupGUI();
    }

    @Override
    public void setupGUI() {

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        TextView usernameView = findViewById(R.id.homepage_username);

        String username = getIntent().getExtras().getString("username");

        usernameView.setText("Bonjour "+username+" !");

        ViewPager viewPager = findViewById(R.id.home_viewpager);
        viewPager.setAdapter(new FragmentAdapter(this.getSupportFragmentManager(), NUMBER_OF_PAGES));

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.accueil96);
        tabLayout.getTabAt(1).setIcon(R.drawable.hobbiescoeur96);
        tabLayout.getTabAt(2).setIcon(R.drawable.activites96);
        tabLayout.getTabAt(3).setIcon(R.drawable.humeurs96);
        tabLayout.getTabAt(4).setIcon(R.drawable.stats96);

    }
}
