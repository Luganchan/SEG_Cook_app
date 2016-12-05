package ca.uottawa.cookapp;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by shawnco on 12/5/16.
 */

public class FavouriteRecipeManager {
    static ArrayList<Recipe> recipeList = new ArrayList<>();

    static ArrayList<Recipe> favouritesList = new ArrayList<>();

    public FavouriteRecipeManager(){
        recipeList = RecipeManager.getList();
        for (int i = 0; i< recipeList.size(); i++){
            if (recipeList.get(i).isFavourite){
                favouritesList.add(recipeList.get(i));
            }
        }
    }
    public static ArrayList <Recipe> getList(){
        return favouritesList;
    }

    public static void updateFavourites(){
        recipeList = RecipeManager.getList();
        for (int i = 0; i< recipeList.size(); i++){
            if (recipeList.get(i).isFavourite){
                favouritesList.add(recipeList.get(i));
            }
        }
    }

}

