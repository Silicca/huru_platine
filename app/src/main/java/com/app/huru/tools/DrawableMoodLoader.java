package com.app.huru.tools;

import com.app.huru.R;
import com.app.huru.model.Mood;

/**
 * Classe utilitaire permettant de charger la bonne image en fonction de l'humeur cible
 * */
public abstract class DrawableMoodLoader {

    public static int load(Mood mood){

        int moodImageDrawable = 0;

        switch (mood.getMoodName()){

            case "Content(e)":
                moodImageDrawable = R.drawable.content;
                break;
            case "Triste":
                moodImageDrawable = R.drawable.triste;
                break;
            case "Fatigué(e)":
                moodImageDrawable = R.drawable.fatigue;
                break;
            case "Stressé(e)":
                moodImageDrawable = R.drawable.stress;
                break;
            case "En colère":
                moodImageDrawable = R.drawable.colere;
                break;
        }

        return moodImageDrawable;
    }
}
