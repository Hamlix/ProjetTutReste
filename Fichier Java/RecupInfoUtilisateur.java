package com.example.projettutore;

import com.example.projettutore.ParametreConnexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class RecupInfoUtilisateur extends Thread{
	public String resultat;
    public static final String chemin_acces_script= "http://"+ ParametreConnexion.adresseIP+"/projet/ScriptJeu/recupDonnerUtilisateur.php";
	public void run(){
        try{
            URL url = new URL(chemin_acces_script+"?login="+ParametreConnexion.login);
            URLConnection connexion = url.openConnection();
            connexion.setDoInput(true);
            connexion.setDoOutput(true);
            ((HttpURLConnection) connexion).setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            int test;
            String ret = "";
            do {
                ret += reader.readLine();
                test = reader.read();
            } while (test != -1);
            Utilisateur u = new Utilisateur();
            u.transformationDonner(ret);
            System.out.println(ret);
            resultat = "ok";
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resultat = "p io";
        }catch(NullPointerException n){
            System.out.println(n.getMessage());
            resultat = "problem null pointer";
        }
    }
	/*@Override
	protected String doInBackground(String... params) {
			System.out.println("on tente de lancer le scripte");
        try{
        URL url = new URL(chemin_acces_script);

        URLConnection connexion = url.openConnection();
        resultat = "connexion ok";
        connexion.setDoInput(true);
        connexion.setDoOutput(false);
        ((HttpURLConnection) connexion).setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connexion.getInputStream()));
        int test;
        String ret = "";
        do {
            ret += reader.readLine();
            test = reader.read();
        } while (test != -1);
           System.out.println(ret);
            resultat += ret;
        return ret;

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
            resultat += "problem";
        return "probleme";
    }catch(NullPointerException n){
        System.out.println(n.getMessage());
            resultat += "problem";
        return "probleme";
    }
	}*/
}
