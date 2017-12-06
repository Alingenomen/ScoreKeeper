package com.example.android.scorekeeper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    private int scoreTeam1, scoreTeam2;
    private int offsidesTeam1, offsidesTeam2;
    private int cornersTeam1, cornersTeam2;
    private int foulsTeam1, foulsTeam2;
    private int yellowsTeam1, yellowsTeam2;
    private int redsTeam1, redsTeam2;

    // create a Linechart
    LineChart lineChart;

    // create ArrayList for the values along X-axis
    ArrayList<String> labels = new ArrayList<String>();

    // create ArrayList which contains
    // ArrayLists per Graphline
    ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

    // create datasets per line in the Graph
    LineDataSet dataGoalsTeam1, dataGoalsTeam2;
    LineDataSet dataOffsidesTeam1, dataOffsidesTeam2;
    LineDataSet dataCornersTeam1, dataCornersTeam2;
    LineDataSet dataFoulsTeam1, dataFoulsTeam2;
    LineDataSet dataYellowsTeam1, dataYellowsTeam2;
    LineDataSet dataRedsTeam1, dataRedsTeam2;

    // create spinner with its values
    Spinner spinnerChoice;
    String[] items = {"Goals", "Corners", "Offsides", "Fouls", "Yellow cards", "Red cards"};

    @Override
    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // Assign the chart in the UI to the
        // LineChart variable
        lineChart = (LineChart) findViewById(R.id.chart);

        // Get reference of SpinnerView from layout/main_activity.xml
        spinnerChoice =(Spinner)findViewById(R.id.fiterSpinner);

        // Create adapter for the spinner & link the spinners adapter
        // to the newly created one
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items );
        spinnerChoice.setAdapter(adapter);

        // listener for the choice made from the spinner
        spinnerChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get select item
                int sid=spinnerChoice.getSelectedItemPosition();
                switch (sid){
                    case 0:
                        showGoals();
                        break;
                    case 1:
                        showCorners();
                        break;
                    case 2:
                        showOffsides();
                        break;
                    case 3:
                        showFouls();
                        break;
                    case 4:
                        showYellows();
                        break;
                    case 5:
                        showReds();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        // Get the arraylists passed along with the
        // initialisation of the Graph Activity

        ArrayList<Entry> al_goalsTeam1 = getIntent().getParcelableArrayListExtra("goalsTeam1");
        ArrayList<Entry> al_goalsTeam2 = getIntent().getParcelableArrayListExtra("goalsTeam2");
        ArrayList<Entry> al_offsidesTeam1 = getIntent().getParcelableArrayListExtra("offsidesTeam1");
        ArrayList<Entry> al_offsidesTeam2 = getIntent().getParcelableArrayListExtra("offsidesTeam2");
        ArrayList<Entry> al_cornersTeam1 = getIntent().getParcelableArrayListExtra("cornersTeam1");
        ArrayList<Entry> al_cornersTeam2 = getIntent().getParcelableArrayListExtra("cornersTeam2");

        ArrayList<Entry> al_foulsTeam1 = getIntent().getParcelableArrayListExtra("foulsTeam1");
        ArrayList<Entry> al_foulsTeam2 = getIntent().getParcelableArrayListExtra("foulsTeam2");
        ArrayList<Entry> al_yellowsTeam1 = getIntent().getParcelableArrayListExtra("yellowsTeam1");
        ArrayList<Entry> al_yellowsTeam2 = getIntent().getParcelableArrayListExtra("yellowsTeam2");
        ArrayList<Entry> al_redsTeam1 = getIntent().getParcelableArrayListExtra("redsTeam1");
        ArrayList<Entry> al_redsTeam2 = getIntent().getParcelableArrayListExtra("redsTeam2");

        // Get the counters passed along with the
        // initialisation of the Graph activity

        scoreTeam1 = getIntent().getIntExtra("goalsTeam1int",scoreTeam1);
        scoreTeam2 = getIntent().getIntExtra("goalsTeam2int",scoreTeam2);

        cornersTeam1 = getIntent().getIntExtra("cornersTeam1int",cornersTeam1);
        cornersTeam2 = getIntent().getIntExtra("cornersTeam2int",cornersTeam2);

        offsidesTeam1 = getIntent().getIntExtra("offsidesTeam1int",offsidesTeam1);
        offsidesTeam2 = getIntent().getIntExtra("offsidesTeam2int",offsidesTeam2);

        foulsTeam1 = getIntent().getIntExtra("foulsTeam1int",foulsTeam1);
        foulsTeam2 = getIntent().getIntExtra("foulsTeam2int",foulsTeam2);

        yellowsTeam1 = getIntent().getIntExtra("yellowsTeam1int",yellowsTeam1);
        yellowsTeam2 = getIntent().getIntExtra("yellowsTeam2int",yellowsTeam2);

        redsTeam1 = getIntent().getIntExtra("redsTeam1int",redsTeam1);
        redsTeam2 = getIntent().getIntExtra("redsTeam2int",redsTeam2);

        // Fill the 12 LineDataSet with the entries

        dataGoalsTeam1 = new LineDataSet(al_goalsTeam1, "GOALS Team 1");
        dataGoalsTeam2 = new LineDataSet(al_goalsTeam2, "GOALS Team 2");
        dataOffsidesTeam1 = new LineDataSet(al_offsidesTeam1, "OFFSIDES Team 1");
        dataOffsidesTeam2 = new LineDataSet(al_offsidesTeam2, "OFFSIDES Team 2");
        dataCornersTeam1 = new LineDataSet(al_cornersTeam1, "CORNERS Team 1");
        dataCornersTeam2 = new LineDataSet(al_cornersTeam2, "CORNERS Team 2");
        dataFoulsTeam1 = new LineDataSet(al_foulsTeam1, "FOULS Team 1");
        dataFoulsTeam2 = new LineDataSet(al_foulsTeam2, "FOULS Team 2");
        dataYellowsTeam1 = new LineDataSet(al_yellowsTeam1, "YELLOW CARDS Team 1");
        dataYellowsTeam2 = new LineDataSet(al_yellowsTeam2, "YELLOW CARDS Team 2");
        dataRedsTeam1 = new LineDataSet(al_redsTeam1, "YELLOW CARDS Team 1");
        dataRedsTeam2 = new LineDataSet(al_redsTeam2, "YELLOW CARDS Team 2");

        // Add labels to the values along the
        // X-Axis, 0 to 90 minutes
        for(int i=0; i<90; i++){
            labels.add(Integer.toString(i));
        }

        // Design the graph lines
        designGraphLines();
    }

    public static int findMax(int team1, int team2) {
        int max;
        if (team1 < team2)
            max = team2;
        else
            max = team1;
        return max;
    }

    // method designGraphLines
    //////////////////////////
    //
    // This method designs the appearance of the graph lines

    public void designGraphLines() {
        // Design Goals Team 1
        dataGoalsTeam1.setColor(Color.argb(255,0,200, 200));
        dataGoalsTeam1.setValueTextColor(Color.argb(255,0,200, 200));
        dataGoalsTeam1.setFillColor(Color.argb(100,0, 200, 200));
        dataGoalsTeam1.setCircleColor(Color.argb(255,0,200, 200));
        dataGoalsTeam1.setDrawFilled(true);
        dataGoalsTeam1.setDrawCircles(true);
        dataGoalsTeam1.setDrawCubic(true);
        dataGoalsTeam1.setCircleRadius(6);
        dataGoalsTeam1.setValueTextSize(12);
        dataGoalsTeam1.setLineWidth(3);

        // Design Goals Team 2
        dataGoalsTeam2.setColor(Color.argb(255,255, 0, 0));
        dataGoalsTeam2.setValueTextColor(Color.argb(255,255, 0, 0));
        dataGoalsTeam2.setFillColor(Color.argb(100,255, 0, 0));
        dataGoalsTeam2.setCircleColor(Color.argb(255,255,0, 0));
        dataGoalsTeam2.setDrawFilled(true);
        dataGoalsTeam2.setDrawCircles(true);
        dataGoalsTeam2.setDrawCubic(true);
        dataGoalsTeam2.setCircleRadius(6);
        dataGoalsTeam2.setValueTextSize(12);
        dataGoalsTeam2.setLineWidth(3);

        // Design Corners Team 1
        dataCornersTeam1.setColor(Color.argb(255,255,0, 255));
        dataCornersTeam1.setValueTextColor(Color.argb(255,255,0, 255));
        dataCornersTeam1.setFillColor(Color.argb(100,255, 0, 255));
        dataCornersTeam1.setCircleColor(Color.argb(255,255,0, 255));
        dataCornersTeam1.setDrawFilled(true);
        dataCornersTeam1.setDrawCircles(true);
        dataCornersTeam1.setDrawCubic(true);
        dataCornersTeam1.setCircleRadius(6);
        dataCornersTeam1.setValueTextSize(12);
        dataCornersTeam1.setLineWidth(3);

        // Design Corners Team 2
        dataCornersTeam2.setColor(Color.argb(255,0,255, 0));
        dataCornersTeam2.setValueTextColor(Color.argb(255,0,255, 0));
        dataCornersTeam2.setFillColor(Color.argb(100,0, 255, 0));
        dataCornersTeam2.setCircleColor(Color.argb(255,0,255, 0));
        dataCornersTeam2.setDrawFilled(true);
        dataCornersTeam2.setDrawCircles(true);
        dataCornersTeam2.setDrawCubic(true);
        dataCornersTeam2.setCircleRadius(6);
        dataCornersTeam2.setValueTextSize(12);
        dataCornersTeam2.setLineWidth(3);

        // Design Offsides Team 1
        dataOffsidesTeam1.setColor(Color.argb(255,25,25, 25));
        dataOffsidesTeam1.setValueTextColor(Color.argb(255,25,25, 25));
        dataOffsidesTeam1.setFillColor(Color.argb(100,25, 25, 25));
        dataOffsidesTeam1.setCircleColor(Color.argb(255,25,25, 25));
        dataOffsidesTeam1.setDrawFilled(true);
        dataOffsidesTeam1.setDrawCircles(true);
        dataOffsidesTeam1.setDrawCubic(true);
        dataOffsidesTeam1.setCircleRadius(6);
        dataOffsidesTeam1.setValueTextSize(12);
        dataOffsidesTeam1.setLineWidth(3);

        // Design Offsides Team 2
        dataOffsidesTeam2.setColor(Color.argb(255,255,100, 0));
        dataOffsidesTeam2.setValueTextColor(Color.argb(255,255,100, 0));
        dataOffsidesTeam2.setFillColor(Color.argb(100,255, 100, 0));
        dataOffsidesTeam2.setCircleColor(Color.argb(255,255,100, 0));
        dataOffsidesTeam2.setDrawFilled(true);
        dataOffsidesTeam2.setDrawCircles(true);
        dataOffsidesTeam2.setDrawCubic(true);
        dataOffsidesTeam2.setCircleRadius(6);
        dataOffsidesTeam2.setValueTextSize(12);
        dataOffsidesTeam2.setLineWidth(3);

        // Design Fouls Team 1
        dataFoulsTeam1.setColor(Color.argb(255,68,36, 214));
        dataFoulsTeam1.setValueTextColor(Color.argb(255,68,36, 214));
        dataFoulsTeam1.setFillColor(Color.argb(100,68, 36, 214));
        dataFoulsTeam1.setCircleColor(Color.argb(255,68,36, 214));
        dataFoulsTeam1.setDrawFilled(true);
        dataFoulsTeam1.setDrawCircles(true);
        dataFoulsTeam1.setDrawCubic(true);
        dataFoulsTeam1.setCircleRadius(6);
        dataFoulsTeam1.setValueTextSize(12);
        dataFoulsTeam1.setLineWidth(3);

        // Design Fouls Team 2
        dataFoulsTeam2.setColor(Color.argb(255,218,212, 147));
        dataFoulsTeam2.setValueTextColor(Color.argb(255,218,212, 247));
        dataFoulsTeam2.setFillColor(Color.argb(100,218, 212, 247));
        dataFoulsTeam2.setCircleColor(Color.argb(255,218,212, 247));
        dataFoulsTeam2.setDrawFilled(true);
        dataFoulsTeam2.setDrawCircles(true);
        dataFoulsTeam2.setDrawCubic(true);
        dataFoulsTeam2.setCircleRadius(6);
        dataFoulsTeam2.setValueTextSize(12);
        dataFoulsTeam2.setLineWidth(3);

        // Design Yellows Team 1
        dataYellowsTeam1.setColor(Color.argb(255,252,203, 26));
        dataYellowsTeam1.setValueTextColor(Color.argb(255,252,203, 26));
        dataYellowsTeam1.setFillColor(Color.argb(100,252, 203, 26));
        dataYellowsTeam1.setCircleColor(Color.argb(255,252,203, 26));
        dataYellowsTeam1.setDrawFilled(true);
        dataYellowsTeam1.setDrawCircles(true);
        dataYellowsTeam1.setDrawCubic(true);
        dataYellowsTeam1.setCircleRadius(6);
        dataYellowsTeam1.setValueTextSize(12);
        dataYellowsTeam1.setLineWidth(3);

        // Design Yellows Team 2
        dataYellowsTeam2.setColor(Color.argb(255,52,43, 9));
        dataYellowsTeam2.setValueTextColor(Color.argb(255,52,43, 9));
        dataYellowsTeam2.setFillColor(Color.argb(100,52, 43, 9));
        dataYellowsTeam2.setCircleColor(Color.argb(255,52,43, 9));
        dataYellowsTeam2.setDrawFilled(true);
        dataYellowsTeam2.setDrawCircles(true);
        dataYellowsTeam2.setDrawCubic(true);
        dataYellowsTeam2.setCircleRadius(6);
        dataYellowsTeam2.setValueTextSize(12);
        dataYellowsTeam2.setLineWidth(3);

        // Design Reds Team 1
        dataRedsTeam1.setColor(Color.argb(255,184,20, 31));
        dataRedsTeam1.setValueTextColor(Color.argb(255,184,20, 31));
        dataRedsTeam1.setFillColor(Color.argb(100,184, 20, 31));
        dataRedsTeam1.setCircleColor(Color.argb(255,184,20, 31));
        dataRedsTeam1.setDrawFilled(true);
        dataRedsTeam1.setDrawCircles(true);
        dataRedsTeam1.setDrawCubic(true);
        dataRedsTeam1.setCircleRadius(6);
        dataRedsTeam1.setValueTextSize(12);
        dataRedsTeam1.setLineWidth(3);

        // Design Reds Team 2
        dataRedsTeam2.setColor(Color.argb(255,50,0, 255));
        dataRedsTeam2.setValueTextColor(Color.argb(255,50,0, 255));
        dataRedsTeam2.setFillColor(Color.argb(100,50, 0, 255));
        dataRedsTeam2.setCircleColor(Color.argb(255,50,0, 255));
        dataRedsTeam2.setDrawFilled(true);
        dataRedsTeam2.setDrawCircles(true);
        dataRedsTeam2.setDrawCubic(true);
        dataRedsTeam2.setCircleRadius(6);
        dataRedsTeam2.setValueTextSize(12);
        dataRedsTeam2.setLineWidth(3);
    }

    private void showGoals() {
        lineDataSets.clear();
        lineChart.clear();
        // Add the lineDataSets with its properties
        // to the lineDataSets

        lineDataSets.add(dataGoalsTeam1);
        lineDataSets.add(dataGoalsTeam2);

        // Initialise the Graph with the relevant data
        YAxis y = lineChart.getAxisLeft();
        y.setAxisMaxValue((findMax(scoreTeam1, scoreTeam2))+2);
        y.setAxisMinValue(0);

        y = lineChart.getAxisRight();
        y.setAxisMaxValue((findMax(scoreTeam1, scoreTeam2))+2);
        y.setAxisMinValue(0);

        lineChart.setData(new LineData(labels,lineDataSets));
        lineChart.setDescription("Goals - Waregem VS. Gent");
        lineChart.setBorderColor(Color.BLACK);
        lineChart.setBackgroundColor(Color.argb(255,100,100, 100));
        lineChart.setDescriptionTextSize(20);
        lineChart.animateY(5000);

        // chart.getAxisRight().setAxisMinValue(chartDataSet.getYMin())
        // chart.getAxisRight().setAxisMaxValue(chartDataSet.getYMax())
    }

    private void showCorners() {
        lineDataSets.clear();
        lineChart.clear();
        // Add the lineDataSets with its properties
        // to the lineDataSets

        lineDataSets.add(dataCornersTeam1);
        lineDataSets.add(dataCornersTeam2);

        // Initialise the Graph with the relevant data

        YAxis y = lineChart.getAxisLeft();
        y.setAxisMaxValue((findMax(cornersTeam1, cornersTeam2))+2);
        y.setAxisMinValue(0);

        y = lineChart.getAxisRight();
        y.setAxisMaxValue((findMax(cornersTeam1, cornersTeam2))+2);
        y.setAxisMinValue(0);

        lineChart.setData(new LineData(labels,lineDataSets));
        lineChart.setDescription("Corners - Waregem VS. Gent");
        lineChart.setBackgroundColor(Color.argb(255,100,100, 100));
        lineChart.setDescriptionTextSize(20);
        lineChart.animateY(5000);
        lineChart.setBorderColor(Color.BLACK);
    }

    private void showOffsides() {
        lineDataSets.clear();
        lineChart.clear();
        // Add the lineDataSets with its properties
        // to the lineDataSets

        lineDataSets.add(dataOffsidesTeam1);
        lineDataSets.add(dataOffsidesTeam2);

        // Initialise the Graph with the relevant data
        YAxis y = lineChart.getAxisLeft();
        y.setAxisMaxValue((findMax(offsidesTeam1, offsidesTeam2))+2);
        y.setAxisMinValue(0);

        y = lineChart.getAxisRight();
        y.setAxisMaxValue((findMax(offsidesTeam1, offsidesTeam2))+2);
        y.setAxisMinValue(0);

        lineChart.setData(new LineData(labels,lineDataSets));
        lineChart.setDescription("Offsides - Waregem VS. Gent");
        lineChart.setBackgroundColor(Color.argb(255,100,100, 100));
        lineChart.setDescriptionTextSize(20);
        lineChart.animateY(5000);
        lineChart.setBorderColor(Color.BLACK);
    }

    private void showFouls() {
        lineDataSets.clear();
        lineChart.clear();
        // Add the lineDataSets with its properties
        // to the lineDataSets

        lineDataSets.add(dataFoulsTeam1);
        lineDataSets.add(dataFoulsTeam2);

        // Initialise the Graph with the relevant data
        YAxis y = lineChart.getAxisLeft();
        y.setAxisMaxValue((findMax(foulsTeam1, foulsTeam2))+2);
        y.setAxisMinValue(0);

        y = lineChart.getAxisRight();
        y.setAxisMaxValue((findMax(foulsTeam1, foulsTeam2))+2);
        y.setAxisMinValue(0);

        lineChart.setData(new LineData(labels,lineDataSets));
        lineChart.setDescription("Fouls - Waregem VS. Gent");
        lineChart.setBackgroundColor(Color.argb(255,100,100, 100));
        lineChart.setDescriptionTextSize(20);
        lineChart.animateY(5000);
        lineChart.setBorderColor(Color.BLACK);
    }

    private void showYellows() {
        lineDataSets.clear();
        lineChart.clear();
        // Add the lineDataSets with its properties
        // to the lineDataSets

        lineDataSets.add(dataYellowsTeam1);
        lineDataSets.add(dataYellowsTeam2);

        // Initialise the Graph with the relevant data
        YAxis y = lineChart.getAxisLeft();
        y.setAxisMaxValue((findMax(yellowsTeam1, yellowsTeam2))+2);
        y.setAxisMinValue(0);

        y = lineChart.getAxisRight();
        y.setAxisMaxValue((findMax(yellowsTeam1, yellowsTeam2))+2);
        y.setAxisMinValue(0);

        lineChart.setData(new LineData(labels,lineDataSets));
        lineChart.setDescription("Yellow cards - Waregem VS. Gent");
        lineChart.setBackgroundColor(Color.argb(255,100,100, 100));
        lineChart.setDescriptionTextSize(20);
        lineChart.animateY(5000);
        lineChart.setBorderColor(Color.BLACK);
    }

    private void showReds() {
        lineDataSets.clear();
        lineChart.clear();
        // Add the lineDataSets with its properties
        // to the lineDataSets

        lineDataSets.add(dataRedsTeam1);
        lineDataSets.add(dataRedsTeam2);

        // Initialise the Graph with the relevant data
        YAxis y = lineChart.getAxisLeft();
        y.setAxisMaxValue((findMax(redsTeam1, redsTeam2))+2);
        y.setAxisMinValue(0);

        y = lineChart.getAxisRight();
        y.setAxisMaxValue((findMax(redsTeam1, redsTeam2))+2);
        y.setAxisMinValue(0);

        lineChart.setData(new LineData(labels,lineDataSets));
        lineChart.setDescription("Red cards - Waregem VS. Gent");
        lineChart.setBackgroundColor(Color.argb(255,100,100, 100));
        lineChart.setDescriptionTextSize(20);
        lineChart.animateY(5000);
        lineChart.setBorderColor(Color.BLACK);
    }
}
