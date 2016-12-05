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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by shawnco on 11/23/16.
 */


public class Recipe extends AppCompatActivity implements Serializable {

    Drawable drawable;
    TextView title, dscription;
    String[] ingredients;
    String setTitle;
    Boolean isFavourite;
    int id;


    public Recipe(int id, Drawable drawable, String setTitle, String[] ingredients) {
        this.drawable = drawable;
        this.setTitle = setTitle;
        this.ingredients = ingredients;
        this.isFavourite = false;
        this.id = id;
    }

    public Drawable getRecipeDrawable() {
        return drawable;
    }

    public String getRecipeTitle() {
        return setTitle;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setRecipeDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setRecipeTitle(String setTitle) {
        this.setTitle = setTitle;
    }

    public void setRecipeIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public void setIsFavourite(Boolean favourite) {
        this.isFavourite = favourite;
    }

    public boolean getIsFavourite() {
        return isFavourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}