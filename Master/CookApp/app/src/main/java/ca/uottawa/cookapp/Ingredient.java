package ca.uottawa.cookapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by shawnco on 11/30/16.
 */

public class Ingredient extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_layout);
        setTitle("Ingredient");
    }
}
