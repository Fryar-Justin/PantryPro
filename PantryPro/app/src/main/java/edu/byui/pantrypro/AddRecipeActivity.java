package edu.byui.pantrypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity {

    MyDBHandler dbHandler;
    Recipe newRecipe;
    ArrayList<Ingredient> myIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        newRecipe = new Recipe();
        dbHandler = new MyDBHandler(this, null, null, 1);

        // initialize
        myIngredients = newRecipe.getIngredients();

        // set the buttons and labels
        setRecipiesAndLabels();
    }

    // save the entire recipe to the database
    public void saveToDatabase(View view) {
        // new implementation
        EditText recipeName = findViewById(R.id.input_Name);
        EditText recipeDirections = findViewById(R.id.input_Directions);
        EditText recipeNotes = findViewById(R.id.input_Notes);


        if (!(recipeName.getText().toString().equals(""))) {
            newRecipe.setName(recipeName.getText().toString());
            newRecipe.setDirections(recipeDirections.getText().toString());
            newRecipe.setNotes(recipeNotes.getText().toString());

            dbHandler.addRecipe(newRecipe);

            finish();
        }
        else {
            Toast.makeText(this, "You must enter a name for your recipe!", Toast.LENGTH_SHORT).show();
        }
    }

    // add the ingredient to the recipe object
    public void addIngredient(View view) {
        // find the input boxes
        EditText ingredientName = findViewById(R.id.input_Ingredient);
        EditText ingredientQuantity = findViewById(R.id.input_Quantity);

        // get the values
        String name = ingredientName.getText().toString();
        String quantity = ingredientQuantity.getText().toString();

        if (!(name.equals(""))) {
            // assign it to the recipe
            newRecipe.addIngredient(new Ingredient(name, quantity));

            // update the listview for the ingredients
            updateIngredientListView();

            // remove the contents of those fields
            ingredientName.setText("");
            ingredientQuantity.setText("");
        }
        else {
            Toast.makeText(this, "You must enter a name for your ingredient!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Don't need the delete function anymore because All you have to do is select the ingredient and it is removed
     */
//    // delete the ingredient from the recipe object
//    public void deleteIngredient(View view) {
//        // find the input boxes
//        EditText ingredientName = findViewById(R.id.input_Name);
//
//        // get the values
//        String name = ingredientName.getText().toString();
//
//        // assign it to the recipe
//        for (int i = 0; i < myIngredients.size(); i++) {
//            if (name.equals(myIngredients.get(i).getName())) {
//                myIngredients.remove(i);
//            }
//        }
//
//        // update the ingredient list view
//        updateIngredientListView();
//    }

    // updates the specified listview with the values
    private void updateIngredientListView() {
        // get the list view that holds the ingredients list
        ListView ingredientList = findViewById(R.id.listView_Ingredients);

        // copy over all the names to a string array in preparation to be put on the list
        ArrayList<String> ingredientNames = new ArrayList<String>();
        for (int i = 0; i < newRecipe.getIngredients().size(); i++) {
            ingredientNames.add(newRecipe.getIngredients().get(i).getName());
        }

        // update the list view with the ingredients
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredientNames);
        ingredientList.setAdapter(arrayAdapter);
    }

    // adds functionality to the buttons programmatically
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
//                        String food = String.valueOf(adapterView.getItemAtPosition(i));
                        myIngredients.remove(i);
                        updateIngredientListView();
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
