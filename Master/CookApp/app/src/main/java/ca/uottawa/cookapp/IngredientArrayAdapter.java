package ca.uottawa.cookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by shawnco on 12/5/16.
 */

public class IngredientArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;
    public IngredientArrayAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.ingredient_item, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ingredient_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.ingredient);
        textView.setText(values.get((position)));

        return rowView;
    }


}
