package com.app.huru.activity.fragment;

import android.content.Context;
import android.graphics.Color;
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
import com.app.huru.model.Mood;
import com.app.huru.model.Stats;
import com.app.huru.service.MoodService;
import com.app.huru.service.StatsService;

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

    private Button weekButton;
    private Button monthButton;
    private Button totalButton;

    private boolean updateFragment = true;

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

        this.updateFragment = true;

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
        this.weekButton = this.parentView.findViewById(R.id.weekButton);
        this.monthButton = this.parentView.findViewById(R.id.monthButton);
        this.totalButton = this.parentView.findViewById(R.id.totalButton);
    }

    private void updateWebView(){

        this.webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        this.webview.getSettings().setJavaScriptEnabled(true);

        this.webview.addJavascriptInterface(new WebAppStatsInterface(this.getContext()), "Android");
        this.webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        this.webview.getSettings().setLoadWithOverviewMode(true);

        this.webview.loadUrl("file:///android_asset/chart.html");
    }

    private int getStatsOf(String moodName){

        float res = 0.0f;

        int total = 0;
        int count = 0;

        List<Stats> stats = this.statsService.getAllStats();
        total = stats.size();

        if(total == 0){

            return (int) res;
        }

        for(Stats stat : stats){

            if(stat.getMoodId() == this.moodService.getMoodByName(moodName).getId()){
                count ++;
            }
        }

        res = ( count / (float) total ) * 100;

        return (int) res;
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
            return getStatsOf("Content(e)");
        }
        //colere
        @JavascriptInterface
        public int getNum2() {
            return getStatsOf("En colère");
        }
        //tristesse
        @JavascriptInterface
        public int getNum3() {
            return getStatsOf("Triste");
        }
        //fatigue
        @JavascriptInterface
        public int getNum4() {
            return getStatsOf("Fatigué(e)");
        }
        //stress
        @JavascriptInterface
        public int getNum5() {
            return getStatsOf("Stressé(e)");
        }
    }
}


