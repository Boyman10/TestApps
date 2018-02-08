package com.ocr.john.testapps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
}
