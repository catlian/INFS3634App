package com.example.infs3634app.model;

import java.util.ArrayList;
/*
Adding ingredient details was a low-priority feature. Need this class for POJO -> Java to work.
 */
public class IngredientImport {
    private ArrayList<Ingredient> ingredients = null;

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
