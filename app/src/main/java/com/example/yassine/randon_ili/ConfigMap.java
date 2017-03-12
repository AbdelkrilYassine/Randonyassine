package com.example.yassine.randon_ili;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigMap extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button confirmm, cancell;
    EditText lieuarr, hdep, datte, distancepp, harr, lieudep, nbplace, nome;
    private Pattern pattern;
    private Matcher matcher;
    private static final String TIME24HOURS_PATTERN =
            "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    private Spinner spinner1, spinner2, spinner3, spinner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_map);
        confirmm = (Button) findViewById(R.id.confirmm2);
        cancell = (Button) findViewById(R.id.cancell2);
        confirmm.setOnClickListener(this);
        cancell.setOnClickListener(this);

        nbplace = (EditText) findViewById(R.id.nbplace);
        datte = (EditText) findViewById(R.id.datte);
        lieudep = (EditText) findViewById(R.id.lieudep);
        distancepp = (EditText) findViewById(R.id.distparr);
        hdep = (EditText) findViewById(R.id.hdep);
        harr = (EditText) findViewById(R.id.hrearr);
        nome = (EditText) findViewById(R.id.nom);
        lieuarr = (EditText) findViewById(R.id.lieuarr);


        spinner1 = (Spinner) findViewById(R.id.spinner1);
        String[] values = new String[]{"Ariana", "Béja", "Ben Arous", "Bizerte", "Gabès", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili", "Le Kef", "Mahdia", "La Manouba", "Médenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"};


        spinner2 = (Spinner) findViewById(R.id.spinner2);
        String[] values2 = new String[]{"Facile", "Moyenne", "Difficile", "Trés difficile", "Extrêmement difficile"};

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        String[] values3 = new String[]{"A pied", "A VTT", "Cyclo-route", "A ski", "En raquettes à neige", "A cheval", "En barque,canoé,kayak,quad"};

        spinner4 = (Spinner) findViewById(R.id.spinner4);
        String[] values4 = new String[]{"Moins de 2h", "Entre 2h et 3h", "Entre 3h et 4h", "Entre 4h et 5h", "Entre 5h et 7h", "Plus de 7h"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, values);
        spinner1.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, values2);

        spinner2.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item, values3);

        spinner3.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.spinner_item, values4);

        spinner4.setAdapter(adapter4);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);

        datte.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(ConfigMap.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
/*
                        if (selectedday < 10) {
                            datte.setText(selectedyear + "/" + selectedmonth + "/0"+ selectedday);
                        } else {
                            datte.setText(selectedyear + "/" + selectedmonth + "/"  + selectedday);
                        }
                        if (selectedmonth + 1 < 10) {
                            int x = selectedmonth + 1;
                            datte.setText(selectedyear + "/" + 0 + x + "/" + selectedday);
                        } else {
                            datte.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                        }*/

                        int x = selectedmonth + 1;

                        if (selectedday < 10 && x < 10)
                            datte.setText(selectedyear + "/" + 0 + x + "/" + 0 + selectedday);
                        else if (selectedday < 10)
                            datte.setText(selectedyear + "/" + x + "/" + 0 + selectedday);
                        else if (x < 10)
                            datte.setText(selectedyear + "/" + 0 + x + "/" + selectedday);
                        else if (selectedday > 10 && x > 10)
                            datte.setText(selectedyear + "/" + x + "/" + selectedday);
                        else if (selectedday > 10)
                            datte.setText(selectedyear + "/" + x + "/" + selectedday);
                        else if (x > 10)
                            datte.setText(selectedyear + "/" + x + "/" + selectedday);



                    /*      Your code   to get date and time    */

                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });


        hdep.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ConfigMap.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        if (selectedHour < 10 && selectedMinute < 10)
                            hdep.setText("0" + selectedHour + ":" + 0 + selectedMinute);
                        else if (selectedHour < 10)
                            hdep.setText("0" + selectedHour + ":" + selectedMinute);
                        else if (selectedMinute < 10)
                            hdep.setText(selectedHour + ":" + 0 + selectedMinute);
                        else if (selectedHour > 10 && selectedMinute > 10)
                            hdep.setText(selectedHour + ":" + selectedMinute);
                        else if (selectedHour > 10)
                            hdep.setText(selectedHour + ":" + selectedMinute);
                        else if (selectedMinute > 10)
                            hdep.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        harr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ConfigMap.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        if (selectedHour < 10 && selectedMinute < 10)
                            harr.setText(0 + selectedHour + ":" + 0 + selectedMinute);
                        else if (selectedHour < 10)
                            harr.setText(0 + ":" + selectedMinute);
                        else if (selectedMinute < 10)
                            harr.setText(selectedHour + ":" + 0 + selectedMinute);
                        else if (selectedHour > 10 && selectedMinute > 10)
                            harr.setText(selectedHour + ":" + selectedMinute);
                        else if (selectedHour > 10)
                            harr.setText(selectedHour + ":" + selectedMinute);
                        else if (selectedMinute > 10)
                            harr.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


    }

    public void Rando() {
        if (!validate()) {
            onRandFailed();
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(400);
        } else if (validate()) {
            onRandSuccess();


        }
    }


    public void onRandSuccess() {
        Toast.makeText(getApplicationContext(), "Congrats: Created Successfull", Toast.LENGTH_LONG)
                .show();
        confirmm.setEnabled(true);
        Intent maiin = new Intent(ConfigMap.this, Organiser.class);
        //  startActivity(maiin);
    }

    public void onRandFailed() {
        Toast.makeText(getApplicationContext(), "Creation failed", Toast.LENGTH_LONG).show();

        confirmm.setEnabled(true);
    }


    public boolean validate() {

        boolean valid = true;

        String text1 = lieuarr.getText().toString();
        String text2 = hdep.getText().toString();
        String text3 = datte.getText().toString();
        String text4 = distancepp.getText().toString();
        String text5 = harr.getText().toString();
        String text6 = lieudep.getText().toString();
        String text7 = nbplace.getText().toString();
        String text9 = nome.getText().toString();

        Drawable dr = getResources().getDrawable(R.drawable.spamm);
        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());

        try {
            int i = Integer.parseInt(text7);
            nbplace.setError(null, null);
            nbplace.setCompoundDrawables(null, null, null, null);
            valid = true;
            if (i < 4) {
                nbplace.setError("Entrer le nombre de participants", dr);
                nbplace.setCompoundDrawables(null, null, dr, null);
                valid = false;
            }

        } catch (NumberFormatException e) {
            nbplace.setError("Entrer le nombre de participants", dr);
            nbplace.setCompoundDrawables(null, null, dr, null);
            valid = false;
        }


        try {
            float j = Float.parseFloat(text4);
            distancepp.setError(null, null);
            distancepp.setCompoundDrawables(null, null, null, null);
            valid = true;
            if (j <= 0.0) {

                valid = false;
            }
        } catch (NumberFormatException e) {
            distancepp.setError("Entrer la distance à parcourir en km", dr);
            distancepp.setCompoundDrawables(null, null, dr, null);
            valid = false;
        }

        if (text1.isEmpty()) {

            lieuarr.setError("Entrer un lieu d'arrivée", dr);

            lieuarr.setCompoundDrawables(null, null, dr, null);

            valid = false;
        } else {
            lieuarr.setError(null, null);
            lieuarr.setCompoundDrawables(null, null, null, null);
        }

        if (text2.isEmpty() || text2.length() < 5 || text2.length() > 5 || !validateTime(text2)) {
            hdep.setError("Entrer l'heure de départ", dr);
            hdep.setCompoundDrawables(null, null, dr, null);
            valid = false;
        } else {
            hdep.setError(null, null);
            hdep.setCompoundDrawables(null, null, null, null);
        }
        if (text3.isEmpty() || !isValidDate(text3) || text3.length() < 10 || text3.length() > 10) {
            datte.setError("Entrer date du randonnée", dr);
            datte.setCompoundDrawables(null, null, dr, null);
            valid = false;
        } else {
            datte.setError(null, null);
            datte.setCompoundDrawables(null, null, null, null);
        }

        if (text5.isEmpty() || text5.length() < 5 || text5.length() > 5 || !validateTime(text5)) {
            harr.setError("Entrer l'heure d'arrivée", dr);
            harr.setCompoundDrawables(null, null, dr, null);
            valid = false;
        } else {
            harr.setError(null, null);
            harr.setCompoundDrawables(null, null, null, null);
        }
        if (text6.isEmpty()) {
            lieudep.setError("Entrer lieu de départ", dr);
            lieudep.setCompoundDrawables(null, null, dr, null);
            valid = false;
        } else {
            lieudep.setError(null, null);
            lieudep.setCompoundDrawables(null, null, null, null);
        }

        if (text9.isEmpty()) {
            nome.setError("Entrer nom du randonnée", dr);
            nome.setCompoundDrawables(null, null, dr, null);
            valid = false;
        } else {
            nome.setError(null, null);
            nome.setCompoundDrawables(null, null, null, null);
        }

        return valid;
    }


    public boolean isValidDate(String date) {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        // declare and initialize testDate variable, this is what will hold
        // our converted string

        Date testDate = null;
        Date datecurrent = new Date();

        // we will now try to parse the string into date form
        try {
            testDate = sdf.parse(date);
        }

        // if the format of the string provided doesn't match the format we
        // declared in SimpleDateFormat() we will get an exception

        catch (ParseException e) {

            return false;
        }

        // dateformat.parse will accept any date as long as it's in the format
        // you defined, it simply rolls dates over, for example, december 32
        // becomes jan 1 and december 0 becomes november 30
        // This statement will make sure that once the string
        // has been checked for proper formatting that the date is still the
        // date that was entered, if it's not, we assume that the date is invalid

        if (!sdf.format(testDate).equals(date)) {
            return false;
        }

        if (testDate.before(datecurrent)) {
            return false;
        }
        // if we make it to here without getting an error it is assumed that
        // the date was a valid one and that it's in the proper format

        return true;

    } // end isValidDate


    public boolean validateTime(final String time) {

        matcher = pattern.matcher(time);
        return matcher.matches();

    }

    public ConfigMap() {
        pattern = Pattern.compile(TIME24HOURS_PATTERN);
    }

    private void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).setText("");
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearForm((ViewGroup) view);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.confirmm2:
                final ProgressDialog progressDialog = new ProgressDialog(ConfigMap.this, R.style.TransparentProgressDialog);
                progressDialog.setProgressStyle(ProgressDialog.BUTTON_NEGATIVE);
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(true);
                progressDialog.setMessage("Creating...");
                progressDialog.show();
                Rando();
                break;
            case R.id.cancell2:
                final ProgressDialog progressDialog1 = new ProgressDialog(ConfigMap.this, R.style.TransparentProgressDialog);
                progressDialog1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog1.setIndeterminate(false);
                progressDialog1.setCancelable(true);
                progressDialog1.setMessage("Cancel...");
                progressDialog1.show();
                clearForm((ViewGroup) findViewById(R.id.activity_config_map));


                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    //********************************************************************************************************************
/*

    public static void main(String[] args) {

        System.out.println(org.apache.commons.lang3.RandomStringUtils.random(6, true, true));

    }


    static Set<String> getRandomUniqueStrings(int count, int length, boolean letters, boolean numbers){
        Set<String> rus = new HashSet<>();

        while (rus.size() < count){
            rus.add(RandomStringUtils.random(length, letters, numbers));
        }

        return rus;
    }

    public class UniqueIDTest {
 public static void main(String[] args) {
  UUID uniqueKey = UUID.randomUUID();
  System.out.println (uniqueKey);
}
}
    */


}
