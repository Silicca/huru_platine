package com.app.huru.activity;

import android.os.Bundle;
import android.widget.TableLayout;
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

    private TextView usernameView;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    private static final int NUMBER_OF_PAGES = 5;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.homepage_activity_layout);

        this.setupGUI();
    }

    @Override
    public void setupGUI() {

        this.tabLayout = findViewById(R.id.tabLayout);

        this.usernameView = findViewById(R.id.homepage_username);

        String username = getIntent().getExtras().getString("username");

        this.usernameView.setText("Bonjour "+username+" !");

        this.viewPager = findViewById(R.id.home_viewpager);
        this.viewPager.setAdapter(new FragmentAdapter(this.getSupportFragmentManager(), NUMBER_OF_PAGES));

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
