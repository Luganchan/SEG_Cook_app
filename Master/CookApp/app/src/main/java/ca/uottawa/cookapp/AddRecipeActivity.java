package ca.uottawa.cookapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by shawnco on 11/23/16.
 */

public class AddRecipeActivity extends AppCompatActivity {
    public Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe_layout);
        setTitle("Add Recipe");
        setVisible(true);
    }
}
