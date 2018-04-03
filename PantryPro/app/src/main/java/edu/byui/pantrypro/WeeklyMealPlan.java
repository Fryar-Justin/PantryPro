package edu.byui.pantrypro;


import java.util.ArrayList;

public class WeeklyMealPlan {
    /*************************************************************************************************
     * Class: WeeklyMealPlan
     *
     * Created by Justin on 4/02/18.
     * Holds a meal plan for an entire week
     *
     * Variable Description:
     *      sunday:       Holds sunday's list of meal plans
     *      monday: Holds Holds monday's list of meal plans
     *      tuesday:      Holds tuesday's list of meal plans
     *      wednesday:    Holds wednesday's list of meal plans
     *      thrusday:     Holds thursday's list of meal plans
     *      friday:       Holds friday's list of meal plans
     *      saturday:     Holds saturday'slist of meal plans
     *************************************************************************************************/

    /*********************************************************************************************
     ***                                        Private                                        ***
     *********************************************************************************************/

    /***************** Data *****************/
    private DailyMealPlan sunday;
    private DailyMealPlan monday;
    private DailyMealPlan tuesday;
    private DailyMealPlan wednesday;
    private DailyMealPlan thursday;
    private DailyMealPlan friday;
    private DailyMealPlan saturday;

    /*********************************************************************************************
     ***                                        Public                                         ***
     *********************************************************************************************/

    /********************************* Constructors *********************************/
    public WeeklyMealPlan() {
        this.sunday = new DailyMealPlan();
        this.monday = new DailyMealPlan();
        this.tuesday = new DailyMealPlan();
        this.wednesday = new DailyMealPlan();
        this.thursday = new DailyMealPlan();
        this.friday = new DailyMealPlan();
        this.saturday = new DailyMealPlan();
    }

    // manual input
    public WeeklyMealPlan(DailyMealPlan sunday, DailyMealPlan monday, DailyMealPlan tuesday, DailyMealPlan wednesday, DailyMealPlan thursday, DailyMealPlan friday, DailyMealPlan saturday) {
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
    }

    // you should have all days of the week in the weeksPlans parameter, so 7 items in the arraylist
    public WeeklyMealPlan(ArrayList<DailyMealPlan> weeksPlans) {
        this.sunday = weeksPlans.get(0);
        this.monday = weeksPlans.get(1);
        this.tuesday = weeksPlans.get(2);
        this.wednesday = weeksPlans.get(3);
        this.thursday = weeksPlans.get(4);
        this.friday = weeksPlans.get(5);
        this.saturday = weeksPlans.get(6);
    }

    /******************************* Getters ********************************/
    public DailyMealPlan getSunday()    { return sunday;    }
    public DailyMealPlan getMonday()    { return monday;    }
    public DailyMealPlan getTuesday()   { return tuesday;   }
    public DailyMealPlan getWednesday() { return wednesday; }
    public DailyMealPlan getThursday()  { return thursday;  }
    public DailyMealPlan getFriday()    { return friday;    }
    public DailyMealPlan getSaturday()  { return saturday;  }

    /*********************************** Setters ************************************/
    public void setSunday(DailyMealPlan sunday)       { this.sunday = sunday;       }
    public void setMonday(DailyMealPlan monday)       { this.monday = monday;       }
    public void setTuesday(DailyMealPlan tuesday)     { this.tuesday = tuesday;     }
    public void setWednesday(DailyMealPlan wednesday) { this.wednesday = wednesday; }
    public void setThursday(DailyMealPlan thursday)   { this.thursday = thursday;   }
    public void setFriday(DailyMealPlan friday)       { this.friday = friday;       }
    public void setSaturday(DailyMealPlan saturday)   { this.saturday = saturday;   }

    /********************************** Member Functions ******************************/

    // getAllPlans() - Gathers the entire weeks plans and returns them in an ArrayList<DailyMealPlan> object
    //               - This should make it easier to quickly iterate through all of the days of the week if needed

    public ArrayList<DailyMealPlan> getAllPlans() {
        ArrayList<DailyMealPlan> weeksplans = new ArrayList<DailyMealPlan>();

        weeksplans.add(this.sunday);
        weeksplans.add(this.monday);
        weeksplans.add(this.tuesday);
        weeksplans.add(this.wednesday);
        weeksplans.add(this.thursday);
        weeksplans.add(this.friday);
        weeksplans.add(this.saturday);

        return weeksplans;
    }
}
