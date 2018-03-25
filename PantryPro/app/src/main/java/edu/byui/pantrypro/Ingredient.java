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
 *      dept:        Holds the name of the department (backery, dairy, canned goods, etc.)
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
    private String dept;
    private String measurement;
    private double price;
    private int qty;

    /*************************** Default Values **************************/
    private final        String TAG                  = "Ingredient Class:";
    private static final String DEFAULT_VALUE_STRING = "Default";
    private static final int    DEFAULT_VALUE_INT    = 0;

    /*********************************************************************************************
     ***                                        Public                                         ***
     *********************************************************************************************/

    /*********************** Constructors ***********************/
    // default constructor
    public Ingredient() throws Exception {
        // everything to default
        setName       (DEFAULT_VALUE_STRING);
        setDept       (DEFAULT_VALUE_STRING);
        setMeasurement(DEFAULT_VALUE_STRING);
        setPrice      (DEFAULT_VALUE_INT);
        setQty        (DEFAULT_VALUE_INT);
    }
    // non-default constructor
    public Ingredient(String name,
                      String dept,
                      String measurement,
                      double price,
                      int qty) throws Exception
    {
        // set from parameters
        setName(name);
        setDept(dept);
        setPrice(price);
        setMeasurement(measurement);
        setQty(qty);
    }
    // non-default constructor
    public Ingredient(String name, int qty) throws Exception {
        // set from parameters
        setName(name);
        setQty(qty);
        setPrice(DEFAULT_VALUE_INT);

        // everything else to default
        setDept(DEFAULT_VALUE_STRING);
        setMeasurement(DEFAULT_VALUE_STRING);
    }

    /************************ Getters ************************/
    public String getName()        { return this.name;        }
    public String getDept()        { return this.dept;        }
    public String getMeasurement() { return this.measurement; }
    public double getPrice()       { return this.price;       }
    public int    getQty()         { return this.qty;         }

    /*********************************** Setters ************************************/
    public void setName(String name)               { this.name = name;               }
    public void setDept(String dept)               { this.dept = dept;               }
    public void setMeasurement(String measurement) { this.measurement = measurement; }

    public void setPrice(double price) throws Exception {
        // check if the number being assigned is valid
        if (price < 0) {
            throw new Exception("Price cannot be negative");
        } else {
            // valid, assign it
            this.price = price;
        }
    }

    public void setQty(int qty) throws Exception {
        // check if the number being assigned is valid
        if (qty < 0) {
            throw new Exception("Qty cannot be negative");
        }
        else {
            // valid, assign it
            this.qty = qty;
        }
    }
}
