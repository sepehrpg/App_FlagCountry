package com.example.infoflgs;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.Slider;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {

    View _V;
    Slider slider;
    TextView Txnemone,TxnemoneFont;
    RadioButton pishfarz,nasim,bardia;
    Typeface typeface;
    Typeface typeface22;



    CardView cardView;
    Switch _Swich;
    Button btnokPass,btnfinishApp;
    EditText _Pass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _V=inflater.inflate(R.layout.fragment_settings, container, false);
        init();
        SizeText();
        FontText();
        PassSettings();
        finishApp();


        return _V;
    }




    public void init(){
        Txnemone=_V.findViewById(R.id.Nemone);
        TxnemoneFont=_V.findViewById(R.id.nemonefount);
        slider=_V.findViewById(R.id.slider);
        pishfarz=_V.findViewById(R.id.Pishfarz);
        nasim=_V.findViewById(R.id.nasim);
        bardia=_V.findViewById(R.id.bardia);
        _Swich=_V.findViewById(R.id.swichBtn);
        cardView=_V.findViewById(R.id.CardViewGone);
        btnokPass=_V.findViewById(R.id.btnOkMakePass);
        btnfinishApp=_V.findViewById(R.id.finishApp);
        _Pass=_V.findViewById(R.id.edtMakePass);

        typeface22 = Typeface.createFromAsset(getActivity().getAssets(), "Bardiya-.ttf");
        typeface =Typeface.createFromAsset(getActivity().getAssets(), "bnasimBd.ttf");

    }




    public void SizeText(){
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("sizeText", Context.MODE_PRIVATE);
        int sizeMain=sharedPreferences.getInt("size",20);
        Txnemone.setTextSize(sizeMain);
        slider.setValue((float)sizeMain,false);
        slider.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {
                Txnemone.setTextSize(newValue);
                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("sizeText", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("size",newValue);
                editor.apply();
            }
        });

    }





    public void FontText(){

        SharedPreferences shared=getActivity().getSharedPreferences("fontText",Context.MODE_PRIVATE);
        int check=shared.getInt("font",1);
        if (check==1){
            pishfarz.setChecked(true);
            TxnemoneFont.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        if (check==2){
            nasim.setChecked(true);
            TxnemoneFont.setTypeface(typeface);
        }
        if (check==3){
            bardia.setChecked(true);
            TxnemoneFont.setTypeface(typeface22);
        }
        pishfarz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (pishfarz.isChecked()){
                    SharedPreferences shared=getActivity().getSharedPreferences("fontText",Context.MODE_PRIVATE);
                    SharedPreferences.Editor EdtShared=shared.edit();
                    EdtShared.putInt("font",1);
                    TxnemoneFont.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    EdtShared.apply();

                    MainActivity.typefaceMain=(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
            }
        });
        nasim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (nasim.isChecked()){
                    SharedPreferences shared=getActivity().getSharedPreferences("fontText",Context.MODE_PRIVATE);
                    SharedPreferences.Editor EdtShared=shared.edit();
                    EdtShared.putInt("font",2);
                    TxnemoneFont.setTypeface(typeface);
                    EdtShared.apply();

                    MainActivity.typefaceMain=Typeface.createFromAsset(getActivity().getAssets(), "bnasimBd.ttf");
                }
            }
        });
        bardia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (bardia.isChecked()){
                    SharedPreferences shared=getActivity().getSharedPreferences("fontText",Context.MODE_PRIVATE);
                    SharedPreferences.Editor EdtShared=shared.edit();
                    EdtShared.putInt("font",3);
                    TxnemoneFont.setTypeface(typeface22);
                    EdtShared.apply();
                    MainActivity.typefaceMain=Typeface.createFromAsset(getActivity().getAssets(), "Bardiya-.ttf");
                }
            }
        });
    }




    public void PassSettings(){
        final SharedPreferences sharedPreferences=getActivity().getSharedPreferences("swich",Context.MODE_PRIVATE);


        _Swich.setChecked(sharedPreferences.getBoolean("swichT",false));
        if (_Swich.isChecked()){
            cardView.setVisibility(View.VISIBLE);
        }
        else {
            cardView.setVisibility(View.GONE);
        }

        _Swich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("swich",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();

                if (_Swich.isChecked()){
                    editor.putBoolean("swichT",true);
                    cardView.setVisibility(View.VISIBLE);
                    editor.apply();
                }
                else
                    {
                        editor.putBoolean("swichT",false);
                        cardView.setVisibility(View.GONE);
                        editor.apply();

                        if (!_Swich.isChecked()){
                            SharedPreferences sharedPreferences1=getActivity().getSharedPreferences("passFile",Context.MODE_PRIVATE);
                            SharedPreferences.Editor passFileEdit=sharedPreferences1.edit();
                            passFileEdit.putBoolean("checkPass",false);
                            passFileEdit.apply();
                        }
                    }

            }
        });








        btnokPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass=_Pass.getText().toString().trim();

                if (pass.length()<3){
                    Toast.makeText(getContext(), "پسورد حداقل 4 کاراکتر باید داشته باشد", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences sharedPreferences1=getActivity().getSharedPreferences("passFile",Context.MODE_PRIVATE);
                SharedPreferences.Editor passFileEdit=sharedPreferences1.edit();
                passFileEdit.putString("pass",pass);
                passFileEdit.putBoolean("checkPass",true);

                Toast.makeText(getActivity(), "پسورد وارد شد. یک بار از برنامه خارج و دوباره وارد شوید", Toast.LENGTH_LONG).show();
                passFileEdit.apply();

            }
        });



    }

    public void finishApp(){

        btnfinishApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }



}
