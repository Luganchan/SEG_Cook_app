package ca.uottawa.cookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by shawnco on 11/23/16.
 */

public class AddRecipeActivity extends AppCompatActivity {
    public Button saveButton;
    ArrayList <String> ingredients = new ArrayList <String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe_layout);
        setTitle("Add Recipe");
        setVisible(true);



    }
    public void addIngredient(View view){
        ingredients.add(findViewById(R.id.addIngredienttext).toString());
    }

    public void save(View view){
    Recipe newRecipe= new Recipe(RecipeManager.recipeList.size()+1,null, findViewById(R.id.editName).toString(), ingredients);
        RecipeManager.recipeList.add(newRecipe);
        this.finish();

    }

}
