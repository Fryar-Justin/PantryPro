package edu.byui.pantrypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    TextView outputname;
    TextView outputdirection;
    TextView outputnote;
    ListView outputingredients;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        dbHandler = new MyDBHandler(this, null, null, 1);

        Button topRight = findViewById(R.id.button_TopRight);
        topRight.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("NAME");
        Recipe recipe = dbHandler.findRecipe(name);

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

        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        for(int i = 0; i < ingredients.size(); i++){
            if(!dbHandler.checkIfExists(ingredients.get(i).getName())){
                dbHandler.addGrocery(ingredients.get(i));
            }
        }

        Intent grocery = new Intent(RecipeDetailsActivity.this, GroceryListActivity.class);
        startActivity(grocery);
    }
}
