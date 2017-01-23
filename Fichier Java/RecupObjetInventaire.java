package com.example.projettutore;
import android.os.AsyncTask;

import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
/**
 * Created by Hamlix on 23/03/2015.
 */
public class RecupObjetInventaire extends AsyncTask<String, Integer, String>{
    private static final String chemin_acces_script= "http://"+ParametreConnexion.adresseIP+"/projet/ScriptJeu/inventaire.php";
    private int idU;
    public String resultat;
    public RecupObjetInventaire(int id){
        idU=id;
        resultat="";
    }
    @Override
    protected String doInBackground(String... params) {
        try{
            URL url = new URL(chemin_acces_script+
                    "?idU="+idU);
            URLConnection connexion = url.openConnection();
            System.out.println(url.toString());
            connexion.setDoInput(true);
            connexion.setDoOutput(true);
            ((HttpURLConnection) connexion).setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            int test;
            do {
                resultat += reader.readLine();
                test = reader.read();
            } while (test != -1);
            System.out.println(resultat);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resultat = "p io";
        } catch (NullPointerException n) {
            System.out.println(n.getMessage());
            resultat = "problem null pointer";
        }
        return null;
    }
}
