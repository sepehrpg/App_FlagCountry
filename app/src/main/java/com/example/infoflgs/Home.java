package com.example.infoflgs;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    View _V;

    TabLayout tabLayout;
    ViewPager pager;
    FloatingActionButton Flot;





    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        _V=inflater.inflate(R.layout.fragment_home, container, false);





        pager=_V.findViewById(R.id.ViewPager);
        tabLayout=_V.findViewById(R.id.tab);
        pager.setAdapter(new AdapterViewPager(getActivity().getSupportFragmentManager()));

        tabLayout.setupWithViewPager(pager);
        tabLayout.getTabAt(2).setText("صفحه 1");
        tabLayout.getTabAt(1).setText("صفحه 2");
        tabLayout.getTabAt(0).setText("برگزیده ها ");
        tabLayout.getTabAt(2).select();


        FlotingMthod();




        return _V;

    }

    public void FlotingMthod(){
        Flot=_V.findViewById(R.id.FabFloting);
        Flot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Act_Search.class));
            }
        });

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

   /*

    public void PrintFree(){

        ArrayList<ContentValues> contentValues=classDatabase.MakeDatabase(getContext());

    }*/

}
