package ca.uottawa.cookapp;

import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class CookApp extends AppCompatActivity implements SearchView.OnQueryTextListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static ArrayList<Recipe> tempSearchHolder = new ArrayList<>();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public Toolbar toolbar;
    static RecipeArrayAdapter Recipeadapter;
    static RecipeArrayAdapter Favouriteadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_app);


        //RecipeManager recipeManager = new RecipeManager();

        Recipe pasta = new Recipe(0,ContextCompat.getDrawable(getApplicationContext(), R.drawable.pasta), "Pasta", new String[]{"Water", "Bread", "Butter","kevinzaft"},new String[]{"one"},null);
        Recipe soup = new Recipe(1,ContextCompat.getDrawable(getApplicationContext(), R.drawable.soupe), "Soup",  new String[]{"Water", "Bread", "Butter", "tomatoes","kevinzaft"},new String[]{"one", "two"},null);
        Recipe bread = new Recipe(2,ContextCompat.getDrawable(getApplicationContext(), R.drawable.bread), "Bread", new String[]{"Water", "Bread", "Butter", "tomatoes"},new String[]{"one", "two", "three"},null);
        Recipe pizza = new Recipe(3,ContextCompat.getDrawable(getApplicationContext(), R.drawable.pepperoni_pizza), "Pizza", new String[]{"Water", "Bread", "Butter", "tomatoes"} ,new String[]{"one", "two", "three","four"},null);
        Recipe perogies = new Recipe(4,ContextCompat.getDrawable(getApplicationContext(), R.drawable.perogies), "Perogies", new String[]{"Water", "Bread", "Butter", "tomatoes"},new String[]{"one", "two", "three","four","five"},null );
        Recipe salad = new Recipe(5,ContextCompat.getDrawable(getApplicationContext(), R.drawable.garden), "Salad", new String[]{"Water", "Bread", "Butter", "tomatoes"} ,new String[]{"one", "two", "three","four","five","six"},null);



        RecipeManager.recipeList.add(pasta);
        RecipeManager.recipeList.add(soup);

        RecipeManager.recipeList.add(bread);

        RecipeManager.recipeList.add(pizza);

        RecipeManager.recipeList.add(perogies);

        RecipeManager.recipeList.add(salad);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openAddRecipe();
                }
        });

    }


    public void openAddRecipe(){
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivityForResult(intent,262);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe_list, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String newText) {// User pressed the search button

        //Checks to see if the User actually entered something in the TextField
        if(newText != null && !newText.isEmpty()){

            ArrayList<Recipe> lstFound = new ArrayList<>();//ArrayList to hold the search results

            String[] wordArray = newText.toLowerCase().split(" ");//splits the User input String into an array

            /*Creates an array of ArrayLists
             *each index of the array contains words grouped by "and"
             * the ArrayLists inside of the array holds the words needed to be checked
             * when adding to the search results
             */
            ArrayList<String>[] thingsToSearch = new ArrayList[wordArray.length];
            for (int a = 0; a<thingsToSearch.length;a++){
                thingsToSearch[a]=new ArrayList<>();
            }
            int thingsToSearchIndex = 0;
            for (int i = 0; i<wordArray.length; i++){

                if(wordArray[i].equals("and")){
                    //does nothing with the word
                }
                else if(wordArray[i].equals("or")){
                    thingsToSearchIndex++;//basically number of ORs (total search results -1)
                }
                else{
                    thingsToSearch[thingsToSearchIndex].add(wordArray[i]);
                }
            }

            /*
             *this while loop loops through the array (of ArrayList) indices that actually have a ArrayList
             * and then does some logic to determine if it should be added to the search results or not
             */
            while(thingsToSearchIndex>=0){
                for(int i = 0; i<RecipeManager.getList().size();i++){//Goes though all of the stored recipes
                    boolean inTitle = false;
                    boolean okToAddToSearch = false;
                    boolean foundCurrent = false;
                    for (int z= 0; z <thingsToSearch[thingsToSearchIndex].size();z++) {//goes through all of the things the stored recipe needs to have
                        //checks to see if the word is in the title
                        if((RecipeManager.getList().get(i).getRecipeTitle().toLowerCase().contains(thingsToSearch[thingsToSearchIndex].get(z)))){
                            inTitle = true;
                        }
                        else {
                            for (int x = 0; x < RecipeManager.getList().get(i).getIngredients().length; x++) {//checks the stored recipe's ingredients
                                if (RecipeManager.getList().get(i).getIngredients()[x].toLowerCase().contains(thingsToSearch[thingsToSearchIndex].get(z))) {
                                    okToAddToSearch = true;
                                    foundCurrent = true;
                                }
                            }
                            for (int x = 0; x < RecipeManager.getList().get(i).getTypeList().length; x++) {//checks the stored recipe's types
                                if (RecipeManager.getList().get(i).getTypeList()[x].toLowerCase().contains(thingsToSearch[thingsToSearchIndex].get(z))) {
                                    okToAddToSearch = true;
                                    foundCurrent = true;
                                }
                            }
                            /*
                             * as soon as the recipe does not find the word in the title, ingredients, or types,
                             * disqualify this recipe
                             */
                            if (!foundCurrent&&!inTitle){
                                okToAddToSearch = false;
                                break;
                            }
                            foundCurrent=false;
                        }
                        inTitle=false;
                    }
                    //adds to the search results if the recipe has what the User searched for
                    if (okToAddToSearch){
                        lstFound.add(RecipeManager.getList().get(i));
                    }
                    okToAddToSearch=false;

                }
                thingsToSearchIndex--;
            }

            tempSearchHolder =lstFound;//
        }

        // if the User entered nothing into the search bar then don't do anything
        else{
            tempSearchHolder = new ArrayList<>();
        }
        openSearchResults();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.help_page_button:
                openHelpPage();
        }

        return super.onOptionsItemSelected(item);
    }


    public void openHelpPage(){
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }
    public void openSearchResults(){
        Intent intent = new Intent(this, SearchResults.class);
        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.cook_app, container, false);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch(position){
                case 0:
                    return new RecipesListFragment();
                case 1:
                    return new FavoritesListFragment();
                default:
                    return PlaceholderFragment.newInstance(position + 1);

            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Recipes";
                case 1:
                    return "Favorites";
            }
            return null;
        }
    }

    /*
    The two next classes make the fragments of the fragments of the CookApp activity.
     */

    public static class RecipesListFragment extends Fragment{
        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.cook_app_fragments, container, false);
            ListView listView = (ListView) view.findViewById(R.id.list);


            Recipeadapter = new RecipeArrayAdapter(this.getContext(), RecipeManager.getList());
            listView.setAdapter(Recipeadapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    Intent intent = new Intent (getContext(),RecipeActivity.class);
                    intent.putExtra("recipeIndex",position);
                    intent.putExtra("Class", "Recipes");
                    startActivityForResult(intent,262);

                }
            });

            return view;

        }


    }


    public static class FavoritesListFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.cook_app_fragments, container, false);
            ListView listView = (ListView) view.findViewById(R.id.list);

            ArrayList<Recipe> tempFav = new ArrayList<Recipe>();
            for (int i=0; i<RecipeManager.getList().size(); i++){
                if (RecipeManager.getList().get(i).getIsFavourite()){
                    tempFav.add(i,RecipeManager.getList().get(i));
                }
            }
            Favouriteadapter = new RecipeArrayAdapter(this.getContext(), FavouriteRecipeManager.getList());
            listView.setAdapter(Favouriteadapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    Intent intent = new Intent (getContext(),RecipeActivity.class);
                    intent.putExtra("FavouritesRecipeIndex",position);
                    intent.putExtra("Class", "Favourites");

                    startActivityForResult(intent,5);
                }
            });

            return view;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
            Recipeadapter.notifyDataSetChanged();
            Favouriteadapter.notifyDataSetChanged();
    }

}



