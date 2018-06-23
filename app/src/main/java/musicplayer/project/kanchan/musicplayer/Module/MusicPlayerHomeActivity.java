package musicplayer.project.kanchan.musicplayer.Module;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

import musicplayer.project.kanchan.musicplayer.Adapter.SongListAdapter;
import musicplayer.project.kanchan.musicplayer.R;
import musicplayer.project.kanchan.musicplayer.Util.IactiononClick;
import musicplayer.project.kanchan.musicplayer.Util.Util;

public class MusicPlayerHomeActivity extends Activity implements IactiononClick {
    private RecyclerView rv_songlist;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<File> songlist;


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayerhome);
        //songlist = FindSongByRead(Environment.getRootDirectory());
        SetExternalStorageActivity();
        initView();
        setAdapter();
    }

    private void SetExternalStorageActivity() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
        {
            ///mounted
            songlist= FindSongByRead(android.os.Environment.getExternalStorageDirectory());
        }else{
            Util.showToast(this,"SD card not mounted");
        }
    }

    private ArrayList<File> FindSongByRead(File root){
        ArrayList<File>SongList = new ArrayList<File>();
        File[]files = root.listFiles();
        for(File singlefile : files){
            if(singlefile.isDirectory() && !singlefile.isHidden()){
                SongList.addAll(FindSongByRead(singlefile)); //<--- Recursive Call--->>
            }else{
                if(singlefile.getName().endsWith(".mp3")){
                    SongList.add(singlefile);
                }
            }
        }
        return SongList;
    }

    private void initView() {
        rv_songlist = (RecyclerView)findViewById(R.id.rv_songlist);
    }

    public void setAdapter(){
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        SongListAdapter songListAdapter = new SongListAdapter(this,songlist,this);
        rv_songlist.setAdapter(songListAdapter);
        rv_songlist.setLayoutManager(layoutManager);
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_recyclrview);
        rv_songlist.addItemDecoration(new musicplayer.project.kanchan.musicplayer.View.DividerItemDecoration(dividerDrawable));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void OnActionClick(int position) {
        Intent songdetails = new Intent(this,MusicPlayerDetails.class)
                .putExtra("pos",position)
                .putExtra("songlist",songlist);
        startActivity(songdetails);

    }
}
