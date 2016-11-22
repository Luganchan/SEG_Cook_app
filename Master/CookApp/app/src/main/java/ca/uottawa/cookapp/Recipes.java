package ca.uottawa.cookapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by shawnco on 11/22/16.
 */

public class Recipes extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.recipes_layout, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list);

        String[] values = new String[]{
                "Item 01", "Item 02", "Item 03", "Item 04", "Item 05", "Item 06"
        };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        RecipeArrayAdapter adapter = new RecipeArrayAdapter(this.getContext(), values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
//Do something with the string that you just got!
            }
        });

        return view;
    }
}
