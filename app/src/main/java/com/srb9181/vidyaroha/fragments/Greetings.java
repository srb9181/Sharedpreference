package com.srb9181.vidyaroha.fragments;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.srb9181.vidyaroha.utils.Constants;
import com.srb9181.vidyaroha.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Greetings extends Fragment {
    int count;
    TextView textView;

    public Greetings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_greetings, container, false);
        textView = (TextView) view.findViewById(R.id.text);


        return view;

    }


    @Override
    public void onResume() {
        super.onResume();

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.APP, Context.MODE_PRIVATE);
            count = sharedPreferences.getInt(Constants.APP_COUNT, 0);

            String appCount = String.valueOf(count);
            if (count == 1)
                appCount = appCount + "st";
            else if (count == 2)
                appCount = appCount + "nd";
            else if (count == 3)
                appCount = appCount + "rd";
            else
                appCount = appCount + "th";
            textView.setText("Welcome to " + Constants.APP+"you have entered into the app for the " + appCount + " time today");
            Toast.makeText(getActivity(), "Hello user , you have entered into the app for the " + appCount + " time today", Toast.LENGTH_SHORT).show();

    }
}
