package com.example.theatreapp2;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class customerOrderAdapter extends RecyclerView.Adapter<customerOrderAdapter.MyViewHolder> {

    //This sends the information from the array list to the recycler view
    private List<orderInfo> orderInformation;

    private LayoutInflater inflater;

    Context context;

    public customerOrderAdapter(Context c, List<orderInfo> detailConst) {
        context = c;
        orderInformation = detailConst;
        inflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public customerOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.orders_row,parent,false);

        return new customerOrderAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull customerOrderAdapter.MyViewHolder holder, int position) {
        //Make variables then assign them

        String nam1 = orderInformation.get(position).getName();
        String seat1 = orderInformation.get(position).getSeat();
        String price1 = String.valueOf(orderInformation.get(position).getPriceTicket());
        String tickNum1 = String.valueOf(orderInformation.get(position).getTicketNum());
        String code1 = orderInformation.get(position).getUniqueCode();

        Log.i(TAG, "onBindViewHolder: " + orderInformation.get(position).getName());
        //apply them to the holder below which is called holder
        //Sets the text in eah card in the recycler view


        holder.nameTxt.setText(nam1);
        //holder.seatTxt.setText(seat1);

        //Two above work but not the ones below
        //holder.priceTxt.setText(price1);
        //holder.tickNumTxt.setText(tickNum1);
        holder.codeTxt.setText(code1);

        /////////////////////////////



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,orderInformationPage.class);
                intent.putExtra("name",nam1);
                intent.putExtra("seatType",seat1);
                intent.putExtra("Price",price1);
                intent.putExtra("ticketNumber",tickNum1);
                intent.putExtra("Code",code1);
                context.startActivity(intent);
            }
        });







    }

    @Override
    public int getItemCount() {
        return orderInformation.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt, seatTxt, priceTxt, tickNumTxt, codeTxt;
        //ImageView img2;

        public MyViewHolder(@NonNull View view) {
            super(view);

            nameTxt = itemView.findViewById(R.id.nameTxt);
            //seatTxt = itemView.findViewById(R.id.seatType);
            //priceTxt = itemView.findViewById(R.id.priceTicket);
            //img2 = itemView.findViewById(R.id.imageView);
            //tickNumTxt = itemView.findViewById(R.id.seatNum);
            codeTxt = itemView.findViewById(R.id.code);

        }

    }
}



