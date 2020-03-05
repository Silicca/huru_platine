package com.app.huru.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.huru.R;
import com.app.huru.activity.ActivityGUI;
import com.app.huru.model.Stats;
import com.app.huru.service.MoodService;
import com.app.huru.service.StatsService;
import com.app.huru.tools.DateFormatter;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Fragment pour les statistiques
 * */
public class StatsFragment extends Fragment implements ActivityGUI {

    private int layout;
    private View parentView;

    private WebView webview;

    private StatsService statsService;
    private MoodService moodService;

    private Button dayButton;
    private Button monthButton;
    private Button totalButton;

    private String searchingDate;

    public StatsFragment(){
        super();

        this.layout = R.layout.stats_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.parentView = inflater.inflate(this.layout, container, false);

        this.statsService = new StatsService(this.parentView.getContext());
        this.moodService = new MoodService(this.parentView.getContext());
        this.searchingDate = null;

        this.setupGUI();

        this.updateWebView();

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

    @Override
    public void setupGUI() {

        this.webview = this.parentView.findViewById(R.id.chartView);
        this.dayButton = this.parentView.findViewById(R.id.dayButton);
        this.monthButton = this.parentView.findViewById(R.id.monthButton);
        this.totalButton = this.parentView.findViewById(R.id.totalButton);

        this.dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchingDate = DateFormatter.dateToString(new Date());
                Snackbar.make(v, "Statistiques du jour.", 1000).show();
                updateWebView();
            }
        });

        this.monthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dateString = DateFormatter.dateToString(new Date());
                searchingDate = dateString.substring(2, dateString.length());
                Snackbar.make(v, "Statistiques du mois.", 1000).show();
                updateWebView();
            }
        });

        this.totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchingDate = null;
                Snackbar.make(v, "Statistiques globales.", 1000).show();
                updateWebView();
            }
        });
    }

    private void updateWebView(){

        this.webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        this.webview.getSettings().setJavaScriptEnabled(true);

        this.webview.addJavascriptInterface(new WebAppStatsInterface(this.getContext()), "Android");
        this.webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        this.webview.getSettings().setLoadWithOverviewMode(true);

        this.webview.loadUrl("file:///android_asset/chart.html");
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        if(menuVisible){

            if(this.webview != null) {
                this.updateWebView();
            }
        }
    }

    /**
     * Classe utilisée pour communiquer avec la webview des statistiques
     * */
    private class WebAppStatsInterface {

        private Context context;


        public WebAppStatsInterface(Context context) {

            this.context = context;
        }

        //content
        @JavascriptInterface
        public int getNum1() {
            return statsService.getPercentOf("Content(e)", searchingDate);
        }
        //colere
        @JavascriptInterface
        public int getNum2() {
            return  statsService.getPercentOf("En colère", searchingDate);
        }
        //tristesse
        @JavascriptInterface
        public int getNum3() {
            return  statsService.getPercentOf("Triste", searchingDate);
        }
        //fatigue
        @JavascriptInterface
        public int getNum4() {
            return  statsService.getPercentOf("Fatigué(e)", searchingDate);
        }
        //stress
        @JavascriptInterface
        public int getNum5() {
            return  statsService.getPercentOf("Stressé(e)", searchingDate);
        }
    }
}


