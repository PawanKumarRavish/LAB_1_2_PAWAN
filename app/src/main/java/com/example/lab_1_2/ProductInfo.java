package com.example.lab_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProductInfo extends AppCompatActivity {

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        Button update;
        Button delete;

        EditText eName;
        EditText eDescription;
        EditText ePrice;
        EditText eLat;
        EditText eLong;


        eName = findViewById(R.id.updateProductName);
        ePrice = findViewById(R.id.updateProductPrice);
        eDescription = findViewById(R.id.updateProductDesc);
        eLong = findViewById(R.id.updateLong);
        eLat = findViewById(R.id.updateLat);


        Bundle extras = getIntent().getExtras();
        eName.setText(extras.getString("productName"));
        ePrice.setText(extras.getString("productPrice"));
        eDescription.setText(extras.getString("productDescription"));
        eLat.setText(extras.getString("productLocation").split(",")[0]);
        eLong.setText(extras.getString("productLocation").split(",")[1]);

        update = findViewById(R.id.btnUpdateProduct);
        delete = findViewById(R.id.btnDeleteProduct);

        dbHandler = new DBHandler(ProductInfo.this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.updateProduct(extras.getString("productId").toString(), eName.getText().toString(), ePrice.getText().toString(), eDescription.getText().toString(), (eLat.getText().toString() + "," + eLong.getText().toString()));
                Toast.makeText(ProductInfo.this, "Product has been updated.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProductInfo.this, ProductList.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteProduct(extras.getString("productId").toString());
                Toast.makeText(ProductInfo.this, "Product has been deleted.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProductInfo.this, ProductList.class);
                startActivity(intent);
            }
        });
    }

}