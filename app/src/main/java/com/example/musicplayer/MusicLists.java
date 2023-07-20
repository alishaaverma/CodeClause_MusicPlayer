package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MusicLists extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView noMusic;
    ArrayList<AudioModel>songslists=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_lists);

        recyclerView=findViewById(R.id.recylerview);
        noMusic=findViewById(R.id.no_song);

        if (checkPermission()==false){
            requestPermission();
            return;
        }

        String[] projection={
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        String selection=MediaStore.Audio.Media.IS_MUSIC+"!=0";
        Cursor cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
        while (cursor.moveToNext()) {
            AudioModel songsdata = new AudioModel(cursor.getString(1), cursor.getString(0), cursor.getString(2));
            if (new File(songsdata.getPath()).exists()){
                songslists.add(songsdata);
        }
        }
        if (songslists.size()==0){
            noMusic.setVisibility(View.VISIBLE);
        }else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MusicListAdapter(songslists,getApplicationContext()));
        }
    }
    boolean checkPermission(){
        int result= ContextCompat.checkSelfPermission(MusicLists.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else {
            return false;
        }
    }

    void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(MusicLists.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(MusicLists.this,"please allow from setting",Toast.LENGTH_SHORT).show();
        }else {
            ActivityCompat.requestPermissions(MusicLists.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerView!=null){
            recyclerView.setAdapter(new MusicListAdapter(songslists,getApplicationContext()));
        }
    }
}