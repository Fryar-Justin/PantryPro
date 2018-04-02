package edu.byui.pantrypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    ListView shoppingList;
    MyDBHandler dbHandler;
    Intent shareButtonIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        shoppingList = (ListView) findViewById(R.id.shoppingList);
        dbHandler = new MyDBHandler(this, null, null, 1);


        shoppingList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String food = String.valueOf(adapterView.getItemAtPosition(i));
                        Toast.makeText(InventoryActivity.this, food, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        printDatabase();
    }

    public void addButtonClicked(View view){
        shareButtonIntent = new Intent(InventoryActivity.this, addInventoryItem.class);
        startActivity(shareButtonIntent);
    }

    public void printDatabase(){
        ArrayList<String> stringList = dbHandler.populateArrayList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, stringList );
        shoppingList.setAdapter(arrayAdapter);
    }

    public void mealClicked(View view){
        shareButtonIntent = new Intent(InventoryActivity.this, MealPlanActivity.class);
        startActivity(shareButtonIntent);
    }
}


