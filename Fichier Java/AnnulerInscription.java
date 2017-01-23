package com.example.projettutore;

import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alexandre on 09/03/2015.
 */
public class AnnulerInscription implements View.OnClickListener {
    private int id;
    private MainActivity main;
    public String resultat ="";
    private static final String chemin_acces_script= "http://"+ParametreConnexion.adresseIP+"/projet/ScriptJeu/suppressionU.php";
    public AnnulerInscription(int i, MainActivity m) {
        id=i;
        main=m;
    }

    @Override
    public void onClick(View v) {
        SuppressionU s = new SuppressionU(id);
        s.execute();
        main.connexion();
    }
}
