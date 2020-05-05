package com.example.infoflgs;


import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rey.material.widget.FloatingActionButton;

import java.util.ArrayList;


public class SetPagerView extends Fragment {
    int pageMain;
    View _V;
    RecyclerView Rec,Rec2,Rec3;

    ClassDatabase classDatabase;
    ResAdapterPage1 resAdapterPage1;

    FloatingActionButton Flot;

    boolean lock=false;

    public static SetPagerView instance(int page){
        Bundle bundle=new Bundle();
        bundle.putInt("Key",page);
        SetPagerView setPagerView=new SetPagerView();
        setPagerView.setArguments(bundle);
        return setPagerView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (pageMain==0){
            _V=inflater.inflate(R.layout.fragment_star, container, false);
            Rec3=_V.findViewById(R.id.RecViewPage3);
            RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
            Rec3.setLayoutManager(manager);
            classDatabase=new ClassDatabase();
            ArrayList<ContentValues> contentValues=classDatabase.MakeDatabase333(getContext());

            Rec3.setAdapter(new ResAdapterPage3(getContext(),contentValues,Clik3));

        }
        if (pageMain==1){

            _V=inflater.inflate(R.layout.fragment_page2, container, false);
            Rec2=_V.findViewById(R.id.RecViewPage2);
            RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
            Rec2.setLayoutManager(manager);
            classDatabase=new ClassDatabase();
            ArrayList<ContentValues> contentValues=classDatabase.MakeDatabase222(getContext());

            Rec2.setAdapter(new ResAdapterPage2(getContext(),contentValues,Clik2));



        }
        if (pageMain==2){

            _V=inflater.inflate(R.layout.fragment_page1, container, false);

            classDatabase=new ClassDatabase();
            ArrayList<ContentValues> contentValues=classDatabase.MakeDatabase(getContext());
            //Toast.makeText(getContext(), contentValues.size(), Toast.LENGTH_SHORT).show();


            Log.i("size",contentValues.size()+"");

            Rec=_V.findViewById(R.id.RecViewPage1);
            RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
            Rec.setLayoutManager(manager);
            Rec.setAdapter(new ResAdapterPage1(getContext(),contentValues,Clik));



            //resAdapterPage1=new ResAdapterPage1(getContext(),contentValues);



        }
        return _V;
    }














    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageMain=getArguments().getInt("Key");


    }

    View.OnClickListener Clik=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ContentValues contentValues=(ContentValues)view.getTag();
            Intent intent=new Intent(getContext(),Act_ShowData.class);
            intent.putExtra("title",contentValues.getAsString("title"));
            intent.putExtra("des",contentValues.getAsString("des"));
            intent.putExtra("image",contentValues.getAsString("image"));
            intent.putExtra("Name",contentValues.getAsString("subject"));
            intent.putExtra("id",contentValues.getAsInteger("id"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


            startActivity(intent);




        }
    };

    View.OnClickListener Clik3=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ContentValues contentValues=(ContentValues)view.getTag();
            Intent intent=new Intent(getContext(),Act_ShowData.class);
            intent.putExtra("title",contentValues.getAsString("title"));
            intent.putExtra("des",contentValues.getAsString("des"));
            intent.putExtra("image",contentValues.getAsString("image"));
            intent.putExtra("Name",contentValues.getAsString("subject"));
            intent.putExtra("id",contentValues.getAsInteger("id"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


            startActivity(intent);




        }
    };


    View.OnClickListener Clik2=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (MainActivity.lock2) {
                ContentValues contentValues = (ContentValues) view.getTag();
                Intent intent = new Intent(getContext(), Act_ShowData.class);
                intent.putExtra("title", contentValues.getAsString("title"));
                intent.putExtra("des", contentValues.getAsString("des"));
                intent.putExtra("image", contentValues.getAsString("image"));
                intent.putExtra("Name", contentValues.getAsString("subject"));
                intent.putExtra("id", contentValues.getAsInteger("id"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }else {
                MainActivity.dialog.show();
            }



        }
    };
}
