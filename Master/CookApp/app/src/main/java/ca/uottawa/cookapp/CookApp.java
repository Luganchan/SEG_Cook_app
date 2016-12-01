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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_app);
        String[] ingredients=new String[] {"bread"};
        //RecipeManager recipeManager = new RecipeManager();
        Recipe pasta = new Recipe(1,ContextCompat.getDrawable(getApplicationContext(), R.drawable.pasta), "pasta", ingredients );
        Recipe pastb = new Recipe(1,ContextCompat.getDrawable(getApplicationContext(), R.drawable.pasta), "pastB", ingredients );
        Recipe pastb2 = new Recipe(1,ContextCompat.getDrawable(getApplicationContext(), R.drawable.pasta), "pastb", ingredients );
        RecipeManager.recipeList.add(pasta);
        RecipeManager.recipeList.add(pastb);
        RecipeManager.recipeList.add(pastb2);

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
        startActivity(intent);
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
    public boolean onQueryTextSubmit(String newText) {
        // User pressed the search button
        // User changed the text
        if(newText != null && !newText.isEmpty()){
            ArrayList<Recipe> lstFound = new ArrayList<>();
            for(int i = 0; i<RecipeManager.getList().size();i++){
                if(RecipeManager.getList().get(i).getRecipeTitle().toLowerCase().contains(newText.toLowerCase()))
                    lstFound.add(RecipeManager.getList().get(i));
            }
            tempSearchHolder =lstFound;
        }
        else{
            tempSearchHolder = new ArrayList<>();
        }

        openSearchResults();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

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
            case R.id.action_filter:
                openFilterActivity();
                break;
            case R.id.action_settings:
                openSettingsActivity();
                break;
            case R.id.help_page_button:
                openHelpPage();
        }

        return super.onOptionsItemSelected(item);
    }

    public void openSettingsActivity(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


    public void openFilterActivity(){
        Intent intent = new Intent(this, FilterActivity.class);
        startActivity(intent);

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
            View rootView = inflater.inflate(R.layout.cook_app, container, false);

            return rootView;
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
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Recipes";
                case 1:
                    return "Favorites";
                case 2:
                    return "Grocery List";
            }
            return null;
        }
    }

    /*
    The two next classes make the fragments of the fragments of the CookApp activity.
     */

    public static class RecipesListFragment extends Fragment{


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.cook_app_fragments, container, false);
            ListView listView = (ListView) view.findViewById(R.id.list);


            String[] ingredients = new String[]{"bread"};



            Recipe pasta = new Recipe(1,ContextCompat.getDrawable(getContext(), R.drawable.pasta), "pasta", ingredients );
            Recipe soupe = new Recipe(1,ContextCompat.getDrawable(getContext(), R.drawable.soupe), "Soupe", ingredients );

            Recipe[] recipes = new Recipe[]{
                pasta,soupe,pasta,pasta,pasta,pasta,pasta,

            };


            RecipeArrayAdapter adapter = new RecipeArrayAdapter(this.getContext(), RecipeManager.getList());
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    Intent intent = new Intent (getContext(),RecipeActivity.class);
                    startActivityForResult(intent,0);

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


            RecipeArrayAdapter adapter = new RecipeArrayAdapter(this.getContext(),RecipeManager.getList());
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    Recipe item = (Recipe) parent.getItemAtPosition(position);
                    openRecipe(item);
                }
            });

            return view;
        }
        public void openRecipe(Recipe item){
            Intent intent = new Intent(getContext(), Recipe.class);
            intent.putExtra("Recipe", item);
            startActivity(intent);
        }
    }
}



