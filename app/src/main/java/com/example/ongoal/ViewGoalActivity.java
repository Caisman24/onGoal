package com.example.ongoal;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewGoalActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseFirestore firestore;
    private List<Goal> goalList;
    private ArrayList<Goal> currentUserGoalList;
    private GoalListAdapter goalListAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goal);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        currentUserGoalList = new ArrayList<>();
        goalList = new ArrayList<>();
        listView = findViewById(R.id.listViewGoal);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = auth.getCurrentUser();

        if (user != null) {
            firestore.collection("goal").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful() && task.getResult().size() > 0) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            goalList.add(documentSnapshot.toObject(Goal.class));
                        }
                        filterGoalsForUser();
                        goalListAdapter = new GoalListAdapter(ViewGoalActivity.this, R.layout.list_adapter_layout, currentUserGoalList);
                        listView.setAdapter(goalListAdapter);
                    }
                }
            });
        }
    }

    private void filterGoalsForUser() {
        user = auth.getCurrentUser();
        for (Goal goal : goalList) {
            if (goal.getGoalID().equals(user.getUid())) {
                currentUserGoalList.add(goal);
            }
        }
        Log.v("here goes: ", "error");
    }
}