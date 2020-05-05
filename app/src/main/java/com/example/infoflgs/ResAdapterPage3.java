package com.example.infoflgs;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResAdapterPage3 extends RecyclerView.Adapter<ResAdapterPage3._Holder> {

    Context Ctx;
    ArrayList<ContentValues> arrayList;
    View.OnClickListener Clik;






    public ResAdapterPage3(Context Ctx, ArrayList<ContentValues> arrayList,    View.OnClickListener Clik){

        this.Ctx=Ctx;
        this.arrayList=arrayList;
        this.Clik=Clik;
    }


    @NonNull
    @Override
    public ResAdapterPage3._Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View _V= LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_layout,parent,false);

        return new ResAdapterPage3._Holder(_V);
    }

    @Override
    public void onBindViewHolder(@NonNull ResAdapterPage3._Holder holder, int position) {

        ContentValues contentValues=(ContentValues)arrayList.get(position);
        String img=contentValues.getAsString("image");

        holder.title.setText(contentValues.getAsString("title"));
        holder.capital.setText(contentValues.getAsString("capital"));

        //holder.title.setTypeface(MainActivity.typeface);
        //holder.capital.setTypeface(MainActivity.typeface);

        //holder.resCapitalTitle.setTypeface(MainActivity.typeface);
        holder.resimgLock.setImageResource(R.drawable.ic_star);
        holder.resimgLock.setColorFilter(Color.RED);


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
