package edu.byui.pantrypro;

import java.util.ArrayList;

/*************************************************************************************************
 * Class: Recipe
 *
 * Created by Justin on 3/18/18.
 * Holds a meal plan for a single day
 *
 * Variable Description:
 *      day:       Holds the day of the week this meal plan applies to
 *      breakfast: Holds the morning recipe
 *      lunch:     Holds the afternoon recipe
 *      dinner:    Holds the evening recipe
 *************************************************************************************************/

public class MealPlan {
    /*********************************************************************************************
     ***                                        Private                                        ***
     *********************************************************************************************/

    /***************** Data *****************/
    private String day;
    private Recipe breakfast;
    private Recipe lunch;
    private Recipe dinner;

    /*********************************************************************************************
     ***                                        Public                                         ***
     *********************************************************************************************/

    /********************************* Constructors *********************************/
    public MealPlan() {
        breakfast = new Recipe();
        lunch = new Recipe();
        dinner = new Recipe();
    }

    public MealPlan(Recipe breakfast, Recipe lunch, Recipe dinner) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    /******************************* Getters ********************************/
    public Recipe getBreakfast() { return breakfast; }
    public Recipe getLunch()     { return lunch;     }
    public Recipe getDinner()    { return dinner;    }
    public String getDay()       { return day;       }

    /********************************* Setters **********************************/
    public void setBreakfast(Recipe breakfast) { this.breakfast = breakfast; }
    public void setLunch(Recipe lunch)         { this.lunch = lunch;         }
    public void setDinner(Recipe dinner)       { this.dinner = dinner;       }
    public void setDay(String day)             { this.day = day;             }

    /******************************** Member Functions ****************************/

    // equals(MealPlan mealplan) - checks to see if the meal day, breakfast, lunch and dinner plans names are the same

    public boolean equals(MealPlan mealplan) {
        if (this.breakfast.getName().equals(mealplan.getBreakfast().getName())
                && this.lunch.getName().equals(mealplan.getLunch().getName())
                && this.dinner.getName().equals(mealplan.getDinner().getName())
                && this.day.equals(mealplan.getDay()))
        {
            return true;
        }
        return false;
    }
}
