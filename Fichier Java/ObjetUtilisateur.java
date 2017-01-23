package com.example.projettutore;

/**
 * Created by Alexandre on 14/03/2015.
 */
public class ObjetUtilisateur {
    private int boutonUtilU;
    private int idO;
     private String nomO, descriptionO;
     private int moralO, propreteO,experience, limiteNbO, quantiteO, limitationUtilisationO,idU,satieteO,nbUseO;
     public ObjetUtilisateur(int id,String nom, String description, int moral, int proprete, int exp, int limiteNb, int quantite, int limitationUtilisation,int idU, int satiete, int nbUse,int bouton) {
         idO = id;
         nomO = nom;
         descriptionO = description;
         moralO = moral;
         propreteO = proprete;
         experience = exp;
         limiteNbO = limiteNb;
         quantiteO = quantite;
         limitationUtilisationO = limitationUtilisation;
         this.idU = idU;
         nbUseO = nbUse;
         satieteO = satiete;
         boutonUtilU = bouton;
     }

    public String getNomO() {
        return nomO;
    }

    public int getSatieteO() {
        return satieteO;
    }

    public String getDescriptionO() {
        return descriptionO;
    }

    public int getMoralO() {
        return moralO;
    }

    public int getPropreteO() {
        return propreteO;
    }

    public int getQuantiteO() {
        return quantiteO;
    }

    public int getLimiteNbO() {
        return limiteNbO;
    }

    public int getLimitationUtilisationO() {
        return limitationUtilisationO;
    }

    public int getExperience() {
        return experience;
    }
    public int getBouton(){ return boutonUtilU; }

    public int getnbUse() {
        return nbUseO;
    }

    public int getId() {
        return idO;
    }
}

