package com.francispro.dota2.app.dota2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.francispro.dota2.app.CounterPickActivity;
import com.francispro.dota2.app.Informacion;

import java.io.IOException;
import java.sql.SQLException;

import static com.francispro.dota2.app.CounterPickActivity.*;

/**
 * Created by franciscojavier on 23-05-14.
 */
public class CopyAdapter {
    private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;
    private static String TAG = "--CopyAdapter ";
    private static String ACCOUNT_TABLE = "personajes";
    public static String ACCOUNT_EXTRADATA = "extraData";
    public static String ACCOUNT_ID = "_id";
    public static String ACCOUNT_ADDITIONALDATA = "additionalData";
    public static String ACCOUNT_DATA = "data";
    public static String C_GRUPO = "grupo";
    public static String C_NOMBRE = "nombre";
    public static String C_COUNTERPICK = "counterpick";
    public static String C_STRONGERPICK = "strongerpick";
    public static String C_IMG_BANNER = "img_banner";
    public static String C_ATRIBUTO = "atributo";
    public static String C_NINFO = "name_info";

    public CopyAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DatabaseHelper(mContext);
        Log.d(TAG, "done");
    }

    public CopyAdapter createDatabase() throws SQLException
    {
        try
        {
            Log.d(TAG,"create database");
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public CopyAdapter open() throws SQLException
    {
        try
        {
            Log.d(TAG,"Open");
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getWritableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public int countAccountData()
    {
        Cursor mCoursor = mDb.query(ACCOUNT_TABLE, new String[] {}, null, null, null, null, null);
        int mReturnedCount = mCoursor.getCount();
        mCoursor.close();
        return mReturnedCount;
    }

    public long insertData(String mExtra, String mAdditionalData, String mData)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(ACCOUNT_EXTRADATA, mExtra);
        initialValues.put(ACCOUNT_ADDITIONALDATA, mAdditionalData);
        initialValues.put(ACCOUNT_DATA, mData);
        return mDb.insert(ACCOUNT_TABLE, null, initialValues);
    }

    public boolean updateData(int mPosition, String mExtra, String mAdditionalData, String mData)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(ACCOUNT_EXTRADATA, mExtra);
        initialValues.put(ACCOUNT_ADDITIONALDATA, mAdditionalData);
        initialValues.put(ACCOUNT_DATA, mData);
        return mDb.update(ACCOUNT_TABLE, initialValues, "ID=" + mPosition, null) > 0;
    }

    public void retriveData(int mPosition)
    {
        try {
            mDbHelper.openDataBase();
            mDb = mDbHelper.getReadableDatabase();
        }catch (Exception error){
            Log.d("Error cursor",error.toString());
        }

        String q = "SELECT *  FROM personajes WHERE _id = " + mPosition  ;
        System.out.println(TAG+": Valor de q = "+q);
        Cursor mCursor = null;
        try{
            mCursor = mDb.rawQuery(q, null);
        }catch (Exception e){
            Log.d("Error cursor",e.toString() +" - "+q);
        }
        if (mCursor.moveToFirst())
        {
            String url, cp, sp;
            do {
                Nombre.setText(mCursor.getString(mCursor.getColumnIndex(C_NOMBRE)));
                url = mCursor.getString(mCursor.getColumnIndex(C_IMG_BANNER));
                URL_IMAGEN = url;
                Atributo.setText(mCursor.getString(mCursor.getColumnIndex(C_ATRIBUTO)));
                cp = mCursor.getString(mCursor.getColumnIndex(C_COUNTERPICK));
                CounterPick = cp;
                sp = mCursor.getString(mCursor.getColumnIndex(C_STRONGERPICK));
                StrongerPick = sp;
                System.out.println(TAG+": Valor de CounterPick = "+ cp);

            } while(mCursor.moveToNext());
        }
        else
        {
            Log.e("--CopyAdapter : No se encontraron todos los valores buscados",null);
        }
        System.out.println(TAG+": Valor de URL_IMAGEN = "+ URL_IMAGEN);
        mDbHelper.close();
        mCursor.close();
    }

    public String RegresaURL_Imagen(int mPosition)
    {
        //System.out.println("--CopyAdapter : Entrada funcion RegresaURL_Imagen mPosition : "+mPosition);
        String mReturn = "";

        try {
            mDbHelper.openDataBase();
            mDb = mDbHelper.getReadableDatabase();
        }catch (Exception error){
            Log.d("Error cursor",error.toString());
        }

        String q = "SELECT img_banner FROM personajes WHERE _id = " + mPosition  ;
        Cursor mCursor = null;

        try{
            mCursor = mDb.rawQuery(q, null);
        }catch (Exception e){
            Log.d("Error cursor",e.toString() +" - "+q);
        }

        if (mCursor.moveToFirst())
        {
            do {
                mReturn = mCursor.getString(mCursor.getColumnIndex(C_IMG_BANNER));
                //System.out.println(TAG+": mReturn: "+mReturn);
            } while(mCursor.moveToNext());
        }
        else
        {
            System.out.println(TAG+": No se encontraron todos los valores buscados");
        }
        mDbHelper.close();
        mCursor.close();

        return mReturn;
    }

    public void retriveDataFromInfo(int mPosition)
    {
        try {
            mDbHelper.openDataBase();
            mDb = mDbHelper.getReadableDatabase();
        }catch (Exception error){
            Log.d("Error cursor",error.toString());
        }

        String q = "SELECT *  FROM personajes WHERE _id = " + mPosition  ;
        System.out.println(TAG+": Valor de q = "+q);
        Cursor mCursor = null;
        try{
            mCursor = mDb.rawQuery(q, null);
        }catch (Exception e){
            Log.d("Error cursor",e.toString() +" - "+q);
        }
        if (mCursor.moveToFirst())
        {
            String url, cp, sp;
            do {
                Informacion.Text_Name.setText(mCursor.getString(mCursor.getColumnIndex(C_NOMBRE)));
                url = mCursor.getString(mCursor.getColumnIndex(C_IMG_BANNER));
                Informacion.URL_IMAGEN_INFO = url;
                Informacion.name_info = mCursor.getString(mCursor.getColumnIndex(C_NINFO));

            } while(mCursor.moveToNext());
        }
        else
        {
            Log.e("--CopyAdapter : No se encontraron todos los valores buscados",null);
        }
        System.out.println(TAG+": Valor de URL_IMAGEN = "+ URL_IMAGEN);
        mDbHelper.close();
        mCursor.close();
    }

    public String retriveAdditionalData(int mPosition)
    {
        Cursor mCursor = mDb.query(ACCOUNT_TABLE, new String[] {ACCOUNT_ADDITIONALDATA}, "ID=" + mPosition, null, null, null, null);
        mCursor.moveToFirst();
        String mReturn = mCursor.getString(mCursor.getColumnIndex(ACCOUNT_ADDITIONALDATA));
        mCursor.close();
        return mReturn;
    }

    public boolean deleteAccount(int mPosition)
    {
        return mDb.delete(ACCOUNT_TABLE, ACCOUNT_ID + "=" + mPosition, null) > 0;
    }
}