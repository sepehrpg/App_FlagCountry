package com.example.infoflgs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Act_Search extends AppCompatActivity {

    EditText capitalTx,countryTx;
    ClassDatabase classDatabase;
    RecyclerView ResSearch;
    ArrayList<ContentValues> arrayList;

    ResAdapterSearchAct AdapterSearch;

    SQLiteDatabase MyDataSql;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__search);

        MyDataSql=new DataBaseHelper(this).getWritableDatabase();
        TimerMethod();
        capitalTx=findViewById(R.id.SearchCapital);
        countryTx=findViewById(R.id.SearchCountry);
        classDatabase=new ClassDatabase();
        ResSearch=findViewById(R.id.ResSearchAct);

        arrayList=new ArrayList<>();// hatman bayad por shavad

        RecyclerView.LayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        ResSearch.setLayoutManager(manager);


        //PrintSearch("");
        PrintSearch("");
        //arrayList.clear();






        //ResSearch.setAdapter(new ResAdapterSearchAct(this,arrayList,clikSearch));
        AdapterSearch=new ResAdapterSearchAct(this,arrayList,clikSearch);
        //ResSearch.setAdapter(AdapterSearch);
        ResSearch.setAdapter(AdapterSearch);





        capitalTx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search=" WHERE capital LIKE '%"+charSequence.toString().trim()+"%'";
                PrintSearch(search);
                AdapterSearch.notifyDataSetChanged();



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        countryTx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //ResSearch.removeAllViews();
                //arrayList.clear(); //ok bod
                String search2="WHERE title LIKE '%"+charSequence.toString().trim()+"%' ";
                //PrintSearch(search2);
                PrintSearch(search2);
                AdapterSearch.notifyDataSetChanged();

                //PrintSearch(charSequence.toString());
                //String search=" WHERE title LIKE '%"+charSequence.toString().trim()+"%'";

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }



    public void PrintError(String serch){
        //arrayList.clear();

        //arrayList=classDatabase.SearchMethod(this,serch);
      /*
        for (int f=0;f<arrayList.size();f++){

            ContentValues contentValues1=arrayList.get(f);

        }*/


        //in rah javab nmidahad chon bayad arrylisti ke samte resc view mifrestim clear shavad
    }


    int ff=2;
    public void PrintSearch(String search2){

        try {

            //arrayList =classDatabase.SearchMethod(this,search2);


            Cursor cursor=MyDataSql.rawQuery(" SELECT * FROM tablemain " +search2,null);
            arrayList.clear();

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
            //MyDataSql.close();


        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }




    }

    public void TimerMethod(){

        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String titleC=countryTx.getText().toString().trim();
                        String capitalC=capitalTx.getText().toString().trim();

                        if (titleC.isEmpty()&&capitalC.isEmpty()){
                            arrayList.clear();
                            ResSearch.setAdapter(AdapterSearch);
                        }



                    }
                });
            }
        },1,1500);

    }


    View.OnClickListener clikSearch=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ContentValues AllcontentValues = (ContentValues) view.getTag();
            String Subject = AllcontentValues.getAsString("subject");
            if (Subject.equals("free")) {
                ContentValues contentValues=(ContentValues)view.getTag();
                Intent intent=new Intent(Act_Search.this,Act_ShowData.class);
                intent.putExtra("title",contentValues.getAsString("title"));
                intent.putExtra("des",contentValues.getAsString("des"));
                intent.putExtra("image",contentValues.getAsString("image"));
                intent.putExtra("Name",contentValues.getAsString("subject"));
                intent.putExtra("id",contentValues.getAsInteger("id"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                startActivity(intent);
            }

            else {

                if (MainActivity.lock2) {
                    ContentValues contentValues = (ContentValues) view.getTag();
                    Intent intent = new Intent(Act_Search.this, Act_ShowData.class);
                    intent.putExtra("title", contentValues.getAsString("title"));
                    intent.putExtra("des", contentValues.getAsString("des"));
                    intent.putExtra("image", contentValues.getAsString("image"));
                    intent.putExtra("Name", contentValues.getAsString("subject"));
                    intent.putExtra("id", contentValues.getAsInteger("id"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);
                } else {
                    Toast.makeText(Act_Search.this, "این قسمت خریداری  هنوز نشده", Toast.LENGTH_SHORT).show();
                }

            }
        }
    };
}
