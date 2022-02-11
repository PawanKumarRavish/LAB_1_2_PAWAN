package com.example.lab_1_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductList extends AppCompatActivity {
    private ArrayList<HashMap<String, String>> productList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DBHandler db;
    private FloatingActionButton actionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        productList= new ArrayList<HashMap<String, String>>();
        actionButton = findViewById(R.id.floatingActionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        db = new DBHandler(ProductList.this);
        Cursor res = db.getAllData();
        res.moveToFirst();
        for (int i = 1; i<=res.getCount(); i++) {
            HashMap<String, String> mapBlog = new HashMap<String, String>();
            mapBlog.put("Name", res.getString(1));
            mapBlog.put("Price", res.getString(2));
            mapBlog.put("Location", res.getString(3));
            mapBlog.put("Description", res.getString(4));
            res.moveToNext();
            productList.add(mapBlog);
        }

        mAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(mAdapter);
    }
}