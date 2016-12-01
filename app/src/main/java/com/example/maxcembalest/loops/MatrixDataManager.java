package com.example.maxcembalest.loops;

import android.graphics.Matrix;
import android.util.Log;
import android.widget.Toast;

import com.example.maxcembalest.loops.grid.LoopGrid;
import com.example.maxcembalest.loops.grid.LoopGridSquare;
import com.example.maxcembalest.loops.grid.MatrixRow;
import com.example.maxcembalest.loops.grid.ToneMatrix;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        HashMap<String, Object> newLoop = new HashMap<>();
        String path = "users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/loops";
        String key = FirebaseDatabase.getInstance().getReference().child(path).push().getKey();
        newLoop.put("key", key);
        newLoop.put("rows", extractMatrixRows());
        FirebaseDatabase.getInstance().getReference().child(path).child(key).setValue(newLoop);
        return true; // TODO try catch or exceptions? in case of DB failure
    }

   /* public boolean save() {
        String path = "users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/loops";
        String key = FirebaseDatabase.getInstance().getReference().child(path).push().getKey();
        HashMap<String, Object> newLoopMap = new HashMap<>();
        newLoopMap.put(key, extractMatrixRows());
        FirebaseDatabase.getInstance().getReference().child(path).child(key).setValue(newLoop);
    }*/

    public ToneMatrix load(String key) {
        ToneMatrix tm = new ToneMatrix();
        String path = "users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/loops/"+key;
        for (int i = 0; i < dimNotes; i++) {
            tm.setRowI(i, convertDbToRow(path,i));
        }
        return tm;
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

        return binString;
    }

    private MatrixRow convertDbToRow(String path, final int note) {
        final MatrixRow new_row = new MatrixRow();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(path+"/row"+note);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,Object> hm = (HashMap<String, Object>) dataSnapshot.getValue();
                new_row.setSoundKey((String) hm.get("soundKey"));
                new_row.setFrequency(Float.parseFloat((String) (hm.get("frequency"))));
                setRowSquaresFromString(new_row, (String)hm.get("rowClicks"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", "ERROR IN ON LOAD FROM DB");
            }
        });
        return new_row;
    }

    private void setRowSquaresFromString(MatrixRow row, String binString) {
        //Potential for error here, off by one, maybe other
        for (int i = 0; i < dimBeats; i++) {
            if (binString.length() <= i) {
                row.setSqJ(i, new LoopGridSquare(false));
                continue;
            }
            row.setSqJ(i, new LoopGridSquare(binString.charAt(i) == '1'));
        }
    }

}




