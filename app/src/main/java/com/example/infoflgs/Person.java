package com.example.infoflgs;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Person extends Fragment {


    View _V;

    ImageView menu, GoGalerimImg;
    TextView GotelegramTx, GoGaleriTxt, Search;
    DrawerLayout Drw;
    int _result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _V = inflater.inflate(R.layout.fragment_person, container, false);

        try {
            menu = _V.findViewById(R.id.OpenDrw);
            Drw = _V.findViewById(R.id.Drw);
            GotelegramTx = _V.findViewById(R.id.Telegram);

            GoGalerimImg = _V.findViewById(R.id.GoImage);
            //GoGaleriTxt = _V.findViewById(R.id.GoImageTxt);
            Search=_V.findViewById(R.id.search);

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("image", getActivity().MODE_PRIVATE);
            String img=sharedPreferences.getString("Adres",null);


            if (img!=null){
                GoGalerimImg.setImageURI(Uri.parse(img));
            }
/*
           if (img.equals("Adres")){
                GoGalerimImg.setImageURI(Uri.parse(img));
            }else {
                GoGalerimImg.setImageResource(R.drawable.images);
            }*/


            DrwbleMenu();
            Telegram();
            goGalery();
            SearchAct();
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }




        return _V;
    }

    public void DrwbleMenu() {
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drw.openDrawer(Gravity.LEFT);
            }
        });
    }

    public void Telegram() {

        GotelegramTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=sepehrnikbin"));
                startActivity(intent);
            }
        });
    }


    public void goGalery() {

        GoGalerimImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "قبل از انتخاب عکس به تنظیمات رفته و اجازه دسترسی را وارد کنید", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, _result);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == _result && data != null) {
            Uri uri = data.getData();

            String information[] = {MediaStore.Images.Media.DATA}; //araye 0 om hast
            Cursor cursor = getContext().getContentResolver().query(uri, information, null, null, null); //Cursur yek zare bin ast.
            cursor.moveToFirst();

            int F = cursor.getColumnIndex(information[0]);
            String Adres = cursor.getString(F);
            GoGalerimImg.setImageURI(Uri.parse(Adres));

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("image", getActivity().MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("Adres", Adres);
            editor.apply();

        }
    }




   public void SearchAct(){
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Act_Search.class));
            }
        });
    }

/*
    @Override
    public void onStart() {


        super.onStart();

        Toast.makeText(getContext(), "okkk", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("image", getContext().MODE_PRIVATE);
        String img=sharedPreferences.getString("Adres",null);
        GoGalerimImg.setImageURI(Uri.parse(img));
    }*/
}
