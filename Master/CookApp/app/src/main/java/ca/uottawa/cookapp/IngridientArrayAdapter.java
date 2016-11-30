package ca.uottawa.cookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shawnco on 11/30/16.
 */
public class IngridientArrayAdapter extends ArrayAdapter {

    private final Context context;
    private final String[] values;

    public IngridientArrayAdapter(Context context, String[] values) {
        super(context,R.layout.ingredient_layout, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.ingredient_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.ingredient_title);
        textView.setText(values[0]);

        return rowView;

    }
}
