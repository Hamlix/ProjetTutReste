package com.example.projettutore;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexandremeunier.projettutore.R;

import org.json.simple.JSONValue;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    private Equide[] equide;
    private TextView login;
    private TextView mdp;
    private EditText editLogin;
    private EditText editMdp;
    private Button boutonConnexion;
    private Button boutonProfile;
    private TextView pseudo;
    private TextView nbOr;
    private TextView nomEquide;
    private TextView niveau;
    private TextView nomUtilisateur;
    private TextView email;
    private TextView or;
    private TextView stade;
    private TextView jourCo;
    private TextView succes;
    private TextView mission;
    private TextView nbEquideProfil;
    private TextView jaugeExp;
    private TextView erreur;
    private ProgressBar barJaugeExp;
    private Button bouton_modifier;
    private Button bouton_poney;
    private Button bouton_succes;
    private Button bouton_mission;
    private Spinner liste;
    public int indiceRobe = 0, indiceCrin = 0;
    private int[] robe = {Color.rgb(245, 245, 220), Color.rgb(88, 41, 0), Color.GRAY, Color.BLUE, Color.rgb(102, 0, 153)};
    private int[] crin = {Color.YELLOW, Color.BLACK, Color.rgb(88, 41, 0), Color.GREEN, Color.rgb(255, 0, 127)};
    private Button boutonNourir;
    private int equideAfficher = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connexion();
    }

    public void connexion() {
        setContentView(R.layout.activity_main);
        login = (TextView) findViewById(R.id.login);
        mdp = (TextView) findViewById(R.id.mdp);
        erreur = (TextView) findViewById(R.id.erreur);
        editLogin = (EditText) findViewById(R.id.editLogin);
        editMdp = (EditText) findViewById(R.id.editMdp);
        boutonConnexion = (Button) findViewById(R.id.connexion);
        boutonConnexion.setOnClickListener(new ControleurConnexion(this));
        Button boutonInscritption = (Button) findViewById(R.id.inscription);
        boutonInscritption.setOnClickListener(new ControleurInscrption(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void lancerPageAccueille() {
        setContentView(R.layout.page_accueille);
        //On recupere la liste des items composant la page
        RecupInfoUtilisateur recup = new RecupInfoUtilisateur();
        recup.start();
        while (recup.resultat == null) ;
        pseudo = (TextView) findViewById(R.id.pseudo);
        nbOr = (TextView) findViewById(R.id.nbOr);
        nomEquide = (TextView) findViewById(R.id.nomEquide);
        niveau = (TextView) findViewById(R.id.niveau);
        boutonProfile = (Button) findViewById(R.id.profile);
        boutonNourir = (Button) findViewById(R.id.nourrir);
        stade = (TextView) findViewById(R.id.stade);
        pseudo.setText(ParametreConnexion.login);
        nbOr.setText("Or : " + Utilisateur.nbOr);
        nomEquide.setText(Utilisateur.nomEquide);

        ProgressBar barExp = (ProgressBar) findViewById(R.id.barExp);
        ProgressBar barFaim = (ProgressBar) findViewById(R.id.barFaim);
        ProgressBar barMoral = (ProgressBar) findViewById(R.id.barMoral);
        ProgressBar barPropre = (ProgressBar) findViewById(R.id.barProprete);
        RecupEquide recupE = new RecupEquide(Utilisateur.idU, barExp, barFaim, barMoral, barPropre, this);
        recupE.execute();
        while (recupE.attendre) ;
        stade.setText("Stade : " + equide[equideAfficher].getStade());
        niveau.setText("Niveau : " + equide[equideAfficher].getNiveau());
        ImageView imageEquide = (ImageView) findViewById(R.id.imageEquide);
        retouverImage(imageEquide);
        boutonProfile.setOnClickListener(new ControleurBoutonProfile(this));
        boutonNourir.setOnClickListener(new BoutonNourir(this, Utilisateur.idU));
        Button boutonBoutique = (Button) findViewById(R.id.boutique);
        boutonBoutique.setOnClickListener(new BoutonBoutique(Utilisateur.idU, this));
        Button boutonInventaire = (Button) findViewById(R.id.inventaire);
        boutonInventaire.setOnClickListener(new BoutonInventaire(Utilisateur.idU, this));
    }

    public void affichagePageProfile() {
        setContentView(R.layout.profile);
        //Les TextView
        nomUtilisateur = (TextView) findViewById(R.id.nom_profile);
        nomUtilisateur.setText("Nom ; " + Utilisateur.nom);
        email = (TextView) findViewById(R.id.email_profile);
        email.setText("Email : " + Utilisateur.email);
        or = (TextView) findViewById(R.id.nbOr_profile);
        or.setText("Or : " + Utilisateur.nbOr);
        jourCo = (TextView) findViewById(R.id.jourConnexion);
        jourCo.setText("Jour de connexion : " + Utilisateur.jourConnexion);
        succes = (TextView) findViewById(R.id.succes);
        succes.setText("Succ\u00e8s : " + Utilisateur.succes);
        mission = (TextView) findViewById(R.id.mission);
        mission.setText("Mission : " + Utilisateur.mission);
        nbEquideProfil = (TextView) findViewById(R.id.nb_equide_profile);
        nbEquideProfil.setText("Nombre d'\u00e9quid\u00e9 : " + Utilisateur.nbEquide);
        jaugeExp = (TextView) findViewById(R.id.jauge_exp);
        //La bar de progression pour la jauge d'experience
        barJaugeExp = (ProgressBar) findViewById(R.id.bar_jauge_exp);
        //Les boutons
        bouton_modifier = (Button) findViewById(R.id.bouton_modifier);
        Button bouton_inventaire = (Button) findViewById(R.id.bouton_inventaire_profile);
        bouton_poney = (Button) findViewById(R.id.bouton_poney);
        Button bouton_boutique = (Button) findViewById(R.id.bouton_boutique);
        bouton_succes = (Button) findViewById(R.id.bouton_success);
        bouton_mission = (Button) findViewById(R.id.bouton_mission);
        bouton_poney = (Button) findViewById(R.id.bouton_poney);
        bouton_poney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerPageAccueille();
            }
        });
        bouton_boutique.setOnClickListener(new BoutonBoutique(Utilisateur.idU, this));
        bouton_inventaire.setOnClickListener(new BoutonInventaire(Utilisateur.idU,this));
    }

    public void inscription() {
        setContentView(R.layout.inscription);
        EditText[] information = new EditText[3];
        information[0] = (EditText) findViewById(R.id.editLogin);
        information[0].setText(editLogin.getText());
        information[1] = (EditText) findViewById(R.id.editMdp);
        information[1].setText(editMdp.getText());
        information[2] = (EditText) findViewById(R.id.editEmail);
        Button inscription = (Button) findViewById(R.id.inscription);
        inscription.setOnClickListener(new ControleurEnvoyerInscription(this, information));
        Button annuler = (Button) findViewById(R.id.annuler);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connexion();
            }
        });
    }

    public void creationPoney(int id, String email) {
        setContentView(R.layout.creation_poney);
        EditText nom = (EditText) findViewById(R.id.editNomPoulain);
        TextView r = (TextView) findViewById(R.id.couleurRobe);
        r.setBackgroundColor(robe[0]);
        TextView c = (TextView) findViewById(R.id.couleurCrin);
        c.setBackgroundColor(crin[0]);
        indiceCrin++;
        indiceRobe++;
        ImageButton precedentRobe = (ImageButton) findViewById(R.id.precedentRobe);
        ImageButton suivantRobe = (ImageButton) findViewById(R.id.suivantRobe);
        ImageButton precedentCrin = (ImageButton) findViewById(R.id.precedentCrin);
        ImageButton suivantCrin = (ImageButton) findViewById(R.id.suivantCrin);
        precedentRobe.setOnClickListener(new ChangerCouleur(r, 0, this));
        suivantRobe.setOnClickListener(new ChangerCouleur(r, 2, this));
        precedentCrin.setOnClickListener(new ChangerCouleur(c, 1, this));
        suivantCrin.setOnClickListener(new ChangerCouleur(c, 3, this));
        Button finir = (Button) findViewById(R.id.inscription);
        finir.setOnClickListener(new BoutonFinirInscription(this, nom, id, email));
        Button annuler = (Button) findViewById(R.id.annuler);
        annuler.setOnClickListener(new AnnulerInscription(id, this));

    }

    public void changerCouleurRobe(TextView texte, int etat) {
        if (etat == 0) {
            if (indiceRobe > 0)
                indiceRobe--;
        } else {
            if (etat == 2)
                if (indiceRobe < robe.length - 1)
                    indiceRobe++;
        }
        texte.setBackgroundColor(robe[indiceRobe]);
    }

    public void changerCouleurCrin(TextView texte, int etat) {
        if (etat == 1) {
            if (indiceCrin > 0)
                indiceCrin--;
        } else {
            if (etat == 3)
                if (indiceCrin < crin.length - 1)
                    indiceCrin++;
        }
        texte.setBackgroundColor(crin[indiceCrin]);
    }

    public EditText getEditLogin() {
        return editLogin;
    }

    public void setEditLogin(EditText editLogin) {
        this.editLogin = editLogin;
    }

    public EditText getEditMdp() {
        return editMdp;
    }

    public void setEditMdp(EditText editMdp) {
        this.editMdp = editMdp;
    }

    public TextView getErreur() {
        return erreur;
    }

    public void listeObjetNourrir(final ObjetUtilisateur[] objet) {
        DisplayMetrics ecran = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ecran);
        int hauteur = ecran.heightPixels;
        int largeur = ecran.widthPixels;
        setContentView(R.layout.afficher_inventaire);
        Button retour = (Button) findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerPageAccueille();
            }
        });
        ScrollView s = (ScrollView) findViewById(R.id.liste);
        LinearLayout tous = new LinearLayout(this);
        tous.setOrientation(LinearLayout.VERTICAL);
        int i = 0;
        while (i < objet.length) {
            LinearLayout ligne = new LinearLayout(this);
            ligne.setOrientation(LinearLayout.HORIZONTAL);
            for (int compteur = 0; compteur < 2 && i < objet.length; i++, compteur++) {
                final int indice = i;
                LinearLayout l = new LinearLayout(this);
                l.setOrientation(LinearLayout.VERTICAL);
                ImageView image = new ImageView(this);
                imageBoutique(image,objet[indice].getId());
                l.addView(image);
                TextView texte = new TextView(this);
                texte.setText(objet[i].getNomO());
                int taille = texte.getWidth();
                texte.setWidth(largeur / 2);
                texte.setGravity(Gravity.CENTER_HORIZONTAL);
                l.addView(texte);
                final int finalI = i;
                l.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        afficherDescripionObjet(objet,indice,1);
                    }
                });
                ligne.addView(l);

            }
            tous.addView(ligne);
        }
        s.addView(tous);

    }

    public void afficherDescripionObjet(final ObjetUtilisateur[] objet,final int indice, int action) {
        setContentView(R.layout.description_objet);
        LinearLayout l = (LinearLayout) findViewById(R.id.l);
        int taille = l.getWidth();
        System.out.println(taille);
        TextView nom = (TextView) findViewById(R.id.nom);
        TextView description = (TextView) findViewById(R.id.description);
        TextView satiete = (TextView) findViewById(R.id.satiete);
        TextView moral = (TextView) findViewById(R.id.moral);
        TextView proprete = (TextView) findViewById(R.id.proprete);
        TextView experience = (TextView) findViewById(R.id.exp);
        TextView qte = (TextView) findViewById(R.id.quantite);
        TextView limite = (TextView) findViewById(R.id.limitationUtilisationO);
        ImageView image = (ImageView) findViewById(R.id.image);
        imageBoutique(image,objet[indice].getId());
        TextView use = (TextView) findViewById(R.id.nbUse);
        nom.setText("Nom : " + objet[indice].getNomO());
        description.setText("Description : " + objet[indice].getDescriptionO());
        satiete.setText("Satiete  :" + objet[indice].getSatieteO());
        moral.setText("Moral :" + objet[indice].getMoralO());
        proprete.setText("Propret\u00e9 :" + objet[indice].getPropreteO());
        experience.setText("Experience :" + objet[indice].getExperience());
        qte.setText("Quantit\u00e9:" + objet[indice].getQuantiteO());
        limite.setText("Nombre d'utilisation max :  " + objet[indice].getLimitationUtilisationO());
        use.setText("Nombre d'utilisation(s) faite :  " + objet[indice].getnbUse());
        Button bouton_retour = (Button) findViewById(R.id.retour);
        Button bouton_action = (Button) findViewById(R.id.utiliser);
        final MainActivity main = this;
        if(action==0) {
            if (objet[indice].getBouton() == 1){
                bouton_action.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(main, "Utilisation ", Toast.LENGTH_LONG).show();
                    }
                });
             }else {
                bouton_action.setVisibility(Button.GONE);
            }

            bouton_retour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   afficherInventaire(objet);
                }
            });
        }else if(action==1) {
            bouton_action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    equide[equideAfficher].setSatiete(equide[equideAfficher].getSatiete() + objet[indice].getSatieteO());
                    System.out.println("Mis a jour de la faim ok");
                }
            });
            bouton_retour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   listeObjetNourrir(objet);
                }
            });
        }else if(action==2){
            bouton_action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* equide[equideAfficher].setSatiete(equide[equideAfficher].getSatiete() + objet[indice].getSatieteO());*/
                    System.out.println("Votre équidé vous aime encore plus !");
                }
            });
            bouton_retour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*listeObjetJouer(objet);*/
                }
            });
        }else if(action==3){
            bouton_action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* equide[equideAfficher].setSatiete(equide[equideAfficher].getSatiete() + objet[indice].getSatieteO());*/
                    System.out.println("Votre équidé est encore plus propre !");
                }
            });
            bouton_retour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /*listeObjetLaver(objet);*/
                }
            });
        }
    }

    public void setEquide(Equide[] e) {
        equide = e;
    }

    public Equide[] getEquide() {
        return equide;
    }

    public void retouverImage(ImageView imageEquide) {
        String obtenir = "e" + equide[0].getCouleurR() + "" + equide[0].getCouleurC();
        if (obtenir.equals("e11"))
            imageEquide.setImageResource(R.drawable.e11);
        if (obtenir.equals("e12"))
            imageEquide.setImageResource(R.drawable.e12);
        if (obtenir.equals("e13"))
            imageEquide.setImageResource(R.drawable.e13);
        if (obtenir.equals("e14"))
            imageEquide.setImageResource(R.drawable.e14);
        if (obtenir.equals("e15"))
            imageEquide.setImageResource(R.drawable.e15);
        if (obtenir.equals("e21"))
            imageEquide.setImageResource(R.drawable.e21);
        if (obtenir.equals("e22"))
            imageEquide.setImageResource(R.drawable.e22);
        if (obtenir.equals("e23"))
            imageEquide.setImageResource(R.drawable.e23);
        if (obtenir.equals("e24"))
            imageEquide.setImageResource(R.drawable.e24);
        if (obtenir.equals("e25"))
            imageEquide.setImageResource(R.drawable.e25);
        if (obtenir.equals("e31"))
            imageEquide.setImageResource(R.drawable.e31);
        if (obtenir.equals("e32"))
            imageEquide.setImageResource(R.drawable.e32);
        if (obtenir.equals("e33"))
            imageEquide.setImageResource(R.drawable.e33);
        if (obtenir.equals("e34"))
            imageEquide.setImageResource(R.drawable.e34);
        if (obtenir.equals("e35"))
            imageEquide.setImageResource(R.drawable.e35);
        if (obtenir.equals("e41"))
            imageEquide.setImageResource(R.drawable.e41);
        if (obtenir.equals("e42"))
            imageEquide.setImageResource(R.drawable.e42);
        if (obtenir.equals("e43"))
            imageEquide.setImageResource(R.drawable.e43);
        if (obtenir.equals("e44"))
            imageEquide.setImageResource(R.drawable.e44);
        if (obtenir.equals("e45"))
            imageEquide.setImageResource(R.drawable.e45);
        if (obtenir.equals("e51"))
            imageEquide.setImageResource(R.drawable.e51);
        if (obtenir.equals("e52"))
            imageEquide.setImageResource(R.drawable.e52);
        if (obtenir.equals("e53"))
            imageEquide.setImageResource(R.drawable.e53);
        if (obtenir.equals("e54"))
            imageEquide.setImageResource(R.drawable.e54);
        if (obtenir.equals("e55"))
            imageEquide.setImageResource(R.drawable.e55);
    }

    public void afficherBoutique(final ObjetBoutique[] objet) {
        DisplayMetrics ecran = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ecran);
        int hauteur = ecran.heightPixels;
        int largeur = ecran.widthPixels;
        setContentView(R.layout.boutique);
        Button retour = (Button) findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerPageAccueille();
            }
        });
        ScrollView s = (ScrollView) findViewById(R.id.liste);
        LinearLayout tous = new LinearLayout(this);
        tous.setOrientation(LinearLayout.VERTICAL);
        int i = 0;
        while (i < objet.length) {
            LinearLayout ligne = new LinearLayout(this);
            ligne.setOrientation(LinearLayout.HORIZONTAL);
            for (int compteur = 0; compteur < 2 && i < objet.length; i++, compteur++) {
                final int indice = i;
                LinearLayout l = new LinearLayout(this);
                l.setOrientation(LinearLayout.VERTICAL);
                ImageView image = new ImageView(this);
                imageBoutique(image,objet[indice].getId());
                image.setMaxWidth(50);
                image.setMaxHeight(50);
                l.addView(image);
                TextView texte = new TextView(this);
                texte.setText(objet[i].getNomO());
                int taille = texte.getWidth();
                texte.setWidth(largeur / 2);
                texte.setGravity(Gravity.CENTER_HORIZONTAL);
                l.addView(texte);
                TextView prix = new TextView(this);
                prix.setText("Prix : " + objet[i].getPrixO());
                prix.setWidth(largeur / 2);
                prix.setGravity(Gravity.CENTER_HORIZONTAL);
                l.addView(prix);
                final int finalI = i;
                l.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        acheterObjet(objet, indice);
                    }
                });
                ligne.addView(l);

            }
            tous.addView(ligne);
        }
        s.addView(tous);
    }

    private void imageBoutique(ImageView image, int id) {
        String objet="o"+id;
        if (objet.equals("o1"))
            image.setImageResource(R.drawable.o1);
        if (objet.equals("o2"))
            image.setImageResource(R.drawable.o2);
        if (objet.equals("o3"))
            image.setImageResource(R.drawable.o3);
        if (objet.equals("o4"))
            image.setImageResource(R.drawable.o4);
        if (objet.equals("o5"))
            image.setImageResource(R.drawable.o5);
        if (objet.equals("o6"))
            image.setImageResource(R.drawable.o6);
        if (objet.equals("o7"))
            image.setImageResource(R.drawable.o7);
        if (objet.equals("o8"))
            image.setImageResource(R.drawable.o8);
        if (objet.equals("o9"))
            image.setImageResource(R.drawable.o9);
        if (objet.equals("o10"))
            image.setImageResource(R.drawable.o10);
        if (objet.equals("o11"))
            image.setImageResource(R.drawable.o11);
        if (objet.equals("o12"))
            image.setImageResource(R.drawable.o12);
        if (objet.equals("o13"))
            image.setImageResource(R.drawable.o13);
        if (objet.equals("o14"))
            image.setImageResource(R.drawable.o14);
        if (objet.equals("o15"))
            image.setImageResource(R.drawable.o15);
        if (objet.equals("o16"))
            image.setImageResource(R.drawable.o16);
        if (objet.equals("o17"))
            image.setImageResource(R.drawable.o17);
        if (objet.equals("o18"))
            image.setImageResource(R.drawable.o18);
        if (objet.equals("o19"))
            image.setImageResource(R.drawable.o19);
        if (objet.equals("o20"))
            image.setImageResource(R.drawable.o20);
        if (objet.equals("o21"))
            image.setImageResource(R.drawable.o21);
        if (objet.equals("o123"))
            image.setImageResource(R.drawable.o123);
        if (objet.equals("o124"))
            image.setImageResource(R.drawable.o124);
        if (objet.equals("o125"))
            image.setImageResource(R.drawable.o125);
        if (objet.equals("o126"))
            image.setImageResource(R.drawable.o126);
        if (objet.equals("o22"))
            image.setImageResource(R.drawable.o22);
        if (objet.equals("o23"))
            image.setImageResource(R.drawable.o23);
        if (objet.equals("o24"))
            image.setImageResource(R.drawable.o24);
        if (objet.equals("o25"))
            image.setImageResource(R.drawable.o25);
        if (objet.equals("o26"))
            image.setImageResource(R.drawable.o26);
        if (objet.equals("o27"))
            image.setImageResource(R.drawable.o27);
        if (objet.equals("o28"))
            image.setImageResource(R.drawable.o28);
        if (objet.equals("o29"))
            image.setImageResource(R.drawable.o29);
        if (objet.equals("o30"))
            image.setImageResource(R.drawable.o30);
        if (objet.equals("o31"))
            image.setImageResource(R.drawable.o31);
        if (objet.equals("o32"))
            image.setImageResource(R.drawable.o32);
        if (objet.equals("o33"))
            image.setImageResource(R.drawable.o33);
        if (objet.equals("o34"))
            image.setImageResource(R.drawable.o34);
        if (objet.equals("o35"))
            image.setImageResource(R.drawable.o35);
        if (objet.equals("o36"))
            image.setImageResource(R.drawable.o36);
        if (objet.equals("o37"))
            image.setImageResource(R.drawable.o37);
        if (objet.equals("o26"))
            image.setImageResource(R.drawable.o26);
        if (objet.equals("o70"))
            image.setImageResource(R.drawable.o70);
        if (objet.equals("o31"))
            image.setImageResource(R.drawable.o31);
        if (objet.equals("o91"))
            image.setImageResource(R.drawable.o91);
        if (objet.equals("o38"))
            image.setImageResource(R.drawable.o38);
        if (objet.equals("o74"))
            image.setImageResource(R.drawable.o74);
        if (objet.equals("o105"))
            image.setImageResource(R.drawable.o105);
        if (objet.equals("o113"))
            image.setImageResource(R.drawable.o113);
        if (objet.equals("o68"))
            image.setImageResource(R.drawable.o68);
        if (objet.equals("o66"))
            image.setImageResource(R.drawable.o66);
        if (objet.equals("o83"))
            image.setImageResource(R.drawable.o83);
        if (objet.equals("o118"))
            image.setImageResource(R.drawable.o118);
        if (objet.equals("o92"))
            image.setImageResource(R.drawable.o92);
        if (objet.equals("o75"))
            image.setImageResource(R.drawable.o75);
        if (objet.equals("o78"))
            image.setImageResource(R.drawable.o78);
        if (objet.equals("o95"))
            image.setImageResource(R.drawable.o95);
        if (objet.equals("o110"))
            image.setImageResource(R.drawable.o110);
        if (objet.equals("o122"))
            image.setImageResource(R.drawable.o122);
        if (objet.equals("o57"))
            image.setImageResource(R.drawable.o57);
        if (objet.equals("o81"))
            image.setImageResource(R.drawable.o81);


    }

    public void acheterObjet(final ObjetBoutique[] objet, int indice) {
        setContentView(R.layout.acheter_objet);
        TextView nom = (TextView) findViewById(R.id.nom);
        TextView descrption = (TextView) findViewById(R.id.description);
        TextView satiete = (TextView) findViewById(R.id.satiete);
        TextView moral = (TextView) findViewById(R.id.moral);
        TextView proprete = (TextView) findViewById(R.id.proprete);
        TextView exp = (TextView) findViewById(R.id.exp);
        TextView nbLimiteObjet = (TextView) findViewById(R.id.nbObjetlimite);
        TextView nbUse = (TextView) findViewById(R.id.nbLimiteUse);
        TextView prix = (TextView) findViewById(R.id.prix);
        ImageView image = (ImageView) findViewById(R.id.image);
        imageBoutique(image,objet[indice].getId());
        nom.setText("Nom : " + objet[indice].getNomO());
        descrption.setText("Description : " + objet[indice].getDescription());
        satiete.setText("Satiete : " + objet[indice].getSatiete());
        moral.setText("Moral : " + objet[indice].getMoral());
        proprete.setText("Proprete : " + objet[indice].getProprete());
        exp.setText("Experience : " + objet[indice].getExperience());
        nbLimiteObjet.setText("Possession maximale : " + objet[indice].getnbLimiteObjet());
        nbUse.setText("Nombre d'utilisation maximum : " + objet[indice].getnbUse());
        prix.setText("Prix : " + objet[indice].getPrixO());
        Button retour = (Button) findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherBoutique(objet);
            }
        });
        Button achat = (Button) findViewById(R.id.achat);
        achat.setOnClickListener(new AchatObjet(Utilisateur.idU, objet[indice].getId(), objet[indice].getPrixO(), objet[indice].getnbLimiteObjet(), this));
    }

    public void afficherInventaire(final ObjetUtilisateur[] objet) {
        DisplayMetrics ecran = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ecran);
        int hauteur = ecran.heightPixels;
        int largeur = ecran.widthPixels;
        setContentView(R.layout.afficher_inventaire);
        Button retour = (Button) findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerPageAccueille();
            }
        });
        ScrollView s = (ScrollView) findViewById(R.id.liste);
        LinearLayout tous = new LinearLayout(this);
        tous.setOrientation(LinearLayout.VERTICAL);
        int i = 0;
        while (i < objet.length) {
            LinearLayout ligne = new LinearLayout(this);
            ligne.setOrientation(LinearLayout.HORIZONTAL);
            for (int compteur = 0; compteur < 2 && i < objet.length; i++, compteur++) {
                final int indice = i;
                LinearLayout l = new LinearLayout(this);
                l.setOrientation(LinearLayout.VERTICAL);
                ImageView image = new ImageView(this);
                imageBoutique(image,objet[indice].getId());
                l.addView(image);
                TextView texte = new TextView(this);
                texte.setText(objet[i].getNomO());
                int taille = texte.getWidth();
                texte.setWidth(largeur / 2);
                texte.setGravity(Gravity.CENTER_HORIZONTAL);
                l.addView(texte);
                final int finalI = i;
                l.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        afficherDescripionObjet(objet,indice,0);
                    }
                });
                ligne.addView(l);

            }
            tous.addView(ligne);
        }
        s.addView(tous);
    }
}