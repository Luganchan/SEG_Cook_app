package ca.uottawa.cookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        EditText ingr;
        ingr =(EditText)findViewById(R.id.editName);
        ingredients.add(ingr.getText().toString());
    }

    public void save(View view){
        EditText title;
        title =(EditText)findViewById(R.id.editName);

    Recipe newRecipe= new Recipe(RecipeManager.recipeList.size()+1,null,title.getText().toString(), ingredients);
        RecipeManager.recipeList.add(newRecipe);
        this.finish();

    }

}
