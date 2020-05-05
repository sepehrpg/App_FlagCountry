package com.example.infoflgs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Act_passForm extends AppCompatActivity {
    boolean PassLock;
    EditText edtpass;

    boolean lockShowPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences1=getSharedPreferences("passFile", Context.MODE_PRIVATE);
        PassLock=sharedPreferences1.getBoolean("checkPass",false);
        lockShowPass=false;


        if (PassLock){
             setContentView(R.layout.activity_act_pass_form);
             edtpass=findViewById(R.id.edtPassAsli);
             lockShowPass=false;
             //edtpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);



        }

         else
             {
             startActivity(new Intent(Act_passForm.this,MainActivity.class));
             finish();
         }


    }

    public void OkGo(View e){
        SharedPreferences sharedPreferences1=getSharedPreferences("passFile",Context.MODE_PRIVATE);
        String PassOk=edtpass.getText().toString().trim();
        String pasShared=sharedPreferences1.getString("pass",null);

        if (PassOk.length()<3){
            Toast.makeText(this, "پسورد حداقل 4 کاراکتر باید داشته باشد", Toast.LENGTH_SHORT).show();
            return;
        }

        if (PassOk.equals(pasShared)){
            startActivity(new Intent(Act_passForm.this,MainActivity.class));
            finish();
        }
        else {
            Toast.makeText(this, "رمز وارد شده صحیح نیست", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    public void ShowPass(View t){

       if (!lockShowPass){
           edtpass.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
           //lockShowPass=true;
        }

/*
        else {
            lockShowPass=false;

        }*/

    }

}
