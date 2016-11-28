package com.example.maxcembalest.loops;

import android.graphics.Matrix;
import android.util.Log;

import com.example.maxcembalest.loops.grid.LoopGrid;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by maxcembalest on 11/28/16.
 */

public class MatrixDataManager {
    private static MatrixDataManager instance = null;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    public MatrixDataManager MatrixDataManager() { // Database references are aggressively not working.
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.firebaseAuth = FirebaseAuth.getInstance();
        return this;
    }

    public static MatrixDataManager getInstance() {
        if (instance == null) {
            instance = new MatrixDataManager();
        }
        return instance;
    }

    public boolean save() {

        HashMap<String, Object> newLoop = new HashMap<>();

        extractMatrixRows();

        String path = "users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/loops";

        String key = FirebaseDatabase.getInstance().getReference().child(path).push().getKey();
        newLoop.put("key", key);
        FirebaseDatabase.getInstance().getReference().child(path).child(key).setValue(newLoop);
        return true; // TODO try catch or exceptions? in case of DB failure
    }

    private HashMap<String,Object> extractMatrixRows() {
        HashMap<String,Object> rows = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            HashMap<String,Object> row = new HashMap<>();
            //fill row/create string of 10
            row.put("soundKey", "DEFAULT");
            row.put("frequency", "880.0");
            row.put("rowSettings", "11001100");

            rows.put("row"+Integer.toString(i), row);
        }
        return rows;
    }

}


