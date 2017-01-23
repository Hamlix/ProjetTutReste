package com.example.projettutore;
import org.json.simple.*;
public class Utilisateur {
    public static int idU;
	public static String nom="coucou";
	public static String email="blabla@adresse.fr";
	public static int nbOr = 300;
	public static int jourConnexion = 8;
	public static int succes = 3;
	public static int mission = 7;
	public static String nomEquide = "Carotte";
	public static int nbEquide = 1;
	public static int niveau = 3;
    public static String stade = "poulain";
    private String resultat;
	public int obtenirNiveau(){
		return niveau;
	}

    public void transformationDonner(String resultat){
        Object obj = JSONValue.parse(resultat);
        org.json.simple.JSONArray array = (org.json.simple.JSONArray) obj;
        int i=0;
        do {
            org.json.simple.JSONObject obj2 = (org.json.simple.JSONObject) array.get(i);
            //idU = Integer.parseInt(obj2.get("idU").toString());
            nom = obj2.get("nomU").toString();
            email = obj2.get("emailU").toString();
            nbOr = Integer.parseInt(obj2.get("orU").toString());
            jourConnexion = Integer.parseInt(obj2.get("nbJourCoU").toString());
            succes = Integer.parseInt(obj2.get("nbSuccesU").toString());
            mission = Integer.parseInt(obj2.get("nbMissionsU").toString());
            //Modifier le script afin de recuperer les informations sur l equide
            System.out.println(obj2.get("nomE").toString());
            nomEquide = obj2.get("nomE").toString();
            niveau = Integer.parseInt(obj2.get("niveauE").toString());
            i++;
        }while(i < array.size());
    }
}
