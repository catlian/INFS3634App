package com.example.infs3634app.model;

public class DrinksImport {
    private Drinks[] drinks;

    public Drinks[] getDrinks ()
    {
        return drinks;
    }

    public void setDrinks (Drinks[] drinks)
    {
        this.drinks = drinks;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [drinks = "+drinks+"]";
    }
}
