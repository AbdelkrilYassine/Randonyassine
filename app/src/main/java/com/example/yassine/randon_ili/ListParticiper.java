package com.example.yassine.randon_ili;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

public class ListParticiper extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener {
    private TextView tvDay, tvHour, tvMinute, tvSecond, tvEvent;
    private LinearLayout linearLayout1, linearLayout2;
    private Handler handler;
    private Runnable runnable;
    Button contacter, annuler, consulter;
    private GoogleMap mMapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_participer);
        this.setTitle(" ");


// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mappp);
        mapFragment.getMapAsync(this);
        initUI();
        countDownStart();

        contacter = (Button) findViewById(R.id.bcontacter);
        consulter = (Button) findViewById(R.id.bconsulter);
        annuler = (Button) findViewById(R.id.bannuler);
        contacter.setOnClickListener(this);
        annuler.setOnClickListener(this);
        consulter.setOnClickListener(this);
    }

    @SuppressLint("SimpleDateFormat")
    private void initUI() {
        tvDay = (TextView) findViewById(R.id.txtTimerDay2);
        tvHour = (TextView) findViewById(R.id.txtTimerHour2);
        tvMinute = (TextView) findViewById(R.id.txtTimerMinute2);
        tvSecond = (TextView) findViewById(R.id.txtTimerSecond2);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMapp = googleMap;

        if (mMapp != null) {
            mMapp.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                @Override
                public View getInfoWindow(Marker marker) {

                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.infomap, null);

                    TextView locca = (TextView) v.findViewById(R.id.locality);
                    TextView latt = (TextView) v.findViewById(R.id.lat);
                    TextView lngg = (TextView) v.findViewById(R.id.lng);
                    TextView snippett = (TextView) v.findViewById(R.id.snippet);
                    LatLng ll = marker.getPosition();
                    locca.setText(marker.getTitle());
                    latt.setText("Latitude: " + ll.latitude);
                    lngg.setText("Longitude: " + ll.longitude);
                    snippett.setText(marker.getSnippet());

                    return v;
                }


            });

            mMapp.setOnMapClickListener(this);
            mMapp.setOnMapLongClickListener(this);
            mMapp.setOnMarkerDragListener(this);

            LatLng sydney = new LatLng(-34, 151);

            mMapp.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMapp.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bcontacter:
                Context context = getApplicationContext();
                CharSequence text = "Send an Email";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();


                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font color='#FF0000'>You lost? need information?</font>"));
                builder.setIcon(R.drawable.di);
                String Newligne = System.getProperty("line.separator");

                final TextView email = new TextView(context);
                email.setHeight(100);
                email.setWidth(100);
                email.setGravity(Gravity.CENTER);
                email.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                email.setTextSize(15);
                email.setImeOptions(EditorInfo.IME_ACTION_DONE);
                email.setText("address_email.randon-ili@gmail.com");
                builder.setView(email);


                builder.setMessage("Contact the organizer for any further information and details feel free to ask any question using this email address below." + Newligne);


                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                builder.setPositiveButton("Send",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                String to = email.getText().toString();
                                String subject = "Contact organizer";
                                String message = "your message";
                                Intent email = new Intent(Intent.ACTION_SEND);
                                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                                email.putExtra(Intent.EXTRA_TEXT, message);

                                // need this to prompts email client only
                                email.setType("message/rfc822");

                                startActivity(Intent.createChooser(email, "Choose an Email client"));
                            }


                        });
                builder.show();
                break;

        }


    }


    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(ListParticiper.this,
                "onMapClick:\n" + latLng.latitude + " : " + latLng.longitude,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

   // //////////////COUNT DOWN START/////////////////////////
    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    // Here Set your Event Date
                    Date eventDate = dateFormat.parse("2017-12-30");
                    Date currentDate = new Date();
                    if (!currentDate.after(eventDate)) {
                        long diff = eventDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        tvDay.setText("" + String.format("%02d", days));
                        tvHour.setText("" + String.format("%02d", hours));
                        tvMinute.setText("" + String.format("%02d", minutes));
                        tvSecond.setText("" + String.format("%02d", seconds));
                    } else {

                        handler.removeCallbacks(runnable);
                        // handler.removeMessages(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    // //////////////COUNT DOWN END/////////////////////////


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menumap, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemsatelite:

                MediaPlayer mp = MediaPlayer.create(this, R.raw.soundmenu);
                mp.start();
                mMapp.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


                return true;
            case R.id.itemnormale:
                MediaPlayer mp1 = MediaPlayer.create(this, R.raw.soundmenu);
                mp1.start();
                mMapp.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                return true;

            case R.id.itemterrain:
                MediaPlayer mp2 = MediaPlayer.create(this, R.raw.soundmenu);
                mp2.start();
                mMapp.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                return true;
            case R.id.itemhybrid:
                MediaPlayer mp3 = MediaPlayer.create(this, R.raw.soundmenu);
                mp3.start();
                mMapp.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                return true;
            case R.id.itemnotype:
                MediaPlayer mp4 = MediaPlayer.create(this, R.raw.soundmenu);
                mp4.start();
                mMapp.setMapType(GoogleMap.MAP_TYPE_NONE);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
