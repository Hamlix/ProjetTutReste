package com.example.projettutore;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONValue;

import java.util.StringTokenizer;

public class ControleurConnexion implements OnClickListener {

	private MainActivity main;
	public ControleurConnexion(MainActivity m){
		main = m;
	}
	@Override
	public void onClick(View arg0) {
        if(!main.getEditLogin().getText().toString().equals("") && !main.getEditMdp().getText().toString().equals(""))
        {
            ParametreConnexion.login = main.getEditLogin().getText().toString();
            ParametreConnexion.mdp = main.getEditMdp().getText().toString();
            System.out.println(ParametreConnexion.login + "/" + ParametreConnexion.mdp);
            Connexion co = new Connexion();
            co.start();
            while (co.resultat.equals("")) ;
            StringTokenizer st = new StringTokenizer(co.resultat, "-");
            if (st.nextToken().equals("ok")) {
                Utilisateur.idU = Integer.parseInt(st.nextToken());

                        main.lancerPageAccueille();
                }
            else {
                if(co.resultat.equals("Inscription non valide")){
                    Toast.makeText(main, "Inscription non validée  ", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(main, "Login ou mot de passe incorrect ", Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(main, "Certains champs sont vides ", Toast.LENGTH_LONG).show();
	}

}
