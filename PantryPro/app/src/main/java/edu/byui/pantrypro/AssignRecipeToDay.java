package edu.byui.pantrypro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class AssignRecipeToDay extends AppCompatActivity {

    DailyMealPlan dailyMealPlan;
    MyDBHandler dbHandler;
    ListView recipeList;
    String day = "";
    String time = "";
    Bundle extras;
    String weekPlanString;
    ArrayList<String> weekPlanArray;
    SharedPreferences prefs;
    public SharedPreferences.Editor preferenceEditor;

    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_MORNING = "morning";
    public static final String COLUMN_AFTERNOON = "afternoon";
    public static final String COLUMN_EVENING = "evening";

    public static final String MONDAY = "monday";
    public static final String TUESDAY = "tuesday";
    public static final String WEDNESDAY = "wednesday";
    public static final String THURSDAY = "thursday";
    public static final String FRIDAY = "friday";
    public static final String SATURDAY = "saturday";
    public static final String SUNDAY = "sunday";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_recipe_to_day);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        dailyMealPlan = new DailyMealPlan();
        dbHandler = new MyDBHandler(this, null, null, 1);
        recipeList = findViewById(R.id.listview_Recipes);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        preferenceEditor = prefs.edit();
        weekPlanString = prefs.getString("weekPlan", null);
        weekPlanArray = new ArrayList<>();
        extras = getIntent().getExtras();
        // setup stuff
        setListenersAndLabels();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateRecipes();
    }

    private void setListenersAndLabels() {
        // set the label
        TextView activityLabel = findViewById(R.id.textView_MenuLabel);
        String text = getIntent().getExtras().getString("day");
        text = text.substring(4, text.length());
        activityLabel.setText(text);

        int spaceLocation = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                spaceLocation = i;
                break;
            }
        }
        day = text.substring(0, spaceLocation);
        day = day.toLowerCase();
        time = text.substring(spaceLocation + 1, text.length());

        if (time.equals("Breakfast")) {
            time = COLUMN_MORNING;
        } else if (time.equals("Lunch")) {
            time = COLUMN_AFTERNOON;
        } else if (time.equals("Dinner")) {
            time = COLUMN_EVENING;
        }

        Toast.makeText(AssignRecipeToDay.this, time, Toast.LENGTH_SHORT).show();

        // set the currently assigned recipe

        TextView currentRecipe = findViewById(R.id.textView_CurrentRecipe);

        currentRecipe.setText(extras.getString("day"));

        // set the onclick event for list items
        ListView daysList = findViewById(R.id.listview_Recipes);
        daysList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String recipeName = String.valueOf(adapterView.getItemAtPosition(i));
                        weekPlanArray = deStringifyArray(weekPlanString);
                        weekPlanArray.set(extras.getInt("position"), "    " + recipeName);
                        preferenceEditor.putString("weekPlan", stringifyArray(weekPlanArray));
                        preferenceEditor.apply();
                        Intent mealPlan = new Intent(AssignRecipeToDay.this, MealPlanActivity.class);
                        startActivity(mealPlan);
                    }
                }
        );

        // populate the list
        populateRecipes();

        // make the top right button disappear
        Button addButton = findViewById(R.id.button_TopRight);
        addButton.setVisibility(View.INVISIBLE);

    }

    private void populateRecipes() {
        ArrayList<String> stringList = dbHandler.populateRecipeArrayList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        recipeList.setAdapter(arrayAdapter);
    }

    private void setRecipeToDay(String recipeName) {
        // set the currently assigned recipe
        TextView currentRecipe = findViewById(R.id.textView_CurrentRecipe);
        currentRecipe.setText(recipeName);

        dbHandler.addGroceryAssignment(day, time, recipeName);

        Toast.makeText(AssignRecipeToDay.this, "Boom! It's done!", Toast.LENGTH_SHORT).show();
    }


    public String stringifyArray(ArrayList<String> items){
        Gson gson = new Gson();
        String itemString = gson.toJson(items);
        return itemString;
    }

    public ArrayList<String> deStringifyArray(String outputarray) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();

        ArrayList<String> finalOutputString = gson.fromJson(outputarray, type);
        return finalOutputString;
    }
}
/*    private String getCurrentRecipe() {

////        // TODO: Needs to get the current recipe from the db that is assigned to this particular day

        String recipeName = extras.getString("day");


//        if (recipeName == null || recipeName.equals("")) {
//            return "Not yet assigned";
//        }
//        else {
//            return recipeName;
//        }
        return recipeName;
    }
}

//    private void populateMultiline() {
//        EditText viewer = findViewById(R.id.editText_Look);
//
//        Ingredient ing = new Ingredient("Cheerios", "1 Cups");
//        Ingredient ing2 = new Ingredient("Kix", "2 Cups");
//        Ingredient ing3 = new Ingredient("Snickers", "3 bars");
//        Ingredient ing4 = new Ingredient("WhatChaMaCallit", "4 bars");
//        Ingredient ing5 = new Ingredient("Heath", "5 bars");
//
//        Recipe rec = new Recipe();
//        rec.getIngredients().add(ing);
//        rec.getIngredients().add(ing2);
//        rec.getIngredients().add(ing3);
//        rec.getIngredients().add(ing4);
//        rec.getIngredients().add(ing5);
//
//        ArrayList<Ingredient> al = rec.parseIngredients(rec.stringifyIngredients());
//
//        String outThis = rec.stringifyIngredients() + "\n\n";
//        for (int i = 0; i < al.size(); i++) {
//            String name = rec.getIngredients().get(i).getName();
//            String qty = rec.getIngredients().get(i).getQty();
//
//            outThis += "{" + name + ":" + qty + "}\n";
//        }
//        viewer.setText(outThis);
//    }
*/