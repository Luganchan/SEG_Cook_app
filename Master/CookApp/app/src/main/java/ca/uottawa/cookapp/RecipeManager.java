package ca.uottawa.cookapp;

import java.util.ArrayList;

/**
 * Created by Oleg on 11/30/2016.
 */
public class RecipeManager {
    static ArrayList <Recipe> recipeList = new ArrayList<>();
    public RecipeManager(){
    }
    public static ArrayList <Recipe> getList(){
        return recipeList;
    }

}
