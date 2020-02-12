package com.app.huru.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.huru.R;
import com.app.huru.activity.ActivityGUI;

/**
 * Fragment concernant les humeurs
 * */
public class MoodFragment extends Fragment implements ActivityGUI {

    private int layout;

    private View parentView;

    public MoodFragment(){
        super();
        this.layout = R.layout.mood_fragment;
    }
    @Override
    public void setupGUI() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.parentView = inflater.inflate(this.layout, container, false);

        return this.parentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
    }


}
