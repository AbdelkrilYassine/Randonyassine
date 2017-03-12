package com.example.yassine.randon_ili;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Organiser extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organiser);

        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        String usernames = b.getString("usernametest");
        String passwords = b.getString("passwordtest");
        this.setTitle("Welcome " + usernames);

        Button organiser = (Button) findViewById(R.id.organiserr);
       Button listorganiiser = (Button) findViewById(R.id.listorganiser);
       organiser.setOnClickListener(this);
       listorganiiser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.organiserr:
                Toast.makeText(getApplicationContext(), "Organisé une randonnée", Toast.LENGTH_LONG)
                        .show();
                Intent maiin = new Intent(Organiser.this, MapsActivity.class);
                startActivity(maiin);
                break;

            case R.id.listorganiser:
                Toast.makeText(getApplicationContext(), "Vous randonnées déja organisées", Toast.LENGTH_LONG)
                        .show();
                Intent main = new Intent(Organiser.this, ListOrganiser.class);
                startActivity(main);
                break;
        }
    }

    @Override
    public void onBackPressed()
    {}
}
