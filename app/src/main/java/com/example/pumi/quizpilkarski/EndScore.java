package com.example.pumi.quizpilkarski;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.IntentCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.example.pumi.quizpilkarski.R.id.image;

/**
 * Created by Pumi on 2017-07-06.
 */

public class EndScore extends Activity implements SensorEventListener {
    int wynikk;
    private float currentDegree = 0f;
    private SensorManager mSensorManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endscore);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        int gameCount = 0;
        String new_line = null;
        //  Odbieranie wyniku :
        Bundle extras = getIntent().getExtras();
        String StringVariableName = extras.getString("WYNIK");
        wynikk = Integer.parseInt(StringVariableName);
        TextView tv;
        tv = (TextView) findViewById(R.id.wynikko);
        tv.setText("Twój wynik to: " + wynikk + " punktów!\n       Spójrz na północ ;)");

        //  liczbagier++
        try {
            InputStream instream = openFileInput("secret_stat.txt");

            if (instream != null) {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line = null;
                //new_line = null;
                line = buffreader.readLine();
                new_line = Integer.toString(Integer.parseInt((line.substring(0, line.length()))) + 1);

                try {
                    OutputStreamWriter out = new OutputStreamWriter(openFileOutput("secret_stat.txt",MODE_PRIVATE));
                    out.write(new_line);
                    out.close();
                } catch (java.io.IOException e) {
                    Toast.makeText(this,"Sorry Text could't be added", Toast.LENGTH_LONG).show();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(View v){
        try {
            UpdateTable(wynikk);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Context context;
        context = getApplicationContext();
        Intent intent = new Intent(context,Highscores.class);
        startActivity(intent);
    }
    public void goBack(View v){
        Intent intents = new Intent(EndScore.this, MainActivity.class);
        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intents);
        finish();
    }

    public void UpdateTable (int wynik) throws FileNotFoundException {     // ShowTable (View v)
        String[] scoresList = new String[5];
        EditText et;
        et = (EditText) findViewById(R.id.name);

        try {
            InputStream instream = openFileInput("scores_table.txt");

            if (instream != null) {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line = null;
                int it = 0;
                while (it < 5) {
                    line = buffreader.readLine();
                    scoresList[it] = line;
                    it++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] place1 = scoresList[0].split(" ");
        String[] place2 = scoresList[1].split(" ");
        String[] place3 = scoresList[2].split(" ");
        String[] place4 = scoresList[3].split(" ");
        String[] place5 = scoresList[4].split(" ");

        if (Integer.parseInt(place1[2]) < wynik){
            move(place5, place4);
            move(place4, place3);
            move(place3, place2);
            move(place2, place1);
            place1[2] = Integer.toString(wynik);
            place1[1] = et.getText().toString();
        }
        else if (Integer.parseInt(place2[2]) < wynik){
            move(place5, place4);
            move(place4, place3);
            move(place3, place2);
            place2[2] = Integer.toString(wynik);
            place2[1] = et.getText().toString();
        }
        else if (Integer.parseInt(place3[2]) < wynik) {
            move(place5, place4);
            move(place4, place3);
            place3[2] = Integer.toString(wynik);
            place3[1] = et.getText().toString();
        }
        else if (Integer.parseInt(place4[2]) < wynik){
            move(place5, place4);
            place4[2] = Integer.toString(wynik);
            place4[1] = et.getText().toString();
        }
        else if (Integer.parseInt(place5[2]) < wynik){
            place5[2] = Integer.toString(wynik);
            place5[1] = et.getText().toString();
        }

        //  zapis do pliku wyników
        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("scores_table.txt",MODE_PRIVATE));

            String text = place1[0] + " " + place1[1] + " " + place1[2];
            out.write(text);
            out.write('\n');

            text = place2[0] + " " + place2[1] + " " + place2[2];
            out.write(text);
            out.write('\n');

            text = place3[0] + " " + place3[1] + " " + place3[2];
            out.write(text);
            out.write('\n');

            text = place4[0] + " " + place4[1] + " " + place4[2];
            out.write(text);
            out.write('\n');

            text = place5[0] + " " + place5[1] + " " + place5[2];
            out.write(text);

            // zamknięcie pliku
            out.close();
        } catch (java.io.IOException e) {
            //gdy nie uda się zapisać:
            Toast.makeText(this,"Sorry Text could't be added", Toast.LENGTH_LONG).show();
        }



    }
    public void onBackPressed() {}
    public void move(String[] in, String[] out){
        in[1] = out[1];
        in[2] = out[2];
    }


    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView tvv = (TextView) findViewById(R.id.theend);
        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);
        Float.toString(degree);
        if((degree >= 0 && degree <= 15) || (degree >= 345 && degree <= 360)){
            tvv.setText("Nic specjalnego ...");
        }
        else{
            tvv.setText("");
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }


}
