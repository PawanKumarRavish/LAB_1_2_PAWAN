package com.example.lab_1_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private Button btnSearch;
    private EditText search;
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

        search = findViewById(R.id.search);
        btnSearch = findViewById(R.id.btnsearch);
        db = new DBHandler(ProductList.this);

        Cursor res = db.getAllData();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor response = db.searchProduct(search.getText().toString());

                response.moveToFirst();
                for (int i = 1; i<=res.getCount(); i++) {
                    HashMap<String, String> mapBlog = new HashMap<String, String>();
                    mapBlog.put("Id", res.getString(0));
                    mapBlog.put("Name", res.getString(1));
                    mapBlog.put("Price", res.getString(2));
                    mapBlog.put("Description", res.getString(3));
                    mapBlog.put("Location", res.getString(4));
                    res.moveToNext();
                    productList.add(mapBlog);
                }

                mAdapter = new ProductAdapter(productList);
                recyclerView.setAdapter(mAdapter);
            }
        });

        res.moveToFirst();
        for (int i = 1; i<=res.getCount(); i++) {
            HashMap<String, String> mapBlog = new HashMap<String, String>();
            mapBlog.put("Id", res.getString(0));
            mapBlog.put("Name", res.getString(1));
            mapBlog.put("Price", res.getString(2));
            mapBlog.put("Description", res.getString(3));
            mapBlog.put("Location", res.getString(4));
            res.moveToNext();
            productList.add(mapBlog);
        }

        mAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(mAdapter);
    }
}