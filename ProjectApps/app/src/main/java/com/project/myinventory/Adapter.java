package com.project.myinventory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {
    Context ctx;
    ArrayList<Inventory> inventory;

    public Adapter(Context ctx, ArrayList<Inventory> inventoryList) {
        this.ctx = ctx;
        this.inventory = inventoryList;
    }

    @NonNull
    @Override
    public Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.myViewHolder holder, final int position) {
        Inventory inventoryList = inventory.get(position);

        holder.nama.setText(inventoryList.getName());
        holder.deskripsi.setText(inventoryList.getDesc());
        holder.qty.setText(inventoryList.getQty());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update = new Intent(ctx, UpdateActivity.class);
                update.putExtra("name", position);
                ctx.startActivity(update);
            }
        });
    }


    @Override
    public int getItemCount() {
        return inventory.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView nama;
        TextView deskripsi;
        TextView qty;

        public myViewHolder(View itemView){
            super(itemView);
                nama = itemView.findViewById(R.id.namaInventory);
                deskripsi = itemView.findViewById(R.id.descInventory);
                qty = itemView.findViewById(R.id.qtyInventory);

            }
    }
}
