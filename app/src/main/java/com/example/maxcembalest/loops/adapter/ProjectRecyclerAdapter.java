package com.example.maxcembalest.loops.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maxcembalest.loops.R;
import com.example.maxcembalest.loops.data.Song;
import com.example.maxcembalest.loops.grid.ToneMatrix;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chase on 11/21/16.
 */

public class ProjectRecyclerAdapter extends FirebaseRecyclerAdapter<ProjectRecyclerAdapter.ViewHolder, ToneMatrix> {


    public ProjectRecyclerAdapter(Query query, Class<ToneMatrix> itemClass) {
        super(query, itemClass);
    }

    public ProjectRecyclerAdapter(Query query, Class<ToneMatrix> itemClass, @Nullable ArrayList<ToneMatrix> items, @Nullable ArrayList<String> keys) {
        super(query, itemClass, items, keys);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("onbind", "In onbind");
        View loopCell = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.project_icon,parent,false);
        return new ViewHolder(loopCell);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ToneMatrix item = getItem(position);
        Log.d("onbind", "In onbind");
        holder.tvName.setText("Loop name");
    }

    @Override
    protected void itemAdded(ToneMatrix item, String key, int position) {
        Log.d("MyAdapter", "Added a new item to the adapter.");
    }

    @Override
    protected void itemChanged(ToneMatrix oldItem, ToneMatrix newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(ToneMatrix item, String key, int position) {

    }

    @Override
    protected void itemMoved(ToneMatrix item, String key, int oldPosition, int newPosition) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView infoBtn;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            infoBtn = (ImageView) itemView.findViewById(R.id.projInfoBtn);
            tvName = (TextView) itemView.findViewById(R.id.projName);
        }
    }
}
/*
    private List<Song> songList;
    public ProjectRecyclerAdapter() {


    }


    @Override
    public ProjectRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View songCell = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.project_icon,parent,false);
        return new ViewHolder(songCell);
    }

    @Override
    public void onBindViewHolder(ProjectRecyclerAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(songList.get(position).getName());
    }



    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView infoBtn;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            infoBtn = (ImageView) itemView.findViewById(R.id.projInfoBtn);
            tvName = (TextView) itemView.findViewById(R.id.projName);
        }
    }
}
*/