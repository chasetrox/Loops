package com.example.maxcembalest.loops;

import android.util.Log;
import android.widget.Toast;

import com.example.maxcembalest.loops.grid.LoopGrid;
import com.example.maxcembalest.loops.grid.LoopGridSquare;
import com.example.maxcembalest.loops.grid.MatrixRow;
import com.example.maxcembalest.loops.grid.ToneMatrix;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

import static com.example.maxcembalest.loops.LoopActivity.dimBeats;
import static com.example.maxcembalest.loops.LoopActivity.dimNotes;


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

    public boolean edit(String key) {
        HashMap<String, Object> editedLoop = new HashMap<>();
        String path = "users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/loops";
        editedLoop.put("key", key);
        editedLoop.put("rows", extractMatrixRows());
        FirebaseDatabase.getInstance().getReference().child(path).child(key).setValue(editedLoop);
        return true;
    }

    private HashMap<String,Object> extractMatrixRows() {
        HashMap<String,Object> rows = new HashMap<>();
        String map = "";
        HashMap<String, Double> freqs = new HashMap<>();
        HashMap<String, String> soundkeys = new HashMap<>();
        for (int i = 0; i < dimNotes; i++) {
            MatrixRow currRow = LoopGrid.getInstance().getRow(i);
            map += convertRowToBinString(currRow);
            freqs.put("row"+i, currRow.getFrequency());
            soundkeys.put("row"+i, currRow.getSoundKey());
        }
        rows.put("checkMap", map);
        rows.put("freqs", freqs);
        rows.put("soundkeys", soundkeys);

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

    public ToneMatrix load(String key) {
        String path = "users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/loops/"+key;
        return populateToneMatrix(path);
    }

    private ToneMatrix populateToneMatrix(String path) {
        final ToneMatrix tm = new ToneMatrix(null,null,null,null,null,null,null,null);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(path).child("rows");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,Object> hm = (HashMap<String, Object>) dataSnapshot.getValue();
                // Extracts matrix/row data
                populateTMfromHash(hm, tm);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", "ERROR IN ON LOAD FROM DB:" + databaseError.toString());
            }
        });
        return tm;
    }

    public void populateTMfromHash(HashMap<String, Object> hm, ToneMatrix tm) {
        String cm = (String) hm.get("checkMap");
        HashMap<String,Double> freqs = (HashMap<String,Double>) hm.get("freqs");
        HashMap<String,String> sk = (HashMap<String,String>) hm.get("soundkeys");
        for (int i = 0; i < dimNotes; i++) {
            MatrixRow new_row = new MatrixRow();
            if (i < freqs.size()) {
                //extracts row data from overall fb info
                new_row.setSoundKey(sk.get("row" + i));
                new_row.setFrequency(Double.parseDouble("" + freqs.get("row" + i)));
                setRowSquaresFromString(new_row, cm.substring(dimNotes * i, (dimNotes * i) + dimBeats));
            }
            tm.setRowI(i, new_row);
        }
    }

    private void setRowSquaresFromString(MatrixRow row, String binString) {
        //Potential for error here, off by one, maybe other
        int strlen = binString.length();
        for (int j = 0; j < dimBeats; j++) {
            if (j < strlen) {
                row.getSqJ(j).setClicked(binString.charAt(j) == '1');
            }
        }
    }

    ///---------------------------------------------------------------------------------------\\\
    /// Old code graveyard
    ///---------------------------------------------------------------------------------------\\\

    /*private MatrixRow convertDbToRow(String path, final int note) {
        final MatrixRow new_row = new MatrixRow(null,null,null,null,null,null,null,null,0,"KEY_DEFAULT");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(path).child("rows").child("row"+Integer.toString(note));
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,Object> hm = (HashMap<String, Object>) dataSnapshot.getValue();

                new_row.setSoundKey((String) hm.get("soundKey"));
                Double freq = Double.parseDouble(""+hm.get("frequency"));//
                new_row.setFrequency(freq);
                setRowSquaresFromString(new_row, (String) hm.get("rowSettings"));
                Log.d("HEY ALL", "I'm in on Data Change!!!");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", "ERROR IN ON LOAD FROM DB");
            }
        });
        return new_row;
    }*/

    /* public boolean save() {
        String path = "users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/loops";
        String key = FirebaseDatabase.getInstance().getReference().child(path).push().getKey();
        HashMap<String, Object> newLoopMap = new HashMap<>();
        newLoopMap.put(key, extractMatrixRows());
        FirebaseDatabase.getInstance().getReference().child(path).child(key).setValue(newLoop);
    }*/

    /*private HashMap<String,Object> extractMatrixRows() {
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
    }*/

}




