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
    private String qty;

    /*********************************************************************************************
     ***                                        Public                                         ***
     *********************************************************************************************/

    /*********************** Constructors ***********************/
    // default constructor
    public Ingredient() {
        // everything to default
        setName       ("Default");
        setQty        ("Default");
    }

    public Ingredient(Ingredient newIng) {
        setName(newIng.getName());
        setQty(newIng.getQty());
    }

    // non-default constructor
    public Ingredient(String name, String qty) {
        // set from parameters
        setName(name);
        setQty(qty);

        // everything else to default
    }

    /************************ Getters ************************/
    public String getName()        { return this.name;        }
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

    /*********************************** Member Functions ************************************/
    public String stringify() {
        String stringified = "";

        stringified = "{" + getName() + ":" + getQty() + "}";

        return stringified;
    }

    public String stringify(Ingredient ing) {
        String stringified = "";

        stringified = "{" + ing.getName() + ":" + ing.getQty() + "}";

        return stringified;
    }

    public Ingredient parse(String stringified) {
        String parsedName = "";
        String parsedQty = "";

        boolean fLeftCurly = false;
        boolean fRightCurly = false;
        boolean fColon = false;
        for (int i = 0; i < stringified.length(); i++) {
            if (stringified.charAt(i) == '{') {
                fLeftCurly = true;
            }
            else if (fLeftCurly && !fColon) {
                if (stringified.charAt(i) != ':') {
                    parsedName += stringified.charAt(i);
                } else {
                    fColon = true;
                }
            }
            else if (fColon && !fRightCurly) {
                if (stringified.charAt(i) == '}') {
                    fRightCurly = true;
                    break;
                }
                parsedQty += stringified.charAt(i);
            }
        }
        return new Ingredient(parsedName, parsedQty);
    }
}
