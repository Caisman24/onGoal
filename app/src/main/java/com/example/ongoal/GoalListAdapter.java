package com.example.ongoal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GoalListAdapter extends ArrayAdapter<Goal> {
    private static final String TAG = "GoalListAdapter";
    private Context context;
    private int resource;
    private List<Goal> goalList;

    public GoalListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Goal> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.goalList = new ArrayList<>();
        goalList.addAll(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Goal goal = goalList.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent, false);

        TextView titleText = convertView.findViewById(R.id.textGoalTitle);
        TextView startText = convertView.findViewById(R.id.textGoalNumberOfDay);
        TextView finalText = convertView.findViewById(R.id.textGoalDayLimit);

        titleText.setText(goal.getData());
        startText.setText("Day number: "+String.valueOf(goal.getCurrentDay()));
        finalText.setText("Estimated days: "+String.valueOf(goal.getNumberOfDays()));

        return convertView;
    }
}
