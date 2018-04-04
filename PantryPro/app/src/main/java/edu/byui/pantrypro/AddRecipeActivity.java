package edu.byui.pantrypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddRecipeActivity extends AppCompatActivity {

    Recipe newRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        // set the buttons and labels
        setRecipiesAndLabels();

        newRecipe = new Recipe();
    }

    // save the entire recipe to the database
    private void saveToDatabase() {
        // TODO: Fill in
    }

    // add the ingredient to the recipe object
    private void addIngredient() {
        // find the input boxes
        EditText ingredientName = findViewById(R.id.input_Ingredient);
        EditText ingredientMeasurement = findViewById(R.id.input_Measurement);

        // get the values
        String name = ingredientName.getText().toString();
        String measurement = ingredientMeasurement.getText().toString();

        // assign it to the recipe
        newRecipe.addIngredient(new Ingredient(name, measurement));

        // update the listview for the ingredients
        updateListView();
    }

    // delete the ingredient from the recipe object
    private void deleteIngredient() {
        // TODO: Fill in
    }

    // updates the specified listview with the values
    private void updateListView() {
        // TODO: Fill in
    }

    private void setRecipiesAndLabels() {
        // set the label
        TextView activityLabel = findViewById(R.id.textView_MenuLabel);
        activityLabel.setText(R.string.recipes);

        // set the onclick event for list items
        ListView shoppingList = findViewById(R.id.listView_Ingredients);
        shoppingList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String food = String.valueOf(adapterView.getItemAtPosition(i));
                        Toast.makeText(AddRecipeActivity.this, food, Toast.LENGTH_SHORT).show();
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

        // disable the top right button
        Button addButton = findViewById(R.id.button_TopRight);
        addButton.setText("");
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // do nothing
                    }
                }
        );
    }
}
