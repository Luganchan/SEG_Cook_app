
/**
 * Created by shawnco on 11/19/16.
 */
package ca.uottawa.cookapp;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

/**
 * Created by shawnco on 11/17/16.
 */

public class RecipeArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    public RecipeArrayAdapter(Context context, String[] values) {
        super(context, R.layout.items, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.items, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.line01);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);
// Change the icon for Windows and iPhone
        /*
        String s = values[position];
        if (s == null || s.isEmpty() || s.equals("empty")) {
            imageView.setImageResource(R.drawable.ic_logo_empty);
        } else {
            imageView.setImageResource(R.drawable.ic_logo_mil);
        }
        return rowView;
    */
        return rowView;
    }



}