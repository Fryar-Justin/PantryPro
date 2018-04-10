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
    private static final int DATABASE_VERSION = 28;
    private static final String DATABASE_NAME = "items.db";

    public static final String TABLE_ITEMS     = "items";
    public static final String COLUMN_ID       = "_id";
    public static final String COLUMN_ITEMNAME = "itemname";
    public static final String COLUMN_QUANTITY = "quantity";

    public static final String TABLE_RECIPES      = "recipe";
    public static final String COLUMN_RECIPENAME  = "recipename";
    public static final String COLUMN_DIRECTIONS  = "directions";
    public static final String COLUMN_NOTES       = "notes";
    public static final String COLUMN_INGREDIENTS = "ingredients";

    public static final String TABLE_GROCERY          = "grocery";
    public static final String COLUMN_GROCERYNAME     = "groceryname";
    public static final String COLUMN_GROCERYQUANTITY = "groceryquantity";

    public static final String TABLE_MEALPLAN   = "mealplan";
    public static final String COLUMN_DAY       = "day";
    public static final String COLUMN_MORNING   = "morning";
    public static final String COLUMN_AFTERNOON = "afternoon";
    public static final String COLUMN_EVENING   = "evening";

    public static final String MONDAY    = "monday";
    public static final String TUESDAY   = "tuesday";
    public static final String WEDNESDAY = "wednesday";
    public static final String THURSDAY  = "thursday";
    public static final String FRIDAY    = "friday";
    public static final String SATURDAY  = "saturday";
    public static final String SUNDAY    = "sunday";
    public static boolean databasePopulated = false;

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_ITEMS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEMNAME + " TEXT NOT NULL," + COLUMN_QUANTITY + " TEXT" + ");";
        String query2 = "CREATE TABLE " + TABLE_RECIPES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECIPENAME + " TEXT NOT NULL, " + COLUMN_DIRECTIONS + " TEXT, " + COLUMN_NOTES + " TEXT, " + COLUMN_INGREDIENTS + " TEXT" + ");";
        String query3 = "CREATE TABLE " + TABLE_GROCERY + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_GROCERYNAME + " TEXT NOT NULL, " + COLUMN_GROCERYQUANTITY + " TEXT" + ");";
        String query4 = "CREATE TABLE " + TABLE_MEALPLAN + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DAY + " TEXT, " + COLUMN_MORNING + " TEXT, " + COLUMN_AFTERNOON + " TEXT, " + COLUMN_EVENING + " TEXT" + ");";
        //execute the query
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);

        seedMealPlan(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROCERY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEALPLAN);


        onCreate(db);
    }

    public void populateInventoryAndRecipes() {
        if (!databasePopulated) {
            int size = 9;
            String recNames[] = {"Cereal", "Chili", "Pancakes", "Toast", "Lollipops", "Trail Mix", "Bread", "Waffles", "French Toast"};
            String recDirections[] = {"Put the Cheerios in the bowl and add milk", "Add beef and beans to a crockpot, cook for 6 hours", "Cook the batter and add syrup",
                    "Make bread and put it in the toaster", "Add sugar to a stick and lick", "Combine cereal and lots of goodies", "Put flower in a bread machine and make",
                    "Use a waffle iron, clothes irons don't work", "French toast can be made in the U.S."
            };
            String recNotes[] = {"Don't burn the cereal!", "Chili can sometimes be hot!", "They are circular", "Don't make them black, just crispy",
                    "I like the red kind", "I like the honey roasted", "Good Hawaiian bread", "The checkers are my favorite", "They burn easily!"
            };
            //                      0           1       2          3        4         5        6          7          8
            String ingNames[] = {"Cheerios", "Milk", "Flower", "Yeast", "Butter", "Syrup", "Sugar", "Corn Syrup", "Beef"};
            String ingQty[] = {"1 Box", "1 Gallon", "5 lbs", "1 Tablespoon", "12 Ounces", "1 Bottle", "5 lbs", "5 Quarts", "2 lbs"};

            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            ArrayList<Recipe> recipes = new ArrayList<Recipe>();

            for (int i = 0; i < size; i++) {
                ingredients.add(new Ingredient(ingNames[i], ingQty[i]));
            }
            for (int i = 0; i < size; i++) {
                recipes.add(new Recipe(recNames[i], recDirections[i], recNotes[i]));
            }

            ArrayList<Ingredient> toAdd = new ArrayList<Ingredient>();

            // cereal
            toAdd.add(ingredients.get(0));
            toAdd.add(ingredients.get(1));
            recipes.get(0).setIngredients(toAdd);
            toAdd = new ArrayList<>();
            // chili
            toAdd.add(ingredients.get(4));
            toAdd.add(ingredients.get(6));
            toAdd.add(ingredients.get(8));
            recipes.get(1).setIngredients(toAdd);
            toAdd = new ArrayList<>();
            // pancakes
            toAdd.add(ingredients.get(0));
            toAdd.add(ingredients.get(1));
            recipes.get(2).setIngredients(toAdd);
            toAdd = new ArrayList<>();
            // toast
            toAdd.add(ingredients.get(2));
            toAdd.add(ingredients.get(5));
            toAdd.add(ingredients.get(6));
            recipes.get(3).setIngredients(toAdd);
            toAdd = new ArrayList<>();
            // lolli pops
            toAdd.add(ingredients.get(6));
            toAdd.add(ingredients.get(7));
            recipes.get(4).setIngredients(toAdd);
            toAdd = new ArrayList<>();
            // trail mix
            toAdd.add(ingredients.get(0));
            toAdd.add(ingredients.get(4));
            recipes.get(5).setIngredients(toAdd);
            toAdd = new ArrayList<>();
            // bread
            toAdd.add(ingredients.get(3));
            recipes.get(6).setIngredients(toAdd);
            toAdd = new ArrayList<>();
            // waffles
            toAdd.add(ingredients.get(1));
            toAdd.add(ingredients.get(2));
            toAdd.add(ingredients.get(4));
            toAdd.add(ingredients.get(5));
            recipes.get(7).setIngredients(toAdd);
            toAdd = new ArrayList<>();
            // french toast
            toAdd.add(ingredients.get(1));
            toAdd.add(ingredients.get(2));
            toAdd.add(ingredients.get(3));
            recipes.get(8).setIngredients(toAdd);
            toAdd = new ArrayList<>();

            for (int i = 0; i < recipes.size(); i++) {
                addRecipe(recipes.get(i));
            }
            for (int i = 0; i < ingredients.size(); i++) {
                addIngredient(ingredients.get(i));
            }

            Ingredient ing1 = new Ingredient("Noodles", "16 ounces");
            Ingredient ing2 = new Ingredient("Garlic", "1 container");
            Ingredient ing3 = new Ingredient("Cheese Sauce", "16 ounces");
            Ingredient ing4 = new Ingredient("Garlic Bread", "1 loaf");
            Ingredient ing5 = new Ingredient("Garlic Salt", "1 container");

            ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
            ings.add(ing1); ings.add(ing2); ings.add(ing3); ings.add(ing4); ings.add(ing5);

            Recipe rec = new Recipe("Spaghetti", "Boil the noodles until they are soft, cook bread", "Garlic bread burns quickly so watch it!", ings);
            addRecipe(rec);

            ing1 = new Ingredient("Beef", "2 lbs");
            ing2 = new Ingredient("Flower", "5 lbs");
            ing3 = new Ingredient("Milk", "1 Gallon");
            ing4 = new Ingredient("Hard Taco Shells", "24 count");
            ing5 = new Ingredient("Taco Seasoning", "1 package");

            ings = new ArrayList<Ingredient>();
            ings.add(ing1); ings.add(ing2); ings.add(ing3); ings.add(ing4); ings.add(ing5);

            rec = new Recipe("Tacos", "Brown the beef and mix with the taco seasoning", "The water will boil off over time and it will thicken", ings);
            addRecipe(rec);

            databasePopulated = true;
        }
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

    //product is the class that will be stored
    public void addIngredient(Ingredient ingredient){
        //sets different values for different columns and makes inserting easy

        ContentValues values = new ContentValues();
        //2 paramaters. first column. second value
        values.put(COLUMN_ITEMNAME, ingredient.getName());
        values.put(COLUMN_QUANTITY, ingredient.getQty());
        //database item
        SQLiteDatabase db = getWritableDatabase();
        //purely an insert statement rather than executing a query. 3 parameters
        //name of table, optional null?, list of values or contentvalues
        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public void addRecipe(Recipe recipe) {
        ContentValues values = new ContentValues();
        //2 paramaters. first column. second value
        values.put(COLUMN_RECIPENAME, recipe.getName());
        values.put(COLUMN_DIRECTIONS, recipe.getDirections());
        values.put(COLUMN_NOTES, recipe.getNotes());
        values.put(COLUMN_INGREDIENTS, recipe.stringifyIngredients());
        //database item
        SQLiteDatabase db = getWritableDatabase();
        //purely an insert statement rather than executing a query. 3 parameters
        //name of table, optional null?, list of values or contentvalues
        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    public void addGrocery(Ingredient ingredient) {
        //sets different values for different columns and makes inserting easy

        ContentValues values = new ContentValues();
        //2 paramaters. first column. second value
        values.put(COLUMN_GROCERYNAME, ingredient.getName());
        values.put(COLUMN_GROCERYQUANTITY, ingredient.getQty());

        //database item
        SQLiteDatabase db = getWritableDatabase();
        //purely an insert statement rather than executing a query. 3 parameters
        //name of table, optional null?, list of values or contentvalues
        db.insert(TABLE_GROCERY, null, values);
        db.close();
    }

    public void addGroceryAssignment(String day, String time, String recipeName) {
        ContentValues value = new ContentValues();

        SQLiteDatabase db = getWritableDatabase();

        String update = "UPDATE " + TABLE_MEALPLAN + " SET " + time + " = " + recipeName + " WHERE " + COLUMN_DAY + " = " + day;

        db.execSQL(update);
    }

    public void updateIngredient(Ingredient newIngredient, Ingredient oldIngredient){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_ITEMS + " SET " + COLUMN_ITEMNAME + " = \'" + newIngredient.getName() + "\', " + COLUMN_QUANTITY + " = \'" + newIngredient.getQty() + "\' WHERE " + COLUMN_ITEMNAME + "=\"" + oldIngredient.getName() + "\";");

    }

    // delete product from the database
    public void deleteItem(String itemName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEMNAME + "=\"" + itemName + "\";");
    }

    // delete an item from the grocery table
    public void deleteGroceryItem(String itemName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_GROCERY + " WHERE " + COLUMN_GROCERYNAME + "=\"" + itemName + "\";");
    }

    // delete a recipe from the recipe table
    public void deleteRecipe(String recipeName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RECIPES + " WHERE " + COLUMN_RECIPENAME + "=\"" + recipeName + "\";");
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
    public String findEntry(String search) {
        String query = "SELECT * FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEMNAME + " = " + "\"" + search + "\"";
        SQLiteDatabase db = getWritableDatabase();

        try {
            // point cursor to a location in results
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();

            String dbString = c.getString(c.getColumnIndex(COLUMN_QUANTITY));
            c.close();
            db.close();

            return dbString;
        } catch (Exception e) {
            Log.e("MyDBHandler", e.getMessage());
            return e.getMessage();
        }
    }

    // finds an entry in the database, I think it works...
    public Recipe findRecipe(String search) {
        String query = "SELECT * FROM " + TABLE_RECIPES + " WHERE " + COLUMN_RECIPENAME + " = " + "\"" + search + "\"";
        SQLiteDatabase db = getWritableDatabase();

        try {
            // point cursor to a location in results
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            Recipe tempRecipe = new Recipe();

            tempRecipe.setName(c.getString(c.getColumnIndex(COLUMN_RECIPENAME)));
            tempRecipe.setDirections(c.getString(c.getColumnIndex(COLUMN_DIRECTIONS)));
            tempRecipe.setNotes(c.getString(c.getColumnIndex(COLUMN_NOTES)));
            tempRecipe.setIngredients(tempRecipe.parseIngredients(c.getString(c.getColumnIndex(COLUMN_INGREDIENTS))));

            c.close();
            db.close();

            return tempRecipe;
        } catch (Exception e) {
            Log.e("MyDBHandler", e.getMessage());
            return new Recipe();
        }
    }



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

    public ArrayList<String> populateRecipeArrayList(){
        ArrayList<String> stringList = new ArrayList<String>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_RECIPES + " WHERE 1";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("recipename")) != null) {
                stringList.add(c.getString(c.getColumnIndex("recipename")));
            }
            c.moveToNext();
        }

        c.close();
        return stringList;
    }

    public ArrayList<Ingredient> populateGroceryArrayList(){
        ArrayList<Ingredient> groceryList = new ArrayList<Ingredient>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_GROCERY + " WHERE 1";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_GROCERYNAME)) != null) {
                String name = c.getString(c.getColumnIndex((COLUMN_GROCERYNAME)));
                String qty = c.getString(c.getColumnIndex((COLUMN_GROCERYQUANTITY)));

                groceryList.add(new Ingredient(name, qty));
            }
            c.moveToNext();
        }

        c.close();
        return groceryList;
    }

    public String getMealPlanTable() {
        String query = "SELECT * FROM " + TABLE_MEALPLAN + " WHERE 1";
        String dbString = "";

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_DAY)) != null) {
                dbString += c.getString(c.getColumnIndex(COLUMN_DAY));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public Ingredient getGroceryItemDetails(String name) {
        String itemName = "";
        String itemQty = "";

        String query = "SELECT * FROM " + TABLE_GROCERY + " WHERE " + COLUMN_GROCERYNAME + " = \"" + name +  "\"";
        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            itemName = c.getString(c.getColumnIndex(COLUMN_GROCERYNAME));
            itemQty = c.getString(c.getColumnIndex(COLUMN_GROCERYQUANTITY));

            c.close();
        } catch (Exception e) {
            Log.e("MyDBHandler:getGroceryItemDetails(String)", e.getMessage());
        }
        db.close();

        return new Ingredient(itemName, itemQty);
    }

    public String getAssignedRecipe(String day, String time) {
        String recipeName = "";
        String query = "SELECT * FROM " + TABLE_MEALPLAN + " WHERE " + COLUMN_DAY + " = \"" + time + "\"";

        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor c = db.rawQuery(query, null);

            c.moveToFirst();

            recipeName = c.getString(c.getColumnIndex(time));
            c.close();
        } catch (Exception e) {
            recipeName = "No recipe assigned";
        }

        db.close();

        return recipeName;
    }

    public boolean checkIfExists(String search){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEMNAME + " = " + "\"" + search + "\"";
        Cursor c = db.rawQuery(query, null);
        if(c.getCount() <= 0){
            c.close();
            db.close();
            return false;
        }
        c.close();
        db.close();
        return true;
    }

    public void seedMealPlan(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DAY, MONDAY);
        values.put(COLUMN_MORNING, "Monday Breakfast");
        values.put(COLUMN_AFTERNOON, "Monday Lunch");
        values.put(COLUMN_EVENING, "Monday Dinner");
        db.insert(TABLE_MEALPLAN, null, values);

        values.put(COLUMN_DAY, TUESDAY);
        values.put(COLUMN_MORNING, "Tuesday Breakfast");
        values.put(COLUMN_AFTERNOON, "Tuesday Lunch");
        values.put(COLUMN_EVENING, "Tuesday Dinner");
        db.insert(TABLE_MEALPLAN, null, values);

        values.put(COLUMN_DAY, WEDNESDAY);
        values.put(COLUMN_MORNING, "Wednesday Breakfast");
        values.put(COLUMN_AFTERNOON, "Wednesday Lunch");
        values.put(COLUMN_EVENING, "Wednesday Dinner");
        db.insert(TABLE_MEALPLAN, null, values);

        values.put(COLUMN_DAY, THURSDAY);
        values.put(COLUMN_MORNING, "Thursday Breakfast");
        values.put(COLUMN_AFTERNOON, "Thursday Lunch");
        values.put(COLUMN_EVENING, "Thursday Dinner");
        db.insert(TABLE_MEALPLAN, null, values);

        values.put(COLUMN_DAY, FRIDAY);
        values.put(COLUMN_MORNING, "Friday Breakfast");
        values.put(COLUMN_AFTERNOON, "Friday Lunch");
        values.put(COLUMN_EVENING, "Friday Dinner");
        db.insert(TABLE_MEALPLAN, null, values);

        values.put(COLUMN_DAY, SATURDAY);
        values.put(COLUMN_MORNING, "Saturday Breakfast");
        values.put(COLUMN_AFTERNOON, "Saturday Lunch");
        values.put(COLUMN_EVENING, "Saturday Dinner");
        db.insert(TABLE_MEALPLAN, null, values);

        values.put(COLUMN_DAY, SUNDAY);
        values.put(COLUMN_MORNING, "Sunday Breakfast");
        values.put(COLUMN_AFTERNOON, "Sunday Lunch");
        values.put(COLUMN_EVENING, "Sunday Dinner");
        db.insert(TABLE_MEALPLAN, null, values);

    }
}

/*
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
