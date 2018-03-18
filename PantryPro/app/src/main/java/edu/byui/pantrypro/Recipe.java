package edu.byui.pantrypro;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/*************************************************************************************************
 * Class: Recipe
 *
 * Created by Justin on 3/18/18.
 * Description: Holds a collection of ingredients required for the meal
 *
 * Variable Description:
 *      name:           Holds the name of the recipe
 *      directions:     Holds details about how to cook the meal
 *      notes:          Holds notes about the recipe
 *      ingredients:    Holds the ingredients needed to cook the recipe
 *************************************************************************************************/

public class Recipe extends AppCompatActivity {
    /*********************************************************************************************
     ***                                        Private                                        ***
     *********************************************************************************************/

    /*************** Data ***************/
    private String name;
    private String directions;
    private String notes;
    private ArrayList<Ingredient> ingredients;

    /*************************** Default Values **************************/
    private final        String TAG                  = "Recipe Class:";
    private static final String DEFAULT_VALUE_STRING = "Default";
    private static final int    DEFAULT_VALUE_INT    = 0;

    /*********************************************************************************************
     ***                                        Public                                         ***
     *********************************************************************************************/

    /********************************* Constructors *********************************/
    // default constructor
    public Recipe() {
        setIngredients(new ArrayList<Ingredient>());
        setName       (DEFAULT_VALUE_STRING       );
        setDirections (DEFAULT_VALUE_STRING       );
        setNotes      (DEFAULT_VALUE_STRING       );
    }
    // non-default constructor
    public Recipe(String name,
                  String directions,
                  String notes,
                  ArrayList<Ingredient> ingredients)
    {
        setIngredients(ingredients);
        setName       (name);
        setDirections (directions);
        setNotes      (notes);
    }

    // non-default constructor
    public Recipe(String name, String directions, ArrayList<Ingredient> ingredients) {
        setIngredients(ingredients);
        setName       (name       );
        setDirections (directions );
    }

    /******************************* Getters ********************************/
    public String getName()                       { return this.name;        }
    public String getDirections()                 { return this.directions;  }
    public String getNotes()                      { return this.notes;       }
    public ArrayList<Ingredient> getIngredients() { return this.ingredients; }

    /********************************* Setters **********************************/
    public void setDirections(String directions) { this.directions = directions; }
    public void setNotes(String notes)           { this.notes = notes;           }

    public void setName(String name) {
        // check if the string is empty
        if (name == "") {
            // inform the user of their error
            Toast.makeText(this.getApplicationContext(),
                    "The name of your recipe cannot be empty!",
                    Toast.LENGTH_SHORT).show();
            // log the error
            Log.e(TAG, "User attempted to leave the name of the recipe blank");
        }
        else {
            this.name = name;
        }
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /******************************** Member Functions ****************************/

    // addIngredient(Ingredient)    - Adds to list of ingredients
    // removeIngredient(Ingredient) - Removes from list of ingredients
    // clearIngredients()           - Removes all ingredients

    /*********************************************************************
     * addIngredient
     *
     * Description: Adds an additional ingredient to the recipe list. It
     * doesn't allow duplicate entries and will inform the user if that
     * occurs.
     *********************************************************************/
    public void addIngredient(Ingredient newIngredient) {
        // first check if we have this item already on the list
        boolean haveit = false;
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName() == newIngredient.getName()) {
                haveit = true;
                break;
            }
        }

        if (!haveit) {
            // if we didn't find a duplicate then add it
            ingredients.add(newIngredient);
        }
        else {
            // inform the user of their error
            Toast.makeText(this.getApplicationContext(),
                    "You already have this item on your list!",
                    Toast.LENGTH_SHORT).show();
            // log the error
            Log.e(TAG, "User attempted to enter the duplicate ingredient "
                    + newIngredient.getName());
        }

    }

    /*********************************************************************
     * removeIngredient
     *
     * Description: Removes the ingredient from the recipe
     *********************************************************************/
    public void removeIngredient(Ingredient item) {
        boolean haveit = false;
        // check our list to see if it is in the list
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName() == item.getName()) {
                // remove from the list
                ingredients.remove(i);
                // inform the user
                Toast.makeText(this.getApplicationContext(),
                        "Added " + item.getName(),
                        Toast.LENGTH_SHORT).show();

                // log the removal
                Log.i(TAG, "User removed the ingredient "
                        + ingredients.get(i).getName()
                        + " from the "
                        + this.name
                        + " recipe");
                haveit = true;
            }
        }

        if (!haveit) {
            // if we didn't find it in the list, tell the user
            Toast.makeText(this.getApplicationContext(),
                    item.getName() + " doesn't exist",
                    Toast.LENGTH_SHORT).show();

            // log the error
            Log.e(TAG, "User attempted to remove an ingredient that did not exist");
        }
    }

    /*********************************************************************
     * clearIngredients
     *
     * Description: Removes all ingredients from the recipe
     *********************************************************************/
    public void clearIngredients() {
        ingredients.clear();
    }
}