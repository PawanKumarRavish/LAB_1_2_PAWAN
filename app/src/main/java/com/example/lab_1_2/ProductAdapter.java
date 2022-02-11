package com.example.lab_1_2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private ArrayList<HashMap<String, String>> productDataset;

    ProductAdapter(ArrayList<HashMap<String, String>> productList){
        productDataset=productList;
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        String Id = productDataset.get(position).get("Id");
        String Name = productDataset.get(position).get("Name");
        String Price = productDataset.get(position).get("Price");
        String Description = productDataset.get(position).get("Description");
        String Location = productDataset.get(position).get("Location");

        holder.name.setText(Name);
        holder.price.setText(Price);
        holder.description.setText(Description);
        holder.location.setText(Location);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                int clickedContentPosition = holder.getAdapterPosition();
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ProductInfo.class);
                intent.putExtra("productId",Id);
                intent.putExtra("productName",Name);
                intent.putExtra("productPrice",Price);
                intent.putExtra("productDescription",Description);
                intent.putExtra("productLocation",Location);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productDataset.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView description;
        TextView location;

        MyViewHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_product,parent,false));
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            description=itemView.findViewById(R.id.description);
            location=itemView.findViewById(R.id.location);

        }
    }
}
