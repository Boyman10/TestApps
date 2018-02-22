package com.ocr.john.testapps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Class where we will put different link to other activities in order to test stuff
 * Please also take a look at the resources files - some are not being tested.
 * ex linear layout : https://developer.android.com/guide/topics/ui/declaring-layout.html
 * https://developer.android.com/guide/topics/ui/declaring-layout.html#CommonLayouts
 *
 * Activity concept : https://developer.android.com/guide/components/activities/intro-activities.html
 */
public class MainActivity extends AppCompatActivity {

    private String dialogVar;

    private DialogClickListener dListener;
    // Notre liste de mots que connaîtra l'AutoCompleteTextView
    private static final String[] COULEUR = new String[] {
            "Bleu", "Vert", "Jaune", "Jaune canari", "Rouge", "Orange"
    };

    private Menu m = null;

//Notez qu'on utilise Menu.FIRST pour indiquer le premier élément d'un menu
    private final static int  MENU_DESACTIVER = Menu.FIRST;
    private final static int  MENU_RETOUR = Menu.FIRST + 1;

    // Other button to test intent
    private Button mPasserelle = null;
    public final static String AGE = "com.ocr.john.testpass.AGE";


    // The onCreate() callback method in your Activity is called by the Android framework when your Activity is launched
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Testing Strings value resources file in english...
        Resources res = getResources();
        // Anaïs se mettra dans %1 et 22 ira dans %2, mais le reste changera en fonction de la langue du terminal !
        String chaine = res.getString(R.string.hello, "Anaïs", 22);
        Log.i("MainActivity", "ma chaine : " + chaine );








        // Click on button :
        Button mBut = findViewById(R.id.my_btn);
        mPasserelle = (Button) findViewById(R.id.passerelle);

        mBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Context context = v.getContext();

                // Prepare layout to define within dialogbox :
                LayoutInflater layout = LayoutInflater.from(context);
                final View dialogView = layout.inflate(R.layout.unique_comment, null);
                final EditText edt = (EditText) dialogView.findViewById(R.id.txt_comment);
                edt.setText("Replace text here");

                AlertDialog show = new AlertDialog.Builder(context)
                        .setTitle("Pick up your text")
                        .setView(dialogView)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dListener.onDialogClick(edt.getText().toString());

                                Log.i("AlertDialog", "text submitted : " + edt.getText() );
                            }
                        })
                        .show();

            }
        });

        // SEcond activity launcher with intent :
        mPasserelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination
                Intent secondeActivite = new Intent(MainActivity.this, IntentExample.class);

                // On rajoute un extra
                secondeActivite.putExtra(AGE, 31);

                // Puis on lance l'intent !
                startActivity(secondeActivite);
            }
        });


        /*
        Logs :
        Log.v("Étiquette", "Message à envoyer") pour vos messages communs.
        Log.d("Étiquette", "Message à envoyer") pour vos messages de debug.
        Log.i("Étiquette", "Message à envoyer") pour vos messages à caractère informatif.
        Log.w("Étiquette", "Message à envoyer") pour vos avertissements.
        Log.e("Étiquette", "Message à envoyer") pour vos erreurs.
         */


        // Set Listener on DialogBox :
        dListener = new DialogClickListener() {
            @Override
            public void onDialogClick(String txt) {

                dialogVar = txt;
                Log.i("MainActivity", "Text retrieved : " + txt);

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), dialogVar, duration);
                toast.show();
            }
        };

    }

    /**
     * To inflate, c'est désérialiser en français, et dans notre cas c'est transformer un objet qui n'est décrit qu'en XML en véritable objet qu'on peut manipuler.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        //R.menu.menu est l'id de notre menu
        inflater.inflate(R.menu.menu, menu);
        m = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.item1:
                //Dans le Menu "m", on active tous les items dans le groupe d'identifiant "R.id.group2"
                m.setGroupEnabled(R.id.group2, true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Trying on context menu -- SHOULD BE REPLACED BY ActionBar
     * https://developer.android.com/design/patterns/actionbar.html
     * @param menu
     * @param vue
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View vue, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, vue, menuInfo);
        menu.add(Menu.NONE, MENU_DESACTIVER, Menu.NONE, "Supprimer cet élément");
        menu.add(Menu.NONE, MENU_RETOUR, Menu.NONE, "Retour");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_DESACTIVER:
                // start activity here
                Intent intent = new Intent(this,LayoutActivity.class);
                startActivity(intent);
            case MENU_RETOUR:

                return true;
        }
        return super.onContextItemSelected(item);
    }


}
