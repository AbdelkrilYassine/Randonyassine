package com.example.yassine.randon_ili;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.Date;

public class ListOrganiser extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener {
    private TextView tvDay, tvHour, tvMinute, tvSecond, tvEvent;
    private LinearLayout linearLayout1, linearLayout2;
    private Handler handler;
    private Runnable runnable;
    private GoogleMap mMapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_organiser);
        this.setTitle(" ");


// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapp);
        mapFragment.getMapAsync(this);
        initUI();
        countDownStart();
    }

    @SuppressLint("SimpleDateFormat")
    private void initUI() {
        tvDay = (TextView) findViewById(R.id.txtTimerDay);
        tvHour = (TextView) findViewById(R.id.txtTimerHour);
        tvMinute = (TextView) findViewById(R.id.txtTimerMinute);
        tvSecond = (TextView) findViewById(R.id.txtTimerSecond);
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

    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(ListOrganiser.this,
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
                    Date eventDate = dateFormat.parse("2018-01-18");
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


