package ca.uottawa.cookapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    Drawable drawable;
    TextView title, description;
    String setTitle;
    Boolean isFavourite;
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_layout);

        final int data = getIntent().getExtras().getInt("recipeIndex");

        int position =getIntent().getExtras().getInt("recipeIndex");
        recipe = RecipeManager.getList().get(position);


        //setTitle(RecipeManager.recipeList);
// Get ListView object from xml layout
        ListView listView = (ListView) findViewById(R.id.ingredient_list);
//Defining Array values to show in


        String[] ingredients = recipe.getIngredients();


//Converting Array to ArrayList
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < ingredients.length; ++i) {
            list.add(ingredients[i]);
        }

        IngredientArrayAdapter adapter = new IngredientArrayAdapter(this.getApplicationContext(), ingredients);
        listView.setAdapter(adapter);


        ImageView imageView = (ImageView) findViewById(R.id.recipe_image);
        imageView.setImageDrawable(recipe.getRecipeDrawable());
        TextView textView = (TextView) findViewById(R.id.recipe_title);
        textView.setText(recipe.getRecipeTitle());
    //Create an ArrayAdapter and Set it on the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
            }
        });
        //delete button function
        Button deletebutton = (Button) findViewById(R.id.deletebutton);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecipe(data);
            }
        });

        // favourite button
        Button favourite = (Button) findViewById(R.id.favourite_button);
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {favouriteRecipe(data);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recipe_menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.edit_recipe:
                setEditable();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setEditable(){


        title = (TextView) findViewById(R.id.recipe_title);


        title.setFocusable(true);
        title.setEnabled(true);
        title.setClickable(true);
        title.setFocusableInTouchMode(true);
    }

    public void favouriteRecipe(int data){
        Recipe favourite = RecipeManager.getList().get(data);
        favourite.setIsFavourite();
        FavouriteRecipeManager.updateFavourites();
    }


    public void deleteRecipe(int data){
        RecipeManager.getList().remove(data);
        finish();

    }


}
