package com.example.infoflgs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView Navi;
    LinearLayout Line;
    Fragment fragment;
    Fragment fragment1;
    Fragment fragment2;
    public static Context context;

    static Typeface typefaceMain;

    static Dialog dialog;
    static boolean Lock=false;
    static boolean lock2;

    FragmentManager fragmentManager=getSupportFragmentManager();



    EditText edtPass;
    Button btnPassVorord;
    boolean lockPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            try {

                DialogShow();

                Navi = findViewById(R.id.NaviGation);
                Line = findViewById(R.id.LineLine);


                SharedPreferences shared = getSharedPreferences("fontText", Context.MODE_PRIVATE);
                int check = shared.getInt("font", 1);

                if (check == 1) {
                    typefaceMain = Typeface.defaultFromStyle(Typeface.NORMAL);
                }

                if (check == 2) {
                    typefaceMain = Typeface.createFromAsset(getAssets(), "bnasimBd.ttf");

                }
                if (check == 3) {
                    typefaceMain = Typeface.createFromAsset(getAssets(), "Bardiya-.ttf");
                    //typefaceMain=Typeface.createFromAsset(getAssets(),"bnasimBd.ttf");

                }


                fragment = new Home();
                fragment1 = new Settings();
                fragment2 = new Person();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.add(R.id.LinerMain, fragment);
                transaction.add(R.id.LinerMain, fragment1);
                transaction.add(R.id.LinerMain, fragment2);

                transaction.show(fragment);
                transaction.hide(fragment2);
                transaction.hide(fragment1);


                Navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        int id = item.getItemId();


                        if (id == R.id.home) {
                            SharedPreferences sharedPreferences = getSharedPreferences("okcode", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("res", R.id.home);
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.show(fragment);
                            transaction.hide(fragment2);
                            transaction.hide(fragment1);
                            transaction.commit();
                            editor.apply();

                            //FragmentTrans(new Home());
                        }

                        if (id == R.id.settings) {
                            SharedPreferences sharedPreferences = getSharedPreferences("okcode", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("res", R.id.settings);
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.show(fragment1);
                            transaction.hide(fragment2);
                            transaction.hide(fragment);
                            transaction.commit();
                            editor.apply();


                            //FragmentTrans(new Settings());
                        }

                        if (id == R.id.person) {
                            SharedPreferences sharedPreferences = getSharedPreferences("okcode", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("res", R.id.person);
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.show(fragment2);
                            transaction.hide(fragment1);
                            transaction.hide(fragment);
                            transaction.commit();
                            editor.apply();

                            //FragmentTrans(new Person());
                        }
                        return false;
                    }
                });

               SharedPreferences sharedPreferences = getSharedPreferences("okcode", Context.MODE_PRIVATE);
                lock2 = sharedPreferences.getBoolean("true", false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.apply();
                int page = sharedPreferences.getInt("res", 0);
                Navi.setSelectedItemId(R.id.home);

          if (page == 0) {
                Navi.setSelectedItemId(R.id.home);
            } else {
                Navi.setSelectedItemId(sharedPreferences.getInt("res", 0));
            }
                transaction.commit();

            }catch (Exception e){
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }






    public void FragmentTrans(Fragment frag ){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.LinerMain,frag);
        transaction.commit();
    }



    public void DialogShow(){

        SharedPreferences sharedPreferences=getSharedPreferences("okcode",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("code","sepehr");
        //final String okmehmanShared=sharedPreferences.getString("code",null);
        editor.apply();



        dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        TextView yes=MainActivity.dialog.findViewById(R.id.yes);
        TextView no=MainActivity.dialog.findViewById(R.id.no);
        final EditText edtDialog=dialog.findViewById(R.id.edtDialog);
        Button btnokMehman=dialog.findViewById(R.id.okmehman);



        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "برنامه دمو بوده و در آخرین به روز رسانی این قسمت را باز خواهیم کرد", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });



        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });



        //final String ok=edtDialog.getText().toString().trim();
        btnokMehman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("okcode",Context.MODE_PRIVATE);
                String okmehmanShared=sharedPreferences.getString("code",null);
                String ok=edtDialog.getText().toString().trim();
                if (ok.equals(okmehmanShared)){

                    Toast.makeText(MainActivity.this, "تایید شد", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("true",true);
                    editor.apply();

                    startActivity(new Intent(MainActivity.this,MainActivity.class));
                    finish();



                }
                else {
                    Toast.makeText(MainActivity.this, "کد وارد شده صحیح نیست", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


}
