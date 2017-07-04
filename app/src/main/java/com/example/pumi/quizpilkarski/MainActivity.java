package com.example.pumi.quizpilkarski;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    //Button b_highscores = (Button) findViewById(R.id.Highscores_button);
    //Button exitButton = (Button) findViewById(R.id.exit_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBackPressed() {}

    //  Przycisk zamykający aplikację
    public void Exit(View v){
        finish();
        System.exit(0);
    }
    //  Przycisk odtwierający tablicę wyników
    public void Highscores_table(View v) {
        Context context;
        context = getApplicationContext();
        Intent intent = new Intent(context,Highscores.class);
        startActivity(intent);
    }




}


//  ----------------------------------------------------------------------------------------------
//                                          BRUDNOPIS


    /*public void SaveText(View view){
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
    }*/