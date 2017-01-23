package com.example.projettutore;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.StringTokenizer;

public class BoutonFinirInscription implements View.OnClickListener {
    private MainActivity main;
    private EditText nom;
    private int idU;
    private String email;
    public BoutonFinirInscription(MainActivity m, EditText n,int id, String e) {
        main = m;
        nom=n;
        idU = id;
        email =e;
    }

    @Override
    public void onClick(View v) {
        String n = nom.getText().toString();
        if(!n.equals("")) {
            FinirInscription f = new FinirInscription(suppressionEspace(n), main.indiceRobe, main.indiceCrin, idU, email);
            f.execute();
            while (f.resultat.equals("")) ;
            if (f.resultat.equals("Message bien envoye")) {
                main.connexion();
                Toast.makeText(main, "Inscription réussie :), un mail vous a \u00e9t\u00e9 envoy\u00e9 ", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(main, "Impossible de créer le poulain :( ", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(main, "Veuillez choisir un nom pour votre poulain:( ", Toast.LENGTH_LONG).show();
    }
    public String suppressionEspace(String chaine){
        StringTokenizer st = new StringTokenizer(chaine, " ");
            chaine = st.nextToken();
            while (st.hasMoreTokens())
                chaine +="%20"+st.nextToken();
        return chaine;
    }
}
