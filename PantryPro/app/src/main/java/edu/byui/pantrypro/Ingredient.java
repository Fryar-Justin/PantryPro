package edu.byui.pantrypro;

import android.support.v7.app.AppCompatActivity;

/*************************************************************************************************
 * Class: Ingredient
 *
 * Created by Justin on 3/18/18.
 * Description: Holds a single ingredient item which can be used in a collection of ingredients
 *
 * Variable Description:
 *      name:        Holds the name of the ingredient (banana, apple, peach, etc.)
 *      measurement: Holds the metric (cups, gallons, lbs, etc.)
 *      price:       Holds the price of the product ($5.00, $10.00, $15.00)
 *      qty:         Holds the number of items of that type (1, 2, 3, 4, etc.)
 *************************************************************************************************/

public class Ingredient extends AppCompatActivity {
    /*********************************************************************************************
     ***                                        Private                                        ***
     *********************************************************************************************/

    /********** Data *********/
    private String name;
<<<<<<< Updated upstream
    private String measurement;
=======
    private String qty;
>>>>>>> Stashed changes

    /*********************************************************************************************
     ***                                        Public                                         ***
     *********************************************************************************************/

    /*********************** Constructors ***********************/
    // default constructor
    public Ingredient() {
        // everything to default
        setName       ("Default");
<<<<<<< Updated upstream
        setMeasurement("Default");
    }
    // non-default constructor
    public Ingredient(String name,
                      String measurement)
    {
        // set from parameters
        setName(name);
        setMeasurement(measurement);
=======
        setQty        ("Default");
>>>>>>> Stashed changes
    }

    // non-default constructor
    public Ingredient(String name, String qty) {
        // set from parameters
        setName(name);

        // everything else to default
    }

    // copy constructor
    public Ingredient(Ingredient newIngredient) {
        this.name = newIngredient.getName();
        this.measurement = newIngredient.getMeasurement();
    }

    /************************ Getters ************************/
    public String getName()        { return this.name;        }
<<<<<<< Updated upstream
    public String getMeasurement() { return this.measurement; }

    /*********************************** Setters ************************************/
    public void setName(String name)               { this.name = name;               }
    public void setMeasurement(String measurement) { this.measurement = measurement; }
=======
    public String getQty()         { return this.qty;         }

    /*********************************** Setters ************************************/
    public void setName(String name)               { this.name = name;               }

    public void setQty(String qty) {
        // check if the number being assigned is valid
        if (qty == null) {
            this.qty = "";
        }
        else {
            // valid, assign it
            this.qty = qty;
        }
    }
>>>>>>> Stashed changes
}
