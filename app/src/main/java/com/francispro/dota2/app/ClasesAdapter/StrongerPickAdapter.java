package com.francispro.dota2.app.ClasesAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.francispro.dota2.app.CounterPickActivity;
import com.francispro.dota2.app.Main;
import com.francispro.dota2.app.cp;
import com.francispro.dota2.app.sp;

/**
 * Created by franciscojavier on 31-05-14.
 */
public class StrongerPickAdapter extends BaseAdapter {

    public static final String TAG = "--StrongerPickAdapter ";

    private Context context;


    public StrongerPickAdapter(Context applicationContext){

        context = applicationContext;
    }
    public int getCount() {
        //numero de elementos que seran mostras en la grilla
        //return imagesFuerza.length;
        return CounterPickActivity.largoCP_SP;
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
            if(Main.pixels == 300) {
                //HDPI  = 300  480x800
                iv.setLayoutParams(new GridView.LayoutParams(100, 80));//ajusta el (ancho,alto) general de las imagenes de la grilla
            }else if(Main.pixels == 400) {
                //XHDPI < 400  720x1280
                iv.setLayoutParams(new GridView.LayoutParams(150, 120));
            }else if(Main.pixels == 600) {
                //XHDPI < 400  1080x1920
                iv.setLayoutParams(new GridView.LayoutParams(220, 160));
            }

            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(8,4,8,4);//ajusta la separacion entre cada una de las imagenes de la grilla
        }



        iv.setImageResource(sp.URL_SP[position]);
        //System.out.println(TAG+": valor imagesFuerza = "+imagesFuerza[position]);

        return iv;
    }

}
