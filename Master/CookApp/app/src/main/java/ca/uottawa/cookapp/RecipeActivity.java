package ca.uottawa.cookapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shawnco on 11/30/16.
 */
public class RecipeActivity extends AppCompatActivity {

    Drawable drawable;
    TextView title, discription;
    Ingredient[] ingredients;
    String setTitle;
    Boolean isFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_layout);
        setTitle("Recipe");
// Get ListView object from xml layout
        ListView listView = (ListView) findViewById(R.id.ingredient_list);
//Defining Array values to show in ListView
        String[] values = new String[]{
                "Item 01", "Item 02", "Item 03", "Item 04", "Item 05", "Item 06", "Item 07", "Item 08","Item 09", "Item 10", "Item 11", "Item 12","Item 13", "Item 14", "Item 15", "Item 16",
        };
//Converting Array to ArrayList
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
//Create an ArrayAdapter and Set it on the ListView
        IngridientArrayAdapter adapter = new IngridientArrayAdapter(this, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
//Do something with the string that you just got!
            }
        });

        FloatingActionButton heart = (FloatingActionButton) findViewById(R.id.heart);
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIsFavourite();
            }
        });
        FloatingActionButton delete = (FloatingActionButton) findViewById(R.id.delete);
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecipe();
            }
        });
    }
    public void setIsFavourite(){
        if (this.isFavourite==true){
            this.isFavourite=false;
        }else if (this.isFavourite==false){
            this.isFavourite=false;
        }

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
            case R.id.delete_recipe:
                deleteRecipe();
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

    public void deleteRecipe(){

    }
}
