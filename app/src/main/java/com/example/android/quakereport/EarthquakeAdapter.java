package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mpudota on 7/27/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;
        if (rootView == null)
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        Earthquake currentEathquake = getItem(position);
        TextView magnitude = (TextView) rootView.findViewById(R.id.magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudecircleColor = getMagnitudeColor(currentEathquake.getMagnitude());
        magnitudeCircle.setColor(magnitudecircleColor);
        magnitude.setText(formatMagnitude(currentEathquake.getMagnitude()));
        TextView direction = (TextView) rootView.findViewById(R.id.direction);
        TextView location = (TextView) rootView.findViewById(R.id.location);
        String location1 = currentEathquake.getLocation();
        String offsetLocation, primaryLocation;
        if (location1.contains(" of ")) {
            String[] directionAndLocation = location1.split(" of ");
            offsetLocation = directionAndLocation[0] + " of ";
            primaryLocation = directionAndLocation[1];
        }
        else {
            offsetLocation = "Near the";
            primaryLocation = currentEathquake.getLocation();
        }

        direction.setText(offsetLocation);
        location.setText(primaryLocation);
        TextView date = (TextView) rootView.findViewById(R.id.date);
        TextView timeofday = (TextView) rootView.findViewById(R.id.time);
        long time = currentEathquake.getDate();
        Date date1 = new Date(time);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd, hh:mm:ss a");
        String day = dateFormat.format(date1);
        String[] dayAndTime = day.split(",");
        date.setText(dayAndTime[0]);
        timeofday.setText(dayAndTime[1]);
        return rootView;
    }
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
    private int getMagnitudeColor (double magValue) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magValue);
        switch (magnitudeFloor) {
            case 0 :
            case 1 :
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2 :
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3 :
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4 :
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5 :
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6 :
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7 :
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8 :
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9 :
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default :
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
