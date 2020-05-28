package com.project.myinventory;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddInventory extends AppCompatActivity {
    EditText nama;
    EditText desc;
    EditText qty;
    Button submit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama = findViewById(R.id.invName);
        desc = findViewById(R.id.invDesc);
        qty = findViewById(R.id.invQuantity);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().equals("") || desc.getText().toString().equals("") || qty.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Must be filled", Toast.LENGTH_SHORT).show();
                }
                else {
                    String namaBarang = nama.getText().toString();
                    String deskripsiBarang = desc.getText().toString();
                    String qtyBarang = qty.getText().toString();

                    DBHelper.insert(new Inventory(namaBarang, deskripsiBarang, qtyBarang), getApplicationContext());
                    Toast.makeText(getApplicationContext(), "Successfully Uploaded ", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_to_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
