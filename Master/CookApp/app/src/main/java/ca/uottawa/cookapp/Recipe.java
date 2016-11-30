package ca.uottawa.cookapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shawnco on 11/23/16.
 */


public class Recipe extends AppCompatActivity {

    Drawable drawable;
    TextView title, dscription;
    Ingredient[] ingredients;
    String setTitle;
    Boolean isFavourite;


    public Recipe (Drawable drawable, String setTitle, Ingredient[] ingredients){
        this.drawable = drawable;
        this.setTitle = setTitle;
        this.ingredients = ingredients;
        this.isFavourite=false;
    }

    public Drawable getRecipeDrawable(){
        return drawable;
    }

    public String getRecipeTitle(){
        return setTitle;
    }

    public Ingredient[] getIngredients(){
        return ingredients;
    }
    public void setRecipeDrawable(Drawable drawable){
        this.drawable = drawable;
    }

    public void setRecipeTitle(String setTitle){
        this.setTitle = setTitle;
    }

    public void setRecipeIngredients(Ingredient[] ingredients){
        this.ingredients = ingredients;
    }
    public void setIsFavourite(Boolean favourite){
        this.isFavourite=isChangingConfigurations();
    }
    public boolean getIsFavourite(){
        return isFavourite;
    }


   public void deleteRecipe(){}
}
