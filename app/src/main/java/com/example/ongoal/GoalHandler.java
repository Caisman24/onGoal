package com.example.ongoal;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class GoalHandler {
    private static final String TAG = "GoalHandler";
    private Goal currentGoal;
    private FirebaseFirestore fireStore;

    public GoalHandler(Goal currentGoal) {
        this.currentGoal = currentGoal;
        fireStore = FirebaseFirestore.getInstance();
    }

    public void addGoal() {
        DocumentReference goalReference = fireStore.collection("goal").document(currentGoal.getGoalID());
        goalReference.set(currentGoal).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.v(TAG, "Goal created with id: " + currentGoal.getGoalID());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v(TAG, "Error creating goal: " + e.toString());
            }
        });

    }
}
