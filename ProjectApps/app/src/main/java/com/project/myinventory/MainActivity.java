package com.project.myinventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    public static ArrayList<Inventory> inventoryList = new ArrayList<>();
    Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.inventoryList);
        DBHelper.dbHelper = new DBHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        inventoryList = DBHelper.getAllRecords();
        if(inventoryList.size() == 0){
            rv.setVisibility(View.GONE);
        }else{
            rv.setVisibility(View.VISIBLE);
            layoutManager = new LinearLayoutManager(this);
            rv.setLayoutManager(layoutManager);
            mAdapter = new Adapter(this, inventoryList);

            rv.setAdapter(mAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:{
                Intent added = new Intent(this, AddInventory.class);
                startActivity(added);
            }
        }
        return true;
    }
}
