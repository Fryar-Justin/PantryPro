package edu.byui.pantrypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IngredientDetailsActivity extends AppCompatActivity {
    MyDBHandler dbHandler;
    TextView outputName;
    TextView outputQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_details);
        dbHandler = new MyDBHandler(this, null, null, 1);

        TextView activityLabel = findViewById(R.id.textView_MenuLabel);
        activityLabel.setText(R.string.ingredientDetails);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("NAME");
        String quantity = dbHandler.getItemQuantity(name);

        outputName = (TextView) findViewById(R.id.outputName);
        outputQuantity = (TextView) findViewById(R.id.outputQuantity);

        outputName.setText(name);
        outputQuantity.setText(quantity);

    }

    public void deleteItem(View view){
        dbHandler.deleteItem((String)outputName.getText());
        Intent returnInventory = new Intent(IngredientDetailsActivity.this, InventoryActivity.class);
        startActivity(returnInventory);
    }
}
