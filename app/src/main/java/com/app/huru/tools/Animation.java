package com.app.huru.tools;

import android.view.View;
import android.view.animation.AlphaAnimation;
/**
 * Classe utilitaire pour les animations des recyclerview et autres
 * */
public abstract class Animation {

    public static void setAnimation(View view) {

        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }
}
