package edu.byui.pantrypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    TextView outputname;
    TextView outputdirection;
    TextView outputnote;
    ListView outputingredients;
    Recipe recipe;
    String newRecipeName;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        dbHandler = new MyDBHandler(this, null, null, 1);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("NAME");
        recipe = dbHandler.findRecipe(name);
        newRecipeName = recipe.getName();

        // set up the page
        setListenersAndLabels();
    }

    @Override
    public void onResume() {
        super.onResume();
        recipe.setName(newRecipeName);
        recipe = dbHandler.findRecipe(recipe.getName());

        outputname.setText(recipe.getName());
        outputdirection.setText(recipe.getDirections());
        outputnote.setText(recipe.getNotes());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, getPrintableIngredients(recipe.getIngredients()));
        outputingredients.setAdapter(arrayAdapter);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                newRecipeName = data.getStringExtra("newRecipeName");
            }
        }
    }

    public ArrayList<String> getPrintableIngredients(ArrayList<Ingredient> ingredients){
        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0; i < ingredients.size(); i++){
            String temp = ingredients.get(i).getName() + " - " + ingredients.get(i).getQty();
            strings.add(temp);
        }
        return strings;
    }

    public void addToGrocery(View view){
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("NAME");
        Recipe recipe = dbHandler.findRecipe(name);
        String rejected = "";
        int numberAdded = 0;

        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        for(int i = 0; i < ingredients.size(); i++){
            if(!dbHandler.checkIfExists(ingredients.get(i).getName())){
                dbHandler.addGrocery(ingredients.get(i));
                numberAdded++;
            }
            else {
                rejected += ingredients.get(i).getName() + "\n";
            }
        }
        if (!rejected.equals("")) {
            rejected += "are already in your inventory and were not added to the grocery list";
            Toast.makeText(RecipeDetailsActivity.this, rejected, Toast.LENGTH_LONG).show();
        }

        if (numberAdded != 0) {
            Intent grocery = new Intent(RecipeDetailsActivity.this, GroceryListActivity.class);
            startActivity(grocery);
        }
        else {
            Toast.makeText(RecipeDetailsActivity.this, "All of the items you entered are already in your inventory", Toast.LENGTH_LONG).show();
        }
    }

    private void setListenersAndLabels() {

        // setup the top right button
        Button topRight = findViewById(R.id.button_TopRight);
        topRight.setText("DELETE");
        topRight.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView name = findViewById(R.id.name);
                        String nameText = name.getText().toString();
                        dbHandler.deleteRecipe(nameText);
                        finish();
                    }
                }
        );

        Button topLeft = findViewById(R.id.button_TopLeft);
        topLeft.setVisibility(View.VISIBLE);
        topLeft.setText("Modify");
        topLeft.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent modifyRecipeIntent = new Intent(RecipeDetailsActivity.this, EditRecipeActivity.class);
                        modifyRecipeIntent.putExtra("recipeName", outputname.getText().toString());
                        startActivityForResult(modifyRecipeIntent, 1);
                    }
                }
        );

        outputname = (TextView) findViewById(R.id.name);
        outputdirection= (TextView) findViewById(R.id.directions);
        outputnote= (TextView) findViewById(R.id.notes);
        outputingredients= (ListView) findViewById(R.id.listIngredients);

        outputname.setText(recipe.getName());
        outputdirection.setText(recipe.getDirections());
        outputnote.setText(recipe.getNotes());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, getPrintableIngredients(recipe.getIngredients()));
        outputingredients.setAdapter(arrayAdapter);
    }
}
