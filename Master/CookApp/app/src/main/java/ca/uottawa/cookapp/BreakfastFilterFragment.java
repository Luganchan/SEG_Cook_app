package ca.uottawa.cookapp;


import android.view.View;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by shawnco on 11/23/16.
 */

public class BreakfastFilterFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.breakfast_filter_fragment, container, false);
    }
}
