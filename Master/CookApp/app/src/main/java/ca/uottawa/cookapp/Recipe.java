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
    TextView title, discription;
    Ingredient[] ingredients;
    String setTitle;


    public Recipe (Drawable drawable, String setTitle, Ingredient[] ingredients){
        this.drawable = drawable;
        this.setTitle = setTitle;
        this.ingredients = ingredients;
    }

   public void deleteRecipe(){}
}
