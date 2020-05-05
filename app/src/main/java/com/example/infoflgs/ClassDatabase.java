package com.example.infoflgs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ClassDatabase {

    SQLiteDatabase MyDb;
    Context Ctx;


    public ArrayList<ContentValues> MakeDatabase(Context Ctx){
        ArrayList<ContentValues> arrayList=new ArrayList<>();

        MyDb=new DataBaseHelper(Ctx).getWritableDatabase();

        Cursor cursor=MyDb.rawQuery(" SELECT * FROM tablemain WHERE subject = 'free' ",null);
        //arrayList.clear();

        if (cursor.moveToFirst()){
            do {
                ContentValues contentValues=new ContentValues();

               for (int i = 0; i < cursor.getColumnCount(); i++) {

                    Log.i("sqlcursur",cursor.getColumnName(i)+ ":"+  cursor.getString(i));


                    contentValues.put(cursor.getColumnName(i), cursor.getString(i));//hamon kare ghabli vali pishrafte tare
                }
                contentValues.put("id",cursor.getInt(0));
                contentValues.put("title",cursor.getString(1));
                contentValues.put("capital",cursor.getString(2));
                contentValues.put("subject",cursor.getString(3));
                contentValues.put("fav",cursor.getInt(4));
                contentValues.put("des",cursor.getString(5));
                contentValues.put("image",cursor.getString(6));

               // Log.i("count",cursor.getColumnName(0)+":"+cursor.getString(0));

                arrayList.add(contentValues);


            }while (cursor.moveToNext());
        }
        MyDb.close();



        return arrayList;
    }




    public ArrayList<ContentValues> MakeDatabase222(Context Ctx){
        ArrayList<ContentValues> arrayList=new ArrayList<>();

        MyDb=new DataBaseHelper(Ctx).getWritableDatabase();

        Cursor cursor=MyDb.rawQuery(" SELECT * FROM tablemain WHERE subject = 'buy' ",null);

        if (cursor.moveToFirst()){
            do {
                ContentValues contentValues=new ContentValues();

             /*  for (int i = 0; i < cursor.getColumnCount(); i++) {

                    Log.i("sqlcursur",cursor.getColumnName(i)+ ":"+  cursor.getString(i));


                    contentValues.put(cursor.getColumnName(i), cursor.getString(i));//hamon kare ghabli vali pishrafte tare
                }*/
                contentValues.put("id",cursor.getInt(0));
                contentValues.put("title",cursor.getString(1));
                contentValues.put("capital",cursor.getString(2));
                contentValues.put("subject",cursor.getString(3));
                contentValues.put("fav",cursor.getInt(4));
                contentValues.put("des",cursor.getString(5));
                contentValues.put("image",cursor.getString(6));

                Log.i("count",cursor.getColumnName(1)+":"+cursor.getString(1));


                arrayList.add(contentValues);


            }while (cursor.moveToNext());
        }
        MyDb.close();



        return arrayList;
    }




    public ArrayList<ContentValues> MakeDatabase333(Context Ctx){
        ArrayList<ContentValues> arrayList=new ArrayList<>();

        MyDb=new DataBaseHelper(Ctx).getWritableDatabase();

        Cursor cursor=MyDb.rawQuery(" SELECT * FROM tablemain WHERE fav =1 ",null);

        if (cursor.moveToFirst()){
            do {
                ContentValues contentValues=new ContentValues();

             /*  for (int i = 0; i < cursor.getColumnCount(); i++) {

                    Log.i("sqlcursur",cursor.getColumnName(i)+ ":"+  cursor.getString(i));


                    contentValues.put(cursor.getColumnName(i), cursor.getString(i));//hamon kare ghabli vali pishrafte tare
                }*/
                contentValues.put("id",cursor.getInt(0));
                contentValues.put("title",cursor.getString(1));
                contentValues.put("capital",cursor.getString(2));
                contentValues.put("subject",cursor.getString(3));
                contentValues.put("fav",cursor.getInt(4));
                contentValues.put("des",cursor.getString(5));
                contentValues.put("image",cursor.getString(6));

                Log.i("count",cursor.getColumnName(1)+":"+cursor.getString(1));


                arrayList.add(contentValues);


            }while (cursor.moveToNext());
        }
        MyDb.close();



        return arrayList;
    }





/*
    public ArrayList<ContentValues> SearchMethod(Context Ctx,String  search){
        ArrayList<ContentValues> arrayList=new ArrayList<>();

        MyDb=new DataBaseHelper(Ctx).getWritableDatabase();

        Cursor cursor=MyDb.rawQuery(" SELECT * FROM tablemain "+search,null);

        if (cursor.moveToFirst()){
            do {
                ContentValues contentValues=new ContentValues();

             *//*  for (int i = 0; i < cursor.getColumnCount(); i++) {

                    Log.i("sqlcursur",cursor.getColumnName(i)+ ":"+  cursor.getString(i));


                    contentValues.put(cursor.getColumnName(i), cursor.getString(i));//hamon kare ghabli vali pishrafte tare
                }*//*
                contentValues.put("id",cursor.getInt(0));
                contentValues.put("title",cursor.getString(1));
                contentValues.put("capital",cursor.getString(2));
                contentValues.put("subject",cursor.getString(3));
                contentValues.put("fav",cursor.getInt(4));
                contentValues.put("des",cursor.getString(5));
                contentValues.put("image",cursor.getString(6));

                Log.i("count",cursor.getColumnName(1)+":"+cursor.getString(1));


                arrayList.add(contentValues);


            }while (cursor.moveToNext());
        }
        MyDb.close();



        return arrayList;
    }*/


}
