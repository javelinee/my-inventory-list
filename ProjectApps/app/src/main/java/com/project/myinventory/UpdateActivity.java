package com.project.myinventory;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    Button submit;
    EditText namaBarang;
    EditText descBarang;
    EditText qtyBarang;

    private int pos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pos = getIntent().getIntExtra("name", 0);
        Inventory inventory = MainActivity.inventoryList.get(pos);

        namaBarang = findViewById(R.id.inventName);
        descBarang = findViewById(R.id.inventDesc);
        qtyBarang = findViewById(R.id.inventQuantity);
        submit = findViewById(R.id.submit);

        namaBarang.setText(inventory.getName());
        descBarang.setText(inventory.getDesc());
        qtyBarang.setText(inventory.getQty());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(namaBarang.getText().toString().equals("") || descBarang.getText().toString().equals("") || qtyBarang.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Must be filled", Toast.LENGTH_SHORT).show();
                }else{
                    DBHelper.update(new Inventory(String.valueOf(pos+1),namaBarang.getText().toString(), descBarang.getText().toString(), qtyBarang.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
