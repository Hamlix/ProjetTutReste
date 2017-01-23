package com.example.projettutore;

import android.view.View;

import org.json.simple.JSONValue;

/**
 * Created by Hamlix on 23/03/2015.
 */
public class BoutonInventaire implements View.OnClickListener {
    private int idU;
    private MainActivity main;
    public BoutonInventaire(int id, MainActivity m) {
            idU=id;
            main=m;
    }
    @Override
    public void onClick(View v) {
        RecupObjetInventaire recup = new RecupObjetInventaire(idU);
        recup.execute();
        while (recup.resultat.equals(""));
        if (!recup.resultat.equals("ERREUR SQL")) {
            int i = 0;
            Object obj = JSONValue.parse(recup.resultat);
            org.json.simple.JSONArray array = (org.json.simple.JSONArray) obj;
            if (array.size() > 0) {
                ObjetUtilisateur[] objet = new ObjetUtilisateur[array.size()];
                do {
                    org.json.simple.JSONObject obj2 = (org.json.simple.JSONObject) array.get(i);
                    int id = Integer.parseInt(obj2.get("idO").toString());
                    String nom = obj2.get("nomO").toString();
                    String description = obj2.get("descriptionO").toString();
                    int experience = Integer.parseInt(obj2.get("experience").toString());
                    int moral = Integer.parseInt(obj2.get("moralO").toString());
                    int satiete = Integer.parseInt(obj2.get("satieteO").toString());
                    int proprete = Integer.parseInt(obj2.get("propreteO").toString());
                    int quantite = Integer.parseInt(obj2.get("quantiteO").toString());
                    int nbUse = Integer.parseInt(obj2.get("nbUse").toString());
                    int bouton = Integer.parseInt(obj2.get("boutonUtilU").toString());
                    int limitationNb = Integer.parseInt(obj2.get("limiteNbO").toString());
                    int limitationUtilisation = Integer.parseInt(obj2.get("limitationUtilisationO").toString());
                    objet[i] = new ObjetUtilisateur(id, nom, description, moral, proprete,experience, limitationNb, quantite, limitationUtilisation,idU, satiete,nbUse,bouton );
                    i++;
                } while (i < array.size());
                main.afficherInventaire(objet);
            }
        }
    }
}
