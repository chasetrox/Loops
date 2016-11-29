package com.example.maxcembalest.loops;

import android.graphics.Matrix;
import android.util.Log;

import com.example.maxcembalest.loops.grid.LoopGrid;
import com.example.maxcembalest.loops.grid.MatrixRow;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

import static com.example.maxcembalest.loops.grid.LoopGridView.dimBeats;
import static com.example.maxcembalest.loops.grid.LoopGridView.dimNotes;

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
        Log.d("IN SAVE", "Hey it me at top of save");
        HashMap<String, Object> newLoop = new HashMap<>();
        String path = "users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/loops";
        Log.d("IN SAVE", "Hey it me post get instance");
        String key = FirebaseDatabase.getInstance().getReference().child(path).push().getKey();
        newLoop.put("key", key);
        newLoop.put("rows", extractMatrixRows());
        Log.d("IN SAVE", "post extraction");
        FirebaseDatabase.getInstance().getReference().child(path).child(key).setValue(newLoop);
        Log.d("IN SAVE", "fin");
        return true; // TODO try catch or exceptions? in case of DB failure
    }

    private HashMap<String,Object> extractMatrixRows() {
        HashMap<String,Object> rows = new HashMap<>();
        for (int i = 0; i < dimNotes; i++) {
            HashMap<String,Object> row = new HashMap<>();

            MatrixRow currRow = LoopGrid.getInstance().getRow(i);
            row.put("soundKey", currRow.getSoundKey());
            row.put("frequency", currRow.getFrequency());
            row.put("rowClicks", convertRowToBinString(currRow));

            rows.put("row"+Integer.toString(i), row);
        }
        return rows;
    }

    private String convertRowToBinString(MatrixRow row) {
        String binString = "";

        for (int i = 0; i < dimBeats; i++) {
            boolean c = row.getSqJ(i).isClicked();
            binString += (c ? "1": "0");
        }
        Log.d("ROW2STRING", binString);

        return binString;
    }

}


