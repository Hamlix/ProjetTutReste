package com.example.projettutore;

import android.view.View;
import android.view.View.OnClickListener;

public class ControleurBoutonProfile implements OnClickListener {
	private MainActivity main;
	public ControleurBoutonProfile(MainActivity m){
		main = m;
	}
	@Override
	public void onClick(View arg0) {
        main.affichagePageProfile();
	}

}
