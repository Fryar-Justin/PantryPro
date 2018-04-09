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

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class AddGroceryItem extends AppCompatActivity {

    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery_item);

        // set the labels and everything
        setRecipiesAndLabels();

        // initialize dbHandler
        dbHandler = new MyDBHandler(this, null, null, 1);
    }

    public void addGroceryItem(View view) {
        EditText nameInput = findViewById(R.id.nameInput);
        EditText qtyInput = findViewById(R.id.quantityInput);

        String name = nameInput.getText().toString();
        String qty = qtyInput.getText().toString();

        if (name.equals("") || qty.equals("")) {
            Toast.makeText(AddGroceryItem.this, "Enter both Name & Qty!", LENGTH_SHORT).show();
        }
        else {
            if (dbHandler.checkIfExists(name)) {
                Toast.makeText(AddGroceryItem.this, "Item is already in inventory!", LENGTH_SHORT).show();
            }
            else {
                dbHandler.addGrocery(new Ingredient(name, qty));
                finish();
            }
        }
    }

    private void setRecipiesAndLabels() {
        // set the label
        TextView activityLabel = findViewById(R.id.textView_MenuLabel);
        activityLabel.setText("Add Grocery Item");

        // hide the top right button
        Button addButton = findViewById(R.id.button_TopRight);
        addButton.setVisibility(View.INVISIBLE);
    }
}
