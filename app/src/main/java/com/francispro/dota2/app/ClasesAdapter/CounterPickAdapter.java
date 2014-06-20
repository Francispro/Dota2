package com.francispro.dota2.app.ClasesAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.francispro.dota2.app.CounterPickActivity;
import com.francispro.dota2.app.R;
import com.francispro.dota2.app.cp;


/**
 * Created by franciscojavier on 27-05-14.
 */
public class CounterPickAdapter extends BaseAdapter {

    public static final String TAG = "--CounterPickAdapter ";

    private Context context;

    public CounterPickAdapter(Context applicationContext){

        context = applicationContext;
    }
    public int getCount() {
        //numero de elementos que seran mostras en la grilla
        //return imagesFuerza.length;
        return 6;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv;
        if(convertView!=null){
            iv=(ImageView) convertView;
        }
        else
        {
            iv = new ImageView(context);
            if(CounterPickActivity.pixels <= 300) {
                //HDPI  = 300  480x800
                iv.setLayoutParams(new GridView.LayoutParams(100, 80));//ajusta el (ancho,alto) general de las imagenes de la grilla
            }else {
                //XHDPI < 400  720x1280
                iv.setLayoutParams(new GridView.LayoutParams(150, 120));
            }
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(8,4,8,4);
        }


        iv.setImageResource(cp.URL_CP[position]);

        return iv;
    }

}
