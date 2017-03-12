package com.example.yassine.randon_ili;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Participate extends AppCompatActivity implements View.OnClickListener {
    TextView code;
    Button valide, listparr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate);

        code = (TextView) findViewById(R.id.code);
        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        String usernames = b.getString("usernametest");
        String passwords = b.getString("passwordtest");
        this.setTitle("Welcome " + usernames);
        valide=(Button)findViewById(R.id.validd);
        listparr=(Button)findViewById(R.id.listpartr);
        valide.setOnClickListener(this);
listparr.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.validd:
                Toast.makeText(getApplicationContext(), "Participer à une randonnée", Toast.LENGTH_LONG)
                        .show();
                //Intent maiin = new Intent(Participate.this, ListParticiper.class);
              //   startActivity(maiin);
                break;

            case R.id.listpartr:
                Toast.makeText(getApplicationContext(), "Vous randonnées déja participées", Toast.LENGTH_LONG)
                        .show();
                Intent main = new Intent(Participate.this, ListParticiper.class);
                startActivity(main);
                break;
        }

    }
}
