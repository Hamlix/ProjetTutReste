package com.example.projettutore;

/**
 * Created by Alexandre on 16/03/2015.
 */
public class Equide {
    private int id,couleurR, couleurC, couleurY, exp, niveau, moral, satiete,proprete,etat, nbNourrit, nbLaver, nbCaresse, nbJoue, nbMiniJeu,nbMort,idU;
    private String nom,stade;
    public Equide(int id,String nom,String stade, int couleurR, int couleurC, int couleurY, int exp, int niveau, int moral, int satiete,
                  int proprete, int etat, int nbNourrit, int nbLaver, int nbCaresse, int nbJoue, int nbMiniJeu, int nbMort, int idU){
        this.id = id;
        this.nom = nom;
        this.stade = stade;
        this.couleurR = couleurR;
        this.couleurC = couleurC;
        this.couleurY = couleurY;
        this.exp = exp;
        this.niveau = niveau;
        this.moral = moral;
        this.satiete = satiete;
        this.proprete = proprete;
        this.etat = etat;
        this.nbNourrit = nbNourrit;
        this.nbCaresse = nbCaresse;
        this.nbLaver = nbLaver;
        this.nbJoue= nbJoue;
        this.nbMiniJeu = nbMiniJeu;
        this.nbMort = nbMort;
        this.idU = idU;
    }

    public int getExp() {
        return exp;
    }

    public int getSatiete() {
        return satiete;
    }

    public int getMoral() {
        return moral;
    }

    public int getProprete() {
        return proprete;
    }

    public int getCouleurR() {
        return couleurR;
    }

    public int getCouleurC() {
        return couleurC;
    }

    public void setSatiete(int satiete) {
        this.satiete = satiete;
    }

    public String getStade() {
        return stade;
    }

    public int getNiveau() {
        return niveau;
    }
}
