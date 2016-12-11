package com.example.maxcembalest.loops.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maxcembalest.loops.R;
import com.example.maxcembalest.loops.adapter.ProjectRecyclerAdapter;
import com.example.maxcembalest.loops.grid.ToneMatrix;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.concurrent.TimeUnit;

/**
 * Created by Chase on 12/11/16.
 */

public class SharedLoopsFragment extends Fragment {
    protected RecyclerView projectRecycler;
    protected ProjectRecyclerAdapter projectRecyclerAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;
    public String TAG = "SHARED";
    private int time = 500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query q = FirebaseDatabase.getInstance().getReference().child("users/"+user+"/loops/");

        View rootView = inflater.inflate(R.layout.my_loops_fragment, container, false);
        rootView.setTag(TAG);

        projectRecycler = (RecyclerView) rootView.findViewById(R.id.myLoopsRecycler);
        projectRecycler.setHasFixedSize(true);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new GridLayoutManager(rootView.getContext(), 2);

        projectRecyclerAdapter = new ProjectRecyclerAdapter(q, ToneMatrix.class);
        // Set CustomAdapter as the adapter for RecyclerView.
        projectRecycler.setAdapter(projectRecyclerAdapter);
        projectRecyclerAdapter.applyListener();

        try { //Janky way to ensure data loads.
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        projectRecycler.setLayoutManager(mLayoutManager);

        return rootView;
    }


}
