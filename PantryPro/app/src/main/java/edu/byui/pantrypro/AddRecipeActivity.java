package edu.byui.pantrypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AddRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        // change the labels
        TextView menuLabel = findViewById(R.id.textView_MenuLabel);
        menuLabel.setText(R.string.recipes);
    }
}
