package com.example.maxcembalest.loops.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maxcembalest.loops.R;
import com.example.maxcembalest.loops.data.Song;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chase on 11/21/16.
 */

public class ProjectRecyclerAdapter extends
        RecyclerView.Adapter<ProjectRecyclerAdapter.ViewHolder> {

    private List<Song> songList;
    public ProjectRecyclerAdapter() {
        songList = new ArrayList<Song>();
        for (int i = 0; i < 20; i++) {
            songList.add(new Song("Song"+i));
        }

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
