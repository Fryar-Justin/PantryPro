package edu.byui.pantrypro;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Justin on 3/18/18.
 */
public class RecipeTest {
    // recipe container
    private Recipe r;
    // recipe data
    private String name       = "Cereal";
    private String directions = "Add Cheerios & Milk to a bowl...Done!";
    private String notes      = "Make sure the milk is cold!";

    // ingredient data
    private String name1        = "Cheerios";
    private String dept1        = "Cerial Aisle";
    private String measurement1 = "Cups";
    private double price1       = 5.67;
    private int qty1            = 1;

    private String name2        = "Milk";
    private String dept2        = "Dairy";
    private String measurement2 = "Cups";
    private double price2       = 2.47;
    private int qty2            = 1;

    // constructor
    public RecipeTest() {
        r = new Recipe(name, directions, notes);

        r.getIngredients().add(new Ingredient(name1, dept1, measurement1, price1, qty1));
        r.getIngredients().add(new Ingredient(name2, dept2, measurement2, price2, qty2));
    }

    @Test
    public void getName() throws Exception {
        assertEquals(r.getName(), name);
    }

    @Test
    public void getDirections() throws Exception {
        assertEquals(r.getDirections(), directions);
    }

    @Test
    public void getNotes() throws Exception {
        assertEquals(r.getNotes(), notes);
    }

    @Test
    public void getIngredients() throws Exception {
        assertEquals(r.getIngredients().isEmpty(), false);
        assertEquals(r.getIngredients().get(0).getName(), name1);
        assertEquals(r.getIngredients().get(1).getName(), name2);
    }

    @Test
    public void setDirections() throws Exception {
        String newDirection = "This is a new change";
        assertEquals(r.getDirections(), directions);

        r.setDirections(newDirection);

        assertEquals(r.getDirections(), newDirection);
    }

    @Test
    public void setNotes() throws Exception {
        String newNote = "This is a new note";
        assertEquals(r.getNotes(), notes);

        r.setNotes(newNote);

        assertEquals(r.getNotes(), newNote);
    }

    @Test
    public void setName() throws Exception {
        String newName = "This is a new name";
        assertEquals(r.getName(), name);

        r.setName(newName);

        assertEquals(r.getName(), newName);
    }

    @Test
    public void setIngredients() throws Exception {
        assertEquals(r.getIngredients().size(), 2);
        assertEquals(r.getIngredients().get(0).getQty(), qty1);
        assertEquals(r.getIngredients().get(1).getQty(), qty2);

        ArrayList<Ingredient> newList = new ArrayList<Ingredient>();
        newList.add(new Ingredient("Name", 120));
        newList.add(new Ingredient("Name2", 150));

        r.setIngredients(newList);

        assertEquals(r.getIngredients().size(), 2);
        assertEquals(r.getIngredients().get(0).getQty(), 120);
        assertEquals(r.getIngredients().get(1).getQty(), 150);
    }

    @Test
    public void addIngredient() throws Exception {
        assertEquals(r.getIngredients().size(), 2);
        assertEquals(r.getIngredients().get(0).getQty(), 1);
        assertEquals(r.getIngredients().get(1).getQty(), 1);

        r.addIngredient(new Ingredient("Potatos", 100));

        assertEquals(r.getIngredients().size(), 3);
        assertEquals(r.getIngredients().get(0).getQty(), 1);
        assertEquals(r.getIngredients().get(1).getQty(), 1);
        assertEquals(r.getIngredients().get(2).getName(), "Potatos");
        assertEquals(r.getIngredients().get(2).getQty(), 100);
    }

    @Test
    public void removeIngredient() throws Exception {
        assertEquals(r.getIngredients().size(), 2);
        assertEquals(r.getIngredients().get(0).getName(), name1);
        assertEquals(r.getIngredients().get(1).getName(), name2);

        r.removeIngredient(new Ingredient(name1, 200));

        assertEquals(r.getIngredients().size(), 1);
        assertEquals(r.getIngredients().get(0).getName(), name2);
    }

    @Test
    public void clearIngredients() throws Exception {
        assertEquals(r.getIngredients().size(), 2);
        assertEquals(r.getIngredients().get(0).getName(), name1);
        assertEquals(r.getIngredients().get(1).getName(), name2);

        r.clearIngredients();

        assertEquals(r.getIngredients().size(), 0);
    }

}