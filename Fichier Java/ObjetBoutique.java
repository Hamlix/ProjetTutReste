package com.example.projettutore;

/**
 * Created by Alexandre on 14/03/2015.
 */
public class ObjetBoutique {
    private int idO;
    private String nomO;
    private String descriptionO;
    private int satieteO;
    private int moralO;
    private int propreteO;
    private int missionMinO;
    private int lvlMinPoO;
    private int limitationNbO;
    private int prixO;
    private int limitationUtilisationO;
    private int experience;
    public ObjetBoutique(int id, String nom, String description, int satiete, int moral, int proprete, int missionMin, int lvlMinPo, int limitationNb, int experience, int prix, int limitationUtilisation){
        idO = id;
        nomO=nom;
        descriptionO= description;
        satieteO = satiete;
        moralO=moral;
        propreteO=proprete;
        missionMinO = missionMin;
        lvlMinPoO = lvlMinPo;
        limitationNbO = limitationNb;
        this.experience=experience;
        prixO = prix;
        limitationUtilisationO =limitationUtilisation;
    }

    public String getNomO() {
        return nomO;
    }

    public int getPrixO() {
        return prixO;
    }

    public String getDescription() {
        return descriptionO;
    }

    public int getSatiete() {
        return satieteO;
    }

    public int getProprete() {
        return propreteO;
    }

    public int getExperience() {
        return experience;
    }

    public int getnbLimiteObjet() {
        return limitationNbO;
    }

    public int getnbUse() {
        return limitationUtilisationO;
    }

    public int getMoral() {
        return moralO;
    }

    public int getId() {
        return idO;
    }
}
