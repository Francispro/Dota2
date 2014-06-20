package com.francispro.dota2.app;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.*;


public class Informacion extends Activity {

    public static int Identificador = 0;
    private XYPlot plot;
    ArrayList <Double> Vector = new ArrayList<Double>();
    public Double DatoX = 0.0, Datoy = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        Bundle bundle = getIntent().getExtras();
        Identificador = bundle.getInt("id");

        plot = (XYPlot) findViewById(R.id.Grafico1);

        plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);//rango de distancia horizontal
        plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 0.5);//rango de distancia vertical
        plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.rgb(255,255,255));
        plot.getGraphWidget().getDomainGridLinePaint().setColor(Color.rgb(185, 185, 185));
        plot.getGraphWidget().getRangeGridLinePaint().setColor(Color.rgb(185, 185, 185));
        plot.setRangeBoundaries( 54, 56, BoundaryMode.FIXED);//rango vertical

        Double [] arreglo1 = {54.86, 54.64, 55.03, 54.91, 54.91, 54.91, 54.98, 54.62};
        Double [] arreglo2 = {02.06, 03.06, 05.06, 05.06, 06.06, 07.06, 08.06, 09.06};

        for(int i=0; i<7;i++)
        {
            Vector.add(arreglo1[i]);
            Vector.add(arreglo2[i]);

        }



        XYSeries series = new SimpleXYSeries(Vector, SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, "Señal");
        LineAndPointFormatter serieFormat = new LineAndPointFormatter(Color.rgb(38, 154,220), 0x000000, 0x000000, null);
        plot.clear();
        plot.addSeries(series, serieFormat);


    }

}
