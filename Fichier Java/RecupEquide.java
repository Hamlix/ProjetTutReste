package com.example.projettutore;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alexandre on 16/03/2015.
 */
public class RecupEquide extends AsyncTask<String, Integer, String> {
    private static final String chemin_acces_script= "http://"+ParametreConnexion.adresseIP+"/projet/ScriptJeu/recupEquide.php";
    private int idU;
    private String resultat;
    private ProgressBar barExp,barFaim,barMoral,barPropre;
    public boolean attendre;
    private MainActivity main;
    public RecupEquide(int id, ProgressBar be,ProgressBar bf,ProgressBar bm, ProgressBar bp,MainActivity m) {
        idU=id;
        resultat="";
        barExp = be;
        barFaim = bf;
        barMoral = bm;
        barPropre = bp;
        main = m;
        attendre=true;
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
                resultat = reader.readLine();
                test = reader.read();
            } while (test != -1);
            System.out.println(resultat);
            if(!resultat.equals("ERREUR SQL")) {
                Object obj = JSONValue.parse(resultat);
                org.json.simple.JSONArray array = (org.json.simple.JSONArray) obj;
                if (array.size() > 0) {
                    Equide[] equide = new Equide[array.size()];
                        org.json.simple.JSONObject obj2 = (org.json.simple.JSONObject) array.get(0);
                        int id = Integer.parseInt(obj2.get("idE").toString());
                        String nom = obj2.get("nomE").toString();
                        String stade = obj2.get("stadeE").toString();
                        int couleurR = Integer.parseInt(obj2.get("couleurRobeE").toString());
                        int couleurC = Integer.parseInt(obj2.get("couleurCrinE").toString());
                        int couleurY = Integer.parseInt(obj2.get("couleurYeuxE").toString());
                        int experience = Integer.parseInt(obj2.get("experienceE").toString());
                        int niveau = Integer.parseInt(obj2.get("niveauE").toString());
                        int moral = Integer.parseInt(obj2.get("moralE").toString());
                        int satiete = Integer.parseInt(obj2.get("satieteE").toString());
                        int proprete = Integer.parseInt(obj2.get("propreteE").toString());
                        int etat = Integer.parseInt(obj2.get("etatE").toString());
                        int nbNourrit = Integer.parseInt(obj2.get("nbNourrit").toString());
                        int nbLave = Integer.parseInt(obj2.get("nbLave").toString());
                        int nbCaresse = Integer.parseInt(obj2.get("nbCaresse").toString());
                        int nbJoue = Integer.parseInt(obj2.get("nbJoue").toString());
                        int nbMiniJeu = Integer.parseInt(obj2.get("nbMinijeu").toString());
                        int nbMort = Integer.parseInt(obj2.get("nbMort").toString());
                        equide[0] = new Equide(id, nom, stade, couleurR, couleurC, couleurY, experience, niveau, moral,
                                satiete, proprete, etat, nbNourrit, nbLave, nbCaresse, nbJoue, nbMiniJeu, nbMort, Utilisateur.idU);
                        publishProgress(experience,satiete,moral,proprete);
                    main.setEquide(equide);
                    attendre = false;
                }

            }

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
    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        barExp.setProgress(values[0]);
        barFaim.setProgress(values[1]);
        barMoral.setProgress(values[2]);
        barPropre.setProgress(values[3]);
        // mProgressBar.setProgress(values[0]);
    }
}
