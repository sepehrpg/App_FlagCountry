package com.example.infoflgs;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResAdapterSearchAct extends RecyclerView.Adapter<ResAdapterSearchAct._Holder> {

    ArrayList<ContentValues> arrayList;
    Context Ctx;
    View.OnClickListener Clik;

    public ResAdapterSearchAct(Context Ctx, ArrayList<ContentValues> arrayList,View.OnClickListener Clik){
        this.Ctx=Ctx;
        this.arrayList=arrayList;
        this.Clik=Clik;


    }


    @NonNull
    @Override
    public ResAdapterSearchAct._Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View _V= LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_layout,parent,false);

        return new ResAdapterSearchAct._Holder(_V);
    }

    @Override
    public void onBindViewHolder(@NonNull _Holder holder, int position) {

        ContentValues contentValues=(ContentValues)arrayList.get(position);
        String img=contentValues.getAsString("image");

        holder.title.setText(contentValues.getAsString("title"));
        holder.capital.setText(contentValues.getAsString("capital"));



        holder.title.setTypeface(MainActivity.typefaceMain);
        holder.capital.setTypeface(MainActivity.typefaceMain);
        holder.resCapitalTitle.setTypeface(MainActivity.typefaceMain);



        holder.resimgLock.setImageResource(R.drawable.ic_search_black_24dp);



        int Image=Ctx.getResources().getIdentifier(img,"drawable",Ctx.getPackageName()); //getpackageName Chist??
        holder.Img.setImageResource(Image);
        //getIdentifier=moayan konande hoviat



        holder.LineClik.setOnClickListener(Clik);
        holder.LineClik.setTag(contentValues);
    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public class _Holder extends RecyclerView.ViewHolder {

        TextView title,capital,resCapitalTitle;
        ImageView Img,resimgLock;
        LinearLayout LineClik;

        public _Holder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.resTitle);
            capital=itemView.findViewById(R.id.resCapital);
            resCapitalTitle=itemView.findViewById(R.id.resCapitalTitle);
            Img=itemView.findViewById(R.id.resImageFlag);
            resimgLock=itemView.findViewById(R.id.resimgLock);
            LineClik=itemView.findViewById(R.id.LineClik);
        }
    }
}
