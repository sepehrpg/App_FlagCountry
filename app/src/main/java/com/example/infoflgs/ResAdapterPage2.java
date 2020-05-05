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

public class ResAdapterPage2 extends RecyclerView.Adapter<ResAdapterPage2._Holder> {


    ArrayList<ContentValues> arrayList;
    Context Ctx;
    View.OnClickListener Clik;

    public ResAdapterPage2(Context Ctx, ArrayList<ContentValues> arrayList,View.OnClickListener Clik){
        this.Ctx=Ctx;
        this.arrayList=arrayList;
        this.Clik=Clik;

    }



    @NonNull
    @Override
    public ResAdapterPage2._Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View _V= LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_layout,parent,false);

        return new ResAdapterPage2._Holder(_V);
    }

    @Override
    public void onBindViewHolder(@NonNull ResAdapterPage2._Holder holder, int position) {

        ContentValues contentValues=(ContentValues)arrayList.get(position);
        holder.title.setText(contentValues.getAsString("title"));
        holder.capital.setText(contentValues.getAsString("capital"));

        if (MainActivity.lock2){
            holder.resimgLock.setImageResource(R.drawable.ic_lock_open);
        }
        holder.title.setTypeface(MainActivity.typefaceMain);
        holder.capital.setTypeface(MainActivity.typefaceMain);
        holder.resCapitalTitle.setTypeface(MainActivity.typefaceMain);


        String img=contentValues.getAsString("image");
        int Image=Ctx.getResources().getIdentifier(img,"drawable",Ctx.getPackageName());
        holder.Img.setImageResource(Image);



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
