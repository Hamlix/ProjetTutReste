package com.example.projettutore;

import android.view.View;

public class ControleurInscrption implements View.OnClickListener {
    private MainActivity main;
    public ControleurInscrption(MainActivity m) {
        main = m;
    }

    @Override
    public void onClick(View v) {
        main.inscription();
    }
}
