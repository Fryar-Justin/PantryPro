package edu.byui.pantrypro;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper implements Serializable{

    private static MyDBHandler mydb;
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "items.db";
    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEMNAME = "itemname";

    public static final String TABLE_RECIPES = "recipe";
    public static final String COLUMN_RECIPENAME = "recipename";
    public static final String COLUMN_DIRECTIONS = "directions";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_INGREDIENTS = "ingredients";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_ITEMS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEMNAME + " TEXT NOT NULL" + ");";
        String query2 = "CREATE TABLE " + TABLE_RECIPES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECIPENAME + " TEXT NOT NULL, " + COLUMN_DIRECTIONS + " TEXT, " + COLUMN_NOTES + " TEXT, " + COLUMN_INGREDIENTS + " TEXT" + ");";
        //execute the query
        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    //product is the class that will be stored
    public void addItem(Item item){
        //sets different values for different columns and makes inserting easy

        ContentValues values = new ContentValues();
        //2 paramaters. first column. second value
        values.put(COLUMN_ITEMNAME, item.get_itemname());
        //database item
        SQLiteDatabase db = getWritableDatabase();
        //purely an insert statement rather than executing a query. 3 parameters
        //name of table, optional null?, list of values or contentvalues
        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    // adds a new recipe to the database
    // TODO: Hanson - I Need you to add the required functionality to this, I don't know how exactly to make this work but I think I'm close
    public void addRecipe(Recipe recipe) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_RECIPENAME, recipe.getName());
        values.put(COLUMN_DIRECTIONS, recipe.getDirections());
        values.put(COLUMN_NOTES, recipe.getNotes());
        values.put(COLUMN_INGREDIENTS, recipe.getIngredients().toString());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    // delete product from the database
    public void deleteItem(String itemName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEMNAME + "=\"" + itemName + "\";");
    }

    // turn data into a string to be displayed
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ITEMS + " WHERE 1";

        // cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        // move to the first row in your results
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_ITEMNAME)) != null) {
                dbString += c.getString(c.getColumnIndex(COLUMN_ITEMNAME));
                dbString += "\n";
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbString;
    }

    // finds an entry in the database, I think it works...
    public String findEntry(String table, String column, String search, String returnColumn) {
        String query = "SELECT * FROM " + table + " WHERE " + column + " = " + "\"" + search + "\"";
        SQLiteDatabase db = getWritableDatabase();

        try {
            // point cursor to a location in results
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();

            String dbString = c.getString(c.getColumnIndex(returnColumn));
            c.close();
            db.close();

            return dbString;
        } catch (Exception e) {
            Log.e("MyDBHandler", e.getMessage());
            return e.getMessage();
        }
    }

    /*
        //Delete from database
        public void deleteProduct(String productName){
            //database item
            SQLiteDatabase db = getWritableDatabase();
            //delete item where itemname is the same as what was entered
            db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
        }

        //Print database as a string
        public String databaseToString(){
            String dbString = "";
            //database item
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

            //Cursor point to a location in your results
            Cursor c = db.rawQuery(query, null);
            //Move to the first row in your results
            c.moveToFirst();

            while(!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("productname")) != null) {
                    dbString += c.getString(c.getColumnIndex("productname"));
                    dbString += "\n";
                }
                c.moveToNext();
            }

            db.close();
            return dbString;
        }
    */
    public ArrayList<String> populateArrayList(){
        ArrayList<String> stringList = new ArrayList<String>();


        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ITEMS + " WHERE 1";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("itemname")) != null) {
                stringList.add(c.getString(c.getColumnIndex("itemname")));
            }
            c.moveToNext();
        }

        c.close();
        return stringList;
    }
}





