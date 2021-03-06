package com.example.pumi.quizpilkarski;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*
*
*                               WAŻNE!
*                wyniki zapisane są w "scores_table.txt"
*                a statystyki w "secret_stat.txt"
* */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Created by Pumi on 2017-06-29.
 */

public class Highscores extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mProximity;
    private static final int SENSOR_SENSITIVITY = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscores);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        try {
            ShowTable();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void onBackPressed() {}
    public void Return(View v){
        Intent intents = new Intent(Highscores.this, MainActivity.class);
        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intents);
        finish();
    }
    public void ShowTable () throws FileNotFoundException {     // ShowTable (View v)
        String[] scoresList = new String[5];
        try {
            InputStream instream = openFileInput("scores_table.txt");

            if (instream != null) {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line=null;
                int it = 0;
                while (( line = buffreader.readLine()) != null) {
                    scoresList[it] = line;
                    it++;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String[] place1 = scoresList[0].split(" ");
        String[] place2 = scoresList[1].split(" ");
        String[] place3 = scoresList[2].split(" ");
        String[] place4 = scoresList[3].split(" ");
        String[] place5 = scoresList[4].split(" ");

        TextView textView;

        textView = (TextView) findViewById(R.id.first_id);
        textView.setText(place1[0]);
        textView = (TextView) findViewById(R.id.first_name);
        textView.setText(place1[1]);
        textView = (TextView) findViewById(R.id.first_score);
        textView.setText(place1[2]);

        textView = (TextView) findViewById(R.id.second_id);
        textView.setText(place2[0]);
        textView = (TextView) findViewById(R.id.second_name);
        textView.setText(place2[1]);
        textView = (TextView) findViewById(R.id.second_score);
        textView.setText(place2[2]);

        textView = (TextView) findViewById(R.id.third_id);
        textView.setText(place3[0]);
        textView = (TextView) findViewById(R.id.third_name);
        textView.setText(place3[1]);
        textView = (TextView) findViewById(R.id.third_score);
        textView.setText(place3[2]);

        textView = (TextView) findViewById(R.id.forth_id);
        textView.setText(place4[0]);
        textView = (TextView) findViewById(R.id.forth_name);
        textView.setText(place4[1]);
        textView = (TextView) findViewById(R.id.forth_score);
        textView.setText(place4[2]);

        textView = (TextView) findViewById(R.id.fifth_id);
        textView.setText(place5[0]);
        textView = (TextView) findViewById(R.id.fifth_name);
        textView.setText(place5[1]);
        textView = (TextView) findViewById(R.id.fifth_score);
        textView.setText(place5[2]);

    }

    //  czujnik zbliżenia
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                //near
                try {
                    InputStream instream = openFileInput("secret_stat.txt");

                    if (instream != null) {
                        InputStreamReader inputreader = new InputStreamReader(instream);
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line = null;
                        line = buffreader.readLine();
                        TextView textView;
                        textView = (TextView) findViewById(R.id.secret);
                        textView.setText("Rozegrane gry: " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {
                //far
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
