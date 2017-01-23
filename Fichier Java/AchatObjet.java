package com.example.projettutore;



import android.view.View;
import android.widget.Toast;

/**
 * Created by Hamlix on 22/03/2015.
 */
public class AchatObjet implements View.OnClickListener {
    private int u;
    private int o;
    private int prix;
    private int nbMax;
    private MainActivity main;
    public AchatObjet(int idU, int idO, int prixO,int nbLimiteObjet, MainActivity m) {
        this.u =idU;
        o=idO;
        prix=prixO;
        main=m;
        nbMax=nbLimiteObjet;
    }

    @Override
    public void onClick(View v) {
        AchatObjetOK recup = new AchatObjetOK(u,o,prix,nbMax);
        recup.execute();
        while (recup.resultat.equals(""));
        if (!recup.resultat.equals("ERREUR SQL")) {
            if (recup.resultat.equals("OK"))
                Toast.makeText(main, "Achat bien réussi.  ", Toast.LENGTH_LONG).show();
            else if (recup.resultat.equals("OK2"))
                Toast.makeText(main, "Mission validé. ", Toast.LENGTH_LONG).show();
            else if (recup.resultat.equals("NOP"))
                Toast.makeText(main, "Vous avez déjà trop de cet objet ! ", Toast.LENGTH_LONG).show();
            else if (recup.resultat.equals("NOP2"))
                Toast.makeText(main, "Vous n'avez pas l'argent !", Toast.LENGTH_LONG).show();
        }else
                Toast.makeText(main, "L'achat n'a pas pu être fait.  ", Toast.LENGTH_LONG).show();
    }
}