package com.francispro.dota2.app;


import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;


public class Main extends TabActivity {

    public static final String TAG = "--Main ";
    public static int pixels;
    private TabHost mTabHost;
    private ViewGroup vg;
    private TextView tv;
    private int Currentab = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        mTabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent = null;

        /*Medidas de DPI
         XHDPI < 300
         HDPI  = 240
         MDPI  = 160
         LDPI  = 120

         ________
         XHDPI < 400  720x1280
         HDPI  = 300  480x800
        */

        int dips = 200,aux;
        float pixelsFloat = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips, res.getDisplayMetrics());
        pixels = (int)pixelsFloat;

        System.out.println(TAG+"Valor Pixels :"+pixels);


        //Fuerza tabs
        intent = new Intent(this, FuerzaActivity.class);
        spec = mTabHost.newTabSpec("fuerza")
                .setIndicator("Fuerza")
                //.setIndicator("", res.getDrawable(R.drawable.tab_icon))
                .setContent(intent);

        mTabHost.addTab(spec);

        //Agilidad tabs
        intent = new Intent(this, AgilidadActivity.class);
        spec = mTabHost.newTabSpec("agilidad")
                .setIndicator("Agilidad")
                //.setIndicator("", res.getDrawable(R.drawable.tab_icon_agilidad))
                .setContent(intent);
        mTabHost.addTab(spec);

        //Inteligencia tabs
        intent = new Intent(this, InteligenciaActivity.class);
        spec = mTabHost.newTabSpec("inteligencia")
                .setIndicator("Inteligencia")
                //.setIndicator("", res.getDrawable(R.drawable.tab_icon_inteligencia))
                .setContent(intent);
        mTabHost.addTab(spec);

        // permite elegir que tab se muestre primero al ejecutar la aplicacion
        mTabHost.setCurrentTab(0);

        mTabHost = getTabHost();

        //crea una variable con un nuevo tipo de fuente, el font tiene que estar en la carpeta assets
        Typeface face = Typeface.createFromAsset(getAssets(), "Realize.otf");


        // pinta el texto de cada tab al correr la aplicacion
        for (int i = 0; i < mTabHost.getTabWidget().getTabCount(); i++) {
            vg = (ViewGroup) getTabHost().getTabWidget().getChildAt(i);
            tv = (TextView) vg.getChildAt(1);

            //modifica el tamaño de la letra, se relaciona con el archivo values/dimensions.xml
            tv.setTextSize(getResources().getDimension(R.dimen.textsize));

            //cambia el tipo de fuente por defecto por el que se cargo al "face"
            tv.setTypeface(face);

            if (i == 0) {
                tv.setTextColor(Color.parseColor("#2daed9"));
                Currentab = 0;
            } else {
                tv.setTextColor(Color.parseColor("#a9a9a9"));
            }
        }

        // configura el color de cada tab una vez que es pulsada
        getTabHost().setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                int i = getTabHost().getCurrentTab();
                if (Currentab != i) {
                    vg = (ViewGroup) getTabHost().getTabWidget().getChildAt(Currentab);
                    tv = (TextView) vg.getChildAt(1);
                    tv.setTextColor(Color.parseColor("#a9a9a9"));// cuando esta off

                    Currentab = i;
                    vg = (ViewGroup) getTabHost().getTabWidget()
                            .getChildAt(i);
                    tv = (TextView) vg.getChildAt(1);
                    tv.setTextColor(Color.parseColor("#2daed9"));// cuando esta on
                }
            }
        });

    }
    public void onBackPressed() {
        finish();
    }

}
