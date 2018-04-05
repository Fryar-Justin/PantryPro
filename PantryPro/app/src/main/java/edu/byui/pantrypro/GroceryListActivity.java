package edu.byui.pantrypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

public class GroceryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        // set up the listeners and labels
        setRecipiesAndLabels();
    }

    private void setRecipiesAndLabels() {
        // set the label
        TextView activityLabel = findViewById(R.id.textView_MenuLabel);
        activityLabel.setText(R.string.groceryList);

        // set the onclick event for list items
        ListView shoppingList = findViewById(R.id.listView_GroceryList);
        shoppingList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String food = String.valueOf(adapterView.getItemAtPosition(i));
                        Toast.makeText(GroceryListActivity.this, food, LENGTH_SHORT).show();
                    }
                }
        );

        // set the top right button click event
        Button addButton = findViewById(R.id.button_TopRight);
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent addInventory = new Intent(GroceryListActivity.this, AddRecipeActivity.class);
                        startActivity(addInventory);
                    }
                }
        );

        // set the top left button click event
        Button backButton = findViewById(R.id.button_TopLeft);
        backButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        // set the Lists navigation at the bottom
        Button listsButton = findViewById(R.id.button_Inventory);
        listsButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent inventoryActivity = new Intent(GroceryListActivity.this, InventoryActivity.class);
                        startActivity(inventoryActivity);
                    }
                }
        );

        // set the Recipes navigation at the bottom
        Button recipesButton = findViewById(R.id.button_Recipes);
        recipesButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent recipeInventory = new Intent(GroceryListActivity.this, RecipeInventoryActivity.class);
                        startActivity(recipeInventory);
                    }
                }
        );

        // set the Meal Plan navigation at the bottom
        Button mealPlan = findViewById(R.id.button_MealPlan);
        mealPlan.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent recipeInventory = new Intent(GroceryListActivity.this, MealPlanActivity.class);
                        startActivity(recipeInventory);
                    }
                }
        );

        // set the Recipes navigation at the bottom
        Button groceryList = findViewById(R.id.button_Grocery);
        groceryList.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // do nothing because we are already on this screen
                        Toast.makeText(GroceryListActivity.this, "You are already here!", LENGTH_SHORT).show();
                    }
                }
        );
    }
}
