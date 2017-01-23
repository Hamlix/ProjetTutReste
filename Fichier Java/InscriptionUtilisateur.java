package com.example.projettutore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import android.os.AsyncTask;
public class InscriptionUtilisateur extends AsyncTask<String, String, String>{
    private String login;
    private String mdp;
    private String email;
    public String resultat;
    public static final String chemin_acces_script= "http://"+ParametreConnexion.adresseIP+"/projet/ScriptJeu/inscription.php";
    public InscriptionUtilisateur(String l, String m, String e) {
        login= l;
        mdp = m;
        email = e;
        resultat="";
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(chemin_acces_script+
                    "?login="+login+"&mdp="+mdp+"&email="+email);
            URLConnection connexion = url.openConnection();
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
