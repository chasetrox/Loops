package com.example.maxcembalest.loops.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maxcembalest.loops.LoopActivity;
import com.example.maxcembalest.loops.R;
import com.example.maxcembalest.loops.data.Song;
import com.example.maxcembalest.loops.grid.LoopGrid;
import com.example.maxcembalest.loops.grid.ToneMatrix;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chase on 11/21/16.
 */

public class ProjectRecyclerAdapter extends FirebaseRecyclerAdapter<ProjectRecyclerAdapter.ViewHolder, ToneMatrix> {
    Query q;


    public ProjectRecyclerAdapter(Query query, Class<ToneMatrix> itemClass) {
        super(query, itemClass);
        q = query;
    }

    public ProjectRecyclerAdapter(Query query, Class<ToneMatrix> itemClass, @Nullable ArrayList<ToneMatrix> items, @Nullable ArrayList<String> keys) {
        super(query, itemClass, items, keys);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View loopCell = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.project_icon,parent,false);
        return new ViewHolder(loopCell);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ToneMatrix item = getItem(position);
        holder.tvName.setText("Loop name");
        holder.cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.cell.getContext();
                int pos = getPositionForItem(item);
                Intent toLoopActivity = new Intent(context, LoopActivity.class);
                toLoopActivity.putExtra("key", getKeys().get(pos));
                LoopGrid.getInstance().populateGrid(item);
                context.startActivity(toLoopActivity);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getPositionForItem(item);
                final String key = getKeys().get(pos);
                q.getRef().child(key).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        itemRemoved(item, key, holder.getAdapterPosition());
                    }
                });
            }
        });
    }

    @Override
    protected void itemAdded(ToneMatrix item, String key, int position) {
        notifyDataSetChanged();
        Log.d("MyAdapter", "Added a new item to the adapter.");
    }

    @Override
    protected void itemChanged(ToneMatrix oldItem, ToneMatrix newItem, String key, int position) {
        notifyDataSetChanged();
        Log.d("MyAdapter", "Changed a new item to the adapter.");
    }

    @Override
    protected void itemRemoved(ToneMatrix item, String key, int position) {
        notifyItemRemoved(position);
        Log.d("MyAdapter", "Deleted a loop @"+position);
    }

    @Override
    protected void itemMoved(ToneMatrix item, String key, int oldPosition, int newPosition) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView playBtn;
        private TextView tvName;
        private boolean isPlaying;
        private RelativeLayout cell;
        private TextView delete;

        public ViewHolder(View itemView) {
            super(itemView);
            isPlaying = false;
            delete = (TextView) itemView.findViewById(R.id.deleteText);
            cell = (RelativeLayout) itemView.findViewById(R.id.cellView);
            playBtn = (ImageView) itemView.findViewById(R.id.playButton);
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