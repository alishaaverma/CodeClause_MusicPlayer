package com.example.musicplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{

    ArrayList<AudioModel> songslist;
    Context context;

    public MusicListAdapter(ArrayList<AudioModel> songslist, Context context) {
        this.songslist = songslist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recyler_item,parent,false);
        return new MusicListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AudioModel songData=songslist.get(position);
        holder.TitletextView.setText(songData.getTitle());

        if (MyMediaPlayer.currentIndex==position){
            holder.TitletextView.setTextColor(Color.parseColor("#FF0000"));
        }else {
            holder.TitletextView.setTextColor(Color.parseColor("#000000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex=position;
                Intent intent=new Intent(context,MusicPage.class);
                intent.putExtra("LIST",songslist);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView TitletextView;
        ImageView iconImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            TitletextView=itemView.findViewById(R.id.music_title);
            iconImageView=itemView.findViewById(R.id.icon_view);
        }
    }
}
