package com.example.yassine.randon_ili;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.list;

public class ExtraOrg extends AppCompatActivity {
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extras);

        sp = (Spinner) findViewById(R.id.spinner1);
        String[] values = new String[] { "1", "2", "3", "4" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,values);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLUE);
             //  ((TextView) arg0.getChildAt(0)).setTextSize(20);



                Toast.makeText(getBaseContext(), sp.getSelectedItem().toString() + " Bus",
                        Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

                for (int i = 0; i < sp.getCount(); i++) {
                }

            }
        });

    }
}

