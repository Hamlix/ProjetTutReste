package com.example.projettutore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class Connexion extends Thread{
    public String resultat ="";
    public static final String chemin_acces_script= "http://"+ParametreConnexion.adresseIP+"/Users/Hamlix/Desktop/scripts/connexion.php";
    public void run() {
        try {
            URL url = new URL(chemin_acces_script+
                    "?login="+ParametreConnexion.login+"&mdp="+ParametreConnexion.mdp);
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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resultat = "p io";
        } catch (NullPointerException n) {
            System.out.println(n.getMessage());
            resultat = "problem null pointer";
        }
    }
}
