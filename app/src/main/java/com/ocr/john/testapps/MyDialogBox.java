package com.ocr.john.testapps;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


/**
 * Created by bob on 25/01/18.
 * Pattern Observer with dialog box
 */

public class MyDialogBox extends Dialog implements View.OnClickListener{

    private DialogClickListener dListener;
    final EditText edt;

    public MyDialogBox(final Context context, DialogClickListener listener)
    {
        // Set your theme here
        super(context);
        dListener = listener;

        // This is the layout XML file that describes your Dialog layout
        this.setContentView(R.layout.unique_comment);
        edt = (EditText) this.findViewById(R.id.txt_comment);
        edt.setText("Replace text here");
    }


    @Override
    public void onClick(View v) {

        dListener.onDialogClick(edt.getText().toString());

        Log.i("AlertDialog", "text submitted : " + edt.getText() );
    }
}
