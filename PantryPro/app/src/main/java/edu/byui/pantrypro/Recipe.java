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
 *      ingredients:    Holds the ingredients needed to make the recipe
 *************************************************************************************************/

public class Recipe {
    /*********************************************************************************************
     ***                                        Private                                        ***
     *********************************************************************************************/

    /***************** Data *****************/
    private String name;
    private String directions;
    private String notes;
    private ArrayList<Ingredient> ingredients;

    /************************* Default Values ************************/
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
        setName       (name);
        setDirections (directions);
        setNotes      (notes);
        setIngredients(ingredients);
    }
    // non-default constructor
    public Recipe(String name, String directions, String notes) {
        setName       (name);
        setDirections (directions);
        setNotes      (notes);
        setIngredients(new ArrayList<Ingredient>());
    }
    // non-default constructor
    public Recipe(String name, String directions) {
        setName       (name       );
        setDirections (directions );
        setIngredients(new ArrayList<Ingredient>());
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
        if (name != "") {
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
    public void addIngredient(Ingredient newIngredient) throws Exception {
        // first check if we have this item already on the list
        boolean duplicate = false;
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName() == newIngredient.getName()) {
                duplicate = true;
                break;
            }
        }

        if (!duplicate) {
            // if we didn't find a duplicate then add it
            ingredients.add(newIngredient);
        }
        else {
            throw new Exception("Recipe already exists");
        }

    }

    /*********************************************************************
     * removeIngredient
     *
     * Description: Removes the ingredient from the recipe
     *********************************************************************/
    public void removeIngredient(Ingredient item) throws Exception {
        // check our list to see if it is in the list
        boolean removedItems = false;
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName() == item.getName()) {
                // remove from the list
                ingredients.remove(i);
                removedItems = true;
            }
        }

        if (!removedItems) {
            throw new Exception("Didn't find Ingredient");
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