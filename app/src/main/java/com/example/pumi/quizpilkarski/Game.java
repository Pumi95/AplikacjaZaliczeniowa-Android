package com.example.pumi.quizpilkarski;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import java.util.Random;
import java.util.Vector;


/**
 * Created by Pumi on 2017-07-05.
 */

public class Game extends Activity{

    public int points = 0;
    public int questionsCounter = 0;
    public String answ1 = "";

    public ImageView iv;
    public TextView tv;
    public Button b1, b2, b3, b4;


    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);




        b1 = (Button) findViewById(R.id.answer_1);
        b2 = (Button) findViewById(R.id.answer_2);
        b3 = (Button) findViewById(R.id.answer_3);
        b4 = (Button) findViewById(R.id.answer_4);
        tv = (TextView) findViewById(R.id.question_text);
        iv = (ImageView) findViewById(R.id.question_pic);


        Vector<Question> listaPytan = new Vector<Question>();

        try {
            loadQuestions(listaPytan);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //  losowanie 5-ciu pytań
        final Vector<Question> queue = new Vector<>();

        int[] chosenList = new int[5];
        for(int x : chosenList) {
            x = -1;
        }

        int chosen;
        int it = 0;
        boolean test = true;
        while (it < 5) {
            chosen = randInt(0, 13);
            for(int x : chosenList){
                if(x == chosen){
                    test = false;
                }
            }
            if(test == true){
                queue.add(listaPytan.get(chosen));
                chosenList[it] = chosen;
                it++;
            }

            test = true;
        }


        int resID = getResources().getIdentifier(queue.get(0).img_src, "drawable", "com.example.pumi.quizpilkarski");
        iv.setImageResource(resID);
        tv.setText(queue.get(0).question);
        b1.setText(queue.get(0).ans_1);
        b2.setText(queue.get(0).ans_2);
        b3.setText(queue.get(0).ans_3);
        b4.setText(queue.get(0).ans_4);
        setAnsw(b2);

        it = 1;
        b1.setOnClickListener(new MyLovelyOnClickListener(it){
            public void onClick(View v) {
                if(getQuestionsCounter()<5) {
                    setAnsw(b1);
                    if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                        addPoint();
                    }
                    toastMe(queue);
                    setQuestionsCounter();
                    changeQuestion(getQuestionsCounter(), queue);
                }

            }
        }
        );
        b2.setOnClickListener(new MyLovelyOnClickListener(it){
            public void onClick(View v) {
                if(getQuestionsCounter()<5) {
                    setAnsw(b2);
                    if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                        addPoint();
                        }
                    toastMe(queue);
                    setQuestionsCounter();
                    changeQuestion(getQuestionsCounter(), queue);
                }
            }
        }
        );
        b3.setOnClickListener(new MyLovelyOnClickListener(it) {
            public void onClick(View v) {
                if (getQuestionsCounter() < 5) {
                    setAnsw(b3);
                    if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                        addPoint();
                    }
                    toastMe(queue);
                    setQuestionsCounter();
                    changeQuestion(getQuestionsCounter(), queue);
                }
            }
        }
        );
        b4.setOnClickListener(new MyLovelyOnClickListener(it){
            public void onClick(View v) {
                if(getQuestionsCounter()<5) {
                    setAnsw(b4);
                    if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                        addPoint();
                    }
                    toastMe(queue);
                    setQuestionsCounter();
                    changeQuestion(getQuestionsCounter(), queue);
                }

            }
        }
        );



        //      tu dalszy kod !!!!!!!!!!!!!!!!!!!!!!
        //      tu dalszy kod !!!!!!!!!!!!!!!!!!!!!!
        //      tu dalszy kod !!!!!!!!!!!!!!!!!!!!!!






        //  TYLKO do doddawania pytań
/*
        try {
            AddQuestions();
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void addPoint(){
        this.points += 1;
    }
    public String getPoints(){
        String tmp = "";
        tmp = tmp.valueOf(this.points);
        return tmp;
    }

    public int getQuestionsCounter() {
        return questionsCounter;
    }
    public void setQuestionsCounter() {
        this.questionsCounter += 1;
    }

    public void setAnsw(Button b) {
        this.answ1 = (String) b.getText();
    }
    public String getAnsw() {
        return answ1;
    }

    public void toastMe(Vector<Question> queue){
        Toast.makeText(this,queue.get(getQuestionsCounter()).ans_good + " " + getAnsw() + " p:" + getPoints(),Toast.LENGTH_LONG).show();
    }

    public void changeQuestion(int it, Vector<Question> queue) {
        ImageView iv;
        TextView tv;
        Button b1, b2, b3, b4;

        b1 = (Button) findViewById(R.id.answer_1);
        b2 = (Button) findViewById(R.id.answer_2);
        b3 = (Button) findViewById(R.id.answer_3);
        b4 = (Button) findViewById(R.id.answer_4);
        tv = (TextView) findViewById(R.id.question_text);
        iv = (ImageView) findViewById(R.id.question_pic);

        if(it < 5){


            int resID = getResources().getIdentifier(queue.get(it).img_src, "drawable", "com.example.pumi.quizpilkarski");
            iv.setImageResource(resID);
            tv.setText(queue.get(it).question);
            b1.setText(queue.get(it).ans_1);
            b2.setText(queue.get(it).ans_2);
            b3.setText(queue.get(it).ans_3);
            b4.setText(queue.get(it).ans_4);
            //Toast.makeText(this,"nr. pytania:" + getQuestionsCounter() + " || punkty:" + getPoints(),Toast.LENGTH_LONG).show();
        }
        else{
            //Toast.makeText(this,"nr. pytania:" + getQuestionsCounter() + " || punkty:" + getPoints(),Toast.LENGTH_LONG).show();
        }

    }

    public void loadQuestions(Vector<Question> listaPytan) throws FileNotFoundException {

        String[] pytanie = new String[7];
        try {
            InputStream instream = openFileInput("questions.txt");

            if (instream != null) {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line=null;
                int it = 0;
                while (( line = buffreader.readLine()) != null) {
                    pytanie = line.split("x007xx");
                    listaPytan.add(new Question(pytanie[0], pytanie[1], pytanie[2], pytanie[3], pytanie[4], pytanie[5], pytanie[6]));

                    it++;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void AddQuestions() throws IOException {
        Vector<Question> listaPytan = new Vector<Question>();
        listaPytan.add(new Question("kaka_tmp", "W którym roku Kaka wygrał złotą piłkę?", "2005", "2006", "2007", "2008", "2008"));
        listaPytan.add(new Question("bvb_tmp", "W którym sezonie Borussia zdobyła tytuł mistrza Bundesligi poraz ostatni?", "2011/2012", "2010/2011", "2013/2014", "1999/2000", "2011/2012"));
        listaPytan.add(new Question("juventus_tmp", "W jakim klubie Pogba dotarł do finału ligi mistrzów w sezonie 2014/2015?", "Manchester United", "FC Porto", "Juventus", "Paris Saint Germain", "Juventus"));
        listaPytan.add(new Question("messi_tmp", "Przeciwko jakiemu zespołowi debiutował Lionel Messi w barwach FC Barcelony?", "Real Madryt", "Celta Vigo", "Espanyol", "Villareal", "Espanyol"));
        listaPytan.add(new Question("milik_tmp", "Ile bramek Arek Milik strzelił w lidze holenderskiej w sezonie 15/16?", "20", "21", "22", "18", "21"));
        listaPytan.add(new Question("barca_tmp", "W którym sezonie FC Barcelona zdobyła swój pierwszy tryplet?", "2008/2009", "2010/2011", "2013/2014", "1999/2000", "2008/2009"));
        listaPytan.add(new Question("copaamerica_tmp", "Który z wymienionych piłkarzy nigdy nie brał udziału w Copa America?", "Arturo Vidal", "Dani Alves", "Claudo Bravo", "Franco Vazquez", "Franco Vazquez"));
        listaPytan.add(new Question("misscl_tmp", "Który piłkarz rozegrał w Lidze Mistrzów najwięcej meczów i nie triumfował ani razu w tych rozgrywkach?", "Gianluigi Buffon", "Zlatan Ibrahimović", "Pavel Nedved", "Ryan Giggs", "Zlatan Ibrahimović"));
        listaPytan.add(new Question("bayern_tmp", "Który z wymienionych Polaków nie grał nigdy w Bayernie Monachium?", "Robert Lewandowski", "Sławomir Wojciechowski", "Adam Matysek", "Wayne Rozitsky", "Adam Matysek"));
        listaPytan.add(new Question("bale_tmp", "Jakiej narodowości jest Gareth Bale?", "Irlandzkiej", "Islandzkiej", "Angielskiej", "Walijskiej", "Walijskiej"));
        listaPytan.add(new Question("batman_tmp", "Z jakiego kraju pochodzi Pierre-Emerick Aubameyang?", "Z Gabonu", "Z Norwegii", "Z Kenii", "Z Francji", "Z Gabonu"));
        listaPytan.add(new Question("griezmann_tmp", "Gdzie grał Griezmann przed Atletico?", "West Ham United", "Olympique Marsylia", "Real Sociedad", "Olympique Lyon", "Real Sociedad"));
        listaPytan.add(new Question("glik_tmp", "W którym roku urodził się Kamil Glik?", "1988", "1989", "1990", "1991", "1988"));
        listaPytan.add(new Question("baggio_tmp", "Kim jest piłkarz ze zdjęcia?", "Alessandro Del Piero", "Paolo Rossi", "Roberto Baggio", "Christian Vieri", "Roberto Baggio"));



        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("questions.txt",MODE_PRIVATE));

            for(Question el : listaPytan) {
                out.write(el.inLine());
                out.write('\n');
            }

            out.close();

            Toast.makeText(this,"Text Saved !",Toast.LENGTH_LONG).show();
        }
        catch (java.io.IOException e) {
            Toast.makeText(this,"Sorry Text could't be added",Toast.LENGTH_LONG).show();
        }
    }


}



//  ----------------------------------------------------------------------------------------------
//                                          BRUDNOPIS
/*

 public void SaveText(View view){
 try {

    OutputStreamWriter out = new OutputStreamWriter(openFileOutput("myfilename.txt",MODE_APPEND));

    EditText ET = (EditText)findViewById(R.id.editText1);
    String text = ET.getText().toString();

    out.write(text);
    out.write('\n');
    out.close();

    Toast.makeText(this,"Text Saved !",Toast.LENGTH_LONG).show();
 }
 catch (java.io.IOException e) {
    Toast.makeText(this,"Sorry Text could't be added",Toast.LENGTH_LONG).show();
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



*/