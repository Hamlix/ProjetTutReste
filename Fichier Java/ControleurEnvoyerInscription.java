package com.example.projettutore;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ControleurEnvoyerInscription implements View.OnClickListener {
    private MainActivity main;
    private EditText[] information;
    public ControleurEnvoyerInscription(MainActivity m, EditText[] info) {
        main = m;
        information = info;
    }

    @Override
    public void onClick(View v) {
        String login = information[0].getText().toString();
        String mdp = information[1].getText().toString();
        String email = information[2].getText().toString();
        if(!login.equals("") && !mdp.equals("") && !email.equals("")) {
            InscriptionUtilisateur i = new InscriptionUtilisateur(login, mdp, email);
            i.execute();
            while (i.resultat.equals("")) ;
            if (!i.resultat.equals("Erreur SQL ")) {
                main.creationPoney(Integer.parseInt(i.resultat), email);
            } else {
                Toast.makeText(main, "Login ou email déjà utilisé:( ", Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(main, "Certains champs sont vides merci de les remplir:( ", Toast.LENGTH_LONG).show();
    }
}
