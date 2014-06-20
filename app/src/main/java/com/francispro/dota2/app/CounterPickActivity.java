package com.francispro.dota2.app;


import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


import com.francispro.dota2.app.dota2.db.CopyAdapter;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;


public class CounterPickActivity extends TabActivity {

    public static final String TAG = "--CounterPickActivity ";
    private TabHost th_CP;
    private ViewGroup vg;
    public static TextView tv = null,Atributo = null, Nombre = null;
    public static String URL_IMAGEN, CounterPick, StrongerPick;
    private int Currentab = 0, position=0;
    public int Identificador = 0;
    public static int [] cp = {0,0,0,0,0,0};
    public static int [] sp = {0,0,0,0,0,0};
    public static String [] cp_url = new String[6];
    public static String [] sp_url = new String[6];
    public static int pixels;

    //Variables para los Adapter
    public static int [] CPA_CP = {0,0,0,0,0,0};
    public static int [] SPA_SP = {0,0,0,0,0,0};

    private static final String DB_NAME = "DBDota2.sqlite";
    private static final int DATABASE_VERSION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_pick);

        Bundle bundle = getIntent().getExtras();
        Identificador = bundle.getInt("id");


        /*Medidas de DPI
         XHDPI < 300
         HDPI  = 240
         MDPI  = 160
         LDPI  = 120
        */
        Resources res = getResources();
        int dips = 200,aux;
        float pixelsFloat = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips, res.getDisplayMetrics());
        pixels = (int)pixelsFloat;



        //crea una variable con un nuevo tipo de fuente, el font tiene que estar en la carpeta assets
        Typeface face = Typeface.createFromAsset(getAssets(), "OptimusPrinceps.ttf");

        Intent extraer = getIntent();
        position = (Integer) extraer.getExtras().get("id");

        ImageButton imageBanner = (ImageButton)findViewById(R.id.imageButtonCP);
        imageBanner.setImageResource(R.drawable.centaur_full);
        //imageBanner.setScaleType(ImageView.ScaleType.FIT_CENTER);

        Nombre = (TextView)findViewById(R.id.Nombre_hero);
        Nombre.setText("Anti-mage");
        Nombre.setTextSize(22);
        Nombre.setTypeface(face);

        Atributo = (TextView)findViewById(R.id.Atributos_hero);
        Atributo.setText("Cuerpo a cuerpo - Portador - Escurridizo");
        Atributo.setTextSize(14);

        ImageButton imageButton;
        imageButton = (ImageButton)findViewById(R.id.imageButtonCP);

       imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), Informacion.class);
                i.putExtra("id",position);
                startActivity(i);
            }
        });


        Locale locale = Locale.getDefault();
        System.out.println("Locale is : [" + locale + "]"); // make sure there is a default Locale
        Calendar calendar = Calendar.getInstance(locale);

        //System.out.println("--CounterPick : 1");
        CopyAdapter mDbHelper = new CopyAdapter(CounterPickActivity.this);
        try {
            mDbHelper.createDatabase();
            System.out.println("--CounterPick : 2");
        } catch (SQLException e) {
            System.out.println("--CounterPick : 3");
            e.printStackTrace();

        }

        //System.out.println("--CounterPick : Valor Identificador: "+Identificador+"");


        mDbHelper.retriveData(Identificador);
        //el valor URL_IMAGEN es obtenido en la clase CopyAdapter y asignado a la variable publica para posteriormente ser asignado como ruta
        int res_imagen = this.getResources().getIdentifier("drawable/" + URL_IMAGEN, null, this.getPackageName());
        imageBanner.setImageResource(res_imagen);
        //System.out.println("--CounterPick : Valor CounterPick : "+CounterPick+"-"+res_imagen);

        TransformarCP(CounterPick);
        TransformarSP(StrongerPick);

        for(int x=0; x<6;x++)
        {
            cp_url[x] = mDbHelper.RegresaURL_Imagen(cp[x]);
            sp_url[x] = mDbHelper.RegresaURL_Imagen(sp[x]);
        }
        //System.out.println("--CounterPick : Valor cp_url: "+cp_url+"-"+sp_url);

        for(int x=0; x<6;x++)
        {
            CPA_CP[x] = this.getResources().getIdentifier("drawable/" + cp_url[x], null, this.getPackageName());
            SPA_SP[x] = this.getResources().getIdentifier("drawable/" + sp_url[x], null, this.getPackageName());
        }




        th_CP = getTabHost();
        TabHost.TabSpec specCP;
        Intent intent;

        Bundle b = new Bundle();
        b.putIntArray("MiArrayCp",CPA_CP);
        b.putIntArray("MiArraySp",SPA_SP);
        b.putIntArray("MiArrayIDCP",cp);
        b.putIntArray("MiArrayIDSP",sp);
        b.putInt("pixels",pixels);

        //CounterPick tabs
        intent = new Intent(this, cp.class);
        intent.putExtras(b);
        specCP = th_CP.newTabSpec("counterpick")
                .setIndicator("CounterPick")
                .setContent(intent);
        th_CP.addTab(specCP);

        //StrongerPick tabs
        intent = new Intent(this, sp.class);
        intent.putExtras(b);
        specCP = th_CP.newTabSpec("strongerpick")
                .setIndicator("StrongerPick")
                .setContent(intent);
        th_CP.addTab(specCP);


        // permite elegir que tab se muestre primero al ejecutar la aplicacion
        th_CP.setCurrentTab(0);





        // pinta el texto de cada tab al correr la aplicacion
        for (int i = 0; i < th_CP.getTabWidget().getTabCount(); i++) {
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

    public void TransformarCP(String valor)
    {
        String []s = CounterPick.split(",");
        for(int x=0;x<6;x++)
        {

            cp[x]= Integer.parseInt(s[x]);
            //System.out.println("valor de x : "+cp[x]+" valor de x : "+x);
        }
    }

    public void TransformarSP(String valor)
    {
        String []s = StrongerPick.split(",");
        for(int x=0;x<6;x++)
        {
            sp[x]= Integer.parseInt(s[x]);
            //System.out.println("valor de x : "+sp[x]+" valor de x : "+x);
        }
    }


    @Override
    public void onBackPressed() {
        if(position>=35)
        {
            Intent i = new Intent(getApplicationContext(), FuerzaActivity.class);
            startActivity(i);
            finish();

        }else if(position>35 && position<=69)
        {
            Intent i = new Intent(getApplicationContext(), AgilidadActivity.class);
            startActivity(i);
            finish();
        }else if(position<69)
        {
            Intent i = new Intent(getApplicationContext(), InteligenciaActivity.class);
            startActivity(i);
            finish();
        }

    }

}
