package edu.byui.pantrypro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    ListView shoppingList;
    MyDBHandler dbHandler;
    Intent shareButtonIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        shoppingList = (ListView) findViewById(R.id.shoppingList);
        dbHandler = new MyDBHandler(this, null, null, 1);

        // set the listeners and labels for everything
        setListenersAndLabels();

        printDatabase();
    }

    public void addButtonClicked(View view){
        shareButtonIntent = new Intent(InventoryActivity.this, addInventoryItem.class);
        startActivity(shareButtonIntent);
    }

    public void printDatabase(){
        ArrayList<String> stringList = dbHandler.populateArrayList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, stringList );
        shoppingList.setAdapter(arrayAdapter);
    }

    public void mealClicked(View view){
        shareButtonIntent = new Intent(InventoryActivity.this, MealPlanActivity.class);
        startActivity(shareButtonIntent);
    }

    private void setListenersAndLabels() {
        // set the label
        TextView activityLabel = findViewById(R.id.textView_MenuLabel);
        activityLabel.setText(R.string.inventory_label);

        // set the onclick event for list items
        shoppingList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String ingredientName = String.valueOf(adapterView.getItemAtPosition(i));
                        Intent itemDetail = new Intent(InventoryActivity.this, IngredientDetailsActivity.class);
                        itemDetail.putExtra("NAME", ingredientName);
                        startActivity(itemDetail);
                        //String quantity = dbHandler.getItemQuantity(ingredientName);
                        //Toast.makeText(InventoryActivity.this, quantity, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // set the top right button click event
        Button addButton = findViewById(R.id.button_TopRight);
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent addInventory = new Intent(InventoryActivity.this, addInventoryItem.class);
                        startActivity(addInventory);
                    }
                }
        );

        // set the top left button click event
        Button backButton = findViewById(R.id.button_TopLeft);
        backButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        // set the Lists navigation at the bottom
        Button inventoryButton = findViewById(R.id.button_Inventory);
        inventoryButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // do nothing because we are already on this screen
                    }
                }
        );

        // set the Recipes navigation at the bottom
        Button recipesButton = findViewById(R.id.button_Recipes);
        recipesButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent recipeInventory = new Intent(InventoryActivity.this, RecipeInventoryActivity.class);
                        startActivity(recipeInventory);
                    }
                }
        );

        // set the Meal Plan navigation at the bottom
        Button mealPlan = findViewById(R.id.button_MealPlan);
        mealPlan.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent mealPlan = new Intent(InventoryActivity.this, MealPlanActivity.class);
                        startActivity(mealPlan);
                    }
                }
        );

        // set the Recipes navigation at the bottom
        Button groceryList = findViewById(R.id.button_Grocery);
        groceryList.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent groceryList = new Intent(InventoryActivity.this, GroceryListActivity.class);
                        startActivity(groceryList);
                    }
                }
        );
    }
}


