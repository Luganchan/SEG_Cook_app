package ca.uottawa.cookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shawnco on 11/22/16.
 */
public class RecipeArrayAdapter extends ArrayAdapter<Recipe> {
    private final Context context;
    private final Recipe[] recipes;

    public RecipeArrayAdapter(Context context, Recipe[] recipes) {
        super(context, R.layout.cook_app_items, recipes);
        this.context = context;
        this.recipes = recipes;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cook_app_items, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.recipe_title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.recipe_image);
        textView.setText(recipes[position].setTitle);
        imageView.setImageDrawable(recipes[position].drawable);


// Change the icon for Windows and iPhone

        return rowView;
    }
}