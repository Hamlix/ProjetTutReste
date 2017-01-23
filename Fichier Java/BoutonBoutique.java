package com.example.projettutore;

import android.view.View;

import org.json.simple.JSONValue;

/**
 * Created by Alexandre on 17/03/2015.
 */
public class BoutonBoutique implements View.OnClickListener {
    private int idU;
    private MainActivity main;
    public BoutonBoutique(int id, MainActivity m) {
        idU=id;
        main=m;
    }

    @Override
    public void onClick(View v) {
        RecupObjetBoutique recup = new RecupObjetBoutique(idU);
        recup.execute();
        while (recup.resultat.equals(""));
        if (!recup.resultat.equals("ERREUR SQL")) {
            int i = 0;
            Object obj = JSONValue.parse(recup.resultat);
            org.json.simple.JSONArray array = (org.json.simple.JSONArray) obj;
            if (array.size() > 0) {
                ObjetBoutique[] objet = new ObjetBoutique[array.size()];
                do {
                    org.json.simple.JSONObject obj2 = (org.json.simple.JSONObject) array.get(i);
                    int id = Integer.parseInt(obj2.get("idO").toString());
                    String nom = obj2.get("nomO").toString();
                    String description = obj2.get("descriptionO").toString();
                    int experience = Integer.parseInt(obj2.get("experience").toString());
                    int moral = Integer.parseInt(obj2.get("moralO").toString());
                    int satiete = Integer.parseInt(obj2.get("satieteO").toString());
                    int proprete = Integer.parseInt(obj2.get("propreteO").toString());
                    int limitationNb = Integer.parseInt(obj2.get("limitationNbO").toString());
                    int prix = Integer.parseInt(obj2.get("prixO").toString());
                    int limitationUtilisation = Integer.parseInt(obj2.get("limitationUtilisationO").toString());
                    objet[i] = new ObjetBoutique(id, nom, description, satiete, moral, proprete, 0, 0, limitationNb,experience, prix, limitationUtilisation);
                    i++;
                } while (i < array.size());
                main.afficherBoutique(objet);
            }
        }
    }
}
