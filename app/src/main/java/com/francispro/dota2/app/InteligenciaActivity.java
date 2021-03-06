package com.francispro.dota2.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.francispro.dota2.app.ClasesAdapter.ImageAdapter;
import com.francispro.dota2.app.ClasesAdapter.ImageAdapterInteligencia;


public class InteligenciaActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inteligencia);


        GridView gv = (GridView)findViewById(R.id.gridViewInteligencia);
        gv.setAdapter(new ImageAdapterInteligencia(getApplicationContext()));


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentView, View iv, int position, long id) {
                position=position+69+1;
                Intent i = new Intent(getApplicationContext(), CounterPickActivity.class);
                i.putExtra("id",position);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        InteligenciaActivity.this.finish();
    }

}
