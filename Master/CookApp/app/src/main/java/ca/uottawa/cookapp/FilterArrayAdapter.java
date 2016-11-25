package ca.uottawa.cookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

/**
 * Created by shawnco on 11/25/16.
 */

public class FilterArrayAdapter extends ArrayAdapter {

    private final Context context;
    private final String[] values;

    public FilterArrayAdapter(Context context, String[] values) {
        super(context,R.layout.filter_item, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.filter_item, parent, false);

        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.filter_checkbox);
        checkBox.setText(values[position]);

        return rowView;
    }
}
