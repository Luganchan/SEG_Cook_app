package ca.uottawa.cookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shawnco on 11/22/16.
 */
public class RecipeArrayAdapter extends ArrayAdapter<Recipe> {
    private final Context context;
    private final ArrayList<Recipe> recipes;

    public RecipeArrayAdapter(Context context, ArrayList<Recipe> recipes) {
        super(context, R.layout.cook_app_items, recipes);
        this.context = context;
        this.recipes = recipes;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Recipe recipe = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cook_app_items, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.recipe_title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.recipe_image);
        textView.setText(recipe.setTitle);
        imageView.setImageDrawable(recipe.drawable);


// Change the icon for Windows and iPhone

        return rowView;
    }
}