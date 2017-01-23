package com.example.projettutore;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Alexandre on 04/03/2015.
 */
public class ChangerCouleur implements View.OnClickListener {
    private TextView texte;
    private int etat;
    private int indice;
    private MainActivity main;
    public ChangerCouleur(TextView t, int e,MainActivity m) {
        texte =t;
        etat =e;
        main = m;
    }

    @Override
    public void onClick(View v) {
        if(etat ==0 ||etat==2)
            main.changerCouleurRobe(texte,etat);
        else
            main.changerCouleurCrin(texte,etat);
    }

}
