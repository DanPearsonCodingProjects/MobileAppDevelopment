package com.example.theatreapp2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    //This sends the information from the array list to the recycler view
    private List<details> detail; //Creating a variable called detail that can store
    //a list with the attributes that the details class has
    private LayoutInflater inflater;
    Context cont;


    public adapter(Context c, List<details> detailConst) {
        cont = c;
        detail = detailConst;
        inflater = LayoutInflater.from(c);

    }

    @NonNull
    @Override
    public adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.my_row,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.MyViewHolder holder, int position) {
        //Make variables then assign them
        String nam = detail.get(position).getTitle();
        String des = detail.get(position).getDescription();
        String date = detail.get(position).getDate();
        String loc = detail.get(position).getLocation();
        int time = detail.get(position).getTime();
        int im = detail.get(position).getImages();
        boolean wheelchairAccess = detail.get(position).getWheelchairAccess();
        boolean epilepsyWarning = detail.get(position).getEpilepsy();

        //apply them to the holder below which is called holder
        //Sets the text in eah card in the recycler view
        holder.title2.setText(nam);
        holder.desc2.setText(des);
        holder.date2.setText(date);
        holder.time3.setText(String.valueOf(time)+":00");
        holder.img2.setImageResource(im);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cont,Main_Theatre.class);
                intent.putExtra("title",nam);
                intent.putExtra("desc",des);
                intent.putExtra("date",date);
                intent.putExtra("time",time);
                intent.putExtra("img",im);
                intent.putExtra("wheelchairAccess",wheelchairAccess);
                intent.putExtra("epilepsyWarning",epilepsyWarning);
                intent.putExtra("location",loc);
                cont.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return detail.size();
    }

    public void filterList(List<details> filterList1){
        //This takes a parameter arbitrarily called filterList1
        //which has the data type of the details class in a list
        detail = filterList1;
        //This then changes the list to only show what is inside the filterlist1
        //filterlist1 is = to the List<details> filteredList = new ArrayList<>(); in the main
        //activity
        notifyDataSetChanged();

    }




    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title2,desc2,date2,time2,time3;
        ImageView img2;

        public MyViewHolder(@NonNull View view){
            super(view);

            title2 = itemView.findViewById(R.id.title1);
            desc2 = itemView.findViewById(R.id.desc1);
            date2 = itemView.findViewById(R.id.date);
            img2 = itemView.findViewById(R.id.imageView);
            time2 = itemView.findViewById(R.id.timetxt);
            time3 = itemView.findViewById(R.id.time1);

        }
    }


}
