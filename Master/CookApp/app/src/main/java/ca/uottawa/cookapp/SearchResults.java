package ca.uottawa.cookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by Kevin on 2016/11/30.
 */

public class SearchResults extends AppCompatActivity implements Serializable {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        ListView listView = (ListView) findViewById(R.id.search_list);

        RecipeArrayAdapter adapter= new RecipeArrayAdapter(this, CookApp.tempSearchHolder);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent (getApplicationContext(),RecipeActivity.class);
                intent.putExtra("recipeIndex",position);
                startActivityForResult(intent,0);
                CookApp.tempSearchHolder= new ArrayList<>();
            }
        });
    }


}