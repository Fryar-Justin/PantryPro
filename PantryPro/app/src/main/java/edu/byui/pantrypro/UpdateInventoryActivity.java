package edu.byui.pantrypro;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateInventoryActivity extends AppCompatActivity{
    EditText modifyName;
    EditText modifyQuantity;
    MyDBHandler dbHandler;
    Intent shareButtonIntent;
    Ingredient oldIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inventory);
        dbHandler = new MyDBHandler(this, null, null, 1);
        modifyName = (EditText) findViewById(R.id.modifyName);
        modifyQuantity = (EditText) findViewById(R.id.modifyQuantity);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("NAME");
        String quantity = extras.getString("QUANTITY");
        modifyName.setText(name, TextView.BufferType.EDITABLE);
        modifyQuantity.setText(quantity, TextView.BufferType.EDITABLE);
        oldIngredient = new Ingredient(name, quantity);
    }

    //Add a product to the database
    public void updateItem(View view){
        Ingredient newIngredient = new Ingredient(modifyName.getText().toString(), modifyQuantity.getText().toString());
        dbHandler.updateIngredient(newIngredient, oldIngredient);
        shareButtonIntent = new Intent(UpdateInventoryActivity.this, InventoryActivity.class);
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