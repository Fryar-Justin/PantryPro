package edu.byui.pantrypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    EditText input;
    TextView text;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        input = findViewById(R.id.editText_input);
        text = findViewById(R.id.textView4);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    // add a product to the database
    public void addButtonClicked(View view) {
        Item item = new Item(input.getText().toString());
        dbHandler.addItem(item);
        printDatabase();
    }

    // deleate a product from the database
    public void deleteButtonClicked(View view) {
        String inputText = input.getText().toString();
        dbHandler.deleteItem(inputText);
        printDatabase();
    }

    // display what's in the database
    public void printDatabase() {
        String dbString = dbHandler.databaseToString();
        text.setText(dbString);
        input.setText("");
    }

    public void something() {
        String food = "Do you see me?";
        Toast.makeText(TestActivity.this, food, Toast.LENGTH_SHORT).show();
    }
}
