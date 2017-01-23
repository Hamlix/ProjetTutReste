package com.example.projettutore;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
public class FinirInscription extends AsyncTask<String, String, String> {
    public String resultat;
    private String nom,email;
    private int indiceR,indiceC,idU;
    private static final String chemin_acces_script= "http://"+ParametreConnexion.adresseIP+"/projet/ScriptJeu/creation_equide_1.php";
    private static String lien_envoyerMail = "http://"+ParametreConnexion.adresseIP+"/projet/ScriptJeu/envoyerMailConfirmation.php";
    public FinirInscription(String n, int ir, int ic,int id,String e) {
        resultat = "";
        nom = n;
        indiceR = ir+1;
        indiceC = ic+1;
        idU=id;
        email = e;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(chemin_acces_script+
                    "?idU="+idU+"&nom="+nom+"&robe="+indiceR+"&crin="+indiceC);
            URLConnection connexion = url.openConnection();
            System.out.println(url.toString());
            connexion.setDoInput(true);
            connexion.setDoOutput(true);
            ((HttpURLConnection) connexion).setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            int test;
            do {
                resultat = reader.readLine();
                test = reader.read();
            } while (test != -1);
            System.out.println(resultat);
            if(resultat.equals("OK")) {
                resultat ="";
                url = new URL(lien_envoyerMail +
                        "?idU=" + idU + "&destinataire=" + email);
                System.out.println(url.toString());
                connexion = url.openConnection();
                connexion.setDoInput(true);
                connexion.setDoOutput(true);
                ((HttpURLConnection) connexion).setRequestMethod("GET");
                reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
                test = 0;
                do {
                    resultat = reader.readLine();
                    test = reader.read();
                } while (test != -1);
            }
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
