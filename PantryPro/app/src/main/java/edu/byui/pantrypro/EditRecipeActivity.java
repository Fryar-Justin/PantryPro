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
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class EditRecipeActivity extends AppCompatActivity {

    MyDBHandler dbHandler;
    Recipe newRecipe;
    EditText recipeName;
    EditText recipeDirections;
    EditText notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        dbHandler = new MyDBHandler(this, null, null, 1);
        recipeName = findViewById(R.id.input_Name);
        recipeDirections = findViewById(R.id.input_Directions);
        notes = findViewById(R.id.input_Notes);

        newRecipe = new Recipe();

        newRecipe = dbHandler.findRecipe(getIntent().getStringExtra("recipeName"));
        recipeName.setText(newRecipe.getName());
        recipeDirections.setText(newRecipe.getDirections());
        notes.setText(newRecipe.getNotes());

        updateIngredientListView(newRecipe);

        setListenersAndLabels();
    }

    // save the entire recipe to the database
    public void saveToDatabase(View view) {
        dbHandler.deleteRecipe(getIntent().getStringExtra("recipeName"));

        // new implementation
        EditText recipeName = findViewById(R.id.input_Name);
        EditText recipeDirections = findViewById(R.id.input_Directions);
        EditText recipeNotes = findViewById(R.id.input_Notes);

        if (!(recipeName.getText().toString().equals(""))) {
            newRecipe.setName(recipeName.getText().toString());
            newRecipe.setDirections(recipeDirections.getText().toString());
            newRecipe.setNotes(recipeNotes.getText().toString());

            dbHandler.addRecipe(newRecipe);

            Intent intent = new Intent();
            intent.putExtra("newRecipeName", newRecipe.getName());
            setResult(RESULT_OK, intent);
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
            updateIngredientListView(newRecipe);

            // remove the contents of those fields
            ingredientName.setText("");
            ingredientQuantity.setText("");
        }
        else {
            Toast.makeText(this, "You must enter a name for your ingredient!", Toast.LENGTH_SHORT).show();
        }
    }

    // updates the specified listview with the values
    private void updateIngredientListView(Recipe recipe) {
        // get the list view that holds the ingredients list
        ListView ingredientList = findViewById(R.id.listView_Ingredients);

        // copy over all the names to a string array in preparation to be put on the list
        ArrayList<String> ingredientNames = new ArrayList<String>();
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            ingredientNames.add(recipe.getIngredients().get(i).getName());
        }

        // update the list view with the ingredients
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredientNames);
        ingredientList.setAdapter(arrayAdapter);
    }

    private void setListenersAndLabels() {
        Button topRight = findViewById(R.id.button_TopRight);
        topRight.setVisibility(View.INVISIBLE);

        ListView ingredientsList = findViewById(R.id.listView_Ingredients);
        // set the onclick event for list items
        ingredientsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String ingredient = String.valueOf(adapterView.getItemAtPosition(i));

                        newRecipe.getIngredients().remove(i);

                        updateIngredientListView(newRecipe);
                    }
                }
        );
    }
}
