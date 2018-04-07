package edu.byui.pantrypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class AssignRecipeToDay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_recipe_to_day);
    }

    private void setListenersAndLabels() {
        // set the label
        TextView activityLabel = findViewById(R.id.textView_MenuLabel);
        activityLabel.setText(R.string.inventory_label);

        // set the onclick event for list items
        ListView daysList = findViewById(R.id.listview_Days);
        daysList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String ingredientName = String.valueOf(adapterView.getItemAtPosition(i));
                        Intent itemDetail = new Intent(AssignRecipeToDay.this, IngredientDetailsActivity.class);
                        itemDetail.putExtra("NAME", ingredientName);
                        startActivity(itemDetail);
                    }
                }
        );

        // make the top right button disappear
        Button addButton = findViewById(R.id.button_TopRight);
        addButton.setVisibility(View.INVISIBLE);
    }
}
