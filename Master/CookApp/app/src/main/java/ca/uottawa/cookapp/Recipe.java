package ca.uottawa.cookapp;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
//import android.widget.TextView;
import java.io.Serializable;





public class Recipe extends AppCompatActivity implements Serializable {

    Drawable drawable;
    String description;
    String[] ingredients, type;
    String setTitle;
    Boolean isFavourite;
    int id;


    public Recipe(int id, Drawable drawable, String setTitle, String[] ingredients, String[] type, String description) {
        this.drawable = drawable;
        this.setTitle = setTitle;
        this.ingredients = ingredients;
        this.isFavourite = false;
        this.id = id;
        this.type = type;
        this.description=description;
    }

    public Drawable getRecipeDrawable() {
        return drawable;
    }

    public String getRecipeTitle() {
        return setTitle;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setRecipeDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setRecipeTitle(String setTitle) {
        this.setTitle = setTitle;
    }

    public void setRecipeIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public void setIsFavourite() {
        if( this.getIsFavourite()){
            this.isFavourite=false;
        }else if (!this.getIsFavourite()){
            this.isFavourite=true;
        }
    }

    public boolean getIsFavourite() {
        return isFavourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getTypeList(){
        return type;
    }

    public void setType(String[] newType){
        this.type=newType;

    }

}