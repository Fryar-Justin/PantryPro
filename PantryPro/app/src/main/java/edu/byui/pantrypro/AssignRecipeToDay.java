package edu.byui.pantrypro;

import android.content.Intent;
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

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class AssignRecipeToDay extends AppCompatActivity {

    DailyMealPlan dailyMealPlan;
    MyDBHandler dbHandler;
    ListView recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_recipe_to_day);
        dailyMealPlan = new DailyMealPlan();
        dbHandler = new MyDBHandler(this, null, null, 1);
        recipeList = findViewById(R.id.listview_Recipes);

        // setup stuff
        setListenersAndLabels();

        // display the list
        printRecipes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateRecipes();
    }

    private void setListenersAndLabels() {
        // set the label
        TextView activityLabel = findViewById(R.id.textView_MenuLabel);
        String day = getIntent().getExtras().getString("day");
        activityLabel.setText(day);

        // set the currently assigned recipe
        TextView currentRecipe = findViewById(R.id.textView_CurrentRecipe);
        currentRecipe.setText(getCurrentRecipe(day));

        // set the onclick event for list items
        ListView daysList = findViewById(R.id.listview_Recipes);
        daysList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String recipeName = String.valueOf(adapterView.getItemAtPosition(i));
                        setRecipeToDay(recipeName);
                        populateMultiline(); // TODO: remove
                    }
                }
        );

        // populate the list
        populateRecipes();

        // make the top right button disappear
        Button addButton = findViewById(R.id.button_TopRight);
        addButton.setVisibility(View.INVISIBLE);

    }

    private void populateMultiline() {
        EditText viewer = findViewById(R.id.editText_Look);

        Ingredient ing = new Ingredient("Cheerios", "1 Cups");
        Ingredient ing2 = new Ingredient("Kix", "2 Cups");
        Ingredient ing3 = new Ingredient("Snickers", "3 bars");
        Ingredient ing4 = new Ingredient("WhatChaMaCallit", "4 bars");
        Ingredient ing5 = new Ingredient("Heath", "5 bars");

        Recipe rec = new Recipe();
        rec.getIngredients().add(ing);
        rec.getIngredients().add(ing2);
        rec.getIngredients().add(ing3);
        rec.getIngredients().add(ing4);
        rec.getIngredients().add(ing5);

        ArrayList<Ingredient> al = rec.parseIngredients(rec.stringifyIngredients());

        String outThis = rec.stringifyIngredients() + "\n\n";
        for (int i = 0; i < al.size(); i++) {
            String name = rec.getIngredients().get(i).getName();
            String qty = rec.getIngredients().get(i).getQty();

            outThis += "{" + name + ":" + qty + "}\n";
        }
        viewer.setText(outThis);
    }

    private void populateRecipes() {
        // TODO: Needs to retrieve all the recipes from the db and display them on the listview
    }

    private void setRecipeToDay(String recipeName) {
        // TODO: Needs to change a field in the db so that we know what recipe is assigned to what day

        Toast.makeText(AssignRecipeToDay.this, "Boom! It's done!", Toast.LENGTH_SHORT).show();
        // set the currently assigned recipe
        TextView currentRecipe = findViewById(R.id.textView_CurrentRecipe);
        currentRecipe.setText(recipeName);
    }

    private String getCurrentRecipe(String day) {
        // TODO: Needs to get the current recipe from the db that is assigned to this particular day
        return "Not yet assigned";
    }

    public void printRecipes() {
        // TODO: This just currently prints the ingredients, we need to change it to print the recipes
        ArrayList<String> stringList = dbHandler.populateArrayList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, stringList );
        recipeList.setAdapter(arrayAdapter);
    }
}
