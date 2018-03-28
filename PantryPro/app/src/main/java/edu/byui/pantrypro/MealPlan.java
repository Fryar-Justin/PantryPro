package edu.byui.pantrypro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MealPlan extends AppCompatActivity{
    ListView inputDate;
    MyDBHandler dbHandler;
    Intent shareButtonIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);
        inputDate = (ListView) findViewById(R.id.datetext);

         dbHandler = new MyDBHandler(this, null, null, 1);


        ArrayList<String> days = populateDays();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, days );
        inputDate.setAdapter(arrayAdapter);
    }

   ArrayList<String> populateDays(){
        ArrayList<String> stringlist = new ArrayList<>();
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for(int i = 0; i < 7; i++){
            stringlist.add(days[i]);
            stringlist.add("    Breakfast");
            stringlist.add("    Lunch");
            stringlist.add("    Dinner");
        }

        return stringlist;
    }
}
