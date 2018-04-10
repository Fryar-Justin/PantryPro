package edu.byui.pantrypro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

public class MealPlanActivity extends AppCompatActivity{
    ListView inputDate;
    MyDBHandler dbHandler;
    ArrayList<String> week;
    SharedPreferences prefs;
    public SharedPreferences.Editor preferenceEditor;
    private static final int PREFERENCE_MODE_PRIVATE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        preferenceEditor = prefs.edit();
        inputDate = (ListView) findViewById(R.id.datetext);

        // set the listeners and labels


        dbHandler = new MyDBHandler(this, null, null, 1);

        String test = prefs.getString("weekPlan", null);
        if (test == null) {
            test = "uninitialized";
        }
        if(test == "uninitialized"){
            week = populateDays();
            preferenceEditor.putString("weekPlan", stringifyArray(week));
            preferenceEditor.apply();
        }
        else{
           week = deStringifyArray(test);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, week );
        inputDate.setAdapter(arrayAdapter);

        setRecipiesAndLabels();
    }

    public ArrayList<String> populateDays(){
        ArrayList<String> stringlist = new ArrayList<>();
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for(int i = 0; i < 7; i++){
            stringlist.add(days[i]);
            stringlist.add("    " + days[i] + " Breakfast");
            stringlist.add("    " + days[i] + " Lunch");
            stringlist.add("    " + days[i] + " Dinner");
        }

        return stringlist;
    }

    private boolean isSelectionValid(String selection) {
        return !selection.equals("Monday")    &&
               !selection.equals("Tuesday")   &&
               !selection.equals("Wednesday") &&
               !selection.equals("Thursday")  &&
               !selection.equals("Friday")    &&
               !selection.equals("Saturday")  &&
               !selection.equals("Sunday");
    }

    private boolean isSelectionNotDefault(String selection) {
        return  isSelectionValid(selection)    &&
                !selection.equals("    Monday Breakfast")    &&
                !selection.equals("    Monday Lunch")    &&
                !selection.equals("    Monday Dinner")    &&
                !selection.equals("    Tuesday Breakfast")   &&
                !selection.equals("    Tuesday Lunch")   &&
                !selection.equals("    Tuesday Dinner")   &&
                !selection.equals("    Wednesday Breakfast") &&
                !selection.equals("    Wednesday Lunch") &&
                !selection.equals("    Wednesday Dinner") &&
                !selection.equals("    Thursday Breakfast")  &&
                !selection.equals("    Thursday Lunch")  &&
                !selection.equals("    Thursday Dinner")  &&
                !selection.equals("    Friday Breakfast")    &&
                !selection.equals("    Friday Lunch")    &&
                !selection.equals("    Friday Dinner")    &&
                !selection.equals("    Saturday Breakfast")  &&
                !selection.equals("    Saturday Lunch")  &&
                !selection.equals("    Saturday Dinner")  &&
                !selection.equals("    Sunday Breakfast")    &&
                !selection.equals("    Sunday Lunch")    &&
                !selection.equals("    Sunday Dinner");
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

    private void setRecipiesAndLabels() {
        // set the label
        TextView activityLabel = findViewById(R.id.textView_MenuLabel);
        activityLabel.setText(R.string.meal_Plan);

        // set the onclick event for list items
        ListView shoppingList = findViewById(R.id.datetext);
        shoppingList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String selection = String.valueOf(adapterView.getItemAtPosition(i));

                        if (isSelectionValid(selection)) {
                            Intent assignToDay = new Intent(MealPlanActivity.this, AssignRecipeToDay.class);
                            assignToDay.putExtra("day", selection);
                            assignToDay.putExtra("position", i);
                            assignToDay.putExtra("weekString", stringifyArray(week));
                            startActivity(assignToDay);
                        }
                        else {
                            Toast.makeText(MealPlanActivity.this, "Don't pick those!", LENGTH_SHORT).show();

                        }
                    }
                }
        );

        // set the top right button click event
        Button addButton = findViewById(R.id.button_TopRight);
        // we don't want to see the button here so make it invisible
        addButton.setVisibility(View.VISIBLE);
        addButton.setText("ADD TO GROCERY");
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        for(int i = 0; i < week.size(); i++){
                            if(isSelectionNotDefault(week.get(i))){
                                addToGrocery(week.get(i));
                            }
                        }
                        Intent grocery = new Intent(MealPlanActivity.this, GroceryListActivity.class);
                        startActivity(grocery);
                    }
                }
        );

        // set the top left button click event
        Button backButton = findViewById(R.id.button_TopLeft);
        backButton.setVisibility(View.VISIBLE);
        backButton.setText("CLEAR LIST");
        backButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        week = populateDays();
                        preferenceEditor.putString("weekPlan", stringifyArray(week));
                        preferenceEditor.apply();

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MealPlanActivity.this,android.R.layout.simple_list_item_1, week );
                        inputDate.setAdapter(arrayAdapter);
                    }
                }
        );

        // set the Lists navigation at the bottom
        Button listsButton = findViewById(R.id.button_Inventory);
        listsButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent inventoryActivity = new Intent(MealPlanActivity.this, InventoryActivity.class);
                        startActivity(inventoryActivity);
                    }
                }
        );

        // set the Recipes navigation at the bottom
        Button recipesButton = findViewById(R.id.button_Recipes);
        recipesButton.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent recipeInventory = new Intent(MealPlanActivity.this, RecipeInventoryActivity.class);
                        startActivity(recipeInventory);
                    }
                }
        );

        // set the Meal Plan navigation at the bottom
        Button mealPlan = findViewById(R.id.button_MealPlan);
        mealPlan.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // do nothing because we are already on this screen
                        Toast.makeText(MealPlanActivity.this, "You are already here!", LENGTH_SHORT).show();
                    }
                }
        );

        // set the Grocery's navigation at the bottom
        Button groceryList = findViewById(R.id.button_Grocery);
        groceryList.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent groceryInventory = new Intent(MealPlanActivity.this, GroceryListActivity.class);
                        startActivity(groceryInventory);
                    }
                }
        );

    }

    public void addToGrocery(String name){
        Recipe recipe = dbHandler.findRecipe(name.substring(4));

        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        for(int i = 0; i < ingredients.size(); i++){
            if(!dbHandler.checkIfExists(ingredients.get(i).getName())){
                dbHandler.addGrocery(ingredients.get(i));
            }
        }
    }
}
