package edu.byui.pantrypro;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class UpdateInventoryActivity extends AppCompatActivity{
    EditText inputText;
    MyDBHandler dbHandler;
    Intent shareButtonIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inventory);
        dbHandler = new MyDBHandler(this, null, null, 1);
        inputText = (EditText) findViewById(R.id.nameInput);
    }

    //Add a product to the database
    public void addItemClicked(View view){
        Item item = new Item(inputText.getText().toString());
        dbHandler.addItem(item);
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