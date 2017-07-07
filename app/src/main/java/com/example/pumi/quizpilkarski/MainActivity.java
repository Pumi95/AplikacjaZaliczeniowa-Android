package com.example.pumi.quizpilkarski;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorMan;

    private Sensor accelerometer;
    private float[] mGravity;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    public boolean wp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorMan = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;



        //  reset licznika gier
        /*try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("secret_stat.txt",MODE_PRIVATE));
            String text = "0";
            out.write(text);
            out.close();
            Toast.makeText(this,"Counter Saved !",Toast.LENGTH_LONG).show();
        } catch (java.io.IOException e) {
            //gdy nie uda się zapisać:
            Toast.makeText(this,"Sorry Text could't be added", Toast.LENGTH_LONG).show();
        }*/
        //  reset wyników
        /*try {
            // Otwarcie pliku myfilename.txt do zapisu z trybem dopisania do
            //istniejącego pliku:
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("scores_table.txt",MODE_PRIVATE));
            // Pobranie tekstu z kontrolki EditText do obiektu klasy string
            //a następnie zapis do pliku:
            String text = "1. - 0";
            out.write(text);
            out.write('\n');

            text = "2. - 0";
            out.write(text);
            out.write('\n');

            text = "3. - 0";
            out.write(text);
            out.write('\n');

            text = "4. - 0";
            out.write(text);
            out.write('\n');

            text = "5. - 0";
            out.write(text);

            // zamknięcie pliku
            out.close();
            Toast.makeText(this,"Text Saved !",Toast.LENGTH_LONG).show();
        } catch (java.io.IOException e) {
            //gdy nie uda się zapisać:
            Toast.makeText(this,"Sorry Text could't be added", Toast.LENGTH_LONG).show();
        }*/
    }
//  czujnik ruchu

    public void onResume() {
        super.onResume();
        sensorMan.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_UI);
    }
    protected void onPause() {
        super.onPause();
        sensorMan.unregisterListener(this);
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
// can be safely ignored for this demo
    }
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values.clone();
            // Shake detection
            float x = mGravity[0];
            float y = mGravity[1];
            float z = mGravity[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float)Math.sqrt(x*x + y*y + z*z);
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            // Make this higher or lower according to how much
            // motion you want to detect



            if(mAccel > 6){
                ImageView iv = (ImageView) findViewById(R.id.testicon);
                if(wp == true){
                    iv.setImageResource(getResources().getIdentifier("test", "drawable", "com.example.pumi.quizpilkarski"));
                    wp = false;
                }
                else{
                    onDestroy();
                    wp = true;
                }
            }
        }



    }

    //  koniec ^^^
    public void onBackPressed() {}

    //  Przycisk zamykający aplikację
    public void Exit(View v){
        finishAffinity();
        System.exit(0);
    }
    //  Przycisk odtwierający tablicę wyników
    public void Highscores_table(View v) {
        Context context;
        context = getApplicationContext();
        Intent intent = new Intent(context,Highscores.class);
        startActivity(intent);
    }
    //  Przycisk włączający grę
    public void Start_game(View v) {
        Context context;
        context = getApplicationContext();
        Intent intent = new Intent(context,Game.class);
        startActivity(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        ImageView iv = (ImageView) findViewById(R.id.testicon);
        iv.setImageDrawable(null);
    }

}


//  ----------------------------------------------------------------------------------------------
//                                          BRUDNOPIS
/*


    public void SaveText(View view){
        try {
            // Otwarcie pliku myfilename.txt do zapisu z trybem dopisania do
            //istniejącego pliku:
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("scores_table.txt",MODE_APPEND));
            // Pobranie tekstu z kontrolki EditText do obiektu klasy string
            //a następnie zapis do pliku:
            String text = "1. - 0";
            out.write(text);
            out.write('\n');

            text = "2. - 0";
            out.write(text);
            out.write('\n');

            text = "3. - 0";
            out.write(text);
            out.write('\n');

            text = "4. - 0";
            out.write(text);
            out.write('\n');

            text = "5. - 0";
            out.write(text);

            // zamknięcie pliku
            out.close();
            Toast.makeText(this,"Text Saved !",Toast.LENGTH_LONG).show();
        } catch (java.io.IOException e) {
            //gdy nie uda się zapisać:
            Toast.makeText(this,"Sorry Text could't be added", Toast.LENGTH_LONG).show();
        }
    }



----------------------------------------------------------------------------------------------  */