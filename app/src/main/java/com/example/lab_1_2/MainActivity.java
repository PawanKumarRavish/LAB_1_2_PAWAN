package com.example.lab_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editProductName;
    private EditText editProductPrice;
    private EditText editProductDesc;
    private EditText editLat;
    private EditText editLong;
    private Button btnAddProduct;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editProductName = findViewById(R.id.editProductName);
        editProductPrice = findViewById(R.id.editProductPrice);
        editProductDesc = findViewById(R.id.editProductDesc);
        editLat = findViewById(R.id.editLat);
        editLong = findViewById(R.id.editLong);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        dbHandler = new DBHandler(MainActivity.this);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = editProductName.getText().toString();
                String productPrice = editProductPrice.getText().toString();
                String productDescription = editProductDesc.getText().toString();
                String productLocation = (editLat.getText() + "," + editLong.getText()).toString() ;
                dbHandler.addNewProduct(productName, productPrice, productDescription, productLocation);
                Toast.makeText(MainActivity.this, "Product has been added.", Toast.LENGTH_SHORT).show();
                editProductName.setText("");
                editProductDesc.setText("");
                editProductPrice.setText("");
                editLat.setText("");
                editLong.setText("");
            }
        });

    }
}