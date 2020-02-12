package com.app.huru.activity.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.huru.R;
import com.app.huru.activity.ActivityGUI;



/**
 * Fragment pour les statistiques
 * */
public class StatsFragment extends Fragment implements ActivityGUI {

    private int layout;
    private View parentView;

    private WebView webview;

    public StatsFragment(){
        super();

        this.layout = R.layout.stats_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.parentView = inflater.inflate(this.layout, container, false);
        webview = this.parentView.findViewById(R.id.chartView);

        webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        webview.getSettings().setJavaScriptEnabled(true);

        webview.addJavascriptInterface(new WebAppStatsInterface(this.getContext()), "Android");
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webview.getSettings().setLoadWithOverviewMode(true);

        webview.loadUrl("file:///android_asset/chart.html");

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
    }

    /**
     * Classe utilisée pour communiquer avec la webview des statistiques
     * */
    private class WebAppStatsInterface {

       private Context context;


        public WebAppStatsInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public int getNum1() {
            return 20;
        }

        @JavascriptInterface
        public int getNum2() {
            return 10;
        }

        @JavascriptInterface
        public int getNum3() {
            return 20;
        }

        @JavascriptInterface
        public int getNum4() {
            return 60;
        }

        @JavascriptInterface
        public int getNum5() {
            return 40;
        }
    }
}


