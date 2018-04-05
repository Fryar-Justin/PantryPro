package edu.byui.pantrypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class IngredientDetailsActivity extends AppCompatActivity {

    TextView name;
    TextView measurement;
    MyDBHandler dbHandler;
    String ingredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_details);

        TextView activityLabel = findViewById(R.id.textView_MenuLabel);
        activityLabel.setText(R.string.ingredientDetails);

        dbHandler = new MyDBHandler(this, null, null, 1);

        Bundle extras = getIntent().getExtras();
        ingredient = "";
        if (extras != null) {
            ingredient = extras.getString("food");
        }

        name = findViewById(R.id.textView_Name);
        measurement = findViewById(R.id.textView_Quantity);

        name.setText(ingredient);
        measurement.setText(dbHandler.findEntry("recipe", "recipename", ingredient, "ingredients"));
    }

    public void deleteFromDatabase(View view) {
        dbHandler.deleteItem(ingredient);
    }
}
