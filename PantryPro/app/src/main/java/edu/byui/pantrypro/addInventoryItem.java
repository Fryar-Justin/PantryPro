package edu.byui.pantrypro;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addInventoryItem extends AppCompatActivity{
    EditText inputText;
    EditText inputTextQuantity;
    MyDBHandler dbHandler;
    Intent shareButtonIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory_item);
        dbHandler = new MyDBHandler(this, null, null, 1);
        inputText = (EditText) findViewById(R.id.nameInput);
        inputTextQuantity = (EditText) findViewById(R.id.quantityInput);
    }

    //Add a product to the database
    public void addItemClicked(View view){
        Ingredient ingredient = new Ingredient(inputText.getText().toString(),inputTextQuantity.getText().toString());
        dbHandler.addIngredient(ingredient);
        shareButtonIntent = new Intent(addInventoryItem.this, InventoryActivity.class);
        startActivity(shareButtonIntent);
    }
/*
    //Delete items pass in view as parameter else clicking wont affect it
    public void deleteButtonClicked(View view){
        String input = inputText.getText().toString();
        dbHandler.deleteProduct(input);
        printDatabase();
    }
*/
}
