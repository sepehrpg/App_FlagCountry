package com.example.infoflgs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Act_ShowData extends AppCompatActivity {

    TextView title, Description;
    ImageView ImageCollaps;
    static ImageView FavImage;

    int id;

    SQLiteDatabase MyDatabase;
    ResAdapterPage1 adapterPage1;

    int Lock;
    int count;
    int count2;

    int red = Color.RED;


    String Name;
    String img;
    String des;
    String Title;
    ClassDatabase classDatabase;
    int newid;

    static boolean finish=false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__show_data);
        title = findViewById(R.id.Showtitle);
        Description = findViewById(R.id.Description);
        ImageCollaps = findViewById(R.id.ImageCollaps);
        FavImage = findViewById(R.id.FavImage);
        classDatabase = new ClassDatabase();
        MyDatabase = new DataBaseHelper(this).getWritableDatabase();

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim);
        Animation animation2= AnimationUtils.loadAnimation(this,R.anim.anim2);
        title.setAnimation(animation);
        Description.setAnimation(animation2);

        SharedPreferences sharedPreferences=getSharedPreferences("sizeText", Context.MODE_PRIVATE);
        Description.setTextSize(sharedPreferences.getInt("size",20));


        Title = getIntent().getStringExtra("title");
        des = getIntent().getStringExtra("des");
        img = getIntent().getStringExtra("image");
        Name = getIntent().getStringExtra("Name");
        id = getIntent().getIntExtra("id", 0);

        count = id;

        FavImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (LockMethod() == 0) {
                    FavImage.setColorFilter(Color.RED);
                    MyDatabase.execSQL(" UPDATE tablemain SET fav=1 WHERE id =" + id);
                    FavImage.setTag("red");
                   // ArrayList<ContentValues> contentValues=classDatabase.MakeDatabase333(Act_ShowData.this);
                   // new ResAdapterPage3(Act_ShowData.this,contentValues,clik000).notifyDataSetChanged();

                } else if (LockMethod() == 1) {
                    FavImage.setColorFilter(Color.BLACK);
                    MyDatabase.execSQL(" UPDATE tablemain SET fav=0 WHERE id =" + id);
                    FavImage.setTag("black");
                    //ArrayList<ContentValues> contentValues=classDatabase.MakeDatabase333(Act_ShowData.this);
                    //new ResAdapterPage3(Act_ShowData.this,contentValues,clik000).notifyDataSetChanged();
                }
                //ArrayList<ContentValues> contentValues=classDatabase.MakeDatabase333(Act_ShowData.this);



/*
                if (!LikeOrDisLike()){
                    FavImage.setColorFilter(Color.RED);
                    MyDatabase.execSQL(" UPDATE tablemain SET fav=1 WHERE id ="+id);
                }
                else {
                    FavImage.setColorFilter(Color.BLACK);
                    MyDatabase.execSQL(" UPDATE tablemain SET fav=0 WHERE id ="+id);
                }*/


            }
        });


        if (LockMethod() == 0) {
            FavImage.setColorFilter(Color.BLACK);

        } else if (LockMethod() == 1) {
            FavImage.setColorFilter(Color.RED);

        }
/*
        if (LikeOrDisLike()){
            FavImage.setColorFilter(Color.RED);

        }
        else{
            FavImage.setColorFilter(Color.BLACK);
        }*/


        if (Name.equals("free")) {
            //title.setText(id+"");
            title.setText(Title);
            Description.setText(des);
            int image = getResources().getIdentifier(img, "drawable", this.getPackageName());
            ImageCollaps.setImageResource(image);

        } else if (Name.equals("buy")) {
            title.setText(Title);
            Description.setText(des);
            int image = getResources().getIdentifier(img, "drawable", this.getPackageName());
            ImageCollaps.setImageResource(image);
        }


    }
/*
    public boolean LikeOrDisLike(){

        Cursor cursor=MyDatabase.rawQuery("SELECT * FROM tablemain WHERE id = "+id , null);
        while (cursor.moveToNext()){ //khili mohem ast
            Lock=cursor.getInt(4);
        }

        if (Lock==1){
            return true;
        }else {
            return false;
        }

    }*/

View.OnClickListener clik000=new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
};

    public int LockMethod() {

        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM tablemain WHERE id = " + id, null);
        while (cursor.moveToNext()) { //khili mohem ast
            Lock = cursor.getInt(4);
        }
        if (Lock == 1) {
            return 1;

        } else {
            return 0;
        }


    }

    public int LockMethodNew() {

        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM tablemain WHERE id = " + newid, null);
        while (cursor.moveToNext()) { //khili mohem ast
            Lock = cursor.getInt(4);
        }
        if (Lock == 1) {
            return 1;

        } else {
            return 0;
        }


    }


    public void next(View _V) {


        if (Name.equals("free")) {

            if (count <= 9) {
                count++; //nokte array list ha az 0 shoro mishavand vali id haye ma az 1 pas dota dota miparad
                ArrayList<ContentValues> arrayList = classDatabase.MakeDatabase(this);
                ContentValues contentValues = (ContentValues) arrayList.get(count - 1);
                title.setText(contentValues.getAsString("title"));
                Description.setText(contentValues.getAsString("des"));
                String newimageName = contentValues.getAsString("image");
                newid = contentValues.getAsInteger("id");
                id = newid;

                int Image = getResources().getIdentifier(newimageName, "drawable", getPackageName()); //getpackageName Chist??
                ImageCollaps.setImageResource(Image);


                if (LockMethodNew() == 0) {
                    FavImage.setColorFilter(Color.BLACK);
                    //MyDatabase.execSQL(" UPDATE tablemain SET fav=1 WHERE id =" + newid);
                    FavImage.setTag("red");
                } else if (LockMethodNew() == 1) {
                    FavImage.setColorFilter(Color.RED);
                    //MyDatabase.execSQL(" UPDATE tablemain SET fav=0 WHERE id =" + newid);
                    FavImage.setTag("black");
                }


            }
            if (count == 10) {
                Toast.makeText(this, "پایان لیست", Toast.LENGTH_SHORT).show();
                return;
            }


        } else if (Name.equals("buy")) {


            if (count <= 17) {
                try {
                    count++; //nokte array list ha az 0 shoro mishavand vali id haye ma az 1 pas dota dota miparad //12  12-*=
                    ArrayList<ContentValues> arrayList = classDatabase.MakeDatabase222(this);
                    ContentValues contentValues = (ContentValues) arrayList.get(count-11);
                    title.setText(contentValues.getAsString("title"));
                    Description.setText(contentValues.getAsString("des"));
                    String newimageName = contentValues.getAsString("image");
                    newid = contentValues.getAsInteger("id");
                    id = newid;

                    int Image = getResources().getIdentifier(newimageName, "drawable", getPackageName()); //getpackageName Chist??
                    ImageCollaps.setImageResource(Image);


                if (LockMethodNew() == 0) {
                    FavImage.setColorFilter(Color.BLACK);
                    //MyDatabase.execSQL(" UPDATE tablemain SET fav=1 WHERE id =" + newid);
                    FavImage.setTag("red");
                } else if (LockMethodNew() == 1) {
                    FavImage.setColorFilter(Color.RED);
                    //MyDatabase.execSQL(" UPDATE tablemain SET fav=0 WHERE id =" + newid);
                    FavImage.setTag("black");
                }

                }catch (Exception e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }





            }
            if (count==17){
                Toast.makeText(this, "پایان لیست", Toast.LENGTH_SHORT).show();
                return;
            }

        }


    }

    public void preview(View _V) {

       if (Name.equals("free")){

            if (count>1){
                count--; //nokte array list ha az 0 shoro mishavand vali id haye ma az 1 pas dota dota miparad
                ArrayList<ContentValues> arrayList=classDatabase.MakeDatabase(this);
                ContentValues contentValues=(ContentValues)arrayList.get(count-1);
                title.setText(contentValues.getAsString("title"));
                Description.setText(contentValues.getAsString("des"));
                String newimageName=contentValues.getAsString("image");
                newid=contentValues.getAsInteger("id");
                id=newid;

                int Image=getResources().getIdentifier(newimageName,"drawable",getPackageName()); //getpackageName Chist??
                ImageCollaps.setImageResource(Image);


                if (LockMethodNew() == 0) {
                    FavImage.setColorFilter(Color.BLACK);
                    //MyDatabase.execSQL(" UPDATE tablemain SET fav=1 WHERE id =" + newid);
                    FavImage.setTag("red");
                } else if (LockMethodNew() == 1) {
                    FavImage.setColorFilter(Color.RED);
                    //MyDatabase.execSQL(" UPDATE tablemain SET fav=0 WHERE id =" + newid);
                    FavImage.setTag("black");
                }


            }
            else if (count==1){
                Toast.makeText(this, "ابتدای لیست", Toast.LENGTH_SHORT).show();
                return;
            }

        }

       else if (Name.equals("buy")){

           if (count>11){
               count--; //nokte array list ha az 0 shoro mishavand vali id haye ma az 1 pas dota dota miparad
               ArrayList<ContentValues> arrayList=classDatabase.MakeDatabase222(this);
               ContentValues contentValues=(ContentValues)arrayList.get(count-11);
               title.setText(contentValues.getAsString("title"));
               Description.setText(contentValues.getAsString("des"));
               String newimageName=contentValues.getAsString("image");
               newid=contentValues.getAsInteger("id");
               id=newid;

               int Image=getResources().getIdentifier(newimageName,"drawable",getPackageName()); //getpackageName Chist??
               ImageCollaps.setImageResource(Image);


               if (LockMethodNew() == 0) {
                   FavImage.setColorFilter(Color.BLACK);
                   //MyDatabase.execSQL(" UPDATE tablemain SET fav=1 WHERE id =" + newid);
                   FavImage.setTag("red");
               } else if (LockMethodNew() == 1) {
                   FavImage.setColorFilter(Color.RED);
                   //MyDatabase.execSQL(" UPDATE tablemain SET fav=0 WHERE id =" + newid);
                   FavImage.setTag("black");
               }


           }
           else if (count==11){
               Toast.makeText(this, "ابتدای لیست", Toast.LENGTH_SHORT).show();
               return;
           }
       }
    }


    public void copy(View F){
        ClipboardManager manager=(ClipboardManager)Act_ShowData.this.getSystemService(CLIPBOARD_SERVICE);
        String Des=Description.getText().toString().trim();
        manager.setText(Des);
        //manager.setText((CharSequence) Description); //ya ravesh bala ya cast kon az noe string chon alan textview hast na string
        Toast.makeText(this, "متن کپی شد", Toast.LENGTH_SHORT).show();

    }
    public void share(View g){
        String Des=Description.getText().toString().trim();
        Intent intent=new Intent(Intent.ACTION_SEND); //***tarif intent va dadan vazife be an

        intent.setType("text/plain");  //plain=sade  //hatman plain ham bayad bashad
        // ***dadan ghaleb be intent ke avalan az noe text befres va bad plain ra gharar dadim ta abzar ferestande ma sade bashan
        // mesl telegram ya whats app ke agar nadim method kar nmikonad

        intent.putExtra(Intent.EXTRA_TEXT,Des); //dadan meghdar be intent va zakhire kardan on dar poshe Intent.EXTRA_TEXT ta goshi ono bekhone
        startActivity(Intent.createChooser(intent,"اشتراک متن")); //Chooser=entekhab konande //hatman bayad create choser bashad
        //***vazife sakht entekhab konande ha mesl telegram ya whatsapp ra darad


    }

    public void sms(View g){

        ClipboardManager manager=(ClipboardManager)Act_ShowData.this.getSystemService(CLIPBOARD_SERVICE);
        String Des=Description.getText().toString().trim();
        manager.setText(Des);
        //manager.setText((CharSequence) Description); //ya ravesh bala ya cast kon az noe string chon alan textview hast na string
        Toast.makeText(this, "متن کپی شد", Toast.LENGTH_SHORT).show();



        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.putExtra("sms",Des);
        intent.setType("vnd.android-dir/mms-sms");
        startActivity(intent);


        //************SMS KHODESH MATN RA BAR NMIDARAD VA NAGHES AST**********

    }


}
