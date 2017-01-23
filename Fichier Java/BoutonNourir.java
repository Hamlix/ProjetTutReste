package com.example.projettutore;

import android.view.View;
import android.widget.Toast;

import org.json.simple.JSONValue;

/**
 * Created by Alexandre on 14/03/2015.
 */
public class BoutonNourir implements View.OnClickListener {

    private MainActivity main;
    private int idU;
    public BoutonNourir(MainActivity mainActivity,int id) {
        main = mainActivity;
        idU = id;
    }

    @Override
    public void onClick(View v) {
        RecupObjetNourir recup = new RecupObjetNourir(idU);
        recup.execute();
        while(recup.resultat.equals(""));
        if(!recup.resultat.equals("ERREUR SQL")) {
            Object obj = JSONValue.parse(recup.resultat);
            org.json.simple.JSONArray array = (org.json.simple.JSONArray) obj;
            int i = 0;
            if (array.size() > 0) {
                ObjetUtilisateur[] objetN = new ObjetUtilisateur[array.size()];
                do {
                    org.json.simple.JSONObject obj2 = (org.json.simple.JSONObject) array.get(i);
                    int idO = Integer.parseInt(obj2.get("idO").toString());
                    String nomO = obj2.get("nomO").toString();
                    String descriptionO = obj2.get("descriptionO").toString();
                    int moralO = Integer.parseInt(obj2.get("moralO").toString());
                    int propreteO = Integer.parseInt(obj2.get("propreteO").toString());
                    int experience = Integer.parseInt(obj2.get("experience").toString());
                    int quantiteO = Integer.parseInt(obj2.get("quantiteO").toString());
                    int limitationUtilisationO = Integer.parseInt(obj2.get("limitationUtilisationO").toString());
                    int satieteO = Integer.parseInt(obj2.get("satieteO").toString());
                    int nbUse = Integer.parseInt(obj2.get("nbUse").toString());
                    objetN[i] = new ObjetUtilisateur(idO, nomO, descriptionO, moralO, propreteO, experience,0, quantiteO, limitationUtilisationO, idU, satieteO,nbUse,0);
                    i++;
                } while (i < array.size());
                main.listeObjetNourrir(objetN);
            }
            else
                Toast.makeText(main, "Vous n\'avez pas d\'objets:( ", Toast.LENGTH_LONG).show();
        }

    }
}
