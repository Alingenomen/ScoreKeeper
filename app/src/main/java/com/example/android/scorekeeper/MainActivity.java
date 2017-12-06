package com.example.android.scorekeeper;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Variables for registration

    Button startBtn, stopBtn;
    Chronometer chronometer;

    long chronometerCounter;

    private int scoreTeam1, scoreTeam2;
    private int offsidesTeam1, offsidesTeam2;
    private int cornersTeam1, cornersTeam2;
    private int foulsTeam1, foulsTeam2;
    private int yellowsTeam1, yellowsTeam2;
    private int redsTeam1, redsTeam2;

    private int state = 0;

    private TextView toChangeText;

    // Variables for Graph

    ArrayList<Entry> al_goalsTeam1 = new ArrayList<>();
    ArrayList<Entry> al_goalsTeam2 = new ArrayList<>();

    ArrayList<Entry> al_offsidesTeam1 = new ArrayList<>();
    ArrayList<Entry> al_offsidesTeam2 = new ArrayList<>();

    ArrayList<Entry> al_cornersTeam1 = new ArrayList<>();
    ArrayList<Entry> al_cornersTeam2 = new ArrayList<>();

    ArrayList<Entry> al_foulsTeam1 = new ArrayList<>();
    ArrayList<Entry> al_foulsTeam2 = new ArrayList<>();

    ArrayList<Entry> al_yellowsTeam1 = new ArrayList<>();
    ArrayList<Entry> al_yellowsTeam2 = new ArrayList<>();

    ArrayList<Entry> al_redsTeam1 = new ArrayList<>();
    ArrayList<Entry> al_redsTeam2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        startBtn = findViewById(R.id.btn_enter);
        stopBtn = findViewById(R.id.btn_stop);

        chronometer.stop();

        // Chronometer initialisation

        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(state == 0) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    state = 1;
                    startBtn.setText("Pause");
                    setGraphstart();
                }

                if(state == 1) {
                    chronometerCounter = SystemClock.elapsedRealtime();
                    chronometer.stop();
                    state = 2;
                    startBtn.setText("Resume");

                } else {
                    chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - chronometerCounter);
                    chronometer.start();
                    state = 1;
                    startBtn.setText("Pause");
                }
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                chronometer.stop();
                startBtn.setText("Start");
                state = 0;
            }

        });

        if (savedInstanceState != null){
            scoreTeam1 = savedInstanceState.getInt("goalsTeam1int");
            scoreTeam2 = savedInstanceState.getInt("goalsTeam2int");
            cornersTeam1 = savedInstanceState.getInt("cornersTeam1int");
            cornersTeam2 = savedInstanceState.getInt("cornersTeam2int");
            offsidesTeam1 = savedInstanceState.getInt("offsidesTeam1int");
            offsidesTeam2 = savedInstanceState.getInt("offsidesTeam2int");
            foulsTeam1 = savedInstanceState.getInt("foulsTeam1int");
            foulsTeam2 = savedInstanceState.getInt("foulsTeam2int");
            yellowsTeam1 = savedInstanceState.getInt("yellowsTeam1int");
            yellowsTeam2 = savedInstanceState.getInt("yellowsTeam2int");
            redsTeam1 = savedInstanceState.getInt("redsTeam1int");
            redsTeam2 = savedInstanceState.getInt("redsTeam2int");

            al_goalsTeam1 = savedInstanceState.getParcelableArrayList("goalsTeam1");
            al_goalsTeam2 = savedInstanceState.getParcelableArrayList("goalsTeam2");
            al_cornersTeam1 = savedInstanceState.getParcelableArrayList("cornersTeam1");
            al_cornersTeam2 = savedInstanceState.getParcelableArrayList("cornersTeam2");
            al_offsidesTeam1 = savedInstanceState.getParcelableArrayList("offsidesTeam1");
            al_offsidesTeam2 = savedInstanceState.getParcelableArrayList("offsidesTeam2");
            al_foulsTeam1 = savedInstanceState.getParcelableArrayList("foulsTeam1");
            al_foulsTeam2 = savedInstanceState.getParcelableArrayList("foulsTeam2");
            al_yellowsTeam1 = savedInstanceState.getParcelableArrayList("yellowsTeam1");
            al_yellowsTeam2 = savedInstanceState.getParcelableArrayList("yellowsTeam2");
            al_redsTeam1 = savedInstanceState.getParcelableArrayList("redsTeam1");
            al_redsTeam2 = savedInstanceState.getParcelableArrayList("redsTeam2");




            displayAll();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // load the current variables in a "saved state"
        // using the "put" command followed by the
        // variable type
        outState.putInt("goalsTeam1int",scoreTeam1);
        outState.putInt("goalsTeam2int",scoreTeam2);
        outState.putInt("cornersTeam1int",cornersTeam1);
        outState.putInt("cornersTeam2int",cornersTeam2);
        outState.putInt("offsidesTeam1int",offsidesTeam1);
        outState.putInt("offsidesTeam2int",offsidesTeam2);
        outState.putInt("foulsTeam1int",foulsTeam1);
        outState.putInt("foulsTeam2int",foulsTeam2);
        outState.putInt("yellowsTeam1int",yellowsTeam1);
        outState.putInt("yellowsTeam2int",yellowsTeam2);
        outState.putInt("redsTeam1int",redsTeam1);
        outState.putInt("redsTeam2int",redsTeam2);

        outState.putParcelableArrayList("goalsTeam1",al_goalsTeam1);
        outState.putParcelableArrayList("goalsTeam2",al_goalsTeam2);
        outState.putParcelableArrayList("cornersTeam1",al_cornersTeam1);
        outState.putParcelableArrayList("cornersTeam2",al_cornersTeam2);
        outState.putParcelableArrayList("offsidesTeam1",al_offsidesTeam1);
        outState.putParcelableArrayList("offsidesTeam2",al_offsidesTeam2);
        outState.putParcelableArrayList("foulsTeam1",al_foulsTeam1);
        outState.putParcelableArrayList("foulsTeam2",al_foulsTeam2);
        outState.putParcelableArrayList("yellowsTeam1",al_yellowsTeam1);
        outState.putParcelableArrayList("yellowsTeam2",al_yellowsTeam2);
        outState.putParcelableArrayList("redsTeam1",al_redsTeam1);
        outState.putParcelableArrayList("redsTeam2",al_redsTeam2);
    }

    // updateCounter method
    // ////////////////////
    //
    // This single method is called by every button in the app
    // along with the button id from which method is called from.
    // The updateCounter method passes the view-object
    // with the 2 methods it contains

    public void updateCounter(View view) {
        register(view);
        display(view);
    }

    // increment method
    // ////////////////
    //
    // The increment method first checks the view which
    // originally called the method before (updateCounter)
    //
    // By getting its ID we can assign the functionality
    // accordingly.
    //
    // The switch (view.getId()) line fetches the
    // id that the view has which has been passed to the
    // increment function while the "case" statement
    // does the selection what needs to be done in
    // which case

    public void register(View view){
        switch (view.getId()) {
            case R.id.goalButtonTeam1:
                scoreTeam1 += 1;
                al_goalsTeam1.add(new Entry(scoreTeam1, fetchTime()));
                break;
            case R.id.goalButtonTeam2:
                scoreTeam2 += 1;
                al_goalsTeam2.add(new Entry(scoreTeam2, fetchTime()));
                break;
            case R.id.offsideButtonTeam1:
                offsidesTeam1 += 1;
                al_offsidesTeam1.add(new Entry(offsidesTeam1, fetchTime()));
                break;
            case R.id.offsideButtonTeam2:
                offsidesTeam2 += 1;
                al_offsidesTeam2.add(new Entry(offsidesTeam2, fetchTime()));
                break;
            case R.id.cornerButtonTeam1:
                cornersTeam1 += 1;
                al_cornersTeam1.add(new Entry(cornersTeam1, fetchTime()));
                break;
            case R.id.cornerButtonTeam2:
                cornersTeam2 += 1;
                al_cornersTeam2.add(new Entry(cornersTeam2, fetchTime()));
                break;
            case R.id.foulButtonTeam1:
                foulsTeam1 += 1;
                al_foulsTeam1.add(new Entry(foulsTeam1, fetchTime()));
                break;
            case R.id.foulButtonTeam2:
                foulsTeam2 += 1;
                al_foulsTeam2.add(new Entry(foulsTeam2, fetchTime()));
                break;
            case R.id.yellowButtonTeam1:
                yellowsTeam1 += 1;
                al_yellowsTeam1.add(new Entry(yellowsTeam1, fetchTime()));
                break;
            case R.id.yellowButtonTeam2:
                yellowsTeam2 +=1;
                al_yellowsTeam2.add(new Entry(yellowsTeam2, fetchTime()));
                break;
            case R.id.redButtonTeam1:
                redsTeam1 += 1;
                al_redsTeam1.add(new Entry(redsTeam1, fetchTime()));
                break;
            case R.id.redButtonTeam2:
                redsTeam2 += 1;
                al_redsTeam2.add(new Entry(redsTeam2, fetchTime()));
                break;
        }
    }

    // fetchTime method
    ///////////////////
    //
    // This method reads the time on the counter
    // and returns its value in minutes

    public int fetchTime(){
        long timestamp;
        int index;
        timestamp = SystemClock.elapsedRealtime() - chronometer.getBase();
        timestamp = (timestamp / 1000) / 60;
        index = (int) timestamp;
        return index;
    }

    // display methods
    // //////////////
    // The display method follows the same principle as
    // the increment method does.
    //
    // displayAll
    /////////////
    // Display all counters

    public void display(View view) {
        switch (view.getId()) {
            case R.id.goalButtonTeam1:
                toChangeText = findViewById(R.id.goalTextTeam1);
                toChangeText.setText("" + scoreTeam1);
                break;
            case R.id.goalButtonTeam2:
                toChangeText = findViewById(R.id.goalTextTeam2);
                toChangeText.setText("" + scoreTeam2);
                break;
            case R.id.offsideButtonTeam1:
                toChangeText = findViewById(R.id.offsideTextTeam1);
                toChangeText.setText("" + offsidesTeam1);
                break;
            case R.id.offsideButtonTeam2:
                toChangeText = findViewById(R.id.offsideTextTeam2);
                toChangeText.setText("" + offsidesTeam2);
                break;
            case R.id.cornerButtonTeam1:
                toChangeText = findViewById(R.id.cornerTextTeam1);
                toChangeText.setText("" + cornersTeam1);
                break;
            case R.id.cornerButtonTeam2:
                toChangeText = findViewById(R.id.cornerTextTeam2);
                toChangeText.setText("" + cornersTeam2);
                break;
            case R.id.foulButtonTeam1:
                toChangeText = findViewById(R.id.foulTextTeam1);
                toChangeText.setText("" + foulsTeam1);
                break;
            case R.id.foulButtonTeam2:
                toChangeText = findViewById(R.id.foulTextTeam2);
                toChangeText.setText("" + foulsTeam2);
                break;
            case R.id.yellowButtonTeam1:
                toChangeText = findViewById(R.id.yellowTextTeam1);
                toChangeText.setText("" + yellowsTeam1);
                break;
            case R.id.yellowButtonTeam2:
                toChangeText = findViewById(R.id.yellowTextTeam2);
                toChangeText.setText("" + yellowsTeam2);
                break;
            case R.id.redButtonTeam1:
                toChangeText = findViewById(R.id.redTextTeam1);
                toChangeText.setText("" + redsTeam1);
                break;
            case R.id.redButtonTeam2:
                toChangeText = findViewById(R.id.redTextTeam2);
                toChangeText.setText("" + redsTeam2);
                break;
        }
    }

    private void displayAll() {
        toChangeText = findViewById(R.id.goalTextTeam1);
        toChangeText.setText("" + scoreTeam1);
        toChangeText = findViewById(R.id.goalTextTeam2);
        toChangeText.setText("" + scoreTeam2);
        toChangeText = findViewById(R.id.offsideTextTeam1);
        toChangeText.setText("" + offsidesTeam1);
        toChangeText = findViewById(R.id.offsideTextTeam2);
        toChangeText.setText("" + offsidesTeam2);
        toChangeText = findViewById(R.id.cornerTextTeam1);
        toChangeText.setText("" + cornersTeam1);
        toChangeText = findViewById(R.id.cornerTextTeam2);
        toChangeText.setText("" + cornersTeam2);
        toChangeText = findViewById(R.id.foulTextTeam1);
        toChangeText.setText("" + foulsTeam1);
        toChangeText = findViewById(R.id.foulTextTeam2);
        toChangeText.setText("" + foulsTeam2);
        toChangeText = findViewById(R.id.yellowTextTeam1);
        toChangeText.setText("" + yellowsTeam1);
        toChangeText = findViewById(R.id.yellowTextTeam2);
        toChangeText.setText("" + yellowsTeam2);
        toChangeText = findViewById(R.id.redTextTeam1);
        toChangeText.setText("" + redsTeam1);
        toChangeText = findViewById(R.id.redTextTeam2);
        toChangeText.setText("" + redsTeam2);
    }


    //resetViews method
    ///////////////////
    //
    // This method resets the counters
    // and redisplays the views
    public void resetViews(View view){
        scoreTeam1 = 0;
        scoreTeam2 = 0;
        offsidesTeam1 = 0;
        offsidesTeam2 = 0;
        cornersTeam1= 0;
        cornersTeam2 = 0;
        foulsTeam1 = 0;
        foulsTeam2 = 0;
        yellowsTeam1 = 0;
        yellowsTeam2 = 0;
        redsTeam1 = 0;
        redsTeam2 = 0;

        toChangeText = findViewById(R.id.goalTextTeam1);
        toChangeText.setText("" + scoreTeam1);
        toChangeText = findViewById(R.id.goalTextTeam2);
        toChangeText.setText("" + scoreTeam2);
        toChangeText = findViewById(R.id.offsideTextTeam1);
        toChangeText.setText("" + offsidesTeam1);
        toChangeText = findViewById(R.id.offsideTextTeam2);
        toChangeText.setText("" + offsidesTeam2);
        toChangeText = findViewById(R.id.cornerTextTeam1);
        toChangeText.setText("" + cornersTeam1);
        toChangeText = findViewById(R.id.cornerTextTeam2);
        toChangeText.setText("" + cornersTeam2);
        toChangeText = findViewById(R.id.foulTextTeam1);
        toChangeText.setText("" + foulsTeam1);
        toChangeText = findViewById(R.id.foulTextTeam2);
        toChangeText.setText("" + foulsTeam2);
        toChangeText = findViewById(R.id.yellowTextTeam1);
        toChangeText.setText("" + yellowsTeam1);
        toChangeText = findViewById(R.id.yellowTextTeam2);
        toChangeText.setText("" + yellowsTeam2);
        toChangeText = findViewById(R.id.redTextTeam1);
        toChangeText.setText("" + redsTeam1);
        toChangeText = findViewById(R.id.redTextTeam2);
        toChangeText.setText("" + redsTeam2);

    }

    // showResults method
    /////////////////////
    //
    // this method shows the Graph of the whole match
    // in a new activity called "GraphActivity"
    public void showResults(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        // pass the arraylists to the Graph activity,
        // containing the data for the graph

        setGraphend();

        intent.putParcelableArrayListExtra("goalsTeam1",al_goalsTeam1);
        intent.putParcelableArrayListExtra("goalsTeam2", al_goalsTeam2);
        intent.putParcelableArrayListExtra("offsidesTeam1",al_offsidesTeam1);
        intent.putParcelableArrayListExtra("offsidesTeam2", al_offsidesTeam2);
        intent.putParcelableArrayListExtra("cornersTeam1",al_cornersTeam1);
        intent.putParcelableArrayListExtra("cornersTeam2", al_cornersTeam2);
        intent.putParcelableArrayListExtra("foulsTeam1",al_foulsTeam1);
        intent.putParcelableArrayListExtra("foulsTeam2", al_foulsTeam2);
        intent.putParcelableArrayListExtra("yellowsTeam1",al_yellowsTeam1);
        intent.putParcelableArrayListExtra("yellowsTeam2", al_yellowsTeam2);
        intent.putParcelableArrayListExtra("redsTeam1",al_redsTeam1);
        intent.putParcelableArrayListExtra("redsTeam2", al_redsTeam2);

        intent.putExtra("goalsTeam1int",scoreTeam1);
        intent.putExtra("goalsTeam2int",scoreTeam2);
        intent.putExtra("cornersTeam1int",cornersTeam1);
        intent.putExtra("cornersTeam2int",cornersTeam2);
        intent.putExtra("offsidesTeam1int",offsidesTeam1);
        intent.putExtra("offsidesTeam2int",offsidesTeam2);
        intent.putExtra("foulsTeam1int",foulsTeam1);
        intent.putExtra("foulsTeam2int",foulsTeam2);
        intent.putExtra("yellowsTeam1int",yellowsTeam1);
        intent.putExtra("yellowsTeam2int",yellowsTeam2);
        intent.putExtra("redsTeam1int",redsTeam1);
        intent.putExtra("redsTeam2int",redsTeam2);



        // Start new activity, holding the graph which
        // initialises it using its onCreate method

        startActivity(intent);
    }

    // setGraphstart method
    ///////////////////////
    //
    // This method sets a datapoint at the beginning of the graph
    // for each dataline
    private void setGraphstart() {
        al_goalsTeam1.add(new Entry(0, 0));
        al_goalsTeam2.add(new Entry(0, 0));
        al_offsidesTeam1.add(new Entry(0, 0));
        al_offsidesTeam2.add(new Entry(0, 0));
        al_cornersTeam1.add(new Entry(0, 0));
        al_cornersTeam2.add(new Entry(0, 0));
        al_foulsTeam1.add(new Entry(0, 0));
        al_foulsTeam2.add(new Entry(0, 0));
        al_yellowsTeam1.add(new Entry(0, 0));
        al_yellowsTeam2.add(new Entry(0, 0));
    }

    // setGraphend method
    ///////////////////////
    //
    // This method sets a datapoint at the end of the graph
    // for each dataline
    private void setGraphend() {
        al_goalsTeam1.add(new Entry(scoreTeam1, 90));
        al_goalsTeam2.add(new Entry(scoreTeam2, 90));
        al_offsidesTeam1.add(new Entry(offsidesTeam1, 90));
        al_offsidesTeam2.add(new Entry(offsidesTeam2, 90));
        al_cornersTeam1.add(new Entry(cornersTeam1, 90));
        al_cornersTeam2.add(new Entry(cornersTeam2, 90));
        al_foulsTeam1.add(new Entry(foulsTeam1, 90));
        al_foulsTeam2.add(new Entry(foulsTeam2, 90));
        al_yellowsTeam1.add(new Entry(yellowsTeam1, 90));
        al_yellowsTeam2.add(new Entry(yellowsTeam2, 90));
        al_redsTeam1.add(new Entry(redsTeam1, 90));
        al_redsTeam2.add(new Entry(redsTeam2, 90));
    }

}
