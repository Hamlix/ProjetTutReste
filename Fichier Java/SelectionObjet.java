package com.example.projettutore;

import android.view.View;
import android.widget.AdapterView;
public class SelectionObjet implements AdapterView.OnItemClickListener {
    private MainActivity main;
    private ObjetUtilisateur[] listeObjet;
    public SelectionObjet(MainActivity mainActivity, ObjetUtilisateur[] o) {
        main = mainActivity;
        listeObjet= o;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        main.afficherDescripionObjet(listeObjet,position,1);
    }
}
