package com.example.pumi.quizpilkarski;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.IntentCompat;
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
import java.util.UUID;
import java.util.Vector;


/**
 * Created by Pumi on 2017-07-05.
 */

public class Game extends Activity{

    private static final int NOTIFICATION_ID= 1; //arbitrary constant

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

        //  losowanie 8-ciu pytań
        final Vector<Question> queue = new Vector<>();

        int[] chosenList = new int[8];
        for(int x : chosenList) {
            x = -1;
        }

        int chosen;
        int it = 0;
        boolean test = true;
        while (it < 8) {
            chosen = randInt(0, 44);
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
                if(getQuestionsCounter()<7) {
                    setAnsw(b1);
                    if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                        addPoint();
                    }
                    //toastMe(queue);
                    setQuestionsCounter();
                    changeQuestion(getQuestionsCounter(), queue);
                }
                else {
                    setAnsw(b1);
                    if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                        addPoint();
                    }
                    Context context;
                    context = getApplicationContext();
                    Bundle extras = new Bundle();
                    Intent intent = new Intent(Game.this,EndScore.class);
                    extras.putString("WYNIK", getPoints().toString());
                    intent.putExtras(extras);
                    startActivity(intent);
                }

            }
        }
        );
        b2.setOnClickListener(new MyLovelyOnClickListener(it){
            public void onClick(View v) {
                if(getQuestionsCounter()<7) {
                     setAnsw(b2);
                     if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                         addPoint();
                     }
                     //toastMe(queue);
                     setQuestionsCounter();
                     changeQuestion(getQuestionsCounter(), queue);
                }
                else {
                    setAnsw(b2);
                    if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                        addPoint();
                    }
                    Context context;
                    context = getApplicationContext();
                    Bundle extras = new Bundle();
                    Intent intent = new Intent(Game.this,EndScore.class);
                    extras.putString("WYNIK", getPoints().toString());
                    intent.putExtras(extras);
                    startActivity(intent);
                }

            }
        }
        );
        b3.setOnClickListener(new MyLovelyOnClickListener(it){
            public void onClick(View v) {
                if(getQuestionsCounter()<7) {
                    setAnsw(b3);
                    if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                        addPoint();
                    }
                    //toastMe(queue);
                    setQuestionsCounter();
                    changeQuestion(getQuestionsCounter(), queue);
                }
                else {
                    setAnsw(b3);
                    if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                        addPoint();
                    }
                    Context context;
                    context = getApplicationContext();
                    Bundle extras = new Bundle();
                    Intent intent = new Intent(Game.this,EndScore.class);
                    extras.putString("WYNIK", getPoints().toString());
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        }
        );
        b4.setOnClickListener(new MyLovelyOnClickListener(it){
            public void onClick(View v) {
                if(getQuestionsCounter()<7) {
                    setAnsw(b4);
                    if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                        addPoint();
                    }
                    //toastMe(queue);
                    setQuestionsCounter();
                    changeQuestion(getQuestionsCounter(), queue);
                }
                else {
                    setAnsw(b4);
                    if(getAnsw().equals(queue.get(getQuestionsCounter()).ans_good)){
                        addPoint();
                    }
                    Context context;
                    context = getApplicationContext();
                    Bundle extras = new Bundle();
                    Intent intent = new Intent(Game.this,EndScore.class);
                    extras.putString("WYNIK", getPoints().toString());
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        }
        );


        //  TYLKO do doddawania pytań

        /*try {
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
    public void onBackPressed() {
        Intent intents = new Intent(Game.this, MainActivity.class);
        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intents);
        finish();
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

        if(it < 8){


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
        listaPytan.add(new Question("wba_tmp", "Czyje to koszulki ?", "FC Lorient", "West Bromwich Albion", "Udinese Calcio", "Hertha Berlin", "West Bromwich Albion"));
        listaPytan.add(new Question("villareal_tmp", "Czyje to koszulki ?", "Atletico Madryt", "Espanyol", "Villareal", "Leganes", "Villareal"));
        listaPytan.add(new Question("piast_tmp", "Czyje to koszulki ?", "Bordeaux", "Lech Poznań", "Chelsea", "Piast Gliwice", "Piast Gliwice"));
        listaPytan.add(new Question("liverpool_tmp", "Czyje to koszulki?", "Liverpool", "Manchester United", "Bayern Monachium", "AS Roma", "Liverpool"));
        listaPytan.add(new Question("leeds_tmp", "Czyje to koszulki?", "West Bromwich Albion", "Crystal Palace", "Leeds United", "Tottenham", "Leeds United"));
        listaPytan.add(new Question("nice_tmp", "Czyje to koszulki?", "OGC Nice", "FC Lorient", "FC Bologna", "FSV Frankfurt", "OGC Nice"));
        listaPytan.add(new Question("bordeaux_tmp", "Czyje to koszulki?", "Olympique Lyon", "Bordeaux", "Schalke 04", "Piast Gliwice", "Bordeaux"));
        listaPytan.add(new Question("tsg_tmp", "Czyje to koszulki>", "TSG Hoffenheim", "Schalke 04", "Chelsea", "Everton", "TSG Hoffenheim"));
        listaPytan.add(new Question("borussia_tmp", "Czyje to koszulki?", "Villareal", "Getafe", "Liverpool", "Borussia Dortmund", "Borussia Dortmund"));
        listaPytan.add(new Question("udine_tmp", "Czyje to koszulki?", "Udinese", "West Bromwich Albion", "Juventus", "FC Parma", "Udinese"));
        listaPytan.add(new Question("parma_tmp", "Czyje to koszulki?", "Udinese", "Juventus", "FC Parma", "West Bromwich Albion", "FC Parma"));
        listaPytan.add(new Question("jardim_tmp", "Który szkoleniowiec widnieje na zdjęciu?", "Pep Guardiola", "Michał Probierz", "Leandro Jardim", "Jurgen Nagelsmann", "Leandro Jardim"));
        listaPytan.add(new Question("valverde_tmp", "Który szkoleniowiec widnieje na zdjęciu?", "Ernesto Valverde", "Michał Latal", "Kiko Ramirez", "Luciano Spaletti", "Ernesto Valverde"));
        listaPytan.add(new Question("julian_tmp", "Który szkoleniowiec widnieje na zdjęciu?", "Andre Villas_Boas", "Luigi Di Baggio", "Julian Nagelsmann", "Mauricio Pochettino", "Julian Nagelsmann"));
        listaPytan.add(new Question("pochettino_tmp", "Który szkoleniowiec widnieje na zdjęciu?", "Andre Villas_Boas", "Luigi Di Baggio", "Julian Nagelsmann", "Mauricio Pochettino", "Mauricio Pochettino"));
        listaPytan.add(new Question("cl_tmp", "Kto wygrał Ligę mistrzów w sezonie 2006/2007?", "AC Milan", "Manchester United", "Chelsea", "FC Barcelona", "AC Milan"));
        listaPytan.add(new Question("cl_tmp", "Kto wygrał Ligę mistrzów w sezonie 2005/2006?", "AC Milan", "Manchester United", "Chelsea", "FC Barcelona", "FC Barcelona"));
        listaPytan.add(new Question("cl_tmp", "Kto wygrał Ligę mistrzów w sezonie 2004/2005?", "AC Milan", "Manchester United", "Liverpool", "FC Barcelona", "Liverpool"));
        listaPytan.add(new Question("cl_tmp", "Kto wygrał Ligę mistrzów w sezonie 2003/2004?", "AC Milan", "Manchester United", "FC Porto", "FC Barcelona", "FC Porto"));
        listaPytan.add(new Question("cl_tmp", "Kto wygrał Ligę mistrzów w sezonie 1999/2000?", "AC Milan", "Real Madryt", "Manchester United", "Juventus Turyn", "Real Madryt"));
        listaPytan.add(new Question("cl_tmp", "Który z poniższych klubów wygrał Ligę Mistrzów najwięcej razy?", "Benfica", "Celtic", "Olympique Marsylia", "Chelsea", "Benfica"));
        listaPytan.add(new Question("cl_tmp", "Który z poniższych klubów brał udział w finale Ligi Mistrzów najmniej razy?", "Valencia", "Arsenal", "Stade Reims", "Chelsea", "Arsenal"));
        listaPytan.add(new Question("cl_tmp", "Który z poniższych klubów brał udział w finale Ligi Mistrzów najmniej razy?", "Hamburger SV", "AS Monaco", "Steaua Bukareszt", "Atletico Madryt", "AS Monaco"));
        listaPytan.add(new Question("premier_tmp", "Który z podanych zawodników miał najwięcej występów na angielskich boiskach?", "Gary Speed", "David James", "Frank Lampard", "Gareth Barry", "Gareth Barry"));
        listaPytan.add(new Question("premier_tmp", "Który z podanych zawodników jest najlepszym strzelcem na angielskich boiskach?", "Frank Lampard", "Wayne Rooney", "Alan Shearer", "Andrew Cole", "Alan Shearer"));
        listaPytan.add(new Question("laliga_tmp", "Który z podanych zawodników miał najwięcej występów na hiszpańskich boiskach?", "Andoni Zubizarreta", "Raúl González", "Eusebio", "Francisco Buyo", "Andoni Zubizarreta"));
        listaPytan.add(new Question("laliga_tmp", "Który z podanych zawodników jest najlepszym strzelcem na hiszpańskich boiskach?", "Raúl González", "Hugo Sánchez", "Alfredo Di Stéfano", "César Rodríguez", "Hugo Sánchez"));
        listaPytan.add(new Question("seriea_tmp", "Który z podanych zawodników miał najwięcej występów na włoskich boiskach?", "Javier Zanetti", "Francesco Totti", "Gianluigi Buffon", "Paolo Maldini", "Paolo Maldini"));
        listaPytan.add(new Question("seriea_tmp", "Który z podanych zawodników jest najlepszym strzelcem na włoskich boiskach?", "Alessandro Del Piero", "Roberto Baggio", "Silvio Piola", "Francesco Totti", "Silvio Piola"));
        listaPytan.add(new Question("polska_tmp", "Który z podanych zawodników miał najwięcej występów w polskiej reprezentacji?", "Jacek Krzynówek", "Grzegorz Lato", "Michał Żewłakow", "Kazimierz Deyna", "Michał Żewłakow"));
        listaPytan.add(new Question("polska_tmp", "Który z podanych zawodników jest najlepszym strzelcem w polskiej reprezentacji?", "Kazimierz Deyna", "Robert Lewandowski", "Grzegorz Lato", "Andrzej Szarmach", "Robert Lewandowski"));







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