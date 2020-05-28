package com.project.myinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "inventory.db";
    public static SQLiteOpenHelper dbHelper;

    public static final String TABLE_NAME = "MyInventory";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_QUANTITY = "Qty";

    private Context context;

    public DBHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " VARCHAR, " + COLUMN_DESCRIPTION + " VARCHAR, " + COLUMN_QUANTITY + " VARCHAR); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public static long insert(Inventory inv, Context ctx) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, inv.getName());
        cv.put(COLUMN_DESCRIPTION, inv.getDesc());
        cv.put(COLUMN_QUANTITY, inv.getQty());

       return  db.insert(TABLE_NAME, null, cv);
    }

    public static long update(Inventory inv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, inv.getName());
        cv.put(COLUMN_DESCRIPTION, inv.getDesc());
        cv.put(COLUMN_QUANTITY, inv.getQty());

        return db.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{inv.getId()});
    }

    public static ArrayList<Inventory> getAllRecords() {
        ArrayList<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase  db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

       if(cursor.moveToFirst()){
           do{
               String id = cursor.getString(0);
               String namaBarang = cursor.getString(1);
               String descBarang = cursor.getString(2);
               String qtyBarang = cursor.getString(3);

               Inventory inventory = new Inventory(id, namaBarang, descBarang, qtyBarang);

               inventoryList.add(inventory);
           }while(cursor.moveToNext());
       }
       return inventoryList;
    }
}
