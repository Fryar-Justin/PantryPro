package edu.byui.pantrypro;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class addInventoryItem extends AppCompatActivity{
    EditText inputText;
    MyDBHandler dbHandler;
    Intent shareButtonIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory_item);
        dbHandler = new MyDBHandler(this, null, null, 1);
        inputText = (EditText) findViewById(R.id.itemInput);

        // set the top left button click event
        Button backButton = findViewById(R.id.button_TopLeft);
        backButton.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            }
        );
    }

    //Add a product to the database
    public void addItemClicked(View view){
        Item item = new Item(inputText.getText().toString());
        dbHandler.addItem(item);
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
