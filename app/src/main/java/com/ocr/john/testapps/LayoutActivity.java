package com.ocr.john.testapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * L'affichage d'une liste s'organise de la manière suivante : on donne une liste de données à un adaptateur (Adapter) qui sera attaché à une liste (AdapterView).
 * L'adaptateur se chargera alors de construire les différentes vues à afficher ainsi que de gérer leurs cycles de vie.
 */
public class LayoutActivity extends AppCompatActivity {
    private AutoCompleteTextView complete = null;
    // Notre liste de mots que connaîtra l'AutoCompleteTextView
    private static final String[] COULEUR = new String[] {
            "Bleu", "Vert", "Jaune", "Jaune canari", "Rouge", "Orange"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_layout);

        // On récupère l'AutoCompleteTextView déclaré dans notre layout
        complete = (AutoCompleteTextView) findViewById(R.id.complete);
        complete.setThreshold(2);

        // On associe un adaptateur à notre liste de couleurs…
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COULEUR);
        // … puis on indique que notre AutoCompleteTextView utilise cet adaptateur
        complete.setAdapter(adapter);


    }
}
