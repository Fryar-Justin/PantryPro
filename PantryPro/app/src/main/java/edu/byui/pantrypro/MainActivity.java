package edu.byui.pantrypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String items[] = new String [] {"Apple", "Orange", "Banana", "Grapes", "Pineapple", "Car", "Door", "Airplane", "Engine", "Transformers", "Sockit", "Justin", "Wheel", "Substance", "Timmy"};
    private static final String TAG = "MainActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView shoppingList = (ListView) findViewById(R.id.shoppingList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        shoppingList.setAdapter(adapter);

        shoppingList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String food = String.valueOf(adapterView.getItemAtPosition(i));
                        Toast.makeText(MainActivity.this, food, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
